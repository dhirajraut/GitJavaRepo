import java.util.Calendar;
import java.util.Date;

public class TestDateCalendar
	{	static public String MyCalendarDateToString(Calendar cal)
			{String str;
				str=Integer.toString(cal.get(Calendar.DAY_OF_MONTH))+'-';
				str=str+Integer.toString(cal.get(Calendar.MONTH)+1)+'-';
				str=str+Integer.toString(cal.get(Calendar.YEAR));
				return(str);
			}
	
		static public String MyCalendarTimeToString(Calendar cal)
			{String str;
				str=Integer.toString(cal.get(Calendar.HOUR))+':';
				str=str+Integer.toString(cal.get(Calendar.MINUTE))+':';
				str=str+Integer.toString(cal.get(Calendar.SECOND));
				return(str);
			}
		
		static public String MyDateDateToString(Date dt)
			{String str="Date :";
				str=str+Long.toString(dt.getTime());
				return(str);
			}
		
		public static void main(String[] args)
			{	Calendar calend = Calendar.getInstance();
				System.out.println("Calendar Date : "+MyCalendarDateToString(calend));
				System.out.println("Calendar Time : "+MyCalendarTimeToString(calend));
				
				Date dt = new Date();
				System.out.println("Date Date :"+dt);
				System.out.println("Date in Milli:"+MyDateDateToString(dt));
				
				
				dt.setTime(10000000000L);
				System.out.println("Date Date : 10000000000L:"+dt);
				System.out.println("Date in Milli of 10000000000L:"+MyDateDateToString(dt));
				
				calend.setTime(dt);
				System.out.println("Calendar Date for 100000000L: "+MyCalendarDateToString(calend));
				System.out.println("Calendar Time for 100000000L: "+MyCalendarTimeToString(calend));
				
				calend.clear();
				System.out.println("Calendar Date for clear(): "+MyCalendarDateToString(calend));
				System.out.println("Calendar Time for clear(): "+MyCalendarTimeToString(calend));
				
				// Setting todays date in calendar
				Date dt1 = new Date(1157428184862l);
				calend.setTime(dt1);
				System.out.println(MyCalendarDateToString(calend));
			}
	}
