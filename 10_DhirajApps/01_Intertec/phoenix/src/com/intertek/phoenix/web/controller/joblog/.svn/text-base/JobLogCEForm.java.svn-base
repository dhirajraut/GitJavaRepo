/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */

package com.intertek.phoenix.web.controller.joblog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

import com.intertek.phoenix.entity.Period;
import com.intertek.domain.Search;
import com.intertek.meta.dropdown.Field;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.util.ArrayMapGrid;
import com.intertek.phoenix.web.controller.joblog.JobLogCESearchResult;

/**
 * The Class JobLogCEForm used to get the search criteria 
 * from the GUI  
 * @version 1.0 May 25, 2009
 * @author Patni *         
 */

public class JobLogCEForm extends Search {

	private String requestAction;
	private int pageNumber;
	private String showAllExcel;
	private String sortOrderFlag;
	private String sortField;
	private String currentSortFlag;
	private String prevSortFlag;
	private long jobSearchCriteriaId = -1;
	private String jobSearchCriteriaName = "";
	private String tabNavigationFrom;
	private String tabNavigationTo;
	private int tabId = 0;
	private String dateFormat = "";

	private ArrayMapGrid searchResultGrid;
	private String[] orderStatus;
	private String[] taskOperationalStatus;

	/** The project operational status. */
	private String[] projectOperationalStatus;
	private String[] taskManagerId;
	private String[] projectManagerId;
	private String[] salesRep;
	private String[] taskOwningOrg;
	private String[] projectOwningOrg;
	private Period taskReadyDate;
	private Period promisedCompletionDate;
	private String stream;
	private Period actualReadyDate;
	private SearchCriteria taskName;
	private Period customerReadyDate;
	private SearchCriteria taskDescription;
	private String[] serviceOfferingParentName;
	private SearchCriteria taskComments;
	private String[] serviceTypeCode;
	private SearchCriteria modelNumber;
	private SearchCriteria taskSampleDescription;
	private SearchCriteria jobDescription;
	private SearchCriteria serviceLocation;
	private SearchCriteria customerName;
	private SearchCriteria contract;
	private SearchCriteria customerNumber;
	private SearchCriteria orderAmount;
	private SearchCriteria projectNumber;
	private SearchCriteria currentMonthBilled;
	private SearchCriteria quoteNumber;
	private SearchCriteria pastDue;
	private SearchCriteria orderNumber;
	private SearchCriteria currentMonth;
	private SearchCriteria ecsOrderNumber;
	private SearchCriteria currentMonthPlus1;
	private SearchCriteria currentMonthPlus2;
	private SearchCriteria futureMonth;
	private Period quoteDate;
	private Period taskStartDate;
	private Period actualStartDate;
	private Period orderDate;
	private Period deliveryShipDate;
	private SearchCriteria poNumber;
	private Period taskCompletionDate;
	private SearchCriteria rowTotalOfRev;
	private JobLogCESearchResult[] result = null;

	private List<Field> numericOperator;
	private List<Field> stringOperator;
	private List<Field> operationalStatusList;
	private List<Field> orderStatusList;
	private List<Field> taskOperationalStatusList;
	private List<Field> serviceTypeList;

	public JobLogCEForm() {
		orderStatus = new String[] { "" };
		taskOperationalStatus = new String[] { "" };
		projectOperationalStatus = new String[] { "" };
		taskManagerId = new String[20];
		taskManagerId[0] = "";
		projectManagerId = new String[20];
		projectManagerId[0] = "";
		salesRep = new String[20];
		salesRep[0] = "";
		taskOwningOrg = new String[20];
		taskOwningOrg[0] = "";
		projectOwningOrg = new String[20];
		projectOwningOrg[0] = "";
		taskReadyDate = new Period();
		promisedCompletionDate = new Period();
		actualReadyDate = new Period();
		taskName = new SearchCriteria();
		customerReadyDate = new Period();
		taskDescription = new SearchCriteria();
		serviceOfferingParentName = new String[20];
		serviceOfferingParentName[0] = "";
		taskComments = new SearchCriteria();
		serviceTypeCode = new String[] { "" };
		modelNumber = new SearchCriteria();
		taskSampleDescription = new SearchCriteria();
		jobDescription = new SearchCriteria();
		serviceLocation = new SearchCriteria();
		customerName = new SearchCriteria();
		contract = new SearchCriteria();
		customerNumber = new SearchCriteria();
		orderAmount = new SearchCriteria();
		projectNumber = new SearchCriteria();
		currentMonthBilled = new SearchCriteria();
		quoteNumber = new SearchCriteria();
		pastDue = new SearchCriteria();
		orderNumber = new SearchCriteria();
		currentMonth = new SearchCriteria();
		ecsOrderNumber = new SearchCriteria();
		currentMonthPlus1 = new SearchCriteria();
		currentMonthPlus2 = new SearchCriteria();
		futureMonth = new SearchCriteria();
		quoteDate = new Period();
		taskStartDate = new Period();
		actualStartDate = new Period();
		orderDate = new Period();
		deliveryShipDate = new Period();
		poNumber = new SearchCriteria();
		taskCompletionDate = new Period();
		rowTotalOfRev = new SearchCriteria();

		Pagination pagination = new Pagination(0, 0, 1, 0);
		setPagination(pagination);
		this.setTabId(0);
		this.setTabNavigationTo("searchCriteria");
		this.requestAction = "new search";

		ArrayList<String> orderStatus = new ArrayList<String>();
		String[] os1 = new String[] { "s1" };
		orderStatus.add("s1");
		this.setOrderStatus(os1);

	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getShowAllExcel() {
		return showAllExcel;
	}

	public void setShowAllExcel(String showAllExcel) {
		this.showAllExcel = showAllExcel;
	}

	public String getSortOrderFlag() {
		return sortOrderFlag;
	}

	public void setSortOrderFlag(String sortOrderFlag) {
		this.sortOrderFlag = sortOrderFlag;
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

	public long getJobSearchCriteriaId() {
		return jobSearchCriteriaId;
	}

	public void setJobSearchCriteriaId(long jobSearchCriteriaId) {
		this.jobSearchCriteriaId = jobSearchCriteriaId;
	}

	public String getJobSearchCriteriaName() {
		return jobSearchCriteriaName;
	}

	public void setJobSearchCriteriaName(String jobSearchCriteriaName) {
		this.jobSearchCriteriaName = jobSearchCriteriaName;

	}

	public String[] getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String[] orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String[] getTaskOperationalStatus() {
		return taskOperationalStatus;
	}

	public void setTaskOperationalStatus(String[] taskOperationalStatus) {
		this.taskOperationalStatus = taskOperationalStatus;
	}

	public String[] getProjectOperationalStatus() {
		return projectOperationalStatus;
	}

	public void setProjectOperationalStatus(String[] projectOperationalStatus) {
		this.projectOperationalStatus = projectOperationalStatus;
	}

	public String[] getTaskManagerId() {
		return taskManagerId;
	}

	public void setTaskManagerId(String[] taskManagerId) {
		this.taskManagerId = taskManagerId;
	}

	public String[] getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(String[] projectManagerId) {
		this.projectManagerId = projectManagerId;
	}

	public String[] getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(String[] salesRep) {
		this.salesRep = salesRep;
	}

	public String[] getTaskOwningOrg() {
		return taskOwningOrg;
	}

	public void setTaskOwningOrg(String[] taskOwningOrg) {
		this.taskOwningOrg = taskOwningOrg;
	}

	public String[] getProjectOwningOrg() {
		return projectOwningOrg;
	}

	public void setProjectOwningOrg(String[] projectOwningOrg) {
		this.projectOwningOrg = projectOwningOrg;
	}

	public Period getTaskReadyDate() {
		return taskReadyDate;
	}

	public void setTaskReadyDate(Period taskReadyDate) {
		this.taskReadyDate = taskReadyDate;
	}

	public Period getPromisedCompletionDate() {
		return promisedCompletionDate;
	}

	public void setPromisedCompletionDate(Period promissedComplaintDate) {
		this.promisedCompletionDate = promissedComplaintDate;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Period getActualReadyDate() {
		return actualReadyDate;
	}

	public void setActualReadyDate(Period actualReadyDate) {
		this.actualReadyDate = actualReadyDate;
	}

	public SearchCriteria getTaskName() {
		return taskName;
	}

	public void setTaskName(SearchCriteria taskName) {
		this.taskName = taskName;
	}

	public Period getCustomerReadyDate() {
		return customerReadyDate;
	}

	public void setCustomerReadyDate(Period customerReadyDate) {
		this.customerReadyDate = customerReadyDate;
	}

	public SearchCriteria getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(SearchCriteria taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String[] getServiceOfferingParentName() {
		return serviceOfferingParentName;
	}

	public void setServiceOfferingParentName(String[] serviceOfferingParentName) {
		this.serviceOfferingParentName = serviceOfferingParentName;
	}

	public SearchCriteria getTaskComments() {
		return taskComments;
	}

	public void setTaskComments(SearchCriteria taskComments) {
		this.taskComments = taskComments;
	}

	public String[] getServiceTypeCode() {
		return serviceTypeCode;
	}

	public void setServiceTypeCode(String[] serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

	public SearchCriteria getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(SearchCriteria modelNumber) {
		this.modelNumber = modelNumber;
	}

	public SearchCriteria getTaskSampleDescription() {
		return taskSampleDescription;
	}

	public void setTaskSampleDescription(SearchCriteria taskSampleDescription) {
		this.taskSampleDescription = taskSampleDescription;
	}

	public SearchCriteria getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(SearchCriteria jobDescription) {
		this.jobDescription = jobDescription;
	}

	public SearchCriteria getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceLocation(SearchCriteria serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public SearchCriteria getCustomerName() {
		return customerName;
	}

	public void setCustomerName(SearchCriteria customerName) {
		this.customerName = customerName;
	}

	public SearchCriteria getContract() {
		return contract;
	}

	public void setContract(SearchCriteria contract) {
		this.contract = contract;
	}

	public SearchCriteria getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(SearchCriteria customerNumber) {
		this.customerNumber = customerNumber;
	}

	public SearchCriteria getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(SearchCriteria orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getProjectNumber() {
		return projectNumber.getValue();
	}

	public void setProjectNumber(SearchCriteria projectNumber) {
		this.projectNumber = projectNumber;
	}

	public void setProjectNumber(String value) {
		this.projectNumber.setValue(value);
	}

	public SearchCriteria getCurrentMonthBilled() {
		return currentMonthBilled;
	}

	public void setCurrentMonthBilled(SearchCriteria currentMonthBilled) {
		this.currentMonthBilled = currentMonthBilled;
	}

	public String getQuoteNumber() {
		return quoteNumber.getValue();
	}

	public void setQuoteNumber(SearchCriteria quoteNumber) {
		this.quoteNumber = quoteNumber;
	}

	public void setQuoteNumber(String quoteNumber) {
		this.quoteNumber.setValue(quoteNumber);
	}

	public SearchCriteria getPastDue() {
		return pastDue;
	}

	public void setPastDue(SearchCriteria pastDue) {
		this.pastDue = pastDue;
	}

	public String getOrderNumber() {
		return orderNumber.getValue();
	}

	public void setOrderNumber(SearchCriteria orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setOrderNumber(String value) {
		this.orderNumber.setValue(value);
	}

	public SearchCriteria getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(SearchCriteria currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getEcsOrderNumber() {
		return ecsOrderNumber.getValue();
	}

	public void setEcsOrderNumber(SearchCriteria ecsOrderNumber) {
		this.ecsOrderNumber = ecsOrderNumber;
	}

	public void setEcsOrderNumber(String value) {
		this.ecsOrderNumber.setValue(value);
	}

	public SearchCriteria getCurrentMonthPlus1() {
		return currentMonthPlus1;
	}

	public void setCurrentMonthPlus1(SearchCriteria currentMonthPlus1) {
		this.currentMonthPlus1 = currentMonthPlus1;
	}

	public SearchCriteria getCurrentMonthPlus2() {
		return currentMonthPlus2;
	}

	public void setCurrentMonthPlus2(SearchCriteria currentMonthPlus2) {
		this.currentMonthPlus2 = currentMonthPlus2;
	}

	public SearchCriteria getFutureMonth() {
		return futureMonth;
	}

	public void setFutureMonth(SearchCriteria futureMonth) {
		this.futureMonth = futureMonth;
	}

	public Period getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(Period quoteDate) {
		this.quoteDate = quoteDate;
	}

	public Period getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Period taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public Period getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Period actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Period getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Period orderDate) {
		this.orderDate = orderDate;
	}

	public Period getDeliveryShipDate() {
		return deliveryShipDate;
	}

	public void setDeliveryShipDate(Period deliveryShipDate) {
		this.deliveryShipDate = deliveryShipDate;
	}

	public SearchCriteria getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(SearchCriteria poNumber) {
		this.poNumber = poNumber;
	}

	public Period getTaskCompletionDate() {
		return taskCompletionDate;
	}

	public void setTaskCompletionDate(Period taskCompletionDate) {
		this.taskCompletionDate = taskCompletionDate;
	}

	public SearchCriteria getRowTotalOfRev() {
		return rowTotalOfRev;
	}

	public void setRowTotalOfRev(SearchCriteria rowTotalOfRev) {
		this.rowTotalOfRev = rowTotalOfRev;
	}

	public String getTabNavigationFrom() {
		return tabNavigationFrom;
	}

	public void setTabNavigationFrom(String tabNavigationFrom) {
		this.tabNavigationFrom = tabNavigationFrom;
	}

	public String getTabNavigationTo() {
		return tabNavigationTo;
	}

	public void setTabNavigationTo(String tabNavigationTo) {
		this.tabNavigationTo = tabNavigationTo;
	}

	public int getTabId() {
		return tabId;
	}

	public void setTabId(int tabId) {
		this.tabId = tabId;
	}

	public JobLogCESearchResult[] getResult() {
		return result;
	}

	public void setResult(JobLogCESearchResult[] result) {
		this.result = result;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public List<Field> getNumericOperator() {
		// return refService.getFilterOpFields();
		return this.numericOperator;
	}

	public void setNumericOperator(List<Field> numericOperator) {
		this.numericOperator = numericOperator;
	}

	public List<Field> getStringOperator() {
		// return refService.getFilterOpFields();
		return this.stringOperator;
	}

	public void setStringOperator(List<Field> stringOperator) {
		this.stringOperator = stringOperator;
	}

	public List<Field> getOperationalStatusList() {
		return this.operationalStatusList;
	}

	public void setOperationalStatusList(List<Field> operationalStatusList) {
		this.operationalStatusList = operationalStatusList;
	}

	public List<Field> getOrderStatusList() {
		return this.orderStatusList;
	}

	public void setOrderStatusList(List<Field> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}

	public List<Field> getTaskOperationalStatusList() {
		return taskOperationalStatusList;
	}

	public void setTaskOperationalStatusList(
			List<Field> taskOperationalStatusList) {
		this.taskOperationalStatusList = taskOperationalStatusList;
	}

	public List<Field> getServiceTypeList() {

		// return refService.getServiceType();
		List<Field> params = new ArrayList<Field>();
		Field field = new Field();
		field.setName("SAF-LOW VOLTAGE DIRECTIVE");
		field.setValue("SAF-LOW VOLTAGE DIRECTIVE");
		params.add(field);
		field = new Field();
		field.setName("SAF-ATEX/EX Directive");
		field.setValue("SAF-ATEX/EX Directive");
		params.add(field);
		field = new Field();
		field.setName("SAF-Prod Safety");
		field.setValue("SAF-Prod Safety");
		params.add(field);
		serviceTypeList = params;
		return serviceTypeList;
	}

	public void setServiceTypeList(List<Field> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	/**
	 * Name :setDateCriteria Date :May 25, 2009 purpose :to set the Date related
	 * fields to criteria list
	 * 
	 * @return
	 */
	private void setDateCriteria(Period fld, String columnName,
			List<SearchCriteria> list) {
		SearchCriteria criteria = null;
		if (fld.getFrom() != null && fld.getTo() != null) {

			criteria = new SearchCriteria(columnName, "'"
					+ dateToString(fld.getFrom()) + "'", " >= ");
			list.add(criteria);
			criteria = new SearchCriteria(columnName, "'"
					+ dateToString(fld.getTo()) + "'", " <= ");
			list.add(criteria);
		} else if (fld.getFrom() != null) {

			criteria = new SearchCriteria(columnName, "'"
					+ dateToString(fld.getFrom()) + "'", " = ");
			list.add(criteria);
		} else if (fld.getTo() != null) {

			criteria = new SearchCriteria(columnName, "'"
					+ dateToString(fld.getTo()) + "'", " = ");
			list.add(criteria);
		}		
	}

	/**
	 * Sets the multiple values.
	 * 
	 * @param fld
	 * 
	 * @param columnName
	 *            the column name
	 * @param list
	 *            the list
	 */
	private void setMultipleValues(String[] fld, String columnName,
			List<SearchCriteria> list) {
		StringBuffer strbuf = new StringBuffer();
		if (fld != null && fld.length > 0) {
			for (String value : fld) {
				if (value != null && value.trim().length() > 0) {
					strbuf.append(",'");
					strbuf.append(value);
					strbuf.append("'");
				}
			}
			if (strbuf.length() > 0) {
				strbuf.setCharAt(0, ' ');
				SearchCriteria criteria = new SearchCriteria(columnName, "("
						+ strbuf.toString() + ")", " IN ");
				list.add(criteria);
			}
		}
	}

	/**
	 * Sets the simple criteria.
	 * 
	 * @param fld
	 *            the fld
	 * @param columnName
	 *            the column name
	 * @param list
	 *            the list
	 */
	private void setSimpleCriteria(SearchCriteria fld, String columnName,
			List<SearchCriteria> list) {
		SearchCriteria tmpField=new SearchCriteria();
		if (fld.getValue() != null && fld.getValue().trim().length() > 0) {
			tmpField.setName(fld.getName());
			tmpField.setOp(" "+fld.getOp()+" ");
			tmpField.setValue("'"+fld.getValue()+"'");
			fld.setName(columnName);
			list.add(tmpField);
		}
	}

	/**
	 * Name :dateToString Date :May 25, 2009 purpose :to convert timestamp to
	 * string
	 * 
	 * @param dt
	 *            Timestamp type date
	 * @return String
	 */
	String dateToString(Timestamp dt) {
		String result = "";
		try {
			DateFormat dateFormat1 = new SimpleDateFormat(this.dateFormat);
			result = dateFormat1.format(dt);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	/**
	 * Name :stringToDate Date :May 25, 2009 purpose :to convert from string to
	 * timestamp
	 * 
	 * @return
	 */
	Timestamp stringToDate(String strDt) {
		Timestamp ts = null;

		if (strDt != null && !"".equals(strDt.trim())) {
			try {
				DateFormat dateFormat1 = new SimpleDateFormat(this.dateFormat);

				java.util.Date dt = dateFormat1.parse(strDt);
				ts = new Timestamp(dt.getTime());
			} catch (Exception e) {
				ts = null;
			}
		}
		return ts;
	}

	public String getRequestAction() {
		return requestAction;
	}

	public void setRequestAction(String requestAction) {
		this.requestAction = requestAction;
	}

	public String getQuoteDateFrom() {
		return dateToString(quoteDate.getFrom());
	}

	public void setQuoteDateFrom(String qDate) {
		quoteDate.setFrom(stringToDate(qDate));
	}

	public String getQuoteDateTo() {
		return dateToString(quoteDate.getTo());
	}

	public void setQuoteDateTo(String qDate) {
		quoteDate.setTo(stringToDate(qDate));
	}

	public String getTaskStartDateFrom() {
		return dateToString(taskStartDate.getFrom());
	}

	public void setTaskStartDateFrom(String qDate) {
		taskStartDate.setFrom(stringToDate(qDate));
	}

	public String getTaskStartDateTo() {
		return dateToString(taskStartDate.getTo());
	}

	public void setTaskStartDateTo(String taskDate) {
		taskStartDate.setTo(stringToDate(taskDate));
	}

	public String getActualStartDateFrom() {
		return dateToString(actualStartDate.getFrom());
	}

	public void setActualStartDateFrom(String aDate) {
		this.actualStartDate.setFrom(stringToDate(aDate));
	}

	public String getActualStartDateTo() {
		return this.dateToString(actualStartDate.getTo());
	}

	public void setActualStartDateTo(String aDate) {
		this.actualStartDate.setTo(stringToDate(aDate));
	}

	public String getOrderDateFrom() {
		return dateToString(orderDate.getFrom());
	}

	public void setOrderDateFrom(String aDate) {
		this.orderDate.setFrom(stringToDate(aDate));
	}

	public String getOrderDateTo() {
		return this.dateToString(orderDate.getTo());
	}

	public void setOrderDateTo(String aDate) {
		this.orderDate.setTo(stringToDate(aDate));
	}

	public String getDeliveryShipDateFrom() {
		return dateToString(deliveryShipDate.getFrom());
	}

	public void setDeliveryShipDateFrom(String aDate) {
		this.deliveryShipDate.setFrom(stringToDate(aDate));
	}

	public String getDeliveryShipDateTo() {
		return this.dateToString(deliveryShipDate.getTo());
	}

	public void setDeliveryShipDateTo(String aDate) {
		this.deliveryShipDate.setTo(stringToDate(aDate));
	}

	public String getTaskReadyDateFrom() {
		return dateToString(taskReadyDate.getFrom());
	}

	public void setTaskReadyDateFrom(String aDate) {
		this.taskReadyDate.setFrom(stringToDate(aDate));
	}

	public String getTaskReadyDateTo() {
		return this.dateToString(taskReadyDate.getTo());
	}

	public void setTaskReadyDateTo(String aDate) {
		this.taskReadyDate.setTo(stringToDate(aDate));
	}

	public String getPromisedCompletionDateFrom() {
		return dateToString(promisedCompletionDate.getFrom());
	}

	public void setPromisedCompletionDateFrom(String aDate) {
		this.promisedCompletionDate.setFrom(stringToDate(aDate));
	}

	public String getPromisedCompletionDateTo() {
		return this.dateToString(promisedCompletionDate.getTo());
	}

	public void setPromisedCompletionDateTo(String aDate) {
		this.promisedCompletionDate.setTo(stringToDate(aDate));
	}

	public String getActualReadyDateFrom() {
		return dateToString(actualReadyDate.getFrom());
	}

	public void setActualReadyDateFrom(String aDate) {
		this.actualReadyDate.setFrom(stringToDate(aDate));
	}

	public String getActualReadyDateTo() {
		return this.dateToString(actualReadyDate.getTo());
	}

	public void setActualReadyDateTo(String aDate) {
		this.actualReadyDate.setTo(stringToDate(aDate));
	}

	public String getCustomerReadyDateFrom() {
		return dateToString(customerReadyDate.getFrom());
	}

	public void setCustomerReadyDateFrom(String aDate) {
		this.customerReadyDate.setFrom(stringToDate(aDate));
	}

	public String getCustomerReadyDateTo() {
		return this.dateToString(customerReadyDate.getTo());
	}

	public void setCustomerReadyDateTo(String aDate) {
		this.customerReadyDate.setTo(stringToDate(aDate));
	}

	public String getTaskCompletionDateFrom() {
		return dateToString(taskCompletionDate.getFrom());
	}

	public void setTaskCompletionDateFrom(String aDate) {
		this.taskCompletionDate.setFrom(stringToDate(aDate));
	}

	public String getTaskCompletionDateTo() {
		return this.dateToString(taskCompletionDate.getTo());
	}

	public void setTaskCompletionDateTo(String aDate) {
		this.taskCompletionDate.setTo(stringToDate(aDate));
	}

	/**
	 * Gets the search criteria list.
	 * 
	 * @return the search criteria list
	 */
	public List<SearchCriteria> getSearchCriteriaList() {
		List<SearchCriteria> search = new ArrayList<SearchCriteria>();
		SearchCriteria criteria = null;
		setDateCriteria(taskReadyDate, "taskReadyDate", search);
		setDateCriteria(promisedCompletionDate, "promisedCompletionDate",
				search);
		setDateCriteria(actualReadyDate, "actualReadyDate", search);
		setDateCriteria(customerReadyDate, "customerReadyDate", search);

		if (stream != null && stream.trim().length() > 0) {
			criteria = new SearchCriteria("jo.businessStreamCode", "'" + stream
					+ "'", "=");
			search.add(criteria);
			criteria = null;
		}
		setMultipleValues(orderStatus, "orderStatus", search);
		setMultipleValues(taskOperationalStatus, "taskOperationalStatus",
				search);
		setMultipleValues(projectOperationalStatus, "projectOperationalStatus",
				search);
		setMultipleValues(taskManagerId, "taskManager", search);
		setMultipleValues(projectManagerId, "jo.projectManagerName", search);
		setMultipleValues(salesRep, "jo.salesPersonName", search);
		setMultipleValues(taskOwningOrg, "taskOwningOrg", search);
		setMultipleValues(projectOwningOrg, "projectOwningOrg", search);
		setSimpleCriteria(taskName, "taskName", search);
		setSimpleCriteria(taskDescription, "taskDescription", search);
		setMultipleValues(serviceOfferingParentName,
				"serviceOfferingParentName", search);
		setMultipleValues(serviceTypeCode, "serviceTypeCode", search);
		setSimpleCriteria(taskComments, "taskComments", search);
		setSimpleCriteria(modelNumber, "jo.modelNumber", search);
		setSimpleCriteria(taskSampleDescription, "taskSampleDescription",
				search);
		setSimpleCriteria(jobDescription, "jobDescription", search);
		setSimpleCriteria(serviceLocation, "serviceLocation", search);
		setSimpleCriteria(customerName, "customerName", search);
		setSimpleCriteria(contract, "contractNumber", search);
		setSimpleCriteria(customerNumber, "customerNumber", search);
		setSimpleCriteria(orderAmount, "orderAmt", search);

		projectNumber.setOp(" = ");
		setSimpleCriteria(projectNumber, "jo.projectNumber", search);

		quoteNumber.setOp("=");
		setSimpleCriteria(quoteNumber, "quoteNumber", search);
		orderNumber.setOp("=");
		setSimpleCriteria(orderNumber, "orderNumber", search);
		ecsOrderNumber.setOp("=");
		setSimpleCriteria(ecsOrderNumber, "q.ecsOrderNumber", search);
		setSimpleCriteria(currentMonthBilled, "currentMonthBilled", search);
		setSimpleCriteria(pastDue, "pastDue", search);
		setSimpleCriteria(currentMonth, "currentMonth", search);
		setSimpleCriteria(currentMonthPlus1, "currentMonthPlus1", search);
		setSimpleCriteria(currentMonthPlus2, "currentMonthPlus2", search);
		setSimpleCriteria(futureMonth, "futureMonths", search);
		setSimpleCriteria(rowTotalOfRev, "rowTotalOfRev", search);
		setSimpleCriteria(poNumber, "poNumber", search);
		setDateCriteria(quoteDate, "quoteDate", search);
		setDateCriteria(taskStartDate, "taskStartDate", search);
		setDateCriteria(taskCompletionDate, "taskCompletionDate", search);
		setDateCriteria(actualStartDate, "actualStart", search);
		setDateCriteria(orderDate, "orderDate", search);
		setDateCriteria(deliveryShipDate, "deliverableShipDate", search);
		return search;
	}

	public int getPageNumber() {

		if (getPagination() != null) {
			return getPagination().getCurrentPageNum();
		} else {
			return this.pageNumber;
		}
	}

	public void setPageNumber(int pageNumber) {
		if (getPagination() != null) {
			getPagination().setCurrentPageNum(pageNumber);
		}
		this.pageNumber = pageNumber;
	}

	public ArrayMapGrid getSearchResultGrid() {
		return searchResultGrid;
	}

	public void setSearchResultGrid(ArrayMapGrid searchResultGrid) {
		this.searchResultGrid = searchResultGrid;
		populateResultArray();
	}

	/**
	 * Populate search result
	 * 
	 */
	private void populateResultArray() {
		if (searchResultGrid != null) {
			result = new JobLogCESearchResult[searchResultGrid.getRecordCount()];
			for (int i = 0; i < searchResultGrid.getRecordCount(); i++) {
				if (searchResultGrid.moveNext()) {
					result[i] = new JobLogCESearchResult();
					result[i].setValues(searchResultGrid);
				}
			}
		}
	}
}
