package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.VesselTypeService;

public class VesselTypeSetNamesDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");

    List vtsList = vesselTypeService.getVesselTypeSetNameList();

    for(int i=0; i<vtsList.size(); i++)
    {
      String vts = (String)vtsList.get(i);

      Field field = new Field();

      field.setName(vts);
      field.setValue(vts);
      results.add(field);
    }

    return results;
  }
}
