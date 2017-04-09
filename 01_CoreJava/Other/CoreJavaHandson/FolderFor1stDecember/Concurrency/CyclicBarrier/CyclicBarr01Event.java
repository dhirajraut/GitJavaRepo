import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarr01Event
	{	public static void main(String[] argv)
			{	CyclicBarrier cb = new CyclicBarrier(3, new Event("Deptt Event Management"));
		
				Deptt df = new Deptt("Deptt Finance", cb);
				Deptt da = new Deptt("Deptt Admin", cb);
				Deptt dc = new Deptt("Deptt Cultural", cb);
				
				df.start();
				da.start();
				dc.start();
			}
	}

class Deptt extends Thread
	{	CyclicBarrier cb;
	
		public Deptt(String nm, CyclicBarrier cb)
			{	super(nm);
				this.cb = cb;
			}
		public void run()
			{	System.out.println("Thread "+getName()+" is making pre event preparation");
				
				try {	cb.await();	}
				catch (InterruptedException e)
					{	e.printStackTrace();	}
				catch (BrokenBarrierException e)
					{	e.printStackTrace();	}
				
				System.out.println("Thread "+getName()+" is making post event preparation");
			}
	}

class Event extends Thread
	{	public Event(String nm)
			{	super(nm);	}
		public void run()
			{	System.out.println("Glorious Event presented by "+getName());	}
	}