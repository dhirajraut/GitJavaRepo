package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.JobEvent;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdQty;

public class AddJobProd
{
  @CascadeValidation

  private JobProd jobProd;
  private AddJobProdSample[] addJobProdSamples;
  private JobEvent[] jobEvents;
  private JobProdQty[] jobProdQtys;
  private String[] jobContractCodes;
  private AddJobContract[] applicableContracts;
  private AddJobContract[] notApplicableContracts;
  
  private String optionDesc;
  
  private String displayStatus = "SHOW";

  private String serviceLocation;
 
  
  
public String getServiceLocation() {
	return serviceLocation;
}
public void setServiceLocation(String serviceLocation) {
	this.serviceLocation = serviceLocation;
}
public JobEvent[] getJobEvents() {
	return jobEvents;
}
public void setJobEvents(JobEvent[] jobEvents) {
	this.jobEvents = jobEvents;
}
public JobProd getJobProd() {
    return jobProd;
  }
  public void setJobProd(JobProd jobProd) {
    this.jobProd = jobProd;
  }

  public JobProdQty[] getJobProdQtys() {
    return jobProdQtys;
  }
  public void setJobProdQtys(JobProdQty[] jobProdQtys) {
    this.jobProdQtys = jobProdQtys;
  }
  public AddJobProdSample[] getAddJobProdSamples() {
    return addJobProdSamples;
  }
  public void setAddJobProdSamples(AddJobProdSample[] addJobProdSamples) {
    this.addJobProdSamples = addJobProdSamples;
  }

  public String[] getJobContractCodes() {
    return jobContractCodes;
  }
  public void setJobContractCodes(String[] jobContractCodes) {
    this.jobContractCodes = jobContractCodes;
  }

public String getDisplayStatus() {
	return displayStatus;
}
public void setDisplayStatus(String displayStatus) {
	this.displayStatus = displayStatus;
}

public String getOptionDesc() {
	return optionDesc;
}
public void setOptionDesc(String optionDesc) {
	this.optionDesc = optionDesc;
}
public AddJobContract[] getApplicableContracts() {
	return applicableContracts;
}
public void setApplicableContracts(AddJobContract[] applicableContracts) {
	this.applicableContracts = applicableContracts;
}
public AddJobContract[] getNotApplicableContracts() {
	return notApplicableContracts;
}
public void setNotApplicableContracts(AddJobContract[] notApplicableContracts) {
	this.notApplicableContracts = notApplicableContracts;
}

}
