package com.intertek.meta.dropdown.impl;

import java.util.*;

import com.intertek.entity.*;
import com.intertek.locator.*;
import com.intertek.service.*;
import com.intertek.meta.dropdown.*;
import com.intertek.util.*;

public class UomTableDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

    List uomTableList = inspectionRateService.getUomTableList();

    for(int i=0; i<uomTableList.size(); i++)
    {
      UomTable uomTable = (UomTable)uomTableList.get(i);

      Field field = new Field();
      field.setName(uomTable.getUom());
      field.setValue(uomTable.getIntData4().toString());
      results.add(field);
    }

    return results;
  }
}
