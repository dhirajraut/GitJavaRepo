package Module04.inheritance.version01;

public class Banking {
	public static void main (String [] args) {
    	SavingsAccount sa = new SavingsAccount();
    	sa.deposit (5000);
    	System.out.println ("Balance : " + sa.getCurBal ());
    	System.out.println ("Annual interest : " +sa.isSalaryAccount());
  	}
}

