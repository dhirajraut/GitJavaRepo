/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.validators;

import java.util.HashSet;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.phoenix.web.controller.invoice.DepositInvoiceForm;
import com.intertek.phoenix.web.controller.job.CEBatchForm;
import com.intertek.phoenix.web.controller.job.CEScJobOrderLineItemForm;
import com.intertek.phoenix.web.controller.job.CESelectChargeForm;
import com.intertek.phoenix.web.controller.job.CEServiceLevelForm;
import com.intertek.util.Constants;

/**
 * CE select charges page validator
 * @author patni
 * 
 */
public class CESelectChargesValidator implements Validator {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class clazz) {
        return CESelectChargeForm.class.isAssignableFrom(clazz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object obj, Errors errors) {
        CESelectChargeForm scharge = (CESelectChargeForm) obj;

        //for Type1-2 projects
        if (!scharge.getIsType3Project()) {
            if (scharge.getRefreshing().equals("deleteProduct")) {
                int productIndex = scharge.getProductIndex();
                int joliSize = scharge.getProducts()[productIndex].getJoliForms().length;

                if (joliSize > 0) {
                    errors.reject("product.servicelevel.nonEmpty");
                    return;
                }
            }

            if (scharge.getRefreshing().equals(Constants.NEXT_ACTION)) {
                
                if(!isJoliSelectedForInvoicing(scharge) && !isDepInvSelectedForInvoicing(scharge)) {
                    errors.reject("no.line.selected.for.invoicing");
                }
                
                for (CEServiceLevelForm productSLForm : scharge.getProducts()) {
                    for (CEScJobOrderLineItemForm joliForm : productSLForm.getJoliForms()) {
                        // TODO: revisit once validations are finalized
                        /*
                         * if (joliForm.getAccruedAmount() <= 0) {
                         * errors.reject("accrued.amount.greater.error");
                         * return; }
                         */}
                }
            }

            if (scharge.getRefreshing().equals("sort")) {
                CEServiceLevelForm[] srvLvlFrm = scharge.getProducts();
                for (CEServiceLevelForm srvLvForm : srvLvlFrm) {
                    if (isDuplicateLineNumberPresent(srvLvForm.getJoliForms())) {
                        errors.reject("duplicate.line.number");
                    }
                }
            }
        }
        //for type-3 projects
        else {
            if (scharge.getRefreshing().equals("sort")) {
                CEBatchForm[] batchForms = scharge.getBatchForms();
                for (CEBatchForm batchForm : batchForms) {
                    if (isDuplicateLineNumberPresent(batchForm.getJoliForms())) {
                        errors.reject("duplicate.line.number");
                    }
                }
            }
        }

        if (scharge.getRefreshing().equals("sortDepositInvoices")) {
            if (isDuplicateSortNumberPresentForDepInv(scharge.getDepositInvoiceForms())) {
                errors.reject("duplicate.deposit.invoice.sort.number");
            }
        }
    }

    private boolean isJoliSelectedForInvoicing(CESelectChargeForm scForm) {
        for (CEServiceLevelForm productSLForm : scForm.getProducts()) {
            if(productSLForm.getSelectedJolis().size()>0) {
                return true;
            }
        }
        
        return false;
    }

    private boolean isDepInvSelectedForInvoicing(CESelectChargeForm scForm) {
        for (DepositInvoiceForm depForm: scForm.getDepositInvoiceForms()) {
            if(depForm.isSelected()) {
                return true;
            }
        }
        
        return false;
    }
    private boolean isDuplicateSortNumberPresentForDepInv(DepositInvoiceForm[] depInvForms) {
        Set<Long> sortNumbers = new HashSet<Long>();
        for (DepositInvoiceForm depForm : depInvForms) {
            sortNumbers.add(depForm.getSortNumber());
        }
        return (sortNumbers.size() != depInvForms.length);
    }

    private boolean isDuplicateLineNumberPresent(CEScJobOrderLineItemForm[] joliForms) {
        Set<Long> lineNumbers = new HashSet<Long>();
        for (CEScJobOrderLineItemForm joliForm : joliForms) {
            Set<Long> splitLineNumbers = new HashSet<Long>();
            lineNumbers.add(joliForm.getSortNumber());
            CEScJobOrderLineItemForm[] spForm = joliForm.getSplitItems();

            for (CEScJobOrderLineItemForm splitForm : spForm) {
                splitLineNumbers.add(splitForm.getSortNumber());
            }

            if (splitLineNumbers.size() != spForm.length) {
                return true;
            }
        }

        if (lineNumbers.size() != joliForms.length) {
            return true;
        }

        return false;
    }

}
