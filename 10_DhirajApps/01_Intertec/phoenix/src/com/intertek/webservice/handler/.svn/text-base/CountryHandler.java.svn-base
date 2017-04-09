package com.intertek.webservice.handler;

import com.intertek.entity.Country;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;


public class CountryHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof Country)
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      countryService.updateCountryHeaderOnly((Country)entity);

      return entity;
    }

    return null;
  }
  
	public String getType() {
		return "COUNTRY";
	}

	public String getId(Object entity) {
		if(entity instanceof Country){
			return ((Country)entity).getCountryCode();
		}
		return null;
	} 
}
