package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.CfgJobCode;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CalculatorService;

public class ProductTypeDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    List jobCodes = calculatorService.getCfgJobCodes();

    for(int i=0; i<jobCodes.size(); i++)
    {
      CfgJobCode jobCode = (CfgJobCode)jobCodes.get(i);

      Field field = new Field();

      field.setName(jobCode.getJobCode());
      field.setValue(jobCode.getJobCode());
      results.add(field);
    }

    return results;
  }
}

