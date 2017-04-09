package com.intertek.domain;

import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.sort.OrderNumSortable;

public class JobContractProductInspectionResultExt implements OrderNumSortable
{
  private boolean selected;
  private JobContractProductInspectionResult result;

  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  public boolean getSelected()
  {
    return selected;
  }

  public JobContractProductInspectionResult getResult()
  {
    return result;
  }

  public void setResult(JobContractProductInspectionResult result)
  {
    this.result = result;
  }

  public Integer getSortOrderNum()
  {
    if(result != null) return result.getSortOrderNum();

    return null;
  }
}

