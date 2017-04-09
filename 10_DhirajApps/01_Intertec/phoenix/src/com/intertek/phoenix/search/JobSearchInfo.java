package com.intertek.phoenix.search;

import java.sql.Timestamp;
import java.util.List;

import com.intertek.phoenix.dao.FilterInfo;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.util.DateUtil;

public class JobSearchInfo {
    public enum Division {
        CE, 
        OCA, 
        GENERAL
    };
    List<FilterInfo> criteria = new java.util.ArrayList<FilterInfo>();
    List<FilterInfo> ceCriteria = new java.util.ArrayList<FilterInfo>();
    List<FilterInfo> ocaCriteria = new java.util.ArrayList<FilterInfo>();
    
    public static String[] jobIdField=new String[]{"jobOrder.jobNumber", "jobNumber"};
    public static String[] buNameField = new String[]{"jobOrder.buName", "buName"};
    public static String[] operatingUnitField= new String[]{"jobOrder.operation", "operation"};
    public static String[] branchNameField= new String[]{"jobOrder.branchName", "branch"};
    public static String[] jobTypeField = new String[]{ "jobOrder.jobType", "jobType"};
    public static String[] svcLocationCodeField = new String[]{ "jobOrder.serviceLocationCode", "serviceLocationCode"};
    public static String[] svcLocationNameField = new String[]{ "jobOrder.serviceLocation.name", "serviceLocationName"};
    //private String product;
    public static String[] companyIdField = new String[]{ "jobContract.customer.custCode", "companyCode"};
    public static String[] companyNameField = new String[]{ "jobContract.customer.name", "customer"};
    public static String[] promiseCompletionDateField = new String[]{ "jobOrder.promiseCompletionDate", "promiseCompletionDate"};
    public static String[] jobFinishedDateField = new String[]{ "jobOrder.jobFinishDate", "jobFinishedDate"};
    public static String[] contactFullName = new String[]{ "concat(concat(jobContract.contact.firstName, ' '), jobContract.contact.lastName) as scheduler", "scheduler"};
    public static String[] custRefNumField = new String[]{ "jobContract.custRefNum", "custRefNum"};
    public static String[] contractIdField = new String[]{ "jobContract.contract.contractCode", "contractCode"};
    public static String[] contractDescField= new String[]{"jobContract.contract.description as contractDesc", "contractDesc"};
    public static String[] contractStatusField = new String[]{ "jobContract.contract.status as contractStatus", "contractStatus"};
    public static String[] modifiedByField = new String[]{ "jobOrder.updatedByUserName", "modifiedBy"};
    public static String[] modifiedDateField = new String[]{ "jobOrder.updateDate", "modifiedDate"};
    public static String[] billingStatusField = new String[]{ "jobOrder.billingStatus", "billingStatus"};
    public static String[] ocaJobStatusField = new String[]{ "jobOrder.jobStatus", "jobStatus"};
    public static String[] ocaRevisionDttmField = new String[]{ "jobOrder.revisionDttm", "modifiedDate"};
    public static String[] jobOrderDateField = new String[]{"jobOrder.createDate", "createDate"};
    public static String[] ocaJobOrderDateField = new String[]{"jobOrder.nominationDate", "createDate"};
    public static String[] invoiceNameField = new String[]{ "invoice.invoiceNumber", "invoice"};
    public static String[] ocaInvoiceNameField = new String[]{ "invoice.invoice", "invoice"};
    public static String[] invoiceStatusField= new String[]{"invoice.status", "invoiceStatus"};
    public static String[] createdByField = new String[]{ "jobOrder.createdByUserName", "createdBy"};
    public static String[] contactIdField = new String[]{ "jobContract.contact.id", "contactId"};
    public static String[] billingContactField= new String[]{"jobContract.billingContact.firstName", "billingContact"};
    public static String[] statusField = new String[]{ "jobOrder.status", "jobStatus"};
    
//  "jobNumber","buName", "operation", "jobType", "serviceLocationCode",
//  //"product",
//  "companyCode", "customer", "contractCode", "custRefNum",
//  //"icbRefNum",
//  "promiseCompletionDate","billingStatus",
//  "invoice",
//  "contractDesc", "contractStatus", "createdBy", "modifiedBy", "modifiedDate"
    public static String[] headers = new String[]{jobIdField[1],buNameField[1],operatingUnitField[1],jobTypeField[1],svcLocationNameField[1],
                                                  //product,
                                                  companyIdField[1], companyNameField[1], contractIdField[1], custRefNumField[1],
                                                  //icbRefNum,
                                                  promiseCompletionDateField[1],billingStatusField[1], invoiceNameField[1],
                                                  contractDescField[1], contractStatusField[1], createdByField[1], modifiedByField[1],modifiedDateField[1]

    };
    //input values
    private String buName;
    private String operatingUnit ;
    private String status;
    private String jobType;
    private String fromJobId;
    private String toJobId;
    private String fromJobOrderDate;
    private String toJobOrderDate;
    private String proudct;
    private String custRefNum;
    private String invoiceName;
    private String invoiceStatus;
    private String contractDesc;
    private String contractId;
    private String createdBy;
    private String modifiedBy;
    private String svcLocationCode;
    private String companyId;
    private String companyName;
    private String contactId;
    private String billingContactName;
//    private String customerCoordinator;
    private String schedulerName;
    private String scheduler;


    public String getBuName() {
        return buName;
    }
    public void setBuName(String buName) {
        this.buName = buName;
        addSearchCriteria(buNameField[0], this.buName);
    }

    public String getOperatingUnit() {
        return operatingUnit;
    }
    public void setOperatingUnit(String operatingUnit) {
        this.operatingUnit = operatingUnit;
        addSearchCriteria(branchNameField[0], this.operatingUnit);
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
        //TODO if status is "ALL", ignore this criterion
        addSearchCriteria(statusField[0], this.status);
    }
    public String getJobType() {
        return jobType;
    }
    public void setJobType(String jobType) {
        this.jobType = jobType;
        //TODO if jobType    is "ALL", ignore this criterion
        addSearchCriteria(jobTypeField[0], this.jobType, FilterOp.EQUAL);
    }
    public String getFromJobId() {
        return fromJobId;
    }
    public void setFromJobId(String fromJobId) {
        this.fromJobId = fromJobId;
        addSearchCriteria(jobIdField[0], this.fromJobId, FilterOp.GREATER_OR_EQUAL);
    }
    public String getToJobId() {
        return toJobId;
    }
    public void setToJobId(String toJobId) {
        this.toJobId = toJobId;
        addSearchCriteria(jobIdField[0], this.toJobId, FilterOp.LESS_OR_EQUAL);
    }
    public String getFromJobOrderDate() {
        return fromJobOrderDate;
    }

    public void setFromJobOrderDate(String fromJobOrderDate) {
        this.fromJobOrderDate = fromJobOrderDate;
        java.sql.Timestamp fromDate = DateUtil.stringToDate(this.fromJobOrderDate);
        addSearchCriteria(jobOrderDateField[0], fromDate, FilterOp.GREATER_OR_EQUAL, Division.CE);
        addSearchCriteria(ocaJobOrderDateField[0], fromDate, FilterOp.GREATER_OR_EQUAL, Division.OCA);
    }
    public void setToJobOrderDate(String toJobOrderDate) {
        this.toJobOrderDate = toJobOrderDate;
        Timestamp toDate = DateUtil.stringToDate(this.toJobOrderDate);
        addSearchCriteria(jobOrderDateField[0], toDate, FilterOp.LESS_OR_EQUAL, Division.CE);
        addSearchCriteria(ocaJobOrderDateField[0], toDate, FilterOp.LESS_OR_EQUAL, Division.OCA);
    }
    public String getCustRefNum() {
        return custRefNum;
    }
    public void setCustRefNum(String custRefNum) {
        this.custRefNum = custRefNum;
        addSearchCriteria(custRefNumField[0], this.custRefNum);
    }
    public String getInvoiceName() {
        return invoiceName;
    }
    public void setInvoiceName(String invoiceName, String op) {
        this.invoiceName = invoiceName;
        addSearchCriteria(invoiceNameField[0], this.invoiceName, getFilterOp(op));
    }
    public String getInvoiceStatus() {
        return invoiceStatus;
    }
    public void setInvoiceStatus(String invoiceStatus, String op) {
        this.invoiceStatus = invoiceStatus;
        addSearchCriteria(invoiceStatusField[0], this.invoiceStatus, getFilterOp(op));
    }
    private FilterOp getFilterOp(String op) {
        FilterOp[] list = FilterOp.list();
        for (FilterOp filterOp : list) {
            if (filterOp.symbol() == op.trim()){
                return filterOp;
            }
        }
        return FilterOp.EQUAL;
    }
    public String getContractDesc() {
        return contractDesc;
    }
    public void setContractDesc(String contractDesc) {
        this.contractDesc = contractDesc;
    }
    public String getContractId() {
        return contractId;
    }
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        //TODO the input could be first name or last name or combination. need to take care of this.
        addSearchCriteria(createdByField[0], this.createdBy);
    }
    public String getSvcLocationCode() {
        return svcLocationCode;
    }
    public void setSvcLocationCode(String svcLocationCode) {
        this.svcLocationCode = svcLocationCode;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getContactId() {
        return contactId;
    }
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    public String getBillingContactName() {
        return billingContactName;
    }
    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }
    public void setSchedulerName(String schedulerName) {
        this.schedulerName = schedulerName;
    }
    
    private void addSearchCriteria(String field, String value) {
        if (value != null && !"".equals(value) && !"-1".equals(value)){
            addFilterInfo(field, value, FilterOp.EQUAL);
        }
    }
    private void addSearchCriteria(String field, String value, FilterOp operator) {
        if (value != null && !"".equals(value) && !"-1".equals(value)){
            addFilterInfo(field, value, operator);
        }
    }  
    private void addFilterInfo(String field, Object value, FilterOp operator){
        addFilterInfo(field, value, operator, Division.GENERAL);
    }
    private void addSearchCriteria(String field, java.sql.Timestamp formattedDate, FilterOp operator, Division division) {
        if (formattedDate != null){
            addFilterInfo(field, formattedDate, operator, division);
        }
    }
    private void addFilterInfo(String field, Object value, FilterOp operator, Division division){
        FilterInfo filterInfo = new FilterInfo();
        filterInfo.setName(field);
        filterInfo.setValue(value);
        filterInfo.setOp(operator);
        switch(division) {
            case CE:
                ceCriteria.add(filterInfo);
                break;
            case OCA:
                ocaCriteria.add(filterInfo);
                break;
            case GENERAL:
            default:
                ceCriteria.add(filterInfo);
                ocaCriteria.add(filterInfo);
                break;
        }
    }
    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        //TODO take care of first name or last name issue
        addSearchCriteria(modifiedByField[0], this.modifiedBy);
    }
    public List<FilterInfo> getCriteria() {
        return criteria;
    }
    public List<FilterInfo> getCeCriteria() {
        return ceCriteria;
    }
    public List<FilterInfo> getOcaCriteria() {
        return ocaCriteria;
    }
}
