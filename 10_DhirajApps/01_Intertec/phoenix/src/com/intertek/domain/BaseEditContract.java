package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.Contract;

public class BaseEditContract
{
  @CascadeValidation
  private Contract contract;

  private boolean viewOnly;

  private ContractSearch contractSearch;

  public void setContract(Contract contract)
  {
    this.contract = contract;
  }

  public Contract getContract()
  {
    return this.contract;
  }

  public void setViewOnly(boolean viewOnly)
  {
    this.viewOnly = viewOnly;
  }

  public boolean getViewOnly()
  {
    return viewOnly;
  }

  public void setContractSearch(ContractSearch contractSearch)
  {
    this.contractSearch = contractSearch;
  }

  public ContractSearch getContractSearch()
  {
    return contractSearch;
  }
}
