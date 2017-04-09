package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * CfgContract generated by hbm2java
 */
public class CfgContract  implements java.io.Serializable {

    // Fields    

     private CfgContractId cfgContractId;
     @Length(min=0, max=12) private String contractStatus;
     private Date statusDate;
     @Length(min=0, max=90) private String statusUser;
     @Length(min=0, max=9) private String currencyCD;
     private Double qualityDiscount;
     private Double quantityDiscount;
     private Double slateDiscount;
     private Double testDiscount;
     private Double inspectionDiscountPercent;
     private Double labDiscountPercent;
     private Double opsDiscountPercent;
     private Double annualEscalator;
     private Date escalatorDate;
     @Length(min=0, max=45) private String priceBookId;
     @Length(min=0, max=45) private String uom;
     private Double cfgDiscountPercent;
     @Length(min=0, max=45) private String productGroupSet;
     @Length(min=0, max=45) private String vesselSet;
     @Length(min=0, max=3) private String zoneAssoc;
     private Date endDate;
     @Length(min=0, max=9) private Boolean editPB;
     @Length(min=0, max=3) private Boolean hideTestDiscount;
     @Length(min=0, max=45) private String pbSeries;
     private Boolean overridable;
     private Contract contract;

     // Constructors

    /** default constructor */
    public CfgContract() {
    }

	/** minimal constructor */
    public CfgContract(CfgContractId cfgContractId) {
        this.cfgContractId = cfgContractId;
    }
    /** full constructor */
    public CfgContract(CfgContractId cfgContractId, String contractStatus, Date statusDate, String statusUser, String currencyCD, Double qualityDiscount, Double quantityDiscount, Double slateDiscount, Double testDiscount, Double inspectionDiscountPercent, Double labDiscountPercent, Double opsDiscountPercent, Double annualEscalator, Date escalatorDate, String priceBookId, String uom, Double cfgDiscountPercent, String productGroupSet, String vesselSet, String zoneAssoc, Date endDate, Boolean editPB, Boolean hideTestDiscount, String pbSeries, Boolean overridable, Contract contract) {
       this.cfgContractId = cfgContractId;
       this.contractStatus = contractStatus;
       this.statusDate = statusDate;
       this.statusUser = statusUser;
       this.currencyCD = currencyCD;
       this.qualityDiscount = qualityDiscount;
       this.quantityDiscount = quantityDiscount;
       this.slateDiscount = slateDiscount;
       this.testDiscount = testDiscount;
       this.inspectionDiscountPercent = inspectionDiscountPercent;
       this.labDiscountPercent = labDiscountPercent;
       this.opsDiscountPercent = opsDiscountPercent;
       this.annualEscalator = annualEscalator;
       this.escalatorDate = escalatorDate;
       this.priceBookId = priceBookId;
       this.uom = uom;
       this.cfgDiscountPercent = cfgDiscountPercent;
       this.productGroupSet = productGroupSet;
       this.vesselSet = vesselSet;
       this.zoneAssoc = zoneAssoc;
       this.endDate = endDate;
       this.editPB = editPB;
       this.hideTestDiscount = hideTestDiscount;
       this.pbSeries = pbSeries;
       this.overridable = overridable;
       this.contract = contract;
    }
   
    // Property accessors
    public CfgContractId getCfgContractId() {
        return this.cfgContractId;
    }
    
    public void setCfgContractId(CfgContractId cfgContractId) {
        this.cfgContractId = cfgContractId;
    }
    public String getContractStatus() {
        return this.contractStatus;
    }
    
    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
    public Date getStatusDate() {
        return this.statusDate;
    }
    
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
    public String getStatusUser() {
        return this.statusUser;
    }
    
    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }
    public String getCurrencyCD() {
        return this.currencyCD;
    }
    
    public void setCurrencyCD(String currencyCD) {
        this.currencyCD = currencyCD;
    }
    public Double getQualityDiscount() {
        return this.qualityDiscount;
    }
    
    public void setQualityDiscount(Double qualityDiscount) {
        this.qualityDiscount = qualityDiscount;
    }
    public Double getQuantityDiscount() {
        return this.quantityDiscount;
    }
    
    public void setQuantityDiscount(Double quantityDiscount) {
        this.quantityDiscount = quantityDiscount;
    }
    public Double getSlateDiscount() {
        return this.slateDiscount;
    }
    
    public void setSlateDiscount(Double slateDiscount) {
        this.slateDiscount = slateDiscount;
    }
    public Double getTestDiscount() {
        return this.testDiscount;
    }
    
    public void setTestDiscount(Double testDiscount) {
        this.testDiscount = testDiscount;
    }
    public Double getInspectionDiscountPercent() {
        return this.inspectionDiscountPercent;
    }
    
    public void setInspectionDiscountPercent(Double inspectionDiscountPercent) {
        this.inspectionDiscountPercent = inspectionDiscountPercent;
    }
    public Double getLabDiscountPercent() {
        return this.labDiscountPercent;
    }
    
    public void setLabDiscountPercent(Double labDiscountPercent) {
        this.labDiscountPercent = labDiscountPercent;
    }
    public Double getOpsDiscountPercent() {
        return this.opsDiscountPercent;
    }
    
    public void setOpsDiscountPercent(Double opsDiscountPercent) {
        this.opsDiscountPercent = opsDiscountPercent;
    }
    public Double getAnnualEscalator() {
        return this.annualEscalator;
    }
    
    public void setAnnualEscalator(Double annualEscalator) {
        this.annualEscalator = annualEscalator;
    }
    public Date getEscalatorDate() {
        return this.escalatorDate;
    }
    
    public void setEscalatorDate(Date escalatorDate) {
        this.escalatorDate = escalatorDate;
    }
    public String getPriceBookId() {
        return this.priceBookId;
    }
    
    public void setPriceBookId(String priceBookId) {
        this.priceBookId = priceBookId;
    }
    public String getUom() {
        return this.uom;
    }
    
    public void setUom(String uom) {
        this.uom = uom;
    }
    public Double getCfgDiscountPercent() {
        return this.cfgDiscountPercent;
    }
    
    public void setCfgDiscountPercent(Double cfgDiscountPercent) {
        this.cfgDiscountPercent = cfgDiscountPercent;
    }
    public String getProductGroupSet() {
        return this.productGroupSet;
    }
    
    public void setProductGroupSet(String productGroupSet) {
        this.productGroupSet = productGroupSet;
    }
    public String getVesselSet() {
        return this.vesselSet;
    }
    
    public void setVesselSet(String vesselSet) {
        this.vesselSet = vesselSet;
    }
    public String getZoneAssoc() {
        return this.zoneAssoc;
    }
    
    public void setZoneAssoc(String zoneAssoc) {
        this.zoneAssoc = zoneAssoc;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Boolean getEditPB() {
        return this.editPB;
    }
    
    public void setEditPB(Boolean editPB) {
        this.editPB = editPB;
    }
    public Boolean getHideTestDiscount() {
        return this.hideTestDiscount;
    }
    
    public void setHideTestDiscount(Boolean hideTestDiscount) {
        this.hideTestDiscount = hideTestDiscount;
    }
    public String getPbSeries() {
        return this.pbSeries;
    }
    
    public void setPbSeries(String pbSeries) {
        this.pbSeries = pbSeries;
    }
    public Boolean getOverridable() {
        return this.overridable;
    }
    
    public void setOverridable(Boolean overridable) {
        this.overridable = overridable;
    }
    public Contract getContract() {
        return this.contract;
    }
    
    public void setContract(Contract contract) {
        this.contract = contract;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CfgContract) ) return false;
		 CfgContract castOther = ( CfgContract ) other; 
         
		 return ( (this.getCfgContractId()==castOther.getCfgContractId()) || ( this.getCfgContractId()!=null && castOther.getCfgContractId()!=null && this.getCfgContractId().equals(castOther.getCfgContractId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCfgContractId() == null ? 0 : this.getCfgContractId().hashCode() );
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         return result;
   }   


}

