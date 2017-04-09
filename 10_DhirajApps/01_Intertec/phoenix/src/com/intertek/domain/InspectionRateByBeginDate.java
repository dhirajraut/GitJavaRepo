package com.intertek.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.intertek.entity.*;
import com.intertek.util.*;
import com.intertek.calculator.*;
import com.intertek.servicetype.*;

public class InspectionRateByBeginDate
{
  private Date beginDate;
  private Date endDate;

  private Date oldBeginDate;
  private Date oldEndDate;

  private List inspectionRateByVGList = new ArrayList();

  public Date getBeginDate()
  {
    return beginDate;
  }

  public void setBeginDate(Date beginDate)
  {
    this.beginDate = beginDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }

  public Date getOldBeginDate()
  {
    return oldBeginDate;
  }

  public void setOldBeginDate(Date oldBeginDate)
  {
    this.oldBeginDate = oldBeginDate;
  }

  public Date getOldEndDate()
  {
    return oldEndDate;
  }

  public void setOldEndDate(Date oldEndDate)
  {
    this.oldEndDate = oldEndDate;
  }

  public List getInspectionRateByVGList()
  {
    return inspectionRateByVGList;
  }

  public void setInspectionRateByVGList(List inspectionRateByVGList)
  {
    this.inspectionRateByVGList = inspectionRateByVGList;
  }
}
