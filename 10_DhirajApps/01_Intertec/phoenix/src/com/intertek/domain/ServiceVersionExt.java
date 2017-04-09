package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.entity.ServiceVersion;
import com.intertek.util.Constants;

public class ServiceVersionExt
{
  private ServiceVersion serviceVersion;
  private ServiceLevel serviceLevel;

  private List ceExtListForContract;
  private List ceExtListForContractBuffer = new ArrayList();
  private List deletedCeExtListForContract = new ArrayList();

  private ContractExpressionExt allZoneCeExtForContract;

  private List contractServiceRateExtList;
  private List contractServiceRateExtListBuffer = new ArrayList();
  private List deletedContractServiceRateExtList = new ArrayList();

  private boolean priceBookCheck = true;
  private Double discountPct;
  private boolean hide;

  private boolean enableApplyAll = true;
  private String rateApplied;
  private String applyToThisLevel;

  private String selectedZoneId;

  //private List pbControlList;
  private List contractControlExtList;
  private List contractControlExtListBuffer = new ArrayList();

  private boolean enableVessel;
  private boolean enableProductGroup;

  private boolean vesselChecked;
  private boolean productGroupChecked;

  private ServiceExt serviceExtForContract;

  private Date allBeginDate;
  private Date allEndDate;

  private boolean checkAll;

  private int rowsToAdd = 1;

  public void setServiceVersion(ServiceVersion serviceVersion)
  {
    this.serviceVersion = serviceVersion;
  }

  public ServiceVersion getServiceVersion()
  {
    return serviceVersion;
  }

  public void setServiceLevel(ServiceLevel serviceLevel)
  {
    this.serviceLevel = serviceLevel;
  }

  public ServiceLevel getServiceLevel()
  {
    return serviceLevel;
  }

  public void setCeExtListForContract(List ceExtListForContract)
  {
    this.ceExtListForContract = ceExtListForContract;
  }

  public List getCeExtListForContract()
  {
    return ceExtListForContract;
  }

  public void setCeExtListForContractBuffer(List ceExtListForContractBuffer)
  {
    this.ceExtListForContractBuffer = ceExtListForContractBuffer;
  }

  public List getCeExtListForContractBuffer()
  {
    return ceExtListForContractBuffer;
  }

  public List getDeletedCeExtListForContract()
  {
    return deletedCeExtListForContract;
  }

  public void setAllZoneCeExtForContract(ContractExpressionExt allZoneCeExtForContract)
  {
    this.allZoneCeExtForContract = allZoneCeExtForContract;
  }

  public ContractExpressionExt getAllZoneCeExtForContract()
  {
    return allZoneCeExtForContract;
  }

  public void setContractServiceRateExtList(List contractServiceRateExtList)
  {
    this.contractServiceRateExtList = contractServiceRateExtList;
  }

  public List getContractServiceRateExtList()
  {
    return contractServiceRateExtList;
  }

  public void setContractServiceRateExtListBuffer(List contractServiceRateExtListBuffer)
  {
    this.contractServiceRateExtListBuffer = contractServiceRateExtListBuffer;
  }

  public List getContractServiceRateExtListBuffer()
  {
    return contractServiceRateExtListBuffer;
  }

  public List getDeletedContractServiceRateExtList()
  {
    return deletedContractServiceRateExtList;
  }

  public void setPriceBookCheck(boolean priceBookCheck)
  {
    this.priceBookCheck = priceBookCheck;
  }

  public boolean getPriceBookCheck()
  {
    return priceBookCheck;
  }

  public void setDiscountPct(Double discountPct)
  {
    this.discountPct = discountPct;
  }

  public Double getDiscountPct()
  {
    return discountPct;
  }

  public void setHide(boolean hide)
  {
    this.hide = hide;
  }

  public boolean getHide()
  {
    return hide;
  }

  public void setEnableApplyAll(boolean enableApplyAll)
  {
    this.enableApplyAll = enableApplyAll;
  }

  public boolean getEnableApplyAll()
  {
    return enableApplyAll;
  }

  public void setRateApplied(String rateApplied)
  {
    this.rateApplied = rateApplied;
  }

  public String getRateApplied()
  {
    return rateApplied;
  }

  public void setApplyToThisLevel(String applyToThisLevel)
  {
    this.applyToThisLevel = applyToThisLevel;
  }

  public String getApplyToThisLevel()
  {
    return applyToThisLevel;
  }

  public void setSelectedZoneId(String selectedZoneId)
  {
    this.selectedZoneId = selectedZoneId;
  }

  public String getSelectedZoneId()
  {
    return selectedZoneId;
  }

  //public void setPbControlList(List pbControlList)
  //{
  //  this.pbControlList = pbControlList;
  //}

  //public List getPbControlList()
  //{
  //  return pbControlList;
  //}

  public void setContractControlExtList(List contractControlExtList)
  {
    this.contractControlExtList = contractControlExtList;
  }

  public List getContractControlExtList()
  {
    return contractControlExtList;
  }

  public void setContractControlExtListBuffer(List contractControlExtListBuffer)
  {
    this.contractControlExtListBuffer = contractControlExtListBuffer;
  }

  public List getContractControlExtListBuffer()
  {
    return contractControlExtListBuffer;
  }

  public void setEnableVessel(boolean enableVessel)
  {
    this.enableVessel = enableVessel;
  }

  public boolean getEnableVessel()
  {
    return enableVessel;
  }

  public void setEnableProductGroup(boolean enableProductGroup)
  {
    this.enableProductGroup = enableProductGroup;
  }

  public boolean getEnableProductGroup()
  {
    return enableProductGroup;
  }

  public void setVesselChecked(boolean vesselChecked)
  {
    this.vesselChecked = vesselChecked;
  }

  public boolean getVesselChecked()
  {
    return vesselChecked;
  }

  public void setProductGroupChecked(boolean productGroupChecked)
  {
    this.productGroupChecked = productGroupChecked;
  }

  public boolean getProductGroupChecked()
  {
    return productGroupChecked;
  }

  public void setServiceExtForContract(ServiceExt serviceExtForContract)
  {
    this.serviceExtForContract = serviceExtForContract;
  }

  public ServiceExt getServiceExtForContract()
  {
    return serviceExtForContract;
  }

  public Date getAllBeginDate()
  {
    return allBeginDate;
  }

  public void setAllBeginDate(Date allBeginDate)
  {
    this.allBeginDate = allBeginDate;
  }

  public Date getAllEndDate()
  {
    return allEndDate;
  }

  public void setAllEndDate(Date allEndDate)
  {
    this.allEndDate = allEndDate;
  }

  public boolean getCheckAll()
  {
    return checkAll;
  }

  public void setCheckAll(boolean checkAll)
  {
    this.checkAll = checkAll;
  }

  public void setRowsToAdd(int rowsToAdd)
  {
    this.rowsToAdd = rowsToAdd;
  }

  public int getRowsToAdd()
  {
    return rowsToAdd;
  }
}
