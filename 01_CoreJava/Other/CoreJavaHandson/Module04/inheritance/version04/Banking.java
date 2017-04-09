package Module04.inheritance.version04;

class BankAccount {
	protected float curBal;
 	protected String name;
 	
 	public BankAccount () {
    	curBal = 0;
   	}
}

class SavingsAccount extends BankAccount {
	private boolean isSalaried;
	
	SavingsAccount (float curBal) {
		this(curBal,true);
	}
	
	SavingsAccount (float curBal,boolean sal) {
		this.curBal = curBal;
		isSalaried = sal;
	}
	
	public void display(){
		System.out.println("Current Balance : "+curBal+"\tIs the account Salaried ? "+isSalaried);
	}
	
}

public class Banking {
	public static void main(String[] args){
		SavingsAccount sa1 = new SavingsAccount(10000);
		SavingsAccount sa2 = new SavingsAccount(12000,false);
		
		sa1.display();
		sa2.display();
	}
}
