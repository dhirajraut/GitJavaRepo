/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.common.ReferenceDataService.DropdownName;
import com.intertek.phoenix.entity.BillingEvent;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.JobOrderType;
import com.intertek.phoenix.entity.Project;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.web.controller.invoice.DepositInvoiceForm;

/**
 * Form to represent 'Select Charges' page
 * 
 * @author rautsmit
 * 
 */
public class CESelectChargeForm extends Form {

    private CEJobContract jobContract;
    private DepositInvoiceForm[] depositInvoiceForms;
    private String refreshing;
    private int productIndex;
    private String joliIndex;
    private double extendedPriceTotal;
    private CEServiceLevelForm[] products;
    private CEBatchForm[] batchForms;
    private boolean noOfBatches = true;
    private String selBatch;

    public boolean isNoOfBatches() {
        if (batchForms.length == 1) {
            noOfBatches = false;
        }
        return noOfBatches;
    }

    public CESelectChargeForm() {
    }

    public CESelectChargeForm(CEJobContract jo) {
        updateForm(jo);
    }

    /**
     * Rebuilds form data with passed JobOrder
     * 
     * @param jobContract
     */
    public void updateForm(CEJobContract jobContract) {
        this.jobContract = jobContract;
        // type1 and 2 processing
        if (!getIsType3Project()) {
            Set<DepositInvoice> depInvoices = jobContract.getDepositInvoices();
            depositInvoiceForms = new DepositInvoiceForm[depInvoices.size()];
            int depCounter = 0;
            for (DepositInvoice depInv : depInvoices) {
                DepositInvoiceForm depInvForm = new DepositInvoiceForm(depInv);
                depInvForm.setSortNumber(depInv.getSortNumber());
                depositInvoiceForms[depCounter++] = depInvForm;
            }
            Arrays.sort(depositInvoiceForms, new DepositInvoiceComparator());

            Collection<ContractServiceLevel> srvLevel = jobContract.getRootServiceLevel().getChildServiceLevels();
            products = new CEServiceLevelForm[srvLevel.size()];
            int slCounter = 0;
            for (ContractServiceLevel srvLvl : srvLevel) {
                CEServiceLevelForm slForm = new CEServiceLevelForm(srvLvl);
                products[slCounter++] = slForm;
            }
        }
        // type-3 processing
        else if (getIsType3Project()) {
            batchForms = new CEBatchForm[jobContract.getBillingEvents().size()];
            int batchCounter = 0;
            for (BillingEvent billingEvent : jobContract.getBillingEvents()) {
                batchForms[batchCounter++] = new CEBatchForm(billingEvent);
            }

            setSelBatch("0");
        }
    }

    public String getBaseCurrency() {
        return jobContract.getContractCurrency();
    }

    public String getBillingCurrency() {
        return jobContract.getTransactionCurrency();
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }

    public String getJobNumber() {
        return jobContract.getJobOrder().getJobNumber();
    }

    public String getJobStatus() {
        return jobContract.getJobOrder().getStatus().getDescription();
    }

    public DepositInvoiceForm[] getDepositInvoiceForms() {
        return depositInvoiceForms;
    }

    public void setDepositInvoiceForms(DepositInvoiceForm[] depInvForms) {
        this.depositInvoiceForms = depInvForms;
    }

    // extended price total

    // TODO: revisit
    public double getExtendedPriceTotal() {
        extendedPriceTotal = 0.0;

        /*
         * for (CEJobOrderLineItemForm joliForms : this.joliForms) {
         * extendedPriceTotal = extendedPriceTotal +
         * joliForms.getExtendedPrice(); }
         */
        return extendedPriceTotal;
    }

    public String getRefreshing() {
        return refreshing;
    }

    public void setRefreshing(String refreshing) {
        this.refreshing = refreshing;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    public String getJoliIndex() {
        return joliIndex;
    }

    public void setJoliIndex(String joliIndex) {
        this.joliIndex = joliIndex;
    }

    public CEServiceLevelForm[] getProducts() {
        return products;
    }

    public void setProducts(CEServiceLevelForm[] products) {
        this.products = products;
    }

    public List<Field> getProductGroupList() {
        return refenceDataSerivce.getProductGroups(jobContract.getProductType());
    }

    public CEBatchForm[] getBatchForms() {
        return batchForms;
    }

    public boolean isDisplayNewProductIcon() {
        if(jobContract.getJobOrder().getJobType().equals(JobOrderType.CE)) {
            //For CE, allow to add only one product/ SL
            return (this.products == null || this.products.length <= 0);
        }
        
        return true;
    }

    public boolean getIsType3Project() {
        Project project = jobContract.getJobOrder().getProject();
        return (project != null && project.getType().equals(ProjectType.TYPE_3));
    }

    public void assignLineNumbers(CESelectChargeForm ceSelectChargesForm, int productIndex) {
        if (getIsType3Project()) {
            CEBatchForm[] batchFrm = ceSelectChargesForm.getBatchForms();
            CEScJobOrderLineItemForm[] batJoliFrm = batchFrm[productIndex].getJoliForms();
            for (CEScJobOrderLineItemForm jForm : batJoliFrm) {
                jForm.getCeJobOrderLineItem().setLineNumber(jForm.getSortNumber());
            }
        }
        else {
            CEServiceLevelForm[] srvLvFrm = ceSelectChargesForm.getProducts();
            CEScJobOrderLineItemForm[] joliFrm = srvLvFrm[productIndex].getJoliForms();

            for (CEScJobOrderLineItemForm jForm : joliFrm) {
                jForm.getCeJobOrderLineItem().setLineNumber(jForm.getSortNumber());

                CEScJobOrderLineItemForm[] splitFrm = jForm.getSplitItems();
                for (CEScJobOrderLineItemForm spFrm : splitFrm) {
                    spFrm.getCeJobOrderLineItem().setLineNumber(spFrm.getSortNumber());
                }
            }
        }
    }

    public void selectJOLIForms(Set<Long> selectedJoliIds) {
        if (getIsType3Project()) {
            for (CEBatchForm batchForm : batchForms) {
                for (CEScJobOrderLineItemForm joliForm : batchForm.getJoliForms()) {
                    if (selectedJoliIds.contains(joliForm.getId())) {
                        batchForm.setSelected(true);
                        break;
                    }
                }
            }
        }
        else {
            for (CEServiceLevelForm slForm : products) {
                for (CEScJobOrderLineItemForm joliForm : slForm.getJoliForms()) {
                    if (selectedJoliIds.contains(joliForm.getId())) {
                        joliForm.setSelectedFlag(true);
                    }
                    for (CEScJobOrderLineItemForm splitForm : joliForm.getSplitItems()) {
                        if (selectedJoliIds.contains(splitForm.getId())) {
                            splitForm.setSelectedFlag(true);
                        }
                    }
                }
            }
        }
    }

    public void selectDepInvForms(Set<Long> selectedDepIds) {
        for (DepositInvoiceForm depForm : depositInvoiceForms) {
            if (selectedDepIds.contains(depForm.getDepositInvoice().getId())) {
                depForm.setSelected(true);
            }
        }
    }

    public void assignDepInvSortNumbers(CESelectChargeForm ceSelectChargesForm) {
        for (DepositInvoiceForm depForm : ceSelectChargesForm.getDepositInvoiceForms()) {
            depForm.getDepositInvoice().setSortNumber(depForm.getSortNumber());
        }
    }

    public String getSelBatch() {
        return selBatch;
    }

    public void setSelBatch(String selBatch) {
        this.selBatch = selBatch;
    }

    public BillingEvent getSelectedBatch() {
        int index = Integer.parseInt(selBatch);
        return batchForms[index].getBillingEvent();
    }

    /**
     * To order/ sort JOLIs using line number
     * 
     * @author rautsmit
     * 
     */
    private class DepositInvoiceComparator implements Comparator<DepositInvoiceForm> {
        @Override
        public int compare(DepositInvoiceForm form1, DepositInvoiceForm form2) {
            Long lineNum1 = form1.getDepositInvoice().getSortNumber();
            Long lineNum2 = form2.getDepositInvoice().getSortNumber();

            return lineNum1.compareTo(lineNum2);
        }
    }
}
