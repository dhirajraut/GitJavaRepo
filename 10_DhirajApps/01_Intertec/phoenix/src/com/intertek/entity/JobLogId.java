package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta6a


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobLogId generated by hbm2java
 */
public class JobLogId  implements java.io.Serializable {

    // Fields    

     private long jobContractId;
     private String jobNumber;

     // Constructors

    /** default constructor */
    public JobLogId() {
    }

    /** full constructor */
    public JobLogId(long jobContractId, String jobNumber) {
       this.jobContractId = jobContractId;
       this.jobNumber = jobNumber;
    }
   
    // Property accessors
    public long getJobContractId() {
        return this.jobContractId;
    }
    
    public void setJobContractId(long jobContractId) {
        this.jobContractId = jobContractId;
    }
    public String getJobNumber() {
        return this.jobNumber;
    }
    
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JobLogId) ) return false;
		 JobLogId castOther = ( JobLogId ) other; 
         
		 return (this.getJobContractId()==castOther.getJobContractId())
 && ( (this.getJobNumber()==castOther.getJobNumber()) || ( this.getJobNumber()!=null && castOther.getJobNumber()!=null && this.getJobNumber().equals(castOther.getJobNumber()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getJobContractId();
         result = 37 * result + ( getJobNumber() == null ? 0 : this.getJobNumber().hashCode() );
         return result;
   }   


}

