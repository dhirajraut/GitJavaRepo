package com.intertek.phoenix.webservices;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.BillingEvent;
import com.intertek.phoenix.entity.BillingEventItem;
import com.intertek.phoenix.entity.BillingLineDistribution;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobOrderType;
import com.intertek.phoenix.entity.OrderOrigin;
import com.intertek.phoenix.externalEntity.BillingLineDistributionX;
import com.intertek.phoenix.externalEntity.PSBillableItemX;
import com.intertek.phoenix.externalEntity.response.BillableItemsResultX;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.search.SearchService;

/*
 * Subscriber
 * @author Eric.Nguyen
 * @author Richard.Qin
 */
public class PeopleSoftBillableItemsProjectEndpoint extends BaseJDomPayloadEndpoint<PSBillableItemX, BillableItemsResultX> {
    private SearchService searchService;

    // TODO
    @Override
    public BillableItemsResultX process(PSBillableItemX obj) throws PhoenixException {
        System.out.println("Go PeopleSoftBillableItemsProjectEndpoint");

        BillingEventItem be = convert(/*this.getSearchService(),*/ obj);

        this.getEsbService().saveOrUpdate(BillingEventItem.class, be);

        BillableItemsResultX result = new BillableItemsResultX();
        result.setStatusCode("success");
        return result;
    }

    @Override
    protected BillableItemsResultX processAll(List<PSBillableItemX> objs) throws PhoenixException{
        Map<String, BillingEvent> billingEvents = convert(objs);
        
        JobOrderService jobService = ServiceManager.getJobOrderService();
        for(BillingEvent billingEvent: billingEvents.values()){
            jobService.processBillingEvent(billingEvent);
            DaoManager.getDao(BillingEvent.class).saveOrUpdate(billingEvent);
        }
        
        BillableItemsResultX result = new BillableItemsResultX();
        result.setStatusCode("success");
        return result;
    }
    
    @Override
    public String getId(PSBillableItemX obj) {
        return obj.getJobNumber();
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    // this method does not assume anything, that is, a list of billing events
    // items can be a bag of mix, and this method will sort them out into one
    // or more BillingEvents, each contains one or more billing event items.
    private Map<String, BillingEvent> convert(List<PSBillableItemX> objects) throws PhoenixException{
        Map<String, BillingEvent> billingEvents = new HashMap<String, BillingEvent>();
        for(PSBillableItemX x: objects){
            String jobNumber = x.getJobNumber();
            BillingEvent billingEvent = billingEvents.get(jobNumber);
            if(billingEvent == null){
                CEJobOrder jo = DaoManager.getDao(CEJobOrder.class).find(x.getJobNumber());
                // RQ: works for CE from ECS. For DC_app, I need to check
                // the logic TODO
                if(jo.getJobType() == JobOrderType.CE){ 
                    billingEvent = jo.getJobContract().getBillingEvent(x.getTemporaryInvoiceNumber());
                    if(billingEvent == null){
                        billingEvent = new BillingEvent();
                        billingEvent.setPsInvoiceNumber(x.getTemporaryInvoiceNumber());
                        jo.getJobContract().addBillingEvents(billingEvent);
                    }
                    billingEvents.put(jobNumber, billingEvent);
                }
            }
            if(billingEvent != null){
                // it would be better if x is converted to JOLI directly,
                // but it only works for PeopleSoft, not DC app. Therefore
                // it is stuck with BillingEventItem here. However, once DC
                // requirement becomes clear, it may still happen.
                BillingEventItem beItem = new BillingEventItem();
                beItem.setPsLineNumber(x.getJobLineNumber());
                beItem.setDescription(x.getLineDescription());
                beItem.setUnitAmount(x.getUnitAmt());
                beItem.setQuantity(x.getQuantity());
                beItem.setBillingCurrencyCD(x.getBillingCurrencyCd());
                beItem.setInvoiceAmount(x.getInvoiceAmt());
                beItem.setBillingLineDistributions(convert(x.getBillingLineDistribution(), beItem));
                // link it to the associated BillingEvent
                billingEvent.addBillingEventItem(beItem);
            }
            else{
                throw new PhoenixException("Failed to process BillingEvent. " + 
                                           "Please make sure that the JobOrderNumber is valid and the JobType is CE.");
            }
        }
        
        return billingEvents;
    }
    
    private BillingEventItem convert(/*SearchService searchService,*/ PSBillableItemX x) throws PhoenixException{
        BillingEventItem be = new BillingEventItem();
        
//        be.setPsInvoiceNumber(this.getTemporaryInvoiceNumber());
        be.setPsLineNumber(x.getJobLineNumber());
        be.setDescription(x.getLineDescription());
        be.setUnitAmount(x.getUnitAmt());
        be.setQuantity(x.getQuantity());
        be.setBillingCurrencyCD(x.getBillingCurrencyCd());
        be.setInvoiceAmount(x.getInvoiceAmt());
        
        //CEJobOrder jo=new CEJobOrder();
        //jo.setJobNumber(this.getJobNumber());
        //jo.setBuName(this.getBusinessUnit());
        
        HashMap<String, String> criteria=new HashMap<String, String>();
        criteria.put("jobNumber", x.getJobNumber());
        List<CEJobOrder> list=searchService.search(CEJobOrder.class, criteria);
        if(list==null || list.isEmpty()){
            throw new PhoenixException("job#="+x.getJobNumber()+" not found!");
        }
//        CEJobOrder jo=list.get(0);
//        be.setOrder(jo);
        be.setBillingLineDistributions(convert(x.getBillingLineDistribution(), be));
        
        return be;
    }
    
    private Set<BillingLineDistribution> convert(List<BillingLineDistributionX> billingDistribution, BillingEventItem bei){
        Set<BillingLineDistribution> toReturn=new HashSet<BillingLineDistribution>();
        for(BillingLineDistributionX bd:billingDistribution){
            BillingLineDistribution bld=bd.convert();
            bld.setBillingEventItem(bei);
            toReturn.add(bld);
        }
        return toReturn;
    }
    
}
