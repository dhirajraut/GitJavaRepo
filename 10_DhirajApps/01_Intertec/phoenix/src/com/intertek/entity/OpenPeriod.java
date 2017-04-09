package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * OpenPeriod generated by hbm2java
 */
public class OpenPeriod  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=5) private String buName;
     private Date openFromDate;
     private Date openToDate;
     private BusinessUnit businessUnit;

     // Constructors

    /** default constructor */
    public OpenPeriod() {
    }

	/** minimal constructor */
    public OpenPeriod(String buName) {
        this.buName = buName;
    }
    /** full constructor */
    public OpenPeriod(String buName, Date openFromDate, Date openToDate, BusinessUnit businessUnit) {
       this.buName = buName;
       this.openFromDate = openFromDate;
       this.openToDate = openToDate;
       this.businessUnit = businessUnit;
    }
   
    // Property accessors
    public String getBuName() {
        return this.buName;
    }
    
    public void setBuName(String buName) {
        this.buName = buName;
    }
    public Date getOpenFromDate() {
        return this.openFromDate;
    }
    
    public void setOpenFromDate(Date openFromDate) {
        this.openFromDate = openFromDate;
    }
    public Date getOpenToDate() {
        return this.openToDate;
    }
    
    public void setOpenToDate(Date openToDate) {
        this.openToDate = openToDate;
    }
    public BusinessUnit getBusinessUnit() {
        return this.businessUnit;
    }
    
    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OpenPeriod) ) return false;
		 OpenPeriod castOther = ( OpenPeriod ) other; 
         
		 return ( (this.getBuName()==castOther.getBuName()) || ( this.getBuName()!=null && castOther.getBuName()!=null && this.getBuName().equals(castOther.getBuName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBuName() == null ? 0 : this.getBuName().hashCode() );
         
         
         
         return result;
   }   


}

