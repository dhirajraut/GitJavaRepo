package com.intertek.service;


import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.CurrencySearch;
import com.intertek.entity.Currency;
import com.intertek.pagination.Pagination;

public class CurrencyServiceImpl extends BaseService implements CurrencyService
{
  public Currency getCurrencyByCurrencyCd(String currencyCd)
  {
    List currencys = getDao().find(
      "from Currency currency where currency.currencyCd = ?",
      new Object[] { currencyCd }
    );

    if(currencys.size() > 0) return (Currency)currencys.get(0);

    return null;
  }

  public Currency addCurrency(Currency currency)
  {
    if(currency == null) return null;

    Currency existedCurrency = getCurrencyByCurrencyCd(currency.getCurrencyCd());
    if(existedCurrency != null)
    {
      throw new RuntimeException("Currency with the same currencyCd exists: " + currency.getCurrencyCd());
    }

    return getDao().save(currency);
  }

  public void saveCurrency(Currency currency)
  {
    if(currency == null) return;

    Currency existedCurrency = getCurrencyByCurrencyCd(currency.getCurrencyCd());
    if(existedCurrency == null)
    {
      throw new RuntimeException("Currency not exist for cd: " + currency.getCurrencyCd());
    }

    getDao().save(currency);
  }


/* public List getCurrencies()
  {
    return getDao().getAll(Currency.class);
  }*/

public List getCurrencies()
{
return getDao().find("from Currency c order by c.currencyCd",null);
}

  public void searchCurrency(CurrencySearch search)
  {
  if(search == null) return;

  StringBuffer sb = new StringBuffer();
  List params = new ArrayList();

  boolean hasWhere = false;

  String strcurrencySearch = "";

  String cs = search.getCurrencyCd().getValue();
  if((cs!= null) && !"".equals(cs.trim()))
  strcurrencySearch ='%'+  cs.toUpperCase() + '%';

  if((cs != null) && !"".equals(cs.trim()))
  {
  sb.append(" where upper(c.currencyCd) like ?");
  params.add(strcurrencySearch);
  hasWhere = true;
  }
  Pagination pagination = search.getPagination();
  List results = null;

  if(pagination != null)
  {
  if(pagination.getTotalRecord() > 0)
  {
  List counts = getDao().find(
  "select count(c.currencyCd) from Currency c " + sb.toString(),
  params.toArray()
  );

  if(counts.size() > 0)
  {
  Number count = (Number)counts.get(0);
  pagination.setTotalRecord(count.intValue());
  }
  }
  results = getDao().find(
  "select distinct c from Currency c " + sb.toString() +" order by c.currencyCd",
  params.toArray(),
  pagination
  );

  pagination.calculatePages();
  }
  else
  {
  results = getDao().find(
  "select distinct c from Currency c"+ sb.toString() ,
  params.toArray()
  );
  }
  search.setResults(results);
  search.setPagination(pagination);
  }

}

