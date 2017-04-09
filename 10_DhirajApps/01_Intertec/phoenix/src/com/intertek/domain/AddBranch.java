package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.AssocBranch;
import com.intertek.entity.Branch;
import com.intertek.entity.*;

public class AddBranch
{
  @CascadeValidation

  private Branch branch;
  private BranchLanguage branchLanguage;

  private AssocBranch[] assocBranches;

  private BranchSearch branchSearch;

  private int assocBranchCount;

  private String addOrDelete;
  private int index;
  private String rowNum;
  private String branchTypeFlag;
  private String divName;
  private String divBody;
  private String searchForm;

  public void setBranch(Branch branch)
  {
    this.branch = branch;
  }
  public Branch getBranch()
  {
    return branch;
  }
  public AssocBranch[] getAssocBranches()
  {
    return assocBranches;
  }

  public void setAssocBranches(AssocBranch[] assocBranches)
  {
    this.assocBranches = assocBranches;
  }

  public int getAssocBranchCount()
  {
    return assocBranchCount;
  }

  public void setAssocBranchCount(int assocBranchCount)
  {
    this.assocBranchCount = assocBranchCount;
  }

  public String getAddOrDelete()
  {
		return addOrDelete;
	}

	public void setAddOrDelete(String addOrDelete)
	{
		this.addOrDelete = addOrDelete;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public BranchSearch getBranchSearch()
	{
		return branchSearch;
	}

	public void setBranchSearch(BranchSearch branchSearch)
	{
		this.branchSearch = branchSearch;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}


	public String getBranchTypeFlag() {
		return branchTypeFlag;
	}
	public void setBranchTypeFlag(String branchTypeFlag) {
		this.branchTypeFlag = branchTypeFlag;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivBody() {
		return divBody;
	}
	public void setDivBody(String divBody) {
		this.divBody = divBody;
	}
	public BranchLanguage getBranchLanguage() {
		return branchLanguage;
	}
	public void setBranchLanguage(BranchLanguage branchLanguage) {
		this.branchLanguage = branchLanguage;
	}
	public String getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(String searchForm) {
		this.searchForm = searchForm;
	}

}
