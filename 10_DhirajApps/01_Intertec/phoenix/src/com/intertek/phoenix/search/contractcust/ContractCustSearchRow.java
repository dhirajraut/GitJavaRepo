package com.intertek.phoenix.search.contractcust;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContractCust;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

public class ContractCustSearchRow extends AbstractRow {
    private List<Column> myColumns;
    private ContractCust cc;
    private String callerRowIndex;
    /** The rows. */
    List<ContractCust> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a ContractCustRow from the given ContractCust
     */
    public ContractCustSearchRow(ContractCust cc) {
        this.cc = cc;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column(null, cc.getCustomer().getCustCode()));
        myColumns.add(new Column(null, cc.getCustomer().getName()));
        myColumns.add(new Column("contractSelected", cc.getContract().getContractCode()));
    }

    /*
     * construct THE header row for contractcust search
     */
    public ContractCustSearchRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "customerCode", "customer.custCode"));
        myColumns.add(new Column("doSort", "customerName", "customer.name"));
        myColumns.add(new Column("doSort", "contractCode", "contract.contractCode"));
    }

    public String getCallerRowIndex() {
        return callerRowIndex;
    }

    public void setCallerRowIndex(String rowIndex) {
        this.callerRowIndex = rowIndex;
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return callerRowIndex + "','" + cc.getContract().getContractCode() + "','" + cc.getCustomer().getCustCode();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("custCode"), "customer.custCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerName"), "customer.name"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("contractCode"), "contract.contractCode"));
        return header;
    }

    @Override
    public String getName() {
        return "ContractCusts";
    }

    @Override
    public Object getNextRow() {
        ContractCust obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRows(List<?> list) {
        this.rows = (List<ContractCust>) list;
    }
}
