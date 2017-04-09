package com.intertek.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.intertek.entity.*;
import com.intertek.util.*;
import com.intertek.calculator.*;
import com.intertek.pagination.*;

public class EditInspectionRate extends BaseEditContract
{
  private List inspectionCeList;
  private List inspectionControlList;

  private List inspectionVersionExtList;
  private List deletedInspectionVersionExtList = new ArrayList();

  private int activeInspectionVersionIndex;
  private Pagination pagination;

  private Date earliestContractBeginDate;

  public List getInspectionCeList()
  {
    return inspectionCeList;
  }

  public void setInspectionCeList(List inspectionCeList)
  {
    this.inspectionCeList = inspectionCeList;
  }

  public List getInspectionControlList()
  {
    return inspectionControlList;
  }

  public void setInspectionControlList(List inspectionControlList)
  {
    this.inspectionControlList = inspectionControlList;
  }

  public void setInspectionVersionExtList(List inspectionVersionExtList)
  {
    this.inspectionVersionExtList = inspectionVersionExtList;
  }

  public List getInspectionVersionExtList()
  {
    return inspectionVersionExtList;
  }

  public List getDeletedInspectionVersionExtList()
  {
    return deletedInspectionVersionExtList;
  }

  public void setActiveInspectionVersionIndex(int activeInspectionVersionIndex)
  {
    this.activeInspectionVersionIndex = activeInspectionVersionIndex;
  }

  public int getActiveInspectionVersionIndex()
  {
    return activeInspectionVersionIndex;
  }

  public void setPagination(Pagination pagination)
  {
    this.pagination = pagination;
  }

  public Pagination getPagination()
  {
    return pagination;
  }

  public void setEarliestContractBeginDate(Date earliestContractBeginDate)
  {
    this.earliestContractBeginDate = earliestContractBeginDate;
  }

  public Date getEarliestContractBeginDate()
  {
    return earliestContractBeginDate;
  }
}
