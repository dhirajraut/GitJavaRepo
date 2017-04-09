package com.intertek.phoenix.search.bankaccount;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.BankAccount;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author patni
 *
 */
public class BankAccountRow extends AbstractRow {

    private List<Column> myColumns;
    private BankAccount ba;

    /** The rows. */
    List<BankAccount> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /** The name. */
    String name;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a BankAccountRow from the given bankAccount
     */
    public BankAccountRow(BankAccount ba) {
        this.ba = ba;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column(null, ba.getBankAccountId().getBankCode()));
        myColumns.add(new Column("bankAccountSelected", ba.getBankAccountId().getBankAcctCode()));
        myColumns.add(new Column(null, ba.getBankAcctDesc()));

    }

    /*
     * construct THE header row for bankAccount search
     */
    public BankAccountRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "bankCode", "bankAccountId.bankCode"));
        myColumns.add(new Column("doSort", "bankAcctCode", "bankAccountId.bankAcctCode"));
        myColumns.add(new Column("doSort", "bankAcctDesc", "bankAcctDesc"));

    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        //return ba.getBankAccountId().getBankAcctCode();
        return ba.getBankAccountId().getBankAcctCode()+"|"+ba.getBankAccountId().getBankCode()+"|"+ba.getBankAccountId().getBusinessUnitName();
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("bankCode"), "bankAccountId.bankCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("bankCodeDescription"), "bankAcctDesc"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("bankAccount"), "bankAccountId.bankAcctCode"));
        return header;
    }

    @Override
    public String getName() {
        return "BankAccount";
    }

    @Override
    public Object getNextRow() {
        BankAccount obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    public void setRows(List<?> list) {
        this.rows = (List<BankAccount>) list;
    }
}
