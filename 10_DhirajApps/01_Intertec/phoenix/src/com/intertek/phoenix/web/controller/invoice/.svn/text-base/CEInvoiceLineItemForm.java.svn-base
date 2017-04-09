/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.util.ArrayList;
import java.util.List;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.EnumField;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.entity.TestJobOrderLineItem;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.util.DateUtil;

/**
 * Purpose: Form bean for CEInvoiceLineItem entity in context of CE invoice
 * preview page.
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class CEInvoiceLineItemForm extends Form {
    private CEInvoiceLineItem ceInvoiceLineItem;
    private CEJobOrderLineItemForm joLineItemForm;
    private List<Field> invoiceStatus;
    private String taxVatDate;

    // TODO: get rid of these
    // Below fields are introduced as there are no relevant fields in the entity
    // class will be replaced with relevant entity fields upon confirmation
    private double distributionAmount;

    /**
     * Instantiates new CEInvoiceLineItemForm
     * 
     * @param ceInvoiceLineItem
     */
    public CEInvoiceLineItemForm(CEInvoiceLineItem ceInvoiceLineItem) {
        this.ceInvoiceLineItem = ceInvoiceLineItem;
    }

    public CEInvoiceLineItem getCeInvoiceLineItem() {
        return ceInvoiceLineItem;
    }

    public void setCEInvoiceLineItem(CEInvoiceLineItem ceInvoiceLineItem) {
        this.ceInvoiceLineItem = ceInvoiceLineItem;
    }

    public String getStatus() {
        return ceInvoiceLineItem.getInvoice().getStatus().getValue();
    }

    public void setStatus(String code) {
        // TODO: not sure on this... Need to confirm
        // ceInvoiceLineItem.getInvoice().setStatus(InvoiceStatus.getInvoiceStatus
        // (code));
    }

    public String getDescription() {
        // TODO: confirm
        return ceInvoiceLineItem.getCEJobOrderLineItem().getDescription();
    }

    public void setDescription(String description) {
        ceInvoiceLineItem.getCEJobOrderLineItem().setDescription(description);
    }

    public List<EnumField> getServiceOfferings() throws JobSrvcException {
        List<EnumField> fieldlist = new ArrayList<EnumField>();
//         ServiceOffering serviceOffering = ceInvoiceLineItem.getCEJobOrderLineItem().getServiceOffering();
//         if (serviceOffering != null){
//         return fieldlist;
//         }
        if (((TestJobOrderLineItem) ceInvoiceLineItem.getCEJobOrderLineItem()).getJobContractTask().getTestId() != null) {
            List<ServiceOffering> serviceOfferings = ServiceManager.getJobOrderService().getServiceOffering(
                                                                                                            ((TestJobOrderLineItem) ceInvoiceLineItem
                                                                                                                    .getCEJobOrderLineItem())
                                                                                                                    .getJobContractTask().getTestId());
            for (ServiceOffering so : serviceOfferings) {
                fieldlist.add(so);
            }
            return fieldlist;
        }
        else
            return null;
    }

    public String getTaxCode() {
        return ceInvoiceLineItem.getTaxCode();
    }

    public double getDistributionAmount() {
        return distributionAmount;
    }

    public void setDistributionAmount(double distributionAmount) {
        this.distributionAmount = distributionAmount;
    }

    public double getTaxPct() {
        return ceInvoiceLineItem.getTaxPct();
    }

    public void setTaxPct(double taxPct) {
        ceInvoiceLineItem.setTaxPct(taxPct);
    }

    public double getVatPct() {
        return ceInvoiceLineItem.getVatPct();
    }

    public void setVatPct(double vatPct) {
        ceInvoiceLineItem.setVatPct(vatPct);
    }

    public void setTaxCode(String taxCode) {
        ceInvoiceLineItem.setTaxCode(taxCode);
    }

    public Double getTax() {
        return ceInvoiceLineItem.getTax();
    }

    public void setTax(Double tax) {
        ceInvoiceLineItem.setTax(tax);
    }

    public Double getTaxAmt() {
        return ceInvoiceLineItem.getTaxAmt();
    }

    public void setTaxAmt(Double taxAmt) {
        ceInvoiceLineItem.setTaxAmt(taxAmt);
    }

    public String getVatCode() {
        return ceInvoiceLineItem.getVatCode();
    }

    public void setVatCode(String vatCode) {
        ceInvoiceLineItem.setVatCode(vatCode);
    }

    public Double getVat() {
        return ceInvoiceLineItem.getVat();
    }

    public void setVat(Double vat) {
        ceInvoiceLineItem.setVat(vat);
    }

    public Double getVatAmt() {
        return ceInvoiceLineItem.getVatAmt();
    }

    public void setVatAmt(Double vatAmt) {
        ceInvoiceLineItem.setVatAmt(vatAmt);
    }

    public Double getNetPrice() {
        return ceInvoiceLineItem.getNetPrice();
    }

    public void setNetPrice(Double netPrice) {
        ceInvoiceLineItem.setNetPrice(netPrice);
    }

    public CEJobOrderLineItemForm getJoLineItemForm() {
        return joLineItemForm;
    }

    public void setJoLineItemForm(CEJobOrderLineItemForm joLineItemForm) {
        this.joLineItemForm = joLineItemForm;
    }

    public Boolean getSelectedFlag() {
        return ceInvoiceLineItem.isSelected();
    }

    public void setSelectedFlag(Boolean selectedFlag) {
        ceInvoiceLineItem.setSelected(selectedFlag);
    }

    public List<Field> getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(List<Field> invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getTaxDate() {
        return DateUtil.dateToString(ceInvoiceLineItem.getTaxDate());
    }

    public void setTaxDate(String taxDate) {
        this.taxVatDate=taxDate;
        ceInvoiceLineItem.setTaxDate(DateUtil.stringToDate(taxDate));
    }

    public String getDateFormat() {
        return getUserDateFormat();
    }

    public String getServiceOffering() {
        String svcoff = "";
        if (ceInvoiceLineItem.getCEJobOrderLineItem().getServiceOffering() != null) {
            svcoff = ceInvoiceLineItem.getCEJobOrderLineItem().getServiceOffering().getId() + "";
        }
        return svcoff;
    }

    public void setServiceOffering(String soId) {
        // TODO to which this needs to be set.
        ServiceOffering serviceOffering = ceInvoiceLineItem.getCEJobOrderLineItem().getServiceOffering();
        Long serviceOfferingId = 0l;
        if (serviceOffering != null) {
            serviceOfferingId = serviceOffering.getId();
        }
        try {
            serviceOfferingId = Long.parseLong(soId);
        }
        catch (NumberFormatException e) {
        }
        ceInvoiceLineItem.getCEJobOrderLineItem().setServiceOfferingId(serviceOfferingId);
    }

    public String getTaxVatDate() {
        return taxVatDate;
    }

}
