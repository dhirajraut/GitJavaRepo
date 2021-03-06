package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * PrebillSplit generated by hbm2java
 */
public class PrebillSplit  implements java.io.Serializable {

    // Fields    

     private long id;
     @NotBlank @Length(min=0, max=8) private String branchName;
     private Branch branch;
     private Prebill prebill;
     private double allocPct;
     private Date updateTime;
     @Length(min=0, max=128) private String updatedByUserName;
     private User updatedBy;
     @Length(min=0, max=5) private Integer lineNumber;
     @Length(min=0, max=20) private String uid20;
     private Boolean primaryInd;
     private Double allocAmt;
     private JobOrder jobOrder;

     // Constructors

    /** default constructor */
    public PrebillSplit() {
    }

	/** minimal constructor */
    public PrebillSplit(String branchName, Prebill prebill) {
        this.branchName = branchName;
        this.prebill = prebill;
    }
    /** full constructor */
    public PrebillSplit(String branchName, Branch branch, Prebill prebill, double allocPct, Date updateTime, String updatedByUserName, User updatedBy, Integer lineNumber, String uid20, Boolean primaryInd, Double allocAmt, JobOrder jobOrder) {
       this.branchName = branchName;
       this.branch = branch;
       this.prebill = prebill;
       this.allocPct = allocPct;
       this.updateTime = updateTime;
       this.updatedByUserName = updatedByUserName;
       this.updatedBy = updatedBy;
       this.lineNumber = lineNumber;
       this.uid20 = uid20;
       this.primaryInd = primaryInd;
       this.allocAmt = allocAmt;
       this.jobOrder = jobOrder;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getBranchName() {
        return this.branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public Branch getBranch() {
        return this.branch;
    }
    
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public Prebill getPrebill() {
        return this.prebill;
    }
    
    public void setPrebill(Prebill prebill) {
        this.prebill = prebill;
    }
    public double getAllocPct() {
        return this.allocPct;
    }
    
    public void setAllocPct(double allocPct) {
        this.allocPct = allocPct;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdatedByUserName() {
        return this.updatedByUserName;
    }
    
    public void setUpdatedByUserName(String updatedByUserName) {
        this.updatedByUserName = updatedByUserName;
    }
    public User getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Integer getLineNumber() {
        return this.lineNumber;
    }
    
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    public String getUid20() {
        return this.uid20;
    }
    
    public void setUid20(String uid20) {
        this.uid20 = uid20;
    }
    public Boolean getPrimaryInd() {
        return this.primaryInd;
    }
    
    public void setPrimaryInd(Boolean primaryInd) {
        this.primaryInd = primaryInd;
    }
    public Double getAllocAmt() {
        return this.allocAmt;
    }
    
    public void setAllocAmt(Double allocAmt) {
        this.allocAmt = allocAmt;
    }
    public JobOrder getJobOrder() {
        return this.jobOrder;
    }
    
    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }




}


