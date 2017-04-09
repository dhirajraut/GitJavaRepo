package Module04.inheritance.methodoverriding;

import java.io.IOException;

class BankAccount {
	protected float curBal;
  	protected String name;
  	
  	BankAccount(String n,float bal){
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
	//	super.print();
		System.out.println ("Is Salaried : " + isSalaried);
		
  	}
}

public class OverridingTest {
	public static void main(String[] args){
		SavingsAccount sa = new SavingsAccount("Dinesh",10000,true);
		sa.print();
	}
}
