/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.web.controller.invoice.ApplyDepositForm;
import com.intertek.phoenix.web.controller.invoice.DepositInvoiceForm;

/**
 * Purpose: To validate Invoice Preview - Apply.
 * 
 * @version 1.0 Jun 29, 2009
 * @author Patni
 */
public class CEInvoicePreviewApplyDepositInvoiceValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return ApplyDepositForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ApplyDepositForm applyDepform = (ApplyDepositForm) obj;
        DepositInvoiceForm[] depositForms = applyDepform.getDepositInvoiceForms();

        // 1. At least one deposit invoice should be selected
        boolean selected = false;

        for (DepositInvoiceForm depositForm : depositForms) {
            if (depositForm.isSelected()) {
                selected = true;                
                if (depositForm.getDepositInvoice().getStatus() != InvoiceStatus.INVOICED) {
                    errors.reject("ce.invoice.preview.no.deposit.invoice.error");
                    break;
                }
                if (!depositForm.getDepositInvoice().isDepositPaid()) {
                    errors.reject("ce.invoice.preview.no.deposit.paid.error");
                    break;
                }
                if (depositForm.getAmountToApply() == null) {
                    errors.reject("ce.invoice.preview.apply.no.amount.error");
                    break;
                }
            }
        }

        if (!selected) {
            errors.reject("ce.invoice.preview.apply.no.selection.error");
            return;
        }

        // 2. Amount to Apply should not be greater than Balance
        double amtToApply = 0.0;
        for (DepositInvoiceForm depositForm : depositForms) {
            if (depositForm.getAmountToApply() != null) {
                amtToApply += depositForm.getAmountToApply();
                if (depositForm.getAmountToApply() > depositForm.getAvailableAmount()) {
                    errors.reject("ce.invoice.preview.apply.availableamt.error");
                }
            }
        }

        double netPrice = applyDepform.getCeInvoiceLineItemForm().getNetPrice();

        // 3. Amount should not exceed the net price of master line item
        if (amtToApply > netPrice) {
            errors.reject("ce.invoice.preview.apply.netprice.error");
        }

    }
}
