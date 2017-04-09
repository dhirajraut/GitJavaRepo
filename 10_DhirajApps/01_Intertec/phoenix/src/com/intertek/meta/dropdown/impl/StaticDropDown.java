package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import com.intertek.cache.ITSCacheManager;
import com.intertek.entity.DropDowns;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.DropdownService;
import com.intertek.service.JobService;
import com.intertek.util.Constants;

public class StaticDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    int size = params != null ? params.size() : 0;
    String dropdownName = "";
    String dropDownVal = "";
    String invoiceType = "REG";
    int jobContractCount = 0;
    String invGeneratedFlag = "false";
    String jobfinishdate="";
    boolean invoiced = false;
    JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");   
    if(params != null && params.size() > 0)
    {
    	dropdownName = (String)params.get(0);
    	if(dropdownName != null && (!dropdownName.trim().equals("")) && dropdownName.trim().equals("jobContractStatus") )
    		dropDownVal = (String)params.get(1);
    	if(dropdownName != null && (!dropdownName.trim().equals("")) && dropdownName.trim().equals("jobStatus") )
    	{
    		dropDownVal = (String)params.get(1);
    		//invoiceType = (String)params.get(2);
    		jobContractCount = Integer.parseInt((String) params.get(2));
    		invGeneratedFlag = (String) params.get(3);  
    		jobfinishdate=(String)params.get(5);
    		
    	}
    }
    
    String[] dropdownsWithBlank = Constants.staticDropdowns;
    boolean blankValueFlag = false;
    if(dropdownsWithBlank != null && dropdownsWithBlank.length > 0)
    {
    	for(int i=0;i<dropdownsWithBlank.length;i++)
    	{
    		String blankDropdown = dropdownsWithBlank[i];
    		if(!blankValueFlag && blankDropdown.trim().equalsIgnoreCase(dropdownName))
    		{
    			blankValueFlag = true;
    			break;
    		}
    	}
    }
    DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
    
   // List dropdownList = dropdownService.getDropDownsByDropdownType(dropdownName);
    
//  Cache is stored at application level

    String[] key = {dropdownName};

    List cachedValues = (List) ITSCacheManager.getCacheItem(dropdownName, key);
    
    if (cachedValues == null) {

    /* Cache data from db */

    	cachedValues = dropdownService.getDropDownsByDropdownType(dropdownName);                               
    	ITSCacheManager.setCacheItem(dropdownName, key, cachedValues);
    }
    
    if(blankValueFlag)
    {
	    Field field1 = new Field();
	    field1.setName("");
	    field1.setValue("");
	    results.add(field1);
    }
    
    for(int i=0; i<cachedValues.size(); i++)
    {
      DropDowns dropDowns = (DropDowns) cachedValues.get(i);
      String value = dropDowns.getDropDownId().getFieldName();
      String name = dropDowns.getFieldValue();
      //handle job status dropdown values based on state
      if(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("new"))) //Job is in new status
	  {
    	  
    	 // if(value.trim().equals("1000") || value.trim().equals("6000"))
    	  if(value.trim().equals("1000"))// Job Status while create 
    	  {
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }
	  }
      //this code is added on 22-12-2008
      else if(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("1000"))&& (jobfinishdate!=null && !jobfinishdate.trim().equals(""))) //Job is in open status
	  {
	   	   if(value.trim().equals("1000"))
	   	   {    		  	 
	   		      Field field = new Field();
			      field.setName(name);
			      field.setValue(value);
			      results.add(field);
	   	   }
	      if(value.trim().equals("6000") && invGeneratedFlag.trim().equals("true"))
	   	  {
	    	  	  invoiced = true;
	    	  	  Field field = new Field();
			      field.setName(name);
			      field.setValue(value);
			      results.add(field);
	   	  }
	  }
      
      //up to here
      
      else if(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("1000"))) //Job is in open status
	  {
    	  if(value.trim().equals("1000"))
    	  {
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }
    	  if(value.trim().equals("6000") && invGeneratedFlag.trim().equals("true"))
    	  {   
    		  invoiced = true;
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }
    	//code was commented on 22-12-2008
    	/*  else if(value.trim().equals("6000") && jobContractCount <= 0 && invGeneratedFlag.trim().equals("false"))
    	  {
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }*/
    	//up to here
    	  if(value.trim().equals("1") && invGeneratedFlag.trim().equals("false"))
    	  {
    		 String jobNumber = (String) params.get(4);
    	     String jobFinishDate = (String) params.get(5);
    		 JobOrder jobOrder = jobService.getJobOrderByJobNumberWithJobContracts(jobNumber);
    	   	 Set jc = jobOrder.getJobContracts();
    		 int monthlyJobCount = 0;
       		 int invoicedCount = 0;
       		 if(jc != null && jc.size()>0){
 	      		 for(Iterator itr = jc.iterator();itr.hasNext();){
 	      			 JobContract jobContract = (JobContract)itr.next();
 	 	      		 /* KETAN - 03/30/09 - START - Modification of Job Status dropdown condition for Monthly Jobs */
 	      			 //if(jobContract != null && jobContract.getMonthlyFlag() != null && 
 	      					 //jobContract.getMonthlyFlag()== true && jobFinishDate != null && jobFinishDate.trim().equals(""))
 	      			if(jobContract != null && 
 	      			   jobContract.getMonthlyJobNumber()!=null && 
 	      			   (jobFinishDate==null || jobFinishDate.trim().equals("")))
 	      			 {
 	      				monthlyJobCount++;
 	      				invoicedCount++;
 	      			 }
 	      		 }
 	      		if(monthlyJobCount == 0)
 	      		 /* KETAN - 03/30/09 - END - Modification of Job Status dropdown condition for Monthly Jobs */
 	       		 {
 	      			  Field field = new Field();
	 			      field.setName(name);
	 			      field.setValue(value);
	 			      results.add(field);
 			     }
    	     } else {
    	    	  Field field = new Field();
			      field.setName(name);
			      field.setValue(value);
			      results.add(field);
    	     }
    	  }
    	 /* if(invoiceType.trim().equals(Constants.INV_TYPE_REG) && value.trim().equals("1"))
    	  {
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }*/
	  }
      else if(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("6000"))) //Job is in closed status
	  {
    	  if(value.trim().equals("7000") || value.trim().equals("6000"))
    	  {
    		  invoiced = true;
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }
	  }
      else if(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("7000"))) //Job is in reopen status
	  {
    	  if(value.trim().equals("7000") || value.trim().equals("6000"))
    	  {
    		  invoiced = true;
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }
	  }
      else if(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("1"))) //Job is in cancelled status
	  {
    	  /* KETAN - 03/31/09 - START - Modification of condition required to add Reopen option in the Job Status dropdown */
    	  //if(value.trim().equals("1"))
          if(value.trim().equals("1") || value.trim().equals("7000"))  
          /* KETAN - 03/31/09 - END - Modification of condition required to add Reopen option in the Job Status dropdown */
    	  {
    		  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }
	  }
      
      //this code is added on 23-12-2008
     
	  if(params!=null && params.size()==6)
      {
    	 String jobNumber = (String) params.get(4);
    	 String jobFinishDate = (String) params.get(5);
    	 if(dropdownName != null && (!dropdownName.trim().equals("")) && dropdownName.trim().equals("jobStatus") && !jobNumber.trim().equals("0") )
      	 {   		   		
   		 JobOrder jobOrder = jobService.getJobOrderByJobNumberWithJobContracts(jobNumber);
   		 Set jc = jobOrder.getJobContracts();
   		 if(jc != null && jc.size()>0 && jobFinishDate != null && !jobFinishDate.trim().equals(""))
   		 {
       		 int monthlyJobCount = 0;
       		 int invoicedCount = 0;
       		 for(Iterator itr = jc.iterator();itr.hasNext();){
       			 JobContract jobContract = (JobContract)itr.next();
       			 if(jobContract != null && jobContract.getMonthlyFlag() != null && 
       					 jobContract.getMonthlyFlag()== true && jobContract.getInvoice() != null && !jobContract.getInvoice().trim().equals(""))
       			 {
       				monthlyJobCount++;
       				invoicedCount++;
       			 }
       		 }
       		 if(monthlyJobCount != 0 && invoicedCount != 0)
       		 { 
	 		  if(invoiced == false && value.trim().equals("6000"))
          	  {
          		  Field field = new Field();
      		      field.setName(name);
      		      field.setValue(value);
      		      results.add(field);
			  }
       		 }
   		 } else
   		 {
   			 int monthlyJobCount = 0;
      		 int invoicedCount = 0;
      		 if(jc != null && jc.size()>0)
      		 {
	      		 for(Iterator itr = jc.iterator();itr.hasNext();){
	      			 JobContract jobContract = (JobContract)itr.next();
	      			 if(jobContract != null && jobContract.getMonthlyFlag() != null && 
	      					 jobContract.getMonthlyFlag()== true && jobFinishDate != null && jobFinishDate.trim().equals(""))
	      			 {
	      				monthlyJobCount++;
	      				invoicedCount++;
	      			 }
	      		 }
	      		 if(monthlyJobCount == 0 && invoicedCount == 0)
	       		 {
	      			/* KETAN - 03/03/09 - START - Modification of condition required to add CLOSE option in the Job Status dropdown */
	      			//if(invoiced == false && value.trim().equals("6000") && !(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("1"))))
	      			if(invoiced==true && value.trim().equals("6000") && !(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("1"))))
	      			/* KETAN - 03/03/09 - END - Modification of condition required to add CLOSE option in the Job Status dropdown */	 
	      			{
	          		  Field field = new Field();
	      		      field.setName(name);
	      		      field.setValue(value);
	      		      results.add(field);
	          	    }
	       		 }
      		 }
      		 if(jc != null && jc.size() == 0 && jobFinishDate != null && jobFinishDate.trim().equals("")&& 
      				 !(dropdownName.trim().equals("jobStatus") && (dropDownVal.trim().equals("1"))))
      		 {
      			  /* KETAN - 03/31/09 - START - Modification of condition required to add CLOSE option in the Job Status dropdown */
		 		  //if(invoiced == false && value.trim().equals("6000"))
      			  if(invoiced == true && value.trim().equals("6000"))
      			  /* KETAN - 03/31/09 - END - Modification of condition required to add CLOSE option in the Job Status dropdown */
	          	  {
	          		  Field field = new Field();
	      		      field.setName(name);
	      		      field.setValue(value);
	      		      results.add(field);
	          	  } 
      		 }
   		 }
      	}  
      }
	  //End
     
//    handle job status dropdown values based on state Ends      

  else    if(dropdownName.trim().equals("jobContractStatus") && (dropDownVal.trim().equals("6000")||dropDownVal.trim().equals("7100") || dropDownVal.trim().equals("7200")))
	  {
    	  if(value.trim().equals("6000") || value.trim().equals("7100") || value.trim().equals("7200"))
    	  {
	    	  Field field = new Field();
		      field.setName(name);
		      field.setValue(value);
		      results.add(field);
    	  }
	  }/*For iTrack#135193 -START */
	  else if(dropdownName.trim().equals("paymentType")){
		  String creditApp = (String)params.get(1);
		  if(creditApp.isEmpty()||creditApp.equals("false"))
    	  {
			  if(value.trim().equals("CHE") || value.trim().equals("CRE")|| value.trim().equals("COD")){
		    	  Field field = new Field();
			      field.setName(name);
			      field.setValue(value);
			      results.add(field);
			  }
    	  } else {
    		  Field field = new Field();
    	      field.setName(name);
    	      field.setValue(value);
    	      results.add(field);
    	  }
	  }/*For iTrack#135193 -END */
      else
      {
    	  Field field = new Field();
	      field.setName(name);
	      field.setValue(value);
	      results.add(field);
      }
	      
      }
    

    return results;
  }
}
