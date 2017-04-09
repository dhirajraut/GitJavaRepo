package Module03.staticmembers;

class BankAccount {
  	private int accNo;
 	private float curBal;
 	private static int idNum =1000;
  	
	public BankAccount () {
			accNo = idNum++;
    			curBal = 0;
  	}
  
	public static int getIdNum () {
    			return idNum; 
  	}

	public static void main (String [] args) {
	    BankAccount ba = new BankAccount ();
	    System.out.println (BankAccount.getIdNum ());
	}
}