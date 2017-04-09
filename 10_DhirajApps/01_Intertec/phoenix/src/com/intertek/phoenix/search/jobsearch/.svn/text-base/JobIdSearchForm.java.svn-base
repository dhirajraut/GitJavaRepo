/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.jobsearch;

import java.util.ArrayList;
import java.util.List;

import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * @author patni
 * 
 */
public class JobIdSearchForm extends SearchForm {
    private String buName;
    private String branchName;
    private String jobNumber;

    public JobIdSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/parentjob.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<CEJobOrder> searchResult = (List<CEJobOrder>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (CEJobOrder jo : searchResult) {
            myRows.add(new JobIdSearchRow(jo));
        }
        return myRows;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("buName", "buName", "contains"));
        list.add(new SearchableCriteria("branchName", "branchName", "contains"));
        list.add(new SearchableCriteria("jobNumber", "jobNumber", "contains"));
        return list;
    }

    public Class<?> getEntityType() {
        return CEJobOrder.class;
    }

    public Row getHeader() {
        return new JobIdSearchRow();
    }
}
