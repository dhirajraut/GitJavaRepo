package com.intertek.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil
{
   //public static double round(double input, int precision)
   //{

   /* if(precision < 0) return (double)Math.round(input);

    long factor = (long)Math.pow(10,precision);

    input = input * factor;

    long tmp = Math.round(input);

    return (double)tmp / factor;*/
/*
    Double inputVal = new Double(input);
    String inputStr = inputVal.toString();


    DecimalFormat formatter = new DecimalFormat();
    formatter.setMaximumFractionDigits(precision);
    String numstr = "";
    double returnVal=0;
    try{
     numstr = formatter.format(formatter.parse(inputStr));
     returnVal = formatter.parse(numstr).doubleValue();
    }
    catch(Exception e)
    {

    }

    return returnVal;

  }*/

    public static double roundHalfUp2(double input, int scale){
        long powerOf10[]={1, 
                          10, 
                          100, 
                          1000, 
                          10000, 
                          100000, 
                          1000000, 
                          10000000, 
                          100000000, 
                          1000000000};
        if(scale>=0 && scale<powerOf10.length){
            double round=0.50001;
            if(input<0){
                round=-0.50001;
            }
            long value=(long)(input*powerOf10[scale]+round);
            return (double)((value*1.0000)/(powerOf10[scale]*1.0000));
        }
        System.out.println("Cannot round with scale="+scale+" for input="+input);
        return -1;
    }
    
  public static double roundHalfUp(double input, int scale)
  {
    BigDecimal bd = new BigDecimal(String.valueOf(input));
    // setScale is immutable
    bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
    
    try{
        String x=String.valueOf(roundHalfUp2(input, scale));
        String x1=String.valueOf(bd.doubleValue());
        if(!x.equals(x1)){
            System.out.println("Problem in Rounding for input="+input+" and scale="+scale+" x="+x+" x1="+x1);
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    return bd.doubleValue();
  }

  public static double roundDown(double input, int scale)
  {
    BigDecimal bd = new BigDecimal(String.valueOf(input));
    // setScale is immutable
    bd = bd.setScale(scale, BigDecimal.ROUND_DOWN);
    return bd.doubleValue();
  }

  public static Integer getInteger(String value)
  {
    if(value == null) return null;

    Integer result = null;

    try
    {
      result = new Integer(value);
    }
    catch(Exception e)
    {
    }

    return result;
  }

  public static boolean isOverLap(int minQty1, int maxQty1, int minQty2, int maxQty2)
  {
    if((minQty1 == minQty2) || (maxQty1 == maxQty2)) return true;

    if((minQty1 < minQty2) && (maxQty1 > minQty2)) return true;

    if((minQty1 > minQty2) && (minQty1 < maxQty2)) return true;

    return false;
  }
}
