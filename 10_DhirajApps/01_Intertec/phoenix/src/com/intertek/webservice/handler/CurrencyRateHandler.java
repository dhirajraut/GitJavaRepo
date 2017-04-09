package com.intertek.webservice.handler;

import com.intertek.entity.CurrencyRate;
import com.intertek.entity.CurrencyRateId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CurrencyRateService;


public class CurrencyRateHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof CurrencyRate)
    {
      CurrencyRateService currencyRateService = (CurrencyRateService)ServiceLocator.getInstance().getBean("currencyRateService");
      currencyRateService.saveCurrencyRate((CurrencyRate)entity);
      return entity;
    }

    return null;
  }

	public String getType() {
		return "CURRENCY_RATE";
	}

	public String getId(Object entity) {
		if(entity instanceof CurrencyRate){
			CurrencyRateId id=((CurrencyRate)entity).getCurrencyRateId();
	      return
	    	  "("+
		      id.getRateIndex()+", "+
		      id.getTerm()+", "+
		      id.getFromCurrency()+", "+
		      id.getToCurrency()+", "+
		      id.getType()+
		      ")";
		}
		return null;
	}
}
