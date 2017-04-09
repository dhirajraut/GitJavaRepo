package Module05.abstractclasses;

public class SavingsAccount extends BankAccount{
	
	public SavingsAccount (int id, float balance) {
		super (id, balance);
	}
	public float calculateInterest() {
		return balance * 0.10f;
	}
}


