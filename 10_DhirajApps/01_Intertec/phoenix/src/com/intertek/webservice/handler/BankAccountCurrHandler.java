package com.intertek.webservice.handler;

import com.intertek.entity.BankAccountCurr;
import com.intertek.entity.BankAccountCurrId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.BankAccountCurrService;


public class BankAccountCurrHandler implements Handler
{
  public Object handle(Object entity)
  {
    if(entity instanceof BankAccountCurr)
    {
      BankAccountCurrService bankAccountCurrService = (BankAccountCurrService)ServiceLocator.getInstance().getBean("bankAccountCurrService");
      bankAccountCurrService.saveBankAccountCurr((BankAccountCurr)entity);
      return entity;
    }

    return null;
  }

	public String getType() {
		return "BANK_ACCT_CURR";
	}

	public String getId(Object entity) {
		if(entity instanceof BankAccountCurr){
	      BankAccountCurrId id=((BankAccountCurr)entity).getBankAccountCurrId();
	      return
	    	  "("+
		      id.getBusinessUnitName()+", "+
		      id.getBankCode()+", "+
		      id.getBankAcctCode()+", "+
		      id.getCurrencyCode()+
		      ")";
		}
		return null;
	}
}
