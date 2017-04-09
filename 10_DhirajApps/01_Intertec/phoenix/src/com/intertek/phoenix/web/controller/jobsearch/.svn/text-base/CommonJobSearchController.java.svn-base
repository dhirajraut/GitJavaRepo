package com.intertek.phoenix.web.controller.jobsearch;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.User;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.PhxUserService;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.search.JobSearchInfo;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.ArrayMapGrid;
import com.intertek.phoenix.web.controller.AbstractSearchFormController;
import com.intertek.util.SecurityUtil;

public class CommonJobSearchController extends AbstractSearchFormController {

    public CommonJobSearchController() {
        super();
        setCommandClass(CommonJobSearchForm.class);
        setSessionForm(true);
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        CommonJobSearchForm form = (CommonJobSearchForm) command;
        String pageNumberStr = request.getParameter("pageNo");
        Pagination pagination = doPagination(pageNumberStr);
        doSearch(request, form, pagination);
        form.setSearchFlag("result");
        return showForm(request, response, errors);
    }

    // TODO
    @SuppressWarnings("unchecked")
    private void doSearch(HttpServletRequest request, CommonJobSearchForm myForm, Pagination pagination) throws PhoenixException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        List<?> results = new ArrayList();
        SortInfo sortInfo = buildSortInfo(myForm);
        myForm.setPagination(pagination);
        SearchService searchService = ServiceManager.getSearchService();
        ArrayMapGrid am = searchService.searchJob(buildCriteria(myForm), pagination, sortInfo);
        results = myForm.buildResult(am);
        myForm.setResults(results);
    }

    private SortInfo buildSortInfo(CommonJobSearchForm myForm) {
        SortInfo sortInfo = null;
        String sortBy = myForm.getSortBy();
        if (sortBy != null && !"".equals(sortBy)) {
            sortInfo = new SortInfo();
            String sortFlag = myForm.getSortFlag();
            boolean ascending = true;
            if (sortFlag != null && !"".equals(sortFlag)) {
                ascending = "ASC".equals(sortFlag.toUpperCase());
            }
            if (sortBy.contains(",")) {
                String[] sortFields = sortBy.split(",");
                // This is a little ugly, but SortInfo has to have something to
                // sortBy before appending more sortby's
                for (int i = 0; i < sortFields.length; i++) {
                    if (i == 0) {
                        sortInfo = new SortInfo(sortFields[i], ascending);
                    }
                    else {
                        sortInfo.addSortInfo(new SortInfo(sortFields[i], ascending));
                    }
                }
            }
            else {
                sortInfo = new SortInfo(sortBy, ascending);
            }
        }
        return sortInfo;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws PhoenixException {
        CommonJobSearchForm form = new CommonJobSearchForm();
        PhxUserService phxUserService = ServiceManager.getUserService();
        User user = SecurityUtil.getUser();
        if (user != null) {
            String loginName = user.getLoginName();
            user = phxUserService.getUserByName(loginName);
        }
        form.setSearchFlag("search");
        form.getBuName().setValue(user.getBuName());
        form.getOperatingUnit().setValue(user.getBranchName());
        return form;
    }

    // TODO
    private JobSearchInfo buildCriteria(CommonJobSearchForm myForm) {
        JobSearchInfo searchInfo = new JobSearchInfo();
        searchInfo.setBuName(myForm.getBuName().getValue());
        searchInfo.setOperatingUnit(myForm.getOperatingUnit().getValue());
        searchInfo.setStatus(myForm.getStatusCri().getValue());
        searchInfo.setJobType(myForm.getJobTypeCri().getValue());
        searchInfo.setFromJobId(myForm.getFrmJobId().getValue());
        searchInfo.setToJobId(myForm.getToJobId().getValue());
        // searchInfo.set(new SearchCriteria[] { myForm.getFrmJobFinishDt(),
        // myForm.getToJobFinishDt() }, "actualReadyDate".getValue());
        searchInfo.setFromJobOrderDate(myForm.getJobOrderDtFrm().getValue());
        searchInfo.setToJobOrderDate(myForm.getJobOrderDtTo().getValue());
        // searchInfo.set(myForm.getJobProduct(), "productName".getValue());
        searchInfo.setCustRefNum(myForm.getCustRefNumCri().getValue());
        searchInfo.setInvoiceName(myForm.getJobInvoice().getValue(),"=");
        searchInfo.setInvoiceStatus(myForm.getInvStatus().getValue(), "=");
        searchInfo.setContractId(myForm.getContractId().getValue());
        searchInfo.setContractDesc(myForm.getJobContractDsc().getValue());
        searchInfo.setContactId(myForm.getJobContactId().getValue());
        searchInfo.setCreatedBy(myForm.getJobCrtedBy().getValue());
        // searchInfo.setModifiedBy(myForm.getModifiedByCri().getValue());
        searchInfo.setSvcLocationCode(myForm.getJobSvcLoctn().getValue());
        searchInfo.setCompanyId(myForm.getJobCompanyId().getValue());
        searchInfo.setCompanyName(myForm.getCompany().getValue());
        searchInfo.setBillingContactName(myForm.getBillContact().getValue());
        // searchInfo.set(myForm.getJobCstmrCoordinator().getValue());
        searchInfo.setSchedulerName(myForm.getSchedulerCri().getValue());
        return searchInfo;
    }

    /*
     * private void getSimpleCriteria(SearchCriteria criteria, String name, List<SearchCriteria>
     * criteriaList) {
     * 
     * if (!criteria.getValue().isEmpty()) { criteria.setName(name); if
     * (criteria.getOp() == null || criteria.getOp().isEmpty()) {
     * criteria.setOp("equals"); } criteriaList.add(criteria); } }
     * 
     * private void getFromToCriteria(SearchCriteria[] criteria, String name,
     * List<SearchCriteria> criteriaList) { String fromVal =
     * criteria[0].getValue(); String toVal = criteria[1].getValue(); if
     * (fromVal != null && !fromVal.isEmpty() && toVal != null &&
     * !toVal.isEmpty()) { criteria[0].setName(name); criteria[0].setOp(">=");
     * criteriaList.add(criteria[0]); criteria[1].setName(name);
     * criteria[1].setOp("<="); criteriaList.add(criteria[0]); } else if
     * (fromVal != null && !fromVal.isEmpty()) { criteria[0].setName(name);
     * criteria[0].setOp("="); criteriaList.add(criteria[0]); } else if (toVal !=
     * null && !toVal.isEmpty()) { criteria[1].setName(name);
     * criteria[1].setOp(">="); criteriaList.add(criteria[1]); } }
     */

}
