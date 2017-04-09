package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobSampleMethodId generated by hbm2java
 */
public class JobSampleMethodId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=128) private String jobSampleMethodName;
     private Integer sampSeqId;
     private Integer prodSeqNum;
     @NotBlank @Length(min=0, max=128) private String jobNumber;
     private Integer linkedVslRow;

     // Constructors

    /** default constructor */
    public JobSampleMethodId() {
    }

    /** full constructor */
    public JobSampleMethodId(String jobSampleMethodName, Integer sampSeqId, Integer prodSeqNum, String jobNumber, Integer linkedVslRow) {
       this.jobSampleMethodName = jobSampleMethodName;
       this.sampSeqId = sampSeqId;
       this.prodSeqNum = prodSeqNum;
       this.jobNumber = jobNumber;
       this.linkedVslRow = linkedVslRow;
    }
   
    // Property accessors
    public String getJobSampleMethodName() {
        return this.jobSampleMethodName;
    }
    
    public void setJobSampleMethodName(String jobSampleMethodName) {
        this.jobSampleMethodName = jobSampleMethodName;
    }
    public Integer getSampSeqId() {
        return this.sampSeqId;
    }
    
    public void setSampSeqId(Integer sampSeqId) {
        this.sampSeqId = sampSeqId;
    }
    public Integer getProdSeqNum() {
        return this.prodSeqNum;
    }
    
    public void setProdSeqNum(Integer prodSeqNum) {
        this.prodSeqNum = prodSeqNum;
    }
    public String getJobNumber() {
        return this.jobNumber;
    }
    
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
    public Integer getLinkedVslRow() {
        return this.linkedVslRow;
    }
    
    public void setLinkedVslRow(Integer linkedVslRow) {
        this.linkedVslRow = linkedVslRow;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JobSampleMethodId) ) return false;
		 JobSampleMethodId castOther = ( JobSampleMethodId ) other; 
         
		 return ( (this.getJobSampleMethodName()==castOther.getJobSampleMethodName()) || ( this.getJobSampleMethodName()!=null && castOther.getJobSampleMethodName()!=null && this.getJobSampleMethodName().equals(castOther.getJobSampleMethodName()) ) )
 && ( (this.getSampSeqId()==castOther.getSampSeqId()) || ( this.getSampSeqId()!=null && castOther.getSampSeqId()!=null && this.getSampSeqId().equals(castOther.getSampSeqId()) ) )
 && ( (this.getProdSeqNum()==castOther.getProdSeqNum()) || ( this.getProdSeqNum()!=null && castOther.getProdSeqNum()!=null && this.getProdSeqNum().equals(castOther.getProdSeqNum()) ) )
 && ( (this.getJobNumber()==castOther.getJobNumber()) || ( this.getJobNumber()!=null && castOther.getJobNumber()!=null && this.getJobNumber().equals(castOther.getJobNumber()) ) )
 && ( (this.getLinkedVslRow()==castOther.getLinkedVslRow()) || ( this.getLinkedVslRow()!=null && castOther.getLinkedVslRow()!=null && this.getLinkedVslRow().equals(castOther.getLinkedVslRow()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJobSampleMethodName() == null ? 0 : this.getJobSampleMethodName().hashCode() );
         result = 37 * result + ( getSampSeqId() == null ? 0 : this.getSampSeqId().hashCode() );
         result = 37 * result + ( getProdSeqNum() == null ? 0 : this.getProdSeqNum().hashCode() );
         result = 37 * result + ( getJobNumber() == null ? 0 : this.getJobNumber().hashCode() );
         result = 37 * result + ( getLinkedVslRow() == null ? 0 : this.getLinkedVslRow().hashCode() );
         return result;
   }   


}


