package com.intertek.domain;

import java.util.List;
import java.util.ArrayList;

import com.intertek.entity.ContractTest;

public class ContractTestPriceExt
{
  private String testId;
  private ContractTest contractTest;

  private List testPrices = new ArrayList();

  public String getTestId()
  {
    return testId;
  }

  public void setTestId(String testId)
  {
    this.testId = testId;
  }

  public ContractTest getContractTest()
  {
    return contractTest;
  }

  public void setContractTest(ContractTest contractTest)
  {
    this.contractTest = contractTest;
  }

  public List getTestPrices()
  {
    return testPrices;
  }

  public void setTestPrices(List testPrices)
  {
    this.testPrices = testPrices;
  }
}
