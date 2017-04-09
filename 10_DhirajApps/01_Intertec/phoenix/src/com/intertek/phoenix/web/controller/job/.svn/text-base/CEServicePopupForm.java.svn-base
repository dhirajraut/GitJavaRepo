/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intertek.entity.Service;
import com.intertek.entity.ServiceId;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEJobContract;

/**
 * Form for 'Add Service' popup on 'Select Charges' page
 * 
 * @author rautsmit
 * 
 */
public class CEServicePopupForm extends Form {

    private CEJobContract jobContract;
    private Map<String, Service> servicesMap;
    private Service service;
    private String serviceCode;
    private List<Field> serviceNames;
    private String operation;
    private String productId;
    private ControlForm[] controlForms;

    /**
     * Instantiates CEServicePopupForm
     * @param jobContract
     * @param services
     */
    public CEServicePopupForm(CEJobContract jobContract, List<Service> services) {
        updateForm(jobContract, services);
    }

    /**
     * Rebuilds form data with passed contract and services
     * 
     * @param jobContract
     */
    public void updateForm(CEJobContract jobContract, List<Service> services) {
        servicesMap = new HashMap<String, Service>();
        serviceNames = new ArrayList<Field>();
        this.jobContract = jobContract;

        for (Service s : services) {
            ServiceId serviceId = s.getServiceId();
            String code = serviceId.getContractId() + serviceId.getServiceName() + serviceId.getParentServiceId();
            servicesMap.put(code, s);

            Field fld = new Field();
            fld.setName(s.getServiceId().getServiceName());
            fld.setValue(code);
            serviceNames.add(fld);
        }
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String code) {
        this.serviceCode = code;
        this.service = servicesMap.get(code);
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Field> getServiceNames() {
        return serviceNames;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }

    public String getJobNumber() {
        return jobContract.getJobOrder().getJobNumber();
    }

    public String getJobStatus() {
        return jobContract.getJobOrder().getStatus().getDescription();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public ControlForm[] getControlForms() {
        return controlForms;
    }

    public void setControlForms(ControlForm[] controlForms) {
        this.controlForms = controlForms;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
