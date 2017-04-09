/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.export.Exporter;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.export.template.GenericSearchExcel;
import com.intertek.phoenix.util.CommonSearchUtil;

/**
 * The one and only search controller
 * 
 * @author Eric Nguyen
 */
public class SearchFormController extends SimpleFormController {
    public SearchFormController() {
        super();
        setCommandClass(SearchForm.class);
        setSessionForm(true);
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        SearchForm myForm = (SearchForm) command;
        doSearch(request, myForm, response);
        if (myForm.getExcelFlag().equals("true")) {
            return null;
        }
        return showForm(request, response, errors);
    }

    @SuppressWarnings("unchecked")
    private void doSearch(HttpServletRequest request, SearchForm myForm, HttpServletResponse response) throws PhoenixException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, IOException {
        List<?> results = new ArrayList();
        String excelFlag = myForm.getExcelFlag();
        SortInfo sortInfo = excelFlag.equals("true") ? null : buildSortInfo(myForm);
        Pagination pagination = excelFlag.equals("true") ? null : doPagination(myForm.getPageNumber(), Pagination.UNDEFINED);
        myForm.setPagination(pagination);
        if (myForm instanceof SpecificSearch) {
            SpecificSearch form = (SpecificSearch) myForm;
            results = form.buildResult(pagination, sortInfo);
            myForm.setResult(results);
        }
        else {
            SearchService searchService = ServiceManager.getSearchService();
            results = searchService.advancedSearch(myForm.getEntityType(), buildCriteria(myForm), pagination, sortInfo);
            myForm.setResult(results);
        }
        if (excelFlag.equals("true")) {// loop excutes exportTOXCell method to
            // XL Export.
            GenericSearchExcel gse = new GenericSearchExcel(myForm.getHeader(), results);
            Exporter.exportTOXCell(response, gse);
        }
        pagination.calculatePages();
    }

    private SortInfo buildSortInfo(SearchForm myForm) {
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

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        SearchForm myForm = SearchFormFactory.getInstance().getSearchForm(request);
        myForm.setSearchForm(request.getParameter("searchForm"));
        doSearch(request, myForm, null);
        return myForm;
    }

    private List<SearchCriteria> buildCriteria(SearchForm form) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return CommonSearchUtil.buildCriteria(form, form.getSearchableCriteria());
        /*
         * List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();
         * List<SearchableCriteria> scs = form.getSearchableCriteria(); for
         * (SearchableCriteria sc : scs) { String searchValue =
         * BeanUtils.getProperty(form, sc.getSearchAttribute()); if (searchValue !=
         * null && !searchValue.isEmpty()) { criteria.add(new
         * SearchCriteria(sc.getEntityAttribute(), searchValue,
         * sc.getOperator())); } } return criteria;
         */
    }

    private Pagination doPagination(String numStr, int totalRecords) {
        int pageNumber = 0;
        try {
            pageNumber = Integer.parseInt(numStr);
        }
        catch (Exception e) {
            pageNumber = 1;
        }
        Pagination pagination = new Pagination(totalRecords, 20, pageNumber, 10);
        return pagination;
    }
}
