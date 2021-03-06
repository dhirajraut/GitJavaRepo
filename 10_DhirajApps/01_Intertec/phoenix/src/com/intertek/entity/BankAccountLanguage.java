package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BankAccountLanguage generated by hbm2java
 */
public class BankAccountLanguage  implements java.io.Serializable {

    // Fields    

     private BankAccountLangId bankAccountLangId;
     @NotBlank String bankAcctDesc;
     private BankAccount bankAccount;

     // Constructors

    /** default constructor */
    public BankAccountLanguage() {
    }

	/** minimal constructor */
    public BankAccountLanguage(BankAccountLangId bankAccountLangId, String bankAcctDesc) {
        this.bankAccountLangId = bankAccountLangId;
        this.bankAcctDesc = bankAcctDesc;
    }
    /** full constructor */
    public BankAccountLanguage(BankAccountLangId bankAccountLangId, String bankAcctDesc, BankAccount bankAccount) {
       this.bankAccountLangId = bankAccountLangId;
       this.bankAcctDesc = bankAcctDesc;
       this.bankAccount = bankAccount;
    }
   
    // Property accessors
    public BankAccountLangId getBankAccountLangId() {
        return this.bankAccountLangId;
    }
    
    public void setBankAccountLangId(BankAccountLangId bankAccountLangId) {
        this.bankAccountLangId = bankAccountLangId;
    }
    public String getBankAcctDesc() {
        return this.bankAcctDesc;
    }
    
    public void setBankAcctDesc(String bankAcctDesc) {
        this.bankAcctDesc = bankAcctDesc;
    }
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }
    
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BankAccountLanguage) ) return false;
		 BankAccountLanguage castOther = ( BankAccountLanguage ) other; 
         
		 return ( (this.getBankAccountLangId()==castOther.getBankAccountLangId()) || ( this.getBankAccountLangId()!=null && castOther.getBankAccountLangId()!=null && this.getBankAccountLangId().equals(castOther.getBankAccountLangId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBankAccountLangId() == null ? 0 : this.getBankAccountLangId().hashCode() );
         
         
         return result;
   }   


}


