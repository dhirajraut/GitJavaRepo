package com.intertek.domain;


public class BusinessUnitSearch extends Search
{
  private StringSearchField desc;
  private StringSearchField name;
  private StringSearchField businessUnit;
  private String searchType;
  private String searchForm;
  private String pageNo;
  private String sortFlag;
  private String bxcel="false";
  private String submitFlag;
  private String inputFieldId;
  private StringSearchField country;
  private StringSearchField state;
  private StringSearchField city;
// 96509
  private StringSearchField address1;//96509
  
  //START : #119240 : 19/06/09
  private String currentSortFlag;
  private String prevSortFlag;
  private String sortOrderFlag;
  // END : #119240 : 19/06/09
  

  public BusinessUnitSearch()
  {
    desc = new StringSearchField();
    name = new StringSearchField();
	businessUnit = new StringSearchField();
	pageNo = new String();
	country = new StringSearchField();
    state = new StringSearchField();
    city = new StringSearchField();
// 96509
    address1 = new StringSearchField();//96509
  }

	public void setBusinessUnit(StringSearchField businessUnit) {
		this.businessUnit = businessUnit;
	}
	public StringSearchField getBusinessUnit() {
		return businessUnit;
	}




  public void setDesc(StringSearchField desc)
  {
    this.desc = desc;
  }

  public StringSearchField getDesc()
  {
    return desc;
  }

  public void setName(StringSearchField name)
  {
    this.name = name;
  }

  public StringSearchField getName()
  {
    return name;
  }
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchForm(String searchForm) {
		this.searchForm = searchForm;
	}

	public String getSearchForm() {
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
	public String getInputFieldId() {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId) {
		this.inputFieldId = inputFieldId;
	}
	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}
public String getBxcel(){
return bxcel;
}

public void setBxcel(String bxcel){
this.bxcel=bxcel;
}

public String getSubmitFlag(){
return submitFlag;
}

public void setSubmitFlag(String submitFlag){
this.submitFlag=submitFlag;
}

public StringSearchField getCountry() {
	return country;
}

public void setCountry(StringSearchField country) {
	this.country = country;
}

public StringSearchField getState() {
	return state;
}

public void setState(StringSearchField state) {
	this.state = state;
}

public StringSearchField getCity() {
	return city;
}

public void setCity(StringSearchField city) {
	this.city = city;
}
// 96509
public StringSearchField getAddress1() {
	return address1;
}

public void setAddress1(StringSearchField address1) {
	this.address1 = address1;
}
// 96509 end

//START : #119240 : 19/06/09
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
// END : #119240 : 19/06/09

}
