/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.esb;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.entity.ControlSwitch;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.Collectable;
import com.intertek.phoenix.esb.dataCollector.ConvertibleDataCollector;
import com.intertek.phoenix.esb.dataCollector.DataCollector;
import com.intertek.phoenix.util.HibernateUtil;
import com.intertek.service.WSService;
import com.intertek.webservice.outbound.OutboundWebService;
import com.intertek.webservice.outbound.WSOutboundContext;

/**
 * 
 * @author Eric.Nguyen
 */
public class EntityPublisher implements OutboundWebService, Runnable {
    protected Map<String, DataCollector<?>> dataCollectors;
    protected ESBService esbService;
    protected WSService wsService;

    protected int numInPage;

    private Set<String> queues;
    private boolean enableTrigger = false;

    public EntityPublisher() {
        if (enableTrigger) {
            queues = new HashSet<String>();
            Thread queueThread = new Thread(this);
            queueThread.start();
        }
    }

    public void addToQueue(String entityName) {
        if (enableTrigger) {
            synchronized (queues) {
                queues.add(entityName);
                queues.notifyAll();
            }
        }
    }

    public Map<String, DataCollector<?>> getDataCollectors() {
        return dataCollectors;
    }

    public void setDataCollectors(Map<String, DataCollector<?>> dataCollectors) {
        this.dataCollectors = dataCollectors;
    }

    public int getNumInPage() {
        return numInPage;
    }

    public void setNumInPage(int numInPage) {
        this.numInPage = numInPage;
    }

    public ESBService getEsbService() {
        return esbService;
    }

    public void setEsbService(ESBService esbService) {
        this.esbService = esbService;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean sendService(WSOutboundContext wsOutboundContext) {
        Set<String> entityNames = getDataCollectors().keySet();
        boolean result = true;
        for (String entityName : entityNames) {
            try {
                HibernateUtil.beginTransaction();
                ControlSwitch cs = wsService.getOutboundWSControlSwitch(entityName);
                boolean isEnabled = cs == null || "enabled".equalsIgnoreCase(cs.getFlag() + "");
                if (isEnabled) {
                    result = result && this.publish(getNumInPage(), dataCollectors.get(entityName));
                }
            }
            catch (Exception e) {
                result = false;
                e.printStackTrace();
            }
            finally{
                HibernateUtil.endTransaction(false);
            }
            
        }
        return result;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Running ...");
            Iterator<String> itr = queues.iterator();
            while (itr.hasNext()) {
                try {
                    String entityName = itr.next();
                    synchronized (queues) {
                        queues.remove(entityName);
                    }
                    ControlSwitch cs = wsService.getOutboundWSControlSwitch(entityName);
                    boolean isEnabled = cs == null || "enabled".equalsIgnoreCase(cs.getFlag() + "");
                    if (isEnabled) {
                        this.publish(getNumInPage(), dataCollectors.get(entityName));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Sleeping ...");
            try {
                synchronized (queues) {
                    queues.wait();
                }
            }
            catch (Exception e) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Collectable> boolean publish(int numInPage, DataCollector<T> collector) throws PhoenixException {
        if (collector == null) {
            System.out.println("No DataCollector");
            return false;
        }

        List<T> objs = collector.getObjects(numInPage);
        for (T obj : objs) {
            boolean failed = false;
            Object response = null;
            try {
                Object toSend = obj;
                if (ConvertibleDataCollector.class.isAssignableFrom(collector.getClass())) {
                    ConvertibleDataCollector c = (ConvertibleDataCollector) collector;
                    toSend = c.convert(obj);
                }

                response = getEsbService().sendExtObj(toSend);
            }
            catch (PhoenixException e) {
                failed = true;
                throw e;
            }
            finally {
                if (!failed && response != null) {
                    collector.updateEntityFlag(obj, true);
                }
                else {
                    collector.updateEntityFlag(obj, false);
                }
            }
        }
        return true;
    }

    public WSService getWsService() {
        return wsService;
    }

    public void setWsService(WSService wsService) {
        this.wsService = wsService;
    }
}
