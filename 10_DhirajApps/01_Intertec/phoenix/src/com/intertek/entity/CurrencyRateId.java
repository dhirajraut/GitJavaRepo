package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * CurrencyRateId generated by hbm2java
 */
public class CurrencyRateId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=30) private String rateIndex;
     private Integer term;
     @NotBlank @Length(min=0, max=9) private String fromCurrency;
     @NotBlank @Length(min=0, max=9) private String toCurrency;
     @NotBlank @Length(min=0, max=15) private String type;
     private Date effectiveDate;

     // Constructors

    /** default constructor */
    public CurrencyRateId() {
    }

    /** full constructor */
    public CurrencyRateId(String rateIndex, Integer term, String fromCurrency, String toCurrency, String type, Date effectiveDate) {
       this.rateIndex = rateIndex;
       this.term = term;
       this.fromCurrency = fromCurrency;
       this.toCurrency = toCurrency;
       this.type = type;
       this.effectiveDate = effectiveDate;
    }
   
    // Property accessors
    public String getRateIndex() {
        return this.rateIndex;
    }
    
    public void setRateIndex(String rateIndex) {
        this.rateIndex = rateIndex;
    }
    public Integer getTerm() {
        return this.term;
    }
    
    public void setTerm(Integer term) {
        this.term = term;
    }
    public String getFromCurrency() {
        return this.fromCurrency;
    }
    
    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
    public String getToCurrency() {
        return this.toCurrency;
    }
    
    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CurrencyRateId) ) return false;
		 CurrencyRateId castOther = ( CurrencyRateId ) other; 
         
		 return ( (this.getRateIndex()==castOther.getRateIndex()) || ( this.getRateIndex()!=null && castOther.getRateIndex()!=null && this.getRateIndex().equals(castOther.getRateIndex()) ) )
 && ( (this.getTerm()==castOther.getTerm()) || ( this.getTerm()!=null && castOther.getTerm()!=null && this.getTerm().equals(castOther.getTerm()) ) )
 && ( (this.getFromCurrency()==castOther.getFromCurrency()) || ( this.getFromCurrency()!=null && castOther.getFromCurrency()!=null && this.getFromCurrency().equals(castOther.getFromCurrency()) ) )
 && ( (this.getToCurrency()==castOther.getToCurrency()) || ( this.getToCurrency()!=null && castOther.getToCurrency()!=null && this.getToCurrency().equals(castOther.getToCurrency()) ) )
 && ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
 && ( (this.getEffectiveDate()==castOther.getEffectiveDate()) || ( this.getEffectiveDate()!=null && castOther.getEffectiveDate()!=null && this.getEffectiveDate().equals(castOther.getEffectiveDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRateIndex() == null ? 0 : this.getRateIndex().hashCode() );
         result = 37 * result + ( getTerm() == null ? 0 : this.getTerm().hashCode() );
         result = 37 * result + ( getFromCurrency() == null ? 0 : this.getFromCurrency().hashCode() );
         result = 37 * result + ( getToCurrency() == null ? 0 : this.getToCurrency().hashCode() );
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         result = 37 * result + ( getEffectiveDate() == null ? 0 : this.getEffectiveDate().hashCode() );
         return result;
   }   


}


