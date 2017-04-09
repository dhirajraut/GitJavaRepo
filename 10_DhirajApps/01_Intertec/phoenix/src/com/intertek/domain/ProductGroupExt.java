package com.intertek.domain;

import java.util.List;

import com.intertek.entity.*;
import com.intertek.util.*;
import com.intertek.calculator.*;
import com.intertek.servicetype.*;

public class ProductGroupExt
{
  private boolean viewOnly;
  private boolean selected = true;
  private boolean newlyAdded;
  private String defaultDescription;
  private String customDescription;

  private ProductGroup productGroup;
  private ProductGroup oldProductGroup;

  private RBExt rbExt;
  private Integer uom;

  public ProductGroupExt()
  {
  }

  public void setViewOnly(boolean viewOnly)
  {
    this.viewOnly = viewOnly;
  }

  public boolean getViewOnly()
  {
    return viewOnly;
  }

  public boolean getSelected()
  {
    return selected;
  }

  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  public boolean getNewlyAdded()
  {
    return newlyAdded;
  }

  public void setNewlyAdded(boolean newlyAdded)
  {
    this.newlyAdded = newlyAdded;
  }

  public String getDefaultDescription()
  {
    return defaultDescription;
  }

  public void setDefaultDescription(String defaultDescription)
  {
    this.defaultDescription = defaultDescription;
  }

  public String getCustomDescription()
  {
    return customDescription;
  }

  public void setCustomDescription(String customDescription)
  {
    this.customDescription = customDescription;
  }

  public void setProductGroup(ProductGroup productGroup)
  {
    this.productGroup = productGroup;
  }

  public ProductGroup getProductGroup()
  {
    return productGroup;
  }

  public void setOldProductGroup(ProductGroup oldProductGroup)
  {
    this.oldProductGroup = oldProductGroup;
  }

  public ProductGroup getOldProductGroup()
  {
    return oldProductGroup;
  }

  public RBExt getRbExt()
  {
    return rbExt;
  }

  public void setRbExt(RBExt rbExt)
  {
    this.rbExt = rbExt;
  }

  public void setUom(Integer uom)
  {
    this.uom = uom;
  }

  public Integer getUom()
  {
    return uom;
  }
}
