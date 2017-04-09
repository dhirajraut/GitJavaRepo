/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.purchaseorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.exception.ServiceException;
import com.intertek.export.Exporter;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.export.template.PurchaseOrderExcel;
import com.intertek.phoenix.export.template.PurchaseOrderSheet;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.web.controller.AbstractSearchFormController;

/**
 * Purpose: Controller class responsible for searching purchase order.
 * 
 * @version 1.0 Apr 15, 2009
 * @author Patni
 */
public class SearchPurchaseOrderFormController extends AbstractSearchFormController {

    /**
     * * Constructor Instantiates object of SearchPurchaseOrderFormController.
     */
    public SearchPurchaseOrderFormController() {
        super();
        setCommandClass(PurchaseOrderSearchForm.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        PurchaseOrderSearchForm searchForm = (PurchaseOrderSearchForm) command;

        String pageNumberStr = request.getParameter("pageNo");
        Pagination pagination = doPagination(pageNumberStr);

        SearchService searchService = ServiceManager.getSearchService();
        List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();

        String poNumber = searchForm.getPoNumber().getValue();
        String custName = searchForm.getCustomerName().getValue();
        String custCode = searchForm.getCustomerId().getValue();

        if (poNumber != null && !poNumber.equals("")) {
            criteria.add(new SearchCriteria("poNumber", poNumber, "equals"));
        }

        if (custCode != null && !custCode.equals("")) {
            criteria.add(new SearchCriteria("custCode", custCode, "equals"));
        }
        if (custName != null && !custName.equals("")) {
            criteria.add(new SearchCriteria("customer.name", custName, "equals"));
        }

        List<PurchaseOrder> poList = (List<PurchaseOrder>) searchService.advancedSearch(PurchaseOrder.class, criteria, pagination, null);

        if (poList.size() > 0) {
            searchForm.setResults(poList);
            if (searchForm.getCxcel().equalsIgnoreCase("true")) {
                if (exportToExcel(poList, response)) {
                    return null;
                }
            }

        }
        else {
            throw new ServiceException("search.string.not.exist.error", new Object[] {}, null);
        }

        return showForm(request, response, errors);
    }

    /**
     * This method will take care of export to Excel.
     * 
     * @param poList
     *            the list of PurchaseOrder
     * @param response
     *            the HttpServletResponse
     * 
     * @return true, if successful
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private boolean exportToExcel(List<PurchaseOrder> poList, HttpServletResponse response) throws IOException {
        PurchaseOrderExcel poExcel = new PurchaseOrderExcel(new PurchaseOrderSheet(poList));
        return Exporter.exportTOXCell(response, poExcel);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
     *      javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     *      (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new PurchaseOrderSearchForm();
    }

}
