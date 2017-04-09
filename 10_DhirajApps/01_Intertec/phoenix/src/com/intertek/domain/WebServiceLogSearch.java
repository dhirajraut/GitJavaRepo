package com.intertek.domain;

import java.util.Calendar;
import java.util.Date;

import com.intertek.pagination.Pagination;
import com.intertek.util.DateUtil;


public class WebServiceLogSearch extends Search{
	private String fromDate="";
	private String toDate="";
	private String key="";
	
	public WebServiceLogSearch(){
		Pagination pagination=new Pagination(0, 15, 1, 10);
		setPagination(pagination);
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public Date getFromDate(String dateFormat){
		Date aDate=DateUtil.parseDate(getFromDate(), dateFormat);
		if(aDate==null){
			aDate=Calendar.getInstance().getTime();
			setFromDate(DateUtil.formatDate(aDate, dateFormat));
			aDate=DateUtil.parseDate(getFromDate(), dateFormat);
		}
		return aDate;
	}
	
	public Date getToDate(String dateFormat){
		Date aDate=DateUtil.parseDate(getToDate()+" 23:59:59", dateFormat+" HH:mm:ss");
		if(aDate==null){
			aDate=Calendar.getInstance().getTime();
			setToDate(DateUtil.formatDate(aDate, dateFormat));
			aDate=DateUtil.parseDate(getToDate()+" 23:59:59", dateFormat+" HH:mm:ss");
		}
		return aDate;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
