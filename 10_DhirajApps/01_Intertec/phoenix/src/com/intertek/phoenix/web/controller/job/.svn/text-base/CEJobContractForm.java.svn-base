/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.entity.Contract;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.PaymentType;
import com.intertek.phoenix.entity.SourceOrigin;
import com.intertek.phoenix.util.CommonUtil;

/**
 * This class is for the 'Add Customer' tab on job_entry_ce.jsp
 * 
 * @author lily.sun
 * @author eric.nguyen
 */

public class CEJobContractForm extends Form {
    private CEJobContract jobContract;
    private String inputFieldIdValue;
    private String tabName = "1";
    private String billingAddress;
    private String billingContactName;
    private String reportReceivingContactName;
    private String supplierContactName;
    private String manufacturerContactName;
    private String reportReceivingAddress;
    private String manufacturerAddress;
    private String supplierAddress;
    private String parentContractCode;
    
    
    public CEJobContractForm(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }

    public String getInputFieldIdValue() {
        return inputFieldIdValue;
    }

    public void setInputFieldIdValue(String inputFieldIdValue) {
        this.inputFieldIdValue = inputFieldIdValue;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public boolean isCustomerExist() {
        if (getJobContract() != null && getJobContract().getCustomerCode() != null && !"".equals(getJobContract().getCustomerCode())) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getCustomerName() {
        if (getJobContract().getCustomer() != null) {
            return getJobContract().getCustomer().getName();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public List<Field> getZoneIdList() {
        /*
         * <icb:list var="pcodes">
         * <icb:item>${command.addJobContracts[counter.index].jobContract.locationDesc}</icb:item>
         * <icb:item>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</icb:item>
         * <icb:item>${command.addJobContracts[counter.index].jobContract.zone}</icb:item>
         * <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
         * <icb:item>${command.jobOrder.etaDate}</icb:item>
         * <icb:item>${command.addJobContracts[counter.index].priceBookId}</icb:item>
         * </icb:list> ${icbfn:dropdown('zoneId', pcodes)}
         */

        List<String> params = new ArrayList<String>();
        params.add(getZoneDescription());
        params.add(getContractName());
        params.add(getZoneId());
        params.add(""); // Use current date for jobFinished Date
        params.add(""); // Use current date for etaDate
        Contract contract = getJobContract().getContract();
        // Is the working book good for priceBookId
        params.add(contract == null ? "" : contract.getWorkingPB()); 
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("zoneId", params);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getZoneDescriptionList() {
        /*
         * <icb:list var="contracts">
         * <icb:item>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</icb:item>
         * <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
         * <icb:item>${command.jobOrder.etaDate}</icb:item>
         * <icb:item>${command.addJobContracts[counter.index].priceBookId}</icb:item>
         * </icb:list> items="${icbfn:dropdown('portCode', contracts)}"
         */
        List<String> params = new ArrayList<String>();
        params.add(getContractName());
        params.add("");
        params.add("");
        Contract contract = getJobContract().getContract();
        // Is the working book good for priceBookId
        params.add(contract == null ? "" : contract.getWorkingPB()); 
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("portCode", params);
    }

    @SuppressWarnings("unchecked")
    // TODO lily this needs to be revisited to see if we can use
    // referenceDataService
    public List<Field> getVatRegIds() {
        List<String> params = new ArrayList<String>();
        params.add(getJobContract().getCustomerCode());
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("vatRegIds", params);
    }

    @SuppressWarnings("unchecked")
    // TODO lily this needs to be revisited to see if we can use
    // referenceDataService
    public List<Field> getTransactionCurrencies() {
        List<String> params = new ArrayList<String>();
       // params.add(getJobContract().getTransactionCurrency());//This line changed to get the transaction currencies
                                                                // based on the contract currencies.
        params.add(getJobContract().getContractCurrency());
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("currencyTrans", params);
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public String getSourceOrigin() {
        if (getJobContract() != null && getJobContract().getSourceOrigin() != null)
            return getJobContract().getSourceOrigin().getValue();
        else
            return "";
    }

    // TODO extract to be a common method
    public void setSourceOrigin(String origin) {
        SourceOrigin[] list = SourceOrigin.list();
        for (SourceOrigin sourceOrigin : list) {
            if (sourceOrigin.name().equals(origin)) {
                getJobContract().setSourceOrigin(sourceOrigin);
                break;
            }
        }
    }

    public String getPaymentType() {
        if (getJobContract() != null && getJobContract().getPaymentType() != null)
            return getJobContract().getPaymentType().getValue();
        else
            return "";
    }

    public void setPaymentType(String paymentType) {
        PaymentType[] list = PaymentType.list();
        for (PaymentType pt : list) {
            if (pt.getValue().equals(paymentType)) {
                getJobContract().setPaymentType(pt);
                break;
            }
        }
    }

    public String getContactAddress() {
        if (getJobContract().getContactAddress() != null) {
            return getJobContract().getContactAddress().getFullAddress();
        }
        return "";
    }

    public String getReportReceivingAddress() {
        if (getJobContract().getReportReceivingAddress() != null) {
            return getJobContract().getReportReceivingAddress().getFullAddress();
        }
        return reportReceivingAddress;
    }

    public String getBillingAddress() {
        if (getJobContract().getBillingAddress() != null) {
            return getJobContract().getBillingAddress().getFullAddress();
        }
        return billingAddress;
    }

    public String getManufacturerAddress() {
        if (getJobContract().getManufacturerAddress() != null) {
            return getJobContract().getManufacturerAddress().getFullAddress();
        }
        return manufacturerAddress;
    }

    // for supplier address
    public String getSupplierAddress() {
        if (getJobContract().getSupplierAddress() != null) {
            return getJobContract().getSupplierAddress().getFullAddress();
        }
        return supplierAddress;
    }

    public String getContactName() {
        if (getJobContract().getContact() != null) {
            return getJobContract().getContact().getFirstName() + " " + getJobContract().getContact().getLastName();
        }
        return "";
    }

    public String getBillingContactName() {
        if (getJobContract().getBillingContact() != null) {
            return getJobContract().getBillingContact().getFirstName() + " " + getJobContract().getBillingContact().getLastName();
        }
        return billingContactName;
    }

    public String getReportReceivingContactName() {
        if (getJobContract().getReportReceivingContact() != null) {
            return getJobContract().getReportReceivingContact().getFirstName() + " " + getJobContract().getReportReceivingContact().getLastName();
        }
        return reportReceivingContactName;
    }

    // for supplier contactName
    public String getSupplierContactName() {
        if (getJobContract().getSupplierContact() != null) {
            return getJobContract().getSupplierContact().getFirstName() + " " + getJobContract().getSupplierContact().getLastName();
        }
        return supplierContactName;
    }

    public String getManufacturerContactName() {
        if (getJobContract().getManufacturerContact() != null) {
            return getJobContract().getManufacturerContact().getFirstName() + " " + getJobContract().getManufacturerContact().getLastName();
        }
        return manufacturerContactName;
    }

    private String warnUserFlag;

    public String getWarnUserFlag() {
        return warnUserFlag;
    }

    public void setWarnUserFlag(String warnUserFlag) {
        this.warnUserFlag = warnUserFlag;
    }

    public String getReportReceivingContactId() {
        return CommonUtil.toString(getJobContract().getReportReceivingContactId());
    }

//    public long getReportReceivingContactId() {
//        if (getJobContract().getReportReceivingContactId() == null) {
//            return 0L;
//        }
//        else {
//            return getJobContract().getReportReceivingContactId();
//        }
//
//    }

    public void setReportReceivingContactId(String reportReceivingContactId) {
        getJobContract().setReportReceivingContactId(CommonUtil.toLongOrNull(reportReceivingContactId));
    }
    
    public String getParentContractCode(){
        return parentContractCode;
    }
    
    public void setParentContractCode(String parentContractCode){
        this.parentContractCode = parentContractCode;
    }
    
//    public void setReportReceivingContactId(long reportReceivingContactId) {
//        if(reportReceivingContactId != 0){
//            getJobContract().setReportReceivingContactId(reportReceivingContactId);
//        }
//        else{
//            getJobContract().setReportReceivingContactId(null);
//        }
//    }

    // Currently getting null value for getBillingContactId,
    // so we made the changes to return 0
    public String getBillingContactId() {
        return CommonUtil.toString(getJobContract().getBillingContactId());
    }

    public void setBillingContactId(String billingContactId) {
        getJobContract().setBillingContactId(CommonUtil.toLongOrNull(billingContactId));
    }

    // Currently getting null value for getManufacturerContactId,
    // so we made the changes to return 0
    public String getManufacturerContactId() {
        return CommonUtil.toString(getJobContract().getManufacturerContactId() );
    }
    public void setManufacturerContactId(String  manufacturerContactId) {
        getJobContract().setManufacturerContactId(CommonUtil.toLongOrNull(manufacturerContactId));
    }

    public String getSupplierContactId() {
        return CommonUtil.toString(getJobContract().getSupplierContactId());
    }

    public void setSupplierContactId(String supplierContactId) {
        getJobContract().setSupplierContactId(CommonUtil.toLongOrNull(supplierContactId));
    }

    public String getRemitToCode() {
        return getJobContract().getRemitToCode();
//        if (getJobContract().getRemitTo() != null) {
//            return getJobContract().getRemitTo().getBankCode();
//        }
        
    }

    public void setRemitToCode(String remitToCode) {
        getJobContract().setRemitToCode(CommonUtil.toStringOrNull(remitToCode));
    }

    public String getRemitToBankAccountNum() {
//        if (getJobContract().getRemitToBankAccount() != null) {
//            return getJobContract().getRemitToBankAccount().getBankAccountId().getBankAcctCode();
//        }
//        return "";
        return getJobContract().getRemitToBankAccountNum();
    }

    public void setRemitToBankAccountNum(String remitToBankAccountNum) {
        getJobContract().setRemitToBankAccountNum(CommonUtil.toStringOrNull(remitToBankAccountNum));
    }

    public String getContractName() {
        if (getJobContract().getContract() != null) {
            return getJobContract().getContract().getContractCode();
        }
        return "";
    }

    public String getContractDescription() {
        if (getJobContract().getContract() != null) {
            return getJobContract().getContract().getDescription();
        }
        return "";
    }

    public String getZoneId() {
        if (getJobContract().getZoneId() != null) {
            return getJobContract().getZoneId();
        }
        return "";
    }

    public String getZoneDescription() {
        if (getJobContract().getZoneDescription() != null) {
            return getJobContract().getZoneDescription();
        }
        return "";
    }

    public void setZoneId(String zoneId) {
        getJobContract().setZoneId(CommonUtil.toStringOrNull(zoneId));
    }

    public void setZoneDescription(String zoneDescription) {
        getJobContract().setZoneDescription(CommonUtil.toStringOrNull(zoneDescription));
    }

    public String getContactAddressId() {
        if (getJobContract().getContactAddress() != null) {
            return getJobContract().getContactAddress().getId() + "";
        }
        return "";
    }

    public void setContactAddressId(String addrId) {
        getJobContract().setContactAddressId(CommonUtil.toLongOrNull(addrId));
    }

    public String getBillingAddressId() {
        if (getJobContract().getBillingAddress() != null) {
            return getJobContract().getBillingAddress().getId() + "";
        }
        return "";
    }

    public void setBillingAddressId(String addrId) {
        getJobContract().setBillingAddressId(CommonUtil.toLongOrNull(addrId));
    }

    public String getReportReceivingAddressId() {
        if (getJobContract().getReportReceivingAddress() != null) {
            return getJobContract().getReportReceivingAddress().getId() + "";
        }
        return "";
    }

    public void setReportReceivingAddressId(String addrId) {
        getJobContract().setReportReceivingAddressId(CommonUtil.toLongOrNull(addrId));
    }

    public String getManfAddressId() {
        if (getJobContract().getManufacturerAddress() != null) {
            return getJobContract().getManufacturerAddress().getId() + "";
        }
        return "";
    }

    public void setManfAddressId(String addrId) {
        getJobContract().setManfAddressId(CommonUtil.toLongOrNull(addrId));
    }

    public String getSupplierAddressId() {
        if (getJobContract().getSupplierAddress() != null) {
            return getJobContract().getSupplierAddress().getId() + "";
        }
        return "";
    }

    public void setSupplierAddressId(String addrId) {
        getJobContract().setSupplierAddressId(CommonUtil.toLongOrNull(addrId));
    }

    public String getBillingCustCode() {
        if (getJobContract().getBillingCustomer() != null) {
            return getJobContract().getBillingCustomer().getCustCode();
        }
        return "";
    }

    public void setBillingCustCode(String billingCustCode) {
        getJobContract().setBillingCustCode(CommonUtil.toStringOrNull(billingCustCode));
    }

    public String getReportReceivingCustCode() {
        if (getJobContract().getReportReceivingCustomer() != null) {
            return getJobContract().getReportReceivingCustomer().getCustCode();
        }
        return "";
    }

    public void setReportReceivingCustCode(String reportReceivingCustCode) {
        getJobContract().setReportReceivingCustCode(CommonUtil.toStringOrNull(reportReceivingCustCode));
    }

    public String getSupplierCustCode() {
        if (getJobContract().getSupplierCustomer() != null) {
            return getJobContract().getSupplierCustomer().getCustCode();
        }
        return "";
    }

    public void setSupplierCustCode(String supplierCustCode) {
        getJobContract().setSupplierCustCode(CommonUtil.toStringOrNull(supplierCustCode));
    }

    public String getManfCustCode() {
        if (getJobContract().getManufacturerCustomer() != null) {
            return getJobContract().getManufacturerCustomer().getCustCode();
        }
        return "";
    }

    public void setManfCustCode(String manufacturerCustCode) {
        getJobContract().setManfCustCode(CommonUtil.toStringOrNull(manufacturerCustCode));
    }

    public String getId() {
        return CommonUtil.toString(getJobContract().getId());
    }

    public CEJobOrderLineItem[] getCejobOrderLineItems() {
        if (jobContract.getJobOrderLineItems() != null) {
            Set<CEJobOrderLineItem> st = jobContract.getJobOrderLineItems();
            CEJobOrderLineItem[] result = st.toArray(new CEJobOrderLineItem[st.size()]);
            CEJobOrderLineItem tmpItem = null;
            for (int i = result.length - 1; i >= 0; i--) {
                for (int k = i - 1; k >= 0; k--) {
                    if (result[k].getLineNumber() > result[i].getLineNumber()) {
                        tmpItem = result[i];
                        result[i] = result[k];
                        result[k] = tmpItem;
                    }
                }
            }

            return result;
        }
        else {
            return null;
        }
    }

    public void setCejobOrderLineItems(CEJobOrderLineItem[] ceJobOrderLineItems) {
        Set<CEJobOrderLineItem> hs = new HashSet<CEJobOrderLineItem>();
        for (CEJobOrderLineItem cj : ceJobOrderLineItems) {
            hs.add(cj);
        }
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }

    public void setReportReceivingContactName(String reportReceivingContactName) {
        this.reportReceivingContactName = reportReceivingContactName;
    }

    public void setSupplierContactName(String supplierContactName) {
        this.supplierContactName = supplierContactName;
    }

    public void setManufacturerContactName(String manufacturerContactName) {
        this.manufacturerContactName = manufacturerContactName;
    }

    public void setReportReceivingAddress(String reportReceivingAddress) {
        this.reportReceivingAddress = reportReceivingAddress;
    }

    public void setManufacturerAddress(String manufacturerAddress) {
        this.manufacturerAddress = manufacturerAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
    
}
