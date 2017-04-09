package com.intertek.domain;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.intertek.entity.Slate;

public class SlatePriceExt
{
  private String contractCode;
  private String priceBookId;
  private String priceType;

  private String slateId;
  private Slate slate;

  private List priceBookSlatePrices = new ArrayList();
  private List slatePrices = new ArrayList();

  private int rowsToAdd = 1;

  private String pageNumber;

  private Date fromDate;
  private Date toDate;

  public String getContractCode()
  {
    return contractCode;
  }

  public void setContractCode(String contractCode)
  {
    this.contractCode = contractCode;
  }

  public String getPriceBookId()
  {
    return priceBookId;
  }

  public void setPriceBookId(String priceBookId)
  {
    this.priceBookId = priceBookId;
  }

  public String getPriceType()
  {
    return priceType;
  }

  public void setPriceType(String priceType)
  {
    this.priceType = priceType;
  }

  public String getSlateId()
  {
    return slateId;
  }

  public void setSlateId(String slateId)
  {
    this.slateId = slateId;
  }

  public Slate getSlate()
  {
    return slate;
  }

  public void setSlate(Slate slate)
  {
    this.slate = slate;
  }

  public List getSlatePrices()
  {
    return slatePrices;
  }

  public void setSlatePrices(List slatePrices)
  {
    this.slatePrices = slatePrices;
  }

  public List getPriceBookSlatePrices()
  {
    return priceBookSlatePrices;
  }

  public void setPriceBookSlatePrices(List priceBookSlatePrices)
  {
    this.priceBookSlatePrices = priceBookSlatePrices;
  }

  public void setRowsToAdd(int rowsToAdd)
  {
    this.rowsToAdd = rowsToAdd;
  }

  public int getRowsToAdd()
  {
    return rowsToAdd;
  }

  public void setPageNumber(String pageNumber)
  {
    this.pageNumber = pageNumber;
  }

  public String getPageNumber()
  {
    return pageNumber;
  }


  public void setfromDate(Date fromDate)
  {
    this.fromDate = fromDate;
  }

  public Date getFromDate()
  {
    return fromDate;
  }


  public void setToDate(Date toDate)
  {
    this.toDate = toDate;
  }

  public Date getToDate()
  {
    return toDate;
  }
}
