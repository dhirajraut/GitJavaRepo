package com.intertek.domain;

import com.intertek.entity.Slate;

public class SelectedSlate
{
  private boolean selected;
  private Double qty = 1.0;

  private Slate slate;

  public boolean isSelected()
  {
    return selected;
  }

  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  public Double getQty()
  {
    return qty;
  }

  public void setQty(Double qty)
  {
    this.qty = qty;
  }

  public Slate getSlate()
  {
    return slate;
  }

  public void setSlate(Slate slate)
  {
    this.slate = slate;
  }
}
