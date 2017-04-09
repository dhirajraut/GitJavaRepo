package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BranchLocation generated by hbm2java
 */
public class BranchLocation  implements java.io.Serializable {

    // Fields    

     private BranchLocationId branchLocationId;
     private Date endDate;
     @Length(min=0, max=1024) private String description;

     // Constructors

    /** default constructor */
    public BranchLocation() {
    }

	/** minimal constructor */
    public BranchLocation(BranchLocationId branchLocationId) {
        this.branchLocationId = branchLocationId;
    }
    /** full constructor */
    public BranchLocation(BranchLocationId branchLocationId, Date endDate, String description) {
       this.branchLocationId = branchLocationId;
       this.endDate = endDate;
       this.description = description;
    }
   
    // Property accessors
    public BranchLocationId getBranchLocationId() {
        return this.branchLocationId;
    }
    
    public void setBranchLocationId(BranchLocationId branchLocationId) {
        this.branchLocationId = branchLocationId;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BranchLocation) ) return false;
		 BranchLocation castOther = ( BranchLocation ) other; 
         
		 return ( (this.getBranchLocationId()==castOther.getBranchLocationId()) || ( this.getBranchLocationId()!=null && castOther.getBranchLocationId()!=null && this.getBranchLocationId().equals(castOther.getBranchLocationId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBranchLocationId() == null ? 0 : this.getBranchLocationId().hashCode() );
         
         
         return result;
   }   


}

