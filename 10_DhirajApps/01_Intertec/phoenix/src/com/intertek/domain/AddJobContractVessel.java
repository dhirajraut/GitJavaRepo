package com.intertek.domain;

import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.calculator.ControlExt;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.VesselType;
import com.intertek.sort.OrderNumSortable;

public class AddJobContractVessel extends BaseSelects implements OrderNumSortable
{
  @CascadeValidation
  private JobContractVessel jobContractVessel;

  private String contractCode;
  private String index;
  private String selectedVesselIndex;

  private AddJobContractProd[] addJobContractProds;
  private AddJobContractVesselServices addJobContractVesselServices;

  private List vesselTypes;
  private ControlExt[] controlExts;
  private VesselType selectedVesselType;

  private String displayStatus = "SHOW";

  private List productGroups;

  public String getContractCode()
  {
    return contractCode;
  }

  public void setContractCode(String contractCode)
  {
    this.contractCode = contractCode;
  }

  public String getIndex()
  {
    return index;
  }

  public void setIndex(String index)
  {
    this.index = index;
  }

  public void setSelectedVesselIndex(String selectedVesselIndex)
  {
    this.selectedVesselIndex = selectedVesselIndex;
  }

  public String getSelectedVesselIndex()
  {
    return selectedVesselIndex;
  }

  public JobContractVessel getJobContractVessel() {
    return jobContractVessel;
  }
  public void setJobContractVessel(JobContractVessel jobContractVessel) {
    this.jobContractVessel = jobContractVessel;
  }

  public AddJobContractProd[] getAddJobContractProds() {
    return addJobContractProds;
  }

  public void setAddJobContractProds(AddJobContractProd[] addJobContractProds) {
    this.addJobContractProds = addJobContractProds;
  }

  public void setAddJobContractVesselServices(AddJobContractVesselServices addJobContractVesselServices)
  {
    this.addJobContractVesselServices = addJobContractVesselServices;
  }

  public AddJobContractVesselServices getAddJobContractVesselServices()
  {
    return addJobContractVesselServices;
  }

  public List getVesselTypes()
  {
    return vesselTypes;
  }

  public void setVesselTypes(List vesselTypes)
  {
    this.vesselTypes = vesselTypes;
  }

  public ControlExt[] getControlExts()
  {
    return controlExts;
  }

  public void setControlExts(ControlExt[] controlExts)
  {
    this.controlExts = controlExts;
  }

  public VesselType getSelectedVesselType()
  {
    return selectedVesselType;
  }

  public void setSelectedVesselType(VesselType selectedVesselType)
  {
    this.selectedVesselType = selectedVesselType;
  }

  public Integer getSortOrderNum()
  {
    if(jobContractVessel != null) return jobContractVessel.getSortOrderNum();

    return null;
  }

  public void setDisplayStatus(String displayStatus)
  {
    this.displayStatus = displayStatus;
  }

  public String getDisplayStatus()
  {
    return displayStatus;
  }

  public List getProductGroups()
  {
    return productGroups;
  }

  public void setProductGroups(List productGroups)
  {
    this.productGroups = productGroups;
  }
}
