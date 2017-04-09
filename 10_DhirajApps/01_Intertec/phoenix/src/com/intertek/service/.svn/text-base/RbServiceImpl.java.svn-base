package com.intertek.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.intertek.domain.EditRBExt;
import com.intertek.domain.RBExt;
import com.intertek.entity.RB;
import com.intertek.util.Constants;
import com.intertek.util.EntityUtil;

public class RbServiceImpl extends BaseService implements RbService
{
  public List getRbList(String contractId, String rbKey)
  {
    if((contractId == null) || (rbKey == null)) return new ArrayList();

    return getDao().find(
      "select distinct rb from RB rb where rb.rbId.contractId = ? and rb.rbId.rbKey  = ? order by rb.rbId.beginDate",
      new Object[] { contractId, rbKey }
    );
  }

  public List getRbList(String contractId, Date date, List rbKeyList)
  {
    if((contractId == null) || (date == null) || (rbKeyList == null) || (rbKeyList.size() == 0)) return new ArrayList();

    StringBuffer sb = new StringBuffer();
    sb.append("select distinct rb from RB rb where rb.rbId.contractId = ? and ? between rb.rbId.beginDate and rb.rbId.endDate and rb.rbId.rbKey in (");

    Object[] params = new Object[rbKeyList.size() + 2];
    params[0] = contractId;
    params[1] = date;

    for(int i=0; i<rbKeyList.size(); i++)
    {
      params[i + 2] = rbKeyList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getRbListWithEqualBeginDate(String contractId, Date date, List rbKeyList)
  {
    if((contractId == null) || (date == null) || (rbKeyList == null) || (rbKeyList.size() == 0)) return new ArrayList();

    StringBuffer sb = new StringBuffer();
    sb.append("select distinct rb from RB rb where rb.rbId.contractId = ? and ? = rb.rbId.beginDate and rb.rbId.rbKey in (");

    Object[] params = new Object[rbKeyList.size() + 2];
    params[0] = contractId;
    params[1] = date;

    for(int i=0; i<rbKeyList.size(); i++)
    {
      params[i + 2] = rbKeyList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getRbList(String contractId, List rbKeyList)
  {
    if((contractId == null) || (rbKeyList == null) || (rbKeyList.size() == 0)) return new ArrayList();

    StringBuffer sb = new StringBuffer();
    sb.append("select distinct rb from RB rb where rb.rbId.contractId = ? and rb.rbId.rbKey in (");

    Object[] params = new Object[rbKeyList.size() + 1];
    params[0] = contractId;

    for(int i=0; i<rbKeyList.size(); i++)
    {
      params[i + 1] = rbKeyList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public RB getRb(String contractId, String rbKey)
  {
    Date date = new Date();

    List rbs = getDao().find(
      "from RB rb where rb.rbId.contractId = ? and rb.rbId.rbKey = ? and rb.rbId.beginDate <= ? and rb.rbId.endDate >= ?",
      new Object[] { contractId, rbKey, date, date }
    );

    if(rbs != null && rbs.size() > 0) return (RB)rbs.get(0);

    return null;
  }


  public RB getRb(String rbKey)
  {
    Date date = new Date();

    List rbs = getDao().find(
      "from RB rb where rb.rbId.contractId = ? and rb.rbId.rbKey = ? and rb.rbId.beginDate <= ? and rb.rbId.endDate >= ?",
      new Object[] { "NONE", rbKey, date, date }
    );

    if(rbs != null && rbs.size() > 0) return (RB)rbs.get(0);

    return null;
  }

  public RB getRB(String contractId, String rbKey, Date beginDate)
  {
    if((contractId == null) || (rbKey == null) || (beginDate == null)) return null;

    List list = getDao().find(
      "from RB rb where rb.rbId.contractId = ? and rb.rbId.rbKey =? and rb.rbId.beginDate = ?",
      new Object[] { contractId, rbKey, beginDate }
    );

    if(list.size() > 0) return (RB)list.get(0);

    return null;
  }

  public RB getRB(String contractId, String rbKey, Date beginDate, Date endDate)
  {
    if((contractId == null) || (rbKey == null) || (beginDate == null) || (endDate == null)) return null;

    List list = getDao().find(
      "from RB rb where rb.rbId.contractId = ? and rb.rbId.rbKey =? and rb.rbId.beginDate = ? and rb.rbId.endDate = ?",
      new Object[] { contractId, rbKey, beginDate, endDate }
    );

    if(list.size() > 0) return (RB)list.get(0);

    return null;
  }

  public void saveRbExt(RBExt rbExt)
  {
    if(rbExt == null) return;

    RB rb = rbExt.getRb();
    if(rb != null)
    {
      saveRb(rb);
    }

    RB notesRb = rbExt.getNotesRb();
    if(notesRb != null)
    {
      saveRb(notesRb);
    }
  }

  public void saveNotesRb(RBExt rbExt, Date beginDate, Date endDate)
  {
    if(rbExt == null) return;

    RB notesRb = rbExt.getNotesRb();
    if(notesRb != null)
    {
      if("NONE".equals(notesRb.getRbId().getContractId())) return;

      notesRb.getRbId().setBeginDate(beginDate);
      notesRb.getRbId().setEndDate(endDate);

      getDao().save(notesRb);
    }
  }

  public void removeRb(RB rb)
  {
    if(rb == null) return;

    getDao().remove(rb);
  }

  private void saveRb(RB rb)
  {
    if(rb == null) return;

    if("NONE".equals(rb.getRbId().getContractId())) return;

    removeRbByContractIdAndRbKey(rb.getRbId().getContractId(), rb.getRbId().getRbKey());

    getDao().save(rb);
  }

  private void removeRbByContractIdAndRbKey(
    String contractId,
    String rbKey
  )
  {
    if((contractId == null) || (rbKey == null)) return;

    getDao().bulkUpdate(
      "delete from RB o where o.rbId.contractId = ? and o.rbId.rbKey = ?",
      new Object[] { contractId, rbKey }
    );
  }


  public void removeRbsByContractIdAndBeginDateForGroup(
    String contractId,
    Date beginDate
  )
  {
    if((contractId == null) || (beginDate == null)) return;

    getDao().bulkUpdate(
      "delete from RB o where o.rbId.contractId = ? and o.rbId.beginDate = ? and o.rbId.rbKey like 'Group.%'",
      new Object[] { contractId, beginDate }
    );
  }

  public void removeRbsByContractIdAndBeginDateForVessel(
    String contractId,
    Date beginDate
  )
  {
    if((contractId == null) || (beginDate == null)) return;

    getDao().bulkUpdate(
      "delete from RB o where o.rbId.contractId = ? and o.rbId.beginDate = ? and o.rbId.rbKey like 'Vessel.%'",
      new Object[] { contractId, beginDate }
    );
  }
}

