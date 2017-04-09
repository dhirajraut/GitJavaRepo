import java.io.IOException;

class dish
	{	String menuItem;
		boolean readyToServe=true;
		//boolean readyToEat = false;
		synchronized void serve(String menuItem) throws InterruptedException
			{	if (!readyToServe)
					{	wait();	}
				this.menuItem=menuItem;
				readyToServe=false;
				//readyToEat = true;
				System.out.println("Serving:"+this.menuItem);
				//notifyAll();
				notify();
			}
		
		synchronized String eat() throws InterruptedException
			{	/*if (!readyToEat)
					{	wait();	}
				if (readyToEat)
					{	readyToEat=false;
						readyToServe=true;
						System.out.print(Thread.currentThread().getName());
						System.out.println(" Eating:"+menuItem+"\n");
				//Thread.currentThread().sleep((long)(2000*Math.random()));
						notifyAll();
						return(menuItem);
					}
				return null;*/
			if (readyToServe)
				{	wait();	}
			this.menuItem=menuItem;
			readyToServe=true;
			System.out.println("Eating:"+this.menuItem);
			notify();
			return menuItem;
		}
	}

public class ThreadTest7
	{	static dish ts = new dish();
		static String [] menu =
			{ 	"Kachauri", "Batata Vada", "Idlee", "MungVada",
				"Bhajiya", "Allu Wadi", "Poha", "Upma",
				"Shrikhand", "Shira"
			};
		public static void main(String[] args)
			{	Waiter7 wt = new Waiter7("WaiterThread");
				Guest7 rt = new Guest7("GuestThread");
				//Guest1 rt1 = new Guest1("OtherGuest");
				//rt1.start();
				wt.start();
				rt.start();
			}
	}

class Waiter7 extends Thread
	{	Waiter7(String msg)
			{ 	super(msg);	}
		
		public void run()
			{	
				try	{	for(int i=0; i<ThreadTest7.menu.length; i++)
							{	
								ThreadTest7.ts.serve(ThreadTest7.menu[i]);
							}
						ThreadTest7.ts.serve("FingerBowl");
						System.out.println("Thanks.  Do visit again.");
					}
				catch (InterruptedException ie)
					{	System.out.println("Exception while putting string.");	}
			}
	}

class Guest7 extends Thread
	{	Guest7(String msg)
			{	super(msg);	}
	
		public void run()
			{	
				String str;
				try {	while( !((str=ThreadTest7.ts.eat()).equalsIgnoreCase("FingerBowl")));
						System.out.println("Loud Burp!!  Sure. It was a grate test. Thanks.");
					}
				
				catch (InterruptedException ie)
					{	System.out.println("Exception while getting string.");	}
			}
	}

/*
class Guest1 extends Thread
{
	Guest1 (String msg)
	{
		super(msg);
		
	}
	
	public void run()
	{	
		String str;
		try {	while( !((str=ThreadTest7.ts.eat()).equalsIgnoreCase("FingerBowl")));
				System.out.println("Loud Burp!!  Sure. It was a grate test. Thanks.");
			}
		
		catch (InterruptedException ie)
			{	System.out.println("Exception while getting string.");	}
	}
	
}*/