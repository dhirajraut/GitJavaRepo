/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.RevenueSegregation;

/**
 * Purpose: Form bean to represent RevenueSegregation entity in context of CE
 * invoice preview page. It corresponds to one revenue item in the revenue popup
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class RevenueSegregationForm extends Form {

    private RevenueSegregation revenueSegregation;

    /**
     * Instantiates new RevenueSegregationForm
     * 
     * @param revenueSegregation
     */
    public RevenueSegregationForm(RevenueSegregation revenueSegregation) {
        this.revenueSegregation = revenueSegregation;
    }

    public RevenueSegregation getRevenueSegregation() {
        return revenueSegregation;
    }

    public double getAmount() {
        return revenueSegregation.getAmount();
    }

    public void setAmount(double amount) {
        revenueSegregation.setAmount(amount);
    }

    public String getAccount() {
        return revenueSegregation.getAccount();
    }

    public void setAccount(String account) {
        revenueSegregation.setAccount(account);
    }

    public String getDeptId() {
        return revenueSegregation.getDeptId();
    }

    public void setDeptId(String deptId) {
        revenueSegregation.setDeptId(deptId);
    }

    public String getDescription() {
        return revenueSegregation.getDescription();
    }

    public void setDescription(String description) {
        revenueSegregation.setDescription(description);
    }
    
    public boolean isPrimary() {
        return revenueSegregation.getPrimary();
    }
    
}
