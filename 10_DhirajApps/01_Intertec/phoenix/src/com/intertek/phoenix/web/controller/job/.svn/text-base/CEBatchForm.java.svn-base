/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.BillingEvent;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;

/**
 * Form to represent batch/ billing event for Type-3 projects
 * 
 * @author rautsmit
 * 
 */
public class CEBatchForm extends Form {

    private CEScJobOrderLineItemForm[] joliForms;
    private CEJobContract jobContract;
    private BillingEvent billingEvent;
    private boolean selected;

    public CEBatchForm() {
    }

    public CEBatchForm(BillingEvent billingEvent) {
        updateForm(billingEvent);
    }

    /**
     * Rebuilds form with passed in BillingEvent
     * 
     * @param jobContract
     */
    public void updateForm(BillingEvent billingEvent) {
        this.billingEvent = billingEvent;
        this.jobContract = billingEvent.getJobContract();
        Set<CEJobOrderLineItem> jolis = this.jobContract.getJobOrderLineItems(billingEvent);
        joliForms = new CEScJobOrderLineItemForm[jolis.size()];

        int joliCounter = 0;
        for (CEJobOrderLineItem joli : jolis) {
            CEScJobOrderLineItemForm joliForm = new CEScJobOrderLineItemForm(joli,null);
            joliForm.setSortNumber(joliForm.getLineNumber());
            joliForms[joliCounter++] = joliForm;
        }
        Arrays.sort(joliForms, new JoliComparator());
    }

    public CEScJobOrderLineItemForm[] getJoliForms() {
        return joliForms;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public BillingEvent getBillingEvent() {
        return billingEvent;
    }

    public String getBatchNumber() {
        return billingEvent.getPsInvoiceNumber();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Double getTotal() {
        Double total = 0.0;
        for (CEScJobOrderLineItemForm joliForms : this.joliForms) {
            total = total + joliForms.getExtendedPrice();
        }
        return total;
    }
    /**
     * To order/ sort JOLIs using line number
     * 
     * @author rautsmit
     * 
     */
    private class JoliComparator implements Comparator<CEScJobOrderLineItemForm> {
        @Override
        public int compare(CEScJobOrderLineItemForm form1, CEScJobOrderLineItemForm form2) {
            Long lineNum1 = form1.getCeJobOrderLineItem().getLineNumber();
            Long lineNum2 = form2.getCeJobOrderLineItem().getLineNumber();

            return lineNum1.compareTo(lineNum2);
        }
    }
}
