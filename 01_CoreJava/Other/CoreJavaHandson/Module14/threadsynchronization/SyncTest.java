package Module14.threadsynchronization;

class Account{
	private int accNo;
	private String name;
	private float balance;

	Account(int acc,String nm,float b){
		accNo = acc;
		name = nm;
		balance = b;
	}
	public float getBal(){
		try{
		System.out.println("Getting Balance ................... ");
		Thread.sleep(1000);
		}
		catch(InterruptedException ioe){}
		return balance;

	} 
	public void setBal(float b){
		try{
		System.out.println("Setting balance......................");
		Thread.sleep(1000);
		balance = b;
		}
		catch(InterruptedException ioe){}
	}
	public  synchronized void deposit(float amt){
		String ThreadName = Thread.currentThread().getName();
		System.out.println("Depositing by : "+ThreadName);
		System.out.println("Balance got : "+getBal());
		float curBal = balance +amt;
		setBal(curBal);
		System.out.println("Balance set to : "+curBal);
		System.out.println("=================================");
	}

}
class Transaction extends Thread{
	Account accObj;
	
	Transaction (Account ac){
		accObj = ac;
	}
	public void run(){
		accObj.deposit(5000);
	}
}
class SyncTest {
	public static void main(String[] args){
		Account a = new Account(1234,"Ramesh",1000);

		Transaction t1 = new Transaction(a);
		Transaction t2 = new Transaction(a);
		Transaction t3 = new Transaction(a);
		
		t1.start();
		t2.start();
		t3.start();
		
		try{
		t1.join();    t2.join();       t3.join();
		}
		catch(InterruptedException ioe){
			ioe.printStackTrace();
		}
		System.out.println("--------------End-----------------");
	}
}
 

