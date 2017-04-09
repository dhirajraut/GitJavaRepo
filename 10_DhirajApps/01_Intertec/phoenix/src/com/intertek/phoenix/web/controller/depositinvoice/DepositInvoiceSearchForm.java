/*
 * DepositInvoiceSearchForm.java
 * 
 * @version 1.0
 * 
 * Jul 21, 2009
 * 
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 */
package com.intertek.phoenix.web.controller.depositinvoice;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.Search;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.common.ReferenceDataService.DropdownName;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.DepositType;
import com.intertek.phoenix.search.SearchCriteria;

/**
 * The Class DepositInvoiceSearchForm.
 */
public class DepositInvoiceSearchForm extends Search {
    private SearchCriteria buissinessUnit;
    private SearchCriteria branchId;

    /** The job id. */
    private SearchCriteria jobId;
    private SearchCriteria depInvoiceNo;
    private DepositInvoice di;
    private boolean paymentReceived;
    private String updateFlag = "false";
    private String depositInvoiceType;

    public SearchCriteria getBuissinessUnit() {
        return buissinessUnit;
    }

    public void setBuissinessUnit(SearchCriteria buissinessUnit) {

        this.buissinessUnit = buissinessUnit;
    }

    public SearchCriteria getBranchId() {
        return branchId;
    }

    public void setBranchId(SearchCriteria branchId) {
        this.branchId = branchId;
    }

    public SearchCriteria getJobId() {
        return jobId;
    }

    public void setJobId(SearchCriteria jobId) {
        this.jobId = jobId;
    }

    public SearchCriteria getDepInvoiceNo() {
        return depInvoiceNo;
    }

    public void setDepInvoiceNo(SearchCriteria depInvoiceNo) {
        this.depInvoiceNo = depInvoiceNo;
    }

    public String getJobNo() {
        return di.getJobContract().getJobOrderNumber();
    }

    public String getInvoiceNo() {
        return di.getInvoiceNumber();
    }

    public boolean getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    @SuppressWarnings("unchecked")
    public List<Field> getBuNames() {
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("businessUnit", null);
    }

    /**
     * Gets the search result.
     * 
     * @param diList
     *            the DepositInvoice list
     * 
     * @return the search result
     */
    @SuppressWarnings("unchecked")
    public List getSearchResult(List<DepositInvoice> diList) {
        List<DepositInvoiceSearchForm> result = new ArrayList<DepositInvoiceSearchForm>();

        for (DepositInvoice di : diList) {
            DepositInvoiceSearchForm form = new DepositInvoiceSearchForm();
            form.setDi(di);
            form.setPaymentReceived(di.isDepositPaid());
            form.setDepositInvoiceType(di.getDepositType().getName());
            result.add(form);
        }

        return result;
    }

    /**
     * Instantiates a new deposit invoice search form.
     */
    public DepositInvoiceSearchForm() {
        this.buissinessUnit = new SearchCriteria();
        this.buissinessUnit.setName("jo.buName");
        this.buissinessUnit.setOp("equals");
        this.branchId = new SearchCriteria();
        this.branchId.setName("jo.branchName");
        this.branchId.setOp("equals");
        this.jobId = new SearchCriteria();
        this.jobId.setName("jo.jobNumber");
        this.jobId.setOp("equals");
        this.depInvoiceNo = new SearchCriteria();
        this.depInvoiceNo.setName("di.invoiceNumber");
        this.depInvoiceNo.setOp("equals");
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public List<Field> getYesNos() {
        final ReferenceDataService refenceDataSerivce = ServiceManager.getReferenceDataService();
        return refenceDataSerivce.getDropdown(DropdownName.yesNo);
    }

    public List<DepositType> getDepositType() {
        final ReferenceDataService refenceDataSerivce = ServiceManager.getReferenceDataService();
        return refenceDataSerivce.getDepositTypeFields();
    }

    public DepositInvoice getDi() {
        return di;
    }

    public void setDi(DepositInvoice di) {
        this.di = di;
    }

    public String getDepositInvoiceType() {
        return depositInvoiceType;
    }

    public void setDepositInvoiceType(String depositInvoiceType) {
        this.depositInvoiceType = depositInvoiceType;
    }

}
