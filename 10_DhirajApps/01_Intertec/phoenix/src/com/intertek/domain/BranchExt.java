package com.intertek.domain;

import java.util.List;
import java.util.ArrayList;

import com.intertek.entity.Branch;

public class BranchExt
{
  private boolean checked;
  private boolean viewOnly;

  private Branch branch;

  public boolean getChecked()
  {
    return checked;
  }

  public void setChecked(boolean checked)
  {
    this.checked = checked;
  }

  public boolean getViewOnly()
  {
    return viewOnly;
  }

  public void setViewOnly(boolean viewOnly)
  {
    this.viewOnly = viewOnly;
  }

  public Branch getBranch()
  {
    return branch;
  }

  public void setBranch(Branch branch)
  {
    this.branch = branch;
  }
}
