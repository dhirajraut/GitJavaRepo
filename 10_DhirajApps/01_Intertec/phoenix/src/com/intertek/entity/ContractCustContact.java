package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * ContractCustContact generated by hbm2java
 */
public class ContractCustContact  implements java.io.Serializable {

    // Fields    

     private ContractCustContactId contractCustContactId;
     private Boolean billContactFlag;
     private Boolean schedulerContactFlag;
     private Boolean reportContactFlag;
     @Length(min=0, max=60) private String invoiceDeliveryMethod;
     @Length(min=0, max=64) private String invoiceLabelLanguage;
     @Length(min=0, max=64) private String invoiceLineItemLanguage;
     private Contact contact;
     private ContractCust contractCust;
     @Length(min=0, max=1) private String status;

     // Constructors

    /** default constructor */
    public ContractCustContact() {
    }

	/** minimal constructor */
    public ContractCustContact(ContractCustContactId contractCustContactId) {
        this.contractCustContactId = contractCustContactId;
    }
    /** full constructor */
    public ContractCustContact(ContractCustContactId contractCustContactId, Boolean billContactFlag, Boolean schedulerContactFlag, Boolean reportContactFlag, String invoiceDeliveryMethod, String invoiceLabelLanguage, String invoiceLineItemLanguage, Contact contact, ContractCust contractCust, String status) {
       this.contractCustContactId = contractCustContactId;
       this.billContactFlag = billContactFlag;
       this.schedulerContactFlag = schedulerContactFlag;
       this.reportContactFlag = reportContactFlag;
       this.invoiceDeliveryMethod = invoiceDeliveryMethod;
       this.invoiceLabelLanguage = invoiceLabelLanguage;
       this.invoiceLineItemLanguage = invoiceLineItemLanguage;
       this.contact = contact;
       this.contractCust = contractCust;
       this.status = status;
    }
   
    // Property accessors
    public ContractCustContactId getContractCustContactId() {
        return this.contractCustContactId;
    }
    
    public void setContractCustContactId(ContractCustContactId contractCustContactId) {
        this.contractCustContactId = contractCustContactId;
    }
    public Boolean getBillContactFlag() {
        return this.billContactFlag;
    }
    
    public void setBillContactFlag(Boolean billContactFlag) {
        this.billContactFlag = billContactFlag;
    }
    public Boolean getSchedulerContactFlag() {
        return this.schedulerContactFlag;
    }
    
    public void setSchedulerContactFlag(Boolean schedulerContactFlag) {
        this.schedulerContactFlag = schedulerContactFlag;
    }
    public Boolean getReportContactFlag() {
        return this.reportContactFlag;
    }
    
    public void setReportContactFlag(Boolean reportContactFlag) {
        this.reportContactFlag = reportContactFlag;
    }
    public String getInvoiceDeliveryMethod() {
        return this.invoiceDeliveryMethod;
    }
    public void setInvoiceDeliveryMethod(String invoiceDeliveryMethod) {
        this.invoiceDeliveryMethod = invoiceDeliveryMethod;
    }
    public String getInvoiceLabelLanguage() {
        return this.invoiceLabelLanguage;
    }
    public void setInvoiceLabelLanguage(String invoiceLabelLanguage) {
        this.invoiceLabelLanguage = invoiceLabelLanguage;
    }
    public String getInvoiceLineItemLanguage() {
        return this.invoiceLineItemLanguage;
    }
    public void setInvoiceLineItemLanguage(String invoiceLineItemLanguage) {
        this.invoiceLineItemLanguage = invoiceLineItemLanguage;
    }
    public Contact getContact() {
        return this.contact;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public ContractCust getContractCust() {
        return this.contractCust;
    }
    
    public void setContractCust(ContractCust contractCust) {
        this.contractCust = contractCust;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ContractCustContact) ) return false;
		 ContractCustContact castOther = ( ContractCustContact ) other; 
         
		 return ( (this.getContractCustContactId()==castOther.getContractCustContactId()) || ( this.getContractCustContactId()!=null && castOther.getContractCustContactId()!=null && this.getContractCustContactId().equals(castOther.getContractCustContactId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContractCustContactId() == null ? 0 : this.getContractCustContactId().hashCode() );
         
         
         
         return result;
   }   


}

