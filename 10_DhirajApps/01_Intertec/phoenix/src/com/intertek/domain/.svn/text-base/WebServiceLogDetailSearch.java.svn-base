package com.intertek.domain;

import com.intertek.pagination.Pagination;


public class WebServiceLogDetailSearch extends WebServiceLogSearch{
	private String inOutBoundInd="";
	private String type="";
	private String status="";
	private String sortBy="createTime";
	private boolean sortDesc=true;
	
	public WebServiceLogDetailSearch(){
		Pagination pagination=new Pagination(0, 25, 1, 10);
		setPagination(pagination);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isSortDesc() {
		return sortDesc;
	}

	public void setSortDesc(boolean sortDesc) {
		this.sortDesc = sortDesc;
	}

	public String getInOutBoundInd() {
		return inOutBoundInd;
	}

	public void setInOutBoundInd(String inOutBoundInd) {
		this.inOutBoundInd = inOutBoundInd;
	}

	public Boolean getBooleanStatus(){
		if(";true;false;".indexOf((";"+status+";").toLowerCase())>=0){
			return new Boolean(status);
		}
		return null;
	}
}
