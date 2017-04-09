package com.intertek.domain;

public class FloatSearchField extends SearchField
{
  private Float value;

  public FloatSearchField()
  {
  }

  public FloatSearchField(int operator)
  {
    super(operator);
  }

  public Float getValue()
  {
    return value;
  }

  public void setValue(Float value)
  {
    this.value = value;
  }
}
