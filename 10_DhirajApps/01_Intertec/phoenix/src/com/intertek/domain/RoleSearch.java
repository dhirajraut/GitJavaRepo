package com.intertek.domain;


public class RoleSearch extends Search
{
  private StringSearchField roleDesc;
  private StringSearchField name;
  private StringSearchField role;
  private String searchType;
  private String searchForm;
  private String pageNo;
  private String inputFieldId;
  private String rowNum;
 private String sortFlag;
 private String rxcel="false";
  private String submitFlag;
  private String styleVisibility="visibility:visible";
//START : #119240 : 22/06/09
  private String currentSortFlag;
  private String prevSortFlag;
  private String sortOrderFlag;
  // END : #119240 : 22/06/09
  
  
  public StringSearchField getRole()
  {
    return role;
  }

  public void setRole(StringSearchField role)
  {
    this.role =role;
  }
  public RoleSearch()
  {
    roleDesc = new StringSearchField();
    name = new StringSearchField();
  role = new StringSearchField();
	  pageNo = new String();
  }

  public void setRoleDesc(StringSearchField roleDesc)
  {
    this.roleDesc = roleDesc;
  }

  public StringSearchField getRoleDesc()
  {
    return roleDesc;
  }

  public void setName(StringSearchField name)
  {
    this.name = name;
  }

  public StringSearchField getName()
  {
    return name;
  }

  public void setSearchType(String searchType)
  {
    this.searchType = searchType;
  }

  public String getSearchType()
  {
    return searchType;
  }
  public void setSearchForm(String searchForm)
  {
    this.searchForm = searchForm;
  }

  public String getSearchForm()
  {
    return searchForm;
  }
   public void setPageNo(String pageNo)
  {
    this.pageNo = pageNo;
  }

  public String getPageNo()
  {
    return pageNo;
  }

  public String getInputFieldId()
  {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId)
	{
		this.inputFieldId = inputFieldId;
	}
  public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}
public String getRxcel(){
return rxcel;
}

public void setRxcel(String rxcel){
this.rxcel=rxcel;
}

public String getSubmitFlag(){
return submitFlag;
}

public void setSubmitFlag(String submitFlag){
this.submitFlag=submitFlag;
}
public String getStyleVisibility() {
	return styleVisibility;
}

public void setStyleVisibility(String styleVisibility) {
	this.styleVisibility = styleVisibility;
}
//START : #119240 : 22/06/09

public String getCurrentSortFlag() {
	return currentSortFlag;
}

public void setCurrentSortFlag(String currentSortFlag) {
	this.currentSortFlag = currentSortFlag;
}

public String getPrevSortFlag() {
	return prevSortFlag;
}

public void setPrevSortFlag(String prevSortFlag) {
	this.prevSortFlag = prevSortFlag;
}

public String getSortOrderFlag() {
	return sortOrderFlag;
}

public void setSortOrderFlag(String sortOrderFlag) {
	this.sortOrderFlag = sortOrderFlag;
}

// END : #119240 : 22/06/09

}
