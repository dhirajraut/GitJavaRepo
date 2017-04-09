package servicepack;

import beanpack.AccountPojo;

public interface IBankService {

	public boolean deposit(AccountPojo acct,double amt);
	public boolean withdraw(AccountPojo acct,double amt);
	public boolean transferFunds(AccountPojo accObjFrom,AccountPojo accObjTo,double amt);
}
