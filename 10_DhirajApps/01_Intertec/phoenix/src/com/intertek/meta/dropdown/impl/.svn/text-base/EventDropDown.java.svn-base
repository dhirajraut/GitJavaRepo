package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.entity.Event;
import com.intertek.entity.Operation;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;

public class EventDropDown implements DropDown
{
  public List execute(List params)
  {
   List results = new ArrayList();
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

	 String operationCode = (String)params.get(0);
    
    
	Operation operation=jobService.getOperationByOperationCode(operationCode);
    
	Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);

	  Set st =operation.getEvents();
	  
	  if(st != null && st.size() >0)
	  {
		  Iterator itr =st.iterator();
    
		  while(itr.hasNext())
			{
			Field field = new Field();
			Event  event=( Event)itr.next();
			field.setName(event.getEventName());
			field.setValue(event.getEventCode());
			results.add(field);
			}
	  }
    
	  return results;
   }
}
