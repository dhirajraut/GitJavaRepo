package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BatchReprint generated by hbm2java
 */
public class BatchReprint  implements java.io.Serializable {

    // Fields    

     private long id;
     @NotBlank @Length(min=0, max=8) private String branchName;
     private Branch branch;
     @Length(min=0, max=128) private String runnedByUserName;
     private User runnedBy;
     private Date runDate;

     // Constructors

    /** default constructor */
    public BatchReprint() {
    }

    /** full constructor */
    public BatchReprint(String branchName, Branch branch, String runnedByUserName, User runnedBy, Date runDate) {
       this.branchName = branchName;
       this.branch = branch;
       this.runnedByUserName = runnedByUserName;
       this.runnedBy = runnedBy;
       this.runDate = runDate;
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
    public String getRunnedByUserName() {
        return this.runnedByUserName;
    }
    
    public void setRunnedByUserName(String runnedByUserName) {
        this.runnedByUserName = runnedByUserName;
    }
    public User getRunnedBy() {
        return this.runnedBy;
    }
    
    public void setRunnedBy(User runnedBy) {
        this.runnedBy = runnedBy;
    }
    public Date getRunDate() {
        return this.runDate;
    }
    
    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }




}


