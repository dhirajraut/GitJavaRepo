package com.intertek.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SequenceNumberServiceImpl extends BaseService implements SequenceNumberService
{
  private static Log log = LogFactory.getLog(SequenceNumberServiceImpl.class);

  public Long getSequenceNumber(String name)
  {
    List results = getDao().findBySQL("select " + name + ".nextval from dual", null);

    if(results.size() > 0)
    {
      Number number = (Number)results.get(0);
      return new Long(number.longValue());
    }

    return null;
  }
}

