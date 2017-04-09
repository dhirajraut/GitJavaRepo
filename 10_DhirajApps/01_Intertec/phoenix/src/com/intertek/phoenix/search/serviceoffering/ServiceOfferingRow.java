/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.serviceoffering;

import java.util.ArrayList;
import java.util.List;

import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * 
 * @version 1.0 June 24, 2009
 * @author Patni
 */

public class ServiceOfferingRow extends AbstractRow {
    private List<Column> myColumns;
    private ServiceOffering serviceOffering;
    private String targetField;
    /** The rows. */
    List<ServiceOffering> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /** The name. */
    String name;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a CustomerRow from the given Customer
     */

    public ServiceOfferingRow(ServiceOffering serviceOffering, String formName, String value) {
        this.serviceOffering = serviceOffering;
        this.targetField = value;
        myColumns = new ArrayList<Column>();

        myColumns.add(new Column(null, serviceOffering.getId() + ""));
        myColumns.add(new Column("serviceofferingSelected", serviceOffering.getDescription()));
    }

    /*
     * construct THE header row for customer search
     */
    public ServiceOfferingRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "id", "id"));
        myColumns.add(new Column("doSort", "description", "description"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        if (serviceOffering.getParentServiceOffering() != null) {
            return serviceOffering.getParentServiceOffering().getDescription();
        }
        else {
            return serviceOffering.getDescription();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getHeader()
     */
    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("id"), "id"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("description"), "description"));
        return header;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getName()
     */
    @Override
    public String getName() {
        return "ServiceOffering";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getNextRow()
     */
    @Override
    public Object getNextRow() {
        ServiceOffering obj = rows.get(currentIndex++);
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
        this.rows = (List<ServiceOffering>) list;
    }

}
