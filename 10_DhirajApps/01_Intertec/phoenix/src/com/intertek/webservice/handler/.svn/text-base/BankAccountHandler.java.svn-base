package com.intertek.webservice.handler;

import com.intertek.entity.BankAccount;
import com.intertek.entity.BankAccountId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.BankAccountService;


public class BankAccountHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof BankAccount)
    {
      BankAccountService bankAccountService = (BankAccountService)ServiceLocator.getInstance().getBean("bankAccountService");
      bankAccountService.saveBankAccount((BankAccount)entity);

      return entity;
    }

    return null;
  }
  
	public String getType() {
		return "BANK_ACCOUNT";
	}

	public String getId(Object entity) {
		if(entity instanceof BankAccount){
			BankAccountId id=((BankAccount)entity).getBankAccountId();
			return
			  "("+
			  id.getBusinessUnitName()+", "+
			  id.getBankCode()+", "+
			  id.getBankAcctCode()+
			  ")";
		}
		return null;
	}  
}
