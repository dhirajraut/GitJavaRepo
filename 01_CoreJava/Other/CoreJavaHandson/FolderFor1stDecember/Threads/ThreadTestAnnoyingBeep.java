/*By default, a program keeps running as long as its timer threads are running.
 * You can terminate a timer thread in four ways. 
 	** Invoke cancel on the timer. You can do this from anywhere in the program,
 			such as from a timer task's run method. 
    ** Make the timer's thread a "daemon" by creating the timer like this:
    		new Timer(true). If the only threads left in the program are daemon threads,
    		the program exits. 
    ** After all the timer's scheduled tasks have finished executing, remove all
    		references to the Timer object. Eventually, the timer's thread will terminate. 
    ** Invoke the System.exit method, which makes the entire program (and all its threads) exit.

The Timer.schedule() method has following syntaxes...
	schedule(TimerTask task, long delay, long period) 
	schedule(TimerTask task, Date time, long period) 
	scheduleAtFixedRate(TimerTask task, long delay, long period) 
	scheduleAtFixedRate(TimerTask task, Date firstTime, long period) 
	* When scheduling a task for repeated execution, you should use one of the schedule
		methods when smoothness is important.
	* Use ScheduleAtFixedRate method when time synchronization is more important.
		For example, the AnnoyingBeep program uses the schedule method, which means that
		the annoying beeps will all be at least 1 second apart. If one beep is late for
		any reason, all subsequent beeps will be delayed. If we decide that the
		AnnoyingBeep program should exit exactly 3 seconds after the first beep
		— even if it means that two beeps might occur close together if a beep is
		delayed for any reason — we should use the scheduleAtFixedRate method instead. 
*/

import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class ThreadTestAnnoyingBeep
	{	Toolkit toolkit;
    	Timer timer;

    public ThreadTestAnnoyingBeep()
    	{	toolkit = Toolkit.getDefaultToolkit();
        	timer = new Timer();
        	timer.schedule(new RemindTask(),
                       0,        //initial delay
                       1*1000);  //subsequent rate
    	}

    class RemindTask extends TimerTask
    	{	int numWarningBeeps = 10;
	        public void run()
	        	{	if (numWarningBeeps > 0)
	        			{	toolkit.beep();
	        				System.out.println("Beep!");
	        				numWarningBeeps--;
	        			}
	        		else
	        			{	toolkit.beep(); 
	        				System.out.println("Time's up!");
			                //timer.cancel(); // Not necessary because
			                                  // we call System.exit
			                System.exit(0);   // Stops the AWT thread 
			                                  // (and everything else)
	        			}
	        	}
    	}
    public static void main(String [] argv)
    	{	ThreadTestAnnoyingBeep ttab = new ThreadTestAnnoyingBeep();
    	}
   	}



