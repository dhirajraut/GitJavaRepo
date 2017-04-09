/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.employee;

import java.util.ArrayList;
import java.util.List;

import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author Eric Nguyen
 * 
 */
public class EmployeeRow extends AbstractRow {

    private List<Column> myColumns;
    private Employee employee;
    List<Employee> rows;

    int currentIndex = 0;

    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a UserRow from the given User
     */
    public EmployeeRow(Employee employee) {
        this.employee= employee;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("employeeSelected", employee.getFirstName()));
        myColumns.add(new Column(null, employee.getLastName()));
        myColumns.add(new Column(null, employee.getBusinessUnit()));
        myColumns.add(new Column(null, employee.getOperatingUnit()));
    }

    /*
     * construct THE header row for user search
     */
    public EmployeeRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "firstName", "firstName"));
        myColumns.add(new Column("doSort", "lastName", "lastName"));
        myColumns.add(new Column("doSort", "businessUnit", "businessUnit"));
        myColumns.add(new Column("doSort", "operatingUnit", "operatingUnit"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return employee.getFullName()+"|"+employee.getEmployeeId();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("firstName"), "firstName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("lastName"), "lastName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("businessUnit"), "businessUnit"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("operatingUnit"), "operatingUnit"));
        return header;
    }

    @Override
    public String getName() {
        return "Users";
    }

    @Override
    public Object getNextRow() {
        Employee obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<Employee>) list;
    }
}
