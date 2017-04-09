package com.intertek.domain;
import java.util.Date;

import com.intertek.util.Constants;



	public class TestSearch extends Search
	{
	  
	  private StringSearchField productGroup;
	  private StringSearchField criteria1;
	  private StringSearchField criteria2;
	  private StringSearchField testSearch;
	  private String searchType;
	  private String searchForm;
	  private String pageNo;
	  private String inputFieldId;
	  private String rowNum;
	  private String chosenContracts;
	  private String chosenTestIds;
	  private String divName1;
	  private String divName2;
	  private String submitFlag;
	  private String sortFlag;
	  private Date nominationDate;
	  private String contractCode;
	  private String jobContractId;
	  
	  public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public TestSearch()
	  {
	    
		  productGroup = new StringSearchField(Constants.CONTAINS);
		  criteria1 = new StringSearchField(Constants.CONTAINS);
		  criteria1.setValue(Constants.BOTH);
		  criteria2 = new StringSearchField(Constants.CONTAINS);
		  criteria2.setValue(Constants.BOTH);
		  testSearch = new StringSearchField(Constants.CONTAINS);
	   
		pageNo = new String();
		rowNum = new String();
	  }
	  
	public String getInputFieldId() {
		return inputFieldId;
	}
	public void setInputFieldId(String inputFieldId) {
		this.inputFieldId = inputFieldId;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(String searchForm) {
		this.searchForm = searchForm;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}



	public StringSearchField getCriteria1() {
		return criteria1;
	}

	public void setCriteria1(StringSearchField criteria1) {
		this.criteria1 = criteria1;
	}

	public StringSearchField getCriteria2() {
		return criteria2;
	}

	public void setCriteria2(StringSearchField criteria2) {
		this.criteria2 = criteria2;
	}

	public StringSearchField getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(StringSearchField productGroup) {
		this.productGroup = productGroup;
	}

	public StringSearchField getTestSearch() {
		return testSearch;
	}

	public void setTestSearch(StringSearchField testSearch) {
		this.testSearch = testSearch;
	}

	public String getChosenContracts() {
		return chosenContracts;
	}

	public void setChosenContracts(String chosenContracts) {
		this.chosenContracts = chosenContracts;
	}

	public String getChosenTestIds() {
		return chosenTestIds;
	}

	public void setChosenTestIds(String chosenTestIds) {
		this.chosenTestIds = chosenTestIds;
	}

	public String getDivName1() {
		return divName1;
	}

	public void setDivName1(String divName1) {
		this.divName1 = divName1;
	}

	public String getDivName2() {
		return divName2;
	}

	public void setDivName2(String divName2) {
		this.divName2 = divName2;
	}

	public String getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}

	public Date getNominationDate() {
		return nominationDate;
	}

	public void setNominationDate(Date nominationDate) {
		this.nominationDate = nominationDate;
	}
	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}

	public String getJobContractId() {
		return jobContractId;
	}

	public void setJobContractId(String jobContractId) {
		this.jobContractId = jobContractId;
	}
}
