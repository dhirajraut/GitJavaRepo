package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Branch;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.UserService;
import com.intertek.util.StringUtil;

public class BuStreamBranchDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    String buName = (String)params.get(0);
    List branches = userService.getBuStreamBranchesByBuName(buName);
   
    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    
    for(int i=0; i<branches.size(); i++)
    {
      Branch branch = (Branch)branches.get(i);

      Field field = new Field();
      String name = branch.getName();
      String description = branch.getDescription();
      // if(description != null)
      // description = StringUtil.forHTML(description);
      if(description != null) name += " - " + description;
    
      field.setName(name);
      field.setValue(branch.getName());
      results.add(field);
    }

    return results;
  }
}
