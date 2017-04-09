package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * CustAddrSeqId generated by hbm2java
 */
public class CustAddrSeqId  implements java.io.Serializable {

    // Fields    

     private Integer locationNumber;
     @NotBlank @Length(min=0, max=15) private String custCode;

     // Constructors

    /** default constructor */
    public CustAddrSeqId() {
    }

    /** full constructor */
    public CustAddrSeqId(Integer locationNumber, String custCode) {
       this.locationNumber = locationNumber;
       this.custCode = custCode;
    }
   
    // Property accessors
    public Integer getLocationNumber() {
        return this.locationNumber;
    }
    
    public void setLocationNumber(Integer locationNumber) {
        this.locationNumber = locationNumber;
    }
    public String getCustCode() {
        return this.custCode;
    }
    
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CustAddrSeqId) ) return false;
		 CustAddrSeqId castOther = ( CustAddrSeqId ) other; 
         
		 return ( (this.getLocationNumber()==castOther.getLocationNumber()) || ( this.getLocationNumber()!=null && castOther.getLocationNumber()!=null && this.getLocationNumber().equals(castOther.getLocationNumber()) ) )
 && ( (this.getCustCode()==castOther.getCustCode()) || ( this.getCustCode()!=null && castOther.getCustCode()!=null && this.getCustCode().equals(castOther.getCustCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLocationNumber() == null ? 0 : this.getLocationNumber().hashCode() );
         result = 37 * result + ( getCustCode() == null ? 0 : this.getCustCode().hashCode() );
         return result;
   }   


}


