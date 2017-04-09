package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import com.intertek.calculator.ContractExpressionExt;

public class ServiceRates
{
  private EditServiceRate editServiceRate;

  private ServiceLevel serviceLevel;

  private ContractExpressionExt ceExt;

  private List serviceRateExpressionIdList;
  private List serviceLevelList;

  private List priceBookServiceRateList;
  private List contractSpecificServiceRateList;

  private Date earliestContractBeginDate;

  public void setEditServiceRate(EditServiceRate editServiceRate)
  {
    this.editServiceRate = editServiceRate;
  }

  public EditServiceRate getEditServiceRate()
  {
    return editServiceRate;
  }

  public void setServiceLevel(ServiceLevel serviceLevel)
  {
    this.serviceLevel = serviceLevel;
  }

  public ServiceLevel getServiceLevel()
  {
    return serviceLevel;
  }

  public void setServiceRateExpressionIdList(List serviceRateExpressionIdList)
  {
    this.serviceRateExpressionIdList = serviceRateExpressionIdList;
  }

  public List getServiceRateExpressionIdList()
  {
    return serviceRateExpressionIdList;
  }

  public void setServiceLevelList(List serviceLevelList)
  {
    this.serviceLevelList = serviceLevelList;
  }

  public List getServiceLevelList()
  {
    return serviceLevelList;
  }

  public void setPriceBookServiceRateList(List priceBookServiceRateList)
  {
    this.priceBookServiceRateList = priceBookServiceRateList;
  }

  public List getPriceBookServiceRateList()
  {
    return priceBookServiceRateList;
  }

  public void setContractSpecificServiceRateList(List contractSpecificServiceRateList)
  {
    this.contractSpecificServiceRateList = contractSpecificServiceRateList;
  }

  public List getContractSpecificServiceRateList()
  {
    return contractSpecificServiceRateList;
  }

  public void setEarliestContractBeginDate(Date earliestContractBeginDate)
  {
    this.earliestContractBeginDate = earliestContractBeginDate;
  }

  public Date getEarliestContractBeginDate()
  {
    return earliestContractBeginDate;
  }
}
