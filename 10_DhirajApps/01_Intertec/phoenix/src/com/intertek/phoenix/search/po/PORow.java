/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.po;

import java.util.ArrayList;
import java.util.List;

import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author patni
 * 
 */
public class PORow extends AbstractRow {

    private List<Column> myColumns;
    private PurchaseOrder po;
    /** The rows. */
    List<PurchaseOrder> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a Purchase Order Row from the given PurchaseOrder
     */
    public PORow(PurchaseOrder po) {
        this.po = po;
        myColumns = new ArrayList<Column>();

        myColumns.add(new Column("poSelected", po.getPoNumber()));

        myColumns.add(new Column(null, po.getPoNumber()));
        myColumns.add(new Column(null, String.valueOf(po.getBalanceAmount())));

    }

    /*
     * construct THE header row for purchase order search
     */
    public PORow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "poNumber", "poNumber"));
        myColumns.add(new Column("doSort", "balanceAmount", "balanceAmount"));

    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return po.getPoNumber();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("poNumber"), "poNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerId"), "custCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerName"), "customer.name"));
        return header;
    }

    @Override
    public String getName() {
        return "PurchaseOrder";
    }

    @Override
    public Object getNextRow() {
        PurchaseOrder obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<PurchaseOrder>) list;
    }

}
