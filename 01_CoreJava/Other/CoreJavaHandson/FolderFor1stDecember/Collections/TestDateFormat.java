import java.util.*;
import java.text.*;
public class TestDateFormat
	{	public static void main(String[] args)
			{	Date now = new Date();
				DateFormat df = DateFormat.getDateInstance();
			    
				DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
				DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM);
				DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG);
				DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL); 
				String s =  df.format(now);
				String s1 = df1.format(now);
				String s2 = df2.format(now);
				String s3 = df3.format(now);
				String s4 = df4.format(now);
				System.out.println("System Date:");
				System.out.println("(Default) Today is " + s);
				System.out.println("(SHORT)   Today is " + s1);
				System.out.println("(MEDIUM)  Today is " + s2);
				System.out.println("(LONG)    Today is " + s3);
				System.out.println("(FULL)    Today is " + s4);
				
				System.out.println("Converting user string date into date format:");
				String ds = "November 20, 2000";
			    df = DateFormat.getDateInstance();
			    try { now = df.parse(ds);}
			    catch(ParseException e)
			      	{	System.out.println("Unable to parse " + ds);	}
			    s =  df.format(now);
			    s1 = df1.format(now);
				s2 = df2.format(now);
				s3 = df3.format(now);
				s4 = df4.format(now);

				System.out.println("(Default) Today is " + s);
				System.out.println("(SHORT)   Today is " + s1);
				System.out.println("(MEDIUM)  Today is " + s2);
				System.out.println("(LONG)    Today is " + s3);
				System.out.println("(FULL)    Today is " + s4);
				
				System.out.println("Using Gragorian Date");
				GregorianCalendar firstFlight = new GregorianCalendar(1903, Calendar.DECEMBER, 17);
				now = firstFlight.getTime();
			    s = df.format(now);
			    System.out.println("First flight was " + s);
			    
			    firstFlight = new GregorianCalendar();
			    now =firstFlight.getTime();
			    s = df.format(now);
			    System.out.println("First flight was " + s);
			    
			    //firstFlight = new GregorianCalendar(1872, Calendar.OCTOBER, 2);
			    firstFlight.add(GregorianCalendar.DATE, 80);  // It changes original date
			    now = firstFlight.getTime();
			    df = DateFormat.getDateInstance();
			    s = df.format(now);
			    System.out.println("80 day trip will end " + s);
			    
			    GregorianCalendar gc = new GregorianCalendar(2001, Calendar.JANUARY, 15);
			    ReviewDates(gc);
			    listDates();

			}

	    // Calculate dates from today of tomorrow, after one week, after one month
	    // after three months and after one year.
	    static private GregorianCalendar firstDay, oneDay, oneWeek, oneMonth, oneQuarter, oneYear;

	    static void ReviewDates(GregorianCalendar gcDate)
	    	{	int year = gcDate.get(GregorianCalendar.YEAR);
	    	    int month = gcDate.get(GregorianCalendar.MONTH);
	    	    int date = gcDate.get(GregorianCalendar.DATE);

	    	    firstDay = new GregorianCalendar(year, month, date);
	    	    oneDay = new GregorianCalendar(year, month, date);
	    	    oneWeek = new GregorianCalendar(year, month, date);
	    	    oneMonth = new GregorianCalendar(year, month, date);
	    	    oneQuarter = new GregorianCalendar(year, month, date);
	    	    oneYear = new GregorianCalendar(year, month, date);

	    	    oneDay.add(GregorianCalendar.DATE, 1);
	    	    oneWeek.add(GregorianCalendar.DATE, 7);
	    	    oneMonth.add(GregorianCalendar.MONTH, 1);
	    	    oneQuarter.add(GregorianCalendar.MONTH, 3);
	    	    oneYear.add(GregorianCalendar.YEAR, 1);
	    	 }
	    static public void listDates()
	    	{	  DateFormat df = DateFormat.getDateInstance(DateFormat.LONG); 
	    	      Date startDate = firstDay.getTime();
	    	      Date date1 = oneDay.getTime();
	    	      Date date2 = oneWeek.getTime();
	    	      Date date3 = oneMonth.getTime();
	    	      Date date4 = oneQuarter.getTime();
	    	      Date date5 = oneYear.getTime();

	    	      String ss =  df.format(startDate);
	    	      String ss1 = df.format(date1);
	    	      String ss2 = df.format(date2);
	    	      String ss3 = df.format(date3);
	    	      String ss4 = df.format(date4);
	    	      String ss5 = df.format(date5);

	    	      System.out.println("Start date is " + ss);
	    	      System.out.println("Following review dates are:");
	    	      System.out.println("One day after    :"+ss1);
	    	      System.out.println("One week after   :"+ss2);
	    	      System.out.println("One month after  :"+ss3);
	    	      System.out.println("One Quarter after:"+ss4);
	    	      System.out.println("One Year after   :"+ss5);
	    	   }
	}