package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.ProductGroupService;

public class ProductGroupSetNamesDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");

    List pgsList = productGroupService.getProductGroupSetNameList();

    for(int i=0; i<pgsList.size(); i++)
    {
      String pgs = (String)pgsList.get(i);

      Field field = new Field();

      field.setName(pgs);
      field.setValue(pgs);
      results.add(field);
    }

    return results;
  }
}
