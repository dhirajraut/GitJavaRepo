package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.intertek.entity.JobType;
import com.intertek.entity.Operation;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobTypeService;

public class OperationsDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
 //  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
     JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");


	 String jType = (String)params.get(0);
    
//JobType jobType=jobService.getJobOperationByJobType(jType);
	JobType jobType=jobTypeService.getJobOperationByJobType(jType);

    
	Field field1 = new Field();
	field1.setName("");
	field1.setValue("");
	results.add(field1);

	if(jobType != null)
	{
	  Set st =jobType.getOperations();
      Iterator itr =st.iterator();
	  HashMap map = new LinkedHashMap();
    
	  while(itr.hasNext())
      {
		Field field = new Field();
		Operation  operation=( Operation)itr.next();
		field.setName(operation.getDescription());
		field.setValue(operation.getOperationCode());
		map.put(field.getName(),field.getValue());
      }
	  
		List mapKeys = new ArrayList(map.keySet());
		TreeSet sortedSet = new TreeSet(mapKeys);
		
		Iterator itrr=sortedSet.iterator();
		while(itrr.hasNext())
		{
			Field field = new Field();
			field.setName((String)itrr.next());
			field.setValue((String)map.get(field.getName()));
			results.add(field);
		}
    }
    
	  return results;
   }
}
