/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.user;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.User;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author patni
 * 
 */
public class UserRow extends AbstractRow {

    private List<Column> myColumns;
    private User user;
    /** The rows. */
    List<User> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a UserRow from the given User
     */
    public UserRow(User user) {
        this.user = user;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("userSelected", user.getLoginName()));
        myColumns.add(new Column(null, user.getFirstName() + " " + user.getLastName()));
        myColumns.add(new Column(null, user.getEmployeeStatus()));
    }

    /*
     * construct THE header row for user search
     */
    public UserRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "loginName", "loginName"));
        myColumns.add(new Column("doSort", "name", "firstName,lastName"));
        myColumns.add(new Column("doSort", "employeeStatus", "employeeStatus"));

    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return user.getLoginName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getHeader()
     */
    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("loginName"), "loginName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("firstName"), "firstName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("lastName"), "lastName"));

        return header;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getName()
     */
    @Override
    public String getName() {
        return "Users";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getNextRow()
     */
    @Override
    public Object getNextRow() {
        User obj = rows.get(currentIndex++);
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
        this.rows = (List<User>) list;
    }
}
