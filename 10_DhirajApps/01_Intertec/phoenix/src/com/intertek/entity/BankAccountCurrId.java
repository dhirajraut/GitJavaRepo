package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BankAccountCurrId generated by hbm2java
 */
public class BankAccountCurrId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String businessUnitName;
     @NotBlank @Length(min=0, max=5) private String bankCode;
     @NotBlank @Length(min=0, max=4) private String bankAcctCode;
     @NotBlank @Length(min=0, max=3) private String currencyCode;

     // Constructors

    /** default constructor */
    public BankAccountCurrId() {
    }

    /** full constructor */
    public BankAccountCurrId(String businessUnitName, String bankCode, String bankAcctCode, String currencyCode) {
       this.businessUnitName = businessUnitName;
       this.bankCode = bankCode;
       this.bankAcctCode = bankAcctCode;
       this.currencyCode = currencyCode;
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
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BankAccountCurrId) ) return false;
		 BankAccountCurrId castOther = ( BankAccountCurrId ) other; 
         
		 return ( (this.getBusinessUnitName()==castOther.getBusinessUnitName()) || ( this.getBusinessUnitName()!=null && castOther.getBusinessUnitName()!=null && this.getBusinessUnitName().equals(castOther.getBusinessUnitName()) ) )
 && ( (this.getBankCode()==castOther.getBankCode()) || ( this.getBankCode()!=null && castOther.getBankCode()!=null && this.getBankCode().equals(castOther.getBankCode()) ) )
 && ( (this.getBankAcctCode()==castOther.getBankAcctCode()) || ( this.getBankAcctCode()!=null && castOther.getBankAcctCode()!=null && this.getBankAcctCode().equals(castOther.getBankAcctCode()) ) )
 && ( (this.getCurrencyCode()==castOther.getCurrencyCode()) || ( this.getCurrencyCode()!=null && castOther.getCurrencyCode()!=null && this.getCurrencyCode().equals(castOther.getCurrencyCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBusinessUnitName() == null ? 0 : this.getBusinessUnitName().hashCode() );
         result = 37 * result + ( getBankCode() == null ? 0 : this.getBankCode().hashCode() );
         result = 37 * result + ( getBankAcctCode() == null ? 0 : this.getBankAcctCode().hashCode() );
         result = 37 * result + ( getCurrencyCode() == null ? 0 : this.getCurrencyCode().hashCode() );
         return result;
   }   


}


