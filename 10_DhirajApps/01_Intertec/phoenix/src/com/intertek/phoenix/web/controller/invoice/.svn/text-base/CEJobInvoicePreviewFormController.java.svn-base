/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.exception.ServiceException;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.entity.InvoiceType;
import com.intertek.phoenix.entity.RevenueSegregation;
import com.intertek.phoenix.entity.TestJobOrderLineItem;
import com.intertek.phoenix.invoice.InvalidInvoiceOperationException;
import com.intertek.phoenix.invoice.InvoiceService;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.tax.PhxTaxUtil;
import com.intertek.phoenix.web.controller.ControllerUtil;
import com.intertek.util.Constants;
import com.intertek.web.controller.BaseSimpleFormController;

/**
 * Purpose: Controller for CE Invoice Preview page This is the main controller,
 * handles operations for all the tabs except for the popups
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class CEJobInvoicePreviewFormController extends BaseSimpleFormController {

    /**
     * Instantiates a new CEJobInvoicePreviewFormController.
     */
    public CEJobInvoicePreviewFormController() {
        super();
        setSessionForm(true);
        setCommandClass(CEJobInvoicePreviewForm.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
     *      .servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        CEJobInvoicePreviewForm invoicePreviewForm = (CEJobInvoicePreviewForm) command;
        InvoiceService invoiceService = ServiceManager.getInvoiceService();
        String refreshing = invoicePreviewForm.getRefreshing();
        String errorMsg = "";
        try {
            CEJobOrder ceJo = ControllerUtil.loadAndUpdate(invoicePreviewForm.getJobOrder(), CEJobOrder.class);
            
            // TODO review this
            if ((ceJo.getPageNumber() == null) || (ceJo.getPageNumber().intValue() < 4)) {
                ceJo.setPageNumber(Integer.valueOf(4));
            }
            if (refreshing.equals(Constants.GENERATE_INVOICE_ACTION)) {
                // Moved refreshing of CEInvoice down into each specific action
                // to isolate deposit invoice action
                errorMsg = "problem.generate.invoice";
                CEInvoice ceInvoice = ControllerUtil.loadAndUpdate(invoicePreviewForm.getDraftInvoice(), CEInvoice.class);
                // TODO: This is temporary adjustment. Revisit.
                // invoicePreviewForm.getDraftInvoice().setType(InvoiceType.NORMAL);//commented
                // just to make this page work
                ceInvoice.setType(InvoiceType.NORMAL);
                invoiceService.generateInvoice(ceInvoice);
                // remove the next line
                // invoicePreviewForm.getDraftInvoice().setStatus(InvoiceStatus.INVOICED);
                boolean flag = true;
                save(ceJo, flag, invoicePreviewForm);
                if (request.getSession().getAttribute("SELECTED_JOLI_ID_LIST") != null) {
                    request.getSession().removeAttribute("SELECTED_JOLI_ID_LIST");
                }
            }
            else if (refreshing.equals(Constants.PREVIEW_INVOICE_ACTION)) {
                // TODO: Replace this call with appropriate method from
                // InvoiceService once it's available
                // invoiceService.previewInvoice(invoicePreviewForm.getJobOrder());
                errorMsg = "problem.preview.invoice";
                return showForm(request, response, errors);
            }
            else if (refreshing.equals(Constants.GENERATE_DEPOSIT_INVOICE_ACTION)) {
                for (DepositInvoiceForm depositInvoiceForm : invoicePreviewForm.getDepositInvoiceForms()) {
                    // Invoice all deposit invoice lines selected from Select
                    // Charge page to preview page
                    // if (depositInvoiceForm.isSelected()) {
                    DepositInvoice depositInvoice = ControllerUtil.loadAndUpdate(depositInvoiceForm.getDepositInvoice(), DepositInvoice.class);
                    // invoiceService.generateDepositInvoice(depositInvoiceForm.getDepositInvoice());
                    // depositInvoice.setType(InvoiceType.NORMAL);
                    try {
                        invoiceService.generateDepositInvoice(depositInvoice);
                    }
                    catch (Exception e) {
                        errors.reject("ce.invoice.preview.deposit.invoice.generation.error", new Object[] { e.getMessage() }, null);
                        return showForm(request, response, errors);
                    }
                    depositInvoiceForm.setDepositInvoice(depositInvoice);
                    // Note: This is temporary adjustment for demo
                    // asinvoiceService.generateInvoice() is
                    // throwing an exception as as it used ReportServiceImpl
                    // which is not implemented fully
                    // depositInvoiceForm.getDepositInvoice().setStatus(InvoiceStatus.INVOICED);
                    // }
                }
                // After all deposit invoices are generated, update the form
                // flag for deposit invoices generated.
                invoicePreviewForm.setDepInvoiceGenerated(true);
                if (request.getSession().getAttribute("SELECTED_DI_ID_LIST") != null) {
                    request.getSession().getAttribute("SELECTED_DI_ID_LIST");
                }
                return nextPageModeAndView(invoicePreviewForm.getJobOrder().getJobNumber());
            }
            // else if (refreshing.equals(Constants.ADD_SPLIT_ITEM_ACTION)) {
            // addSplitLineItem(invoicePreviewForm);
            // return showForm(request, response, errors);
            // }
            else if (refreshing.equals(Constants.DEL_LINE_ITEM_ACTION)) {
                deleteLineItem(invoicePreviewForm);
                return showForm(request, response, errors);
            }
            // else if (refreshing.equals(Constants.DEL_SPLIT_ITEM_ACTION)) {
            // deleteSplitLineItem(invoicePreviewForm);
            // return showForm(request, response, errors);
            // }
            else if (refreshing.equals(Constants.SAVE_ACTION)) {
                // Moved refreshing of CEInvoice down into each specific action
                // to isolate deposit invoice action
                CEInvoice ceInvoice = ControllerUtil.loadAndUpdate(invoicePreviewForm.getDraftInvoice(), CEInvoice.class);
                // Service call to save the entity to go here
                boolean flag = false;
                for (CEInvoiceLineItem invoiceLineItem : invoicePreviewForm.getDraftInvoice().getInvoiceLineItems()) {
                    invoiceLineItem = PhxTaxUtil.calculateTaxAndVatCodes(ceJo, invoicePreviewForm, invoiceLineItem);
                }
                ControllerUtil.updateCollection(ceInvoice, invoicePreviewForm.getDraftInvoice(), CEInvoice.class, "invoiceLineItems");
                doUpdate(ceInvoice, invoicePreviewForm);
                invoicePreviewForm.setDraftInvoice(ceInvoice);
                save(ceJo, flag, invoicePreviewForm);
                return showForm(request, response, errors);
            }
            else if (refreshing.equals(Constants.NEXT_ACTION)) {
                // Moved refreshing of CEInvoice down into each specific action
                // to isolate deposit invoice action
                CEInvoice ceInvoice = ControllerUtil.loadAndUpdate(invoicePreviewForm.getDraftInvoice(), CEInvoice.class);
                // Service call to save the entity to go here
                // TODO: validate the condition on which user can move to view
                // invoice page. Condition is not yet known
                boolean flag = false;
                for (CEInvoiceLineItem invoiceLineItem : invoicePreviewForm.getDraftInvoice().getInvoiceLineItems()) {
                    invoiceLineItem = PhxTaxUtil.calculateTaxAndVatCodes(ceJo, invoicePreviewForm, invoiceLineItem);
                }
                ControllerUtil.updateCollection(ceInvoice, invoicePreviewForm.getDraftInvoice(), CEInvoice.class, "invoiceLineItems");
                doUpdate(ceInvoice, invoicePreviewForm);
                invoicePreviewForm.setDraftInvoice(ceInvoice);
                save(ceJo, flag, invoicePreviewForm);
            }
            else if (refreshing.equals(Constants.CALC)) {
                return showForm(request, response, errors);
            }
            return nextPageModeAndView(invoicePreviewForm.getJobOrder().getJobNumber());
        }
        catch (InvalidInvoiceOperationException e) {
            e.printStackTrace();
            errorMsg = (errorMsg.equals("") ? "invalid.invoice.operation" : errorMsg);
            errors.reject(errorMsg, new Object[] { e.getMessage() }, null);
            return showForm(request, response, errors);
        }
        catch (Exception e) {
            e.printStackTrace();
            errorMsg = (errorMsg.equals("") ? "problem.general.page" : errorMsg);
            errors.reject(errorMsg, new Object[] { e.getMessage() }, null);
            return showForm(request, response, errors);
        }

    }

    private ModelAndView nextPageModeAndView(String jobNumber) {
        ModelAndView modelAndView = new ModelAndView("job-view-ce-invoice-r");
        modelAndView.addObject("jobNumber", jobNumber);
        return modelAndView;
    }

    // /**
    // * Name :addSplitLineItem Purpose : process add split line item action
    // *
    // * @param invoicePreviewForm
    // * the invoice preview form
    // */
    // private void addSplitLineItem(CEJobInvoicePreviewForm invoicePreviewForm)
    // {
    // int index = invoicePreviewForm.getInvLineItemIndex();
    // CEJobOrderLineItemForm joliForm =
    // invoicePreviewForm.getCeInvLineItemForms()[index].getJoLineItemForm();
    // CEJobOrderLineItem joli = joliForm.getCeJobOrderLineItem();
    // SplitLineItem newSplit = joli.split();
    // joliForm.addSplitItem(newSplit); // update Forms
    // }
    //
    // /**
    // * Name :deleteSplitLineItem Purpose : process delete split line item
    // action
    // *
    // * @param invoicePreviewForm
    // * the invoice preview form
    // */
    // private void deleteSplitLineItem(CEJobInvoicePreviewForm
    // invoicePreviewForm) {
    // int iliIndex = invoicePreviewForm.getInvLineItemIndex();
    // int splitIndex = invoicePreviewForm.getSplitLineItemIndex();
    // CEJobOrderLineItemForm joliForm =
    // invoicePreviewForm.getCeInvLineItemForms()[iliIndex].getJoLineItemForm();
    // CEJobOrderLineItem joli = joliForm.getCeJobOrderLineItem();
    // SplitLineItemForm splitForm =
    // joliForm.getSplitLineItemForms()[splitIndex];
    // joli.removeSplit(splitForm.getSplitItem());
    // joliForm.removeSplitForm(splitForm); // update Forms
    // }
    //
    /**
     * Name :deleteLineItem Purpose : process delete line item action
     * 
     * @param invoicePreviewForm
     *            the invoice preview form
     */
    private void deleteLineItem(CEJobInvoicePreviewForm invoicePreviewForm) {
        int index = invoicePreviewForm.getInvLineItemIndex();
        CEInvoiceLineItem iliToBeDeleted = invoicePreviewForm.getCeInvLineItemForms()[index].getCeInvoiceLineItem();
        invoicePreviewForm.getDraftInvoice().removeInvoiceLineItems(iliToBeDeleted);
        invoicePreviewForm.removeInvoiceLineItemForm(invoicePreviewForm.getCeInvLineItemForms()[index]);
    }

    private void save(CEJobOrder jo, boolean flag, CEJobInvoicePreviewForm invoicePreviewForm) {
        if (jo.getPageNumber().intValue() < 4 && !flag) {
            jo.setPageNumber(Integer.valueOf(4));
        }
        else if (jo.getPageNumber().intValue() < 5 && flag) {
            jo.setPageNumber(Integer.valueOf(5));
        }
    }

    public void doUpdate(CEInvoice ceInvoice, CEJobInvoicePreviewForm invoicePreviewForm) {
        for (CEInvoiceLineItem newLineItem : ceInvoice.getInvoiceLineItems()) {
            for (CEInvoiceLineItemForm ceInvForm : invoicePreviewForm.getCeInvLineItemForms()) {
                if (ceInvForm.getCeInvoiceLineItem().getId() == newLineItem.getId().longValue()) {
                    ceInvForm.setCEInvoiceLineItem(newLineItem);
                    break;
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     *      (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        try {
            InvoiceService invoiceService = ServiceManager.getInvoiceService();
            JobOrderService jobOrderService = ServiceManager.getJobOrderService();
            String jobNumber = request.getParameter("jobNumber");
            /*
             * Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
             * jobDao.find(jobNumber);
             */
            CEJobOrder jo = ControllerUtil.findById(CEJobOrder.class, jobNumber);

            if (jo == null) {
                throw new ServiceException("record.not.exist.error", new Object[] { jobNumber }, null);
            }

            // check project type, for type 1 and 2, preview the current
            // job contract, for type 3, use billing event id to get JOLIs

            CEInvoice draftInvoice = null;

            Set<Long> billables = (HashSet<Long>) request.getSession().getAttribute("SELECTED_JOLI_ID_LIST");
            List<CEJobOrderLineItem> joliIds = new ArrayList<CEJobOrderLineItem>();
            for (Long joliId : billables) {
                joliIds.add(jobOrderService.findById(TestJobOrderLineItem.class, joliId));
            }

            Set<Long> diIds = (HashSet<Long>) request.getSession().getAttribute("SELECTED_DI_ID_LIST");
            List<DepositInvoice> depInvs = new ArrayList<DepositInvoice>();
            for (Long diId : diIds) {
                depInvs.add(jobOrderService.findById(DepositInvoice.class, diId));
            }

            // TODO the jobOrder has a set of jobContracts. Right it preview
            // invoice for the first jobContract.
            draftInvoice = invoiceService.previewInvoice(jo.getJobContract(), joliIds);
            // }

            CEJobInvoicePreviewForm ceInvoicePreviewForm = new CEJobInvoicePreviewForm(jo);
            if (ceInvoicePreviewForm.getTabSource() == null || "".equals(ceInvoicePreviewForm.getTabSource())) {
                ceInvoicePreviewForm.setTabSource(Constants.CE_JOB_INV_PREVIEW_TAB0);
            }
            if (draftInvoice != null) {
                ceInvoicePreviewForm = createForm(jo, ceInvoicePreviewForm, draftInvoice);
                ceInvoicePreviewForm.setDraftInvoice(draftInvoice);
            }

            if (depInvs != null && depInvs.size() > 0) {
                populateDepositInvoiceForms(ceInvoicePreviewForm, depInvs);
            }

            ceInvoicePreviewForm.setJobOrder(jo);
            return ceInvoicePreviewForm;
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw new PhoenixException("Error while loading the page " + e.getMessage());            
        }
       
        
        
    }

    private void populateDepositInvoiceForms(CEJobInvoicePreviewForm ceInvoicePreviewForm, List<DepositInvoice> depInv) throws DaoException {
        ReferenceDataService refDataService = ServiceManager.getReferenceDataService();
        DepositInvoiceForm[] depositInvForms = new DepositInvoiceForm[depInv.size()];
        int counter = 0;
        for (DepositInvoice depInvoice : depInv) {
            // make depInvoice managed object
            depInvoice = ControllerUtil.loadAndUpdate(depInvoice, DepositInvoice.class);
            DepositInvoiceForm depInvoiceForm = new DepositInvoiceForm(depInvoice);
            depositInvForms[counter++] = depInvoiceForm;
            depInvoiceForm.setInvoiceStatus(refDataService.getInvoiceStatusFields());
            depInvoiceForm.setPaymentTypes(refDataService.getPaymentTypeFields());
            // if any depInvoice has been generated, disable the invoice
            // generation button
            if (InvoiceStatus.INVOICED == depInvoice.getStatus()) {
                ceInvoicePreviewForm.setDepInvoiceGenerated(true);
            }
        }
        Arrays.sort(depositInvForms, new DepositInvoiceComparator());
        ceInvoicePreviewForm.setDepositInvoiceForms(depositInvForms);
        ceInvoicePreviewForm.setInvGenerationTypes(refDataService.getInvoiceGenerationTypeFields());
    }

    /**
     * Name :createForm Purpose : To create form out of entity
     * 
     * @param ceJO
     *            the ce job order
     * @param ceInvoicePreviewForm
     * @param draftInvoice
     *            the draft invoice
     * @param depInv
     * 
     * @return the CEJobInvoicePreviewForm
     */
    private CEJobInvoicePreviewForm createForm(CEJobOrder ceJO, CEJobInvoicePreviewForm ceInvoicePreviewForm, CEInvoice draftInvoice) {
        ReferenceDataService refDataService = ServiceManager.getReferenceDataService();
        Set<CEInvoiceLineItem> invLineItems = draftInvoice.getInvoiceLineItems();

        CEInvoiceLineItemForm[] invLineItemForms = new CEInvoiceLineItemForm[invLineItems.size()];
        int counter = 0;
        for (CEInvoiceLineItem invLineItem : invLineItems) {

            CEInvoiceLineItemForm invLineItemForm = new CEInvoiceLineItemForm(invLineItem);
            CEJobOrderLineItem joLineItem = invLineItem.getCEJobOrderLineItem();

            if (joLineItem == null) {
                joLineItem = new CEJobOrderLineItem();
            }

            CEJobOrderLineItemForm joLineItemForm = new CEJobOrderLineItemForm(joLineItem);
            int rsCounter = 0;
            RevenueSegregationForm[] revenueForms = new RevenueSegregationForm[joLineItem.getRevenueSegregations().size()];
            for (RevenueSegregation revenue : joLineItem.getRevenueSegregations()) {
                RevenueSegregationForm revenueForm = new RevenueSegregationForm(revenue);
                revenueForms[rsCounter++] = revenueForm;
            }
            joLineItemForm.setRevenueSegregationForms(revenueForms);

            // Set<SplitLineItem> splitItems = joLineItem.getSplits();
            // SplitLineItemForm[] splitItemForms = new
            // SplitLineItemForm[splitItems.size()];
            // int splitCounter = 0;
            // for (SplitLineItem split : splitItems) {
            // SplitLineItemForm splitForm = new SplitLineItemForm(split);
            // splitItemForms[splitCounter++] = splitForm;
            // }
            // joLineItemForm.setSplitLineItemForms(splitItemForms);

            invLineItem = PhxTaxUtil.getTaxandVatCode(ceJO, ceInvoicePreviewForm, invLineItem);

            invLineItemForm.setJoLineItemForm(joLineItemForm);
            invLineItemForms[counter++] = invLineItemForm;

            invLineItemForm.setInvoiceStatus(refDataService.getInvoiceStatusFields());
        }

        Arrays.sort(invLineItemForms, new IliComparator());
        ceInvoicePreviewForm.setCeInvLineItemForms(invLineItemForms);

        return ceInvoicePreviewForm;
    }

    /**
     * Comparator to order the invoice line items on the JSP
     * 
     * @author Patni
     * 
     */
    private class IliComparator implements Comparator<CEInvoiceLineItemForm> {
        @Override
        public int compare(CEInvoiceLineItemForm form1, CEInvoiceLineItemForm form2) {
            Long lineNum1 = form1.getCeInvoiceLineItem().getCEJobOrderLineItem().getLineNumber();
            Long lineNum2 = form2.getCeInvoiceLineItem().getCEJobOrderLineItem().getLineNumber();

            return lineNum1.compareTo(lineNum2);
        }
    }

    /**
     * Comparator to arrange deposit invoice on the JSP
     * 
     * @author Patni
     * 
     */
    private class DepositInvoiceComparator implements Comparator<DepositInvoiceForm> {
        @Override
        public int compare(DepositInvoiceForm form1, DepositInvoiceForm form2) {
            Long id1 = form1.getDepositInvoice().getId();
            Long id2 = form2.getDepositInvoice().getId();

            return id1.compareTo(id2);
        }
    }
}
