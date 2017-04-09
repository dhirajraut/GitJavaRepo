package com.intertek.domain;

import com.intertek.entity.JobContractManualTest;
import com.intertek.sort.OrderNumSortable;

public class JobContractManualTestExt implements OrderNumSortable
{
  private boolean selected;
  private JobContractManualTest manualTest;

  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  public boolean getSelected()
  {
    return selected;
  }

  public JobContractManualTest getManualTest()
  {
    return manualTest;
  }

  public void setManualTest(JobContractManualTest manualTest)
  {
    this.manualTest = manualTest;
  }

  public Integer getSortOrderNum()
  {
    if(manualTest != null) return manualTest.getSortOrderNum();

    return null;
  }
}

