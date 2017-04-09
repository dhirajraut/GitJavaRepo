package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intertek.entity.PortLocation;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;
import com.intertek.util.DateUtil;

public class PortCodeDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

    String contractCode = (String)params.get(0);
	String jdate=(String)params.get(1);
    String edate=(String)params.get(2); 
	String priceBookId=(String)params.get(3);

	Date asOfDate=new Date(); 
  
    if(jdate!=null&&!jdate.trim().equals("")&&edate==null&&edate.trim().equals(""))
	  {
	   asOfDate=DateUtil.parseDate(jdate,"dd/MM/yyyy");	
	   }
   else if(jdate==null&&jdate.trim().equals("") &&edate!=null&&!edate.trim().equals(""))
      {
	   asOfDate=DateUtil.parseDate(edate,"dd/MM/yyyy");
      }
       else if(jdate==null&&jdate.trim().equals("") &&edate==null&&!edate.trim().equals(""))
	       { 
		   asOfDate=new Date();
		   }
	  

     String dt=DateUtil.formatDate(asOfDate,"dd-MMM-yyyy");
	
	
	List portCodes=jobService.getPortCodeByContractCode(contractCode,priceBookId,dt);

	  if(portCodes.size()==0)
	  {
        Field field1 = new Field();
		field1.setName("NONE");
		field1.setValue("NONE");
		results.add(field1);
	  }
	  else
		  if(portCodes.size()!=0)
	  {
		Field field1 = new Field();
		field1.setName("");
		field1.setValue("");
		results.add(field1);
	  }
    for(int i=0; i<portCodes.size(); i++)
    {
      PortLocation portLocation = (PortLocation)portCodes.get(i);
      Field field = new Field();
     /*field.setName(portLocation.getPortLocationId().getPortCode());
	  field.setValue(portLocation.getPortLocationId().getPortCode());
      results.add(field);*/
	  if(!portLocation.getPortLocationId().getPortCode().trim().equals(""))
	  {
      field.setName(portLocation.getPortLocationId().getPortCode());
	  field.setValue(portLocation.getPortLocationId().getPortCode());
      results.add(field);
	  }
    }
    
    return results;
  }
}
