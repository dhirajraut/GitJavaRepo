import java.util.concurrent.Semaphore;

public class ProducerConsumer
	{	static String [] list = { "Keshar Bhat", "Chirwanta", "Karanji", "Jilabi", "ChamCham", "FigerBowl"};
	
		public static void main(String[] args)
			{	Dish d = new Dish();
				Thread guest = new Thread(new ThreadGuest("Thread Guest:", d));
				Thread waiter = new Thread(new ThreadWaiter("Thread Waiter:", d));
				
				guest.start();
				waiter.start();
			}
	}

class Dish
	{	String item;
		Semaphore semCon = new Semaphore(0);
		Semaphore semProd = new Semaphore(1);
		
		public String eat()
			{	try { 	semCon.acquire();	}
				catch(InterruptedException e)
					{	}
				semProd.release();
				return item;
			}
		
		public void serve(String item)
			{	try {	semProd.acquire();	}
				catch(InterruptedException e)
					{	}
				this.item = item;
				semCon.release();
			}
	}

class ThreadWaiter implements Runnable
	{	Dish d;
		String nm;
		
		public ThreadWaiter(String nm, Dish d)
			{	this.nm = nm;
				this.d = d;
			}
		
		public void run()
			{	for(int i=0; i<ProducerConsumer.list.length; i++)
					{	System.out.println("Serving " + ProducerConsumer.list[i]);
						d.serve(ProducerConsumer.list[i]);
					}
			}
	}

class ThreadGuest implements Runnable
	{	Dish d;
		String nm;
		
		public ThreadGuest(String nm, Dish d)
			{	this.nm = nm;
				this.d = d;
			}
		
		public void run()
			{	String item = d.eat();
				while (!item.equalsIgnoreCase("FigerBowl"))	
					{	System.out.println("Consuming "+ item);
						item = d.eat();
					}
				System.out.println("Loud burp. Thanks.");
			}
	}