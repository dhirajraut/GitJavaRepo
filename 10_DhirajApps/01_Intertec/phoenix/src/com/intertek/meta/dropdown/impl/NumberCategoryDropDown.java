package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import com.intertek.entity.AutoNumber;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.AutoNumberService;

public class NumberCategoryDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    AutoNumberService autoNumberService = (AutoNumberService)ServiceLocator.getInstance().getBean("autoNumberService");

	String buName = (String)params.get(0);
	
	List autoNumbers=autoNumberService.getNumCategoryByBusinessUnit(buName);
   
    if(autoNumbers != null){
    	
    	HashMap map = new LinkedHashMap();
    	 
    	 for(int i=0;i<autoNumbers.size();i++)
 	    {
 	    	AutoNumber  autoNumber=(AutoNumber)autoNumbers.get(i);
 	    	Field field = new Field();
 			field.setName(autoNumber.getAutoNumberId().getNumberCategory());
 			field.setValue(autoNumber.getAutoNumberId().getNumberCategory());
 			map.put(field.getName(),field.getValue());
 	    }
    	List mapKeys = new ArrayList(map.keySet());
  		TreeSet sortedSet = new TreeSet(mapKeys);
  		
 		Iterator itr=sortedSet.iterator();
 		int size=sortedSet.size();
 		while(size>0)
 		{
 			Field field = new Field();
 			field.setName((String)itr.next());
 			field.setValue((String)map.get(field.getName()));
 			results.add(field);
 			size--;
 		}
    }
	return results;
   }
}
