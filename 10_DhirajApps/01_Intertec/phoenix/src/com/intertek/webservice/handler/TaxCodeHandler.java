package com.intertek.webservice.handler;

import com.intertek.entity.TaxCode;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TaxService;


public class TaxCodeHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof TaxCode)
    {
      TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
      taxService.addTaxCode((TaxCode)entity);
    }

    return null;
  }
  
	public String getType() {
		return "TAX_CODE";
	}

	public String getId(Object entity) {
		if(entity instanceof TaxCode){
			return ((TaxCode)entity).getTaxCodeHeader();
		}
		return null;
	} 
}
