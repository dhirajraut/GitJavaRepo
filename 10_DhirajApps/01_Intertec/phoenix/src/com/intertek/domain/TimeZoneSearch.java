package com.intertek.domain;


public class TimeZoneSearch extends Search {

	private StringSearchField timeZone;

	private StringSearchField timeZoneDesc;

	private String searchType;

	private String searchForm;

	private String pageNo;

	private String inputFieldId;

	private String div1;

	private String div2;
	private String sortFlag;
	// START : #119240 : 19/06/09
	   private String currentSortFlag;
	   private String prevSortFlag;
	   private String sortOrderFlag;
	   // END : #119240 : 19/06/09

	public TimeZoneSearch() {
		timeZone = new StringSearchField();
		timeZoneDesc = new StringSearchField();

		pageNo = new String();
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

	public StringSearchField getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(StringSearchField timeZone) {
		this.timeZone = timeZone;
	}

	public StringSearchField getTimeZoneDesc() {
		return timeZoneDesc;
	}

	public void setTimeZoneDesc(StringSearchField timeZoneDesc) {
		this.timeZoneDesc = timeZoneDesc;
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
	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}

	// START : #119240 : 19/06/09
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
