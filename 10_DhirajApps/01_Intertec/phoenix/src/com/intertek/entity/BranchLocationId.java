package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BranchLocationId generated by hbm2java
 */
public class BranchLocationId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String contractId;
     @NotBlank @Length(min=0, max=105) private String location;
     @NotBlank @Length(min=0, max=15) private String branchSetId;
     @NotBlank @Length(min=0, max=45) private String branchCode;
     private Date beginDate;

     // Constructors

    /** default constructor */
    public BranchLocationId() {
    }

    /** full constructor */
    public BranchLocationId(String contractId, String location, String branchSetId, String branchCode, Date beginDate) {
       this.contractId = contractId;
       this.location = location;
       this.branchSetId = branchSetId;
       this.branchCode = branchCode;
       this.beginDate = beginDate;
    }
   
    // Property accessors
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public String getBranchSetId() {
        return this.branchSetId;
    }
    
    public void setBranchSetId(String branchSetId) {
        this.branchSetId = branchSetId;
    }
    public String getBranchCode() {
        return this.branchCode;
    }
    
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BranchLocationId) ) return false;
		 BranchLocationId castOther = ( BranchLocationId ) other; 
         
		 return ( (this.getContractId()==castOther.getContractId()) || ( this.getContractId()!=null && castOther.getContractId()!=null && this.getContractId().equals(castOther.getContractId()) ) )
 && ( (this.getLocation()==castOther.getLocation()) || ( this.getLocation()!=null && castOther.getLocation()!=null && this.getLocation().equals(castOther.getLocation()) ) )
 && ( (this.getBranchSetId()==castOther.getBranchSetId()) || ( this.getBranchSetId()!=null && castOther.getBranchSetId()!=null && this.getBranchSetId().equals(castOther.getBranchSetId()) ) )
 && ( (this.getBranchCode()==castOther.getBranchCode()) || ( this.getBranchCode()!=null && castOther.getBranchCode()!=null && this.getBranchCode().equals(castOther.getBranchCode()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContractId() == null ? 0 : this.getContractId().hashCode() );
         result = 37 * result + ( getLocation() == null ? 0 : this.getLocation().hashCode() );
         result = 37 * result + ( getBranchSetId() == null ? 0 : this.getBranchSetId().hashCode() );
         result = 37 * result + ( getBranchCode() == null ? 0 : this.getBranchCode().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         return result;
   }   


}


