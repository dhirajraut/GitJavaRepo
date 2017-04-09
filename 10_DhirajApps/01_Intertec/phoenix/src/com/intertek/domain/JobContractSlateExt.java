package com.intertek.domain;

import com.intertek.entity.JobContractSlate;
import com.intertek.sort.OrderNumSortable;

public class JobContractSlateExt implements OrderNumSortable
{
  private boolean selected;
  private JobContractSlate slate;

  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  public boolean getSelected()
  {
    return selected;
  }

  public JobContractSlate getSlate()
  {
    return slate;
  }

  public void setSlate(JobContractSlate slate)
  {
    this.slate = slate;
  }

  public Integer getSortOrderNum()
  {
    if(slate != null) return slate.getSortOrderNum();

    return null;
  }
}

