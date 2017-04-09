package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.UserService;

public class BusStreamDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size < 1) return results;

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    String branchName = (String)params.get(0);
    List<String> busStreamCodeList = userService.getBusStreamsByBranchName(branchName);
    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    for(String busStreamCode : busStreamCodeList)
    {
      Field field = new Field();
      
      field.setName(busStreamCode);
      field.setValue(busStreamCode);
      results.add(field);
    }

    return results;
  }
}
