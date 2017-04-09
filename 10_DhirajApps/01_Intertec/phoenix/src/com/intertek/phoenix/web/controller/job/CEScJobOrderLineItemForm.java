/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.invoice.RevenueSegregationForm;

/**
 * Form to represent JOLIs on SC page
 * @author rautsmit
 *
 */
public class CEScJobOrderLineItemForm extends Form {
    private CEJobOrderLineItem ceJobOrderLineItem;
    private ContractServiceLevel parentServiceLevel;
    private RevenueSegregationForm[] revenueSegregationForms;
    private CEScJobOrderLineItemForm[] splitItems;
    private long sortNumber;
    private boolean selectedFlag;

    /**
     * Instantiates new CEScJobOrderLineItemForm
     * 
     * @param ceJobOrderLineItem
     */
    public CEScJobOrderLineItemForm(CEJobOrderLineItem ceJobOrderLineItem, ContractServiceLevel product) {
        this.ceJobOrderLineItem = ceJobOrderLineItem;
        this.parentServiceLevel = product;

        Set<CEJobOrderLineItem> jLI = ceJobOrderLineItem.getRelated();
        splitItems = new CEScJobOrderLineItemForm[jLI.size()];
        int count = 0;
        for (CEJobOrderLineItem jLineItem : jLI) {
            splitItems[count] = (new CEScJobOrderLineItemForm(jLineItem, product));

            //line number assigned to sort number
            splitItems[count].setSortNumber(jLineItem.getLineNumber());
            count++;
        }

        Arrays.sort(splitItems, new SplitJoliComparator());
    }

    public Long getId() {
        return ceJobOrderLineItem.getId();
    }

    public double getBaseCurrencyAmount() {
        return ceJobOrderLineItem.getBaseUnitPrice();
    }

    public double getBillingCurrencyAmount() {
        return ceJobOrderLineItem.getUnitPrice();
    }

    public double getBillingAmount() {
        return ceJobOrderLineItem.getBillingAmount();
    }

    public boolean getIsNotBillable() {
        return !ceJobOrderLineItem.isBillable();
    }

    public boolean isInvoiceable() {
        return ceJobOrderLineItem.getBillingStatus() != BillingStatus.NOT_INVOICEABLE;
    }

    public boolean isShowEditPriceIcon() {

        Collection<ContractServiceLevel> productSLs = this.ceJobOrderLineItem.getJobContract().getRootServiceLevel().getChildServiceLevels();
        for (ContractServiceLevel prodSL : productSLs) {
            Set<JobContractTest> jcTest = prodSL.getJobContractTests();
            for (JobContractTest jTest : jcTest) {
                if (jTest.getJobOrderLineItem().getId().equals(ceJobOrderLineItem.getId())) {

                    return true;
                }
            }
        }

        return false;
    }

    public boolean isDelete() {
        JobOrderService jobOrderService = ServiceManager.getJobOrderService();
        if (parentServiceLevel != null) {
            for (JobContractTest test : parentServiceLevel.getJobContractTests()) {
                for (CEJobOrderLineItem joli : test.getJobOrderLineItems()) {
                    if (joli.getId().equals(ceJobOrderLineItem.getId())) {
                        return jobOrderService.canRemoveJobContractTest(test);
                    }
                }
            }

            //Delete is not supported for services as of now
            /*            for(JobContractService service : parentServiceLevel.getJobContractServices()) {
             for(JobContractServiceExpression expr : service.getJobContractServiceExpresions()) {
             if(expr.getJobOrderLineItem().getId().equals(ceJobOrderLineItem.getId())) {
             return jobOrderService.canRemoveJobContractService(service);
             }
             }
             }
             */
        }

        return false;
    }

    public long getLineNumber() {
        return ceJobOrderLineItem.getLineNumber();
    }

    public long getSortNumber() {
        return this.sortNumber;
    }

    @RegExp(errorCode = "not.a.number", value = "[0-9]*")
    public void setSortNumber(long sortNumber) {
        this.sortNumber = sortNumber;
    }

    public boolean isSelectedFlag() {
        return selectedFlag;
    }

    public void setSelectedFlag(boolean selectedFlag) {
        this.selectedFlag = selectedFlag;
    }

    public String getDescription() {
        return ceJobOrderLineItem.getDescription();
    }

    public void setDescription(String description) {
        ceJobOrderLineItem.setDescription(description);
    }

    public String getTestDescription() {

        // TODO: "many fields not belong to CEJobOrderLineItem, instead,
        // they belong to CEJobTest". Change it accordingly

        /*
         * JobTest test = ceJobOrderLineItem.getJobTest(); return ( (test ==
         * null) ? "" : test.getLineDescription() );
         */

        return "";
    }

    public Double getDiscount() {
        return ceJobOrderLineItem.getDiscountPct();
    }

    public Double getExtendedPrice() {
        return ceJobOrderLineItem.getNetPrice();
    }

    public CEJobOrderLineItem getCeJobOrderLineItem() {
        return ceJobOrderLineItem;
    }

    public RevenueSegregationForm[] getRevenueSegregationForms() {
        return revenueSegregationForms;
    }

    public void setRevenueSegregationForms(RevenueSegregationForm[] revenueSegregationForms) {
        this.revenueSegregationForms = revenueSegregationForms;
    }

    public CEScJobOrderLineItemForm[] getSplitItems() {
        return splitItems;
    }

    /**
     * To order/ sort JOLIs using line number
     * @author rautsmit
     *
     */
    private class SplitJoliComparator implements Comparator<CEScJobOrderLineItemForm> {
        @Override
        public int compare(CEScJobOrderLineItemForm form1, CEScJobOrderLineItemForm form2) {
            long lineNum1 = form1.getCeJobOrderLineItem().getLineNumber();
            long lineNum2 = form2.getCeJobOrderLineItem().getLineNumber();

            return (int)(lineNum1 - lineNum2);
        }
    }
}
