/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.businessstream;

import java.util.ArrayList;
import java.util.List;

import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.entity.BusinessStream;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * 
 * @author Patni
 */
public class BusinessStreamRow extends AbstractRow {
    private List<Column> myColumns;
    private BusinessStream bs;

    /** The rows. */
    List<BusinessStream> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a WarehouseRow from the given Warehouse
     */
    public BusinessStreamRow(BusinessStream bs) {
        this.bs = bs;
        myColumns = new ArrayList<Column>();

        myColumns.add(new Column("buStreamSelected", bs.getCode()));
        myColumns.add(new Column(null, bs.getDescription()));

    }

    /*
     * construct THE header row for warehouse search
     */
    public BusinessStreamRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "code", "code"));
        myColumns.add(new Column("doSort", "description", "description"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return bs.getCode();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("businessStreamCode"), "code"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("description"), "description"));
        return header;
    }

    @Override
    public String getName() {
        return "BusinessStream";
    }

    @Override
    public Object getNextRow() {
        BusinessStream obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    public void setRows(List<?> list) {
        this.rows = (List<BusinessStream>) list;
    }
}
