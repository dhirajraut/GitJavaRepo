package com.intertek.sort;

import java.util.Comparator;

/**
 * A comparator used to compare 2 objects of type OrderNumSortable
 **/
public class SortOrderNumComparator implements Comparator
{
  /**
   * Compares its two OrderNumSortable arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   * @param o1 - the first OrderNumSortable object to be compared..
   * @param o2 - the second OrderNumSortable object to be compared..
   * @return - a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   **/
  public int compare(Object o1, Object o2)
  {
    OrderNumSortable p1 = (OrderNumSortable)o1;
    OrderNumSortable p2 = (OrderNumSortable)o2;

    Integer sortOrderNum1 = p1.getSortOrderNum();
    Integer sortOrderNum2 = p2.getSortOrderNum();

    if(sortOrderNum1 == null)
    {
      if(sortOrderNum2 == null) return 0;
      else return 1;
    }
    else if(sortOrderNum2 == null)
    {
      return -1;
    }

    return sortOrderNum1.compareTo(sortOrderNum2);
  }
}


