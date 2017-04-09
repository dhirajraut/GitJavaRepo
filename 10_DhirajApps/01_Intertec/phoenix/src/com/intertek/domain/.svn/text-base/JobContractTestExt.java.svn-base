package com.intertek.domain;

import com.intertek.entity.JobContractTest;
import com.intertek.sort.OrderNumSortable;

public class JobContractTestExt implements OrderNumSortable
{
  private boolean selected;
  private JobContractTest test;

  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  public boolean getSelected()
  {
    return selected;
  }

  public JobContractTest getTest()
  {
    return test;
  }

  public void setTest(JobContractTest test)
  {
    this.test = test;
  }

  public Integer getSortOrderNum()
  {
    if(test != null) return test.getSortOrderNum();

    return null;
  }
}

