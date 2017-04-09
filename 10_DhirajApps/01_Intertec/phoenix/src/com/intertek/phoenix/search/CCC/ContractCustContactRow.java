/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.CCC;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContractCustContact;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * This class is to show the search result of ContractCustContact.
 *
 * @author lily.sun
 */
public class ContractCustContactRow extends AbstractRow {
    private List<Column> myColumns;
    private ContractCustContact ccc;
    /** The rows. */
    List<ContractCustContact> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /*NOTE: The two constructors must have the columns (header and data) match)*/

    /*
     */
    public ContractCustContactRow(ContractCustContact ccc) {
        this.ccc = ccc;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column(null, ccc.getContractCust().getContract().getContractCode()));
        myColumns.add(new Column(null, ccc.getContractCust().getCustomer().getCustCode()));
        myColumns.add(new Column("customerSelected", ccc.getContractCust().getCustomer().getName()));
        myColumns.add(new Column(null, ccc.getContact().getFirstName() + " " + ccc.getContact().getLastName()));
    }

    /*
     * construct THE header row for CCC search
     */
    public ContractCustContactRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "contractCode", "c.contractCustContactId.contractCode"));
        myColumns.add(new Column("doSort", "customerCode", "c.contractCust.customer.custCode"));
        myColumns.add(new Column("doSort", "customerName", "c.contractCust.customer.name"));
        myColumns.add(new Column("doSort", "scheduler", "c.contact.firstName,c.contact.lastName"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return ccc.getContractCust().getCustomer().getCustCode() + "," + ccc.getContact().getId() + "," + ccc.getContractCust().getContract().getContractCode()
               + "','" + ccc.getContractCust().getContract().getStatus();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("contractCode"), "contractCustContactId.contractCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerCode"), "contractCust.customer.name"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerName"), "contractCust.customer.name"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("scheduler"), "contact.firstName"));
        return header;
    }

    @Override
    public String getName() {
        return "ContractCustContact";
    }

    @Override
    public Object getNextRow() {
        ContractCustContact obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    public void setRows(List<?> list) {
        this.rows = (List<ContractCustContact>) list;
    }

}
