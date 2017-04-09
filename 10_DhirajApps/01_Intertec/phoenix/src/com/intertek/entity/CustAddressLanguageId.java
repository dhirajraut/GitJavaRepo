package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * CustAddressLanguageId generated by hbm2java
 */
public class CustAddressLanguageId  implements java.io.Serializable {

    // Fields    

     private long CustAddressId;
     @NotBlank @Length(min=0, max=9) private String languageCD;

     // Constructors

    /** default constructor */
    public CustAddressLanguageId() {
    }

    /** full constructor */
    public CustAddressLanguageId(long CustAddressId, String languageCD) {
       this.CustAddressId = CustAddressId;
       this.languageCD = languageCD;
    }
   
    // Property accessors
    public long getCustAddressId() {
        return this.CustAddressId;
    }
    
    public void setCustAddressId(long CustAddressId) {
        this.CustAddressId = CustAddressId;
    }
    public String getLanguageCD() {
        return this.languageCD;
    }
    
    public void setLanguageCD(String languageCD) {
        this.languageCD = languageCD;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CustAddressLanguageId) ) return false;
		 CustAddressLanguageId castOther = ( CustAddressLanguageId ) other; 
         
		 return (this.getCustAddressId()==castOther.getCustAddressId())
 && ( (this.getLanguageCD()==castOther.getLanguageCD()) || ( this.getLanguageCD()!=null && castOther.getLanguageCD()!=null && this.getLanguageCD().equals(castOther.getLanguageCD()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getCustAddressId();
         result = 37 * result + ( getLanguageCD() == null ? 0 : this.getLanguageCD().hashCode() );
         return result;
   }   


}

