package Module05.abstractclasses;

public class AbstractTest {
	public static void main (String [] args ) {
		showInterest (new SavingsAccount (3,5000));
	    showInterest(new LoanAccount (4,6000));
	    showInterest(new CurrentAccount (5,7000));
	}
	public static void showInterest(BankAccount e) {
		System.out.println ("Interest :" + e.calculateInterest ());
  	}
}



