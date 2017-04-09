package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Prebill generated by hbm2java
 */
public class Prebill  implements java.io.Serializable {

    // Fields    

     private long id;
     @Length(min=0, max=5) private Integer lineNumber;
     @NotBlank @Length(min=0, max=3048) private String lineDescription;
     private double unitPrice;
     private double splitPct;
     private double discountPct;
     private double netPrice;
     @NotBlank @Length(min=0, max=3) private String currencyCd;
     @NotBlank @Length(min=0, max=10) private String primaryBranchCd;
     @NotBlank @Length(min=0, max=256) private String level0;
     @NotBlank @Length(min=0, max=256) private String level1;
     @NotBlank @Length(min=0, max=50) private String level2;
     @NotBlank @Length(min=0, max=1) private String configuredInd;
     @NotBlank @Length(min=0, max=1) private String auditFlag;
     @NotBlank @Length(min=0, max=6) private String productGroup;
     @NotBlank @Length(min=0, max=10) private String deptid;
     @NotBlank @Length(min=0, max=35) private String busStreamCode;
     @Length(min=0, max=4) private String serviceType;
     @Length(min=0, max=8) private String taxCode;
     @Length(min=0, max=8) private String vatCode;
     private Double taxPct;
     private Double vatPct;
     private Double taxAmt;
     private Double vatAmt;
     @Length(min=0, max=45) private String account;
     private Integer vesselSortNum;
     private Integer lotSortNum;
     private Integer chargeSortNum;
     @Length(min=0, max=20) private String uid20;
     @Length(min=0, max=20) private String rateType;
     private Double rateMult;
     private Double rateDiv;
     @Length(min=0, max=3) private String baseCurrencyCd;
     private Double baseUnitPrice;
     private Double baseNetPrice;
     private Double baseTaxAmt;
     private Double baseVatAmt;
     private Boolean editable;
     private String comments;
     private Double quantity;
     private JobOrder jobOrder;
     private JobContract jobContract;
     private TaxArticle taxArticle;
     private JobContractServiceResult jobContractServiceResult;
     private JobContractVesselServiceResult jobContractVesselServiceResult;
     private JobContractProductServiceResult jobContractProductServiceResult;
     private JobContractTest jobContractTest;
     private JobContractManualTest jobContractManualTest;
     private JobContractSlate jobContractSlate;
     private JobContractProductInspectionResult jobContractProductInspectionResult;
     private JobContractVesselInspectionResult jobContractVesselInspectionResult;
     private JobContractInspectionResult jobContractInspectionResult;
     private Set<PrebillSplit> prebillSplits = new HashSet<PrebillSplit>(0);
     private Set<PrebillHistory> prebillHistories = new HashSet<PrebillHistory>(0);

     // Constructors

    /** default constructor */
    public Prebill() {
    }

    /** full constructor */
    public Prebill(Integer lineNumber, String lineDescription, double unitPrice, double splitPct, double discountPct, double netPrice, String currencyCd, String primaryBranchCd, String level0, String level1, String level2, String configuredInd, String auditFlag, String productGroup, String deptid, String busStreamCode, String serviceType, String taxCode, String vatCode, Double taxPct, Double vatPct, Double taxAmt, Double vatAmt, String account, Integer vesselSortNum, Integer lotSortNum, Integer chargeSortNum, String uid20, String rateType, Double rateMult, Double rateDiv, String baseCurrencyCd, Double baseUnitPrice, Double baseNetPrice, Double baseTaxAmt, Double baseVatAmt, Boolean editable, String comments, Double quantity, JobOrder jobOrder, JobContract jobContract, TaxArticle taxArticle, JobContractServiceResult jobContractServiceResult, JobContractVesselServiceResult jobContractVesselServiceResult, JobContractProductServiceResult jobContractProductServiceResult, JobContractTest jobContractTest, JobContractManualTest jobContractManualTest, JobContractSlate jobContractSlate, JobContractProductInspectionResult jobContractProductInspectionResult, JobContractVesselInspectionResult jobContractVesselInspectionResult, JobContractInspectionResult jobContractInspectionResult, Set<PrebillSplit> prebillSplits, Set<PrebillHistory> prebillHistories) {
       this.lineNumber = lineNumber;
       this.lineDescription = lineDescription;
       this.unitPrice = unitPrice;
       this.splitPct = splitPct;
       this.discountPct = discountPct;
       this.netPrice = netPrice;
       this.currencyCd = currencyCd;
       this.primaryBranchCd = primaryBranchCd;
       this.level0 = level0;
       this.level1 = level1;
       this.level2 = level2;
       this.configuredInd = configuredInd;
       this.auditFlag = auditFlag;
       this.productGroup = productGroup;
       this.deptid = deptid;
       this.busStreamCode = busStreamCode;
       this.serviceType = serviceType;
       this.taxCode = taxCode;
       this.vatCode = vatCode;
       this.taxPct = taxPct;
       this.vatPct = vatPct;
       this.taxAmt = taxAmt;
       this.vatAmt = vatAmt;
       this.account = account;
       this.vesselSortNum = vesselSortNum;
       this.lotSortNum = lotSortNum;
       this.chargeSortNum = chargeSortNum;
       this.uid20 = uid20;
       this.rateType = rateType;
       this.rateMult = rateMult;
       this.rateDiv = rateDiv;
       this.baseCurrencyCd = baseCurrencyCd;
       this.baseUnitPrice = baseUnitPrice;
       this.baseNetPrice = baseNetPrice;
       this.baseTaxAmt = baseTaxAmt;
       this.baseVatAmt = baseVatAmt;
       this.editable = editable;
       this.comments = comments;
       this.quantity = quantity;
       this.jobOrder = jobOrder;
       this.jobContract = jobContract;
       this.taxArticle = taxArticle;
       this.jobContractServiceResult = jobContractServiceResult;
       this.jobContractVesselServiceResult = jobContractVesselServiceResult;
       this.jobContractProductServiceResult = jobContractProductServiceResult;
       this.jobContractTest = jobContractTest;
       this.jobContractManualTest = jobContractManualTest;
       this.jobContractSlate = jobContractSlate;
       this.jobContractProductInspectionResult = jobContractProductInspectionResult;
       this.jobContractVesselInspectionResult = jobContractVesselInspectionResult;
       this.jobContractInspectionResult = jobContractInspectionResult;
       this.prebillSplits = prebillSplits;
       this.prebillHistories = prebillHistories;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Integer getLineNumber() {
        return this.lineNumber;
    }
    
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    public String getLineDescription() {
        return this.lineDescription;
    }
    
    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }
    public double getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getSplitPct() {
        return this.splitPct;
    }
    
    public void setSplitPct(double splitPct) {
        this.splitPct = splitPct;
    }
    public double getDiscountPct() {
        return this.discountPct;
    }
    
    public void setDiscountPct(double discountPct) {
        this.discountPct = discountPct;
    }
    public double getNetPrice() {
        return this.netPrice;
    }
    
    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }
    public String getCurrencyCd() {
        return this.currencyCd;
    }
    
    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }
    public String getPrimaryBranchCd() {
        return this.primaryBranchCd;
    }
    
    public void setPrimaryBranchCd(String primaryBranchCd) {
        this.primaryBranchCd = primaryBranchCd;
    }
    public String getLevel0() {
        return this.level0;
    }
    
    public void setLevel0(String level0) {
        this.level0 = level0;
    }
    public String getLevel1() {
        return this.level1;
    }
    
    public void setLevel1(String level1) {
        this.level1 = level1;
    }
    public String getLevel2() {
        return this.level2;
    }
    
    public void setLevel2(String level2) {
        this.level2 = level2;
    }
    public String getConfiguredInd() {
        return this.configuredInd;
    }
    
    public void setConfiguredInd(String configuredInd) {
        this.configuredInd = configuredInd;
    }
    public String getAuditFlag() {
        return this.auditFlag;
    }
    
    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }
    public String getProductGroup() {
        return this.productGroup;
    }
    
    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }
    public String getDeptid() {
        return this.deptid;
    }
    
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    public String getBusStreamCode() {
        return this.busStreamCode;
    }
    
    public void setBusStreamCode(String busStreamCode) {
        this.busStreamCode = busStreamCode;
    }
    public String getServiceType() {
        return this.serviceType;
    }
    
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getTaxCode() {
        return this.taxCode;
    }
    
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    public String getVatCode() {
        return this.vatCode;
    }
    
    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }
    public Double getTaxPct() {
        return this.taxPct;
    }
    
    public void setTaxPct(Double taxPct) {
        this.taxPct = taxPct;
    }
    public Double getVatPct() {
        return this.vatPct;
    }
    
    public void setVatPct(Double vatPct) {
        this.vatPct = vatPct;
    }
    public Double getTaxAmt() {
        return this.taxAmt;
    }
    
    public void setTaxAmt(Double taxAmt) {
        this.taxAmt = taxAmt;
    }
    public Double getVatAmt() {
        return this.vatAmt;
    }
    
    public void setVatAmt(Double vatAmt) {
        this.vatAmt = vatAmt;
    }
    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    public Integer getVesselSortNum() {
        return this.vesselSortNum;
    }
    
    public void setVesselSortNum(Integer vesselSortNum) {
        this.vesselSortNum = vesselSortNum;
    }
    public Integer getLotSortNum() {
        return this.lotSortNum;
    }
    
    public void setLotSortNum(Integer lotSortNum) {
        this.lotSortNum = lotSortNum;
    }
    public Integer getChargeSortNum() {
        return this.chargeSortNum;
    }
    
    public void setChargeSortNum(Integer chargeSortNum) {
        this.chargeSortNum = chargeSortNum;
    }
    public String getUid20() {
        return this.uid20;
    }
    
    public void setUid20(String uid20) {
        this.uid20 = uid20;
    }
    public String getRateType() {
        return this.rateType;
    }
    
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }
    public Double getRateMult() {
        return this.rateMult;
    }
    
    public void setRateMult(Double rateMult) {
        this.rateMult = rateMult;
    }
    public Double getRateDiv() {
        return this.rateDiv;
    }
    
    public void setRateDiv(Double rateDiv) {
        this.rateDiv = rateDiv;
    }
    public String getBaseCurrencyCd() {
        return this.baseCurrencyCd;
    }
    
    public void setBaseCurrencyCd(String baseCurrencyCd) {
        this.baseCurrencyCd = baseCurrencyCd;
    }
    public Double getBaseUnitPrice() {
        return this.baseUnitPrice;
    }
    
    public void setBaseUnitPrice(Double baseUnitPrice) {
        this.baseUnitPrice = baseUnitPrice;
    }
    public Double getBaseNetPrice() {
        return this.baseNetPrice;
    }
    
    public void setBaseNetPrice(Double baseNetPrice) {
        this.baseNetPrice = baseNetPrice;
    }
    public Double getBaseTaxAmt() {
        return this.baseTaxAmt;
    }
    
    public void setBaseTaxAmt(Double baseTaxAmt) {
        this.baseTaxAmt = baseTaxAmt;
    }
    public Double getBaseVatAmt() {
        return this.baseVatAmt;
    }
    
    public void setBaseVatAmt(Double baseVatAmt) {
        this.baseVatAmt = baseVatAmt;
    }
    public Boolean getEditable() {
        return this.editable;
    }
    
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    public Double getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public JobOrder getJobOrder() {
        return this.jobOrder;
    }
    
    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }
    public JobContract getJobContract() {
        return this.jobContract;
    }
    
    public void setJobContract(JobContract jobContract) {
        this.jobContract = jobContract;
    }
    public TaxArticle getTaxArticle() {
        return this.taxArticle;
    }
    
    public void setTaxArticle(TaxArticle taxArticle) {
        this.taxArticle = taxArticle;
    }
    public JobContractServiceResult getJobContractServiceResult() {
        return this.jobContractServiceResult;
    }
    
    public void setJobContractServiceResult(JobContractServiceResult jobContractServiceResult) {
        this.jobContractServiceResult = jobContractServiceResult;
    }
    public JobContractVesselServiceResult getJobContractVesselServiceResult() {
        return this.jobContractVesselServiceResult;
    }
    
    public void setJobContractVesselServiceResult(JobContractVesselServiceResult jobContractVesselServiceResult) {
        this.jobContractVesselServiceResult = jobContractVesselServiceResult;
    }
    public JobContractProductServiceResult getJobContractProductServiceResult() {
        return this.jobContractProductServiceResult;
    }
    
    public void setJobContractProductServiceResult(JobContractProductServiceResult jobContractProductServiceResult) {
        this.jobContractProductServiceResult = jobContractProductServiceResult;
    }
    public JobContractTest getJobContractTest() {
        return this.jobContractTest;
    }
    
    public void setJobContractTest(JobContractTest jobContractTest) {
        this.jobContractTest = jobContractTest;
    }
    public JobContractManualTest getJobContractManualTest() {
        return this.jobContractManualTest;
    }
    
    public void setJobContractManualTest(JobContractManualTest jobContractManualTest) {
        this.jobContractManualTest = jobContractManualTest;
    }
    public JobContractSlate getJobContractSlate() {
        return this.jobContractSlate;
    }
    
    public void setJobContractSlate(JobContractSlate jobContractSlate) {
        this.jobContractSlate = jobContractSlate;
    }
    public JobContractProductInspectionResult getJobContractProductInspectionResult() {
        return this.jobContractProductInspectionResult;
    }
    
    public void setJobContractProductInspectionResult(JobContractProductInspectionResult jobContractProductInspectionResult) {
        this.jobContractProductInspectionResult = jobContractProductInspectionResult;
    }
    public JobContractVesselInspectionResult getJobContractVesselInspectionResult() {
        return this.jobContractVesselInspectionResult;
    }
    
    public void setJobContractVesselInspectionResult(JobContractVesselInspectionResult jobContractVesselInspectionResult) {
        this.jobContractVesselInspectionResult = jobContractVesselInspectionResult;
    }
    public JobContractInspectionResult getJobContractInspectionResult() {
        return this.jobContractInspectionResult;
    }
    
    public void setJobContractInspectionResult(JobContractInspectionResult jobContractInspectionResult) {
        this.jobContractInspectionResult = jobContractInspectionResult;
    }
    public Set<PrebillSplit> getPrebillSplits() {
        return this.prebillSplits;
    }
    
    public void setPrebillSplits(Set<PrebillSplit> prebillSplits) {
        this.prebillSplits = prebillSplits;
    }
    public Set<PrebillHistory> getPrebillHistories() {
        return this.prebillHistories;
    }
    
    public void setPrebillHistories(Set<PrebillHistory> prebillHistories) {
        this.prebillHistories = prebillHistories;
    }




}


