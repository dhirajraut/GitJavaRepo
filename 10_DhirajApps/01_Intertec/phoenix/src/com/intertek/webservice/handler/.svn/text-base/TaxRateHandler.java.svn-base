package com.intertek.webservice.handler;

import com.intertek.entity.TaxRate;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TaxService;


public class TaxRateHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof TaxRate)
    {
      TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
      taxService.addTaxRate((TaxRate)entity);
    }

    return null;
  }
  
	public String getType() {
		return "TAX_RATE";
	}

	public String getId(Object entity) {
		if(entity instanceof TaxRate){
			return ((TaxRate)entity).getTaxRateId().getTaxCode();
		}
		return null;
	} 
}
