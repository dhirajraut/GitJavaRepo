package Module04.inheritance.version02;

public class BankAccount {
	protected float curBal;
	protected String name;
	
	public BankAccount () {
		curBal = 0;
	}
	
	public void display(){
		System.out.println("Name : "+name+"\tCurrentBalance : "+curBal);
	}
}



