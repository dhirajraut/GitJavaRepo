package com.intertek.phoenix.web.controller.job;

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
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractCustContactId;
import com.intertek.exception.ServiceException;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobOrderType;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.job.InvalidJobOrderOperationException;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.web.controller.ControllerUtil;
import com.intertek.web.controller.BaseSimpleFormController;

/**
 * This is the form controller for job_entry_ce page.
 * 
 * @author lily.sun
 * @author eric.nguyen
 * @author richard.qin
 */

public class JobOrderCEFormController extends BaseSimpleFormController {

    /** The log. */
    private static Log log = LogFactory.getLog(JobOrderCEFormController.class);

    public JobOrderCEFormController() {
        super();
        setSessionForm(true);
        setCommandClass(CEJobOrderForm.class);
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        String errMsg = null;
        CEJobOrderForm myForm = (CEJobOrderForm) command;
        String action = myForm.getActionFlag();

        try {
            CEJobOrder jobOrder = getManagedJobOrder(myForm);

            if ("addJob".equalsIgnoreCase(action) || "saveCustomer".equalsIgnoreCase(action) || "next".equalsIgnoreCase(action)) {
                errMsg = "problem.jo.save.customer";
                processSaveJobOrder(jobOrder, myForm);
            }
            else if ("deleteCustomer".equalsIgnoreCase(action)) {
                errMsg = "problem.jo.delete.customer";
                processDeleteCustomer(jobOrder, myForm);
            }
            else if ("addCustomer".equalsIgnoreCase(action)) {
                errMsg = "problem.jo.add.customer";
                processAddCustomer(jobOrder, request.getParameter("inputFieldIdValue"));
            }
            myForm.setActionFlag("");
            myForm.setInputFieldIdValue("");
            populateJobContractForms(jobOrder, myForm);

            if ("next".equalsIgnoreCase(action)) {
                jobOrder.setStatus(OrderStatus.OPEN);
                return new ModelAndView(new RedirectView("phx_job_operational_info_ce.htm"), "jobNumber", myForm.getJobNumber());
            }

        }
        catch (InvalidJobOrderOperationException e) {
            log.error(e.getMessage());
            errors.reject(errMsg == null ? "problem.general.page" : errMsg, new Object[] { "try again" }, "");
            return showForm(request, response, errors);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            errors.reject(errMsg == null ? "problem.general.page" : errMsg, new Object[] { "try again" }, "");
            return showForm(request, response, errors);
        }

        return showForm(request, response, errors);
    }

    /**
     * @param jobOrderDetached
     * @return the Job Order contained in myForm, and convert it into a managed
     *         object if possible.
     * @throws DaoException
     */
    private CEJobOrder getManagedJobOrder(CEJobOrderForm myForm) throws DaoException {
        CEJobOrder jobOrderDetached = myForm.getJobOrder();

        if (jobOrderDetached.getJobNumber() != null) {
            CEJobOrder jobOrder = ControllerUtil.loadAndUpdate(jobOrderDetached, CEJobOrder.class);

            List<CEJobContract> jobContracts = new ArrayList<CEJobContract>();
            if (myForm.getJobContracts() != null) {
                for (CEJobContractForm form : myForm.getJobContracts()) {
                    jobContracts.add(form.getJobContract());
                }
            }
            try {
                String action = myForm.getActionFlag();
                if (!action.equals("deleteCustomer")) {
                    ControllerUtil.updateCollection(jobOrder.getJobContracts(), jobContracts, CEJobContract.class);
                }
            }
            catch (DaoException e) {
                log.warn("error while updating JobContracts");
            }
            myForm.setJobOrder(jobOrder);
            return jobOrder;
        }
        return jobOrderDetached;
    }

    private void processSaveJobOrder(CEJobOrder jobOrder, CEJobOrderForm myForm) throws JobSrvcException, DaoException {
        JobOrderService jobService = ServiceManager.getJobOrderService();

        if (jobOrder.getJobNumber() == null) {
            // this job order is never persisted before, save it now
            jobOrder = jobService.saveJobOrder(jobOrder);
            ControllerUtil.update(jobOrder, CEJobOrder.class); // self update

            // and save all the job contracts
            if (myForm.getJobContracts() != null) {
                for (CEJobContractForm form : myForm.getJobContracts()) {
                    CEJobContract jobContract = form.getJobContract();
                    // fix to the bank account not saving problem
                    // is this entity error, as there two fields defined to have
                    // the same value?
                    jobContract.setRemitToBankCode(jobContract.getRemitToCode());
                    jobContract.setRemitToBankAccountBuName(jobOrder.getBuName());
                    // done fix
                    ControllerUtil.update(jobContract, CEJobContract.class); // self
                    jobOrder.addJobContract(jobContract);
                }
            }
        }

        // When the job order is created within phoenix, there could be
        // input data for Quote issue date and quote Id
        if (myForm.isQuoteDateChanged() || myForm.isQuoteNumberChanged()) {
            jobService.createOrUpdateQuote(jobOrder, myForm.getFormQuoteDate(), myForm.getFormQuoteNumber());
        }
    }

    /**
     * @throws InvalidJobOrderOperationException
     * @throws DaoException
     * 
     */
    private void processDeleteCustomer(CEJobOrder jobOrder, CEJobOrderForm form) throws InvalidJobOrderOperationException, DaoException {
        JobOrderService jobService = ServiceManager.getJobOrderService();
        int idx = Integer.parseInt(form.getContractIndex());
        CEJobContract jobContractDetached = form.getJobContracts()[idx].getJobContract();
        Long jobContractId = jobContractDetached.getId();
        if (jobContractId != null) {
            CEJobContract jobContract = ControllerUtil.findById(CEJobContract.class, jobContractId);
            jobService.removeContract(jobOrder, jobContract);
        }
        else {
            jobOrder.removeJobContract(jobContractDetached);
        }
    }

    private void processAddCustomer(CEJobOrder jobOrder, String inputField) throws DaoException, JobSrvcException {
        JobOrderService jobService = ServiceManager.getJobOrderService();
        ContractCustContact contractCustContact = getContractCustContact(inputField);
        jobService.addCustomer(jobOrder, contractCustContact);
    }

    /**
     * @param inputField
     * @return
     * @throws DaoException
     */
    private ContractCustContact getContractCustContact(String inputField) throws DaoException {
        ContractCustContact contractCustContact = null;
        String[] values = inputField.split(",");
        if (values.length == 3) {
            long contactId = Long.parseLong(values[1]);
            ContractCustContactId cccId = new ContractCustContactId();
            cccId.setCustCode(values[0]);
            cccId.setContactId(contactId);
            cccId.setContractCode(values[2]);
            contractCustContact = DaoManager.getDao(ContractCustContact.class).find(cccId);
        }
        return contractCustContact;
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    protected Object formBackingObject(HttpServletRequest request) throws PhoenixException {

        JobOrderService jobService = ServiceManager.getJobOrderService();
        Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
        CEJobOrderForm jobOrderForm = null;
        try {
            String jobtype = request.getParameter("jobType");
            CEJobOrder jobOrder = null;
            String ecsOrderNumber = request.getParameter("ecsOrderNumber");
            String jobNumber = request.getParameter("jobNumber");

            if (!CommonUtil.isNullOrEmpty(jobNumber)) {
                jobOrder = jobDao.find(jobNumber);
            }
            else if (!CommonUtil.isNullOrEmpty(ecsOrderNumber)) {
                // what is the ecsOrderNumber?

                // RQ: how can the following code work? TODO
                // Quote quote = new Quote();
                // quote.setOrderNumber(ecsOrderNumber);
                // ceJobOrder.setQuote(quote);
                // try{
                // ceJobOrder = jobDao.searchUnique(ceJobOrder);
                // }
                // catch(Exception e){
                // throw new ServiceException("ecs.order.number.non.exist", new
                // Object[] { ecsOrderNumber }, e);
                // }
            }
            else {
                jobOrder = jobService.createJobOrder();
                if (jobtype != null) {
                    jobOrder.setJobType(JobOrderType.valueOf(jobtype));
                }
            }

            jobOrderForm = new CEJobOrderForm(jobOrder);
            populateJobContractForms(jobOrder, jobOrderForm);
            request.getSession().setAttribute("dateFormat", Form.getCurrentUserFormat());
        }
        catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getParams(), e);
        }
        catch (PhoenixException e) {
            throw new ServiceException(e.getMessage(), null, e);
        }

        catch (Throwable t) {
            logger.info(t.getMessage());
            throw new ServiceException("Error while loading the page", new Object[] { t.getMessage() }, t);
        }

        return jobOrderForm;
    }

    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        String refreshing = request.getParameter("refreshing");
        if ((refreshing != null) && "true".equals(refreshing)) {
            return true;
        }
        String action = ((CEJobOrderForm) command).getActionFlag();
        if ("deleteCustomer".equals(action)) {
            return true;
        }
        return super.suppressValidation(request, command);
    }

    private void populateJobContractForms(CEJobOrder jobOrder, CEJobOrderForm jobOrderForm) throws PhoenixException {

        try {
            Set<CEJobContract> jcs = jobOrder.getJobContracts();
            if (jcs.size() > 0) {
                CEJobContractForm[] jobContractForms = new CEJobContractForm[jcs.size()];
                int counter = 0;
                for (CEJobContract jobContract : jcs) {
                    CEJobContractForm jobContractForm = new CEJobContractForm(jobContract);
                    jobContractForms[counter++] = jobContractForm;
                }
                jobOrderForm.setJobContracts(jobContractForms);
            }
            else {
                jobOrderForm.setJobContracts(null);
            }
        }
        catch (Exception e) {
            throw new PhoenixException("Error in populating the JobContracts");
        }
    }

}
