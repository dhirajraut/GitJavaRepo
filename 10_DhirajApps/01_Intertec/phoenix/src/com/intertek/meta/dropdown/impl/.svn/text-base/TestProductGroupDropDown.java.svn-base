package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.TestProduct;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;
import com.intertek.util.Constants;

public class TestProductGroupDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

    Field allField = new Field();
    allField.setName(Constants.All);
    allField.setValue(Constants.None);

    results.add(allField);

    List testProductGrps = jobService.getTestProductGroups();
    for(int i=0; i<testProductGrps.size(); i++)
    {
      TestProduct testProduct = (TestProduct)testProductGrps.get(i);

      Field field = new Field();
      String name = testProduct.getTestProductDescription();

      field.setName(name);
      field.setValue(testProduct.getTestProductId());
      results.add(field);
    }

    return results;
  }
}

