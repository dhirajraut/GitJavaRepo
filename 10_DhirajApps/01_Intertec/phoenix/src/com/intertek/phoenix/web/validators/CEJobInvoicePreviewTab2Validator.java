package com.intertek.phoenix.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.web.controller.invoice.CEInvoiceLineItemForm;
import com.intertek.phoenix.web.controller.invoice.CEJobInvoicePreviewForm;

public class CEJobInvoicePreviewTab2Validator implements Validator {

    public boolean supports(Class clazz) {

        return CEJobInvoicePreviewForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        CEJobInvoicePreviewForm lineItemForm = (CEJobInvoicePreviewForm) obj;
        CEInvoiceLineItemForm form[] = lineItemForm.getCeInvLineItemForms();
        int lineNo = 1;
        for (CEInvoiceLineItemForm invoiceLineItemForm : form) {

            if (!CommonUtil.isValidDate(invoiceLineItemForm.getTaxVatDate())) {
                errors.reject("invalid.taxVatDate", new Object[] { Integer.toString(lineNo) }, "");

            }
            if (!CommonUtil.isNullOrEmpty(invoiceLineItemForm.getVatCode())) {
                if (!CommonUtil.validateAlphaNum(invoiceLineItemForm.getVatCode())) {
                    errors.reject("invalid.vatCode", new Object[] { Integer.toString(lineNo) }, "");
                }
            }
            lineNo++;

        }

    }
}
