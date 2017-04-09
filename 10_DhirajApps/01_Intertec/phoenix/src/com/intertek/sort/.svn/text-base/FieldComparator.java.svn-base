package com.intertek.sort;

import java.util.Comparator;

import com.intertek.meta.dropdown.Field;

/**
 * A comparator used to compare 2 objects of type Field
 **/
public class FieldComparator implements Comparator
{
  /**
   * Compares its two Field arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   * @param o1 - the first Field object to be compared..
   * @param o2 - the second Field object to be compared..
   * @return - a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
   *
   **/
  public int compare(Object o1, Object o2)
  {
    Field p1 = (Field)o1;
    Field p2 = (Field)o2;

    String name1 = p1.getName();
    String name2 = p2.getName();

    if(name1 == null)
    {
      if(name2 == null) return 0;
      else return 1;
    }
    else if(name2 == null)
    {
      return -1;
    }

    return name1.compareTo(name2);
  }
}


