package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.LogConfigDetail;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;

public class FirstFreezeDropDown implements DropDown
{
 List finalResults = new ArrayList();

  public List execute(List params)
  {

    int size = params != null ? params.size() : 0;

    Field field1 = new Field();
	field1.setName("Main");
	field1.setValue("Main");

String confId = (String)params.get(0);

long confListId=0;
if(confId!=null && !confId.trim().equals("")){
	 confListId =(Long.valueOf(confId)).longValue(); 
}
else
	{
    confListId=1;
	}

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

 

    List Columns = jobService.getCloumnsByTabName(field1.getName(),confListId);
    for(int j=0;j<Columns.size();j++)
			 {
		 
			  LogConfigDetail logConfigDetail = (LogConfigDetail)Columns.get(j);
			  if(logConfigDetail.getFreezeOrder()!=0){
			  Field field = new Field();
			  field.setName(logConfigDetail.getColName()); 
			  field.setValue(logConfigDetail.getLogConfigDetailId().getColHeader());
			 finalResults.add(field);
			 }	
          }



	return finalResults;			
  }
}