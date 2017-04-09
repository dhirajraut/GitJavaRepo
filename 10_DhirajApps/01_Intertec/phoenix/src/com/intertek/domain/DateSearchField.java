package com.intertek.domain;

import java.util.Date;

public class DateSearchField extends SearchField
{
  private Date value;

  public DateSearchField()
  {
  }

  public DateSearchField(int operator)
  {
    super(operator);
  }

  public Date getValue()
  {
    return value;
  }

  public void setValue(Date value)
  {
    this.value = value;
  }
}
