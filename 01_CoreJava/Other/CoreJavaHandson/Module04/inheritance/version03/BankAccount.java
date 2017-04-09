package Module04.inheritance.version03;

public class BankAccount {
	protected float curBal;
  	protected String name;
  	
  	public BankAccount (float amt,String name) {
  		curBal = amt;
  		this.name = name;
  	}
  	public void display(){
  		System.out.println("Name : "+name+"\tCurrentBalance : "+curBal);
  	}
}


