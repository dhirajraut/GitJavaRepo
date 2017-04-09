/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.DepositType;
import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.entity.PaymentType;

/**
 * Purpose: Form bean to represent DepositInvoice entity in context of CE
 * invoice preview page.
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class DepositInvoiceForm extends Form {
    private DepositInvoice depositInvoice;
    private Double amountToApply;
    private long sortNumber;
    private List<Field> paymentTypes;
    private List<Field> invoiceStatus;
    private boolean isSelected;

    // Currently there is no field in entity to map following fields
    private String primaryBranch;
    private String busStream;

    /**
     * Instantiates new DepositInvoiceForm
     * 
     * @param depositInvoice
     */
    public DepositInvoiceForm(DepositInvoice depositInvoice) {
        this.depositInvoice = depositInvoice;
    }

    public DepositInvoice getDepositInvoice() {
        return depositInvoice;
    }
    
    public void setDepositInvoice(DepositInvoice depositInvoice) {
        this.depositInvoice=depositInvoice;
    }
    
    public String getInvoiceNumber() {
        return depositInvoice.getInvoiceNumber();
    }

    public void getInvoiceNumber(String depositLineNo) {
        depositInvoice.setInvoiceNumber(depositLineNo);
    }

    public String getStatus() {
        return depositInvoice.getStatus().getValue();
    }

    public void setStatus(String code) {
        depositInvoice.setStatus(InvoiceStatus.getInvoiceStatus(code));
    }

    public Double getDepositAmount() {
        return depositInvoice.getDepositAmount();
    }

    public void setDepositAmount(Double depositAmount) {
        depositInvoice.setDepositAmount(depositAmount);
    }

    public DepositType getDepositType() {
        return depositInvoice.getDepositType();
    }

    public void setDepositType(DepositType depositType) {
        depositInvoice.setDepositType(depositType);
    }

    public String getDepositReferenceNo() {
        return depositInvoice.getDepositReference();
    }

    public void setDepositReferenceNo(String depositReferenceNo) {
        depositInvoice.setDepositReference(depositReferenceNo);
    }

    public String getPrimaryBranch() {
        return primaryBranch;
    }

    public void setPrimaryBranch(String primaryBranch) {
        this.primaryBranch = primaryBranch;
    }

    public Double getAvailableAmount() {
        return depositInvoice.getAvailableAmount();
    }

    public void setAvailableAmount(Double amount) {
        depositInvoice.setAvailableAmount(amount);
    }

    public Double getAmountToApply() {
        return amountToApply;
    }

    public void setAmountToApply(Double amountToApply) {
        this.amountToApply = amountToApply;
    }

    public String getPaymentType() {
        if(depositInvoice!= null && depositInvoice.getPaymentType()!=null){
        return depositInvoice.getPaymentType().value();
        }
        else
            return "";
    }

    public void setPaymentType(String value) {
        depositInvoice.setPaymentType(PaymentType.getPaymentType(value));
    }

    public String getAccount() {
        return depositInvoice.getAccount();
    }

    public void setAccount(String account) {
        depositInvoice.setAccount(account);
    }

    public String getDeptId() {
        return depositInvoice.getDeptId();
    }

    public void setDeptId(String deptId) {
        depositInvoice.setDeptId(deptId);
    }

    public String getBusStream() {
        return busStream;
    }

    public void setBusStream(String busStream) {
        this.busStream = busStream;
    }

    public List<Field> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<Field> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public List<Field> getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(List<Field> invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getDepInvoiceNumber() {
        return depositInvoice.getInvoiceNumber();
    }
    
    public boolean getShowEditAmount() {
        return (depositInvoice.getDepositAmount() <= 0
           || depositInvoice.getAvailableAmount() == depositInvoice.getDepositAmount())
           && InvoiceStatus.INVOICED != depositInvoice.getStatus();
    }

    public long getSortNumber() {
        return sortNumber;
    }

    @RegExp(errorCode = "not.a.number", value = "[0-9]*")
    public void setSortNumber(long sortNumber) {
        this.sortNumber = sortNumber;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
