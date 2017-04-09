package com.intertek.service;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.TimeZoneSearch;
import com.intertek.entity.TimeZone;
import com.intertek.exception.ServiceException;
import com.intertek.pagination.Pagination;

public class TimeZoneServiceImpl extends BaseService implements TimeZoneService
{
  public TimeZone getTimeZoneByName(String name)
  {
    if(name == null) return null;

    String ename=name.toUpperCase();
    List timeZones = getDao().find(
      "from TimeZone tz where upper(tz.timeZone) ='" + ename + "'",
      null
    );

    if(timeZones.size() > 0) return (TimeZone)timeZones.get(0);

    return null;
  }

  public List getTimeZonesByName(String name)
  {
    if (name == null) return new ArrayList();

    return getDao().find(
      "from TimeZone tz where upper(tz.timeZone) like '" + name.toUpperCase() + "%'",
      null
    );
  }

  public void searchTimeZone(TimeZoneSearch search)
  {
    if (search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strTimeZoneSearch = "";
    String strZoneDescSearch = "";
    strTimeZoneSearch = search.getTimeZone().getValue();

    if ((strTimeZoneSearch != null) && !"".equals(strTimeZoneSearch.trim()))
      strTimeZoneSearch = '%' + strTimeZoneSearch.toUpperCase() + '%';

    if ((strTimeZoneSearch != null) && !"".equals(strTimeZoneSearch.trim())) {
      sb.append(" where upper(tz.timeZone) like ?");
      params.add(strTimeZoneSearch);
      hasWhere = true;
    }

    strZoneDescSearch = search.getTimeZoneDesc().getValue();

    if ((strZoneDescSearch != null) && !"".equals(strZoneDescSearch.trim()))
      strZoneDescSearch = '%' + strZoneDescSearch.toUpperCase() + '%';

    if ((strZoneDescSearch != null) && !"".equals(strZoneDescSearch.trim())) {
      if (hasWhere)
        sb.append(" and ");
      else {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(tz.timeZoneDesc) like ?");
      params.add(strZoneDescSearch);
    }

    Pagination pagination = search.getPagination();
    List results = null;

    if (pagination != null) {
      if (pagination.getTotalRecord() > 0) {
        List counts = getDao().find(
            "select count(tz.timeZone) from TimeZone tz "
                + sb.toString(), params.toArray());

        if (counts.size() > 0) {
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      System.out.println("string buffer :" + sb.toString());
      results = getDao().find(
          "select distinct tz from TimeZone tz " + sb.toString()
              + " order by tz.timeZone", params.toArray(),
          pagination);

      pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
          "select distinct tz from TimeZone tz " + sb.toString(),
          params.toArray());
    }

    search.setResults(results);
    search.setPagination(pagination);
  }

  public void addTimeZone(TimeZone timeZone)
  {
    if(timeZone == null) return;

    TimeZone existedTimeZone = getTimeZoneByName(timeZone.getTimeZone());
    if(existedTimeZone != null)
    {
      throw new ServiceException(
        "TIME_ZONE_EXIST", new Object[] { timeZone.getTimeZone() }
      );
    }

    getDao().save(timeZone);
  }

  public void saveTimeZone(TimeZone timeZone)
  {
    if(timeZone == null) return;

    getDao().save(timeZone);
  }
}

