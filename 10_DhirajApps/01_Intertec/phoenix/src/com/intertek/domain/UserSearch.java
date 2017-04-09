package com.intertek.domain;


public class UserSearch extends Search
{
  private StringSearchField name;
  private StringSearchField firstName;
  private StringSearchField lastName;
	private String inputFieldId;
	private String div1;
	private String div2;
	private String searchForm;
	private String sortFlag;
	private String uxcel="false";
	private String submitFlag;
private StringSearchField countryCode;
//START : #119240 : 22/06/09
private String currentSortFlag;
private String prevSortFlag;
private String sortOrderFlag;
// END : #119240 : 22/06/09
  public UserSearch()
  {
    name = new StringSearchField();
    firstName = new StringSearchField();
    lastName = new StringSearchField();
	countryCode=new StringSearchField();
  }

  public void setName(StringSearchField name)
  {
    this.name = name;
  }

  public StringSearchField getName()
  {
    return name;
  }

  public void setFirstName(StringSearchField firstName)
  {
    this.firstName = firstName;
  }

  public StringSearchField getFirstName()
  {
    return firstName;
  }

  public void setLastName(StringSearchField lastName)
  {
    this.lastName = lastName;
  }

  public StringSearchField getLastName()
  {
    return lastName;
  }
public StringSearchField getCountryCode()
  {
    return countryCode;
  }

  public void setCountryCode(StringSearchField countryCode)
  {
    this.countryCode = countryCode;
  }
	public String getInputFieldId() {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId) {
		this.inputFieldId = inputFieldId;
	}
	public String getDiv1() {
		return div1;
	}

	public void setDiv1(String div1) {
		this.div1 = div1;
	}

	public String getDiv2() {
		return div2;
	}

	public void setDiv2(String div2) {
		this.div2 = div2;
	}
public String getSearchForm()
{
return searchForm;
}

public void setSearchForm(String searchForm)
{
this.searchForm = searchForm;
}

public String getSortFlag() {
	return sortFlag;
}

public void setSortFlag(String sortFlag) {
	this.sortFlag = sortFlag;
}

public String getUxcel()
{
return uxcel;
}
public void setUxcel(String uxcel)
{
this.uxcel=uxcel;
}

public String getSubmitFlag()
{
return submitFlag;
}
public void setSubmitFlag(String submitFlag)
{
this.submitFlag=submitFlag;
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
