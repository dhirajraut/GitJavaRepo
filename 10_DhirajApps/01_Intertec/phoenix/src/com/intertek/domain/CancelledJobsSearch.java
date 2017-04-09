package com.intertek.domain;

import com.intertek.pagination.Pagination;

public class CancelledJobsSearch extends Search {
	private Pagination pagination;
	private StringSearchField businessUnit;
	private StringSearchField branch;
	private DateSearchField nomDateFrom;
	private DateSearchField nomDateTo;
	private StringSearchField jobId;
	private String jxcel = "false";
	private String pageNo;
	private String currentSortFlag;
	private String prevSortFlag;
	private String sortOrderFlag;
	private String sortFlag;
	private String div1;
	private String div2;
	private StringSearchField status;
	private String dateFormat;
	private String[] cancelNoteDetails;

	public StringSearchField getStatus() {
		return status;
	}

	public void setStatus(StringSearchField status) {
		this.status = status;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getJxcel() {
		return jxcel;
	}

	public void setJxcel(String jxcel) {
		this.jxcel = jxcel;
	}

	public CancelledJobsSearch() {
		businessUnit = new StringSearchField();
		branch = new StringSearchField();
		nomDateFrom = new DateSearchField();
		nomDateTo = new DateSearchField();
		jobId = new StringSearchField();
		status = new StringSearchField();
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public StringSearchField getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(StringSearchField businessUnit) {
		this.businessUnit = businessUnit;
	}

	public StringSearchField getBranch() {
		return branch;
	}

	public void setBranch(StringSearchField branch) {
		this.branch = branch;
	}

	public DateSearchField getNomDateFrom() {
		return nomDateFrom;
	}

	public void setNomDateFrom(DateSearchField nomDateFrom) {
		this.nomDateFrom = nomDateFrom;
	}

	public DateSearchField getNomDateTo() {
		return nomDateTo;
	}

	public void setNomDateTo(DateSearchField nomDateTo) {
		this.nomDateTo = nomDateTo;
	}

	public StringSearchField getJobId() {
		return jobId;
	}

	public void setJobId(StringSearchField jobId) {
		this.jobId = jobId;
	}

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

	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
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

	public String[] getCancelNoteDetails() {
		return cancelNoteDetails;
	}

	public void setCancelNoteDetails(String[] cancelNoteDetails) {
		this.cancelNoteDetails = cancelNoteDetails;
	}

}
