package com.intertek.phoenix.search;

import com.intertek.phoenix.dao.FilterOp;

/**
 *  This class is used by Command Object to construct (name operation value) to be used
 *  later by QueryInfo
 *  
 * @author lily.sun
 */

public class SearchCriteria {
    private String name;
    private String value;
    private String op;

    public SearchCriteria(){
        
    }
    
    /**
     * Construct the search criteria with name, value and operator
     * 
     * @param name
     * @param value
     * @param op
     */
    public SearchCriteria(String name, String value, String op) {
        this.name = name;
        this.value = value;
        this.op = op;
    }

    /**
     * Construct a search criteria with name and value, default operator to
     * FilterOp.EQUAL
     * 
     * @param name
     * @param value
     */
    public SearchCriteria(String name, String value) {
        this.name = name;
        this.value = value;
        this.op = "=";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the op
     */
    public String getOp() {
        return op;
    }
    
    public FilterOp getFilterOp(){
        if ("contains".equals(this.op.trim().toLowerCase()))
            return FilterOp.LIKE;
        FilterOp[] allOps = FilterOp.list();
        for (int i = 0; i < allOps.length; i++) {
            if (this.op.toLowerCase().equals(allOps[i].symbol()))
                return allOps[i];
        }
        return FilterOp.EQUAL;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setOp(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
    //TODO Lily not complete yet
        return name + op + value;
    }

    
}


