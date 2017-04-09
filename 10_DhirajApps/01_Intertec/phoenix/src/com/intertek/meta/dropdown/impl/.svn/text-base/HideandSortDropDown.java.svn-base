package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.LogConfigDetail;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;

public class HideandSortDropDown implements DropDown
{
 List finalResults = new ArrayList();
  public List execute(List params)
  {
    List results = new ArrayList();

 JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
   
    List tabs = jobService.getTabNames();

	Field field1 = new Field();
	field1.setName("Main");
	field1.setValue("Main");
	Field field2 = new Field();
	field2.setName("Dispatch");
	field2.setValue("Dispatch");
	Field field3 = new Field();
	field3.setName("Billing");
	field3.setValue("Billing");
	Field field4 = new Field();
	field4.setName("Process Log");
	field4.setValue("Process Log");

	/*long confListId=1;*/


String confId = (String)params.get(0);
long confListId=0;
if(confId!=null && !confId.trim().equals("")){
	 confListId =(Long.valueOf(confId)).longValue(); 
}
else
	{
    confListId=1;
	}
			List Columns = jobService.getCloumnsByTabName(field1.getName(),confListId);
			finalResults.add(field1);
			getColumns(Columns);
			 
			List Columns1 = jobService.getCloumnsByTabName(field2.getName(),confListId);
			finalResults.add(field2);
			getColumns(Columns1);

			List Columns2 = jobService.getCloumnsByTabName(field3.getName(),confListId);
			finalResults.add(field3);
			getColumns(Columns2);

			List Columns3 = jobService.getCloumnsByTabName(field4.getName(),confListId);
			finalResults.add(field4);
			getColumns(Columns3);
			
			 
   
return finalResults;			
  }
 

 
		/*for(int i=0; i<tabs.size(); i++)
		{
		  String tabname = (String)tabs.get(i);		 
		  System.out.println("tabname is "+ tabname);
		  List Columns = null;

		  if(tabname.equalsIgnoreCase("main"))
		    { Columns = jobService.getCloumnsByTabName(tabname);
			results.add(field1);}

			 if(tabname.equalsIgnoreCase("Dispatch"))
			 {
				 Columns = jobService.getCloumnsByTabName(tabname);
				 results.add(field2);}

				 if(tabname.equalsIgnoreCase("Billing"))
					{
					  Columns = jobService.getCloumnsByTabName(tabname);
					 results.add(field3);}

						 if(tabname.equalsIgnoreCase("Process Log"))
							{
							 Columns = jobService.getCloumnsByTabName(tabname);
							 results.add(field4);}
		 
			 for(int j=0;j<Columns.size();j++)
			 {
			  LogConfigDetail logConfigDetail = (LogConfigDetail)Columns.get(j);
			  Field field = new Field();
			  field.setName(logConfigDetail.getLogConfigDetailId().getColName()); 
			  field.setValue(logConfigDetail.getLogConfigDetailId().getColName());
			  results.add(field);
			 }	 
		}  
    return results;*/
  

public void getColumns(List Columns)
	{
	List results1=new ArrayList();

	 for(int j=0;j<Columns.size();j++)
			 {
		 
			  LogConfigDetail logConfigDetail = (LogConfigDetail)Columns.get(j);
			  Field field = new Field();
			  field.setName(logConfigDetail.getColName()); 
			  field.setValue(logConfigDetail.getLogConfigDetailId().getColHeader());
			 finalResults.add(field);
			 }	
}



}