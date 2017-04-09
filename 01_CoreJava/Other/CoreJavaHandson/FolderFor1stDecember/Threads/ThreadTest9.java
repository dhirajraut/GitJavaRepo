import ioservices.MyIOService;
import java.io.IOException;
class CondObject
	{ boolean condThrd1= false;
	  boolean condThrd2= false;
	  boolean condThrd3= false;

	  public synchronized void forThrd1()
		{ try {while(!condThrd1)
			wait();
		      }
		  catch (InterruptedException ie)
			{}
		  System.out.println("I am from Thread1");
		} 

	  public synchronized void forThrd2()
		{ try { while(!condThrd2)
				wait();
		      }
		  catch (InterruptedException ie)
			{}
		  System.out.println("I am from Threa2");
		}

	  public synchronized void forThrd3()
		{ try { while(!condThrd3)
				wait();
		      }
		  catch (InterruptedException ie)
			{}
		  System.out.println("I am from Thread3");
		}

	  public synchronized void toNotify(int n)
		{ switch(n)
			{ case 1 : condThrd1 = true; break;
			  case 2 : condThrd2 = true; break;
			  case 3 : condThrd3 = true; break;
			}
		  notifyAll();
		}
	}

class NotifyingThread extends Thread
	{ CondObject co = null;
 	  NotifyingThread(CondObject co)
		{ this.co = co;	}

	  public void run()
		{ int n=0;
		  while (n!=4)
			{ try {	n = MyIOService.getInt();
			  	co.toNotify(n);
			      }
			  catch (IOException ie)
				{}
			}
		}
	}

public class ThreadTest9
	{ 
	  public static void main(String [] argv)
		{ final CondObject co = new CondObject();
		  Thread th1=null, th2=null, th3=null;

		  
		  th1 = new Thread( new Runnable()
				{ public void run()
					{ co.forThrd1();}
				});

		  th2 = new Thread( new Runnable()
				{ public void run()
					{ co.forThrd2();}
				});

		  th3 = new Thread( new Runnable()
				{ public void run()
					{ co.forThrd3();}
				});
		  

		  NotifyingThread thn = new NotifyingThread(co);

		  th1.start();
		  th2.start();
		  th3.start();
		  thn.start();
		}
	}		  