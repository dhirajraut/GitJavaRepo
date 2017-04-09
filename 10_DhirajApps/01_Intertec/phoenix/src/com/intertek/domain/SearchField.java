package com.intertek.domain;

import com.intertek.util.Constants;

public abstract class SearchField
{
  private int operator;

  public SearchField()
  {
    operator = Constants.EQUALS;
  }

  public SearchField(int operator)
  {
    this.operator = operator;
  }

  public int getOperator()
  {
    return operator;
  }

  public void setOperator(int operator)
  {
    this.operator = operator;
  }
}
