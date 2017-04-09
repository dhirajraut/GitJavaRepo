package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Currency;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CurrencyService;

public class CurrencyDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    CurrencyService currencyService = (CurrencyService)ServiceLocator.getInstance().getBean("currencyService");

    List currencies = currencyService.getCurrencies();
    for(int i=0; i<currencies.size(); i++)
    {
      Currency curr = (Currency) currencies.get(i);

      Field field = new Field();
      String name = curr.getCurrencyCd();
      field.setName(name);
      field.setValue(name);
      results.add(field);
    }

    return results;
  }
}
