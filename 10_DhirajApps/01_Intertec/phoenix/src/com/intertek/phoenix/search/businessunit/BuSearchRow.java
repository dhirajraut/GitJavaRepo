package com.intertek.phoenix.search.businessunit;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.BusinessUnit;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

public class BuSearchRow extends AbstractRow {

    private List<Column> myColumns;
    private BusinessUnit bu;

    /** The rows. */
    List<BusinessUnit> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a BusinessUnit from the given BusinessUnit
     */
    public BuSearchRow(BusinessUnit bu) {
        this.bu = bu;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("buSelected", bu.getName()));
        myColumns.add(new Column(null, bu.getDescription()));

    }

    /*
     * construct THE header row for BusinessUnit search
     */
    public BuSearchRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "name", "name"));
        myColumns.add(new Column("doSort", "description", "description"));

    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return bu.getName();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("name"), "name"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("Description"), "description"));
        return header;
    }

    @Override
    public String getName() {
        return "BusinessUnit";
    }

    @Override
    public Object getNextRow() {
        BusinessUnit obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<BusinessUnit>) list;

    }
}
