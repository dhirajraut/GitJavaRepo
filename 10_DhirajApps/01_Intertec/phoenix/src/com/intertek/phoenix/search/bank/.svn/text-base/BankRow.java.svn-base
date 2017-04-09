/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.bank;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Bank;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author patni
 *
 */
public class BankRow extends AbstractRow {

    private List<Column> myColumns;
    private Bank bk;

    /** The rows. */
    List<Bank> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /*NOTE: The two constructors must have the columns (header and data) match)*/

    /*
     * construct a BankRow from the given bank
     */
    public BankRow(Bank bk) {
        this.bk = bk;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("bankSelected", bk.getBankCode()));
        myColumns.add(new Column(null, bk.getBankDesc()));

    }

    /*
     * construct THE header row for bank search
     */
    public BankRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "bankCode", "bankCode"));
        myColumns.add(new Column("doSort", "bankDesc", "bankDesc"));

    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return bk.getBankCode();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("bankCode"), "bankCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("bankCodeDescription"), "bankDesc"));
        return header;
    }

    @Override
    public String getName() {
        return "Branch";
    }

    @Override
    public Object getNextRow() {
        Bank obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<Bank>) list;

    }

}
