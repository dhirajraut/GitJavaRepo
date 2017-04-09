package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.InspectionVersion;
import com.intertek.util.Constants;

public class InspectionVersionExt
{
  private InspectionVersion oldInspectionVersion;
  private InspectionVersion inspectionVersion;

  private CfgContract cfgContract;

  private List vesselTypeExtList;
  private List[] vesselTypeExtRows;

  private int activeVesselTypeExtIndex = -1;

  private List ceExtList;
  private List controlExtList;

  private List deletedCeExtList = new ArrayList();
  private List deletedControlExtList = new ArrayList();

  private List productGroupDropDownList;

  private boolean otherCheckBoxesViewOnly;

  private RBExt contractOperationsRbExt;

  public void setOldInspectionVersion(InspectionVersion oldInspectionVersion)
  {
    this.oldInspectionVersion = oldInspectionVersion;
  }

  public InspectionVersion getOldInspectionVersion()
  {
    return oldInspectionVersion;
  }

  public void setInspectionVersion(InspectionVersion inspectionVersion)
  {
    this.inspectionVersion = inspectionVersion;
  }

  public InspectionVersion getInspectionVersion()
  {
    return inspectionVersion;
  }

  public void setCfgContract(CfgContract cfgContract)
  {
    this.cfgContract = cfgContract;
  }

  public CfgContract getCfgContract()
  {
    return cfgContract;
  }

  public void setVesselTypeExtList(List vesselTypeExtList)
  {
    this.vesselTypeExtList = vesselTypeExtList;
  }

  public List getVesselTypeExtList()
  {
    return vesselTypeExtList;
  }

  public void setVesselTypeExtRows(List[] vesselTypeExtRows)
  {
    this.vesselTypeExtRows = vesselTypeExtRows;
  }

  public List[] getVesselTypeExtRows()
  {
    return vesselTypeExtRows;
  }

  public VesselTypeExt getActiveVesselTypeExt()
  {
    if(activeVesselTypeExtIndex < 0) return null;

    if(vesselTypeExtList == null) return null;

    if(activeVesselTypeExtIndex < vesselTypeExtList.size())
    {
      return (VesselTypeExt)vesselTypeExtList.get(activeVesselTypeExtIndex);
    }

    return null;
  }

  public void setActiveVesselTypeExtIndex(int activeVesselTypeExtIndex)
  {
    this.activeVesselTypeExtIndex = activeVesselTypeExtIndex;
  }

  public int getActiveVesselTypeExtIndex()
  {
    return activeVesselTypeExtIndex;
  }

  public void setCeExtList(List ceExtList)
  {
    this.ceExtList = ceExtList;
  }

  public List getCeExtList()
  {
    return ceExtList;
  }

  public void setControlExtList(List controlExtList)
  {
    this.controlExtList = controlExtList;
  }

  public List getControlExtList()
  {
    return controlExtList;
  }

  public List getDeletedCeExtList()
  {
    return deletedCeExtList;
  }

  public List getDeletedControlExtList()
  {
    return deletedControlExtList;
  }

  public boolean getOtherCheckBoxesViewOnly()
  {
    return otherCheckBoxesViewOnly;
  }

  public void setOtherCheckBoxesViewOnly(boolean otherCheckBoxesViewOnly)
  {
    this.otherCheckBoxesViewOnly = otherCheckBoxesViewOnly;
  }

  public void setProductGroupDropDownList(List productGroupDropDownList)
  {
    this.productGroupDropDownList = productGroupDropDownList;
  }

  public List getProductGroupDropDownList()
  {
    return productGroupDropDownList;
  }

  public void setContractOperationsRbExt(RBExt contractOperationsRbExt)
  {
    this.contractOperationsRbExt = contractOperationsRbExt;
  }

  public RBExt getContractOperationsRbExt()
  {
    return contractOperationsRbExt;
  }
}
