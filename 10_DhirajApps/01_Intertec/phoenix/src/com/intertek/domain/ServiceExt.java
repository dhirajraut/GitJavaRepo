package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.entity.Service;

public class ServiceExt
{
  private Service service;
  private Service serviceForContract;

  private List contractExpressionExtList;
  private String description;
  private boolean hide;

  public void setService(Service service)
  {
    this.service = service;
  }

  public Service getService()
  {
    return service;
  }

  public void setServiceForContract(Service serviceForContract)
  {
    this.serviceForContract = serviceForContract;
  }

  public Service getServiceForContract()
  {
    return serviceForContract;
  }

  public void setContractExpressionExtList(List contractExpressionExtList)
  {
    this.contractExpressionExtList = contractExpressionExtList;
  }

  public List getContractExpressionExtList()
  {
    return contractExpressionExtList;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getDescription()
  {
    return description;
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
