package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.pagination.Pagination;

public class SelectBranches
{
  private EditZone editZone;
  private String zoneIndex;
  private ZoneExt zoneExt;

  private List allBranchExtList = new ArrayList();
  private List selectedBranchExtList = new ArrayList();

  private Set oldBranches = new HashSet();
  private Set newBranches = new HashSet();

  private Pagination allBranchPagination;
  private Pagination selectedBranchPagination;

  private BranchSearch branchSearch;

  private boolean checkAll;
  private boolean selectedCheckAll;

  private String sortFlag;
  private int pageNumber;

  public void setEditZone(EditZone editZone)
  {
    this.editZone = editZone;
  }

  public EditZone getEditZone()
  {
    return editZone;
  }

  public void setZoneIndex(String zoneIndex)
  {
    this.zoneIndex = zoneIndex;
  }

  public String getZoneIndex()
  {
    return zoneIndex;
  }

  public void setZoneExt(ZoneExt zoneExt)
  {
    this.zoneExt = zoneExt;
  }

  public ZoneExt getZoneExt()
  {
    return zoneExt;
  }

  public List getAllBranchExtList()
  {
    return allBranchExtList;
  }

  public List getSelectedBranchExtList()
  {
    return selectedBranchExtList;
  }

  public Set getOldBranches()
  {
    return oldBranches;
  }

  public Set getNewBranches()
  {
    return newBranches;
  }

  public void setAllBranchPagination(Pagination allBranchPagination)
  {
    this.allBranchPagination = allBranchPagination;
  }

  public Pagination getAllBranchPagination()
  {
    return allBranchPagination;
  }

  public void setSelectedBranchPagination(Pagination selectedBranchPagination)
  {
    this.selectedBranchPagination = selectedBranchPagination;
  }

  public Pagination getSelectedBranchPagination()
  {
    return selectedBranchPagination;
  }

  public void setBranchSearch(BranchSearch branchSearch)
  {
    this.branchSearch = branchSearch;
  }

  public BranchSearch getBranchSearch()
  {
    return branchSearch;
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
