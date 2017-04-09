package com.intertek.meta.dropdown.impl;

import java.util.*;

import com.intertek.entity.*;
import com.intertek.locator.*;
import com.intertek.service.*;
import com.intertek.meta.dropdown.*;
import com.intertek.util.*;

public class PriceBookZoneIdDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");

    List zoneIds = zoneService.getPriceBookZoneIds();

    for(int i=0; i<zoneIds.size(); i++)
    {
      String zoneId = (String)zoneIds.get(i);

      Field field = new Field();
      field.setName(zoneId);
      field.setValue(zoneId);
      results.add(field);
    }

    return results;
  }
}
