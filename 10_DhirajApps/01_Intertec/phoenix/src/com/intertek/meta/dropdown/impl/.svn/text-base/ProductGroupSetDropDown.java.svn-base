package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.ProductGroupService;

public class ProductGroupSetDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size < 1) return results;

    ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");

    String productGroupSetName = (String)params.get(0);

    List pgsList = productGroupService.getProductGroupSetNameList(productGroupSetName);

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
