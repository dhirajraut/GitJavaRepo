/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.entity.Customer;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.web.controller.purchaseorder.PurchaseOrderForm;
import com.intertek.web.util.ValidationUtil;

/**
 * Purpose: To validate purchase order command object
 * 
 * @version 1.0 Apr 22, 2009
 * @author Patni
 */
public class PurchaseOrderValidator implements Validator {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class clazz) {
        return PurchaseOrderForm.class.isAssignableFrom(clazz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object obj, Errors errors) {
        PurchaseOrderForm addPO = (PurchaseOrderForm) obj;
        PurchaseOrder po = addPO.getPurchaseOrder();
        if (addPO.getStartDate() != null && !"".trim().equals(addPO.getStartDate())) {
            if (!CommonUtil.isValidDate(addPO.getStartDate())) {
                addPO.setBeginDate(null);
                errors.reject("invalid.begindate");
            }
        }

        if (addPO.getCompleteDate() != null && !"".trim().equals(addPO.getCompleteDate())) {
            if (!CommonUtil.isValidDate(addPO.getCompleteDate())) {
                addPO.setEndDate(null);
                errors.reject("invalid.endDate");
            }
        } 
        
        if (addPO.getExpectCompleteDate() != null && !"".trim().equals(addPO.getExpectCompleteDate())) {
            if (!CommonUtil.isValidDate(addPO.getExpectCompleteDate())) {
                addPO.setExpCompleteDate(null);
                errors.reject("invalid.expCompleteDate");
            }
        }  
        
        if ((po.getBeginDate() != null) && !("".trim().equals(po.getBeginDate())) && (po.getEndDate() != null) && !("".trim().equals(po.getEndDate()))) {
            if (ValidationUtil.validateDate(po.getBeginDate(), po.getEndDate(), errors)) {
                errors.reject("po.date.range.invalid");
            }
        }
        if (po.getCustCode() != null && !(("").equals(po.getCustCode()))) {
            JobOrderService jobSrvc = ServiceManager.getJobOrderService();
            try {
                Customer cust = jobSrvc.findById(Customer.class, po.getCustCode());
                if (cust == null || cust.getName() == null) {
                    errors.reject("invalid.customer.id");
                }
            }
            catch (Exception e) {
                errors.reject("invalid.customer.id", new Object[] { po.getCustCode() }, "");
            }
        }
        
    }
}
