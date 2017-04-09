package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.BusinessUnit;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.UserService;

public class ShipAgentDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size == 0) return results;

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    String orgName = (String)params.get(0);
    List bus = userService.getBusinessUnitsByDivisionName(orgName);
    for(int i=0; i<bus.size(); i++)
    {
      BusinessUnit bu = (BusinessUnit)bus.get(i);

      Field field = new Field();
      String name = bu.getName();
      String description = bu.getDescription();
      if(description != null) name += " - " + description;
      field.setName(name);
      field.setValue(bu.getName());
      results.add(field);
    }

    return results;
  }
}
