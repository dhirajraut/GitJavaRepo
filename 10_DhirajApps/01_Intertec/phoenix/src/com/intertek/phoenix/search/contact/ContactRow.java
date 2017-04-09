package com.intertek.phoenix.search.contact;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Contact;
import com.intertek.entity.ContractCustContact;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 * @author patni
 * 
 */
public class ContactRow extends AbstractRow {

    private List<Column> myColumns;
    private ContractCustContact ccc;
    /** The rows. */
    List<Contact> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /* NOTE: The two constructors must have the columns (header and data) match) */

    /*
     * construct a ContactRow from the given Warehouse
     */
    public ContactRow(ContractCustContact ccc) {
        this.ccc = ccc;
        myColumns = new ArrayList<Column>();

        myColumns.add(new Column("contactSelected", String.valueOf(ccc.getContact().getId())));
        myColumns.add(new Column(null, ccc.getContact().getFirstName() + " " + ccc.getContact().getLastName()));
    }

    /*
     * construct THE header row for contact search
     */
    public ContactRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "contactID", "id"));
        myColumns.add(new Column("doSort", "contactName", "firstName,lastName"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        return String.valueOf(ccc.getContact().getId());
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("contactID"), "id"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("firstName"), "firstName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("lastName"), "lastName"));
        return header;
    }

    @Override
    public String getName() {
        return "Contact";
    }

    @Override
    public Object getNextRow() {
        Contact obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    public void setRows(List<?> list) {
        this.rows = (List<Contact>) list;
    }
}
