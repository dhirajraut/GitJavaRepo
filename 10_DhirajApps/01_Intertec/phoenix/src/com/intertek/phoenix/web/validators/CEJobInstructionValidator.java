/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.web.controller.job.CEJobOrderForm;
import com.intertek.phoenix.web.controller.job.JobTestForm;
import com.intertek.phoenix.web.controller.job.ServiceLevelForm;

/**
 * Purpose: To validate JI page command object 
 * 
 * @version 1.0 Apr 22, 2009
 * @author Patni
 */
public class CEJobInstructionValidator implements Validator {

    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(Class clazz) {
        // TODO Auto-generated method stub

        return CEJobOrderForm.class.isAssignableFrom(clazz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object obj, Errors errors) {
        CEJobOrderForm instructionForm = (CEJobOrderForm) obj;
        
        if(instructionForm.getProducts()!=null && instructionForm.getProducts().size()>0){
            int cnt=0;
            for(ServiceLevelForm frm : instructionForm.getProducts()){
                if (frm.getDescription() == null || "".equals(frm.getDescription())) {
                    errors.reject("product.description.null", new Object[] { Integer.toString(cnt) }, "");
                }
                
                
                if(frm.getProductGroup()==null || "".equals(frm.getProductGroup())){
                    errors.reject("product.productgroup.null",new Object[]{Integer.toString(cnt)},"");
                }
//                else if (!CommonUtil.validateAlphaNum(frm.getProductGroup())) {
//                    errors.reject("product.productgroup.invalid",new Object[]{Integer.toString(cnt)},"");
//                }
//                
               if (frm.getJobTest() != null && frm.getJobTest().length > 0) {
                   double linenum = 0;
                   String linecount;
                    for (JobTestForm jtform : frm.getJobTest()) {
                        JobTest jt = jtform.getJobTest();
                        if (jt.isNotRelated()) {
                            linenum = jt.getLinenumber();
                            linecount= Long.toString(jt.getLinenumber());
                        }
                        else {
                            linenum = jt.getMaster().getLinenumber() + 0.1 * jt.getLinenumber();
                            linecount=Double.toString(linenum);
                        }
                        
                        
                        if (jt.getLineDescription() == null || jt.getLineDescription().equals("")) {
                            errors.reject("jobTest.linedesc.null", new Object[] {linecount }, "");
                        }
//                        else if (!CommonUtil.validateAlphaNum(jt.getLineDescription())) {
//                            errors.reject("jobTest.linedesc.invalid", new Object[] { linecount }, "");
//
//                        }
                       
                        
                        if (jt.getUom()==null||jt.getUom().equals("") ) {
                            errors.reject("jobTest.uom.null", new Object[] {linecount }, "");
                        }
//                        else if (!CommonUtil.validateAlphaNum(jt.getUom())) {
//                            errors.reject("jobTest.uom.invalid", new Object[] {linecount }, "");
//
//                        }
                        
                        // RQ: Look, quotedAmount is already a number, why convert it
                        // to String and then check if it's a number again?
//                        if (jt.getQuotedAmount()==0 ) {
//                            errors.reject("jobTest.quotedAmount.null", new Object[] {linecount }, "");
//                        }
//                        else if (!CommonUtil.validateNum(String.valueOf(jt.getQuotedAmount()))) {
//                            errors.reject("jobTest.quotedAmount.invalid", new Object[] {linecount}, "");
//                        }
                        
                        // See above comment
           //TODO:QuotedAmount default is 0.0 so uncomment afterwards             
//                        if (jt.getForcastedRevenue()==0 ) {
//                            errors.reject("jobTest.forcastedRevenue.null", new Object[] { Integer.toString(testCount) }, "");
//                        }
//                        else 
//                        if (!CommonUtil.validateNum(String.valueOf(jt.getForcastedRevenue()))) {
//                            errors.reject("jobTest.forcastedRevenue.numeric", new Object[] {linecount }, "");
//                        }
                        
                        
                        if (jt.getQuotedAmount() < jt.getForcastedRevenue()) {
                                System.out.println("inside Validator ....... job Test");
                                errors.reject("jobTest.forcastedRevenue.invalid", new Object[] {linecount }, "");
                            }

                            if(jt.getModelNumber()==null || jt.getSampleDescription()==null || jt.getServiceLocationCode()==null)
                            {
                                errors.reject("required.field.others", new Object[] { linecount}, "");
                            }
                            else if(jt.getModelNumber().equals("") || jt.getSampleDescription().equals("") || jt.getSampleDescription().equals(""))
                            {        
                                 errors.reject("required.field.others", new Object[] { linecount}, "");                        
                            }
                        }
                    
                    }
                
             /*   if(frm.getProductName()==null || "".equals(frm.getProductName())){
                    errors.reject("product.productname.invalid",new Object[]{Integer.toString(cnt)},"");
                }*/

                cnt++;
            }
        }
        if(instructionForm.getDepositOrderLineItems()!=null && instructionForm.getDepositOrderLineItems().size()>0){
            for(DepositInvoice depInv : instructionForm.getDepositOrderLineItems()){                
                if(depInv.getDepositReference()==null || "".equals(depInv.getDepositReference())){
                    errors.reject("deposit.refnumber.null");
                }
                
//                TODO:alphanumeric commented
//                else
//                    if(!CommonUtil.validateAlphaNum(depInv.getDepositReference())){
//                        errors.reject("deposit.refnumber.invalid");
//                    }
            }
        }
    }

}
