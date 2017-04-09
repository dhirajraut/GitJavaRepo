package Module14.extendingthread;

public class HeadTail {
	public static void main (String [] args) {
		Toss head = new Toss ("Head",30);
	    Toss tail = new Toss ("Tail",90);
	    head.start ();
	    tail.start ();
	    
	}	
}
class Toss extends Thread {
	  private String name;
	  private int delay;

	  public Toss (String name, int delay) {
		  this.name = name;
		  this.delay = delay;
	  }
	  public void run () {
	    for (int i =0;i<100;i++) {
	      System.out.println(name + " ");
	      try {
	        Thread.sleep (delay);
	      }
	      catch (InterruptedException e) { }
	    }
	  }
}

	    


