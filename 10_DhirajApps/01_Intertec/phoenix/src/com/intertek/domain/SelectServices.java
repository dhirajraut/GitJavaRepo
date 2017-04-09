package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.pagination.Pagination;

public class SelectServices
{
  private EditServiceRate editServiceRate;

  private List allHighLevelServiceExtList = new ArrayList();
  private List selectedHighLevelServiceExtList = new ArrayList();

  private Set oldHighLevelServices = new HashSet();
  private Set newHighLevelServices = new HashSet();

  private String serviceName;
  private boolean includeMasterListServices;

  private Pagination allHighLevelServicePagination;

  private boolean checkAll;
  private boolean selectedCheckAll;

  private String sortFlag;
  private int pageNumber;

  public void setEditServiceRate(EditServiceRate editServiceRate)
  {
    this.editServiceRate = editServiceRate;
  }

  public EditServiceRate getEditServiceRate()
  {
    return editServiceRate;
  }

  public List getAllHighLevelServiceExtList()
  {
    return allHighLevelServiceExtList;
  }

  public List getSelectedHighLevelServiceExtList()
  {
    return selectedHighLevelServiceExtList;
  }

  public Set getOldHighLevelServices()
  {
    return oldHighLevelServices;
  }

  public Set getNewHighLevelServices()
  {
    return newHighLevelServices;
  }

  public void setServiceName(String serviceName)
  {
    this.serviceName = serviceName;
  }

  public String getServiceName()
  {
    return serviceName;
  }

  public void setIncludeMasterListServices(boolean includeMasterListServices)
  {
    this.includeMasterListServices = includeMasterListServices;
  }

  public boolean getIncludeMasterListServices()
  {
    return includeMasterListServices;
  }

  public void setAllHighLevelServicePagination(Pagination allHighLevelServicePagination)
  {
    this.allHighLevelServicePagination = allHighLevelServicePagination;
  }

  public Pagination getAllHighLevelServicePagination()
  {
    return allHighLevelServicePagination;
  }


  public boolean getCheckAll()
  {
    return checkAll;
  }

  public void setCheckAll(boolean checkAll)
  {
    this.checkAll = checkAll;
  }

  public boolean getSelectedCheckAll()
  {
    return selectedCheckAll;
  }

  public void setSelectedCheckAll(boolean selectedCheckAll)
  {
    this.selectedCheckAll = selectedCheckAll;
  }

  public String getSortFlag()
  {
    return sortFlag;
  }

  public void setSortFlag(String sortFlag)
  {
    this.sortFlag = sortFlag;
  }

  public int getPageNumber()
  {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber)
  {
    this.pageNumber = pageNumber;
  }
}
