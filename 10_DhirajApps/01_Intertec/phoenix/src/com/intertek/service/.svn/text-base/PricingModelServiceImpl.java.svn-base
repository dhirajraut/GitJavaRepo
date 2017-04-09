package com.intertek.service;

import java.util.List;

import com.intertek.entity.PricingModel;

public class PricingModelServiceImpl extends BaseService implements PricingModelService
{
  public void savePricingModel(PricingModel pricingModel)
  {
    getDao().save(pricingModel);
  }

  public PricingModel getPricingModel(String name)
  {
    if(name == null) return null;

    List pms = getDao().find(
      "from PricingModel pm where pm.name = ?",
      new Object[] { name }
    );

    if(pms.size() > 0) return (PricingModel)pms.get(0);

    return null;
  }
}
