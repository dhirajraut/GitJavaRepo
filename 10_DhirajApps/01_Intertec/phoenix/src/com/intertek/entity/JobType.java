package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobType generated by hbm2java
 */
public class JobType  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=30) private String jobTypeCode;
     @Length(min=0, max=60) private String jobTypeDesc;
     private Set<Operation> operations = new HashSet<Operation>(0);

     // Constructors

    /** default constructor */
    public JobType() {
    }

	/** minimal constructor */
    public JobType(String jobTypeCode) {
        this.jobTypeCode = jobTypeCode;
    }
    /** full constructor */
    public JobType(String jobTypeCode, String jobTypeDesc, Set<Operation> operations) {
       this.jobTypeCode = jobTypeCode;
       this.jobTypeDesc = jobTypeDesc;
       this.operations = operations;
    }
   
    // Property accessors
    public String getJobTypeCode() {
        return this.jobTypeCode;
    }
    
    public void setJobTypeCode(String jobTypeCode) {
        this.jobTypeCode = jobTypeCode;
    }
    public String getJobTypeDesc() {
        return this.jobTypeDesc;
    }
    
    public void setJobTypeDesc(String jobTypeDesc) {
        this.jobTypeDesc = jobTypeDesc;
    }
    public Set<Operation> getOperations() {
        return this.operations;
    }
    
    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JobType) ) return false;
		 JobType castOther = ( JobType ) other; 
         
		 return ( (this.getJobTypeCode()==castOther.getJobTypeCode()) || ( this.getJobTypeCode()!=null && castOther.getJobTypeCode()!=null && this.getJobTypeCode().equals(castOther.getJobTypeCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJobTypeCode() == null ? 0 : this.getJobTypeCode().hashCode() );
         
         
         return result;
   }   


}


