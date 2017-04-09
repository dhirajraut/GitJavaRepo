package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobContractNote generated by hbm2java
 */
public class JobContractNote  implements java.io.Serializable {

    // Fields    

     private long id;
     @Length(min=0, max=1024) private String note;
     @NotBlank @Length(min=0, max=128) private String jobNumber;
     @NotBlank @Length(min=0, max=15) private String contractCode;
     @Length(min=0, max=128) private String noteSummary;
     @Length(min=0, max=15) private String noteType;
     @Length(min=0, max=15) private String noteVisibility;
     @Length(min=0, max=15) private String noteOrigin;
     @Length(min=0, max=15) private String relatedLine;
     @Length(min=0, max=10) private String noteCategory;
     private Date dateTimeAdded;
     @Length(min=0, max=128) private String addedByUserName;
     private long jobContractId;
     private User addedBy;
     private JobOrder jobOrder;
     private Contract contract;
     private JobContract jobContract;

     // Constructors

    /** default constructor */
    public JobContractNote() {
    }

	/** minimal constructor */
    public JobContractNote(String jobNumber, String contractCode) {
        this.jobNumber = jobNumber;
        this.contractCode = contractCode;
    }
    /** full constructor */
    public JobContractNote(String note, String jobNumber, String contractCode, String noteSummary, String noteType, String noteVisibility, String noteOrigin, String relatedLine, String noteCategory, Date dateTimeAdded, String addedByUserName, long jobContractId, User addedBy, JobOrder jobOrder, Contract contract, JobContract jobContract) {
       this.note = note;
       this.jobNumber = jobNumber;
       this.contractCode = contractCode;
       this.noteSummary = noteSummary;
       this.noteType = noteType;
       this.noteVisibility = noteVisibility;
       this.noteOrigin = noteOrigin;
       this.relatedLine = relatedLine;
       this.noteCategory = noteCategory;
       this.dateTimeAdded = dateTimeAdded;
       this.addedByUserName = addedByUserName;
       this.jobContractId = jobContractId;
       this.addedBy = addedBy;
       this.jobOrder = jobOrder;
       this.contract = contract;
       this.jobContract = jobContract;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public String getJobNumber() {
        return this.jobNumber;
    }
    
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
    public String getContractCode() {
        return this.contractCode;
    }
    
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    public String getNoteSummary() {
        return this.noteSummary;
    }
    
    public void setNoteSummary(String noteSummary) {
        this.noteSummary = noteSummary;
    }
    public String getNoteType() {
        return this.noteType;
    }
    
    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
    public String getNoteVisibility() {
        return this.noteVisibility;
    }
    
    public void setNoteVisibility(String noteVisibility) {
        this.noteVisibility = noteVisibility;
    }
    public String getNoteOrigin() {
        return this.noteOrigin;
    }
    
    public void setNoteOrigin(String noteOrigin) {
        this.noteOrigin = noteOrigin;
    }
    public String getRelatedLine() {
        return this.relatedLine;
    }
    
    public void setRelatedLine(String relatedLine) {
        this.relatedLine = relatedLine;
    }
    public String getNoteCategory() {
        return this.noteCategory;
    }
    
    public void setNoteCategory(String noteCategory) {
        this.noteCategory = noteCategory;
    }
    public Date getDateTimeAdded() {
        return this.dateTimeAdded;
    }
    
    public void setDateTimeAdded(Date dateTimeAdded) {
        this.dateTimeAdded = dateTimeAdded;
    }
    public String getAddedByUserName() {
        return this.addedByUserName;
    }
    
    public void setAddedByUserName(String addedByUserName) {
        this.addedByUserName = addedByUserName;
    }
    public long getJobContractId() {
        return this.jobContractId;
    }
    
    public void setJobContractId(long jobContractId) {
        this.jobContractId = jobContractId;
    }
    public User getAddedBy() {
        return this.addedBy;
    }
    
    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
    public JobOrder getJobOrder() {
        return this.jobOrder;
    }
    
    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }
    public Contract getContract() {
        return this.contract;
    }
    
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    public JobContract getJobContract() {
        return this.jobContract;
    }
    
    public void setJobContract(JobContract jobContract) {
        this.jobContract = jobContract;
    }




}


