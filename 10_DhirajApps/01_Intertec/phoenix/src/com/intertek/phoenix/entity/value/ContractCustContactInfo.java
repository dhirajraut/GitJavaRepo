package com.intertek.phoenix.entity.value;

public class ContractCustContactInfo {
    private String custCode ;
    private long contactId ;
    private String contractCode ;
    
    public String getCustCode() {
        return custCode;
    }
    
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public long getContactId() {
        return contactId;
    }
    
    public void setContactId(long id) {
        this.contactId = id;
    }
    
    public String getContractCode() {
        return contractCode;
    }
    
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
}
