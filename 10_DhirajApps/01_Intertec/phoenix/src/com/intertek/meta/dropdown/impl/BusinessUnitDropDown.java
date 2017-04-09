package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import com.intertek.entity.BusinessUnit;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.UserService;

public class BusinessUnitDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    int size = params != null ? params.size() : 0;
    
    //Commenting out because if no params then we need to display all BUs across all Orgs (mainly for Job Flow Page)
   // if(size == 0) return results;

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    List bus = null;
    HashMap map = new LinkedHashMap();
    
    if(size > 0)
    {
    	String orgName = (String)params.get(0);
    	bus = userService.getBusinessUnitsByDivisionName(orgName);
    }
    else
    {
    	//Load all BUs for job entry screen
    	bus = userService.getBusinessUnitsByName("");
    }
    
    if(bus != null && bus.size() > 0)
	  {
		Field field = new Field();
		field.setName("");
		field.setValue("");
		results.add(field);
	  }
    
    for(int i=0; i<bus.size(); i++)
    {
      BusinessUnit bu = (BusinessUnit)bus.get(i);

      Field field = new Field();
      String name = bu.getName();
      String description = bu.getDescription();
      if(description != null) name += " - " + description;
      field.setName(name);
      field.setValue(bu.getName());
      map.put(field.getName(),field.getValue());
      
    }
    List mapKeys = new ArrayList(map.keySet());
	TreeSet sortedSet = new TreeSet(mapKeys);
	
	Iterator itrr=sortedSet.iterator();
	int sortSize=sortedSet.size();
	while(sortSize>0)
	{
		Field field = new Field();
		field.setName((String)itrr.next());
		field.setValue((String)map.get(field.getName()));
		results.add(field);
		sortSize--;
    }

    return results;
  }
}
