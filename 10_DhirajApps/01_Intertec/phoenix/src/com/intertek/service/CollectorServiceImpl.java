package com.intertek.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.domain.CollectorSearch;
import com.intertek.entity.Collector;
import com.intertek.exception.ServiceException;
import com.intertek.pagination.Pagination;
import com.intertek.web.controller.user.BranchController;

public class CollectorServiceImpl extends BaseService implements CollectorService
{
  private static Log log = LogFactory.getLog(BranchController.class);

  public void searchCollector(CollectorSearch search)
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
      sb.append(" where upper(cl.collectorName) like ?");
      params.add(strNameSearch);
      hasWhere = true;
    }

    String code = search.getCollector().getValue();
    if ((code != null) && !"".equals(code.trim()))
      strCodeSearch = '%' + code.toUpperCase() + '%';

    if ((code != null) && !"".equals(code.trim())) {
      if (hasWhere)
        sb.append(" and ");
      else {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(cl.collectorCode) like ? ");
      params.add(strCodeSearch);
    }

    Pagination pagination = search.getPagination();
    List results = null;

    if (pagination != null) {
      if (pagination.getTotalRecord() > 0) {
        List counts = getDao().find(
            "select count(cl.collectorCode) from Collector cl "
                + sb.toString(), params.toArray());

        if (counts.size() > 0) {
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      log.info("string buffer :" + sb.toString());
      results = getDao().find(
          "select distinct cl from Collector cl " + sb.toString()
              + " order by cl.collectorCode", params.toArray(),
          pagination);

      pagination.calculatePages();
    } else {
      results = getDao().find(
          "select distinct cl from Collector cl " + sb.toString(),
          params.toArray());
    }

    search.setResults(results);
    search.setPagination(pagination);
  }

  public List getCollectorNameByCode(String collectorCode)
  {
    if(collectorCode == null) return new ArrayList();

    return getDao().find(
      "from Collector c where upper(c.collectorCode) like '" + collectorCode.toUpperCase() + "%'",
      null
    );
  }

  public Collector getCollectorByCode(String collectorCode)
  {
    if(collectorCode == null) return null;

    List collector = collector = getDao().find(
      "from Collector c where upper(c.collectorCode) = '" + collectorCode.toUpperCase() + "'",
      null
    );

    if(collector.size() > 0) return (Collector) collector.get(0);

    return null;
  }

  public List listCollectorByCode(String code)
  {
    if(code == null) return new ArrayList();

    String searchName =  code.toUpperCase() + "%";
    List collector = getDao().find(
      "from Collector c where upper(c.collectorCode) like ? ", new Object[] { searchName } );
    if(collector.size() > 0) return collector;

    return null;
  }

  public void addCollector(Collector collector)
  {
    if(collector == null) return;

    Collector existedCollector = getCollectorByCode(collector.getCollectorCode());
    if(existedCollector != null)
    {
      throw new ServiceException(
        "COLLECTOR_EXIST", new Object[] { collector.getCollectorCode() }
      );
    }

    getDao().save(collector);
  }

  public void saveCollector(Collector collector)
  {
    if(collector == null) return;

    getDao().save(collector);
  }
}

