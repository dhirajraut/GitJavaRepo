package com.intertek.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.Contract;
import com.intertek.pagination.Pagination;

public class EditContractTest extends BaseEditContractTestSlate
{
  private Pagination priceBookTestPricePagination;
  private Pagination contractTestPricePagination;
  private List testPriceExtList;
  private List contractTestPriceExtList;

  private int pbTestPageNumber;
  private int contractTestPageNumber;

  private String activeId;
  private String activeType;

  public Pagination getPriceBookTestPricePagination()
  {
    return priceBookTestPricePagination;
  }

  public void setPriceBookTestPricePagination(Pagination priceBookTestPricePagination)
  {
    this.priceBookTestPricePagination = priceBookTestPricePagination;
  }


  public Pagination getContractTestPricePagination()
  {
    return contractTestPricePagination;
  }

  public void setContractTestPricePagination(Pagination contractTestPricePagination)
  {
    this.contractTestPricePagination = contractTestPricePagination;
  }

  public List getTestPriceExtList()
  {
    return testPriceExtList;
  }

  public void setTestPriceExtList(List testPriceExtList)
  {
    this.testPriceExtList = testPriceExtList;
  }

  public List getContractTestPriceExtList()
  {
    return contractTestPriceExtList;
  }

  public void setContractTestPriceExtList(List contractTestPriceExtList)
  {
    this.contractTestPriceExtList = contractTestPriceExtList;
  }

  public void setPbTestPageNumber(int pbTestPageNumber)
  {
    this.pbTestPageNumber = pbTestPageNumber;
  }

  public int getPbTestPageNumber()
  {
    return pbTestPageNumber;
  }

  public void setContractTestPageNumber(int contractTestPageNumber)
  {
    this.contractTestPageNumber = contractTestPageNumber;
  }

  public int getContractTestPageNumber()
  {
    return contractTestPageNumber;
  }

  public void setActiveId(String activeId)
  {
    this.activeId = activeId;
  }

  public String getActiveId()
  {
    return activeId;
  }

  public void setActiveType(String activeType)
  {
    this.activeType = activeType;
  }

  public String getActiveType()
  {
    return activeType;
  }
}

