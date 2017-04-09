package com.intertek.phoenix.webservices;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.externalEntity.CreditHoldX;
import com.intertek.phoenix.externalEntity.response.AcknowledgementX;
/*
 * Subscriber
 * @author Eric.Nguyen
 */
public class UpdateCreditHoldEndpoint extends BaseJDomPayloadEndpoint<CreditHoldX, AcknowledgementX>{

    @Override
    public AcknowledgementX process(CreditHoldX obj) throws PhoenixException{
        System.out.println("Got UpdateCreditHoldEndpoint");
        
        String query="update Customer set creditHoldInd=?, updatedTime=?, upByUser.loginName=? where custCode=?";
        Object[] params=new Object[]{(obj.getCreditHoldFlag()+"").equalsIgnoreCase("Y")?"Y":"N", obj.getDateTime(), obj.getOperatorId(), obj.getCustomerId()};
        int count=getEsbService().executeQuery(obj.getClass(), query, params);
        
        if(count<=0){
            throw new PhoenixException("Customer with custCode="+obj.getCustomerId()+" not found!");
        }
        AcknowledgementX result=new AcknowledgementX();
        result.setStatusCode("success");        
        return result;
    }

    @Override
    public String getId(CreditHoldX obj) {
        return obj.getCustomerId();
    }
}
