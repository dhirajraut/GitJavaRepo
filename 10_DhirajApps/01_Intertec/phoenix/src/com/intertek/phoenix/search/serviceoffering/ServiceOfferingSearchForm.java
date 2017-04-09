/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.serviceoffering;

import java.util.ArrayList;
import java.util.List;

import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * Search Service Offering Parent lookup form
 * 
 * @version 1.0 June 24, 2009
 * @author Patni
 */

public class ServiceOfferingSearchForm extends SearchForm {

    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/serviceoffering.jsp";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Row> getRows() {
        List<ServiceOffering> searchResult = (List<ServiceOffering>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (ServiceOffering serviceOffering : searchResult) {
            myRows.add(new ServiceOfferingRow(serviceOffering, getSearchForm(), getTargetFieldId()));
        }
        return myRows;
    }

    @Override
    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();

        list.add(new SearchableCriteria("id", "id", "="));
        list.add(new SearchableCriteria("description", "description", "contains"));

        return list;
    }

    @Override
    public Class<?> getEntityType() {
        return ServiceOffering.class;
    }

    @Override
    public Row getHeader() {
        return new ServiceOfferingRow();
    }

}
