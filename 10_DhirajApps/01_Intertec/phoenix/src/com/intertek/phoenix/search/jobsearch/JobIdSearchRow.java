/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.jobsearch;

import java.util.ArrayList;
import java.util.List;

import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author patni
 *
 */
public class JobIdSearchRow extends AbstractRow {
    private List<Column> myColumns;
    private CEJobOrder jo;

    /** The rows. */
    List<CEJobOrder> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a JobIdSearchRow from the given CEJobOrder
     */
    public JobIdSearchRow(CEJobOrder jo) {
        this.jo = jo;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("jobSelected", jo.getJobNumber()));
        myColumns.add(new Column(null, jo.getBuName()));
        myColumns.add(new Column(null, jo.getBranchName()));

    }

    /*
     * construct THE header row for CEJobOrder search
     */
    public JobIdSearchRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "jobNumber", "jobNumber"));
        myColumns.add(new Column("doSort", "businessUnitName", "buName"));
        myColumns.add(new Column("doSort", "branchCode", "branchName"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return jo.getJobNumber();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("jobNumber"), "jobNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("businessUnitName"), "buName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("branchCode"), "branchName"));
        return header;
    }

    @Override
    public String getName() {

        return "CEJobOrder";
    }

    @Override
    public Object getNextRow() {
        CEJobOrder obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<CEJobOrder>) list;

    }

}
