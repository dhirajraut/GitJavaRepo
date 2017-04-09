/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.customer;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Customer;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * Search Customer lookup form
 * 
 * @version 1.0 Apr 15, 2009
 * @author Patni
 */
public class CustomerSearchForm extends SearchForm {

    private String custCode;
    private String name;

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/customer.jsp";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Row> getRows() {
        List<Customer> searchResult = (List<Customer>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (Customer customer : searchResult) {
            myRows.add(new CustomerRow(customer, getSearchForm(), getTargetFieldId()));
        }
        return myRows;
    }

    @Override
    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();

        list.add(new SearchableCriteria("custCode", "custCode", "contains"));
        list.add(new SearchableCriteria("name", "name", "contains"));

        return list;
    }

    @Override
    public Class<?> getEntityType() {
        return Customer.class;
    }

    @Override
    public Row getHeader() {
        return new CustomerRow();
    }
}
