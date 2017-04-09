package com.intertek.domain;

import com.intertek.entity.CfgContract;

public class CfgContractExt
{
  private CfgContract oldCfgContract;
  private CfgContract cfgContract;

  private EditProductGroupSet editProductGroupSet;
  private EditVesselTypeSet editVesselTypeSet;

  public CfgContract getOldCfgContract()
  {
    return oldCfgContract;
  }

  public void setOldCfgContract(CfgContract oldCfgContract)
  {
    this.oldCfgContract = oldCfgContract;
  }

  public CfgContract getCfgContract()
  {
    return cfgContract;
  }

  public void setCfgContract(CfgContract cfgContract)
  {
    this.cfgContract = cfgContract;
  }

  public EditProductGroupSet getEditProductGroupSet()
  {
    return editProductGroupSet;
  }

  public void setEditProductGroupSet(EditProductGroupSet editProductGroupSet)
  {
    this.editProductGroupSet = editProductGroupSet;
  }

  public EditVesselTypeSet getEditVesselTypeSet()
  {
    return editVesselTypeSet;
  }

  public void setEditVesselTypeSet(EditVesselTypeSet editVesselTypeSet)
  {
    this.editVesselTypeSet = editVesselTypeSet;
  }
}
