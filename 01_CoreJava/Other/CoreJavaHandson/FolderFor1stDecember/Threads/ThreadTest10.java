/* How to terminate deadlock?
 * 	Ctrl=BREAK shows full thread and monitor cache.
 */
class Friendly
	{ Friendly friend;
	  String name;

	  public Friendly(String st)
		{ name = st;	}

	  public synchronized void hug()
		{ System.out.println("The thread "+Thread.currentThread().getName()+" is trying to hug "+friend.getName());
		  try { Thread.currentThread().sleep(2000); }
		  catch(InterruptedException ie)
			{ System.out.println("Exception in"+Thread.currentThread().getName());	}
		  System.out.println("The thread "+Thread.currentThread().getName()+" is trying to hugback "+friend.getName());
		  friend.hugback();
		}
	  public synchronized void hugback()
		{ System.out.println("The thread "+Thread.currentThread().getName()+ " is hugging back to "+friend.getName());	}

	  public void setFriendShip(Friendly fr)
		{ friend = fr;	}
	  public String getName()
		{ return(name);	}
	}

public class ThreadTest10
	{ public static void main(String [] argv)
		{ final Friendly suresh = new Friendly("Suresh");
		  final Friendly ganesh = new Friendly("Ganesh");

		  suresh.setFriendShip(ganesh);
		  ganesh.setFriendShip(suresh);

		  Thread t1 = new Thread(new Runnable()
					{ public void run()
						{ suresh.hug();}
					}, "Suresh");

		  Thread t2 = new Thread(new Runnable()
					{ public void run()
						{ ganesh.hug();	}
					}, "Ganesh");

		  t1.start();
		  t2.start();
		}
	}