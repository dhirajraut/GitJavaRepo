/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.purchaseorder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Customer;
import com.intertek.entity.JobOrder;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.service.PONotFoundException;
import com.intertek.phoenix.service.PurchaseOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;
import com.intertek.service.CustomerService;
import com.intertek.service.JobService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

/**
 * Purpose: Controller class responsible for creating purchase order.
 * 
 * @version 1.0 Apr 15, 2009
 * @author Patni
 */
public class PurchaseOrderFormController extends BaseSimpleFormController {

    /**
     * * Constructor Instantiates object of PurchaseOrderFormController.
     */
    public PurchaseOrderFormController() {
        super();
        setSessionForm(true);
        setCommandClass(PurchaseOrderForm.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        try {
            PurchaseOrderForm poForm = (PurchaseOrderForm) command;
            PurchaseOrder po = poForm.getPurchaseOrder();

            PurchaseOrderService poService = ServiceManager.getPOService();
            String sortPODFlag = request.getParameter("sortPODFlag");
            // sort purchase order details with on given field
            if (sortPODFlag != null && sortPODFlag.length() > 0) {
                sortPODRecords(poForm, poService);
                return showForm(request, response, errors);
            }
            // Auto populate customer name depending on the cust ID chosen
            String custFlag = request.getParameter("custFlag");
            if ((custFlag != null) && "custval".equals(custFlag)) {
                populateCustomerName(poForm);
                return showForm(request, response, errors);
            }
            // redirect to edit job page
            String jobNumber = request.getParameter("editJobNumber");
            if ((jobNumber != null) && jobNumber.length() > 0) {
                ModelAndView editJob = getEditJobModelView(poForm);
                return editJob;
            }
            // save the purchase order to the DB
            po.setCustomer(ControllerUtil.loadAndUpdate(po.getCustomer(), Customer.class));
            PurchaseOrder savedPO = poService.savePurchaseOrder(po);
            ModelAndView modelAndView = new ModelAndView(Constants.COMMON_MESSAGE_R);
            modelAndView.addObject("backUrl", "phx_create_purchase_order.htm?edit=true&po.poNumber=" + savedPO.getPoNumber());
            modelAndView.addObject("backUrlDescription", "You can continue to edit this purchase order.");
            modelAndView.addObject("description", "Purchase order has been created/ updated successfully.");
            return modelAndView;
        }
        catch (ServiceException e) {
            e.printStackTrace();
            errors.reject(e.getMessage(), e.getParams(), null);
            return showForm(request, response, errors);
        }
        catch (Throwable t) {
            t.printStackTrace();
            errors.reject("generic.error", new Object[] { t.getMessage() }, null);
            return showForm(request, response, errors);
        }
    }

    /**
     * Gets the EditJobModelView.
     * 
     * @param poForm
     *            the PO Form
     * 
     * @return the EditJobModelView
     */
    private ModelAndView getEditJobModelView(PurchaseOrderForm poForm) {
        String jobNumber = poForm.getEditJobNumber();

        JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
        JobOrder jobOrder = jobService.getJobOrderByJobNumber(jobNumber);

        Map<String, String> editJobUrlMap = new HashMap<String, String>();
        Map<String, String> viewJobUrlMap = new HashMap<String, String>();

        editJobUrlMap.put(Constants.INSPECTION_JOBTYPE, "edit_job_entry_insp.htm");
        editJobUrlMap.put(Constants.MARINE_JOBTYPE, "edit_job_entry_marine.htm");
        editJobUrlMap.put(Constants.ANALYTICAL_SERVICE_JOBTYPE, "edit_job_entry_analytical_service.htm");
        editJobUrlMap.put(Constants.OUTSOURCING_JOBTYPE, "edit_job_entry_outsourcing.htm");

        viewJobUrlMap.put(Constants.INSPECTION_JOBTYPE, "view_job_entry_insp.htm");
        viewJobUrlMap.put(Constants.MARINE_JOBTYPE, "view_job_entry_marine.htm");
        viewJobUrlMap.put(Constants.ANALYTICAL_SERVICE_JOBTYPE, "view_job_entry_analytical_service.htm");
        viewJobUrlMap.put(Constants.OUTSOURCING_JOBTYPE, "view_job_entry_outsourcing.htm");

        calculateBalanceAmount(poForm);
        
        
        if (jobOrder != null) {
            if (jobOrder.getJobStatus().equals(Constants.OPEN_STATUS) || jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS)) {
                return new ModelAndView(new RedirectView(editJobUrlMap.get(jobOrder.getJobType())), "jobNumber", jobNumber);
            }
            else {
                return new ModelAndView(new RedirectView(viewJobUrlMap.get(jobOrder.getJobType())), "jobNumber", jobNumber);
            }
        }

        throw new ServiceException("job.not.found.error", new Object[] { jobNumber });
    }

    @SuppressWarnings("unchecked")
	private void calculateBalanceAmount(PurchaseOrderForm poForm){
        PurchaseOrder po = poForm.getPurchaseOrder();
        SearchService sserv = ServiceManager.getSearchService();
        List<Double> list = sserv.getSumOfFundedAmount(po);
        if(list !=null && list.size()>0){
            double balanceValue = list.get(0)!=null?list.get(0).doubleValue():0.0;
            poForm.setBalanceCommitted(po.getMaxAmount()-balanceValue);
        }
        else{
            poForm.setBalanceCommitted(po.getMaxAmount());
        }
    }
    /**
     * Name :populateCustomerName Purpose : populates customer name field name
     * in PO according to the ID passed
     * 
     * @param poForm
     *            the PO Form
     */
    private void populateCustomerName(PurchaseOrderForm poForm) {
        CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
        Customer customer = customerService.getCustomerByCustCode(poForm.getPurchaseOrder().getCustCode());
        poForm.setCustFlag("none");
        poForm.setCustName(customer.getName());
        poForm.getPurchaseOrder().setCustomer(customer);
    }

    /**
     * Name :sortPODRecords Purpose : sorts purchase order details records
     * 
     * @param poForm
     *            the PO Form
     * @param poService
     *            the po service
     */
    private void sortPODRecords(PurchaseOrderForm poForm, PurchaseOrderService poService) throws PONotFoundException {
        PurchaseOrder po = poForm.getPurchaseOrder();
        List<CEJobOrderLineItem> lineItemsList = poService.loadJobOrderLineItems(po.getPoNumber(), poForm.getSortPODFlag());
        Set<CEJobOrderLineItem> lineItems = new LinkedHashSet<CEJobOrderLineItem>(lineItemsList);
        po.setJobOrderLineItems(lineItems);
        poForm.setSortPODFlag("");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
     * javax.servlet.http.HttpServletRequest,
     * org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#suppressValidation(javax.servlet.http.HttpServletRequest)
     */
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        String custFlag = request.getParameter("custFlag");
        if ((custFlag != null) && ("custval".equals(custFlag))) {
            return true;
        }
        return super.suppressValidation(request, command);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     * (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        PurchaseOrderForm poForm = null;
        String edit = request.getParameter("edit");

        if (edit != null && edit.equals("true")) {
            poForm = initEdit(request);
        }
        else {
            poForm = initAdd(request);
        }

        return poForm;
    }

    /**
     * Name :initAdd Purpose :Initialise data for creating new purchase order.
     * 
     * @param request
     *            the request
     * 
     * @return the AddPurchaseOrder
     */
    private PurchaseOrderForm initAdd(HttpServletRequest request) {
        PurchaseOrder po = new PurchaseOrder();
        BusinessUnit bu = new BusinessUnit();
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        User user = userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName());
        String buName = user.getBuName();
        if (!"POST".equals(request.getMethod())) {
            bu.setName(buName);
            po.setBuName(buName);
            po.setBranchCode(user.getBranchName());
            po.setBranch(user.getBranch());
            po.setBusinessUnit(user.getBusinessUnit());
        }

        PurchaseOrderForm poForm = new PurchaseOrderForm(po);
        return poForm;
    }

    /**
     * Name :initEdit Purpose :Initialise data for editing purchase order.
     * 
     * @param request
     *            the request
     * 
     * @return the AddPurchaseOrder
     */
    private PurchaseOrderForm initEdit(HttpServletRequest request) throws PONotFoundException, DaoException {

        String edit = request.getParameter("edit");
        String poNumber = request.getParameter("po.poNumber");
        PurchaseOrderService poService = ServiceManager.getPOService();
        PurchaseOrder po = poService.loadPOByPONumber(poNumber);

        Dao<CEJobContract> dao = DaoManager.getDao(CEJobContract.class);
        List<CEJobContract> jocList = new ArrayList<CEJobContract>();

        QueryInfo query = new QueryInfo(CEJobContract.class);
        query.addFilter("poNumber", po.getPoNumber());
        jocList = dao.search(query);
        PurchaseOrderForm poForm = new PurchaseOrderForm(po, jocList);
        calculateBalanceAmount(poForm);
        poForm.setEdit(edit);
        poForm.setDisableEdit(true);
        poForm.setPurchaseOrder(po);
        poForm.setCustName(po.getCustomer().getName());

        return poForm;
    }

}
