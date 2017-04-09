package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobContractVessel generated by hbm2java
 */
public class JobContractVessel  implements java.io.Serializable {

    // Fields    

     private long id;
     private JobContract jobContract;
     @Length(min=0, max=128) private String jobNumber;
     private Integer linkedVslRow;
     @NotBlank @Length(min=0, max=256) private String vesselName;
     @NotBlank @Length(min=0, max=16) private String type;
     private Integer sortOrderNum;
     private Set<JobContractProd> jobContractProds = new HashSet<JobContractProd>(0);
     private Set<JobContractVesselService> jobContractVesselServices = new HashSet<JobContractVesselService>(0);
     private Set<JobContractVesselControl> controls = new HashSet<JobContractVesselControl>(0);
     private Set<JobContractVesselInspectionResult> results = new HashSet<JobContractVesselInspectionResult>(0);

     // Constructors

    /** default constructor */
    public JobContractVessel() {
    }

	/** minimal constructor */
    public JobContractVessel(String vesselName, String type) {
        this.vesselName = vesselName;
        this.type = type;
    }
    /** full constructor */
    public JobContractVessel(JobContract jobContract, String jobNumber, Integer linkedVslRow, String vesselName, String type, Integer sortOrderNum, Set<JobContractProd> jobContractProds, Set<JobContractVesselService> jobContractVesselServices, Set<JobContractVesselControl> controls, Set<JobContractVesselInspectionResult> results) {
       this.jobContract = jobContract;
       this.jobNumber = jobNumber;
       this.linkedVslRow = linkedVslRow;
       this.vesselName = vesselName;
       this.type = type;
       this.sortOrderNum = sortOrderNum;
       this.jobContractProds = jobContractProds;
       this.jobContractVesselServices = jobContractVesselServices;
       this.controls = controls;
       this.results = results;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public JobContract getJobContract() {
        return this.jobContract;
    }
    
    public void setJobContract(JobContract jobContract) {
        this.jobContract = jobContract;
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
    public String getVesselName() {
        return this.vesselName;
    }
    
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Integer getSortOrderNum() {
        return this.sortOrderNum;
    }
    
    public void setSortOrderNum(Integer sortOrderNum) {
        this.sortOrderNum = sortOrderNum;
    }
    public Set<JobContractProd> getJobContractProds() {
        return this.jobContractProds;
    }
    
    public void setJobContractProds(Set<JobContractProd> jobContractProds) {
        this.jobContractProds = jobContractProds;
    }
    public Set<JobContractVesselService> getJobContractVesselServices() {
        return this.jobContractVesselServices;
    }
    
    public void setJobContractVesselServices(Set<JobContractVesselService> jobContractVesselServices) {
        this.jobContractVesselServices = jobContractVesselServices;
    }
    public Set<JobContractVesselControl> getControls() {
        return this.controls;
    }
    
    public void setControls(Set<JobContractVesselControl> controls) {
        this.controls = controls;
    }
    public Set<JobContractVesselInspectionResult> getResults() {
        return this.results;
    }
    
    public void setResults(Set<JobContractVesselInspectionResult> results) {
        this.results = results;
    }




}


