package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.JobContractAttachType;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;

public class JobContractAttachTypeDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

    List types = jobService.getJobContractAttachTypes();
    
    for(int i=0; i<types.size(); i++)
    {
      JobContractAttachType type = (JobContractAttachType) types.get(i);

      Field field = new Field();
      String name = type.getType();

      field.setName(name);
      field.setValue(name);
      results.add(field);
    }

    return results;
  }
}
