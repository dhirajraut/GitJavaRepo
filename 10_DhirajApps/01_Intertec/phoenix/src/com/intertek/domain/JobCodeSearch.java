package com.intertek.domain;


public class JobCodeSearch extends Search
{

  private StringSearchField jobCode;
  private StringSearchField jobCodeDesc;
 
  private String searchForm;
  private String pageNo;
  private String inputFieldId;
  private String sortFlag;
  //START : #119240 : 22/06/09
  private String currentSortFlag;
  private String prevSortFlag;
  private String sortOrderFlag;
  // END : #119240 : 22/06/09

 public JobCodeSearch()
  {
    jobCode = new StringSearchField();
    jobCodeDesc = new StringSearchField();
    pageNo = new String();
  }   					

 
  	    public void setJobCode(StringSearchField jobCode)
  {
    this.jobCode = jobCode;
  }

  public StringSearchField getJobCode()
  {
    return jobCode;
  }

  public void setJobCodeDesc(StringSearchField jobCodeDesc)
  {
    this.jobCodeDesc = jobCodeDesc;
  }

  public StringSearchField getJobCodeDesc()
  {
    return jobCodeDesc;
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


	public String getSortFlag() {
		return sortFlag;
	}


	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
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

	//END : #119240 : 22/06/09
}
