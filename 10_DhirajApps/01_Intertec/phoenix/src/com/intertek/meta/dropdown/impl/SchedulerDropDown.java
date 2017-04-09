package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Contact;
import com.intertek.entity.JobContract;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CustomerService;
import com.intertek.service.JobService;

public class SchedulerDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

   // if(size < 2) return results;

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");

    String jobNumber = (String)params.get(0);
    
    
    // Approved by setting 

    String otApproved = (String)params.get(1);
   
    if(otApproved != null  && otApproved.trim().equals("true"))
    {
	    List jobContracts = jobService.getJobContractsByJobNumber(jobNumber);
	    if(jobContracts != null && jobContracts.size() > 0)
	    {
		    for(int i=0; i<jobContracts.size(); i++)
		    {
		      JobContract jobContract = (JobContract)jobContracts.get(i);
		      Contact schedulerContact = customerService.getContactById(jobContract.getContactId());
		      if(schedulerContact != null)
		      {
		        Field field = new Field();
		
		        String contactName = schedulerContact.getFirstName() + " " + schedulerContact.getLastName();
		        field.setName(contactName);
		        field.setValue(contactName);
		        results.add(field);
		      }
		    }
	    }
    }
    //End
    return results;
  }
}
