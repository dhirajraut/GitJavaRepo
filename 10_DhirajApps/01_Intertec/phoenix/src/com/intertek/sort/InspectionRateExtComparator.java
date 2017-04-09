package com.intertek.sort;

import java.util.Comparator;
import java.util.Map;

import com.intertek.domain.InspectionRateExt;
import com.intertek.meta.dropdown.Field;

/**
 * A comparator used to compare 2 objects of type InspectionRate
 **/
public class InspectionRateExtComparator implements Comparator
{
  private Map productGroupDropDownMap;

  public InspectionRateExtComparator(Map productGroupDropDownMap)
  {
    this.productGroupDropDownMap = productGroupDropDownMap;
  }

  /**
   * Compares its two InspectionRate arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   * @param o1 - the first InspectionRate object to be compared..
   * @param o2 - the second InspectionRate object to be compared..
   * @return - a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   **/
  public int compare(Object o1, Object o2)
  {
    InspectionRateExt p1 = (InspectionRateExt)o1;
    InspectionRateExt p2 = (InspectionRateExt)o2;

    int result = compareProductGroup(p1, p2);

    if(result == 0)
    {
      result = compareZone(p1, p2);

      if(result == 0)
      {
        result = compareMinRange(p1, p2);
      }
    }

    return result;
  }

  private int compareProductGroup(InspectionRateExt p1, InspectionRateExt p2)
  {
    String pg1 = p1.getInspectionRate().getInspectionRateId().getGroupId();
    String pg2 = p2.getInspectionRate().getInspectionRateId().getGroupId();

    if(productGroupDropDownMap != null)
    {
      Field field1 = (Field)productGroupDropDownMap.get(pg1);
      Field field2 = (Field)productGroupDropDownMap.get(pg2);

      if(field1 != null) pg1 = field1.getName();
      if(field2 != null) pg2 = field2.getName();
    }

    int result = -2;

    if(pg1 == null)
    {
      if(pg2 == null) result = 0;
      else result = 1;
    }
    else if(pg2 == null)
    {
      result = -1;
    }

    if(result == -2) result = pg1.compareTo(pg2);

    return result;
  }

  private int compareZone(InspectionRateExt p1, InspectionRateExt p2)
  {
    String location1 = p1.getInspectionRate().getInspectionRateId().getLocation();
    String location2 = p2.getInspectionRate().getInspectionRateId().getLocation();

    int result = -2;

    if(location1 == null)
    {
      if(location2 == null) result = 0;
      else result = 1;
    }
    else if(location2 == null)
    {
      result = -1;
    }

    if(result == -2) result = location1.compareTo(location2);

    return result;
  }

  private int compareMinRange(InspectionRateExt p1, InspectionRateExt p2)
  {
    Integer minRange1 = p1.getInspectionRate().getInspectionRateId().getIntData2();
    Integer minRange2 = p2.getInspectionRate().getInspectionRateId().getIntData2();

    int result = -2;

    if(minRange1 == null)
    {
      if(minRange2 == null) result = 0;
      else result = 1;
    }
    else if(minRange2 == null)
    {
      result = -1;
    }

    if(result == -2) result = minRange1.compareTo(minRange2);

    return result;
  }
}


