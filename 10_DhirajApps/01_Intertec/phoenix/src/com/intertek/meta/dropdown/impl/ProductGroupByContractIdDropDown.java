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
import com.intertek.service.ProductGroupService;

public class ProductGroupByContractIdDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size == 0) return results;

    String contractId = (String)params.get(0);

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");

    Field field1 = new Field();
    field1.setName("All Product Groups");
    field1.setValue("*");
    results.add(field1);

    List groupIdAndRbKeyList = new ArrayList();

    List productGroupSetDateList = productGroupService.getProductGroupSetDateListByContractId(contractId);

    for(int i=0; i<productGroupSetDateList.size(); i++)
    {
      Object[] objects = (Object[])productGroupSetDateList.get(i);

      Date beginDate = (Date)objects[0];
      Date endDate = (Date)objects[1];
      String proudctGroupSet = (String)objects[2];

      if(contractId.equals(proudctGroupSet))
      {
        List list = productGroupService.getGroupIdAndRbKeyListByProductGroupSetAndDates(
          proudctGroupSet,
          beginDate,
          endDate
        );
        groupIdAndRbKeyList.addAll(list);
      }
      else
      {
        List list = productGroupService.getGroupIdAndRbKeyListByProductGroupSet(proudctGroupSet);
        groupIdAndRbKeyList.addAll(list);
      }
    }

    Set addedGroupIdSet = new HashSet();

    for(int i=0; i<groupIdAndRbKeyList.size(); i++)
    {

      Object[] objects = (Object[])groupIdAndRbKeyList.get(i);

      String groupId = (String)objects[0];
      String rbKey = (String)objects[1];

      if(addedGroupIdSet.contains(groupId)) continue;
      addedGroupIdSet.add(groupId);


      //Get RB object for the each Product Group
      RB rb = jobService.getRBByRBKeyAndContractCode(rbKey,contractId);
      if(rb == null)
      {
        rb = jobService.getRBByRBKeyAndContractCode(rbKey,"NONE");
      }

      String rbValue = groupId;

      if(rb != null)
      {
        rbValue = rb.getRbValue();
      }

      Field field = new Field();

      field.setName(rbValue);
      field.setValue(groupId);
      results.add(field);
    }

    return results;
  }
}

