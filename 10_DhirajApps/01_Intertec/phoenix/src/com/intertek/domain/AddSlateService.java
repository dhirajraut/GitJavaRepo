package com.intertek.domain;

import com.intertek.entity.CfgContract;
import com.intertek.entity.RB;
import com.intertek.util.Constants;

public class AddSlateService
{
  private String contractCode;
  private String index;
  private String selectedVesselIndex;
  private String selectedProdIndex;

  private String searchType = Constants.BOTH;
  private String searchText;

  private SelectedSlate[] selectedSlates;


  private RB rb;

  private CfgContract contract;

  public AddSlateService()
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

  public CfgContract getContract()
  {
    return contract;
  }

  public void setContract(CfgContract contract)
  {
    this.contract = contract;
  }

  public SelectedSlate[] getSelectedSlates()
  {
    return selectedSlates;
  }

  public void setSelectedSlates(SelectedSlate[] selectedSlates)
  {
    this.selectedSlates = selectedSlates;
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