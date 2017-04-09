/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search;

import java.util.List;

import com.intertek.pagination.Pagination;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.dao.QueryInfo;

/**
 * 
 * @author Eric Nguyen
 */
public abstract class SearchForm extends Form {
    private List<?> result; //place holder of the search result

    private String targetFieldId; // pulled up to base form since all the caller will need to set it

    public abstract Class<?> getEntityType();

    public abstract String getSearchCriteriaPage();

    public abstract Row getHeader();

    public abstract List<Row> getRows();

    public abstract List<SearchableCriteria> getSearchableCriteria();
    
    public Pagination pagination;

    private String pageNumber;
    private String sortFlag;
    private String submitFlag;
    private String searchType;
    private String searchForm;
    private String searchValue;
    private String excelFlag="false";
    private String sortBy;
    
    public QueryInfo getQueryInfo(){
        return null;
    }
    
    public void setResult(List<?> result) {
        this.result = result;
    }

    public List<?> getResult() {
        return result;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getSubmitFlag() {
        return submitFlag;
    }

    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchForm() {
        return searchForm;
    }

    public void setSearchForm(String searchForm) {
        this.searchForm = searchForm;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getTargetFieldId() {
        return targetFieldId;
    }

    public void setTargetFieldId(String targetFieldId) {
        this.targetFieldId = targetFieldId;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getExcelFlag() {
        return excelFlag;
    }

    public void setExcelFlag(String excelFlag) {
        this.excelFlag = excelFlag;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
