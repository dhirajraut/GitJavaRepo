package com.intertek.domain;


public class CountrySearch extends Search
{
 
  private StringSearchField stateCode;
  private StringSearchField name;
  private StringSearchField countryCodes;
   private StringSearchField countryDesc;
   private StringSearchField country;
   //start issue 115770
   private StringSearchField status;
   //End 115770
   private String searchForm;

	private String pageNo;

	private String inputFieldId;

	private String rowNum;
	private String countryCode;
	private String sortFlag;
   private String cxcel="false";
   private String submitFlag;
   // START : #119240 : 16/06/09
   private String currentSortFlag;
   private String prevSortFlag;
   private String sortOrderFlag;
   // END : #119240 : 16/06/09

  public CountrySearch()
  {
    stateCode = new StringSearchField();
    name = new StringSearchField();
    countryCodes = new StringSearchField();
	countryDesc = new StringSearchField();
	country = new StringSearchField();
	pageNo = new String();
	status = new StringSearchField();
  }


  public StringSearchField getCountry()
  {
    return country;
  }

  public void setCountry(StringSearchField country)
  {
    this.country =country;
  }
    public void setCountryCodes(StringSearchField countryCodes) {
		this.countryCodes = countryCodes;
	}

	public StringSearchField getCountryCodes() {
		return countryCodes;
	}

	 public void setCountryDesc(StringSearchField countryDesc) {
		this.countryDesc = countryDesc;
	}

	public StringSearchField getCountryDesc() {
		return countryDesc;
	}
 
    public void setName(StringSearchField name) {
		this.name = name;
	}

	public StringSearchField getName() {
		return name;
	}

	public void setStateCode(StringSearchField stateCode) {
		this.stateCode = stateCode;
	}

	public StringSearchField getStateCode() {
		return stateCode;
	}

   public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	} 
	public void setSearchForm(String searchForm) {
		this.searchForm = searchForm;
	}

	public String getSearchForm() {
		return searchForm;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageNo() {
		return pageNo;
	}

	public String getInputFieldId() {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId) {
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
	
public String getCxcel(){
return cxcel;
}

public void setCxcel(String cxcel){
this.cxcel=cxcel;
}

public String getSubmitFlag(){
return submitFlag;
}

public void setSubmitFlag(String submitFlag){
this.submitFlag=submitFlag;
}


public StringSearchField getStatus() {
	return status;
}


public void setStatus(StringSearchField status) {
	this.status = status;
}

//START : #119240 : 16/06/09
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
//END : #119240 : 16/06/09


}
