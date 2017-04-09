package com.intertek.domain;

import java.util.List;

import com.intertek.entity.*;
import com.intertek.util.*;
import com.intertek.calculator.*;
import com.intertek.servicetype.*;

public class EditProductGroupSet
{
  private String index;
  private String baseProductGroupSetName;
  private String changedProductGroupSetName;
  private String oldProductGroupSetName;

  private AddContract addContract;
  private CfgContract cfgContract;
  private CfgContract oldCfgContract;

  private List productGroupExts;
  private List customizedProductGroupExts;
  private List baseProductGroupExts;

  private EditRBExt editRBExt;

  private boolean checkAll;

  public EditProductGroupSet()
  {
  }

  public String getIndex()
  {
    return index;
  }

  public void setIndex(String index)
  {
    this.index = index;
  }

  public String getBaseProductGroupSetName()
  {
    return baseProductGroupSetName;
  }

  public void setBaseProductGroupSetName(String baseProductGroupSetName)
  {
    this.baseProductGroupSetName = baseProductGroupSetName;
  }

  public String getChangedProductGroupSetName()
  {
    return changedProductGroupSetName;
  }

  public void setChangedProductGroupSetName(String changedProductGroupSetName)
  {
    this.changedProductGroupSetName = changedProductGroupSetName;
  }

  public String getOldProductGroupSetName()
  {
    return oldProductGroupSetName;
  }

  public void setOldProductGroupSetName(String oldProductGroupSetName)
  {
    this.oldProductGroupSetName = oldProductGroupSetName;
  }

  public void setAddContract(AddContract addContract)
  {
    this.addContract = addContract;
  }

  public AddContract getAddContract()
  {
    return addContract;
  }

  public CfgContract getCfgContract()
  {
    return cfgContract;
  }

  public void setCfgContract(CfgContract cfgContract)
  {
    this.cfgContract = cfgContract;
  }

  public CfgContract getOldCfgContract()
  {
    return oldCfgContract;
  }

  public void setOldCfgContract(CfgContract oldCfgContract)
  {
    this.oldCfgContract = oldCfgContract;
  }

  public List getProductGroupExts()
  {
    return productGroupExts;
  }

  public void setProductGroupExts(List productGroupExts)
  {
    this.productGroupExts = productGroupExts;
  }

  public List getCustomizedProductGroupExts()
  {
    return customizedProductGroupExts;
  }

  public void setCustomizedProductGroupExts(List customizedProductGroupExts)
  {
    this.customizedProductGroupExts = customizedProductGroupExts;
  }

  public List getBaseProductGroupExts()
  {
    return baseProductGroupExts;
  }

  public void setBaseProductGroupExts(List baseProductGroupExts)
  {
    this.baseProductGroupExts = baseProductGroupExts;
  }

  public EditRBExt getEditRBExt()
  {
    return editRBExt;
  }

  public void setEditRBExt(EditRBExt editRBExt)
  {
    this.editRBExt = editRBExt;
  }

  public boolean getCheckAll()
  {
    return checkAll;
  }

  public void setCheckAll(boolean checkAll)
  {
    this.checkAll = checkAll;
  }
}
