/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.validators;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.entity.Branch;
import com.intertek.entity.User;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.util.DateUtil;
import com.intertek.phoenix.web.controller.job.CEJobInstructionOthersPopupForm;

/**
 * Purpose: To validate JI Others page command object 
 * 
 * @version 1.0 Apr 22, 2009
 * @author Patni
 */
public class CEJobInstructionOthersValidator implements Validator {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(Class clazz) {
        // TODO Auto-generated method stub

        return CEJobInstructionOthersPopupForm.class.isAssignableFrom(clazz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object obj, Errors errors) {
        // TODO Auto-generated method stub

        CEJobInstructionOthersPopupForm othersForm = (CEJobInstructionOthersPopupForm) obj;

        if (othersForm.getWorkertimeFlag()!=null && "save".equals(othersForm.getWorkertimeFlag())) {
            
            System.out.println("inside CEJobInstructionOthersValidator");
            String startDate=othersForm.getStartDate1();
                if(!CommonUtil.isNullOrEmpty(startDate)){
                    Timestamp stringToDate = DateUtil.stringToDate(startDate);
                    if(stringToDate==null ){
                        errors.reject("startDate.invalid");
                    }
                }
                
                String endDate=othersForm.getEndDate1();
                if(!CommonUtil.isNullOrEmpty(endDate)){
                    Timestamp stringToDate = DateUtil.stringToDate(endDate);
                    if(stringToDate==null ){
                        errors.reject("endDate.invalid");
                    }
                }

                String taskReadyDate=othersForm.getTaskReadyDate1();
                if(!CommonUtil.isNullOrEmpty(taskReadyDate)){
                    Timestamp stringToDate = DateUtil.stringToDate(taskReadyDate);
                    if(stringToDate==null ){
                        errors.reject("taskReadyDate.invalid");
                    }
                }

                String buname=othersForm.getWarehouseName();
                if(!CommonUtil.isNullOrEmpty(buname)){
                    SearchService searchSrvc = ServiceManager.getSearchService();
                    Map<String,String> criteria = new HashMap<String,String>();
                    criteria.put("name", buname);
                    try{
                    if(searchSrvc.uniqueSearch(Branch.class, criteria)==null){
                        errors.reject("buname.invalid");
                    }
                    }catch(Exception de){
                        errors.reject("buname.invalid");
                    }
                }

                String poNumber=othersForm.getPurchaseOrderNo();
                Double po_max_amount = new Double(0);
                if (!CommonUtil.isNullOrEmpty(poNumber)) {
                    SearchService searchSrvc = ServiceManager.getSearchService();
                    Map<String,String> criteria = new HashMap<String,String>();
                    criteria.put("poNumber", poNumber);
                    try{
                        PurchaseOrder po  =searchSrvc.uniqueSearch(PurchaseOrder.class, criteria);
                        if(po==null){
                            errors.reject("PO.invalid");
                        }else{
                            po_max_amount = po.getMaxAmount();
                        }
                    }catch(Exception de){
                        errors.reject("PO.invalid");
                    }
                }
                
                if(othersForm.getFundedAmount() > 0) 
                {
                    if (CommonUtil.isNullOrEmpty(othersForm.getPurchaseOrderNo())){
                        errors.reject("po.blank"); 
                    }else{
                        if(othersForm.getFundedAmount() > po_max_amount)
                        {
                            errors.reject("po.customerPOAmount.invalid", new Object[]{po_max_amount},"");
                        }
                    }
                }

                String taskManagerId=othersForm.getTaskManagerId();
                if (!CommonUtil.isNullOrEmpty(taskManagerId)) {
                    SearchService searchSrvc = ServiceManager.getSearchService();
                    Map<String,String> criteria = new HashMap<String,String>();
                    criteria.put("employeeId", taskManagerId);
                    try{
                    if(searchSrvc.uniqueSearch(Employee.class, criteria)==null){
                        errors.reject("taskManager.invalid");
                    }
                    }catch(Exception de){
                        errors.reject("taskManager.invalid");
                    }
                }
                String creditById = othersForm.getCreditOverrideById();
                if (!CommonUtil.isNullOrEmpty(creditById)) {                  
                        SearchService searchSrvc = ServiceManager.getSearchService();
                        Map<String,String> criteria = new HashMap<String,String>();
                        criteria.put("loginName", creditById);
                    try{
                        if(searchSrvc.uniqueSearch(User.class, criteria)==null){
                            errors.reject("creditOverBy.invalid");
                        }
                    }catch(Exception de){
                        errors.reject("creditOverBy.invalid");
                    }
                }
        }
    }

}
