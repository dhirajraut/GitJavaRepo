/*
 * DepositInvoiceSearchFormController.java
 * 
 * @version 1.0
 * 
 * Jul 21, 2009
 * 
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 */
package com.intertek.phoenix.web.controller.depositinvoice;

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
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.DepositType;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.web.controller.AbstractSearchFormController;
import com.intertek.phoenix.web.controller.ControllerUtil;
import com.intertek.util.SecurityUtil;

/**
 * The Class DepositInvoiceSearchFormController.
 */
public class DepositInvoiceSearchFormController extends AbstractSearchFormController {

    public DepositInvoiceSearchFormController() {
        super();
        setCommandClass(DepositInvoiceSearchForm.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        DepositInvoiceSearchForm myForm = (DepositInvoiceSearchForm) command;
        if (request.getParameter("updateFlag").equalsIgnoreCase("true")) {
            for (DepositInvoiceSearchForm form : (List<DepositInvoiceSearchForm>) myForm.getResults()) {
                DepositInvoice di = ControllerUtil.findById(DepositInvoice.class,form.getDi().getId());
                di.setDepositPaid(form.getPaymentReceived());
                if (form.getDepositInvoiceType() != null && !form.getDepositInvoiceType().isEmpty()) {
                    di.setDepositType(DepositType.valueOf(form.getDepositInvoiceType()));
                }
                //ControllerUtil.loadAndUpdate(di, DepositInvoice.class);
            }
        }
        String pageNumberStr = request.getParameter("pageNo");
        Pagination pagination = doPagination(pageNumberStr);
        doSearch(request, myForm, pagination);
        return showForm(request, response, errors);
    }

    /**
     * Do search.Building the criteria,calling the search method and setting the
     * search result to form
     * 
     * @param request
     *            the request
     * @param myForm
     *            the my form
     * @param pagination
     *            the pagination
     * 
     * @throws PhoenixException
     */
    private void doSearch(HttpServletRequest request, DepositInvoiceSearchForm myForm, Pagination pagination) throws PhoenixException {
        SearchService searchService = ServiceManager.getSearchService();
        List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();

        if (!myForm.getBuissinessUnit().getValue().isEmpty()) {
            criteria.add(myForm.getBuissinessUnit());
        }
        if (!myForm.getBranchId().getValue().isEmpty()) {
            criteria.add(myForm.getBranchId());
        }
        if (!myForm.getJobId().getValue().isEmpty()) {
            criteria.add(myForm.getJobId());
        }
        if (!myForm.getDepInvoiceNo().getValue().isEmpty()) {
            criteria.add(myForm.getDepInvoiceNo());
        }

        List<DepositInvoice> diList =  searchService.depositInvoiceSearch(criteria, pagination, null);

        myForm.setResults(myForm.getSearchResult(diList));

    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws PhoenixException {
        DepositInvoiceSearchForm form = new DepositInvoiceSearchForm();
        PhxUserService phxUserService = ServiceManager.getUserService();
        User user = SecurityUtil.getUser();
        if (user != null) {
            String loginName = user.getLoginName();
            user = phxUserService.getUserByName(loginName);
        }
        form.getBuissinessUnit().setValue(user.getBuName());
        return form;
    }

}
