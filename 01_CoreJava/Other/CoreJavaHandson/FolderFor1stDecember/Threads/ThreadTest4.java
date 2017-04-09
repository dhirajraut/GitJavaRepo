// This is for demonstrating Moniter, synchronized methods

import java.lang.Math;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.lang.System;
class BankAccount4
	{	int accNo;
		String accNm;
		public float accBal;
		
		public BankAccount4()
			{	accNo=0; accBal=0;
				accNm=new String("");
			}
		public BankAccount4(int n, String nm, float l)
			{	accNo=n; accBal=l; accNm=nm;	}
		
		public synchronized void deposite(float amt)	//synchronized
			{	for(int i=1; i<=10; i++)
					{	accBal+=amt;
						System.out.println(Thread.currentThread().getName()+" Deposited: "+accBal);
						RandomWait.randomWait(400);
					}
			}
		
		public  synchronized void withdraw(float amt)	//synchronized
			{	accBal-=amt;
				System.out.println(Thread.currentThread().getName()+" Withdrawing : "+accBal);
				RandomWait.randomWait(200);
			}
		
		public  synchronized float getBal()				//synchronized
			{	return(accBal); }
		
		public void changeBal(float amt)
			{	accBal+=amt;
				System.out.println(Thread.currentThread().getName()+" New Balance : "+accBal);
			}
		
		public synchronized void doDeposite(float amt)
			{	System.out.println("Balance Before deposite:"+getBal());
				deposite(amt);
				System.out.println("Balance after deposite:"+getBal());
			}
	}

class RandomWait
	{ 	static void randomWait(int m) 
			{	try {	Thread.currentThread().sleep((long)(m*Math.random()));   }
					  	catch(InterruptedException x)
					  	{ System.out.println("Interrupted"); }
			}
	}

class mythread4_1 extends Thread
	{ 	
		mythread4_1(String msg)
			{	super(msg);	}
		public void run()
			{	ThreadTest4.ba.deposite(500);
			}
	}

class mythread4_2 extends Thread
	{ 	
		mythread4_2(String msg)
			{	super(msg);	}
		public void run()
			{	ThreadTest4.ba.withdraw(300);	}
	}

class mythread4_3 extends Thread
	{ 	
		mythread4_3(String msg)
			{	super(msg);	}
		public void run()
			{	System.out.println("Balance from thread3:"+ThreadTest4.ba.getBal());	}
	}

public class ThreadTest4
	{	static BankAccount4 ba = new BankAccount4();
		public static void main(String[] args)
			{	//BankAccount4 ba = new BankAccount4();
				System.out.println("Thread4 begines.");
				mythread4_1 thread1 = new mythread4_1("Thread1:");
				mythread4_2 thread2 = new mythread4_2("Thread2:");
				mythread4_3 thread3 = new mythread4_3("Thread3:");
				
				thread1.start();
				thread3.start();
				thread2.start();
			}
	}
