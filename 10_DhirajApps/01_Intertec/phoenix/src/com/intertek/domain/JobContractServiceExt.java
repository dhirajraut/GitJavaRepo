package com.intertek.domain;

import com.intertek.entity.JobContractService;
import com.intertek.sort.OrderNumSortable;

public class JobContractServiceExt extends BaseSelects implements OrderNumSortable
{
  private JobContractService service;

  public JobContractService getService()
  {
    return service;
  }

  public void setService(JobContractService service)
  {
    this.service = service;
  }

  public Integer getSortOrderNum()
  {
    if(service != null) return service.getSortOrderNum();

    return null;
  }
}

