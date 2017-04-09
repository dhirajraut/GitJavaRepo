/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.exception.ServiceException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.EnumField;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.Estimation;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.web.controller.ControllerUtil;

/**
 * The Class JobOperationalInstrCEFormController.
 * 
 * @author eric.nguyen/patni
 */
public class JobInstructionTestAttributePopupFormController extends SimpleFormController {

    private static Log log = LogFactory.getLog(JobInstructionTestAttributePopupFormController.class);

    /**
     * Instantiates a new job operational instruction Other Pop-up form
     * controller.
     */
    public JobInstructionTestAttributePopupFormController() {
        super();
        setSessionForm(true);
        setCommandClass(CEJobInstructionOthersPopupForm.class);
    }

    /**
     * On submit.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param command
     *            the command
     * @param errors
     *            the errors
     * 
     * @return the model and view
     * 
     * @throws Exception
     *             the exception
     */
    @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        String errorMsg = "";
        try {
            CEJobInstructionOthersPopupForm ceJobInstructionOthersPopupForm = (CEJobInstructionOthersPopupForm) command;
            String workerFlag = ceJobInstructionOthersPopupForm.getWorkertimeFlag();
            JobTest formJobTest = ceJobInstructionOthersPopupForm.getJobTest();
            setPurchaseOrderId(ceJobInstructionOthersPopupForm);
            JobTest jobTest = ControllerUtil.loadAndUpdate(formJobTest, JobTest.class);

            ControllerUtil.updateCollection(jobTest, formJobTest, JobTest.class, "estimations");

            if ((workerFlag != null)) {
                if ("add".equals(workerFlag)) {
                    errorMsg = "problem.add.Estimation";
                    doAddEstimation(jobTest, ceJobInstructionOthersPopupForm);
                }
                if ("delete".equals(workerFlag)) {
                    errorMsg = "problem.delete.Estimation";
                    doDeleteEstimation(jobTest, ceJobInstructionOthersPopupForm);
                }
                if (workerFlag != null && "save".equals(workerFlag)) {
                    errorMsg = "problem.save.JobTest";
                    doSave(jobTest, ceJobInstructionOthersPopupForm, request);
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            log.error(t.getMessage());
            errors.reject(errorMsg.equals("") ? "problem.general.page" : errorMsg);
        }
        return showForm(request, response, errors);
    }

    private void setPurchaseOrderId(CEJobInstructionOthersPopupForm ceJobInstructionOthersPopupForm) throws JobSrvcException{
        
        SearchService searchSrvc = ServiceManager.getSearchService();
        String poNumber =ceJobInstructionOthersPopupForm.getPurchaseOrderNo();
        if (poNumber != null && poNumber.trim().length()>0) {
            Map<String,String> criteria = new HashMap<String,String>();
            criteria.put("poNumber", poNumber);
            try{
            PurchaseOrder porder =searchSrvc.uniqueSearch(PurchaseOrder.class, criteria);
            JobTest formJobTest = ceJobInstructionOthersPopupForm.getJobTest();
            formJobTest.setPoId(porder.getId());
            formJobTest.setPurchaseOrder(porder);
            }catch(DaoException de){
                throw new JobSrvcException("Invalid Purchase Order Number " +poNumber);
            }
        }
    }

    private void doAddEstimation(JobTest jobTest, CEJobInstructionOthersPopupForm ceJobInstructionOthersPopupForm) throws Exception{
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        jobSrvc.createEstimation(jobTest);
        ceJobInstructionOthersPopupForm.setJobTest(jobTest);
    }
    
    private void doDeleteEstimation(JobTest jobTest, CEJobInstructionOthersPopupForm ceJobInstructionOthersPopupForm) throws Exception{
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        String estId = ceJobInstructionOthersPopupForm.getEstimationId();
        Estimation estimation = ControllerUtil.findById(Estimation.class, Long.valueOf(estId));
        jobSrvc.removeEstimation(jobTest, estimation);
        ceJobInstructionOthersPopupForm.setJobTest(jobTest);
    }

    private void doSave(JobTest jobTest, CEJobInstructionOthersPopupForm ceJobInstructionOthersPopupForm,HttpServletRequest request) throws Exception{
        String key = ceJobInstructionOthersPopupForm.getTestIndex();
        for (Estimation e : jobTest.getEstimations()) {
                ControllerUtil.loadAndUpdate(e, Estimation.class);
        }
        jobTest = ControllerUtil.findById(JobTest.class, jobTest.getId());
        HashMap<String, JobTest> testMap = new HashMap<String, JobTest>();
        testMap.put(key, jobTest);
        request.getSession().setAttribute("TestAttribute", testMap);
        ceJobInstructionOthersPopupForm.setWorkertimeFlag("close");
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        log.debug("inside init binder");
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#suppressValidation(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    protected boolean suppressValidation(HttpServletRequest request, Object command) {

        String workerFlag = request.getParameter("workertimeFlag");
        if ((workerFlag != null) && "add".equalsIgnoreCase(workerFlag) || "delete".equalsIgnoreCase(workerFlag)) {
            return true;
        }
        return super.suppressValidation(request, command);        
        // return super.suppressValidation(request);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    @SuppressWarnings("unchecked")
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        try {
            JobOrderService jobSrvc = ServiceManager.getJobOrderService();
            String testId = request.getParameter("testIndex");
            String jobNumber =request.getParameter("jobNumber");
            CEJobInstructionOthersPopupForm ceJobInstructionOthersPopupForm = new CEJobInstructionOthersPopupForm();
            JobTest jobTest = null;
            jobTest = jobSrvc.findById(JobTest.class,Long.valueOf(testId));
            CEJobOrder jobOrder = jobSrvc.findById(CEJobOrder.class, jobNumber);            
            String divName = request.getParameter("div1");
            String divNameBody = request.getParameter("div2");
            ceJobInstructionOthersPopupForm.setTestIndex(testId);
            ceJobInstructionOthersPopupForm.setJobNumber(jobNumber);
            ceJobInstructionOthersPopupForm.setDivName(divName);
            ceJobInstructionOthersPopupForm.setDivNameBody(divNameBody);            
            ceJobInstructionOthersPopupForm.setBuName(jobOrder.getBuName());
            if(jobTest.getPurchaseOrder()!=null){
                ceJobInstructionOthersPopupForm.setPurchaseOrderNo(jobTest.getPurchaseOrder().getPoNumber());
            }
            setDefaultValue(jobTest,jobOrder);            
            Dao<JobTest> dao=DaoManager.getDao(JobTest.class);
            jobTest = dao.merge(jobTest);
            ceJobInstructionOthersPopupForm.setJobTest(jobTest);
            setDropDownValues(ceJobInstructionOthersPopupForm);
            if (jobTest.getEstimations() == null || jobTest.getEstimations().size() == 0) {
                jobSrvc.createEstimation(jobTest); 
            }
            return ceJobInstructionOthersPopupForm;
        }
        catch (ServiceException e) {
            log.error("error in formBackingObject" + e);
            throw e;
        }
    }
   

    /**
     * Name :setDropDownValues Date :May 25, 2009 purpose :to set all the static
     * drop-down data from Reference Data service
     * 
     * @return
     */
    private void setDropDownValues(CEJobInstructionOthersPopupForm ceJobInstructionOthersPopupForm) {
        ReferenceDataService refService = ServiceManager.getReferenceDataService();
        ceJobInstructionOthersPopupForm.setOperationalStatusList(refService.getOperationalStatusFields());
        ceJobInstructionOthersPopupForm.setBillingStatusList(refService.getBillingStatusFields());
        ceJobInstructionOthersPopupForm.setUserTypeList(refService.getUserTypeFields());
        List<EnumField> serviceOfferingList = getServiceOfferingList(ceJobInstructionOthersPopupForm.getJobTest().getTestId());
        ceJobInstructionOthersPopupForm.setServiceOfferingList(serviceOfferingList);
    }
    
    /**
     * Name :getServiceOfferingList Date :July 23, 2009 
     * purpose :create the list of ServiceOffering associated with the testID
     * object from array
     * 
     * @param String test ID
     * @return List<Field
     */
    private List<EnumField> getServiceOfferingList(String testId) {
        JobOrderService jobService = ServiceManager.getJobOrderService();
        List<EnumField> serviceOfferingFieldList = null;
        try {
            System.out.println("Test ID to fetch Service Offering ::::"+testId);
            List<ServiceOffering> serviceOfferingList = jobService.getServiceOffering(testId);
            if (serviceOfferingList != null) {
                System.out.println("size of serviceOfferingList"+serviceOfferingList.size());
                serviceOfferingFieldList = new ArrayList<EnumField>();
                for (ServiceOffering so : serviceOfferingList) {
                    serviceOfferingFieldList.add(so);
                }
            }
        }
        catch (Exception e) {
            log.error("exception" + e);
        }
        return serviceOfferingFieldList;
    }
    
    private void setDefaultValue(JobTest jobTest, CEJobOrder jobOrder){
        if(jobTest.getBranchName()==null || jobTest.getBranchName().equals("")){
            jobTest.setBranchName(jobOrder.getBranchName());
        }
        
        if(jobTest.getServiceLocationCode()==null || jobTest.getServiceLocationCode().equals("")){
            jobTest.setServiceLocationCode(jobOrder.getServiceLocationCode());
        }
        if(jobTest.getServiceLocation()==null ){
            jobTest.setServiceLocation(jobOrder.getServiceLocation());
        }
        if(jobTest.getModelNumber()==null || jobTest.getModelNumber().equals("")){
            jobTest.setModelNumber(jobOrder.getModelNumber());
        }
        if(jobTest.getOperationalStatus()==null){
            jobTest.setOperationalStatus(jobOrder.getOperationalStatus());
        }
    }

}
