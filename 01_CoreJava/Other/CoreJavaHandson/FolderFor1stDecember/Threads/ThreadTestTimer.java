import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
class Reminder
	{	Timer timer;
		public Reminder(int seconds)
			{	timer = new Timer();
				timer.schedule(new RemindTask(), seconds*1000);
			}
		public Reminder(Date time)
		{	timer = new Timer();
			timer.schedule(new RemindTask(), time);
		}
		class RemindTask extends TimerTask
			{	public void run()
					{	System.out.println("Time's up!");
		            	timer.cancel(); //Terminate the timer thread
					}
		    }
	}
public class ThreadTestTimer
	{	public static void main(String args[])
			{	Reminder rn=new Reminder(10);
	        	System.out.println("Task scheduled.");
	        	// Following codes scheduling thread for 3:35 pm on today.
	        	Calendar calendar = Calendar.getInstance();
	        	calendar.set(Calendar.HOUR_OF_DAY, 15);	// Set Hours
	        	calendar.set(Calendar.MINUTE, 35);		// Set Minutes
	        	calendar.set(Calendar.SECOND, 0);		// Set Seconds
	        	Date time = calendar.getTime();
	        	rn = new Reminder(time);
			}
	}

