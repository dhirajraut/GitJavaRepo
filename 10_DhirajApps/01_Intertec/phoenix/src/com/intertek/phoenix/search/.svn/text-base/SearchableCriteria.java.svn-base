/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search;


/**
 * 
 * @author Eric Nguyen
 */
public class SearchableCriteria{
    private String entityAttribute;
    private String searchValue;
    private String operator;
    private boolean searchValueIsAttribute;
    
    public SearchableCriteria(String entityAttribute, String searchValue, String operator, boolean searchValueIsAttribute){
        this(entityAttribute, searchValue, operator);
        this.searchValueIsAttribute=searchValueIsAttribute;
    }
    
    public SearchableCriteria(String entityAttribute, String searchValue, String operator){
        this.entityAttribute=entityAttribute;
        this.searchValue=searchValue;
        this.operator=operator;
        searchValueIsAttribute=true;
    }
    
    public String getEntityAttribute() {
        return entityAttribute;
    }

    public String getOperator() {
        return operator;
    }

    public void setEntityAttribute(String entityAttribute) {
        this.entityAttribute = entityAttribute;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public boolean isSearchValueIsAttribute() {
        return searchValueIsAttribute;
    }

    public void setSearchValueIsAttribute(boolean searchValueIsAttribute) {
        this.searchValueIsAttribute = searchValueIsAttribute;
    }
}
