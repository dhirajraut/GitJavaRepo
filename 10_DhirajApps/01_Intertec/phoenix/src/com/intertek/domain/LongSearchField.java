package com.intertek.domain;

public class LongSearchField extends SearchField
{
  private Long value;

  public LongSearchField()
  {
  }

  public LongSearchField(int operator)
  {
    super(operator);
  }

  public Long getValue()
  {
    return value;
  }

  public void setValue(Long value)
  {
    this.value = value;
  }
}
