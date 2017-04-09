package com.intertek.domain;

import com.intertek.entity.JobContractProductService;
import com.intertek.sort.OrderNumSortable;

public class JobContractProductServiceExt extends BaseSelects implements OrderNumSortable
{
  private JobContractProductService service;

  public JobContractProductService getService()
  {
    return service;
  }

  public void setService(JobContractProductService service)
  {
    this.service = service;
  }

  public Integer getSortOrderNum()
  {
    if(service != null) return service.getSortOrderNum();

    return null;
  }
}

