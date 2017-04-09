package com.intertek.domain;

import java.util.Date;

public class BaseEditContractTestSlate extends BaseEditContract
{
  private Date pbFromDate;
  private Date pbToDate;

  private Date contractFromDate;
  private Date contractToDate;


  public void setPbFromDate(Date pbFromDate)
  {
    this.pbFromDate = pbFromDate;
  }

  public Date getPbFromDate()
  {
    return pbFromDate;
  }

  public void setPbToDate(Date pbToDate)
  {
    this.pbToDate = pbToDate;
  }

  public Date getPbToDate()
  {
    return pbToDate;
  }


  public void setContractFromDate(Date contractFromDate)
  {
    this.contractFromDate = contractFromDate;
  }

  public Date getContractFromDate()
  {
    return contractFromDate;
  }


  public void setContractToDate(Date contractToDate)
  {
    this.contractToDate = contractToDate;
  }

  public Date getContractToDate()
  {
    return contractToDate;
  }
}
