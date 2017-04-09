package com.intertek.phoenix.webservices;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.externalEntity.DCAppJobOrderX;
import com.intertek.phoenix.externalEntity.response.OrderResultX;
import com.intertek.phoenix.job.JobOrderService;
/*
 * Reply
 * Returns a Phoenix Job Number
 * @author Eric.Nguyen
 */
public class DCAppJobOrderEndpoint extends BaseJDomPayloadEndpoint<DCAppJobOrderX, OrderResultX>{
    private JobOrderService jobOrderService;
    
    @Override
    public OrderResultX process(DCAppJobOrderX obj) throws PhoenixException{
        System.out.println("Got DCAppJobOrderEndpoint");
        CEJobOrder jo=jobOrderService.createJobOrder(obj.convert());        
        OrderResultX result=new OrderResultX();
        result.setJobNumber(jo.getJobNumber());        
        return result;
    }

    @Override
    public String getId(DCAppJobOrderX obj) {
        return obj.getProjectNumber();
    }

    public JobOrderService getJobOrderService() {
        return jobOrderService;
    }

    public void setJobOrderService(JobOrderService jobOrderService) {
        this.jobOrderService = jobOrderService;
    }

}
