package com.intertek.domain;

import java.util.ArrayList;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.JobVessel;

public class AddJobVessel
{
  @CascadeValidation

  private JobVessel jobVessel;

  private AddJobProd[] addJobProds;
  private AddJobContractProd[] addJobContractProds;

  private AddJobContractVesselServices addJobContractVesselServices;
  private List deletedJobContractVesselServices = new ArrayList();

  private String displayStatus = "SHOW";

  public AddJobProd[] getAddJobProds() {
    return addJobProds;
  }

  public void setAddJobProds(AddJobProd[] addJobProds) {
    this.addJobProds = addJobProds;
  }

  public AddJobContractProd[] getAddJobContractProds() {
    return addJobContractProds;
  }

  public void setAddJobContractProds(AddJobContractProd[] addJobContractProds) {
    this.addJobContractProds = addJobContractProds;
  }

  public JobVessel getJobVessel() {
    return jobVessel;
  }

  public void setJobVessel(JobVessel jobVessel) {
    this.jobVessel = jobVessel;
  }

  public void setAddJobContractVesselServices(AddJobContractVesselServices addJobContractVesselServices)
  {
    this.addJobContractVesselServices = addJobContractVesselServices;
  }

  public AddJobContractVesselServices getAddJobContractVesselServices()
  {
    return addJobContractVesselServices;
  }

  public List getDeletedJobContractVesselServices()
  {
    return deletedJobContractVesselServices;
  }

public String getDisplayStatus() {
	return displayStatus;
}

public void setDisplayStatus(String displayStatus) {
	this.displayStatus = displayStatus;
}
}
