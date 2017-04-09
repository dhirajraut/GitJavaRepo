package Module05.abstractclasses;

public abstract class BankAccount {
	private int id;
 	protected float balance;
  	
	public BankAccount (int id, float balance) {
    	this.id = id;
    	this.balance = balance;
  	}
  	abstract float calculateInterest ();
}


