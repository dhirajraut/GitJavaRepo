package com.intertek.webservice.handler;

import com.intertek.entity.Bank;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.BankService;


public class BankHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof Bank)
    {
      BankService bankService = (BankService)ServiceLocator.getInstance().getBean("bankService");
      bankService.saveBank((Bank)entity);

      return entity;
    }

    return null;
  }
  
	public String getType() {
		return "BANK";
	}

	public String getId(Object entity) {
		if(entity instanceof Bank){
			return ((Bank)entity).getBankIdNumber();
		}
		return null;
	}   
}
