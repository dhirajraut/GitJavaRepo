package com.intertek.phoenix.search.businessunit;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.BusinessUnit;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;
import com.intertek.phoenix.search.businessunit.BuSearchRow;

public class BuSearchForm extends SearchForm {

    private String buName;
    private String description;

    public BuSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/businessunit.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<BusinessUnit> searchResult = (List<BusinessUnit>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (BusinessUnit bu : searchResult) {
            myRows.add(new BuSearchRow(bu));
        }
        return myRows;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("name", "buName", "contains"));
        list.add(new SearchableCriteria("description", "description", "contains"));
        return list;
    }

    public Class<?> getEntityType() {
        return BusinessUnit.class;
    }

    public Row getHeader() {
        return new BuSearchRow();
    }
}
