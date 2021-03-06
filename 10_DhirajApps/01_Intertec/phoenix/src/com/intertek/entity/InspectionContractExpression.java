package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * InspectionContractExpression generated by hbm2java
 */
public class InspectionContractExpression  implements java.io.Serializable {

    // Fields    

     private InspectionContractExpressionId inspectionContractExpressionId;
     @Length(min=0, max=93) private String contractComponent;
     @Length(min=0, max=93) private String pbComponentType;
     private Double cfgDiscountPercent;
     @Length(min=0, max=105) private String parentExpressionId;
     @Length(min=0, max=105) private String siblingExpressionId;
     @Length(min=0, max=96) private String questionId;
     @Length(min=0, max=105) private String serviceRateExpressionId;
     @Length(min=0, max=12) private String serviceLevel;
     private Integer expressionRank;
     private Double useGroupId;
     private Double useVesselId;
     @Length(min=0, max=12) private String visibility;
     private Date endDate;
     private Boolean pbDiscountInd;
     private Boolean vesselMaxInd;
     private Boolean additonalVesselInd;
     private Boolean towMaxInd;
     private Boolean numGradesInd;
     private Boolean contractRateInd;
     private Integer rowNumber;
     private Integer overrideRow;

     // Constructors

    /** default constructor */
    public InspectionContractExpression() {
    }

	/** minimal constructor */
    public InspectionContractExpression(InspectionContractExpressionId inspectionContractExpressionId) {
        this.inspectionContractExpressionId = inspectionContractExpressionId;
    }
    /** full constructor */
    public InspectionContractExpression(InspectionContractExpressionId inspectionContractExpressionId, String contractComponent, String pbComponentType, Double cfgDiscountPercent, String parentExpressionId, String siblingExpressionId, String questionId, String serviceRateExpressionId, String serviceLevel, Integer expressionRank, Double useGroupId, Double useVesselId, String visibility, Date endDate, Boolean pbDiscountInd, Boolean vesselMaxInd, Boolean additonalVesselInd, Boolean towMaxInd, Boolean numGradesInd, Boolean contractRateInd, Integer rowNumber, Integer overrideRow) {
       this.inspectionContractExpressionId = inspectionContractExpressionId;
       this.contractComponent = contractComponent;
       this.pbComponentType = pbComponentType;
       this.cfgDiscountPercent = cfgDiscountPercent;
       this.parentExpressionId = parentExpressionId;
       this.siblingExpressionId = siblingExpressionId;
       this.questionId = questionId;
       this.serviceRateExpressionId = serviceRateExpressionId;
       this.serviceLevel = serviceLevel;
       this.expressionRank = expressionRank;
       this.useGroupId = useGroupId;
       this.useVesselId = useVesselId;
       this.visibility = visibility;
       this.endDate = endDate;
       this.pbDiscountInd = pbDiscountInd;
       this.vesselMaxInd = vesselMaxInd;
       this.additonalVesselInd = additonalVesselInd;
       this.towMaxInd = towMaxInd;
       this.numGradesInd = numGradesInd;
       this.contractRateInd = contractRateInd;
       this.rowNumber = rowNumber;
       this.overrideRow = overrideRow;
    }
   
    // Property accessors
    public InspectionContractExpressionId getInspectionContractExpressionId() {
        return this.inspectionContractExpressionId;
    }
    
    public void setInspectionContractExpressionId(InspectionContractExpressionId inspectionContractExpressionId) {
        this.inspectionContractExpressionId = inspectionContractExpressionId;
    }
    public String getContractComponent() {
        return this.contractComponent;
    }
    
    public void setContractComponent(String contractComponent) {
        this.contractComponent = contractComponent;
    }
    public String getPbComponentType() {
        return this.pbComponentType;
    }
    
    public void setPbComponentType(String pbComponentType) {
        this.pbComponentType = pbComponentType;
    }
    public Double getCfgDiscountPercent() {
        return this.cfgDiscountPercent;
    }
    
    public void setCfgDiscountPercent(Double cfgDiscountPercent) {
        this.cfgDiscountPercent = cfgDiscountPercent;
    }
    public String getParentExpressionId() {
        return this.parentExpressionId;
    }
    
    public void setParentExpressionId(String parentExpressionId) {
        this.parentExpressionId = parentExpressionId;
    }
    public String getSiblingExpressionId() {
        return this.siblingExpressionId;
    }
    
    public void setSiblingExpressionId(String siblingExpressionId) {
        this.siblingExpressionId = siblingExpressionId;
    }
    public String getQuestionId() {
        return this.questionId;
    }
    
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getServiceRateExpressionId() {
        return this.serviceRateExpressionId;
    }
    
    public void setServiceRateExpressionId(String serviceRateExpressionId) {
        this.serviceRateExpressionId = serviceRateExpressionId;
    }
    public String getServiceLevel() {
        return this.serviceLevel;
    }
    
    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
    public Integer getExpressionRank() {
        return this.expressionRank;
    }
    
    public void setExpressionRank(Integer expressionRank) {
        this.expressionRank = expressionRank;
    }
    public Double getUseGroupId() {
        return this.useGroupId;
    }
    
    public void setUseGroupId(Double useGroupId) {
        this.useGroupId = useGroupId;
    }
    public Double getUseVesselId() {
        return this.useVesselId;
    }
    
    public void setUseVesselId(Double useVesselId) {
        this.useVesselId = useVesselId;
    }
    public String getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(String visibility) {
        this.visibility = visibility;
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
    public Integer getRowNumber() {
        return this.rowNumber;
    }
    
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }
    public Integer getOverrideRow() {
        return this.overrideRow;
    }
    
    public void setOverrideRow(Integer overrideRow) {
        this.overrideRow = overrideRow;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InspectionContractExpression) ) return false;
		 InspectionContractExpression castOther = ( InspectionContractExpression ) other; 
         
		 return ( (this.getInspectionContractExpressionId()==castOther.getInspectionContractExpressionId()) || ( this.getInspectionContractExpressionId()!=null && castOther.getInspectionContractExpressionId()!=null && this.getInspectionContractExpressionId().equals(castOther.getInspectionContractExpressionId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getInspectionContractExpressionId() == null ? 0 : this.getInspectionContractExpressionId().hashCode() );
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         return result;
   }   


}


