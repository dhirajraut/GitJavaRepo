import java.util.concurrent.Semaphore;
public class TestSemaphoreSimple
	{	public static void main(String[] args)
			{ 	Semaphore sem = new Semaphore(1);
				SharedResource sr = new SharedResource();
				
				Thread t1 = new Thread(new IncreThread("<T Incre>", sem, sr));
				Thread t2 = new Thread(new DecreThread("<T Decre>", sem, sr));
				
				t1.start();
				t2.start();
			}
	}

class SharedResource
	{	int counter;
		public int incre()
			{	return(++counter);	}
		public int decre()
			{	return(--counter);	}
	}

class IncreThread implements Runnable
	{	String nm;
		Semaphore sem;
		SharedResource sr;
		
		IncreThread(String nm, Semaphore sem, SharedResource sr)
			{	this.nm = nm;
				this.sem = sem;
				this.sr = sr;
			}
		
		public void run()
			{	System.out.println("Starting a thread : "+nm);
				///*
				System.out.println(nm + " waiting for permit.");
				try {	sem.acquire();	}
				catch (InterruptedException e)
					{	e.printStackTrace();	}
				System.out.println(nm + " got a permit.");
				//*/
				for(int i=0; i<10; i++)
					{	System.out.println(sr.incre());
						try {	Thread.sleep(1000l);	}
						catch (InterruptedException e)
							{	e.printStackTrace();	}
					}
				///*
				System.out.println(nm+" Releasing a permit");
				sem.release();
				//*/
			}
	}

class DecreThread implements Runnable
	{	String nm;
		Semaphore sem;
		SharedResource sr;
		
		DecreThread(String nm, Semaphore sem, SharedResource sr)
			{	this.nm = nm;
				this.sem = sem;
				this.sr = sr;
			}
		
		public void run()
			{	System.out.println("Starting a thread : "+nm);
				///*
				System.out.println(nm + " waiting for permit.");
				try {	sem.acquire();	}
				catch (InterruptedException e)
					{	e.printStackTrace();	}
				System.out.println(nm + " got a permit.");
				//*/
				for(int i=0; i<10; i++)
					{	System.out.println(sr.decre());
						try {	Thread.sleep(1000l);	}
						catch (InterruptedException e)
							{	e.printStackTrace();	}
					}
				///*
				System.out.println(nm+" Releasing a permit");
				sem.release();
				//*/
			}
	}