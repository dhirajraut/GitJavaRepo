package com.intertek.domain;

import java.util.Date;

import com.intertek.util.Constants;

public class ContractSearch extends Search
{
  private StringSearchField contractCode;
  private StringSearchField contractStatus;
  private StringSearchField contractDescription;
   private String inputFieldId;
	private StringSearchField contract;
	private String searchType;
  private String rowNum;
  private String searchForm;
	private String pageNo;
	private String sortFlag;
	 private String cxcel="false";
	 private String buName;
  private String submitFlag;
  private Date asOfDate=null;
//START : #119240 
  private String currentSortFlag;
  private String prevSortFlag;
  private String sortOrderFlag;
  // END : #119240 
  
  
public ContractSearch()
  {
    contractCode = new StringSearchField(Constants.CONTAINS);
    contractStatus = new StringSearchField(Constants.CONTAINS);
    contractDescription = new StringSearchField(Constants.CONTAINS);
  }

  public StringSearchField getContractCode()
  {
    return contractCode;
  }

  public void setContractCode(StringSearchField contractCode)
  {
    this.contractCode = contractCode;
  }

  public StringSearchField getContractStatus()
  {
    return contractStatus;
  }

  public void setContractStatus(StringSearchField contractStatus)
  {
    this.contractStatus = contractStatus;
  }

  public StringSearchField getContractDescription()
  {
    return contractDescription;
  }

  public void setContractDescription(StringSearchField contractDescription)
  {
    this.contractDescription = contractDescription;
  }
  public String getInputFieldId()
{
return inputFieldId;
}

public void setInputFieldId(String inputFieldId)
{
this.inputFieldId = inputFieldId;
}

public String getRowNum()
{
return rowNum;
}

public void setRowNum(String rowNum)
{
this.rowNum = rowNum;
}

public void setSearchForm(String searchForm)
{ 
this.searchForm = searchForm;
}

public String getSearchForm()
{ 
return searchForm; 
}
	public void setContract(StringSearchField contract) {
		this.contract = contract;
	}

	public StringSearchField getContract() {
		return contract;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageNo() {
		return pageNo;
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

public String getBuName() {
	return buName;
}

public void setBuName(String buName) {
	this.buName = buName;
}

public Date getAsOfDate() {
	return asOfDate;
}

public void setAsOfDate(Date asOfDate) {
	this.asOfDate = asOfDate;
}
//START : #119240 
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
// END : #119240 
}
