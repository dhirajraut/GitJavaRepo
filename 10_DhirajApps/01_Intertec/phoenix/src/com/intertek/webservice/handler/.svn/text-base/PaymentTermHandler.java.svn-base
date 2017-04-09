package com.intertek.webservice.handler;

import com.intertek.entity.PaymentTerm;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.PaymentTermService;

public class PaymentTermHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof PaymentTerm)
    {
      PaymentTermService paymentTermService = (PaymentTermService)ServiceLocator.getInstance().getBean("paymentTermService");
      paymentTermService.savePaymentTerm((PaymentTerm)entity);

      return entity;
    }

    return null;
  }
  
	public String getType() {
		return "PAYMENT_TERM";
	}

	public String getId(Object entity) {
		if(entity instanceof PaymentTerm){
			return ((PaymentTerm)entity).getPaymentTermsCode();
		}
		return null;
	} 
}
