/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 
 * @author richard.qin
 */
@Entity
@Table(name="PHX_SAMPLE_TRACKING")
public class SampleTracking {
    @Id
    @SequenceGenerator(name="SampleTracking_seq_gen", sequenceName = "PHX_SampleTracking_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SampleTracking_seq_gen" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DESCRIPTION", length = 1024)
    private String description;
    
    @Column(name = "SAMPLE_CODE", length = 20)
    private String sampleCode;
    
    @Column(name = "COUNT_SENT")
    private int countSent;
    
    @Column(name = "COUNT_RECEIVED")
    private int countReceived;
    
    @Column(name = "DATE_SENT")
    private Timestamp dateSent;
    
    @Column(name = "DATE_RECEIVED")
    private Timestamp dateReceived;
    
    @Column(name = "STORAGE_LOCATION", length = 100)
    private String storageLocation;
    
    @Column(name = "LAST_MODIFIED")
    private Timestamp lastModified;
    
    @Column(name = "JOB_CONTRACT_ID", updatable = false, insertable = false)
    private Long jobContractId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_CONTRACT_ID", referencedColumnName="ID")
    @Index(name="PHX_SMP_TRK_LVL_IDX_JC_ID")
    private CEJobContract jobContract;
    
    
    public SampleTracking(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public int getCountSent() {
        return countSent;
    }

    public void setCountSent(int countSent) {
        this.countSent = countSent;
    }

    public int getCountReceived() {
        return countReceived;
    }

    public void setCountReceived(int countReceived) {
        this.countReceived = countReceived;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    public void setDateSent(Timestamp dateSent) {
        this.dateSent = dateSent;
    }

    public Timestamp getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Timestamp dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Long getJobContractId() {
        return jobContractId;
    }

    public void setJobContractId(Long jobContractId) {
        this.jobContractId = jobContractId;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }
}