/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.po;

import java.util.ArrayList;
import java.util.List;
import com.intertek.phoenix.entity.PurchaseOrder;

import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

/**
 * @author patni
 *
 */
public class POSearchForm extends SearchForm {

    private String targetFieldId;
    private String poNumber;
    private String custCode;
    private String customerName;

    @Override
    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/purchaseorder.jsp";
    }

    public POSearchForm() {
    }

    public String getTargetFieldId() {
        return targetFieldId;
    }

    public void setTargetFieldId(String targetFieldId) {
        this.targetFieldId = targetFieldId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Row> getRows() {
        List<PurchaseOrder> searchResult = (List<PurchaseOrder>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (PurchaseOrder po : searchResult) {
            myRows.add(new PORow(po));
        }
        return myRows;
    }

    @Override
    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("poNumber", "poNumber", "contains"));
        list.add(new SearchableCriteria("custCode", "custCode", "contains"));
        list.add(new SearchableCriteria("customer.name", "customerName", "contains"));
        return list;
    }

    @Override
    public Class<?> getEntityType() {
        return PurchaseOrder.class;
    }

    @Override
    public Row getHeader() {
        return new PORow();
    }
}
