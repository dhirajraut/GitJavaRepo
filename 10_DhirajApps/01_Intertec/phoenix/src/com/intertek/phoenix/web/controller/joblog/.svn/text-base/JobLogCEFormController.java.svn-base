/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */

package com.intertek.phoenix.web.controller.joblog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.exception.ServiceException;
import com.intertek.export.Exporter;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.export.template.JobLogExcel;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.ArrayMapGrid;
import com.intertek.web.controller.BaseSimpleFormController;

/**
 * The Class JobLogCEFormController used to get the entire search results of the
 * given search criteria.
 * 
 * @version 1.0 May 25, 2009
 * @author Patni *
 */

public class JobLogCEFormController extends BaseSimpleFormController {

    /**
     * Instantiates a new job log ce form controller.
     */
    public JobLogCEFormController() {
        super();
        setCommandClass(JobLogCEForm.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
     *      .servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        JobLogCEForm ceSearch = (JobLogCEForm) command;
        SortInfo sortBy = null;
        String requestAction = request.getParameter("requestAction") == null ? "" : request.getParameter("requestAction");

        if ("searchCriteria".equalsIgnoreCase(ceSearch.getTabNavigationTo())) {
            ceSearch.setTabId(0);
            if (requestAction.equalsIgnoreCase("new search")) {
                ceSearch = new JobLogCEForm();
                setDropDownValues(ceSearch);
                ceSearch.setResult(null);
                ceSearch.setDateFormat(Form.getCurrentUserFormat());
                command = ceSearch;
            }
        }
        else if ("save".equalsIgnoreCase(requestAction)) {
            JobLogCESearchResult[] res = ceSearch.getResult();
            convertToEntity(res, ceSearch);
        }
        else if ("sort".equalsIgnoreCase(requestAction)) {
            SearchService searchService = ServiceManager.getSearchService();
            SortInfo sortInfo = new SortInfo(ceSearch.getSortField());
            ArrayMapGrid searchResult = searchService.searchJobLog(ceSearch.getSearchCriteriaList(), ceSearch.getPagination(), sortInfo);
            if (searchResult != null && searchResult.getRecordCount() > 0) {
                ceSearch.setSearchResultGrid(searchResult);
                Pagination pagination = ceSearch.getPagination();
                pagination.setCurrentPageNum(1);
            }

        }
        else if ("search".equalsIgnoreCase(requestAction)) {
            ArrayMapGrid searchResult = null;
            SearchService searchService = ServiceManager.getSearchService();
            // for calculating the pages
            Pagination pagination = null;
            searchResult = searchService.searchJobLog(ceSearch.getSearchCriteriaList(), pagination, sortBy);
            pagination = ceSearch.getPagination();
            pagination.setTotalRecord(searchResult.getRecordCount());
            pagination.setNumInPage(10);
            if (searchResult.getRecordCount() > 10) {
                pagination.setPagesToDisplay(searchResult.getRecordCount() / 10);
            }
            else {
                pagination.setPagesToDisplay(1);
            }

            pagination.calculatePages();
            pagination.setCurrentPageNum(1);
            // end

            searchResult = searchService.searchJobLog(ceSearch.getSearchCriteriaList(), ceSearch.getPagination(), sortBy);
            if (searchResult != null && searchResult.getRecordCount() > 0) {
                ceSearch.setSearchResultGrid(searchResult);
            }
            ceSearch.setTabId(1);
        }
        else if ("page".equalsIgnoreCase(requestAction)) {
            ArrayMapGrid searchResult = null;
            SearchService searchService = ServiceManager.getSearchService();
            Pagination pagination = ceSearch.getPagination();
            pagination.setCurrentPageNum(ceSearch.getPageNumber());
            // pagination.setPagesToDisplay(ceSearch.getPageNumber());
            SortInfo sortInfo = new SortInfo(ceSearch.getSortField());
            searchResult = searchService.searchJobLog(ceSearch.getSearchCriteriaList(), pagination, sortInfo);
            if (searchResult != null && searchResult.getRecordCount() > 0) {
                ceSearch.setSearchResultGrid(searchResult);
            }
            // ceSearch.setTabId(1);
        }
        else if ("excelupload".equalsIgnoreCase(requestAction)) {
            SearchService searchService = ServiceManager.getSearchService();
            Pagination pagination = null;
            ArrayMapGrid searchResult = searchService.searchJobLog(ceSearch.getSearchCriteriaList(), pagination, sortBy);
            if (exportToExcel(searchResult, response)) {
                return null;
            }

        }
        else if ("navigation".equalsIgnoreCase(requestAction)) {
            if (ceSearch.getTabNavigationTo().equals("main")) {
                ceSearch.setTabId(1);
            }
            else if (ceSearch.getTabNavigationTo().equals("billing")) {
                ceSearch.setTabId(2);
            }
            else if (ceSearch.getTabNavigationTo().equals("process")) {
                ceSearch.setTabId(3);
            }
            else if (ceSearch.getTabNavigationTo().equals("mail")) {

                // ceSearch.setTabId(3);
            }
        }

        return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
     *      javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());

        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#suppressValidation
     *      (javax.servlet.http.HttpServletRequest, java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    protected boolean suppressValidation(HttpServletRequest request, Object command) {

        String requestflag = request.getParameter("requestAction");
        if ((requestflag != null) && (!"".equals(requestflag))) {
            return true;
        }
        return super.suppressValidation(request);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     *      (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        JobLogCEForm jobCeSearch = (JobLogCEForm) request.getSession()
                .getAttribute("com.intertek.phoenix.web.controller.joblog.JobLogCEFormController.FORM.command");

        String reqBy = request.getParameter("reqBy");
        if (jobCeSearch == null || reqBy == null || "".equals(reqBy.trim())) {
            jobCeSearch = new JobLogCEForm();
            setDropDownValues(jobCeSearch);
        }
        jobCeSearch.setRequestAction("new search");
        jobCeSearch.setDateFormat(Form.getCurrentUserFormat());
        return jobCeSearch;
    }

    /**
     * Name :getDateFormat Date :May 25, 2009 purpose :to set all the static
     * dropdown data from Reference Data service
     * 
     * @return
     */
    private void setDropDownValues(JobLogCEForm logCeForm) {
        ReferenceDataService refService = ServiceManager.getReferenceDataService();

        logCeForm.setNumericOperator(refService.getFilterOpFields());
        logCeForm.setStringOperator(refService.getFilterOpFields());
        logCeForm.setOperationalStatusList(refService.getOperationalStatusFields());
        logCeForm.setOrderStatusList(refService.getOrderStatusFields());
        logCeForm.setTaskOperationalStatusList(refService.getTaskOperationalStatusFields());
    }

    /**
     * Name :saveCEJobOrderEntity Date :May 25, 2009 purpose :to set the updated
     * values and update the CEJobOrder Entity
     * 
     * @return
     */

    private void convertToEntity(JobLogCESearchResult[] jobLogCEForm, JobLogCEForm jobLogCeForm) throws Exception {
        com.intertek.phoenix.dao.Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);

        for (JobLogCESearchResult resultForm : jobLogCEForm) {
            try {
                String jobNumber = resultForm.getOrderNumber();
                CEJobOrder ceJobOrder = null;
                if (jobNumber != null && !"".equals(jobNumber.trim())) {
                    if (jobNumber != null && !"".equals(jobNumber)) {
                        ceJobOrder = jobDao.find(jobNumber);
                    }
                    if (ceJobOrder != null) {
                        // ceJobOrder.setProjectNumber(resultForm.getProjectNumber());
                        // List<CEJobOrder> lst = jobDao.search(ceJobOrder);

                        ceJobOrder.setOperationalStatus(convertOperationalStatus(resultForm.getProjectOperationalStatus()));
                        ceJobOrder.setStatus(convertOrderStatus(resultForm.getOrderStatus()));
                        ceJobOrder.setPromiseCompletionDate(jobLogCeForm.stringToDate(resultForm.getPromissedComplaintDate()));
                        ceJobOrder.setProjectManagerName(resultForm.getProjectManager());
                        ceJobOrder.setSalesPersonName(resultForm.getSalesRep());
                        ceJobOrder.setBranchName(resultForm.getProjectOwningOrg());
                        ceJobOrder.setActualReadyDate(jobLogCeForm.stringToDate(resultForm.getActualReadyDate()));
                        ceJobOrder.setCustomerReadyDate(jobLogCeForm.stringToDate(resultForm.getCustomerReadyDate()));
                        ceJobOrder.setServiceLocationCode(resultForm.getServiceLocation());
                        ceJobOrder.setDescription(resultForm.getJobDescription());

                        Set<CEJobContract> jobContracts = ceJobOrder.getJobContracts();
                        for (CEJobContract jobContract : jobContracts) {
                            Set<CEJobOrderLineItem> lineItemSet = jobContract.getJobOrderLineItems();
                            for (CEJobOrderLineItem lineItem : lineItemSet) {
                                if (lineItem.getLineNumber() == Long.parseLong(resultForm.getTaskId())) {
                                    
                                    // TODO: "many fields not belong to CEJobOrderLineItem, instead,
                                    // they belong to CEJobTest". Change it accordingly

                                    /*
                                    lineItem.setSampleDescription(resultForm.getTaskSampleDescription());
                                    lineItem.setComment(resultForm.getTaskComments());
                                    lineItem.setBranchName(resultForm.getTaskOwningOrg());
                                    if (resultForm.getTaskReadyDate() != null) {
                                        lineItem.setTaskReadyDate(jobLogCeForm.stringToDate(resultForm.getTaskReadyDate()));
                                    }
                                    lineItem.setStartDate(jobLogCeForm.stringToDate(resultForm.getTaskStartDate()));
                                    lineItem.setEndDate(jobLogCeForm.stringToDate(resultForm.getTaskCompletionDate()));
                                    lineItem.setTaskOperationalStatus(TaskOperationalStatus.valueOf(resultForm.getTaskOperationalStatus()));
                                    lineItem.setTaskManagerId(resultForm.getTaskManager());
                                    lineItem.setDescription(resultForm.getTaskDescription());
                                    */
                                    
                                    break;
                                }
                            }
                        }
                        jobDao.saveOrUpdate(ceJobOrder);

                    }
                }
            }
            catch (DaoException e) {
                throw new ServiceException("Error while Saving the Job Log :" + e.getMessage());
            }
        }
    }

    private OperationalStatus convertOperationalStatus(String code) {
        for (OperationalStatus st : OperationalStatus.values()) {
            if (st.getValue().equals(code)) {
                return st;
            }
        }
        return null;
    }

    private OrderStatus convertOrderStatus(String code) {
        for (OrderStatus st : OrderStatus.values()) {
            if (st.getValue().equals(code)) {
                return st;
            }
        }
        return null;
    }

    /**
     * JobLog: Export to excel.
     * 
     * @param searchResult
     *            the ArrayMapGrid
     * @param response
     *            the HttpServletResponse
     * 
     * @return true, if successful
     * 
     * @throws Exception
     *             the exception
     */
    private boolean exportToExcel(ArrayMapGrid searchResult, HttpServletResponse response) throws Exception {
        List<JobLogCESearchResult> jlResult = new ArrayList<JobLogCESearchResult>();
        for (int i = 0; i < searchResult.getRecordCount(); i++) {
            if (searchResult.moveNext()) {
                JobLogCESearchResult result = new JobLogCESearchResult();
                result.setValues(searchResult);
                jlResult.add(result);
            }
        }
        JobLogExcel logExcel = new JobLogExcel(jlResult);
        return Exporter.exportTOXCell(response, logExcel);
    }
}
