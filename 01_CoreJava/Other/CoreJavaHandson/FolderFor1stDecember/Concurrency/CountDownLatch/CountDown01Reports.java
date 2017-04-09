import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

public class CountDown01Reports
	{	public static void main(String[] args)
			{	CountDownLatch cdl = new CountDownLatch(2);
				Collection c = new HashSet();
				Thread te1 = new Thread(new ThreadEvent1("Thrd 1:", cdl, c));
				Thread te2 = new Thread(new ThreadEvent2("Thrd 2:", cdl, c));
				
				te1.start();
				te2.start();
				
				try { cdl.await();	}
				catch(InterruptedException ie)
					{	}
				
				System.out.println("Creating statistical reports on whole data.");
			}
	}

class ThreadEvent1 implements Runnable
	{	CountDownLatch cdl;
		Collection c;
		String nm;
	
		ThreadEvent1(String nm, CountDownLatch cdl, Collection c)
			{	this.nm = nm;
				this.c = c;
				this.cdl = cdl;
			}
		public void run()
			{	System.out.println("Data collected from branch 1 : ");
				c.add("Data Branch 1");
				cdl.countDown();
			}
	}

class ThreadEvent2 implements Runnable
	{	CountDownLatch cdl;
		Collection c;
		String nm;
	
		ThreadEvent2(String nm, CountDownLatch cdl, Collection c)
			{	this.nm = nm;
				this.c = c;
				this.cdl = cdl;
			}
		public void run()
			{	System.out.println("Data collected from branch 2 : ");
				c.add("Data Branch 2");
				cdl.countDown();
			}
	}