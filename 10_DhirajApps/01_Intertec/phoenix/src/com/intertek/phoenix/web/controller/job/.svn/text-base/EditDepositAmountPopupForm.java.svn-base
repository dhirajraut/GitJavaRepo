/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import com.intertek.phoenix.entity.DepositInvoice;

/**
 * Edit Deposit Amount form for Select Charges functionality
 * 
 * @author rautsmit
 * 
 */
public class EditDepositAmountPopupForm {

    private DepositInvoice depositInvoice;
    private String operation;

    public EditDepositAmountPopupForm(DepositInvoice depInv) {
        this.depositInvoice = depInv;
    }

    public DepositInvoice getDepositInvoice() {
        return depositInvoice;
    }

    public double getDepositAmount() {
        return depositInvoice.getDepositAmount();
    }

    public void setDepositAmount(double amount) {
        depositInvoice.setDepositAmount(amount);
    }

    public void setDepositInvoice(DepositInvoice depositInvoice) {
        this.depositInvoice = depositInvoice;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDescription() {
        //return depositInvoice.getDescription();

        //TODO: desc is not coming from JI page...
        return "Deposit Invoice Line Item";
    }
    
    public String getReference(){
        return depositInvoice.getDepositReference();
    }
    
}
