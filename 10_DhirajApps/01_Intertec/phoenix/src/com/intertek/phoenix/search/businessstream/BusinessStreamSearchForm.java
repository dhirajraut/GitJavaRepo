/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.businessstream;

import java.util.ArrayList;
import java.util.List;

import com.intertek.phoenix.entity.BusinessStream;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * 
 * @author Patni
 */
public class BusinessStreamSearchForm extends SearchForm {

    private String code;
    private String description;

    public BusinessStreamSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/bustream.jsp";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<BusinessStream> searchResult = (List<BusinessStream>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (BusinessStream bs : searchResult) {
            myRows.add(new BusinessStreamRow(bs));
        }
        return myRows;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("code", "code", "contains"));
        list.add(new SearchableCriteria("description", "description", "contains"));
        return list;
    }

    public Class<?> getEntityType() {
        return BusinessStream.class;
    }

    public Row getHeader() {
        return new BusinessStreamRow();
    }
}
