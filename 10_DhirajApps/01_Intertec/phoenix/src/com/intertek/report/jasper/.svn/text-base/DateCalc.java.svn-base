package com.intertek.report.jasper;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalc {

  static final String C_DUE = "DUE";
  static final String C_DOR = "DOR";
  static final String C_NET7 = "NET 7";
  static final String C_NET14 = "NET14";
  static final String C_NET30 = "NET30";
  static final String C_NET45 = "NET45";
  static final String C_NET60 = "NET60";
  static final String C_NET63 = "NET63";
  static final String C_EOM45 = "EOM45";
  static final long C_SINGLEDAY = 86400000l;

  public static Date findDisplacement(String formulaCode, Date originalDate){
    long originalTimeMilli = originalDate.getTime();

    if (formulaCode.equalsIgnoreCase(C_DUE)||formulaCode.equalsIgnoreCase(C_DOR)){
      return new Date(originalTimeMilli);
    }
    if (formulaCode.equalsIgnoreCase(C_NET7)){
      return new Date(originalTimeMilli + (7 * C_SINGLEDAY));
    }
    if (formulaCode.equalsIgnoreCase(C_NET14)){
      return new Date(originalTimeMilli + (14 * C_SINGLEDAY));
    }
    if (formulaCode.equalsIgnoreCase(C_NET30)){
      return new Date(originalTimeMilli + (30 * C_SINGLEDAY));
    }
    if (formulaCode.equalsIgnoreCase(C_NET45)){
      return new Date(originalTimeMilli + (45 * C_SINGLEDAY));
    }
    if (formulaCode.equalsIgnoreCase(C_NET60)){
      return new Date(originalTimeMilli + (60 * C_SINGLEDAY));
    }
    if (formulaCode.equalsIgnoreCase(C_NET63)){
      return new Date(originalTimeMilli + (63 * C_SINGLEDAY));
    }
    if (formulaCode.equalsIgnoreCase(C_EOM45)){
      int origDay = originalDate.getDate();
      int origMonth = originalDate.getMonth();
      int origYear = originalDate.getYear();
      GregorianCalendar gc = new GregorianCalendar(origYear,origMonth,origDay);
      int totalDays = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
      long milliToAdd = ((totalDays - origDay) + 45 ) * C_SINGLEDAY;
      return new Date(originalTimeMilli + (milliToAdd));
    }

    return null;
  }

  public static void main (String [] args){
    Date week = DateCalc.findDisplacement("Net 7", new Date());
    System.out.println(week.toString());

    Date eom = DateCalc.findDisplacement("EOM45", new Date());
    System.out.println(eom.toString());



  }


}
