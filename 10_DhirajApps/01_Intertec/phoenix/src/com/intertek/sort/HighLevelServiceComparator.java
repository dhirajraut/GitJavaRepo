package com.intertek.sort;

import java.util.Comparator;

import com.intertek.entity.HighLevelService;

/**
 * A comparator used to compare 2 objects of type HighLevelService
 **/
public class HighLevelServiceComparator implements Comparator
{
  /**
   * Compares its two HighLevelService arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   * @param o1 - the first HighLevelService object to be compared..
   * @param o2 - the second HighLevelService object to be compared..
   * @return - a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   **/
  public int compare(Object o1, Object o2)
  {
    HighLevelService p1 = (HighLevelService)o1;
    HighLevelService p2 = (HighLevelService)o2;

    String description1 = p1.getServiceDescription();
    String description2 = p2.getServiceDescription();

    if(description1 == null)
    {
      if(description2 == null) return 0;
      else return 1;
    }
    else if(description2 == null)
    {
      return -1;
    }

    return description1.compareTo(description2);
  }
}


