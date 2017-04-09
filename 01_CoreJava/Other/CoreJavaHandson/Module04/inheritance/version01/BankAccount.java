package Module04.inheritance.version01;

public class BankAccount {
	private int accNo; 
 	public String name;
 	private float curBal;
 	private static int idNum = 1;

 	public BankAccount () {
 		accNo = idNum++;
    	curBal = 0;
  	}
  	
 	public void deposit (float amt) {
    	curBal += amt; 
  	}
  	
 	public void withdraw (float amt) { 
    	curBal -= amt; 
  	}
  	
 	public float getCurBal () { 
    	return curBal; 
  	}
}

