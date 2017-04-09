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

public class ZoneIdDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");


   int size = params != null ? params.size() : 0;

    if(size < 2) return results;


    String portCode = (String)params.get(0);
    String contractCode = (String)params.get(1);
	String zone=(String)params.get(2);
	String jdate=(String)params.get(3);
    String edate=(String)params.get(4);
	String priceBookId=(String)params.get(5);
	 
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
    List ZoneIds=jobService.getZoneByPortCodeandContractCode(portCode,contractCode,priceBookId,dt);
	
	List portCodes=jobService.getPortCodeByContractCode(contractCode,priceBookId,dt);

   List pZones=jobService.getZoneByContractCode(contractCode,priceBookId,dt);

   List zones=new ArrayList();
   for(int i=0; i<portCodes.size(); i++)
    {
    PortLocation portLocation = (PortLocation)portCodes.get(i);
     zones=jobService.getZoneByPortCodeandContractCode(portLocation.getPortLocationId().getPortCode(),contractCode,priceBookId,dt);
	}


	
	/*if(!portCode.trim().equals("")&&ZoneIds.size()==0)
	  {
		Field field1 = new Field();
    	field1.setName(zone);
		field1.setValue(zone);
		results.add(field1);
	  }
             
	 if(portCode.trim().equals("")&&ZoneIds.size()==0)
	  {
       Field field1 = new Field();
    	field1.setName(zone);
		field1.setValue(zone);
		results.add(field1);		
	  }	*/

if(portCode.trim().equals("")||portCode.trim().equals("NONE"))
  {
    if(portCodes.size()==0 && zones.size()==0&&pZones.size()==0&&zone.trim().equals("NONE"))
	  {
        Field field = new Field();
    	field.setName("*");
		field.setValue("*");
		results.add(field);	
	  }
	  else
	  {
		Field field = new Field();
    	field.setName(zone);
		field.setValue(zone);
		results.add(field);
	  }

  }

   for(int i=0; i<ZoneIds.size(); i++)
    {
      PortLocation portLocation = (PortLocation)ZoneIds.get(i);
      Field field = new Field();
      field.setName(portLocation.getPortLocationId().getLocation());
	  field.setValue(portLocation.getPortLocationId().getLocation());
      results.add(field);
    }
	return results;
  }
 }
