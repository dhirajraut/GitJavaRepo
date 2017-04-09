/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.purchaseorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;

/**
 * Form to represent job order details section on 'edit PO' page
 * 
 * @author rautsmit
 * 
 */
public class POJobDetailForm {
    private String poNumber;
    private String custCode;
    private String custName;
    private String balanceAmt;
    private Double totalAmt;
    private CEJobContract jobContract;
    private List<POJoliForm> ceJobLineItemForms;
    private String jobNumber;

    /**
     * Instantiates POJobDetailForm
     * 
     * @param joco
     */
    public POJobDetailForm(CEJobContract joco) {
        this.jobContract = joco;
        this.jobNumber = joco.getJobOrderNumber();
        totalAmt = 0.0d;
        ceJobLineItemForms = new ArrayList<POJoliForm>();
        Set<CEJobOrderLineItem> lineItems = joco.getJobOrderLineItems();

        for (CEJobOrderLineItem lineItem : lineItems) {
            POJoliForm ceLineItemform = new POJoliForm(lineItem);
            ceJobLineItemForms.add(ceLineItemform);
            totalAmt = totalAmt + lineItem.getCommittedAmount();
        }
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public List<POJoliForm> getCeJobLineItemForms() {
        return ceJobLineItemForms;
    }

    public void setCeJobLineItemForms(List<POJoliForm> ceJobLineItemForms) {
        this.ceJobLineItemForms = ceJobLineItemForms;
    }

    public Double getTotalAmt() {
        return (totalAmt == null ? 0.0d : totalAmt);
    }

    public void setTotalAmt(Double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        this.balanceAmt = balanceAmt;
    }
}
