/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.warehouse;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Branch;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * 
 * @author Eric Nguyen
 */
public class WarehouseRow extends AbstractRow {
    private List<Column> myColumns;
    private Branch wh;
    /** The rows. */
    List<Branch> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a WarehouseRow from the given Warehouse
     */
    public WarehouseRow(Branch wh) {
        this.wh = wh;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column(null, wh.getBuName()));
        myColumns.add(new Column("warehouseSelected", wh.getName()));
        myColumns.add(new Column(null, wh.getDescription()));
        myColumns.add(new Column(null, wh.getStatus()));
    }

    /*
     * construct THE header row for warehouse search
     */
    public WarehouseRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "businessUnitName", "buName"));
        myColumns.add(new Column("doSort", "branchCode", "name"));
        myColumns.add(new Column("doSort", "description", "description"));
        myColumns.add(new Column("doSort", "status", "status"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return wh.getName();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("businessUnitName"), "buName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("branchCode"), "name"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("description"), "description"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("status"), "status"));
        return header;
    }

    @Override
    public String getName() {
        return "Branch";
    }

    @Override
    public Object getNextRow() {
        Branch obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<Branch>) list;
    }

}
