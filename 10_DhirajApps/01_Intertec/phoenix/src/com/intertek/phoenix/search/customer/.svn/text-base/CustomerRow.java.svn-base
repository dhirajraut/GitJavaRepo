/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.customer;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Customer;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * Purpose: Row for Customer search popup
 * 
 * @version 1.0 June 18 2009
 * @author Patni
 */
public class CustomerRow extends AbstractRow {
    private List<Column> myColumns;
    private Customer customer;
    private String targetField;
    /** The rows. */
    List<Customer> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a CustomerRow from the given Customer
     */
    public CustomerRow(Customer customer, String formName, String value) {
        this.customer = customer;
        this.targetField = value;
        myColumns = new ArrayList<Column>();

        if (formName.equals("purchaseOrderCreateForm")) {

            myColumns.add(new Column("customerSelected", customer.getCustCode()));
        }
        else if (formName.equals("searchOrderCreateForm")) {

            if (targetField.equals("customerId.value")) {
                myColumns.add(new Column("customerSearchSelectedId", customer.getCustCode()));
            }
            else if (targetField.equals("customerName.value")) {
                myColumns.add(new Column("customerSearchSelectedName", customer.getCustCode()));
            }

        }

        myColumns.add(new Column(null, customer.getName()));
    }

    /*
     * construct THE header row for customer search
     */
    public CustomerRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "custCode", "custCode"));
        myColumns.add(new Column("doSort", "name", "name"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        if (targetField.equals("custCode")) {
            return customer.getCustCode();
        }
        else if (targetField.equals("customerId.value")) {
            return customer.getCustCode();
        }
        else if (targetField.equals("customerName.value")) {
            return customer.getName();
        }

        return null;
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerid"), "custCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerName"), "name"));
        return header;
    }

    @Override
    public String getName() {
        return "Customer";
    }

    @Override
    public Object getNextRow() {
        Customer obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    public void setRows(List<?> list) {
        this.rows = (List<Customer>) list;
    }
}
