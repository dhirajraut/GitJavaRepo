package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.intertek.entity.DropDowns;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.DropdownService;

public class DropDownTypeDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
    List dropdownTypes = dropdownService.getDropdownTypes();
    
    HashMap fieldMap = new HashMap();
    
    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    
    for(int i=0; i<dropdownTypes.size(); i++)
    {
    	DropDowns dropDowns = (DropDowns) dropdownTypes.get(i);

      
      String name = dropDowns.getDropDownId().getDropdownType();
      
      
      
      
      if(!fieldMap.containsKey(name))
      {    
	      Field field = new Field();
	      field.setName(name);
	      field.setValue(name);
	      results.add(field);
	      fieldMap.put(name, name);
      }
    }

    return results;
  }
}
