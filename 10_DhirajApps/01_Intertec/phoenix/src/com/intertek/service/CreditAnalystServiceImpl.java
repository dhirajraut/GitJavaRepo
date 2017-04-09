package com.intertek.service;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.CreditAnalystSearch;
import com.intertek.entity.CreditAnalyst;
import com.intertek.exception.ServiceException;
import com.intertek.pagination.Pagination;

public class CreditAnalystServiceImpl extends BaseService implements CreditAnalystService
{
  public void searchCreditAnalyst(CreditAnalystSearch search)
  {
    if (search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strNameSearch = "";
    String strCodeSearch = "";

    String name = search.getName().getValue();
    if ((name != null) && !"".equals(name.trim()))
      strNameSearch = '%' + name.toUpperCase() + '%';

    if ((name != null) && !"".equals(name.trim())) {
      sb.append(" where upper(cr.crAnalystName) like ?");
      params.add(strNameSearch);
      hasWhere = true;
    }

    String code = search.getCreditAnalyst().getValue();
    if ((code != null) && !"".equals(code.trim()))
      strCodeSearch = '%' + code.toUpperCase() + '%';

    if ((code != null) && !"".equals(code.trim())) {
      if (hasWhere)
        sb.append(" and ");
      else {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(cr.creditAnalystCode) like ? ");
      params.add(strCodeSearch);
    }

    Pagination pagination = search.getPagination();
    List results = null;

    if (pagination != null) {
      if (pagination.getTotalRecord() > 0) {
        List counts = getDao().find(
            "select count(cr.creditAnalystCode) from CreditAnalyst cr "
                + sb.toString(), params.toArray());

        if (counts.size() > 0) {
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      results = getDao().find(
          "select distinct cr from CreditAnalyst cr " + sb.toString()
              + " order by cr.creditAnalystCode",
          params.toArray(), pagination);

      pagination.calculatePages();
    } else {
      results = getDao()
          .find(
              "select distinct cr from CreditAnalyst cr "
                  + sb.toString(), params.toArray());
    }

    search.setResults(results);
    search.setPagination(pagination);
  }

  public List listCreditAnalystByCode(String code)
  {
    if(code == null) return new ArrayList();

    String searchName =  code.toUpperCase() + "%";
    return getDao().find(
      "from CreditAnalyst c where upper(c.creditAnalystCode) like ?",
      new Object[] { searchName }
    );
  }

  public List searchCreditAnalystByCode(String creditAnalystCode)
  {
    if(creditAnalystCode == null) return new ArrayList();

    return getDao().find(
      "from CreditAnalyst c where upper(c.creditAnalystCode) like '" + creditAnalystCode.toUpperCase() + "%'",
      null
    );
  }

  public CreditAnalyst getCreditAnalystByCode(String creditAnalystCode)
  {
    if(creditAnalystCode == null) return null;
    List cas = getDao().find(
      "from CreditAnalyst c where upper(c.creditAnalystCode) = '" + creditAnalystCode.toUpperCase() + "'",
      null
    );

    if(cas.size() > 0) return (CreditAnalyst) cas.get(0);

    return null;
  }

  public void addCreditAnalyst(CreditAnalyst ca)
  {
    if(ca == null) return;

    CreditAnalyst existedCA = getCreditAnalystByCode(ca.getCreditAnalystCode());
    if(existedCA != null)
    {
      throw new ServiceException(
        "CREDIT_ANALYST_EXIST", new Object[] { ca.getCreditAnalystCode() }
      );
    }

    getDao().save(ca);
  }

  public void saveCreditAnalyst(CreditAnalyst ca)
  {
    if(ca == null) return;

    getDao().save(ca);
  }
}

