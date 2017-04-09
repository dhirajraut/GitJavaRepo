package Module06.nestedclasses.staticnestedclasses;

class BankAccount {
	private float curBal;
 	private String name;
  	  	
  	public static class Permission {
    			public static boolean canDeposit;
    			public boolean canWithdraw;
   	}
  	BankAccount () {
  			name = "Aditi";
    		curBal = 5000;
    		Permission.canDeposit = true; 		
    }
  	void show(){
  		System.out.println("Fields from outer class....");
  		System.out.println("The currentBalance : "+curBal+"\tName : "+name);
  		System.out.println("Fields from Static Nested Class : ");
  		System.out.println("CanDeposit ?"+Permission.canDeposit);
  		
  	}
}

