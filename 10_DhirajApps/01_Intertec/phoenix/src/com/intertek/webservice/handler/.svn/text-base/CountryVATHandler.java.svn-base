package com.intertek.webservice.handler;

import com.intertek.entity.CountryVAT;
import com.intertek.entity.CountryVATId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;

public class CountryVATHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof CountryVAT)
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      countryService.saveCountryVAT((CountryVAT)entity);

      return entity;
    }

    return null;
  }
  
	public String getType() {
		return "COUNTRY_VAT";
	}

	public String getId(Object entity) {
		if(entity instanceof CountryVAT){
			CountryVATId id=((CountryVAT)entity).getCountryVATId();
			return
			  "("+
			  id.getCountryCode()+", "+
			  id.getStateCode()+", "+
			  id.getEffDate()+
			  ")";
		}
		return null;
	} 
}
