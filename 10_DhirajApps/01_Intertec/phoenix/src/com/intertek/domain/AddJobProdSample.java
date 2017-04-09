package com.intertek.domain;

import java.util.ArrayList;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.JobManualTest;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobSlate;
import com.intertek.entity.JobTest;

public class AddJobProdSample {
   @CascadeValidation

   private JobProdSample jobProdSample;
   private JobTest[] jobTests;
   private JobSlate[] jobSlates;
   private JobManualTest[] jobManualTests;

  private List deletedJobTests = new ArrayList();
  private List deletedJobSlates = new ArrayList();
  private List deletedJobManualTests = new ArrayList();
  
  private String displayStatus = "SHOW";
  private double massQty;
  private String massOT;
  
  public String getMassOT() {
	return massOT;
}
public void setMassOT(String massOT) {
	this.massOT = massOT;
}
public double getMassQty() {
	return massQty;
}
public void setMassQty(double massQty) {
	this.massQty = massQty;
}
public JobProdSample getJobProdSample() {
    return jobProdSample;
  }
  public void setJobProdSample(JobProdSample jobProdSample) {
    this.jobProdSample = jobProdSample;
  }

  public JobSlate[] getJobSlates() {
    return jobSlates;
  }
  public void setJobSlates(JobSlate[] jobSlates) {
    this.jobSlates = jobSlates;
  }
  public JobTest[] getJobTests() {
    return jobTests;
  }
  public void setJobTests(JobTest[] jobTests) {
    this.jobTests = jobTests;
  }
  public JobManualTest[] getJobManualTests() {
    return jobManualTests;
  }
  public void setJobManualTests(JobManualTest[] jobManualTests) {
    this.jobManualTests = jobManualTests;
  }

  public List getDeletedJobTests()
  {
    return deletedJobTests;
  }

  public List getDeletedJobSlates()
  {
    return deletedJobSlates;
  }

  public List getDeletedJobManualTests()
  {
    return deletedJobManualTests;
  }
public String getDisplayStatus() {
	return displayStatus;
}
public void setDisplayStatus(String displayStatus) {
	this.displayStatus = displayStatus;
}
}
