package com.intertek.util;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.intertek.entity.TimeZone;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.common.Form;
import com.intertek.service.AdminService;
import com.intertek.service.UserService;

public class DateUtil
{
  public static boolean rateDateMatch(Date beginDate, Date endDate, Date rateBeginDate, Date rateEndDate)
  {
    if((beginDate == null) || (endDate == null) || (rateBeginDate == null) || (rateEndDate == null)) return false;

    Calendar beginCal = getCalendarForDateWithDatePartOnly(beginDate);
    Calendar endCal = getCalendarForDateWithDatePartOnly(endDate);
    Calendar rateBeginCal = getCalendarForDateWithDatePartOnly(rateBeginDate);
    Calendar rateEndCal = getCalendarForDateWithDatePartOnly(rateEndDate);

    if((rateBeginCal.compareTo(beginCal) >= 0) && (rateEndCal.compareTo(endCal) <= 0)) return true;

    if((rateBeginCal.compareTo(beginCal) <= 0) && (rateEndCal.compareTo(beginCal) >= 0)) return true;

    if((rateBeginCal.compareTo(beginCal) >= 0) && (rateBeginCal.compareTo(endCal) <= 0)) return true;

    return false;
  }

  public static Calendar getCalendarForDateWithDatePartOnly(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.set(Calendar.HOUR, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar;
  }

  public static String convertDate(String input, String fromFormat, String toFormat)
  {
    Date date = parseDate(input, fromFormat);
    return formatDate(date, toFormat);
  }

  public static String getUserDateFormat()
  {
    String dateFormat = null;

    User user = SecurityUtil.getUser();
    if(user != null)
    {
      dateFormat = user.getDateFormat();
    }

    if(dateFormat == null) dateFormat = "MM/dd/yyyy";

    return dateFormat;
  }

  public static void adjustBeginDateAndEndDateAscend(
    Date[] datePair1,
    Date[] datePair2
  )
  {
    if((datePair1 == null) || (datePair2 == null)) return;

    if(datePair1.length != datePair2.length) return;
    if(datePair1.length != 2) return;

    int result = DateUtil.compareToInDate(
      datePair1[1],
      datePair2[0]
    );

    if(result >= 0)
    {
      Date newBeginDate = DateUtil.addInDate(
        datePair1[1],
        1
      );
      datePair2[0] = newBeginDate;

      result = DateUtil.compareToInDate(
        datePair2[0],
        datePair2[1]
      );

      if(result > 0)
      {
        Date newEndDate = DateUtil.addInDate(
          datePair2[0],
          1
        );
        datePair2[1] = newEndDate;
      }
    }
  }

  public static void adjustBeginDateAndEndDateDescend(
    Date[] datePair1,
    Date[] datePair2
  )
  {
    if((datePair1 == null) || (datePair2 == null)) return;

    if(datePair1.length != datePair2.length) return;
    if(datePair1.length != 2) return;

    int result = DateUtil.compareToInDate(
      datePair1[0],
      datePair2[1]
    );

    if(result <= 0)
    {
      Date newEndDate = DateUtil.addInDate(
        datePair1[0],
        -1
      );
      datePair2[1] = newEndDate;

      result = DateUtil.compareToInDate(
        datePair2[0],
        datePair2[1]
      );

      if(result > 0)
      {
        Date newBeginDate = DateUtil.addInDate(
          datePair2[1],
          -1
        );
        datePair2[0] = newBeginDate;
      }
    }
  }

  public static void calculateBeginDateAndEndDate(
    Date[] newDatePair,
    Date[] oldDatePair
  )
  {
    if((newDatePair == null) || (oldDatePair == null)) return;

    if(newDatePair.length != oldDatePair.length) return;
    if(newDatePair.length != 2) return;

    Date today = new Date();

    int beforeToday = DateUtil.compareToInDate(
      today,
      oldDatePair[0]
    );

    if(beforeToday > 0)
    {
      Date yesterday = DateUtil.addInDate(today, -1);
      oldDatePair[1] = yesterday;
    }
    else
    {
      Date dateAfterLastBeginDate = DateUtil.addInDate(
        oldDatePair[0],
        1
      );
      oldDatePair[1] = oldDatePair[0];
      newDatePair[0] = dateAfterLastBeginDate;
    }
  }

  public static Date addInDate(Date date, int days)
  {
    Calendar c1 = Calendar.getInstance();

    if(date != null) c1.setTime(date);

    c1.add(Calendar.DATE, days);

    return c1.getTime();
  }

  public static int compareToInDate(Date date1, Date date2)
  {
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();

    Date newDate1 = null;
    Date newDate2 = null;

    if(date1 != null)
    {
      c1.setTime(date1);

      c1.set(Calendar.HOUR, 0);
      c1.set(Calendar.MINUTE, 0);
      c1.set(Calendar.SECOND, 0);
      c1.set(Calendar.MILLISECOND, 0);
      c1.set(Calendar.AM_PM, Calendar.AM);
    }

    if(date2 != null)
    {
      c2.setTime(date2);

      c2.set(Calendar.HOUR, 0);
      c2.set(Calendar.MINUTE, 0);
      c2.set(Calendar.SECOND, 0);
      c2.set(Calendar.MILLISECOND, 0);
      c2.set(Calendar.AM_PM, Calendar.AM);
    }

    newDate1 = c1.getTime();
    newDate2 = c2.getTime();

    return newDate1.compareTo(newDate2);
  }

  public static boolean betweenDates(Date date, Date date1, Date date2)
  {
    if(date == null) return false;

    if((date1 == null) && (date2 == null)) return false;

    Calendar c = Calendar.getInstance();
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();

    c.setTime(date);

    c.set(Calendar.HOUR, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    c.set(Calendar.AM_PM, Calendar.AM);

    Date newDate = c.getTime();
    Date newDate1 = null;
    Date newDate2 = null;

    if(date1 != null)
    {
      c1.setTime(date1);

      c1.set(Calendar.HOUR, 0);
      c1.set(Calendar.MINUTE, 0);
      c1.set(Calendar.SECOND, 0);
      c1.set(Calendar.MILLISECOND, 0);
      c1.set(Calendar.AM_PM, Calendar.AM);

      newDate1 = c1.getTime();
    }

    if(date2 != null)
    {
      c2.setTime(date2);

      c2.set(Calendar.HOUR, 0);
      c2.set(Calendar.MINUTE, 0);
      c2.set(Calendar.SECOND, 0);
      c2.set(Calendar.MILLISECOND, 0);
      c2.set(Calendar.AM_PM, Calendar.AM);

      newDate2 = c2.getTime();
    }

    if(newDate1 == null)
    {
      return newDate.compareTo(newDate2) <= 0;
    }
    else if(newDate2 == null)
    {
      return newDate.compareTo(newDate1) >= 0;
    }

    return (newDate.compareTo(newDate1) >= 0) && (newDate.compareTo(newDate2) <= 0);
  }

  /** Retrieves # of years between the given dates */
  public static int getYears(Date d1, Date d2)
  {
    if((d1 == null) || (d2 == null)) return 0;

    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();

    c1.setTime(d1);
    c2.setTime(d2);

    c1.set(Calendar.HOUR, 0);
    c1.set(Calendar.MINUTE, 0);
    c1.set(Calendar.SECOND, 0);
    c1.set(Calendar.MILLISECOND, 0);
    c1.set(Calendar.AM_PM, Calendar.AM);

    c2.set(Calendar.HOUR, 0);
    c2.set(Calendar.MINUTE, 0);
    c2.set(Calendar.SECOND, 0);
    c2.set(Calendar.MILLISECOND, 0);
    c2.set(Calendar.AM_PM, Calendar.AM);

    if(c1.compareTo(c2) > 0) return 0;

    int months = getMonths(d1,d2);

    return Math.round(months/12.0f + 0.5f);
  }

  /** Retrieves # of months between the given dates */
  public static int getMonths(Date d1, Date d2)
  {
    int elapsed = 0;

    GregorianCalendar gc1, gc2;

    GregorianCalendar g1 = new GregorianCalendar();
    GregorianCalendar g2 = new GregorianCalendar();

    g1.setTime(d1);
    g2.setTime(d2);

    if (g2.after(g1))
    {
      gc2 = (GregorianCalendar) g2.clone();
      gc1 = (GregorianCalendar) g1.clone();
    }
    else
    {
      gc2 = (GregorianCalendar) g1.clone();
      gc1 = (GregorianCalendar) g2.clone();
    }

    //Clear out Calendar values
    gc1.clear(Calendar.MILLISECOND);
    gc1.clear(Calendar.SECOND);
    gc1.clear(Calendar.MINUTE);
    gc1.clear(Calendar.HOUR_OF_DAY);
    gc1.clear(Calendar.DATE);

    gc2.clear(Calendar.MILLISECOND);
    gc2.clear(Calendar.SECOND);
    gc2.clear(Calendar.MINUTE);
    gc2.clear(Calendar.HOUR_OF_DAY);
    gc2.clear(Calendar.DATE);

    //Excludes current month
    gc1.add(Calendar.MONTH, 1);
    while ( gc1.before(gc2) || gc1.equals(gc2) )
    {
      gc1.add(Calendar.MONTH, 1);
      elapsed++;
    }

    return elapsed;
  }

  public static String formatDate(Date date, String format)
  {
    String result = null;
    try
    {
      SimpleDateFormat formatter = new SimpleDateFormat(format);
      if(date != null)
      result = formatter.format(date);

    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  public static Date parseDate(String dateStr, String format)
  {
    Date result = null;
    try
    {
      SimpleDateFormat formatter = new SimpleDateFormat(format);
      result = formatter.parse(dateStr);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  public static Date getPreviousDate(Date dtPrevious){
    SimpleDateFormat dateFormat= new SimpleDateFormat(Constants.DATE_PATTERN);
    String previousDate= dateFormat.format(dtPrevious.getTime()-Constants.MILLIS_INS_DAY);


    try
    {
      dtPrevious = dateFormat.parse(previousDate);
    }catch(Exception e){
      e.printStackTrace();
    }

    return dtPrevious ;

  }

  public static Date getNextDate(Date dtNext){

    SimpleDateFormat dateFormat= new SimpleDateFormat(Constants.DATE_PATTERN);
    String previousDate= dateFormat.format(dtNext.getTime()+Constants.MILLIS_INS_DAY);


    try
    {
      dtNext = dateFormat.parse(previousDate);
    }catch(Exception e){
      e.printStackTrace();
    }

    return dtNext ;

  }

  public static Double getCurrentTime(){

    Calendar cal = new GregorianCalendar();

      int hour12= cal.get(Calendar.HOUR);
      int hour24 = cal.get(Calendar.HOUR_OF_DAY);
      int min = cal.get(Calendar.MINUTE);
      int sec = cal.get(Calendar.SECOND);
      int ms = cal.get(Calendar.MILLISECOND);
      int ampm = cal.get(Calendar.AM_PM);
      String nmTime = "";
      if(min >= 0 && min <=9)
       nmTime=String.valueOf(hour24)+"."+ "0" + String.valueOf(min);
      else if(String.valueOf(min).length() == 1)
       nmTime=String.valueOf(hour24)+"."+ String.valueOf(min) + "0";
      else
        nmTime=String.valueOf(hour24)+"."+ String.valueOf(min) ;

      Double time = Double.valueOf(nmTime);
    return time;
  }

  public static String getParsedCurrentTime(){

    String parsedTime = "";

    Double currTime = getCurrentTime();
    String currTimeStr = currTime.toString();
    String hrStr = currTimeStr.substring(0,(currTimeStr.indexOf(".") ));
    String minStr = currTimeStr.substring(currTimeStr.indexOf(".") + 1,currTimeStr.length());
    if(minStr.length() == 1)
     minStr = minStr + "0";

    parsedTime = hrStr + ":" + minStr;
    return parsedTime;
  }

   public static String getDatePartFromDate(Date dt,String dateFormat)
   {
     String jobFinshDate = null;
     if(dt == null)
       return jobFinshDate;
       try{
            DateFormat fDateFormat = null;
            fDateFormat =DateFormat.getDateInstance (DateFormat.DEFAULT);
            jobFinshDate = fDateFormat.format(dt);

          } catch(Exception e) {
            e.printStackTrace();
          }
    return jobFinshDate;
   }
   public static Date getFormateDateFromCurrentDate(Date dt,String datePattern)
   {
     Date formateDate = null;
     if(dt == null)
       return formateDate;

       String[] pattern = datePattern.split("/");
       String month = pattern[1];
       if(pattern[0].equals("mm"))
         datePattern = "MM/dd/yyyy";
       if(month.equals("mmm"))
         datePattern = "dd/MMM/yyyy";
       if(month.equals("mm"))
         datePattern = "dd/MM/yyyy";

       SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
       String date_out = dateFormat.format(dt);

       try
       {
         formateDate = dateFormat.parse(date_out);

       }catch(Exception e)
       {
         e.printStackTrace();
       }

       return formateDate;
   }
  public static Date getConvertedETADate(Date date,oracle.sql.TIMESTAMP time,String timezn) throws ParseException, SQLException {
	  Time t = time.timeValue();
	  String timeString = t.getHours()+":"+t.getMinutes()+":"+t.getSeconds();
	  Date nominationTime=null;
	  if(null != date){
		  date.setHours(t.getHours());
		  date.setMinutes(t.getMinutes());
		  date.setSeconds(t.getSeconds());
		  nominationTime = DateUtil.getConvertedDateTime(date,timeString,Constants.TIME_ZONE,timezn);
	  }
	  else{
		  DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		  Date scheduleTime = new Date();
			try
			{ 
//			Date today = df.parse("20/12/2005 02:03:03 AST");  
			scheduleTime =  df.parse(df.format(time.dateValue()));
			} catch (ParseException e) 
			{ 
			e.printStackTrace(); 
			} 
		  nominationTime = DateUtil.getConvertedDateTime(scheduleTime,timeString,Constants.TIME_ZONE,timezn);
	  }
		return nominationTime;
	}
   
 
   // date fomate code end
  public static Date getConvertedDateTime(Date relatedDate, String time,String sourceTz,String destTz)
  {
   Date convertedDateTime = relatedDate;
    //parse time string to get hr & min values
   if(time.indexOf(":")== -1){
     int timeLength = time.length();
     if(timeLength==4){
      String mints=time.substring(0,2);
      String seconds=time.substring(2,4);
      time=mints+":"+seconds;

     }else if(timeLength==3){
       String mints=time.substring(0,1);
       String seconds=time.substring(1,2);
       time=mints+":"+seconds;

     }else
     {
       String mints=time.substring(0,1);
       String seconds=time.substring(1,2);
       time=mints+":"+seconds;
     }
   }

    String[] hrMins=time.split("\\:");
    int hours = 0;
    int mins = 0;

    if(hrMins.length == 2 || hrMins.length == 3 )
    {
      hours = Integer.parseInt(hrMins[0].trim());
      mins = Integer.parseInt(hrMins[1].trim());
    }

    //Load the source & Dest Timezone objects from DB
      AdminService adminService = (AdminService) ServiceLocator
        .getInstance().getBean("adminService");

    TimeZone sourceTimeZone = adminService.getTimeZoneByName(sourceTz);
    TimeZone destTimeZone = adminService.getTimeZoneByName(destTz);

    int timeInMinutes = hours*60 + mins;

    //Differece in minutes between time zones
    int timeZoneDiffInMins = 0;
    if((destTimeZone != null) && (sourceTimeZone != null))
    {
      timeZoneDiffInMins = Integer.parseInt(destTimeZone.getUtcOffset()) - Integer.parseInt(sourceTimeZone.getUtcOffset());
    }

    int convertedTimeInMins = timeInMinutes + timeZoneDiffInMins;
    if(convertedTimeInMins >= 0)
    {
     if(convertedTimeInMins < 1440)
     {
       int convertedHour = convertedTimeInMins/60;
       int convertedMins = convertedTimeInMins - convertedHour*60;

       convertedDateTime.setHours(convertedHour);
       convertedDateTime.setMinutes(convertedMins);
     }
     else//change it to next date
     {
       convertedDateTime = getNextDate(convertedDateTime);
       convertedTimeInMins =  convertedTimeInMins - 24*60;
       int convertedHour = convertedTimeInMins/60;
       int convertedMins = convertedTimeInMins - convertedHour*60;

       convertedDateTime.setHours(convertedHour);
       convertedDateTime.setMinutes(convertedMins);
     }
    }
    else //change it to previous date
    {
      convertedDateTime = getPreviousDate(convertedDateTime);
      convertedTimeInMins = 24*60 + convertedTimeInMins;

       int convertedHour = convertedTimeInMins/60;

       int convertedMins = convertedTimeInMins - convertedHour*60;

       convertedDateTime.setHours(convertedHour);
       convertedDateTime.setMinutes(convertedMins);
    }
    return convertedDateTime;
  }
/*public static Date DateTime(Date dt, String time){
String datePattern = "yyyy-MM-dd";
String[] d2=time.split(" ");
String[] d1=d2[0].split("\\:");
Date ntime=null;
SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
 String date_out = dateFormat.format(dt);

  try
       {
         ntime = dateFormat.parse(date_out);
       }catch(Exception e)
       {
         e.printStackTrace();
       }

String d3="00";
//String d4="00";


if(d2.length>1&&!d2[1].trim().equals("")&&d2[1].trim().equalsIgnoreCase("pm")&&Integer.parseInt(d1[0].trim())<12)
{ntime.setHours(Integer.parseInt(d1[0].trim())+12);}

else if(d2.length>1&&!d2[1].trim().equals("")&&d2[1].trim().equalsIgnoreCase("am")&&Integer.parseInt(d1[0].trim())==12)
{ntime.setHours(Integer.parseInt(d1[0].trim())-12);}

else
{ntime.setHours(Integer.parseInt(d1[0].trim()));}

if(d1.length==1)
  {
  ntime.setMinutes(Integer.parseInt(d3.trim()));
   //ntime.setSeconds(Integer.parseInt(d4.trim()));
   }
 else if(d1.length==2)
{
ntime.setMinutes(Integer.parseInt(d1[1].trim()));
//ntime.setSeconds(Integer.parseInt(d4));
}
else
  {

  ntime.setMinutes(Integer.parseInt(d1[1].trim()));
  //ntime.setSeconds(Integer.parseInt(d1[2].trim()));
  }
return ntime ;
}*/



public static Date DateTime(Date dt, String time){

    String datePattern = "yyyy-MM-dd";
    String[] d2=time.split(" ");
    String[] d1=d2[0].split("\\:");
    Date ntime=null;
    SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    String date_out = dateFormat.format(dt);

      try {
             ntime = dateFormat.parse(date_out);
      } catch(Exception e) {
             e.printStackTrace();
      }

    String strHH = "00";
    String strMM = "00";

    if(d1[0].trim().length()==3) {
      d1[0]="0"+d1[0].trim();
    }

    if (d1[0].trim().length()>2) {
        strHH = d1[0].trim().substring(0, 2);
        strMM = d1[0].trim().substring(2,4);
    } else {
      strHH = d1[0].trim().substring(0);
    }

    if (d1.length>1 && !d1[1].trim().equals("")) {
      if (d1[1].trim().length()>2) {
        strMM = d1[1].trim().substring(0,2);
      } else {
        strMM = d1[1].trim();
      }
    }

    if(d2.length>1&&!d2[1].trim().equals("")&&d2[1].trim().equalsIgnoreCase("pm")) {

        if(Integer.parseInt(strHH)<12){
              ntime.setHours(Integer.parseInt(strHH)+12);
        } else {
          ntime.setHours(Integer.parseInt(strHH));
        }

    } else if(d2.length>1&&!d2[1].trim().equals("")&&d2[1].trim().equalsIgnoreCase("am")&&Integer.parseInt(strHH)==12)
    {
      ntime.setHours(Integer.parseInt(strHH)-12);
    } else {
          ntime.setHours(Integer.parseInt(strHH));
    }

    if(d1.length==1) {
          ntime.setMinutes(Integer.parseInt(strMM));
    } else if(d1.length==2) {
        ntime.setMinutes(Integer.parseInt(strMM));
    } else {
      ntime.setMinutes(Integer.parseInt(strMM));
    }
    return ntime ;
}




  public static String getTimeFromDate(Date dt)
  {
    String emptString="";
    if(dt == null)
      return emptString;
  DateFormat fDateFormat = null;
  UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
  User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
  //fDateFormat =DateFormat.getTimeInstance(DateFormat.DEFAULT);
  fDateFormat =DateFormat.getTimeInstance(DateFormat.SHORT);
  String date_out = fDateFormat.format (dt);
      if(user.getTimeFormat()!=null && !user.getTimeFormat().equals(Constants.TWENTY_FOURHOUR))
     {
      return date_out;
      }
     else
     {   Calendar cal = new GregorianCalendar();
         cal.setTime(dt);
         int hour24 = cal.get(Calendar.HOUR_OF_DAY);
         int min = cal.get(Calendar.MINUTE);
         if(min==0)
         {
         String m="00";
         date_out=String.valueOf(hour24)+":"+String.valueOf(m);
         }
         else
         {date_out=String.valueOf(hour24)+":"+String.valueOf(min);}
      return date_out;
     }
  }


public static String getTimewithsecondsFromDate(Date dt)
{
  String emptString="";
  if(dt == null)
    return emptString;
  DateFormat fDateFormat = null;
  UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
  User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
  fDateFormat =DateFormat.getTimeInstance(DateFormat.DEFAULT);
    String date_out = fDateFormat.format (dt);
    return date_out;
  }





  public static String formateJobDescriptionDate(Date date)
{
  String formatedDate="";
  if(date == null)
    return formatedDate;
  DateFormat fDateFormat = null;
    fDateFormat =DateFormat.getDateInstance (DateFormat.DEFAULT);

    String fDate=fDateFormat.format(date);

  String[] jobFinishDate=fDate.split("\\ ");

  String dateFormat = SecurityUtil.getUser() != null ? SecurityUtil.getUser().getDateFormat():"dd/mm/yyyy";
  if(dateFormat != null && !dateFormat.trim().equals(""))
  {
    String[] pattern = dateFormat.split("/");
    String month = pattern[1];
    if(pattern[0].trim().equalsIgnoreCase("mm"))
      dateFormat = Constants.MM_DD_YYYY_DATE_FORMAT;
    if(month.trim().equalsIgnoreCase("mmm"))
        dateFormat = Constants.DD_MMM_YYYY_DATE_FORMAT;
    if(month.trim().equalsIgnoreCase("mm"))
      dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT;
  }else
  {
    dateFormat = dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT;
  }
  String dayfield=jobFinishDate[1].trim();
  String monthfield=jobFinishDate[0].trim();
  String yearfield=jobFinishDate[2].trim();

  String[] dtPattern =  dayfield.split(",");
   dayfield = dtPattern[0].trim();

   if(dateFormat.trim().equals(Constants.DD_MM_YYYY_DATE_FORMAT) ||dateFormat.trim().equals(Constants.DD_MMM_YYYY_DATE_FORMAT))
   {
    if(monthfield.equals("Jan") )
       formatedDate=dayfield+" "+"January"+","+" "+yearfield;
    if(monthfield.equals("Feb") )
         formatedDate=dayfield+" "+"February"+","+" "+yearfield;
    if(monthfield.equals("Mar"))
       formatedDate=dayfield+" "+"March"+","+" "+yearfield;
    if(monthfield.equals("Apr"))
       formatedDate=dayfield+" "+"April"+","+" "+yearfield;
    if(monthfield.equals("May") )
       formatedDate=dayfield+" "+"May"+","+" "+yearfield;
    if(monthfield.equals("Jun"))
       formatedDate=dayfield+" "+"June"+","+" "+yearfield;
    if(monthfield.equals("Jul"))
       formatedDate=dayfield+" "+"July"+","+" "+yearfield;
    if(monthfield.equals("Aug"))
       formatedDate=dayfield+" "+"August"+","+" "+yearfield;
    if(monthfield.equals("Sep"))
       formatedDate=dayfield+" "+"September"+","+" "+yearfield;
    if(monthfield.equals("Oct") )
       formatedDate=dayfield+" "+"October"+","+" "+yearfield;
    if(monthfield.equals("Nov") )
       formatedDate=dayfield+" "+"November"+","+" "+yearfield;
    if(monthfield.equals("Dec") )
       formatedDate=dayfield+" "+"December"+","+" "+yearfield;
   }else
   {
     if(monthfield.equals("Jan") )
         formatedDate="January"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Feb") )
           formatedDate="February"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Mar"))
         formatedDate="March"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Apr"))
         formatedDate="April"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("May") )
         formatedDate="May"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Jun"))
         formatedDate="June"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Jul"))
         formatedDate="July"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Aug"))
         formatedDate="August"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Sep"))
         formatedDate="September"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Oct") )
         formatedDate="October"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Nov") )
         formatedDate="November"+" "+dayfield+","+" "+yearfield;
      if(monthfield.equals("Dec") )
         formatedDate="December"+" "+dayfield+","+" "+yearfield;
   }
  return formatedDate;
}


  public static String getUniquetimeString(){
      String DATE_FORMAT="HHmmss";
      SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
      Calendar c1 = Calendar.getInstance();
      String s=sdf.format(c1.getTime());
    return s;
    }

  public static Date getLastThursday(Date date){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    if(dayOfWeek>=Calendar.THURSDAY)
      cal.add(Calendar.DATE, Calendar.THURSDAY-dayOfWeek);
    else
      cal.add(Calendar.DATE,  Calendar.THURSDAY-dayOfWeek-7);
    return cal.getTime();
  }

  public static Date convertTStoDate(Date date)
  {     
      Date effDoday = null;
      DateFormat df = new SimpleDateFormat(Form.getCurrentUserFormat());
      try{
          effDoday = df.parse(DateUtil.formatDate(date, Form.getCurrentUserFormat())); 
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      return effDoday;
  }
}
