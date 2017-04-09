package servicepack;

import beanpack.AccountPojo;

public class BankServiceImpl implements IBankService{

	public boolean deposit(AccountPojo acct, double amt) {
		System.out.println("Deposit Called");
		acct.setBalance(acct.getBalance()+amt);
		return false;
	}

	public boolean transferFunds(AccountPojo accObjFrom, AccountPojo accObjTo,
			double amt) {
		System.out.println("TransferFunds Called");
		withdraw(accObjFrom, amt);
		deposit(accObjTo, amt);
		return false;
	}

	public boolean withdraw(AccountPojo acct, double amt) {
		System.out.println("Withdraw Called");
		acct.setBalance(acct.getBalance()-amt);
		
		return false;
	}

}
