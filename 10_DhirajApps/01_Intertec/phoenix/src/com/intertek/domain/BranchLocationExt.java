package com.intertek.domain;

import java.util.List;
import java.util.ArrayList;

import com.intertek.entity.BranchLocation;

public class BranchLocationExt
{
  private boolean checked;

  private BranchLocation branchLocation;
  private String branchDescription;

  public boolean getChecked()
  {
    return checked;
  }

  public void setChecked(boolean checked)
  {
    this.checked = checked;
  }

  public BranchLocation getBranchLocation()
  {
    return branchLocation;
  }

  public void setBranchLocation(BranchLocation branchLocation)
  {
    this.branchLocation = branchLocation;
  }

  public String getBranchDescription()
  {
    return branchDescription;
  }

  public void setBranchDescription(String branchDescription)
  {
    this.branchDescription = branchDescription;
  }
}
