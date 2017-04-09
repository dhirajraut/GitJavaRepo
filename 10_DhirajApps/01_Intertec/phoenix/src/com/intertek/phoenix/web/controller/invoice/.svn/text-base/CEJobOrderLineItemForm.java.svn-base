/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.JobContractTest;

/**
 * Purpose: Form bean to represent CEJobOrderLineItem entity in context of CE
 * invoice preview page.
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class CEJobOrderLineItemForm extends Form {

    private CEJobOrderLineItem ceJobOrderLineItem;
    private RevenueSegregationForm[] revenueSegregationForms;
    private SplitLineItemForm[] splitLineItemForms;
    
    
    
    
	/**
     * Instantiates new CEJobOrderLineItemForm
     * 
     * @param ceJobOrderLineItem
     */
    public CEJobOrderLineItemForm(CEJobOrderLineItem ceJobOrderLineItem) {
        this.ceJobOrderLineItem = ceJobOrderLineItem;
    }

    	// TODO the actual logic yet to be implemented
    public double getBaseCurrencyAmount() {
        return ceJobOrderLineItem.getNetPrice();
    }

    // TODO the actual logic yet to be implemented
    public double getBillingCurrencyAmount() {
        return ceJobOrderLineItem.getNetPrice();
    }
    
    public String getJobOrderId() {
        return ceJobOrderLineItem.getJobContract().getJobOrderNumber();
    }

    public void setJobOrderId(String jobOrderId) {
        ceJobOrderLineItem.getJobContract().setJobOrderNumber(jobOrderId);
    }

    public long getLineNumber() {
        return ceJobOrderLineItem.getLineNumber();
    }

    public void setLineNumber(long lineNumber) {
        ceJobOrderLineItem.setLineNumber(lineNumber);
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

    public Date getTaskReadyDate() {
        // TODO: "many fields not belong to CEJobOrderLineItem, instead,
        // they belong to CEJobTest". Change it accordingly

        // return ceJobOrderLineItem.getTaskReadyDate();

        return null;

    }

    public void setTaskReadyDate(Timestamp taskReadyDate) {
        // TODO: "many fields not belong to CEJobOrderLineItem, instead,
        // they belong to CEJobTest". Change it accordingly

        // ceJobOrderLineItem.setTaskReadyDate(taskReadyDate);

    }

    public Double getCommittedAmount() {
        return ceJobOrderLineItem.getCommittedAmount();
    }

    public void setCommittedAmount(Double committedAmount) {
        ceJobOrderLineItem.setCommittedAmount(committedAmount);
    }

    public Double getAccruedAmount() {
        return ceJobOrderLineItem.getAccruedAmount();
    }

    public Double getDiscount() {
        return ceJobOrderLineItem.getDiscountPct();
    }

    public Double getExtendedPrice() {
        return ceJobOrderLineItem.getNetPrice();
    }

    public Double getRevenue() {
        return ceJobOrderLineItem.getBilledAmount();
    }

    public void setRevenue(Double revenue) {
        ceJobOrderLineItem.setBilledAmount(revenue);
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

    public void setPoNumber(String poNumber) {
        // TODO: "many fields not belong to CEJobOrderLineItem, instead,
        // they belong to CEJobTest". Change it accordingly

        // ceJobOrderLineItem.setPoNumber(poNumber);
    }

    public String getPoNumber() {
        // TODO: "many fields not belong to CEJobOrderLineItem, instead,
        // they belong to CEJobTest". Change it accordingly

        // return ceJobOrderLineItem.getPoNumber();

        return "";
    }

    public Double getPrice() {
        return ceJobOrderLineItem.getUnitPrice();
    }

    public void setPrice(Double price) {
        ceJobOrderLineItem.setUnitPrice(price);
    }

    public String getPrimaryBranch() {

        // TODO: "many fields not belong to CEJobOrderLineItem, instead,
        // they belong to CEJobTest". Change it accordingly

        // return ceJobOrderLineItem.getBranchName();

        return "";
    }

    public void setPrimaryBranch(String primaryBranch) {
        // TODO: "many fields not belong to CEJobOrderLineItem, instead,
        // they belong to CEJobTest". Change it accordingly

        // ceJobOrderLineItem.setBranchName(primaryBranch);
    }

    public String getServiceType() {
        // return ceJobOrderLineItem.getServiceType().description();
        return ""; // ServiceType entity is removed from the latest code
    }

    public String getBusStream() {
        return ceJobOrderLineItem.getJobContract().getJobOrder().getBusinessStreamCode();
    }

    public SplitLineItemForm[] getSplitLineItemForms() {
        return splitLineItemForms;
    }

    public void setSplitLineItemForms(SplitLineItemForm[] splitLineItemForms) {
        this.splitLineItemForms = splitLineItemForms;
        
    }
    
    public double getBillingAmount(){
        return ceJobOrderLineItem.getBillingAmount();
    }
	
    // /**
    // * Name : addSplitItem
    // * Purpose : Adds given SplitLineItemForm to the array of forms
    // * @param splitForm: form to be added
    // */
    // public void addSplitItem(SplitLineItem newSplitItem) {
    // SplitLineItemForm newSplitForm = new SplitLineItemForm(newSplitItem);
    // List<SplitLineItemForm> splitForms = new ArrayList<SplitLineItemForm>();
    // Collections.addAll(splitForms, splitLineItemForms);
    // splitForms.add(newSplitForm);
    // splitLineItemForms = splitForms.toArray(new
    // SplitLineItemForm[splitForms.size()]);
    // }
    //
    // /**
    // * Name : removeSplitForm
    // * Purpose : Removes given SplitLineItemForm from the array of forms
    // * @param splitForm: form to be removed
    // */
    // public void removeSplitForm(SplitLineItemForm splitForm) {
    // List<SplitLineItemForm> splitFormList = new
    // ArrayList<SplitLineItemForm>();
    // Collections.addAll(splitFormList, splitLineItemForms);
    // splitFormList.remove(splitForm);
    //        splitLineItemForms = splitFormList.toArray(new SplitLineItemForm[splitFormList.size()]);
    //    }
}
