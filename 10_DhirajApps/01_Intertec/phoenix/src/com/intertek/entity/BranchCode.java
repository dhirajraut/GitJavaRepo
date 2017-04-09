package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BranchCode generated by hbm2java
 */
public class BranchCode  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String branchCode;
     @NotBlank @Length(min=0, max=45) private String opsCode;
     @NotBlank @Length(min=0, max=45) private String labCode;

     // Constructors

    /** default constructor */
    public BranchCode() {
    }

    /** full constructor */
    public BranchCode(String branchCode, String opsCode, String labCode) {
       this.branchCode = branchCode;
       this.opsCode = opsCode;
       this.labCode = labCode;
    }
   
    // Property accessors
    public String getBranchCode() {
        return this.branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public String getOpsCode() {
        return this.opsCode;
    }
    
    public void setOpsCode(String opsCode) {
        this.opsCode = opsCode;
    }
    public String getLabCode() {
        return this.labCode;
    }
    
    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BranchCode) ) return false;
		 BranchCode castOther = ( BranchCode ) other; 
         
		 return ( (this.getBranchCode()==castOther.getBranchCode()) || ( this.getBranchCode()!=null && castOther.getBranchCode()!=null && this.getBranchCode().equals(castOther.getBranchCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBranchCode() == null ? 0 : this.getBranchCode().hashCode() );
         
         
         return result;
   }   


}


