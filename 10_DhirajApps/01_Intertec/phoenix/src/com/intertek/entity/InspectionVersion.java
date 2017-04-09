package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * InspectionVersion generated by hbm2java
 */
public class InspectionVersion  implements java.io.Serializable {

    // Fields    

     private InspectionVersionId inspectionVersionId;
     private Date endDate;
     private Boolean pbDiscountInd;
     @Length(min=0, max=21) private String cfgDiscountPctc;
     private Boolean vesselMaxInd;
     private Boolean additonalVesselInd;
     private Boolean towMaxInd;
     private Boolean numGradesInd;
     private Boolean contractRateInd;

     // Constructors

    /** default constructor */
    public InspectionVersion() {
    }

	/** minimal constructor */
    public InspectionVersion(InspectionVersionId inspectionVersionId) {
        this.inspectionVersionId = inspectionVersionId;
    }
    /** full constructor */
    public InspectionVersion(InspectionVersionId inspectionVersionId, Date endDate, Boolean pbDiscountInd, String cfgDiscountPctc, Boolean vesselMaxInd, Boolean additonalVesselInd, Boolean towMaxInd, Boolean numGradesInd, Boolean contractRateInd) {
       this.inspectionVersionId = inspectionVersionId;
       this.endDate = endDate;
       this.pbDiscountInd = pbDiscountInd;
       this.cfgDiscountPctc = cfgDiscountPctc;
       this.vesselMaxInd = vesselMaxInd;
       this.additonalVesselInd = additonalVesselInd;
       this.towMaxInd = towMaxInd;
       this.numGradesInd = numGradesInd;
       this.contractRateInd = contractRateInd;
    }
   
    // Property accessors
    public InspectionVersionId getInspectionVersionId() {
        return this.inspectionVersionId;
    }
    
    public void setInspectionVersionId(InspectionVersionId inspectionVersionId) {
        this.inspectionVersionId = inspectionVersionId;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Boolean getPbDiscountInd() {
        return this.pbDiscountInd;
    }
    
    public void setPbDiscountInd(Boolean pbDiscountInd) {
        this.pbDiscountInd = pbDiscountInd;
    }
    public String getCfgDiscountPctc() {
        return this.cfgDiscountPctc;
    }
    
    public void setCfgDiscountPctc(String cfgDiscountPctc) {
        this.cfgDiscountPctc = cfgDiscountPctc;
    }
    public Boolean getVesselMaxInd() {
        return this.vesselMaxInd;
    }
    
    public void setVesselMaxInd(Boolean vesselMaxInd) {
        this.vesselMaxInd = vesselMaxInd;
    }
    public Boolean getAdditonalVesselInd() {
        return this.additonalVesselInd;
    }
    
    public void setAdditonalVesselInd(Boolean additonalVesselInd) {
        this.additonalVesselInd = additonalVesselInd;
    }
    public Boolean getTowMaxInd() {
        return this.towMaxInd;
    }
    
    public void setTowMaxInd(Boolean towMaxInd) {
        this.towMaxInd = towMaxInd;
    }
    public Boolean getNumGradesInd() {
        return this.numGradesInd;
    }
    
    public void setNumGradesInd(Boolean numGradesInd) {
        this.numGradesInd = numGradesInd;
    }
    public Boolean getContractRateInd() {
        return this.contractRateInd;
    }
    
    public void setContractRateInd(Boolean contractRateInd) {
        this.contractRateInd = contractRateInd;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InspectionVersion) ) return false;
		 InspectionVersion castOther = ( InspectionVersion ) other; 
         
		 return ( (this.getInspectionVersionId()==castOther.getInspectionVersionId()) || ( this.getInspectionVersionId()!=null && castOther.getInspectionVersionId()!=null && this.getInspectionVersionId().equals(castOther.getInspectionVersionId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getInspectionVersionId() == null ? 0 : this.getInspectionVersionId().hashCode() );
         
         
         
         
         
         
         
         
         return result;
   }   


}

