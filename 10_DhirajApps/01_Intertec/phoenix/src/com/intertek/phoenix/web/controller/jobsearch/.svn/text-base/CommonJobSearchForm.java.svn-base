package com.intertek.phoenix.web.controller.jobsearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intertek.domain.Search;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.common.ReferenceDataService.DropdownName;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.search.JobSearchInfo;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.util.ArrayMapGrid;

public class CommonJobSearchForm extends Search {
    // Search Fields
    private SearchCriteria buName;
    private SearchCriteria statusCri;
    private SearchCriteria frmJobId;
    private SearchCriteria frmJobFinishDt;
    private SearchCriteria jobOrderDtFrm;
    private SearchCriteria jobProduct;
    private SearchCriteria jobInvoice;
    private SearchCriteria jobContractDsc;
    private SearchCriteria jobCrtedBy;
    private SearchCriteria jobSvcLoctn;
    private SearchCriteria jobCompanyId;
    private SearchCriteria jobContactId;
    private SearchCriteria jobCstmrCoordinator;
    private SearchCriteria operatingUnit;
    private SearchCriteria jobTypeCri;
    private SearchCriteria toJobId;
    private SearchCriteria toJobFinishDt;
    private SearchCriteria jobOrderDtTo;
    private SearchCriteria custRefNumCri;
    private SearchCriteria invStatus;
    private SearchCriteria contractId;
    private SearchCriteria modifiedByCri;
    private SearchCriteria company;
    private SearchCriteria billContact;
    private SearchCriteria schedulerCri;
    private String searchFlag;
    private String sortBy;
    private String jobSearchCriteriaName;
    private String sortFlag;

    private final ReferenceDataService refrenceDataService = ServiceManager.getReferenceDataService();
    // Search Results
    private String jobNumber;
    private String buUnit;
    private String operation;
    private String jobType;
    private String serviceLctn;
    private String product;
    private String companyId;
    private String companyName;
    private String scheduler;
    private String custRefNum;
    private String icbRefNo;
    private String jobFinishDt;
    private String status;
    private String invoice;
    private String contractDsc;
    private String contractSts;
    private String createdBy;
    private String modifiedBy;
    private String modifiedDt;

    public CommonJobSearchForm() {
        buName = new SearchCriteria();
        statusCri = new SearchCriteria();
        frmJobId = new SearchCriteria();
        frmJobFinishDt = new SearchCriteria();
        jobOrderDtFrm = new SearchCriteria();
        jobProduct = new SearchCriteria();
        jobInvoice = new SearchCriteria();
        jobContractDsc = new SearchCriteria();
        jobCrtedBy = new SearchCriteria();
        jobSvcLoctn = new SearchCriteria();
        jobCompanyId = new SearchCriteria();
        jobContactId = new SearchCriteria();
        jobCstmrCoordinator = new SearchCriteria();
        operatingUnit = new SearchCriteria();
        jobTypeCri = new SearchCriteria();
        toJobId = new SearchCriteria();
        toJobFinishDt = new SearchCriteria();
        jobOrderDtTo = new SearchCriteria();
        custRefNumCri = new SearchCriteria();
        invStatus = new SearchCriteria();
        contractId = new SearchCriteria();
        modifiedByCri = new SearchCriteria();
        company = new SearchCriteria();
        billContact = new SearchCriteria();
        schedulerCri = new SearchCriteria();
    }

    public String getBuUnit() {
        return buUnit;
    }

    public String getOperation() {
        return operation;
    }

    public String getJobType() {
        return jobType;
    }

    public String getServiceLctn() {
        return serviceLctn;
    }

    public String getProduct() {
        return product;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getScheduler() {
        return scheduler;
    }

    public String getCustRefNum() {
        return custRefNum;
    }

    public String getIcbRefNo() {
        return icbRefNo;
    }

    public String getJobFinishDt() {
        return jobFinishDt;
    }

    public String getStatus() {
        return status;
    }

    public String getInvoice() {
        return invoice;
    }

    public String getContractDsc() {
        return contractDsc;
    }

    public String getContractSts() {
        return contractSts;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public String getModifiedDt() {
        return modifiedDt;
    }

    public SearchCriteria getBuName() {
        return buName;
    }

    public void setBuName(SearchCriteria buName) {
        this.buName = buName;
    }

    public SearchCriteria getStatusCri() {
        return statusCri;
    }

    public void setStatusCri(SearchCriteria statusCri) {
        this.statusCri = statusCri;
    }

    public SearchCriteria getFrmJobId() {
        return frmJobId;
    }

    public void setFrmJobId(SearchCriteria frmJobId) {
        this.frmJobId = frmJobId;
    }

    public SearchCriteria getFrmJobFinishDt() {
        return frmJobFinishDt;
    }

    public void setFrmJobFinishDt(SearchCriteria frmJobFinishDt) {
        this.frmJobFinishDt = frmJobFinishDt;
    }

    public SearchCriteria getJobOrderDtFrm() {
        return jobOrderDtFrm;
    }

    public void setJobOrderDtFrm(SearchCriteria jobOrderDtFrm) {
        this.jobOrderDtFrm = jobOrderDtFrm;
    }

    public SearchCriteria getJobProduct() {
        return jobProduct;
    }

    public void setJobProduct(SearchCriteria jobProduct) {
        this.jobProduct = jobProduct;
    }

    public SearchCriteria getJobInvoice() {
        return jobInvoice;
    }

    public void setJobInvoice(SearchCriteria jobInvoice) {
        this.jobInvoice = jobInvoice;
    }

    public SearchCriteria getJobContractDsc() {
        return jobContractDsc;
    }

    public void setJobContractDsc(SearchCriteria jobContractDsc) {
        this.jobContractDsc = jobContractDsc;
    }

    public SearchCriteria getJobCrtedBy() {
        return jobCrtedBy;
    }

    public void setJobCrtedBy(SearchCriteria jobCrtedBy) {
        this.jobCrtedBy = jobCrtedBy;
    }

    public SearchCriteria getJobSvcLoctn() {
        return jobSvcLoctn;
    }

    public void setJobSvcLoctn(SearchCriteria jobSvcLoctn) {
        this.jobSvcLoctn = jobSvcLoctn;
    }

    public SearchCriteria getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(SearchCriteria jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public SearchCriteria getJobContactId() {
        return jobContactId;
    }

    public void setJobContactId(SearchCriteria jobContactId) {
        this.jobContactId = jobContactId;
    }

    public SearchCriteria getJobCstmrCoordinator() {
        return jobCstmrCoordinator;
    }

    public void setJobCstmrCoordinator(SearchCriteria jobCstmrCoordinator) {
        this.jobCstmrCoordinator = jobCstmrCoordinator;
    }

    public SearchCriteria getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(SearchCriteria operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public SearchCriteria getJobTypeCri() {
        return jobTypeCri;
    }

    public void setJobTypeCri(SearchCriteria jobTypeCri) {
        this.jobTypeCri = jobTypeCri;
    }

    public SearchCriteria getToJobId() {
        return toJobId;
    }

    public void setToJobId(SearchCriteria toJobId) {
        this.toJobId = toJobId;
    }

    public SearchCriteria getToJobFinishDt() {
        return toJobFinishDt;
    }

    public void setToJobFinishDt(SearchCriteria toJobFinishDt) {
        this.toJobFinishDt = toJobFinishDt;
    }

    public SearchCriteria getJobOrderDtTo() {
        return jobOrderDtTo;
    }

    public void setJobOrderDtTo(SearchCriteria jobOrderDtTo) {
        this.jobOrderDtTo = jobOrderDtTo;
    }

    public SearchCriteria getCustRefNumCri() {
        return custRefNumCri;
    }

    public void setCustRefNumCri(SearchCriteria custRefNumCri) {
        this.custRefNumCri = custRefNumCri;
    }

    public SearchCriteria getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(SearchCriteria invStatus) {
        this.invStatus = invStatus;
    }

    public SearchCriteria getContractId() {
        return contractId;
    }

    public void setContractId(SearchCriteria contractId) {
        this.contractId = contractId;
    }

    public SearchCriteria getModifiedByCri() {
        return modifiedByCri;
    }

    public void setModifiedByCri(SearchCriteria modifiedByCri) {
        this.modifiedByCri = modifiedByCri;
    }

    public SearchCriteria getCompany() {
        return company;
    }

    public void setCompany(SearchCriteria company) {
        this.company = company;
    }

    public SearchCriteria getBillContact() {
        return billContact;
    }

    public void setBillContact(SearchCriteria billContact) {
        this.billContact = billContact;
    }

    public void setBuUnit(String buUnit) {
        this.buUnit = buUnit;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setServiceLctn(String serviceLctn) {
        this.serviceLctn = serviceLctn;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setScheduler(String scheduler) {
        this.scheduler = scheduler;
    }

    public void setCustRefNum(String custRefNum) {
        this.custRefNum = custRefNum;
    }

    public void setIcbRefNo(String icbRefNo) {
        this.icbRefNo = icbRefNo;
    }

    public void setJobFinishDt(String jobFinishDt) {
        this.jobFinishDt = jobFinishDt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public void setContractDsc(String contractDsc) {
        this.contractDsc = contractDsc;
    }

    public void setContractSts(String contractSts) {
        this.contractSts = contractSts;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifiedDt(String modifiedDt) {
        this.modifiedDt = modifiedDt;
    }

    public SearchCriteria getSchedulerCri() {
        return schedulerCri;
    }

    public void setSchedulerCri(SearchCriteria schedulerCri) {
        this.schedulerCri = schedulerCri;
    }

    public String getJobSearchCriteriaName() {
        return jobSearchCriteriaName;
    }

    public void setJobSearchCriteriaName(String jobSearchCriteriaName) {
        this.jobSearchCriteriaName = jobSearchCriteriaName;
    }

    // DrpDowns
    @SuppressWarnings("unchecked")
    public List<Field> getBuNames() {
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("businessUnit", null);
    }

    // DrpDowns
    public List<Field> getJobStatus() {
        Field field = new Field("ALL", "-1");
        List<Field> fieldLi = new ArrayList<Field>();
        fieldLi.add(field);
        fieldLi.addAll(refrenceDataService.getOrderStatusFields());
        return fieldLi;
    }

    public List<Field> getJobTypes() {
        Field field = new Field("All Job Types", "-1");
        List<Field> fieldLi = new ArrayList<Field>();
        fieldLi.add(field);
        fieldLi.addAll(refrenceDataService.getDropdown(DropdownName.jobType));
        return fieldLi;
    }

    public List<FilterOp> getOperaters() {
        return refrenceDataService.getFilterStringOpFields();
    }

    public String getDateFormat() {
        return Form.getCurrentUserFormat();
    }

    public List<Field> getInvoiceStatus() {
        Field field = new Field("ALL", "-1");
        List<Field> fieldLi = new ArrayList<Field>();
        fieldLi.add(field);
        fieldLi.addAll(refrenceDataService.getInvoiceStatusFields());
        return fieldLi;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(String searchFlag) {
        this.searchFlag = searchFlag;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    // TODO
    public List<?> buildResult(ArrayMapGrid am) {
        List<CommonJobSearchForm> list = new ArrayList<CommonJobSearchForm>();
        int valueIndex = 1;
        while (am.moveNext()) {
            CommonJobSearchForm form = new CommonJobSearchForm();
            form.setJobNumber(convertSearchResult(am.getFieldValue(JobSearchInfo.jobIdField[valueIndex])));
            form.setBuUnit(convertSearchResult(am.getFieldValue(JobSearchInfo.buNameField[valueIndex])));
            form.setOperation(convertSearchResult(am.getFieldValue(JobSearchInfo.operatingUnitField[valueIndex])));
            form.setJobType(convertSearchResult(am.getFieldValue(JobSearchInfo.jobTypeField[valueIndex])));
            form.setServiceLctn(convertSearchResult(am.getFieldValue(JobSearchInfo.svcLocationNameField[1])));
            // form.setProduct(am.getFieldValue(JobSearchInfo.get)==null?"":am.getFieldValue(JobSearchInfo.get).toString());
            form.setCompanyId(convertSearchResult(am.getFieldValue(JobSearchInfo.companyIdField[1])));
            form.setCompanyName(convertSearchResult(am.getFieldValue(JobSearchInfo.companyNameField[1])));
            form.setScheduler(convertSearchResult(am.getFieldValue(JobSearchInfo.contactFullName[1])));
            form.setCustRefNum(convertSearchResult(am.getFieldValue(JobSearchInfo.custRefNumField[1])));
            // form.setIcbRefNo(am.getFieldValue(JobSearchInfo.get)==null?"":am.getFieldValue(JobSearchInfo.get).toString());
            form.setJobFinishDt(getUserDateFormat(am.getFieldValue(JobSearchInfo.promiseCompletionDateField[1])));
            form.setStatus(convertSearchResult(am.getFieldValue(JobSearchInfo.billingStatusField[1])));
            form.setInvoice(convertSearchResult(am.getFieldValue(JobSearchInfo.invoiceNameField[1])));
            form.setContractDsc(convertSearchResult(am.getFieldValue(JobSearchInfo.contractDescField[1])));
            form.setContractSts(convertSearchResult(am.getFieldValue(JobSearchInfo.contractStatusField[1])));
            form.setCreatedBy(convertSearchResult(am.getFieldValue(JobSearchInfo.createdByField[1])));
            form.setModifiedBy(convertSearchResult(am.getFieldValue(JobSearchInfo.modifiedByField[1])));
            form.setModifiedDt(getUserDateFormat(am.getFieldValue(JobSearchInfo.modifiedDateField[1])));
            list.add(form);
        }
        return list;
    }

    private String convertSearchResult(Object fieldValue) {
        if (fieldValue == null) {
            return "";
        }
        else {
            return fieldValue.toString();
        }
    }

    private String getUserDateFormat(Object fieldValue) {
        String date = "";
        if (fieldValue == null) {
            date = "";
        }
        else {
            date = fieldValue.toString();
            String dateFormat = new Form().getUserDateFormat();
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            date = df.format(fieldValue);
        }
        return date;
    }

    // TODO :need to refractor for sorting
    public Map getSearchInfo() {

        /*return new String[] { JobSearchInfo.jobIdField[0], JobSearchInfo.buNameField[0], JobSearchInfo.operatingUnitField[0], JobSearchInfo.jobTypeField[0],
                             JobSearchInfo.svcLocationNameField[0], JobSearchInfo.companyIdField[0], JobSearchInfo.companyNameField[0],
                             JobSearchInfo.contactFullName[0], JobSearchInfo.custRefNumField[0], JobSearchInfo.promiseCompletionDateField[0],
                             JobSearchInfo.billingStatusField[0], JobSearchInfo.invoiceNameField[0], JobSearchInfo.contractDescField[0],
                             JobSearchInfo.contractStatusField[1], JobSearchInfo.createdByField[1], JobSearchInfo.modifiedByField[0],
                             JobSearchInfo.modifiedDateField[0] };
        */
        
        Map<String,String> map=new HashMap<String,String>();
        map.put("jobIdField",JobSearchInfo.jobIdField[0]);
        map.put("buNameField",JobSearchInfo.buNameField[0]);
        map.put("operatingUnitField",JobSearchInfo.operatingUnitField[0]);
        map.put("jobTypeField",JobSearchInfo.jobTypeField[0]);
        map.put("svcLocationNameField",JobSearchInfo.svcLocationNameField[0]);
        map.put("companyIdField",JobSearchInfo.companyIdField[0]);
        map.put("companyNameField",JobSearchInfo.companyNameField[0]);
        map.put("contactFullName",JobSearchInfo.contactFullName[0]);
        map.put("custRefNumField",JobSearchInfo.custRefNumField[0]);
        map.put("promiseCompletionDateField",JobSearchInfo.promiseCompletionDateField[0]);
        map.put("billingStatusField",JobSearchInfo.billingStatusField[0]);
        map.put("invoiceNameField",JobSearchInfo.invoiceNameField[0]);
        map.put("contractDescField",JobSearchInfo.contractDescField[0]);
        map.put("contractStatusField",JobSearchInfo.contractStatusField[0]);
        map.put("createdByField",JobSearchInfo.createdByField[0]);
        map.put("modifiedByField",JobSearchInfo.modifiedByField[0]);
        map.put("modifiedDateField",JobSearchInfo.modifiedDateField[0]);
        return map;

    }

}
