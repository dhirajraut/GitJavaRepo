package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.entity.RB;
import com.intertek.entity.VesselType;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;
import com.intertek.service.VesselTypeService;

public class VesselTypeByContractIdDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size == 0) return results;

    String contractId = (String)params.get(0);

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");

    Field field1 = new Field();
    field1.setName("All Vessel Types");
    field1.setValue("*");
    results.add(field1);

    List vesselTypAndRbKeyList = new ArrayList();

    List vesselSetDateList = vesselTypeService.getVesselSetDateListByContractId(contractId);

    for(int i=0; i<vesselSetDateList.size(); i++)
    {
      Object[] objects = (Object[])vesselSetDateList.get(i);

      Date beginDate = (Date)objects[0];
      Date endDate = (Date)objects[1];
      String vesselSet = (String)objects[2];

      if(contractId.equals(vesselSet))
      {
        List list = vesselTypeService.getVesselTypeAndRbKeyListByVesselSetAndDates(
          vesselSet,
          beginDate,
          endDate
        );
        vesselTypAndRbKeyList.addAll(list);
      }
      else
      {
        List list = vesselTypeService.getVesselTypeAndRbKeyListByVesselSet(vesselSet);
        vesselTypAndRbKeyList.addAll(list);
      }
    }

    Set addedVesselTypeSet = new HashSet();

    for(int i=0; i<vesselTypAndRbKeyList.size(); i++)
    {

      Object[] objects = (Object[])vesselTypAndRbKeyList.get(i);

      String vesselType = (String)objects[0];
      String rbKey = (String)objects[1];

      if(addedVesselTypeSet.contains(vesselType)) continue;
      addedVesselTypeSet.add(vesselType);

      //Get RB object for the each Vessel type
      RB rb = jobService.getRBByRBKeyAndContractCode(rbKey,contractId);
      if(rb == null)
      {
        rb = jobService.getRBByRBKeyAndContractCode(rbKey,"NONE");
      }

      String rbValue = vesselType;

      if(rb != null)
      {
        rbValue = rb.getRbValue();
      }

      Field field = new Field();

      field.setName(rb.getRbValue());
      field.setValue(vesselType);
      results.add(field);
    }

    return results;
  }
}

