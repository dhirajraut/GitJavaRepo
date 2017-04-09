package Module04.inheritance.version01;


public class SavingsAccount extends BankAccount {

	private boolean isSalaryAcc;
  	
	public void setSalaried (boolean is) {
 		isSalaryAcc = is;
	}
 	public boolean isSalaryAccount() {
    	return isSalaryAcc;
  	}
}

