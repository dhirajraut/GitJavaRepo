import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor02Information
	{	public static void main(String[] argv)
			{	CountDownLatch cd1=new CountDownLatch(1);
				CountDownLatch cd2=new CountDownLatch(1);
				CountDownLatch cd3=new CountDownLatch(1);
				CountDownLatch cd4=new CountDownLatch(1);
				
				ExecutorService es = Executors.newFixedThreadPool(4);
				
				es.execute(new ThreadInformation("Thrd 1", cd1, 1));
				es.execute(new ThreadInformation("Thrd 2", cd2, 2));
				es.execute(new ThreadInformation("Thrd 3", cd3, 3));
				es.execute(new ThreadInformation("Thrd 4", cd4, 4));
				
				try {	cd1.await();
						cd2.await();
						cd3.await();
						cd4.await();
					}
				catch(InterruptedException ie)
					{	ie.printStackTrace();	}
				
				System.out.println("All threads being shutdown");
				es.shutdown();
			}
	}

class ThreadInformation implements Runnable
	{	String nm;
		CountDownLatch cd;
		int informCode;
		ObjectInformation oi;
		
		public ThreadInformation(String nm, CountDownLatch cd, int informCode)
			{	this.nm = nm;
				this.cd = cd;
				this.informCode = informCode;
				oi = new ObjectInformation();
			}
		
		public void run()
			{	System.out.println("From thread: "+nm+" Information: "+oi.getInformation(informCode));
				try {	Thread.currentThread().sleep(3000l);	}
				catch (InterruptedException e) {	e.printStackTrace();	}
				cd.countDown();
			}
	}

class ObjectInformation
	{	String[] inform = {"Information1","Information2","Information3","Information4"};
	
		public String getInformation(int i)
			{	return inform[i-1];	}
	}