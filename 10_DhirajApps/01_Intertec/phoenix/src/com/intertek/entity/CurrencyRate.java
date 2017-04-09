package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * CurrencyRate generated by hbm2java
 */
public class CurrencyRate  implements java.io.Serializable {

    // Fields    

     private CurrencyRateId currencyRateId;
     private Double rateMult;
     private Double rateDiv;
     private Integer syncId;
     private Date lastUpdateDate;

     // Constructors

    /** default constructor */
    public CurrencyRate() {
    }

	/** minimal constructor */
    public CurrencyRate(CurrencyRateId currencyRateId) {
        this.currencyRateId = currencyRateId;
    }
    /** full constructor */
    public CurrencyRate(CurrencyRateId currencyRateId, Double rateMult, Double rateDiv, Integer syncId, Date lastUpdateDate) {
       this.currencyRateId = currencyRateId;
       this.rateMult = rateMult;
       this.rateDiv = rateDiv;
       this.syncId = syncId;
       this.lastUpdateDate = lastUpdateDate;
    }
   
    // Property accessors
    public CurrencyRateId getCurrencyRateId() {
        return this.currencyRateId;
    }
    
    public void setCurrencyRateId(CurrencyRateId currencyRateId) {
        this.currencyRateId = currencyRateId;
    }
    public Double getRateMult() {
        return this.rateMult;
    }
    
    public void setRateMult(Double rateMult) {
        this.rateMult = rateMult;
    }
    public Double getRateDiv() {
        return this.rateDiv;
    }
    
    public void setRateDiv(Double rateDiv) {
        this.rateDiv = rateDiv;
    }
    public Integer getSyncId() {
        return this.syncId;
    }
    
    public void setSyncId(Integer syncId) {
        this.syncId = syncId;
    }
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }
    
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CurrencyRate) ) return false;
		 CurrencyRate castOther = ( CurrencyRate ) other; 
         
		 return ( (this.getCurrencyRateId()==castOther.getCurrencyRateId()) || ( this.getCurrencyRateId()!=null && castOther.getCurrencyRateId()!=null && this.getCurrencyRateId().equals(castOther.getCurrencyRateId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCurrencyRateId() == null ? 0 : this.getCurrencyRateId().hashCode() );
         
         
         
         
         return result;
   }   


}

