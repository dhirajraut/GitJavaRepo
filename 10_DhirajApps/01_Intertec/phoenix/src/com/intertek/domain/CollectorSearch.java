package com.intertek.domain;


public class CollectorSearch extends Search {
	private StringSearchField collector;

	private StringSearchField name;

	private String searchType;

	private String searchForm;

	private String pageNo;

	private String inputFieldId;
	private String sortFlag;

	public CollectorSearch() {
		name = new StringSearchField();
		collector = new StringSearchField();

		pageNo = new String();
	}

	public void setName(StringSearchField name) {
		this.name = name;
	}

	public StringSearchField getName() {
		return name;
	}

	public void setCollector(StringSearchField collector) {
		this.collector = collector;
	}

	public StringSearchField getCollector() {
		return collector;
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