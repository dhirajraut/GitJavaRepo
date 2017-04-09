package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.RB;
import com.intertek.entity.VesselType;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;

public class VesselTypeDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    //if(size == 0) return results;

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

    List vessels = jobService.getVesselTypes();
    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    for(int i=0; i<vessels.size(); i++)
    {

      VesselType vesselType =  (VesselType) vessels.get(i);

  //Get RB object for the each Vessel type
      RB rb = jobService.getRBByRBKeyAndContractCode(vesselType.getRbKey(),"NONE");

      if(rb != null)
      {

          Field field = new Field();

       field.setName(rb.getRbValue());
       field.setValue(vesselType.getVesselTypeId().getVesselType());
          results.add(field);
      }
    }

    return results;
  }
}

