package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.entity.ServiceRate;

public class ServiceRateExt
{
  private ServiceRate oldServiceRate;
  private ServiceRate serviceRate;
  private boolean newFlag;
  private boolean checked;

  public void setOldServiceRate(ServiceRate oldServiceRate)
  {
    this.oldServiceRate = oldServiceRate;
  }

  public ServiceRate getOldServiceRate()
  {
    return oldServiceRate;
  }

  public void setServiceRate(ServiceRate serviceRate)
  {
    this.serviceRate = serviceRate;
  }

  public ServiceRate getServiceRate()
  {
    return serviceRate;
  }

  public void setNewFlag(boolean newFlag)
  {
    this.newFlag = newFlag;
  }

  public boolean getNewFlag()
  {
    return newFlag;
  }

  public void setChecked(boolean checked)
  {
    this.checked = checked;
  }

  public boolean getChecked()
  {
    return checked;
  }
}
