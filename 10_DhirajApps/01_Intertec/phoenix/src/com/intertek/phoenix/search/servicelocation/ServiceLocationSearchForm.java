/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.servicelocation;

import java.util.ArrayList;
import java.util.List;

import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;
import com.intertek.entity.ServiceLocation;
import com.intertek.phoenix.search.servicelocation.ServiceLocationRow;
import com.intertek.util.Constants;

/**
 * @author patni
 * 
 */
public class ServiceLocationSearchForm extends SearchForm {

    private String countryName;
    private String stateName;
    private String city;
    private String name;
    private String serviceLocationCode;
    private String status = Constants.STATUS_A;

    @Override
    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/service_location.jsp";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Row> getRows() {
        List<ServiceLocation> searchResult = (List<ServiceLocation>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (ServiceLocation sl : searchResult) {
            myRows.add(new ServiceLocationRow(sl));
        }
        return myRows;
    }

    @Override
    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("countryCode", "countryName", "contains"));
        list.add(new SearchableCriteria("stateCode", "stateName", "contains"));
        list.add(new SearchableCriteria("city", "city", "contains"));
        list.add(new SearchableCriteria("name", "name", "contains"));
        list.add(new SearchableCriteria("serviceLocationCode", "serviceLocationCode", "contains"));
        list.add(new SearchableCriteria("status", "status", "equals"));

        return list;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceLocationCode() {
        return serviceLocationCode;
    }

    public void setServiceLocationCode(String serviceLocationCode) {
        this.serviceLocationCode = serviceLocationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Class<?> getEntityType() {
        return ServiceLocation.class;
    }

    @Override
    public Row getHeader() {
        return new ServiceLocationRow();
    }

}
