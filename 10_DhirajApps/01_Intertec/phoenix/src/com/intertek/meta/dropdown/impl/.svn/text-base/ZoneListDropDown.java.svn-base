package com.intertek.meta.dropdown.impl;

import java.util.*;

import com.intertek.entity.*;
import com.intertek.locator.*;
import com.intertek.service.*;
import com.intertek.meta.dropdown.*;
import com.intertek.util.*;

public class ZoneListDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");

    int size = params != null ? params.size() : 0;

    if(size < 2) return results;

    String contractCode = (String)params.get(0);
    String priceBookId = (String)params.get(1);
    boolean includeAll = true;
    if(size > 2)
    {
      String includeAllStr = (String)params.get(2);
      if(!Constants.INCLUDE_ALL.equals(includeAllStr)) includeAll = false;
    }

    List zoneIdList = zoneService.getZoneIdsForContractWithDate(contractCode, priceBookId, new Date());

    if(includeAll)
    {
      Field field = new Field();
      field.setName("All Zones");
      field.setValue("*");
      results.add(field);
    }

    Set set = new HashSet();
    set.add("*");

    for(int i=0; i<zoneIdList.size(); i++)
    {
      String zoneId = (String)zoneIdList.get(i);

      if(set.contains(zoneId)) continue;

      Field field = new Field();
      field.setName(zoneId);
      field.setValue(zoneId);
      results.add(field);

      set.add(zoneId);
    }

    return results;
  }
}
