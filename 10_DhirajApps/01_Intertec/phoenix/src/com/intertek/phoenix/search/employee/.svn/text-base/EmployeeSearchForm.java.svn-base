/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.employee;

import java.util.ArrayList;
import java.util.List;

import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * @author Eric Nguyen
 * 
 */
public class EmployeeSearchForm extends SearchForm {
    private String firstName;
    private String lastName;
    private String businessUnit;
    private String operatingUnit;

    public EmployeeSearchForm() {

    }

    @Override
    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/employee.jsp";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Row> getRows() {
        List<Employee> searchResult = (List<Employee>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (Employee employee : searchResult) {
            myRows.add(new EmployeeRow(employee));
        }
        return myRows;
    }

    @Override
    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("firstName", "firstName", "contains"));
        list.add(new SearchableCriteria("lastName", "lastName", "contains"));
        list.add(new SearchableCriteria("businessUnit", "businessUnit", "contains"));
        list.add(new SearchableCriteria("operatingUnit", "operatingUnit", "contains"));
        return list;
    }

    @Override
    public Class<?> getEntityType() {
        return Employee.class;
    }

    @Override
    public Row getHeader() {
        return new EmployeeRow();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(String operatingUnit) {
        this.operatingUnit = operatingUnit;
    }
}
