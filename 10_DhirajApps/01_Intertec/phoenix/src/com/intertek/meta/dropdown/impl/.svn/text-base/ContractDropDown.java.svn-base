package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.JobContract;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;

public class ContractDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    int size = params != null ? params.size() : 0;

    if(size < 1) return results;
    
    String jobContractIds = (String)params.get(0);
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    
    if(jobContractIds != null && (!jobContractIds.trim().equals("")))
    {
    	//
    	String[] ids = jobContractIds.split(";");
    	if(ids != null && ids.length > 0)
    	{
    		for(int i=0;i<ids.length;i++)
    		{
    			String id = ids[i];
    			JobContract jobContract = jobService.getJobContractById(new Long(id).longValue());
    			if(jobContract != null)
    			{
    			      Field field = new Field();
    			      String name = jobContract.getContractCode();
    			      String value = new Long(jobContract.getId()).toString();
    			      
    			      field.setName(name);
    			      field.setValue(value);
    			      boolean added = false;
    			      if(results != null && results.size() > 0)
    			      {
    			    	  for(int j=0;j<results.size();j++)
    			    	  {
    			    		  Field addedField = (Field) results.get(j);
    			    		  if(addedField.getValue().equals(value))
    			    			  added = true;
    			    	  }
    			    	  
    			      }
    			      if(!added)
			    		  results.add(field);
    			      
    			}
    			
    		}
    	}
    	
    }
    else
    	return results;
    


    return results;
  }
}
