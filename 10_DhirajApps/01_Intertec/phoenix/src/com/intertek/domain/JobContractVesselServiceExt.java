package com.intertek.domain;

import com.intertek.entity.JobContractVesselService;
import com.intertek.sort.OrderNumSortable;

public class JobContractVesselServiceExt extends BaseSelects implements OrderNumSortable
{
  private JobContractVesselService service;

  public JobContractVesselService getService()
  {
    return service;
  }

  public void setService(JobContractVesselService service)
  {
    this.service = service;
  }

  public Integer getSortOrderNum()
  {
    if(service != null) return service.getSortOrderNum();

    return null;
  }
}

