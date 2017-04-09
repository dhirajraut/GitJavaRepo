package com.intertek.domain;

import java.util.List;
import java.util.ArrayList;

import com.intertek.entity.ContractTest;

public class ContractTestExt
{
  private String contractCode;

  private ContractTest contractTest;

  private String pageNumber;

  public String getContractCode()
  {
    return contractCode;
  }

  public void setContractCode(String contractCode)
  {
    this.contractCode = contractCode;
  }

  public ContractTest getContractTest()
  {
    return contractTest;
  }

  public void setContractTest(ContractTest contractTest)
  {
    this.contractTest = contractTest;
  }

  public void setPageNumber(String pageNumber)
  {
    this.pageNumber = pageNumber;
  }

  public String getPageNumber()
  {
    return pageNumber;
  }
}
