/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.purchaseorder;

import java.util.ArrayList;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.util.DateUtil;
import com.intertek.util.CommonUtil;

/**
 * Purpose: Domain/ command object to hold purchase order information.
 * 
 * @version 1.0 Apr 15, 2009
 * @author Patni
 */
public class PurchaseOrderForm extends Form {

    @CascadeValidation
    private PurchaseOrder purchaseOrder;
    private String custName;
    private String edit;
    private boolean disableEdit = false;
    private String custFlag;
    private String sortPODFlag;
    private Double totalAmount;
    private Double balanceCommitted;
    private String editJobNumber;
    private String startDate;
    private String completeDate;
    private String expectCompleteDate;
    
    
    private List<POJobDetailForm> poJobDetailForms;

    private Double totalInvoicedAmount;

    /**
     * Instantiates new POForm
     * @param po
     */
    public PurchaseOrderForm(PurchaseOrder po) {
        this(po, new ArrayList<CEJobContract>());
    }

    /**
     * Instantiates new POForm
     * @param po
     * @param jocList
     */
    public PurchaseOrderForm(PurchaseOrder po, List<CEJobContract> jocList) {
        this.purchaseOrder = po;
        Double totalAmnt = 0.0d;
        poJobDetailForms = new ArrayList<POJobDetailForm>();

        for (CEJobContract joco : jocList) {
            POJobDetailForm detailForm = new POJobDetailForm(joco);
            poJobDetailForms.add(detailForm);
            totalAmnt += detailForm.getTotalAmt();
        }

        this.totalAmount = totalAmnt;
    }

    public List<POJobDetailForm> getPoJobDetailForms() {
        return poJobDetailForms;
    }

    public void setPoJobDetailForms(List<POJobDetailForm> poJobDetailForms) {
        this.poJobDetailForms = poJobDetailForms;
    }

    public String getBuName() {
        return purchaseOrder.getBuName();
    }

    @NotBlank(errorCode = "not.blank")
    @Length(min = 0, max = 5)
    public void setBuName(String buName) {
        purchaseOrder.setBuName(buName);
    }

    public String getBranchCode() {
        return purchaseOrder.getBranchCode();
    }


    @NotBlank(errorCode = "not.blank")
    @Length(min = 0, max = 15)
    public void setBranchCode(String branchCode) {
        purchaseOrder.setBranchCode(branchCode);
    }

    public String getPoNo() {
        return purchaseOrder.getPoNumber();
    }

    @NotBlank(errorCode = "not.blank")
    @RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    @Length(min = 0, max = 128, errorCode = "lenght")
    public void setPoNo(String poNo) {
        purchaseOrder.setPoNumber(poNo);
    }

    public String getExpCompleteDate() {
        return DateUtil.dateToString(purchaseOrder.getExpCompleteDate());
    }

    @NotNull(errorCode = "not.blank")
    @NotBlank(errorCode = "not.blank")
    public void setExpCompleteDate(String expCompleteDate) {
        this.expectCompleteDate=expCompleteDate;
        purchaseOrder.setExpCompleteDate(DateUtil.stringToDate(expCompleteDate));
    }

    public String getCustCode() {
        return purchaseOrder.getCustCode();
    }

    @NotBlank(errorCode = "not.blank")
    @Length(min = 0, max = 15)
    public void setCustCode(String custCode) {
        purchaseOrder.setCustCode(custCode);
    }

    public String getCustName() {
        if (purchaseOrder.getCustomer() != null) {
            return purchaseOrder.getCustomer().getName();
        }
        return custName;
    }

    public void setCustName(String name) {
        custName = name;
    }

    public String getBeginDate() {
        return DateUtil.dateToString(purchaseOrder.getBeginDate());
    }

    public void setBeginDate(String beginDate) {
        this.startDate=beginDate;
        purchaseOrder.setBeginDate(DateUtil.stringToDate(beginDate));
    }

    public String getEndDate() {
        return DateUtil.dateToString(purchaseOrder.getEndDate());
    }

    public void setEndDate(String endDate) {
        this.completeDate=endDate;
        purchaseOrder.setEndDate(DateUtil.stringToDate(endDate));
    }

    public Double getMaxAmount() {
        return purchaseOrder.getMaxAmount();
    }

    @NotNull(errorCode = "not.blank")
    @Min(value = 1)
    public void setMaxAmount(Double maxAmount) {
        purchaseOrder.setMaxAmount(maxAmount);
    }

    public String getCurrency() {
        return purchaseOrder.getCurrency();
    }

    @Length(min = 0, max = 10)
    public void setCurrency(String currency) {
        purchaseOrder.setCurrency(currency);
    }

    public String getPoName() {
        return purchaseOrder.getPoName();
    }

    public void setPoName(String poName) {
        purchaseOrder.setPoName(poName);
    }

    public String getDescription() {
        return purchaseOrder.getDescription();
    }

    @Length(min = 0, max = 512)
    public void setDescription(String description) {
        purchaseOrder.setDescription(description);
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getDateFormat() {
        return getUserDateFormat();
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setDisableEdit(boolean disableEdit) {
        this.disableEdit = disableEdit;
    }

    public String getCustFlag() {
        return custFlag;
    }

    public void setCustFlag(String custFlag) {
        this.custFlag = custFlag;
    }

    public String getSortPODFlag() {
        return sortPODFlag;
    }

    public void setSortPODFlag(String sortPODFlag) {
        this.sortPODFlag = sortPODFlag;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getEditJobNumber() {
        return editJobNumber;
    }

    public void setEditJobNumber(String editJobNumber) {
        this.editJobNumber = editJobNumber;
    }

    @SuppressWarnings("unchecked")
    public List<Field> getCurrencyVal() {
        return (List<Field>) CommonUtil.getDropDown("currency", null);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getBuNames() {
        return (List<Field>) CommonUtil.getDropDown("businessUnit", null);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getBranchNames() {
        List<String> params = new ArrayList<String>();
        params.add(purchaseOrder.getBusinessUnit().getOrgName());
        params.add(purchaseOrder.getBusinessUnit().getName());

        return (List<Field>) CommonUtil.getDropDown("branch", params);
    }

    // Returns sum of balanceCommitted
    public Double getBalanceCommitted() {
//        balanceCommitted = 0.0;
//
//        for (CEJobOrderLineItem joli : purchaseOrder.getJobOrderLineItems()) {
//            // was using joli.getAccruedAmount(), should be joli.getCommittedAmount()
//            balanceCommitted = balanceCommitted + (joli.getCommittedAmount() - joli.getBilledAmount());
//        }

        return balanceCommitted;
    }
    
    // Returns sum of balanceCommitted
    public void setBalanceCommitted(double balanceCommited) {
        this.balanceCommitted = Double.valueOf(balanceCommited);;
    }
    
    // returns sum of Billed Amount of JobOrderLineItems
    public Double getTotalInvoicedAmount() {
        totalInvoicedAmount = 0.0;

        for (CEJobOrderLineItem joli : purchaseOrder.getJobOrderLineItems()) {
            totalInvoicedAmount = totalInvoicedAmount + joli.getBilledAmount();
        }
        
        return totalInvoicedAmount;
    }

    public Double getBalance() {
    	if(balanceCommitted==null){
    		balanceCommitted = new Double(0);
    	}
        return purchaseOrder.getMaxAmount() - (this.totalInvoicedAmount + this.balanceCommitted);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public String getExpectCompleteDate() {
        return expectCompleteDate;
    }

}
