package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BusStreamId generated by hbm2java
 */
public class BusStreamId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String branchCode;
     @NotBlank @Length(min=0, max=105) private String busStreamCode;

     // Constructors

    /** default constructor */
    public BusStreamId() {
    }

    /** full constructor */
    public BusStreamId(String branchCode, String busStreamCode) {
       this.branchCode = branchCode;
       this.busStreamCode = busStreamCode;
    }
   
    // Property accessors
    public String getBranchCode() {
        return this.branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public String getBusStreamCode() {
        return this.busStreamCode;
    }
    
    public void setBusStreamCode(String busStreamCode) {
        this.busStreamCode = busStreamCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BusStreamId) ) return false;
		 BusStreamId castOther = ( BusStreamId ) other; 
         
		 return ( (this.getBranchCode()==castOther.getBranchCode()) || ( this.getBranchCode()!=null && castOther.getBranchCode()!=null && this.getBranchCode().equals(castOther.getBranchCode()) ) )
 && ( (this.getBusStreamCode()==castOther.getBusStreamCode()) || ( this.getBusStreamCode()!=null && castOther.getBusStreamCode()!=null && this.getBusStreamCode().equals(castOther.getBusStreamCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBranchCode() == null ? 0 : this.getBranchCode().hashCode() );
         result = 37 * result + ( getBusStreamCode() == null ? 0 : this.getBusStreamCode().hashCode() );
         return result;
   }   


}

