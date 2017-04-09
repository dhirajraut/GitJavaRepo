package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * ContractCustContactId generated by hbm2java
 */
public class ContractCustContactId  implements java.io.Serializable {

    // Fields    

     private long contactId;
     @NotBlank @Length(min=0, max=15) private String custCode;
     @NotBlank @Length(min=0, max=15) private String contractCode;

     // Constructors

    /** default constructor */
    public ContractCustContactId() {
    }

    /** full constructor */
    public ContractCustContactId(long contactId, String custCode, String contractCode) {
       this.contactId = contactId;
       this.custCode = custCode;
       this.contractCode = contractCode;
    }
   
    // Property accessors
    public long getContactId() {
        return this.contactId;
    }
    
    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
    public String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    public String getContractCode() {
        return this.contractCode;
    }
    
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ContractCustContactId) ) return false;
		 ContractCustContactId castOther = ( ContractCustContactId ) other; 
         
		 return (this.getContactId()==castOther.getContactId())
 && ( (this.getCustCode()==castOther.getCustCode()) || ( this.getCustCode()!=null && castOther.getCustCode()!=null && this.getCustCode().equals(castOther.getCustCode()) ) )
 && ( (this.getContractCode()==castOther.getContractCode()) || ( this.getContractCode()!=null && castOther.getContractCode()!=null && this.getContractCode().equals(castOther.getContractCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getContactId();
         result = 37 * result + ( getCustCode() == null ? 0 : this.getCustCode().hashCode() );
         result = 37 * result + ( getContractCode() == null ? 0 : this.getContractCode().hashCode() );
         return result;
   }   


}

