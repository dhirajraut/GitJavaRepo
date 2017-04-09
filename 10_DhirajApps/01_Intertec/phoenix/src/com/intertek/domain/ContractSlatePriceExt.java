package com.intertek.domain;

import java.util.List;
import java.util.ArrayList;

import com.intertek.entity.ContractSlate;

public class ContractSlatePriceExt
{
  private String slateId;
  private ContractSlate contractSlate;

  private List slatePrices = new ArrayList();

  public String getSlateId()
  {
    return slateId;
  }

  public void setSlateId(String slateId)
  {
    this.slateId = slateId;
  }

  public ContractSlate getContractSlate()
  {
    return contractSlate;
  }

  public void setContractSlate(ContractSlate contractSlate)
  {
    this.contractSlate = contractSlate;
  }

  public List getSlatePrices()
  {
    return slatePrices;
  }

  public void setSlatePrices(List slatePrices)
  {
    this.slatePrices = slatePrices;
  }
}
