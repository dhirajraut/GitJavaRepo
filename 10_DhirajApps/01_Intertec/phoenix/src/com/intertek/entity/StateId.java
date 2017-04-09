package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * StateId generated by hbm2java
 */
public class StateId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=6) private String stateCode;
     @NotBlank @Length(min=0, max=3) private String countryCode;

     // Constructors

    /** default constructor */
    public StateId() {
    }

    /** full constructor */
    public StateId(String stateCode, String countryCode) {
       this.stateCode = stateCode;
       this.countryCode = countryCode;
    }
   
    // Property accessors
    public String getStateCode() {
        return this.stateCode;
    }
    
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof StateId) ) return false;
		 StateId castOther = ( StateId ) other; 
         
		 return ( (this.getStateCode()==castOther.getStateCode()) || ( this.getStateCode()!=null && castOther.getStateCode()!=null && this.getStateCode().equals(castOther.getStateCode()) ) )
 && ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStateCode() == null ? 0 : this.getStateCode().hashCode() );
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         return result;
   }   


}


