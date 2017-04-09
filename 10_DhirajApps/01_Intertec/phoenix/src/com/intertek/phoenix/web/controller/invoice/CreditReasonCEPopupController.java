/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.exception.ServiceException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.CreditInvoice;
import com.intertek.phoenix.search.SearchService;

// TODO: Auto-generated Javadoc
/**
 * The Class CreditReasonCEPopupController is used to load the credit reason
 * popup and shows credit reason details.
 * 
 * @version 1.0 May 22, 2009
 * @author Patni
 */
public class CreditReasonCEPopupController extends SimpleFormController {

    /**
     * Instantiates a new CreditReasonCEPopupController.
     */
    public CreditReasonCEPopupController() {
        super();
        setCommandClass(CEViewInvoiceForm.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return new ModelAndView("refresh-page");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    @SuppressWarnings("unchecked")
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String contractIndex = request.getParameter("contractIndex");
        String invoiceNumber = request.getParameter("invoiceId");
        String viewFlag = request.getParameter("viewFlag");
        HttpSession session = request.getSession();
        CEViewInvoiceForm ceViewInvoiceForm = (CEViewInvoiceForm) session
                .getAttribute("com.intertek.phoenix.web.controller.invoice.JobViewCEInvoiceFormController.FORM.command");
        if (ceViewInvoiceForm != null && invoiceNumber.contains("CR")) {
            try {
                List<CEInvoiceForm> invoiceFormList = ceViewInvoiceForm.getCeInvoiceFormList();
                for (CEInvoiceForm ceInvoiceForm : invoiceFormList) {
                    if (ceInvoiceForm.getCrInvoice() != null && ceInvoiceForm.getCrInvoice().getInvoiceNumber().equals(invoiceNumber)) {
                        SearchService searchService = ServiceManager.getSearchService();
                        Map<String, String> sMap = new HashMap<String, String>();
                        sMap.put("invoiceNumber", invoiceNumber);
                        List<CreditInvoice> crInvoiceList = (List<CreditInvoice>) searchService.search(CreditInvoice.class, sMap);
                        for (CreditInvoice crInv : crInvoiceList) {
                            if (crInv != null) {
                                ceViewInvoiceForm.setCreditReasonNote(crInv.getCreditReasonNote());
                                ceViewInvoiceForm.setCreditReasonUser(crInv.getCreditReasonUserName());
                            }
                        }
                    }
                }
            }
            catch (Exception e) {
                logger.info(e.getMessage());
                throw new ServiceException("Error while loading the page", new Object[] { e.getMessage() }, e);
            }
        }
        else {
            ceViewInvoiceForm = new CEViewInvoiceForm();
            ceViewInvoiceForm.setCreditReasonUser(ceViewInvoiceForm.getLoginName());
        }
        ceViewInvoiceForm.setIndex(Integer.parseInt(contractIndex));
        ceViewInvoiceForm.setReadWriteFlag(viewFlag);
        return ceViewInvoiceForm;
    }
}
