import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor01Simple {
	public static void main(String[] argv)
		{	CountDownLatch cd1 = new CountDownLatch(3);
			CountDownLatch cd2 = new CountDownLatch(3);
			CountDownLatch cd3 = new CountDownLatch(3);
			CountDownLatch cd4 = new CountDownLatch(3);
			
			// Creating Thread pool
			ExecutorService es = Executors.newFixedThreadPool(2);
			//ExecutorService es = Executors.newFixedThreadPool(4);
			
			System.out.println("Pool created with the capacity of 2");
			es.execute(new ThreadExecutor01("A", cd1));
			es.execute(new ThreadExecutor01("B", cd2));
			es.execute(new ThreadExecutor01("C", cd3));
			es.execute(new ThreadExecutor01("D", cd4));
			System.out.println("Instantiated and started all the the threads");
			try {	cd1.await();
					cd2.await();
					cd3.await();
					cd4.await();
				}
			catch(InterruptedException ie)
				{	ie.printStackTrace();	}
			System.out.println("Executed and now shutting down all the threads");
			es.shutdown();
			System.out.println("Done");
		}
}

class ThreadExecutor01 implements Runnable
	{	String nm;
		CountDownLatch cd;
		
		public ThreadExecutor01(String nm, CountDownLatch cd)
			{	this.nm = nm;
				this.cd = cd;
			}
		
		public void run()
			{	for(int i=0; i<3; i++)
					{	System.out.println("Thread Name : "+nm+" "+(i+1));
						
						try {	Thread.currentThread().sleep(3000l);	}
						catch (InterruptedException e) {	e.printStackTrace();	}
						
						cd.countDown();
					}
			}
	}
