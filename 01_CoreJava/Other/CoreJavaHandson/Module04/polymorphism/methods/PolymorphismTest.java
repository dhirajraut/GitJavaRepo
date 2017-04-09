package Module04.polymorphism.methods;

import Module04.inheritance.methodoverriding.OverridingTest;

class BankAccount {
	protected float curBal;
  	protected String name;
  	
  	public BankAccount(String n,float bal){
  		name = n;
  		curBal = bal;
   	}
  	
  	public void print () {
  		System.out.println ("customer : " + name);
    	System.out.println ("Balance  : " + curBal);
    	
  	}
}

class SavingsAccount extends BankAccount {
	private boolean isSalaried;
	
	SavingsAccount(String name, float bal,boolean sal){
		super(name,bal);
		isSalaried = sal;
	}
  	public void print (){
		super.print();
		System.out.println ("Is Salaried : " + isSalaried);
		
  	}
}
class CurrentAccount extends BankAccount{
	private float overDraftLimit;
	
	CurrentAccount(String name, float bal,float odl){
		super(name,bal);
		overDraftLimit = odl;
	}
	public void print (){
		super.print();
		System.out.println ("OverDraftLimit : " + overDraftLimit);
	}
}
class PolymorphismTest {
	public static void main (String [] args) {
   			BankAccount ba[] = {new SavingsAccount ("Amar",10000,true),
                                new CurrentAccount ("Akbar",20000,5000)
   							   };
    System.out.println ("Printing polymorphically");
    for(int i=0;i<ba.length;i++) {
	      ba[i].print();
	      System.out.println ("-----------------------------");
    }
  }
}
