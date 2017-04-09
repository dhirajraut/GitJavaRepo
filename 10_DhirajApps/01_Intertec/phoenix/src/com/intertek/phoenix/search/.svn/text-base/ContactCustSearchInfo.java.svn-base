package com.intertek.phoenix.search;

public class ContactCustSearchInfo {

    private String custCode;
    private String customerName;
    private long contactId;
    private String contactName;
    
    public ContactCustSearchInfo() {
    }

    public ContactCustSearchInfo(String custCode, String customerName, String contactId, String contactName) {
        this.custCode = custCode;
        this.customerName = customerName;
        long cId = 0;
        try {
            cId = Long.parseLong(contactId);
        }catch(Exception e){
        }
        this.contactId = cId;
        this.contactName = contactName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
