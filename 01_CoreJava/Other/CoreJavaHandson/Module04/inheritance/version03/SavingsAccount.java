package Module04.inheritance.version03;

public class SavingsAccount extends BankAccount {
  	private boolean isSalaried;
  	
  	SavingsAccount (float amount,String name, boolean isSal) {
  		super (amount,name);
		isSalaried = isSal;
  	}
  	
  	public void show(){
  		System.out.println("Is the Account Salaried ? "+isSalaried);
  	}
}
