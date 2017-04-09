package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * TimeZone generated by hbm2java
 */
public class TimeZone  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=8) private String timeZone;
     @Length(min=0, max=50) private String timeZoneDesc;
     @Length(min=0, max=8) private String timeZoneLabel;
     @Length(min=0, max=8) private String utcOffset;

     // Constructors

    /** default constructor */
    public TimeZone() {
    }

	/** minimal constructor */
    public TimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    /** full constructor */
    public TimeZone(String timeZone, String timeZoneDesc, String timeZoneLabel, String utcOffset) {
       this.timeZone = timeZone;
       this.timeZoneDesc = timeZoneDesc;
       this.timeZoneLabel = timeZoneLabel;
       this.utcOffset = utcOffset;
    }
   
    // Property accessors
    public String getTimeZone() {
        return this.timeZone;
    }
    
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    public String getTimeZoneDesc() {
        return this.timeZoneDesc;
    }
    
    public void setTimeZoneDesc(String timeZoneDesc) {
        this.timeZoneDesc = timeZoneDesc;
    }
    public String getTimeZoneLabel() {
        return this.timeZoneLabel;
    }
    
    public void setTimeZoneLabel(String timeZoneLabel) {
        this.timeZoneLabel = timeZoneLabel;
    }
    public String getUtcOffset() {
        return this.utcOffset;
    }
    
    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TimeZone) ) return false;
		 TimeZone castOther = ( TimeZone ) other; 
         
		 return ( (this.getTimeZone()==castOther.getTimeZone()) || ( this.getTimeZone()!=null && castOther.getTimeZone()!=null && this.getTimeZone().equals(castOther.getTimeZone()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTimeZone() == null ? 0 : this.getTimeZone().hashCode() );
         
         
         
         return result;
   }   


}

