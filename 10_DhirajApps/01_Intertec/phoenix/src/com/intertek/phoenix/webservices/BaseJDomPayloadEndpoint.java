package com.intertek.phoenix.webservices;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

import com.intertek.entity.WebServiceEntityInbound;
import com.intertek.entity.WebServiceInbound;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.esb.ESBService;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.tool.transformer.DataTransformer;

/*
 * @author Eric.Nguyen
 */
public abstract class BaseJDomPayloadEndpoint<Input, Output> extends AbstractJDomPayloadEndpoint {
    private DataTransformer dataTransformer;
    private OxmManager oxmManager;
    private ESBService esbService;
    private String type;

    public BaseJDomPayloadEndpoint() {
        super();
    }

    /*
     * perform the action with the given object (transformed and mapped by
     * castor) and return the result object
     */
    public abstract Output process(Input obj) throws PhoenixException;

    public abstract String getId(Input obj);
    
    protected Output processAll(List<Input> objs) throws PhoenixException{
        // this method is created for handling BillingEvents,
        // it does nothing for other inputs by default
        return null; 
    }

    @SuppressWarnings("unchecked")
    protected Element invokeInternal(Element element) throws Exception {
        Element toReturn = null;
        try {
            WebServiceInbound mainMsg = new WebServiceInbound();
            mainMsg.setType(this.getType());
            mainMsg.setContent(convertToXml(element));
            mainMsg.setReceivedTime(new Date(System.currentTimeMillis()));
            mainMsg=this.getEsbService().saveOrUpdate(WebServiceInbound.class, mainMsg);            
            
            Document doc = doTransform(element.getDocument());
            List<Element> children = doc.getRootElement().getChildren();
            List<Input> objects = new ArrayList<Input>();
            for (Element el : children) {
                toReturn = invokeTransformedElement(el, mainMsg, objects);
            }
            
            // RQ: convert all elements to objects, and process all the
            // objects at once. This logic needs to be reviewed and confirmed
            // by Eric.
            processAll(objects);
            // End RQ
            
            return toReturn;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected Element invokeTransformedElement(Element element, WebServiceInbound mainMsg, List<Input> inputs) throws Exception {
        WebServiceEntityInbound log = new WebServiceEntityInbound();
        Element toReturn = null;
        try {
            log.setMainMsg(mainMsg);
            log.setStatus(true);
            log.setType(this.getType());
            log.setContent(convertToXml(element));
            Input obj = this.convertToObject(element);
            log.setEntityKey(this.getId(obj));
            Output result = process(obj);
            toReturn = convertToElement(result);
            log.setResult(convertToXml(toReturn.getDocument()));
            
            // RQ: save the converted object (Input) to be batch processed
            if(inputs != null){
                inputs.add(obj);
            }
            
            return toReturn;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.setStatus(false);
            Throwable t = e.getCause();
            log.setMessage(e.getMessage() + "<cause>" + (t == null ? "" : t.getCause()) + "</cause>");
            throw e;
        }
        finally {
            log.setCreateTime(new Date(System.currentTimeMillis()));
            ServiceManager.getEsbService().saveOrUpdate(WebServiceEntityInbound.class, log);
        }
    }

    public DataTransformer getDataTransformer() {
        return dataTransformer;
    }

    public void setDataTransformer(DataTransformer dataTransformer) {
        this.dataTransformer = dataTransformer;
    }

    public OxmManager getOxmManager() {
        return oxmManager;
    }

    public void setOxmManager(OxmManager oxmManager) {
        this.oxmManager = oxmManager;
    }

    public Element convertToElement(Object obj) throws IOException {
        Document doc = oxmManager.marshal(obj);
        doc = doTransform(doc);
        return doc.getRootElement();
    }

    @SuppressWarnings("unchecked")
    public Input convertToObject(Element element) {
        Input obj = (Input) oxmManager.unmarshal(element);
        return obj;
    }

    private Document doTransform(Document doc) throws IOException {
        if (dataTransformer != null) {
            System.out.println("before:" + convertToXml(doc));
            doc = dataTransformer.transform(doc);
            System.out.println("after:" + convertToXml(doc));
        }
        return doc;
    }

    public String convertToXml(Document doc) throws IOException {
        Document myDoc = doc;
        StringWriter sw = new StringWriter();
        XMLOutputter xmlOut = new XMLOutputter();
        xmlOut.output(myDoc, sw);
        String content = sw.toString();
        return content;
    }

    public String convertToXml(Element element) throws IOException {
        StringWriter sw = new StringWriter();
        XMLOutputter xmlOut = new XMLOutputter();
        xmlOut.output(element, sw);
        return sw.toString();
    }

    public ESBService getEsbService() {
        return esbService;
    }

    public void setEsbService(ESBService esbService) {
        this.esbService = esbService;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
