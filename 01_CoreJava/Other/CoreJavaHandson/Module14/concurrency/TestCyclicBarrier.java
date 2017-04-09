package Module14.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	  public static void main(String[] args){
		  CyclicBarrier cb = new CyclicBarrier(3, new Event("Dept Event Management"));
		  Dept df = new Dept("Dept Finance", cb);
		  Dept da = new Dept("Dept Admin", cb);
		  Dept dc = new Dept("Dept Cultural", cb);
		  df.start(); 
		  da.start();
		  dc.start();
	  }
}

class Dept extends Thread {
	CyclicBarrier cb;
	
	public Dept(String nm, CyclicBarrier cb) {
		super(nm); this.cb = cb;
	}
	public void run() {
		System.out.println("Thread "+getName()+" is making pre event preparation");
		try { 
			cb.await();
		}
		catch (InterruptedException e) { 
			e.printStackTrace(); 
		}
		catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Thread "+getName()+" is making post event preparation");
	}
}

class Event extends Thread {
	public Event(String nm) {
		super(nm);
	}
	public void run() {
	    System.out.println("Glorious Event presented by "+getName());
	}
}


