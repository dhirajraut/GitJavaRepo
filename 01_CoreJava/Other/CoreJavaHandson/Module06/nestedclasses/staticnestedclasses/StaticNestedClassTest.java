package Module06.nestedclasses.staticnestedclasses;

class StaticNestedClassTest { 
	public static void main (String [] args) { 
   		BankAccount b = new BankAccount();	
		
   		BankAccount.Permission.canDeposit = true;
    	BankAccount.Permission p = new BankAccount.Permission ();
    	p.canWithdraw = true;
    	
    	b.show();
    	System.out.println("Instance field of nested class : "+p.canWithdraw);
  	}
}

