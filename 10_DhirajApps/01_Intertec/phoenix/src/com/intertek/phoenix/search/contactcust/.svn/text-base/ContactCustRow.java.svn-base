package com.intertek.phoenix.search.contactcust;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.intertek.entity.ContactCust;
import com.intertek.entity.CustAddress;
import com.intertek.export.template.ExportColumn;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Column;
import com.intertek.phoenix.util.CommonUtil;

/**
 *
 * @author lily.sun
 */

public class ContactCustRow extends AbstractRow {

    private List<Column> myColumns;
    private ContactCust contactCust;
    private String callerRowIndex;

    /** The rows. */
    List<ContactCust> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    public ContactCustRow(ContactCust cc) {
        this.contactCust = cc;
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column(null, cc.getCustomer().getCustCode()));
        myColumns.add(new Column(null, cc.getCustomer().getName()));
        myColumns.add(new Column("customerSelected", cc.getContact().getId() + ""));
        myColumns.add(new Column(null, cc.getContact().getFirstName() + " " + cc.getContact().getLastName()));
    }

    /*
     * construct THE header row for CCC search
     */
    public ContactCustRow() {
        myColumns = new ArrayList<Column>();
        myColumns.add(new Column("doSort", "customerID", "c.customer.custCode"));
        myColumns.add(new Column("doSort", "customerName", "c.customer.name"));
        myColumns.add(new Column("doSort", "contactID", "c.contact.id"));
        myColumns.add(new Column("doSort", "contactName", "c.contact.firstName, c.contact.lastName"));
    }

    @Override
    public List<Column> getColumns() {
        return myColumns;
    }

    @Override
    public String getRowId() {
        Set<CustAddress> custAddresses = contactCust.getCustAddrSeq().getCustAddresses();
        String address = "";
        long addressId = 0;
        for (CustAddress custAddress : custAddresses) {
            address = custAddress.getFullAddress();
            addressId = custAddress.getId();
        }
        return callerRowIndex + "','" + contactCust.getCustomer().getCustCode() + "','" + contactCust.getContact().getId() + "','"
               + contactCust.getContact().getFirstName() + " " + contactCust.getContact().getLastName() + "','" + addressId + "','" + address;
    }

    public String getCallerRowIndex() {
        return callerRowIndex;
    }

    public void setCallerRowIndex(String rowIndex) {
        this.callerRowIndex = rowIndex;
    }

    @Override
    public List<ExportColumn> getHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerID"), "customer.custCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerName"), "customer.name"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("contactID"), "contact.id"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("contactName"), "contact.firstName"));
        return header;
    }

    @Override
    public String getName() {
        return "ContactCust";
    }

    @Override
    public Object getNextRow() {
        ContactCust obj = rows.get(currentIndex++);
        return obj;
    }

    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    @SuppressWarnings("unchecked")
    public void setRows(List<?> list) {
        this.rows = (List<ContactCust>) list;
    }

}
