package com.intertek.domain;

public class DoubleSearchField extends SearchField
{
  private Double value;

  public DoubleSearchField()
  {
  }

  public DoubleSearchField(int operator)
  {
    super(operator);
  }

  public Double getValue()
  {
    return value;
  }

  public void setValue(Double value)
  {
    this.value = value;
  }
}
