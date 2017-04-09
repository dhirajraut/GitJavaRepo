/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.user;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.User;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * @author patni
 * 
 */
public class UserSearchForm extends SearchForm {//implements SpecificSearch {

    private String loginName;
    private String firstName;
    private String lastName;
    private String role;

    public UserSearchForm() {

    }

    @Override
    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/user.jsp";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Row> getRows() {
        List<User> searchResult = (List<User>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (User user : searchResult) {
            myRows.add(new UserRow(user));
        }
        return myRows;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    @Override
    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("loginName", "loginName", "contains"));
        list.add(new SearchableCriteria("firstName", "firstName", "contains"));
        list.add(new SearchableCriteria("lastName", "lastName", "contains"));
        return list;
    }

//    @Override
//    public List<?> buildResult(Pagination pagination, SortInfo sortInfo) throws PhoenixException {
//        SearchService searchService = ServiceManager.getSearchService();
//        List<User> results = searchService.searchUsersByRole(pagination, sortInfo, role, loginName, firstName, lastName);
//        return results;
//    }

    @Override
    public Class<?> getEntityType() {
        return User.class;
    }

    @Override
    public Row getHeader() {
        return new UserRow();
    }
}
