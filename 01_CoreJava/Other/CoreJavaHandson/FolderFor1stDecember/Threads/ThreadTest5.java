// This demonstrates synchronized statements
class BankAccount5
	{	int accNo;
		String accNm;
		public float accBal;
		
		public BankAccount5()
			{	}
		
		public BankAccount5(int n, String nm, float acb)
			{	accNo=n; accNm=nm;	accBal=acb; }
		
		public  void deposite(float amt)	//synchronized
			{	accBal+=amt;
				System.out.println(Thread.currentThread().getName()+" Deposited: "+amt);
				randomWait();
			}
		
		public  void withdraw(float amt)	//synchronized
			{	accBal-=amt;
				System.out.println(Thread.currentThread().getName()+" Withdrawing : "+amt);
				randomWait();
			}
		
		public float getBal()				//synchronized
			{	randomWait();
				return(accBal);
			}
		
		void randomWait() 
			{	try {	Thread.currentThread().sleep((long)(2000*Math.random()));   }
					  	catch(InterruptedException x)
					  	{ System.out.println("Interrupted"); }
			}
	}

class mythread5_1 extends Thread
	{ 	BankAccount5 ba;
		mythread5_1(String msg, BankAccount5 ba)
			{	super(msg);
				this.ba = ba;
			}
		public void run()
			{	
				synchronized(ba)
					{	System.out.println("Balance before deposite:"+ba.getBal());
						ba.deposite(500);
						System.out.println("Balance after deposite:"+ba.getBal());
					}
			}
	}

class mythread5_2 extends Thread
	{ 	BankAccount5 ba;
		mythread5_2(String msg, BankAccount5 ba)
			{	super(msg);
				this.ba = ba;
			}
		public void run()
			{	synchronized(ba)
					{	System.out.println("Balance before withdraw:"+ba.getBal());
						ba.withdraw(300);
						System.out.println("Balance after withdraw:"+ba.getBal());
					}
			}
	}

public class ThreadTest5
	{	public static void main(String [] argv)
			{	BankAccount5 ba = new BankAccount5(101, "Artindar", 5000);
				mythread5_1 depoThread = new mythread5_1("Thread Deposite:", ba);
				mythread5_2 withThread = new mythread5_2("Thread Withdraw:", ba);
				
				depoThread.start();
				withThread.start();
			}
	}