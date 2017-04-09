/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.exception.ServiceException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.entity.Project;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.esb.ESBService;
import com.intertek.phoenix.job.InvalidJobOrderOperationException;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.job.ServiceLevel.ServiceLevelType;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.web.controller.ControllerUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class JobOperationalInstrCEFormController.
 * 
 * @author eric.nguyen
 * @author patni
 * @author richard.qin
 */
public class JobOperationalInstrCEFormController extends SimpleFormController {

    /** The log. */
    private static Log log = LogFactory.getLog(JobOperationalInstrCEFormController.class);

    /**
     * Instantiates a new job operational instr ce form controller.
     */
    public JobOperationalInstrCEFormController() {
        super();
        setCommandClass(CEJobOrderForm.class);
        setSessionForm(true);
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

        log.debug("inside on Submit in JobOperationalInstrCEFormController");
        String errorMsg = "";
        CEJobOrderForm jobOrderForm = (CEJobOrderForm) command;
        int testIndex = jobOrderForm.getTestIndex();
        String addOrDeleteTestLine = request.getParameter("addOrDeleteTestLines");
        String addOrDeleteDepositLine = request.getParameter("addOrDeleteDepositLines");
        CEJobOrder jobOrder =null;
        try {
            errorMsg="problem.Manage.JobOrder";
            jobOrder = getManagedJobOrder(jobOrderForm);
            if (!CommonUtil.isNullOrEmpty(addOrDeleteTestLine) && !addOrDeleteTestLine.equals("none")) {
                if ("addProduct".equals(addOrDeleteTestLine)) {
                    errorMsg="problem.add.Product";
                    doAddProductServiceLevel(jobOrder, jobOrderForm);
                }
                else if ("deleteProduct".equals(addOrDeleteTestLine)) {
                    errorMsg="problem.delete.Product";
                    doDeleteProductServiceLevel(jobOrderForm, jobOrder);
                }
                else if ("addTest".equals(addOrDeleteTestLine)) {
                    errorMsg="problem.add.JobTest";
                    CETestSearchForm addTestForm = (CETestSearchForm) request.getSession()
                        .getAttribute(AddTestController.class.getName() + ".FORM.command");
                    doAddTest(jobOrderForm, addTestForm);
                }
                else if ("deleteTest".equals(addOrDeleteTestLine)) {
                    errorMsg="problem.delete.JobTest";
                    doDeleteTest(testIndex);
                }
                else if ("addspl".equals(addOrDeleteTestLine)) {
                    errorMsg="problem.add.SplitTest";
                    CETestSearchForm addTestForm = (CETestSearchForm) request.getSession()
                        .getAttribute(AddTestController.class.getName() + ".FORM.command");
                    doAddSplitLine(jobOrderForm, jobOrder, addTestForm);
                }
                else if ("delspl".equals(addOrDeleteTestLine)) {                    
                    errorMsg="problem.delete.SplitTest";
                    int splitIndex = jobOrderForm.getSplitIndex();
                    doDeleteTest(splitIndex);
                }
                else if ("saveother".equals(addOrDeleteTestLine)) {
                    errorMsg="problem.save.JobTest";
                    HttpSession session = request.getSession();
                    HashMap<String, JobTest> testMap = (HashMap<String, JobTest>) session.getAttribute("TestAttribute");
                    doSaveOthers(jobOrderForm, jobOrder, testMap);
                }
                else if (addOrDeleteTestLine != null) {
                    if ("save".equals(addOrDeleteTestLine) || "saveandnext".equals(addOrDeleteTestLine)) {
                        doSave(jobOrderForm, jobOrder);
                        validateJobOrder(jobOrder);
                        errorMsg="problem.save.JI";
                        if("saveandnext".equals(addOrDeleteTestLine)){
                            return new ModelAndView(new RedirectView("phx_ce_job_select_charges.htm?jobNumber=" + jobOrderForm.getJobNumber()));
                        }
                    }
                }
                
                if(!"saveotherbefore".equals(jobOrderForm.getAddOrDeleteTestLines())){
                    jobOrderForm.setAddOrDeleteTestLines("none");
                }
                log.warn("after calling updateJobOrder");
            }
            if (addOrDeleteDepositLine != null) {
                if ("adddep".equals(addOrDeleteDepositLine)) {
                    errorMsg="problem.add.DepositInvoice";
                    addDepositInvoice(jobOrder, jobOrderForm);
                }
                else if ("deldep".equals(addOrDeleteDepositLine)) {
                    errorMsg="problem.delete.DepositInvoice";
                    deleteDepositInvoice(jobOrderForm, jobOrder);
                }
                jobOrderForm.setAddOrDeleteDepositLines("none");
            }
            
            validateJobOrder(jobOrder);
            tryCreateProject(request, jobOrder, errors);
        }
        catch (Throwable t) {
            t.printStackTrace();
            log.error(t.getMessage());
            errors.reject(errorMsg.equals("") ? "problem.general.page" : errorMsg, new Object[] { t.getMessage()}, null);
        }
        return showForm(request, response, errors);
    }

    /**
     * @param jobOrder
     * @throws JobSrvcException 
     */
    private void validateJobOrder(CEJobOrder jobOrder) throws JobSrvcException {
        ServiceManager.getJobOrderService().validateJobOrder(jobOrder);
    }

    private void tryCreateProject(HttpServletRequest request, CEJobOrder jobOrder, BindException errors) {
        String createProject = request.getParameter("createProject") + "";
        if (createProject.equalsIgnoreCase("true")) {
            ESBService esbService = ServiceManager.getEsbService();
            Project p = null;
            ProjectType pt = jobOrder.getProjectType();
            try {
                if (pt == null || pt == ProjectType.TYPE_1) {
                    p = esbService.createProjectType1(jobOrder);
                }
                else {
                    p = esbService.createOrUpdateProject(jobOrder);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.reject("generic.error", new Object[] { e.getMessage() }, null);
            }

            if (p != null) {
                jobOrder.setProject(p);
            }
        }
    }
    
    private void doSave(CEJobOrderForm ceJobOrderForm, CEJobOrder ceJobOrder) throws InvalidJobOrderOperationException, DaoException {
        String addOrDeleteTestLine = ceJobOrderForm.getAddOrDeleteTestLines();
        if ("saveandnext".equals(addOrDeleteTestLine) && (ceJobOrder.getStatus() == null || ceJobOrder.getStatus() == OrderStatus.NEW)) {
            ceJobOrder.setStatus(OrderStatus.OPEN);
        }        
        //ControllerUtil.loadAndUpdate(ceJobOrder, CEJobOrder.class);
    }

    private void doAddProductServiceLevel(CEJobOrder ceJobOrder, CEJobOrderForm ceJobOrderForm) throws JobSrvcException, DaoException {
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        JobServiceLevel productServiceLevel = jobSrvc.createJobServiceLevel(ceJobOrder.getRootServiceLevel(), ServiceLevelType.PRODUCT, "");
        ceJobOrderForm.addServiceLevelForm(productServiceLevel);
    }

    private void doDeleteProductServiceLevel(CEJobOrderForm ceJobOrderForm, CEJobOrder ceJobOrder) throws JobSrvcException, DaoException {
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        int prodIndex = ceJobOrderForm.getProdIndex();
        JobServiceLevel productServiceLevel = jobSrvc.findById(JobServiceLevel.class, Long.valueOf(prodIndex));
        jobSrvc.removeJobServiceLevel(ceJobOrder.getRootServiceLevel(), productServiceLevel);
        ceJobOrderForm.removeServiceLevelForm(productServiceLevel);
    }

    private void doAddTest(CEJobOrderForm jobOrderForm, CETestSearchForm addTestForm) throws JobSrvcException, DaoException {
        int prodIndex = jobOrderForm.getProdIndex();
        JobOrderService ceService = ServiceManager.getJobOrderService();
        JobServiceLevel product = ControllerUtil.findById(JobServiceLevel.class, new Long(prodIndex));
        log.warn("value of addTestForm manual test" + addTestForm.getOperation());
        if (addTestForm.getOperation() != null && "addManualTest".equals(addTestForm.getOperation())) {
            log.warn("inside manual Test");   
            if (addTestForm.getManualTestList() != null && addTestForm.getManualTestList().size() > 0) {
                for (HashMap<String, Object> map : addTestForm.getManualTestList()) {
                    JobTest jTest = ceService.createJobTest(product, null);
                    log.warn("after create Test ");
                    jTest.setLineDescription(map.get("description").toString());
                    jTest.setQuantity(((Double) map.get("quantity")).doubleValue());
                    jTest.setUnitPrice(((Double) map.get("unitprice")).doubleValue());
                }
            }
        }
        else {
            List<TestSearchResultRow> selectedTests = addTestForm.getSelectedTests();
            for (TestSearchResultRow testRow : selectedTests) {
//                test = ceService.findById(Test.class, test1.getTest().getTestId());
                JobTest jobTest = ceService.createJobTest(product, testRow.getTest());
                jobTest.setQuantity(testRow.getQty());
            }
        }
        // the entity structure changed, the form object needs to be updated
        jobOrderForm.updateForm();
    }

    private void doDeleteTest(int testIndex) throws JobSrvcException, DaoException {
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        JobTest jobTest = jobSrvc.findById(JobTest.class, Long.valueOf(testIndex));
        jobSrvc.removeJobTest(jobTest);
        DaoManager.getDao(JobTest.class).remove(jobTest);
    }

    private void doSaveOthers(CEJobOrderForm ceJobOrderForm, CEJobOrder ceJobOrder, HashMap<String, JobTest> testMap) throws JobSrvcException, DaoException {
        int testIndex = ceJobOrderForm.getTestIndex();
        String key = testIndex + "";              
        if (testMap != null && testMap.get(key) != null) {
            JobTest sessionTest = testMap.get(key);
            sessionTest = ControllerUtil.loadAndUpdate(sessionTest, JobTest.class);
        }
        ceJobOrderForm.setJobOrder(ceJobOrder);
        ceJobOrderForm.setAddOrDeleteTestLines("none");
    }

    private DepositInvoice addDepositInvoice(CEJobOrder ceJobOrder, CEJobOrderForm ceJobOrderForm) throws JobSrvcException, DaoException {
        log.warn("inside Add Deposit Invoice");
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        return jobSrvc.createDepositInvoice(ceJobOrder.getJobContract());
    }

    private void deleteDepositInvoice(CEJobOrderForm ceJobOrderForm, CEJobOrder ceJobOrder) throws JobSrvcException, DaoException {
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        int depInd = ceJobOrderForm.getDepIndex();
        DepositInvoice rDepInvoice = jobSrvc.findById(DepositInvoice.class, Long.valueOf(depInd));
        jobSrvc.removeDepositInvoice(ceJobOrder.getJobContract(), rDepInvoice);
    }

    private void doAddSplitLine(CEJobOrderForm jobOrderForm, CEJobOrder ceJobOrder, CETestSearchForm addTestForm) throws JobSrvcException, DaoException {
        int testIndex = jobOrderForm.getTestIndex();
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        //JobTest jobTest = jobSrvc.findById(JobTest.class, new Long(testIndex));
        JobTest jobTest = ControllerUtil.findById(JobTest.class, new Long(testIndex));
        String actionFlag = jobOrderForm.getActionFlag();
        if (actionFlag != null && "newTest".equals(actionFlag)) {
            if (addTestForm.getManualTest() == null || !addTestForm.getManualTest().equals("true")) {
                
                List<TestSearchResultRow> selectedTests = addTestForm.getSelectedTests();
                for (TestSearchResultRow test1 : selectedTests) {
//                    test1 = jobSrvc.findById(Test.class, test1.getTestId());
                    JobTest newRelatedJobTest = jobSrvc.createRelatedJobTest(jobTest.getJobServiceLevel(), test1.getTest(), jobTest);
                    newRelatedJobTest = setJobTestDefaultValue(newRelatedJobTest, ceJobOrder);
                    newRelatedJobTest.setQuantity(test1.getQty());
                }
            }
            else {
                log.warn("inside split manual Test");
                JobTest jTest = jobSrvc.createRelatedJobTest(jobTest.getJobServiceLevel(), null, jobTest);
                log.warn("after create Test ");
                jTest.setLineDescription(addTestForm.getDescription());
                jTest.setQuantity(addTestForm.getQuantity());
                jTest.setUnitPrice(addTestForm.getUnitPrice());
            }
        }
        else {
            JobTest newRelatedJobTest = jobSrvc.createRelatedJobTest(jobTest.getJobServiceLevel(), jobTest.getTest(), jobTest);
            newRelatedJobTest = setJobTestDefaultValue(newRelatedJobTest, ceJobOrder);
        }
        // the entity structure has changed, must update the form object to reflect it
        jobOrderForm.updateForm();
        
        
        // why call this here? jobTest is already updated. Remove it.
//        ControllerUtil.update(jobTest, jobTest, JobTest.class);
    }

    private CEJobOrder getManagedJobOrder(CEJobOrderForm jobOrderForm)  throws DaoException {
        // Some updates that are applied to the form but not the entity
        if (jobOrderForm.getProducts() != null && jobOrderForm.getProducts().size() > 0) {
            for (ServiceLevelForm jsf : jobOrderForm.getProducts()) {
                jsf.updateServiceLevelName();
            }
        }
        CEJobOrder detachedJobOrder = jobOrderForm.getJobOrder();
        CEJobOrder jobOrder = ControllerUtil.loadAndUpdate(detachedJobOrder, CEJobOrder.class);

        ControllerUtil.updateCollection(jobOrder, detachedJobOrder, CEJobOrder.class, "jobContracts");
        ControllerUtil.updateCollection(jobOrder, detachedJobOrder, CEJobOrder.class, "instructions");
        ControllerUtil.updateCollection(jobOrder.getRootServiceLevel(), detachedJobOrder.getRootServiceLevel(), 
                                        JobServiceLevel.class, "childServiceLevels");
        //merge Deposit Invoice
        outer: for(CEJobContract formContract: detachedJobOrder.getJobContracts()){
            for(CEJobContract newJobContract: jobOrder.getJobContracts()){
                if(newJobContract.getId().equals(formContract.getId())){
                    ControllerUtil.updateCollection(newJobContract, formContract, CEJobContract.class, "depositInvoices");    
                    break outer;
                }
            }
        }
        
        //merge JobTest
        outer: for(JobServiceLevel formProd: detachedJobOrder.getRootServiceLevel().getChildServiceLevels()){
            for(JobServiceLevel newProd: jobOrder.getRootServiceLevel().getChildServiceLevels()){
                if(newProd.getId().equals(formProd.getId())){
                    ControllerUtil.updateCollection(newProd, formProd, JobServiceLevel.class, "jobTests");    
                    break outer;
                }
            }
        }
        
        // TODO review this
        if ((jobOrder.getPageNumber() == null) || (jobOrder.getPageNumber().intValue() < 2)) {
            jobOrder.setPageNumber(Integer.valueOf(2));
        }

        jobOrderForm.setJobOrder(jobOrder);
        return jobOrder;
    }

    /*
    private CEJobOrder doMerge(CEJobOrderForm ceJobOrderForm) throws JobSrvcException, DaoException {
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        String addOrDeleteTestLine = ceJobOrderForm.getAddOrDeleteTestLines();
        CEJobOrder ceJobOrder = null;        
        if ((addOrDeleteTestLine != null) && "saveother".equals(addOrDeleteTestLine)) {
            CEJobOrder formJobOrder = jobSrvc.findById(CEJobOrder.class, ceJobOrderForm.getJobNumber());
            if (formJobOrder.getProjectType() == null) {
                formJobOrder.setProjectType(ProjectType.TYPE_3);
            }
            ceJobOrder = ControllerUtil.loadAndUpdate(formJobOrder, CEJobOrder.class);
        }
        else {
            CEJobOrder formJobOrder = ceJobOrderForm.getJobOrder();
            if (ceJobOrderForm.getProducts() != null && ceJobOrderForm.getProducts().size() > 0) {
                for (ServiceLevelForm jsf : ceJobOrderForm.getProducts()) {
                    jsf.updateServiceLevelName();
                }
            }
            if ((formJobOrder.getPageNumber() == null) || (formJobOrder.getPageNumber().intValue() < 2)) {
                formJobOrder.setPageNumber(Integer.valueOf(2));
            }
            log.warn("inside do Merge before load and update");
            ceJobOrder = ControllerUtil.loadAndUpdate(formJobOrder, CEJobOrder.class);
            log.warn("inside do Merge after load and update");
            //ceJobOrder = dao.merge(ceJobOrder);
            //ceJobOrder = ControllerUtil.loadAndUpdate(formJobOrder, CEJobOrder.class);
            ControllerUtil.updateCollection(ceJobOrder, formJobOrder, CEJobOrder.class, "instructions");
            //ControllerUtil.updateCollection(ceJobOrder, formJobOrder, CEJobOrder.class, "depositInvoices");
            
            ControllerUtil.updateCollection(ceJobOrder.getRootServiceLevel(), formJobOrder.getRootServiceLevel(), 
                                            JobServiceLevel.class, "childServiceLevels");
            
            //merge Deposit Invoice
            for(CEJobContract formContract: formJobOrder.getJobContracts()){
                if(formContract.getDepositInvoices()!=null && formContract.getDepositInvoices().size()>0){
                    for(CEJobContract newJobContract: ceJobOrder.getJobContracts()){
                        if(newJobContract.getId().longValue()==formContract.getId().longValue()){
                            ControllerUtil.updateCollection(newJobContract, formContract, CEJobContract.class, "depositInvoices");    
                            break;
                        }
                    }
                }
            }
            
            //merge JobTest
            for(JobServiceLevel formProd: formJobOrder.getRootServiceLevel().getChildServiceLevels()){
                if(formProd.getJobTests()!=null && formProd.getJobTests().size()>0){
                    for(JobServiceLevel newProd: ceJobOrder.getRootServiceLevel().getChildServiceLevels()){
                        if(newProd.getId().longValue()==formProd.getId().longValue()){
                            ControllerUtil.updateCollection(newProd, formProd, JobServiceLevel.class, "jobTests");    
                            break;
                        }
                    }
                }
            }
            //ceJobOrderForm.setJo(ceJobOrder);
            //ceJobOrderForm.setJo(ceJobOrder);
        }
        return ceJobOrder;
    }
     */
    
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        String refreshing = request.getParameter("refreshing");

        if ((refreshing != null) && "true".equals(refreshing)) {
            return true;
        }
        String addOrDeleteTestLine = request.getParameter("addOrDeleteTestLines");
        System.out.println("value for addOrDeleteTestLine is ==========" + addOrDeleteTestLine);
        if (addOrDeleteTestLine != null && ("save".equals(addOrDeleteTestLine) ||  "saveandnext".equals(addOrDeleteTestLine) )) {
            log.debug("calling suppress Validation");
            return super.suppressValidation(request, command);
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        System.out.println("inside form backing....");
        String jobNumber = request.getParameter("jobNumber");
        CEJobOrder jo = null;
        JobOrderService jobOrderService = ServiceManager.getJobOrderService();
        // Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
        if (jobNumber != null && !"".equals(jobNumber.trim())) {
            // jo.setJobNumber(jobNumber);
            try {
                jo = jobOrderService.findById(CEJobOrder.class, jobNumber);
                jobOrderService.setDefaultInstructions(jo);
                // TODOD: Project type is currently empty, temporary set it to
                // Type2
                if (jo.getProjectType() == null) {
                    jo.setProjectType(ProjectType.TYPE_3);
                }
                ControllerUtil.loadAndUpdate(jo, CEJobOrder.class);
                //jobOrderService.updateJobOrder(newJo);
            }
            catch (Exception e) {
                log.error(e.getMessage());
                throw new ServiceException("record.not.exist.error", new Object[] { jobNumber }, e);
            }
        }
        CEJobOrderForm myForm = new CEJobOrderForm(jo);
        return myForm;
    }

    private JobTest setJobTestDefaultValue(JobTest jobTest, CEJobOrder jobOrder) {
        jobTest.setBranchName(jobOrder.getBranchName());
        jobTest.setServiceLocationCode(jobOrder.getServiceLocationCode());
        try {
            jobTest = DaoManager.getDao(JobTest.class).merge(jobTest);
        }
        catch (Exception e) {

        }
        return jobTest;
    }

}
