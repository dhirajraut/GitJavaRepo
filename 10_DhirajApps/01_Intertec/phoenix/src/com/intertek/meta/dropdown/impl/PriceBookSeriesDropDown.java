package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.ContractService;
import com.intertek.util.Constants;

public class PriceBookSeriesDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size < 1) return results;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    String currencyCD = (String)params.get(0);

    List pbSeries = contractService.getPriceBookSeriesByCurrencyCD(currencyCD);

    //Field field1 = new Field();
    //field1.setName(Constants.CURRENT);
    //field1.setValue(Constants.CURRENT);
    //results.add(field1);

    for(int i=0; i<pbSeries.size(); i++)
    {
      String pbSeriesName = (String)pbSeries.get(i);

      Field field = new Field();

      field.setName(pbSeriesName);
      field.setValue(pbSeriesName);
      results.add(field);
    }

    return results;
  }
}
