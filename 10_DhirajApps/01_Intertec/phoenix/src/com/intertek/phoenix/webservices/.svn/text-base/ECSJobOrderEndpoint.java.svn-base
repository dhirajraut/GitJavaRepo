package com.intertek.phoenix.webservices;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.externalEntity.ECSJobOrderX;
import com.intertek.phoenix.externalEntity.response.OrderResultX;
import com.intertek.phoenix.job.JobOrderService;
/*
 * Reply
 * Returns a Phoenix Job Number
 * @author Eric.Nguyen
 */
public class ECSJobOrderEndpoint extends BaseJDomPayloadEndpoint<ECSJobOrderX, OrderResultX>{
    private JobOrderService jobOrderService;
    
    @Override
    public OrderResultX process(ECSJobOrderX obj) throws PhoenixException{
        System.out.println("Got ECSJobOrderEndpoint");
        CEJobOrder jo=jobOrderService.createJobOrder(obj.convert());        
        OrderResultX result=new OrderResultX();
        result.setJobNumber(jo.getJobNumber());        
        return result;
    }

    @Override
    public String getId(ECSJobOrderX obj) {
        return obj.getOrderNumber();
    }

    public JobOrderService getJobOrderService() {
        return jobOrderService;
    }

    public void setJobOrderService(JobOrderService jobOrderService) {
        this.jobOrderService = jobOrderService;
    }

}
