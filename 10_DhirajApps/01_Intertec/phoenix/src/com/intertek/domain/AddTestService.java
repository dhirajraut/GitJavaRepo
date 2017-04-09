package com.intertek.domain;

import com.intertek.entity.CfgContract;
import com.intertek.entity.RB;
import com.intertek.util.Constants;

public class AddTestService
{
  private String contractCode;
  private String index;
  private String selectedVesselIndex;
  private String selectedProdIndex;

  private String searchType = Constants.BOTH;
  private String searchText;
  private String contractSearchCD = Constants.BOTH;
  private String productGroup;

  private SelectedTest[] selectedTests;
  private RB rb;
  private CfgContract contract;

  public AddTestService()
  {
  }

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

  public void setSelectedProdIndex(String selectedProdIndex)
  {
    this.selectedProdIndex = selectedProdIndex;
  }

  public String getSelectedProdIndex()
  {
    return selectedProdIndex;
  }

  public String getSearchType()
  {
    return searchType;
  }

  public void setSearchType(String searchType)
  {
    this.searchType = searchType;
  }

  public String getSearchText()
  {
    return searchText;
  }

  public void setSearchText(String searchText)
  {
    this.searchText = searchText;
  }


  public String getContractSearchCD()
  {
    return contractSearchCD;
  }

  public void setContractSearchCD(String contractSearchCD)
  {
    this.contractSearchCD = contractSearchCD;
  }

  public String getProductGroup()
  {
    return productGroup;
  }

  public void setProductGroup(String productGroup)
  {
    this.productGroup = productGroup;
  }

  public CfgContract getContract()
  {
    return contract;
  }

  public void setContract(CfgContract contract)
  {
    this.contract = contract;
  }

  public SelectedTest[] getSelectedTests()
  {
    return selectedTests;
  }

  public void setSelectedTests(SelectedTest[] selectedTests)
  {
    this.selectedTests = selectedTests;
  }

  public RB getRb()
  {
    return rb;
  }

  public void setRb(RB rb)
  {
    this.rb = rb;
  }
}