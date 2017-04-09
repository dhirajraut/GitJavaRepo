package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * AssocBranch generated by hbm2java
 */
public class AssocBranch  implements java.io.Serializable {

    // Fields    

     private AssocBranchId assocBranchId;
     private Branch assocBranch;

     // Constructors

    /** default constructor */
    public AssocBranch() {
    }

	/** minimal constructor */
    public AssocBranch(AssocBranchId assocBranchId) {
        this.assocBranchId = assocBranchId;
    }
    /** full constructor */
    public AssocBranch(AssocBranchId assocBranchId, Branch assocBranch) {
       this.assocBranchId = assocBranchId;
       this.assocBranch = assocBranch;
    }
   
    // Property accessors
    public AssocBranchId getAssocBranchId() {
        return this.assocBranchId;
    }
    
    public void setAssocBranchId(AssocBranchId assocBranchId) {
        this.assocBranchId = assocBranchId;
    }
    public Branch getAssocBranch() {
        return this.assocBranch;
    }
    
    public void setAssocBranch(Branch assocBranch) {
        this.assocBranch = assocBranch;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AssocBranch) ) return false;
		 AssocBranch castOther = ( AssocBranch ) other; 
         
		 return ( (this.getAssocBranchId()==castOther.getAssocBranchId()) || ( this.getAssocBranchId()!=null && castOther.getAssocBranchId()!=null && this.getAssocBranchId().equals(castOther.getAssocBranchId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAssocBranchId() == null ? 0 : this.getAssocBranchId().hashCode() );
         
         return result;
   }   


}

