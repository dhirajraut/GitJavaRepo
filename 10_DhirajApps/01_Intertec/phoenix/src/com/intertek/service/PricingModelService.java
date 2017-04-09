package com.intertek.service;

import com.intertek.entity.PricingModel;

public interface PricingModelService
{
  void savePricingModel(PricingModel pricingModel);

  PricingModel getPricingModel(String name);
}

