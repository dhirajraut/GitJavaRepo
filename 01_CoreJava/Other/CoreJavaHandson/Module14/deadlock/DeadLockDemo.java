package Module14.deadlock;

class Friendly  {
	private Friendly partner;
	private String name;
	public Friendly (String name) {
		this.name = name;
	}
	public synchronized void hug () {
		String n = Thread.currentThread ().getName ();
		System.out.println (n+" in " +name+".hug() trying to invoke "+partner.name+".hugBack()");
    try {
      Thread.sleep (10);
    }
    catch (InterruptedException e) {}
    	partner.hugBack ();
	}
	private synchronized void hugBack () {
		String n = Thread.currentThread ().getName ();
	    System.out.println (n+" in "+name+".hugBack ()");
	}

	public void becomeFriend (Friendly partner) {
	    this.partner  = partner ;
	}
}

public class DeadLockDemo {
	  public static void main (String [] args) {
		  final Friendly suresh = new Friendly ("Suresh");
	      final Friendly dinesh = new Friendly ("Dinesh");
	    
	    suresh.becomeFriend (dinesh);
	    dinesh.becomeFriend (suresh);
	    
	    Thread t1 = new Thread(new Runnable (){
	    							public void run () {
	    									suresh.hug ();	
	    							}
	    					   }, "Thread1");
	    Thread t2 = new Thread (new Runnable () {
	    							public void run () {
	    									dinesh.hug ();	
	    							}
	    						}, "Thread2");
	    t1.start ();
	    t2.start ();
	  }
}
