/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.web.controller.invoice.RevenueSegregationForm;
import com.intertek.phoenix.web.controller.job.EditPricePopupForm;

/**
 * Edit price validator
 * 
 * @author patni
 * 
 */
public class EditPriceValidator implements Validator {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean supports(Class clazz) {
        return EditPricePopupForm.class.isAssignableFrom(clazz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object obj, Errors errors) {
        EditPricePopupForm editPriceForm = (EditPricePopupForm) obj;

        double totalAmount = 0.0;
        if (editPriceForm.getOperation().equals("updateRS")) {
            for (RevenueSegregationForm rsForm : editPriceForm.getRevenueSegregationForms()) {
                totalAmount += rsForm.getAmount();
            }
            CEJobOrderLineItem joli = editPriceForm.getJobOrderLineItem();
            if (totalAmount > joli.getUnbilledAmount() && joli.getTotalAmount() != 0) {
                errors.reject("rs.total.amount.greater.unbilledamount");
                editPriceForm.setOperation("");
                return;
            }
        }
    }

}
