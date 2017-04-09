import java.util.concurrent.Semaphore;

class Dish03
	{	String item = "No Item";
		Semaphore controlWaiter, controlGuest;
	
		public Dish03(Semaphore controlWaiter, Semaphore controlGuest)
			{	this.controlWaiter = controlWaiter;
				this.controlGuest = controlGuest;
			}
		public void serve(String item)
			{	try {	controlWaiter.acquire();
						System.out.println("Waiter serving "+item);
						this.item = item;
						controlGuest.release();
					}
				catch (InterruptedException ie)
					{	ie.printStackTrace();	}
			}
		
		public String eat()
			{	String eatenItem = "Item not eaten";
				try	{	controlGuest.acquire();
						if (!(item.equalsIgnoreCase("No Item")))
							{	eatenItem = item;
								System.out.println(eatenItem);
								item = "No item";
							}
						controlWaiter.release();
					}
				catch (InterruptedException ie)
					{	ie.printStackTrace();	}
				return eatenItem;
			}
	}

class ThrdWaiter03 extends Thread
	{	Dish03 dish;
		String[] items = { "Samosa", "Kachaudi", "Poha", "Upama", "ZunkaBhakar", "GolaBhat",
							"MasalaBhat", "Thalipith", "Alluwadi", "Besanwadi", "Batatawada",
							"FingerBowl"
						 };
		public ThrdWaiter03(String nm, Dish03 d)
			{	super(nm);
				this.dish = d;
			}
		public void run()
			{	for(int i=0; i<items.length; i++)
					{	
						dish.serve(items[i]);
					}
				System.out.println("Thanks!! Visit again!!!");
			}
	}

class ThrdGuest03 extends Thread
	{	Dish03 dish;
	
		public ThrdGuest03(String nm, Dish03 d)
			{	super(nm);
				this.dish = d;
			}
		public void run()
			{	while(!(dish.eat().equalsIgnoreCase("FingerBowl")));
				System.out.println("Loud burp!!!");
			}
	}

public class Sema03GuestWaiter
	{	public static void main(String[] argv) throws InterruptedException
			{	Semaphore controlWaiter = new Semaphore(0, true);
				Semaphore controlGuest  = new Semaphore(1, true);
				
				Dish03 dish = new Dish03(controlWaiter, controlGuest);
				
				ThrdWaiter03 waiter = new ThrdWaiter03("Waiter:", dish);
				ThrdGuest03  guest  = new ThrdGuest03("Guest:", dish);
				
				waiter.start();
				Thread.sleep(300);
				guest.start();
			}
	}