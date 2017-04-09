/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.entity.CfgContract;
import com.intertek.entity.Control;
import com.intertek.entity.Service;
import com.intertek.exception.ServiceException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractServiceControl;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;

/**
 * Controller for 'Add Service' popup
 * 
 * @author rautsmit
 * 
 */
public class AddJobServicesController extends SimpleFormController {

    /**
     * Constructs AddJobServicesController instance
     */
    public AddJobServicesController() {
        super();
        setCommandClass(CEServicePopupForm.class);
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

        CEServicePopupForm form = (CEServicePopupForm) command;
        JobOrderService jobOrderService = ServiceManager.getJobOrderService();
        Service service = form.getService();
        CEJobContract joco = form.getJobContract();
        joco = loadAndUpdateContract(joco);
        
        String contractCode = joco.getContract().getContractCode();
        Date nominationDate = joco.getJobOrder().getNominationDate();
        String langCD = (joco.getInvoiceLanguage() == null ? "ENG" : joco.getInvoiceLanguage());
        CfgContract cfgContract = jobOrderService.getCfgContractByContractId(contractCode, nominationDate);

        if (form.getOperation().equals("addService")) {
            Long productId = Long.valueOf(form.getProductId());
            ContractServiceLevel productSL = DaoManager.getDao(ContractServiceLevel.class).find(productId);

            JobContractService jocoServiceEntity = jobOrderService.createJobContractService(productSL, service);
            List<Control> controls = new ArrayList<Control>();

            Map<Control, ControlForm> controlAndFormMap = new HashMap<Control, ControlForm>();

            for (ControlForm controlForm : form.getControlForms()) {
                if (controlForm.getDataInput() != null) {
                    controlAndFormMap.put(controlForm.getControl(), controlForm);
                    controls.add(controlForm.getControl());
                }
            }

            Set<JobContractServiceControl> jcscSet = jobOrderService.updateJobContractService(joco, jocoServiceEntity, controls);

            for (JobContractServiceControl jcsc : jcscSet) {
                Control control = jcsc.getControl();
                jcsc.setInputValue0(controlAndFormMap.get(control).getDataInput());
            }

            Collection<CEJobOrderLineItem> jolis = jobOrderService.updateJobOrderLineItem(joco, productSL, jocoServiceEntity);

            for (CEJobOrderLineItem joli : jolis) {
                joco.addJobOrderLineItem(joli);
                //TODO: These extra save's will be removed after proper testing once 'Add Service' is functional
                DaoManager.getDao(CEJobOrderLineItem.class).saveOrUpdate(joli);
                DaoManager.getDao(CEJobContract.class).saveOrUpdate(joco);
                DaoManager.getDao(CEJobOrderLineItem.class).flush();
            }
        }

        if (form.getOperation().equals("selectService")) {

            List<Control> controls = jobOrderService.getServiceControls(cfgContract, service.getServiceId().getServiceName(), nominationDate, langCD);

            ControlForm[] controlForms = new ControlForm[controls.size()];
            for (int i = 0; i < controls.size(); i++) {
                controlForms[i] = new ControlForm((Control) controls.get(i));
            }

            form.setControlForms(controlForms);
        }

        return showForm(request, response, errors);
    }

    private CEJobContract loadAndUpdateContract(CEJobContract joco) throws DaoException {
        CEJobContract newJoco = ControllerUtil.loadAndUpdate(joco, CEJobContract.class);
        return newJoco;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        try {
            JobOrderService jobSrvc = ServiceManager.getJobOrderService();
            Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
            CEJobOrder jo = null;
            String jobNumber = request.getParameter("jobNumber");
            String productId = request.getParameter("productId");

            if (jobNumber != null && !"".equals(jobNumber)) {
                jo = jobDao.find(jobNumber);
            }
            if (jo == null) {
                throw new ServiceException("record.not.exist.error", new Object[] { jobNumber }, null);
            }

            CEJobContract jobContract = jo.getJobContract();
            String contractCode = jobContract.getContract().getContractCode();
            Date nominationDate = jo.getNominationDate();
            String langCD = (jobContract.getInvoiceLanguage() == null ? "ENG" : jobContract.getInvoiceLanguage());

            CfgContract cfgContract = jobSrvc.getCfgContractByContractId(contractCode, nominationDate);
            List<Service> services = jobSrvc.getServices(cfgContract, nominationDate, langCD);
            CEServicePopupForm serviceForm = new CEServicePopupForm(jobContract, services);
            serviceForm.setProductId(productId);
            return serviceForm;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
