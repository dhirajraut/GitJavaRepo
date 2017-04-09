/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CreditInvoice;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.entity.InvoiceType;
import com.intertek.phoenix.invoice.InvoiceService;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;
import com.intertek.phoenix.web.controller.invoice.CEInvoiceForm.FormInvoice;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

/**
 * The class JobViewCEInvoiceFormController is used to load all the invoices and
 * create the credit invoice. It also loads all DepositInvoice for this
 * jobOrder/jobContract
 * 
 * @version 1.0 May 22, 2009
 * @author Patni
 * @author lily.sun
 */
public class JobViewCEInvoiceFormController extends BaseSimpleFormController {
    private static Log log = LogFactory.getLog(JobViewCEInvoiceFormController.class);

    /**
     * Instantiates a new JobViewCEInvoiceFormController.
     */
    public JobViewCEInvoiceFormController() {
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

        String errorMsg = null;
        try {
            CEViewInvoiceForm ceViewInvoiceForm = (CEViewInvoiceForm) command;
            InvoiceService invoiceService = ServiceManager.getInvoiceService();
            UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");

            String creditFlag = request.getParameter("creditFlag");
            int rowNum = ceViewInvoiceForm.getIndex();
            if (creditFlag != null && "credit".equals(creditFlag)) {
                errorMsg = "problem.credit.invoice";
                List<CEInvoiceForm> invoiceFormList = ceViewInvoiceForm.getCeInvoiceFormList();
                if (rowNum != -1) {
                    CEInvoiceForm ceInvoiceForm = (CEInvoiceForm) invoiceFormList.get(rowNum);
                    CEInvoice ceInvoice = ControllerUtil.loadAndUpdate(ceInvoiceForm.getCeInvoice(), CEInvoice.class);
                    // CreditInvoice crInvoice = new CreditInvoice();
                    ceInvoice.setType(InvoiceType.NORMAL);
                    ceInvoice.setStatus(InvoiceStatus.CREDITED);
                    User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
                    CreditInvoice crInvoice = invoiceService.creditInvoice(user, ceInvoice, ceViewInvoiceForm.getCreditReasonNote());
                    ceInvoiceForm.setCeInvoice(ceInvoice);
                    invoiceFormList.remove(rowNum);
                    invoiceFormList.add(ceInvoiceForm);
                    ceInvoiceForm = new CEInvoiceForm();
                    ceInvoiceForm.setCrInvoice(crInvoice);
                    ceInvoiceForm.setFormInvoiceType(FormInvoice.CreditInvoice);
                    invoiceFormList.add(ceInvoiceForm);
                }
                ceViewInvoiceForm.setCreditFlag("none");
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
            errors.reject(errorMsg == null ? "problem.general.page" : errorMsg, new Object[] { " try again" }, "");
            return showForm(request, response, errors);
        }
        return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#suppressValidation(javax.servlet.http.HttpServletRequest)
     */

    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        String refreshing = request.getParameter("refreshing");
        if ((refreshing != null) && "true".equals(refreshing)) {
            return true;
        }
        return super.suppressValidation(request, command);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        CEViewInvoiceForm ceViewInvoiceForm = new CEViewInvoiceForm();
        try {
            String jobNumber = request.getParameter("jobNumber");
            if (jobNumber == null) {
                throw new ServiceException("jobnumber.not.exists.error", new Object[] { jobNumber }, null);
            }
            else {
                CEInvoice ceInvoice = new CEInvoice();
                Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
                CEJobOrder jo = jobDao.find(jobNumber);

                // TODO review this
                if ((jo.getPageNumber() == null) || (jo.getPageNumber().intValue() < 5)) {
                    jo.setPageNumber(Integer.valueOf(5));
                }

                // TODO to preview invoice for multiple contracts, the
                // jobContractId
                // needs to be passed in as paramaeter
                // CE has only one customer per job, so this should be fine.
                ceInvoice.setJobContract(jo.getJobContract());
                ceViewInvoiceForm.setJobOrder(jo);
                ceViewInvoiceForm.setCeInvoice(ceInvoice);
                fetchCeViewInvoiceForm(ceInvoice.getJobContract().getId(), ceViewInvoiceForm);
            }
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw new ServiceException("Error while loading the page", new Object[] { e.getMessage() }, e);
        }

        return ceViewInvoiceForm;
    }

    /**
     * Name :fetchCeViewInvoiceForm Purpose :to fetch the list of invoices for
     * the given jobNumber by the search service method.
     * 
     * @param jobNumber
     *            the job number
     * @param ceViewInvoiceForm
     *            the ce view invoice form
     */

    @SuppressWarnings("unchecked")
    private void fetchCeViewInvoiceForm(Long jobOrderContractId, CEViewInvoiceForm ceViewInvoiceForm) throws Exception {
        JobOrderService jobOrderService = ServiceManager.getJobOrderService();
        InvoiceService invoiceService = ServiceManager.getInvoiceService();

        CEJobContract jobContract = jobOrderService.findById(CEJobContract.class, jobOrderContractId);
        List<CEInvoiceForm> invForms = ceViewInvoiceForm.getCeInvoiceFormList();
        Set<CEInvoice> ceInvoiceSet = jobContract.getCeInvoices();
        List<CEInvoice> ceInvoiceList = new ArrayList<CEInvoice>(ceInvoiceSet);

        for (CEInvoice ceInv : ceInvoiceList) {
            if (ceInv.getStatus() != InvoiceStatus.NEW) {
                CEInvoiceForm invForm = new CEInvoiceForm(ceInv);
                invForm.setFormInvoiceType(FormInvoice.CEInvoice);
                invForms.add(invForm);

                CreditInvoice crInvoice = invoiceService.getCreditInvoice(ceInv);
                if (crInvoice != null) {
                    invForm = new CEInvoiceForm();
                    invForm.setCrInvoice(crInvoice);
                    invForm.setFormInvoiceType(FormInvoice.CreditInvoice);
                    invForms.add(invForm);
                }
            }
        }
        Set<DepositInvoice> depositInvoices = jobContract.getDepositInvoices();
        for (DepositInvoice depositInvoice : depositInvoices) {
            if (depositInvoice.getStatus() != InvoiceStatus.NEW) {
                CEInvoiceForm invForm = new CEInvoiceForm();
                invForm.setDepInvoice(depositInvoice);
                invForm.setFormInvoiceType(FormInvoice.DepositInvoice);
                invForms.add(invForm);
            }
        }
        ceViewInvoiceForm.setCeInvoiceFormList(invForms);
    }
}
