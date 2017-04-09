//TestSerializationTimer.java
/* This program is designing a class of which objects we are going to serialize and
 * deserialize on next programs.
*/
import java.io.*;

// An object to be made serializable must be implemented using interface 'Serializable'.
public class TestSerializationTimer	extends Thread implements Serializable
	{	private String name;					// Non-transient so, Serializable
		private long counter=0;					// Non-transient so, Serializable
		private transient boolean stopped;		// Transient so, unserialize
		
		TestSerializationTimer(String name, long counter)
			{	super(name);
				this.name=name;
				this.counter=counter;
			}
		public void run()
			{	long timebase = System.currentTimeMillis();	// Repeatedly incrementable
				long timemove = timebase;					// Base Time
				while(!stopped)
					{	counter++;
						try	{	
								timemove+=10;
								System.out.println("I am in run of Timer : "+(timemove-timebase));
								Thread.sleep(Math.max(0, timemove-timebase));	//sleep for some time.
							}
						catch(InterruptedException e)
							{}
					}
			}
		void begin()
			{	stopped=false;
				start();
			}
		void end()
			{	stopped=true;	}
		long getCounter()
			{	return(counter);	}
		String getTimerName()
			{	return(name);	}
	}
