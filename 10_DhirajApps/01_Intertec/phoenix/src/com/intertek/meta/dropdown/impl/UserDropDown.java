package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.UserService;

public class UserDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    int size = params != null ? params.size() : 0;
    
    //Commenting out because if no params then we need to display all BUs across all Orgs (mainly for Job Flow Page)
   // if(size == 0) return results;

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    List users = null;
    
 
    	users = userService.getUsersByName("");
 
    	Field field1 = new Field();
    	field1.setName("");
        field1.setValue("");
        results.add(field1);
        
    for(int i=0; i<users.size(); i++)
    {
      User user = (User)users.get(i);

      Field field = new Field();
      String name = user.getLoginName();
      String FullName = user.getFirstName() + " " + user.getLastName();

      field.setName(FullName);
      field.setValue(name);
      results.add(field);
    }

    return results;
  }
}
