package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Currency generated by hbm2java
 */
public class Currency  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=3) private String currencyCd;
     @Length(min=0, max=30) private String currencyDescr;
     private Integer decimalDigits;

     // Constructors

    /** default constructor */
    public Currency() {
    }

	/** minimal constructor */
    public Currency(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    /** full constructor */
    public Currency(String currencyCd, String currencyDescr, Integer decimalDigits) {
       this.currencyCd = currencyCd;
       this.currencyDescr = currencyDescr;
       this.decimalDigits = decimalDigits;
    }
   
    // Property accessors
    public String getCurrencyCd() {
        return this.currencyCd;
    }
    
    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    public String getCurrencyDescr() {
        return this.currencyDescr;
    }
    
    public void setCurrencyDescr(String currencyDescr) {
        this.currencyDescr = currencyDescr;
    }
    public Integer getDecimalDigits() {
        return this.decimalDigits;
    }
    
    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Currency) ) return false;
		 Currency castOther = ( Currency ) other; 
         
		 return ( (this.getCurrencyCd()==castOther.getCurrencyCd()) || ( this.getCurrencyCd()!=null && castOther.getCurrencyCd()!=null && this.getCurrencyCd().equals(castOther.getCurrencyCd()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCurrencyCd() == null ? 0 : this.getCurrencyCd().hashCode() );
         
         
         return result;
   }   


}

