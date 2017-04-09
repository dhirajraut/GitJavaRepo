/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.warehouse;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Branch;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * 
 * @author Eric Nguyen
 */
public class WarehouseSearchForm extends SearchForm {

    private String buName;
    private String name;
    private String description;
    private String status;

    public WarehouseSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/warehouse.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<Branch> searchResult = (List<Branch>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (Branch wh : searchResult) {
            myRows.add(new WarehouseRow(wh));
        }
        return myRows;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("description", "description", "contains"));
        list.add(new SearchableCriteria("status", "status", "contains"));
        list.add(new SearchableCriteria("buName", "buName", "contains"));
        list.add(new SearchableCriteria("name", "name", "contains"));
        return list;
    }

    public Class<?> getEntityType() {
        return Branch.class;
    }

    public Row getHeader() {
        return new WarehouseRow();
    }
}
