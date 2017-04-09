package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BankAccountId generated by hbm2java
 */
public class BankAccountId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String businessUnitName;
     @NotBlank @Length(min=0, max=5) private String bankCode;
     @NotBlank @Length(min=0, max=4) private String bankAcctCode;

     // Constructors

    /** default constructor */
    public BankAccountId() {
    }

    /** full constructor */
    public BankAccountId(String businessUnitName, String bankCode, String bankAcctCode) {
       this.businessUnitName = businessUnitName;
       this.bankCode = bankCode;
       this.bankAcctCode = bankAcctCode;
    }
   
    // Property accessors
    public String getBusinessUnitName() {
        return this.businessUnitName;
    }
    
    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }
    public String getBankCode() {
        return this.bankCode;
    }
    
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    public String getBankAcctCode() {
        return this.bankAcctCode;
    }
    
    public void setBankAcctCode(String bankAcctCode) {
        this.bankAcctCode = bankAcctCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BankAccountId) ) return false;
		 BankAccountId castOther = ( BankAccountId ) other; 
         
		 return ( (this.getBusinessUnitName()==castOther.getBusinessUnitName()) || ( this.getBusinessUnitName()!=null && castOther.getBusinessUnitName()!=null && this.getBusinessUnitName().equals(castOther.getBusinessUnitName()) ) )
 && ( (this.getBankCode()==castOther.getBankCode()) || ( this.getBankCode()!=null && castOther.getBankCode()!=null && this.getBankCode().equals(castOther.getBankCode()) ) )
 && ( (this.getBankAcctCode()==castOther.getBankAcctCode()) || ( this.getBankAcctCode()!=null && castOther.getBankAcctCode()!=null && this.getBankAcctCode().equals(castOther.getBankAcctCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBusinessUnitName() == null ? 0 : this.getBusinessUnitName().hashCode() );
         result = 37 * result + ( getBankCode() == null ? 0 : this.getBankCode().hashCode() );
         result = 37 * result + ( getBankAcctCode() == null ? 0 : this.getBankAcctCode().hashCode() );
         return result;
   }   


}

