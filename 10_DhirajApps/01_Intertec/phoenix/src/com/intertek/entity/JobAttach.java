package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobAttach generated by hbm2java
 */
public class JobAttach  implements java.io.Serializable {

    // Fields    

     private long id;
     @NotBlank @Length(min=0, max=128) private String fileName;
     @NotBlank @Length(min=0, max=128) private String systemFileName;
     @NotBlank @Length(min=0, max=128) private String jobNumber;
     private JobOrder jobOrder;

     // Constructors

    /** default constructor */
    public JobAttach() {
    }

	/** minimal constructor */
    public JobAttach(String fileName, String systemFileName, String jobNumber) {
        this.fileName = fileName;
        this.systemFileName = systemFileName;
        this.jobNumber = jobNumber;
    }
    /** full constructor */
    public JobAttach(String fileName, String systemFileName, String jobNumber, JobOrder jobOrder) {
       this.fileName = fileName;
       this.systemFileName = systemFileName;
       this.jobNumber = jobNumber;
       this.jobOrder = jobOrder;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getSystemFileName() {
        return this.systemFileName;
    }
    
    public void setSystemFileName(String systemFileName) {
        this.systemFileName = systemFileName;
    }
    public String getJobNumber() {
        return this.jobNumber;
    }
    
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
    public JobOrder getJobOrder() {
        return this.jobOrder;
    }
    
    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }




}


