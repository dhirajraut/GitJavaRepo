package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.entity.HighLevelService;

public class HighLevelServiceExt
{
  private boolean checked;
  private boolean viewOnly;
  private HighLevelService highLevelService;

  private List serviceExtList;

  private boolean hide;

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

  public void setHighLevelService(HighLevelService highLevelService)
  {
    this.highLevelService = highLevelService;
  }

  public HighLevelService getHighLevelService()
  {
    return highLevelService;
  }

  public void setServiceExtList(List serviceExtList)
  {
    this.serviceExtList = serviceExtList;
  }

  public List getServiceExtList()
  {
    return serviceExtList;
  }

  public void setHide(boolean hide)
  {
    this.hide = hide;
  }

  public boolean getHide()
  {
    return hide;
  }
}
