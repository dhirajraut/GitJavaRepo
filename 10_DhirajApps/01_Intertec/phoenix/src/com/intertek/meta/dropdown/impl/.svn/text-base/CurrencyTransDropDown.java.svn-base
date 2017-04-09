package com.intertek.meta.dropdown.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;
import com.intertek.util.NumberUtil;

public class CurrencyTransDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

String currency = (String)params.get(0);
Date effectiveDate=null;
if(params.size()>1){
	try{
		SimpleDateFormat df=new SimpleDateFormat("MM/dd/yy");
		effectiveDate=df.parse((String)params.get(1));
	}
	catch(Exception e){
	}
}

    List currencyTrans=jobService.getCurrencyTransByCurrency(currency, effectiveDate);

	/*if(currencyTrans != null && currencyTrans.size() > 0)
	  {
		Field field = new Field();
		field.setName(currency);
		field.setValue(currency);
		results.add(field);
	  }*/

   for(int i=0; i<currencyTrans.size(); i++)
    {
      //CurrencyRate currencyRate = (CurrencyRate)currencyTrans.get(i);
      Object[] currencyRateObj = (Object[])currencyTrans.get(i);
      if(currencyRateObj.length == 0) continue;
      
      Field field = new Field();
	  String currCode=(String) currencyRateObj[0];
	  BigDecimal currMult=(BigDecimal) currencyRateObj[1];
	  BigDecimal currDiv=(BigDecimal) currencyRateObj[2];

	  double currencyMultipler=currMult.doubleValue()/currDiv.doubleValue();
	  currencyMultipler=NumberUtil.roundHalfUp(currencyMultipler,6);
	  field.setName(currCode+ " - " + currencyMultipler);
	  field.setValue(currCode);
	  
	  
      results.add(field);
    }
    return results;
  }
}
