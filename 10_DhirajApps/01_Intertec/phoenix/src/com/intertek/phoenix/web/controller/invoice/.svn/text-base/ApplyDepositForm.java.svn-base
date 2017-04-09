/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import com.intertek.phoenix.common.Form;

/**
 * Purpose: Form bean to represent 'Apply Deposit' popup on CE invoice preview
 * page.
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class ApplyDepositForm extends Form {
    DepositInvoiceForm[] depositInvoiceForms;
    CEInvoiceLineItemForm ceInvoiceLineItemForm;

    /**
     * INstantiates new ApplyDepositForm
     * 
     * @param depositInvForms
     * @param ceInvLineItemForm
     */
    public ApplyDepositForm(DepositInvoiceForm[] depositInvForms, CEInvoiceLineItemForm ceInvLineItemForm) {
        this.depositInvoiceForms = depositInvForms;
        this.ceInvoiceLineItemForm = ceInvLineItemForm;
    }

    public DepositInvoiceForm[] getDepositInvoiceForms() {
        return depositInvoiceForms;
    }

    public void setDepositInvoiceForms(DepositInvoiceForm[] depositInvoiceForms) {
        this.depositInvoiceForms = depositInvoiceForms;
    }

    public CEInvoiceLineItemForm getCeInvoiceLineItemForm() {
        return ceInvoiceLineItemForm;
    }

    public void setCeInvoiceLineItemForm(CEInvoiceLineItemForm ceInvoiceLineItemForm) {
        this.ceInvoiceLineItemForm = ceInvoiceLineItemForm;
    }

    /**
     * Resets the form fields which take user input
     */
    public void reset() {
        for (DepositInvoiceForm depInvForm : depositInvoiceForms) {
            depInvForm.setAmountToApply(null);
            depInvForm.setSelected(false);
        }
    }
}
