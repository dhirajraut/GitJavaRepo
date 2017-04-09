package com.intertek.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.intertek.entity.AutoNumber;
import com.intertek.util.Constants;

public class AutoNumberServiceImpl extends BaseService implements AutoNumberService
{
  public void addAutoNumber(AutoNumber autoNumber)
  {
    getDao().save(autoNumber);
  }

  /**
 * Name :getAutoNumber
 * Date :Jul 16, 2008
 * Purpose : creating invoice id for individual and consolidated invoice
 * @param businessUnit
 * @param numberCategory
 * @param numberType
 * @return
 * step 1: It looks for the business unit and category
 * step 2: If it can not find any, it looks just for business unit
 * step 3: If it can not find any, it looks for business unit = share
 * step 4: Set the last auto number
 */


public AutoNumber getAutoNumber(String businessUnit, String numberCategory, String numberType){
  return getAutoNumber(businessUnit, numberCategory, numberType, false);
}

public synchronized AutoNumber getAutoNumber(String businessUnit, String numberCategory, String numberType, boolean reset) {
    if(numberType == null) return null;

    if(businessUnit == null) businessUnit = Constants.SHARE;
    if(numberCategory == null) numberCategory = Constants.SHARE;

    AutoNumber autoNumber = null;

    List ans = getDao().find(
      "from AutoNumber an "
      + "where an.autoNumberId.businessUnit = ? "
      + "and an.autoNumberId.numberCategory = ? "
      + "and an.autoNumberId.numberType = ? ",
      new Object[] { businessUnit, numberCategory, numberType }
    );

    if(ans == null || ans.isEmpty())
       ans = getDao().find(
          "from AutoNumber an "
          + "where an.autoNumberId.businessUnit = ? "
          + "and an.autoNumberId.numberType = ? ",
          new Object[] { businessUnit, numberType }
        );

    if(ans == null || ans.isEmpty())
      ans = getDao().find(
            "from AutoNumber an "
            + "where an.autoNumberId.businessUnit = ? "
            + "and an.autoNumberId.numberType = ? ",
            new Object[] { Constants.SHARE, numberType }
      );


    if(ans!=null && ans.size() > 0) autoNumber = (AutoNumber)ans.get(0);

  if(autoNumber != null){
    if(reset && checkDate(autoNumber.getLastUpdatedDate())){
      int startNum=1;
      try{
        startNum=Integer.parseInt(autoNumber.getBeginSequence());
      }
      catch(Exception e){

      }
      autoNumber.setLastAutoNumber(startNum);
    }
    else{
      autoNumber.setLastAutoNumber(autoNumber.getLastAutoNumber() + 1);
    }
    autoNumber.setLastUpdatedDate(new Date());
  }
    return autoNumber;
  }

  private boolean checkDate(Date aDate){
    if(aDate==null){
      return false;
    }
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy");
    String now=formatter.format(new Date());
    String aDateS=formatter.format(aDate);
    return !aDateS.equals(now);
  }

  public String getSequenceNumber(String businessUnit, String numberCategory, String numberType)
  {
    AutoNumber autoNumber = getAutoNumber(businessUnit, numberCategory, numberType);
    if(autoNumber == null) return null;

    String result = autoNumber.getLastAutoNumber() + "";
    int maxLength = autoNumber.getMaxLength();
    String beginSeq = autoNumber.getBeginSequence();

    int diff = maxLength - (result.length() + beginSeq.length());
    if(diff > 0)
    {
      for(int i=0; i<diff; i++)
      {
        result = "0" + result;
      }
    }

    return beginSeq + result;
  }
 public List getNumCategoryByBusinessUnit(String businessUnit){

   List autonumbers = getDao().find(" from AutoNumber a where a.autoNumberId.businessUnit = '"+businessUnit+"'",
          null);

      if(autonumbers.size()>0) return autonumbers;
      return null;

 }

  public String getSeqNumber(String businessUnit, String numberCategory, String numberType) {
      AutoNumber autoNumber = getAutoNumber(businessUnit, numberCategory, numberType, true);
      if(autoNumber == null) return null;

      String result = autoNumber.getLastAutoNumber() + "";
      int maxLength = autoNumber.getMaxLength();
      String beginSeq = "";//autoNumber.getBeginSequence();

      int diff = maxLength - (result.length() + beginSeq.length());
      if(diff > 0)
      {
        for(int i=0; i<diff; i++)
        {
          result = "0" + result;
        }
      }

      SimpleDateFormat formatter=new SimpleDateFormat("yy");
      String year=formatter.format(autoNumber.getLastUpdatedDate());

      return numberCategory+"-"+numberType+year+beginSeq + result;
  }
}

