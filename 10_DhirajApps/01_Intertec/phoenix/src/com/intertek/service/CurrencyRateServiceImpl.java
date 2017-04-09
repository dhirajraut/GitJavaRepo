package com.intertek.service;

import com.intertek.entity.BankAccountCurr;
import com.intertek.entity.CurrencyRate;

public class CurrencyRateServiceImpl extends BaseService implements CurrencyRateService
{
  public void saveCurrencyRate(CurrencyRate currencyRate)
  {
     getDao().save(currencyRate);
  }
}

