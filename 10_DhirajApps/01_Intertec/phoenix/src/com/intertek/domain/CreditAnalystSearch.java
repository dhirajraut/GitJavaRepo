package com.intertek.domain;


public class CreditAnalystSearch extends Search {
	private StringSearchField creditAnalyst;

	private StringSearchField name;

	private String searchType;

	private String searchForm;

	private String pageNo;

	private String inputFieldId;
	private String sortFlag;
	public CreditAnalystSearch() {
		name = new StringSearchField();
		creditAnalyst = new StringSearchField();

		pageNo = new String();
	}

	public void setName(StringSearchField name) {
		this.name = name;
	}

	public StringSearchField getName() {
		return name;
	}

	public void setCreditAnalyst(StringSearchField creditAnalyst) {
		this.creditAnalyst = creditAnalyst;
	}

	public StringSearchField getCreditAnalyst() {
		return creditAnalyst;
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
	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}
}