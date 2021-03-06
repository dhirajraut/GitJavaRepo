package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * AutoNumber generated by hbm2java
 */
public class AutoNumber  implements java.io.Serializable {

    // Fields    

     private AutoNumberId autoNumberId;
     private int versionNum;
     @NotBlank @Length(min=0, max=3) private String beginSequence;
     private int maxLength;
     private long lastAutoNumber;
     private Date lastUpdatedDate;

     // Constructors

    /** default constructor */
    public AutoNumber() {
    }

	/** minimal constructor */
    public AutoNumber(AutoNumberId autoNumberId, String beginSequence) {
        this.autoNumberId = autoNumberId;
        this.beginSequence = beginSequence;
    }
    /** full constructor */
    public AutoNumber(AutoNumberId autoNumberId, String beginSequence, int maxLength, long lastAutoNumber, Date lastUpdatedDate) {
       this.autoNumberId = autoNumberId;
       this.beginSequence = beginSequence;
       this.maxLength = maxLength;
       this.lastAutoNumber = lastAutoNumber;
       this.lastUpdatedDate = lastUpdatedDate;
    }
   
    // Property accessors
    public AutoNumberId getAutoNumberId() {
        return this.autoNumberId;
    }
    
    public void setAutoNumberId(AutoNumberId autoNumberId) {
        this.autoNumberId = autoNumberId;
    }
    public int getVersionNum() {
        return this.versionNum;
    }
    
    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }
    public String getBeginSequence() {
        return this.beginSequence;
    }
    
    public void setBeginSequence(String beginSequence) {
        this.beginSequence = beginSequence;
    }
    public int getMaxLength() {
        return this.maxLength;
    }
    
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    public long getLastAutoNumber() {
        return this.lastAutoNumber;
    }
    
    public void setLastAutoNumber(long lastAutoNumber) {
        this.lastAutoNumber = lastAutoNumber;
    }
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AutoNumber) ) return false;
		 AutoNumber castOther = ( AutoNumber ) other; 
         
		 return ( (this.getAutoNumberId()==castOther.getAutoNumberId()) || ( this.getAutoNumberId()!=null && castOther.getAutoNumberId()!=null && this.getAutoNumberId().equals(castOther.getAutoNumberId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAutoNumberId() == null ? 0 : this.getAutoNumberId().hashCode() );
         
         
         
         
         
         return result;
   }   


}


