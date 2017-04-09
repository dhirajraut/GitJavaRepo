/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.servicelocation;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ServiceLocation;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author patni
 * 
 */
public class ServiceLocationRow extends AbstractRow {

    private List<Column> myColumns;
    private ServiceLocation sl;
    /** The rows. */
    List<ServiceLocation> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /** The name. */
    String name;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a ServiceLocationRow from the given ServiceLocation
     */
    public ServiceLocationRow(ServiceLocation sl) {
        this.sl = sl;
        myColumns = new ArrayList<Column>();        
        myColumns.add(new Column("servicelocationSelected", sl.getServiceLocationCode()));
        myColumns.add(new Column(null, sl.getName()));
        myColumns.add(new Column(null, sl.getCity()));
        myColumns.add(new Column(null, sl.getFullAddress()));
        myColumns.add(new Column(null, sl.getStateCode()));
        myColumns.add(new Column(null, sl.getCountryCode()));        
    }

    /*
     * construct THE header row for ServiceLocation search
     */
    public ServiceLocationRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "serviceLocationCode", "serviceLocationCode"));
        myColumns.add(new Column("doSort", "name", "name"));
        myColumns.add(new Column("doSort", "city", "city"));
        myColumns.add(new Column("doSort", "address", "address1"));
        myColumns.add(new Column("doSort", "state", "stateCode"));
        myColumns.add(new Column("doSort", "country", "countryCode"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        if (sl != null) {
            return sl.getValue()+ " | " + sl.getServiceLocationCode();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getHeader()
     */
    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("serviceLocationID"), "serviceLocationCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("serviceLocation"), "name"));
        return header;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getName()
     */
    @Override
    public String getName() {
        return "ServiceLocaiton";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getNextRow()
     */
    @Override
    public Object getNextRow() {
        ServiceLocation obj = rows.get(currentIndex++);
        return obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#hasMoreRow()
     */
    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<ServiceLocation>) list;
    }

}
