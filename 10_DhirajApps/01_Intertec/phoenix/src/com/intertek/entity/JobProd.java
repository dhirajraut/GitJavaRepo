package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobProd generated by hbm2java
 */
public class JobProd  implements java.io.Serializable {

    // Fields    

     private JobProdId jobProdId;
     private JobVessel jobVessel;
     private String jobProductName;
     @Length(min=0, max=256) private String destination;
     @NotBlank @Length(min=0, max=40) private String group;
     @Length(min=0, max=4) private String option;
     @Length(min=0, max=4) private String plusMinus;
     @Length(min=0, max=64) private String drafts;
     @Length(min=0, max=256) private String tanks;
     private int plusMinusPct;
     private double productQty;
     @Length(min=0, max=4) private String uom;
     private Set<JobProdSample> jobProdSamples = new HashSet<JobProdSample>(0);
     private Set<JobEvent> jobEvents = new HashSet<JobEvent>(0);
     private Set<JobInspectionEvent> jobInspectionEvents = new HashSet<JobInspectionEvent>(0);
     private Set<JobContract> jobProdContracts = new HashSet<JobContract>(0);
     private Set<JobProdQty> jobProdQtys = new HashSet<JobProdQty>(0);

     // Constructors

    /** default constructor */
    public JobProd() {
    }

	/** minimal constructor */
    public JobProd(JobProdId jobProdId, String jobProductName, String group) {
        this.jobProdId = jobProdId;
        this.jobProductName = jobProductName;
        this.group = group;
    }
    /** full constructor */
    public JobProd(JobProdId jobProdId, JobVessel jobVessel, String jobProductName, String destination, String group, String option, String plusMinus, String drafts, String tanks, int plusMinusPct, double productQty, String uom, Set<JobProdSample> jobProdSamples, Set<JobEvent> jobEvents, Set<JobInspectionEvent> jobInspectionEvents, Set<JobContract> jobProdContracts, Set<JobProdQty> jobProdQtys) {
       this.jobProdId = jobProdId;
       this.jobVessel = jobVessel;
       this.jobProductName = jobProductName;
       this.destination = destination;
       this.group = group;
       this.option = option;
       this.plusMinus = plusMinus;
       this.drafts = drafts;
       this.tanks = tanks;
       this.plusMinusPct = plusMinusPct;
       this.productQty = productQty;
       this.uom = uom;
       this.jobProdSamples = jobProdSamples;
       this.jobEvents = jobEvents;
       this.jobInspectionEvents = jobInspectionEvents;
       this.jobProdContracts = jobProdContracts;
       this.jobProdQtys = jobProdQtys;
    }
   
    // Property accessors
    public JobProdId getJobProdId() {
        return this.jobProdId;
    }
    
    public void setJobProdId(JobProdId jobProdId) {
        this.jobProdId = jobProdId;
    }
    public JobVessel getJobVessel() {
        return this.jobVessel;
    }
    
    public void setJobVessel(JobVessel jobVessel) {
        this.jobVessel = jobVessel;
    }
    public String getJobProductName() {
        return this.jobProductName;
    }
    
    public void setJobProductName(String jobProductName) {
        this.jobProductName = jobProductName;
    }
    public String getDestination() {
        return this.destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getGroup() {
        return this.group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
    public String getOption() {
        return this.option;
    }
    
    public void setOption(String option) {
        this.option = option;
    }
    public String getPlusMinus() {
        return this.plusMinus;
    }
    
    public void setPlusMinus(String plusMinus) {
        this.plusMinus = plusMinus;
    }
    public String getDrafts() {
        return this.drafts;
    }
    
    public void setDrafts(String drafts) {
        this.drafts = drafts;
    }
    public String getTanks() {
        return this.tanks;
    }
    
    public void setTanks(String tanks) {
        this.tanks = tanks;
    }
    public int getPlusMinusPct() {
        return this.plusMinusPct;
    }
    
    public void setPlusMinusPct(int plusMinusPct) {
        this.plusMinusPct = plusMinusPct;
    }
    public double getProductQty() {
        return this.productQty;
    }
    
    public void setProductQty(double productQty) {
        this.productQty = productQty;
    }
    public String getUom() {
        return this.uom;
    }
    
    public void setUom(String uom) {
        this.uom = uom;
    }
    public Set<JobProdSample> getJobProdSamples() {
        return this.jobProdSamples;
    }
    
    public void setJobProdSamples(Set<JobProdSample> jobProdSamples) {
        this.jobProdSamples = jobProdSamples;
    }
    public Set<JobEvent> getJobEvents() {
        return this.jobEvents;
    }
    
    public void setJobEvents(Set<JobEvent> jobEvents) {
        this.jobEvents = jobEvents;
    }
    public Set<JobInspectionEvent> getJobInspectionEvents() {
        return this.jobInspectionEvents;
    }
    
    public void setJobInspectionEvents(Set<JobInspectionEvent> jobInspectionEvents) {
        this.jobInspectionEvents = jobInspectionEvents;
    }
    public Set<JobContract> getJobProdContracts() {
        return this.jobProdContracts;
    }
    
    public void setJobProdContracts(Set<JobContract> jobProdContracts) {
        this.jobProdContracts = jobProdContracts;
    }
    public Set<JobProdQty> getJobProdQtys() {
        return this.jobProdQtys;
    }
    
    public void setJobProdQtys(Set<JobProdQty> jobProdQtys) {
        this.jobProdQtys = jobProdQtys;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JobProd) ) return false;
		 JobProd castOther = ( JobProd ) other; 
         
		 return ( (this.getJobProdId()==castOther.getJobProdId()) || ( this.getJobProdId()!=null && castOther.getJobProdId()!=null && this.getJobProdId().equals(castOther.getJobProdId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJobProdId() == null ? 0 : this.getJobProdId().hashCode() );
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         return result;
   }   


}


