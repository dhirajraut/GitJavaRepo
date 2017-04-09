package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * TaxRateId generated by hbm2java
 */
public class TaxRateId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=8) private String taxCode;
     private Date effDate;

     // Constructors

    /** default constructor */
    public TaxRateId() {
    }

    /** full constructor */
    public TaxRateId(String taxCode, Date effDate) {
       this.taxCode = taxCode;
       this.effDate = effDate;
    }
   
    // Property accessors
    public String getTaxCode() {
        return this.taxCode;
    }
    
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    public Date getEffDate() {
        return this.effDate;
    }
    
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TaxRateId) ) return false;
		 TaxRateId castOther = ( TaxRateId ) other; 
         
		 return ( (this.getTaxCode()==castOther.getTaxCode()) || ( this.getTaxCode()!=null && castOther.getTaxCode()!=null && this.getTaxCode().equals(castOther.getTaxCode()) ) )
 && ( (this.getEffDate()==castOther.getEffDate()) || ( this.getEffDate()!=null && castOther.getEffDate()!=null && this.getEffDate().equals(castOther.getEffDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTaxCode() == null ? 0 : this.getTaxCode().hashCode() );
         result = 37 * result + ( getEffDate() == null ? 0 : this.getEffDate().hashCode() );
         return result;
   }   


}


