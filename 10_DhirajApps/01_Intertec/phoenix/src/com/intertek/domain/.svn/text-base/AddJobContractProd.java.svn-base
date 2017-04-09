package com.intertek.domain;

import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.calculator.CalculatorResult;
import com.intertek.entity.JobContractProd;
import com.intertek.sort.OrderNumSortable;

public class AddJobContractProd extends BaseSelects implements OrderNumSortable
{
  @CascadeValidation
  private JobContractProd jobContractProd;
  private AddJobContractProductServices addJobContractProductServices;

  private List jobContractTestExts;
  private List jobContractSlateExts;
  private List jobContractManualTestExts;

  private String displayStatus = "SHOW";

  private CalculatorResult calculatorResult = null;

  public JobContractProd getJobContractProd() {
    return jobContractProd;
  }
  public void setJobContractProd(JobContractProd jobContractProd) {
    this.jobContractProd = jobContractProd;
  }

  public void setAddJobContractProductServices(AddJobContractProductServices addJobContractProductServices)
  {
    this.addJobContractProductServices = addJobContractProductServices;
  }

  public AddJobContractProductServices getAddJobContractProductServices()
  {
    return addJobContractProductServices;
  }

  public List getJobContractSlateExts() {
    return jobContractSlateExts;
  }
  public void setJobContractSlateExts(List jobContractSlateExts) {
    this.jobContractSlateExts = jobContractSlateExts;
  }

  public List getJobContractTestExts() {
    return jobContractTestExts;
  }
  public void setJobContractTestExts(List jobContractTestExts) {
    this.jobContractTestExts = jobContractTestExts;
  }
  public List getJobContractManualTestExts() {
    return jobContractManualTestExts;
  }
  public void setJobContractManualTestExts(List jobContractManualTestExts) {
    this.jobContractManualTestExts = jobContractManualTestExts;
  }

  public Integer getSortOrderNum()
  {
    if(jobContractProd != null) return jobContractProd.getSortOrderNum();

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

  public void setCalculatorResult(CalculatorResult calculatorResult)
  {
    this.calculatorResult = calculatorResult;
  }

  public CalculatorResult getCalculatorResult()
  {
    return calculatorResult;
  }
}
