package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.ContractService;

public class WorkingPriceBookIdDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size < 1) return results;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    String currencyCD = (String)params.get(0);

    List pbIds = contractService.getPriceBookIdsByCurrencyCD(currencyCD);

    for(int i=0; i<pbIds.size(); i++)
    {
      String pbId = (String)pbIds.get(i);

      Field field = new Field();

      field.setName(pbId);
      field.setValue(pbId);
      results.add(field);
    }

    return results;
  }
}
