package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.VesselTypeService;

public class VesselTypeSetDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size < 1) return results;

    VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");

    String vesselTypeSetName = (String)params.get(0);

    List vtsList = vesselTypeService.getVesselTypeSetNameList(vesselTypeSetName);

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
