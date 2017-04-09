/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.EnumField;
import com.intertek.phoenix.common.EnumFieldFilter;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.Instruction;
import com.intertek.phoenix.entity.InstructionType;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.entity.Permission;
import com.intertek.phoenix.entity.Project;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.util.DateUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.SortUtil;

/**
 * This class is mainly to backup job_entry_ce.jsp
 * 
 * @author lily.sun
 * @author eric.nguyen
 * @author richard.qin
 */

public class CEJobOrderForm extends Form {
    @CascadeValidation 
    private CEJobOrder jo;
    @CascadeValidation private CEJobContractForm[] jobContractForms;
    private String inputFieldIdValue;
    private String actionFlag;
    private String contractIndex;
    private String tabName = "0";

    private String addOrDeleteTestLines;// for the job instruction page
    private String addOrDeleteDepositLines;// for the job instruciton page
    private String projectFlag;// need to be changed for the job instruction
    // page
    private int depIndex;// for the jobinstruction page
    private String addOrDeleteSplitLines;// for the job instruction page
    private int splitIndex;// for the job instruction page
    private String addOrDeleteEvent;
    private int testIndex;
    private int prodIndex;
    private String editFlag;
    private String nextPageFlag;
    private String scrollFlag;
    private String testId;
    private String promiseCompDate;
    private String custReadyDate;
    private String actReadyDate;
    private String qtDate;
    private String serviceLocationName;
    private String projectManagerFullName;
    
    @SuppressWarnings("unused")
    @CascadeValidation private DepositInvoice[] depositOrderLineItem;
    // new addition
    private List<ServiceLevelForm> products;
    private String formQuoteNumber;
    private Timestamp formQuoteDate;

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getNextPageFlag() {
        return nextPageFlag;
    }

    public void setNextPageFlag(String s) {
        this.nextPageFlag = s;
    }

    public String getOperationInstructions() {
        Instruction ins = jo.getInstruction(InstructionType.OPERATION);
        if (ins != null) {
            return ins.getInstruction();
        }
        return "";
    }

    public String getSampInstructions() {
        Instruction ins = jo.getInstruction(InstructionType.SAMPLE);
        if (ins != null) {
            return ins.getInstruction();
        }
        return "";
    }

    public String getLabInstructions() {
        Instruction ins = jo.getInstruction(InstructionType.LAB);
        if (ins != null) {
            return ins.getInstruction();
        }
        return "";
    }

    public String getShipInstructions() {
        Instruction ins = jo.getInstruction(InstructionType.SHIPPING);
        if (ins != null) {
            return ins.getInstruction();
        }
        return "";
    }

    public String getReptInstructions() {
        Instruction ins = jo.getInstruction(InstructionType.REPORTING);
        if (ins != null) {
            return ins.getInstruction();
        }
        return "";
    }

    public String getBillInstructions() {
        Instruction ins = jo.getInstruction(InstructionType.BILLING);
        if (ins != null) {
            return ins.getInstruction();
        }
        return "";
    }

    public String getOtherInstructions() {
        Instruction ins = jo.getInstruction(InstructionType.OTHER);
        if (ins != null) {
            return ins.getInstruction();
        }
        return "";
    }

    public void setOperationInstructions(String operationalInstructions) {
        Instruction ins = jo.getInstruction(InstructionType.OPERATION);
        if (operationalInstructions != null && ins != null) {
            ins.setInstruction(CommonUtil.toStringOrNull(operationalInstructions));
        }
    }

    public void setSampInstructions(String sampleInstruction) {
        Instruction ins = jo.getInstruction(InstructionType.SAMPLE);
        if (sampleInstruction != null && ins != null) {
            ins.setInstruction(CommonUtil.toStringOrNull(sampleInstruction));
        }

    }

    public void setLabInstructions(String labInstructioins) {
        Instruction ins = jo.getInstruction(InstructionType.LAB);
        if (labInstructioins != null && ins != null) {
            ins.setInstruction(CommonUtil.toStringOrNull(labInstructioins));
        }
    }

    public void setShipInstructions(String shipInstructions) {
        Instruction ins = jo.getInstruction(InstructionType.SHIPPING);
        if (shipInstructions != null && ins != null) {
            ins.setInstruction(CommonUtil.toStringOrNull(shipInstructions));
        }
    }

    public void setReptInstructions(String reptInstructions) {
        Instruction ins = jo.getInstruction(InstructionType.REPORTING);
        if (reptInstructions != null && ins != null) {
            ins.setInstruction(CommonUtil.toStringOrNull(reptInstructions));
        }
    }

    public void setBillInstructions(String billInstructions) {
        Instruction ins = jo.getInstruction(InstructionType.BILLING);
        if (billInstructions != null && ins != null) {
            ins.setInstruction(CommonUtil.toStringOrNull(billInstructions));
        }
    }

    public void setOtherInstructions(String otherInstructions) {
        Instruction ins = jo.getInstruction(InstructionType.OTHER);
        if (otherInstructions != null && ins != null) {
            ins.setInstruction(CommonUtil.toStringOrNull(otherInstructions));
        }
    }

    public String getProjectId() {
        Project p = jo.getProject();
        if (p != null) {
            return p.getProjectNumber();
        }
        return "";
    }

    public void setProjectId(String projectNumber) {
        if(projectNumber!=null && projectNumber.trim().length()>0){
            jo.setProjectNumber(CommonUtil.toStringOrNull(projectNumber));
        }
    }

    public List<Field> getProjectTypes() {
        EnumFieldFilter filter=new EnumFieldFilter(){
            @Override
            public boolean accept(EnumField enumField) {
                boolean ok=true;
                if(enumField==ProjectType.TYPE_3){
                    ok=SecurityUtil.isAnyGranted(new String[]{Permission.PROJECT_TYPE_3.permName()});
                }
                return ok;
            }
        };
        
        return refenceDataSerivce.getProjectTypeFields(filter);        
    }
    
    public void setProjectType(String projectType){
        jo.setProjectType(ProjectType.valueOf(projectType));
    }
    
    public String getProjectType() {
        ProjectType t= jo.getProjectType();
        if (t != null) {
            return t.getValue();
        }
        return "";
    }

    public String getScrollFlag() {
        return this.scrollFlag;
    }

    public boolean getViewOnly() {
        return false;
    }

    public boolean getAllowCreateUpdateProject(){
        return !getProjectTypeViewOnly();
    }
    
    public boolean getProjectTypeViewOnly(){//allow user the change the project type in dropdown or not
        boolean viewOnly=getViewOnly();
        Project p=jo.getProject();
        viewOnly=viewOnly //the job is viewOnly
            || (p!=null); //project created -- no change of project type after that 
        return viewOnly;
    }
    
    public String getProjectButtonLabel(){
        String result="Create Project";
        if(getProjectTypeViewOnly()){
            result="Update Project";
        }
        return result;
    }
    
    public CEJobOrder getJobOrder() {
        return jo;
    }

    public CEJobOrderForm(CEJobOrder jobOrder) {
        this.jo = jobOrder;
        if(jo!=null && jo.getRootServiceLevel()!=null && jo.getRootServiceLevel().getChildServiceLevels()!=null && jo.getRootServiceLevel().getChildServiceLevels().size()>0){
          for(JobServiceLevel js : jo.getRootServiceLevel().getChildServiceLevels()) {
              addServiceLevelForm(js);
          }
        }
    }

    public String getInputFieldIdValue() {
        return inputFieldIdValue;
    }

    public void setInputFieldIdValue(String inputFieldIdValue) {
        this.inputFieldIdValue = inputFieldIdValue;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public boolean isCustomerExist() {
        return jo.isCustomerExist();
    }
    
    public boolean isTestSplittable(){
        return jo.isTestSplittable();
    }

    public String getJobNumber() {
        return jo.getJobNumber();
    }

    public String getStatus() {
        if (jo.getStatus() == null)
            return OrderStatus.OPEN.getValue();
        return jo.getStatus().getValue();
    }

    public String getBillingStatus() {
        if (jo.getBillingStatus() == null)
            return BillingStatus.OPEN.getValue();
        return jo.getBillingStatus().getValue();
    }

    public String getServiceLocationName() {
        if (jo.getJobNumber() != null) {
            if (jo.getServiceLocation() != null) {
                return jo.getServiceLocation().getValue();
            }
            return null;
        }
        else {
            return this.serviceLocationName;
        }
    }

    public void setServiceLocationName(String serviceLocationName) {
        this.serviceLocationName = serviceLocationName;
    }

    public String getServiceLocationCode(){
        return jo.getServiceLocationCode();
    }
    
    @NotBlank
    @RegExp(message = "Not Valid", value = "[A-Z,a-z,0-9,' ']*")
    public void setServiceLocationCode(String serviceLocationCode) {
        jo.setServiceLocationCode(CommonUtil.toStringOrNull(serviceLocationCode));
    }

    public String getJobType() {
        return jo.getJobType().name();
    }

    // this field is changed to operation in the screen. does we require this?
    public void setBusinessStreamCode(String bsc) {
        jo.setBusinessStreamCode(CommonUtil.toStringOrNull(bsc));
    }

    // this field is changed to operation in the screen. does we require this?
    public String getBusinessStreamCode() {
        return jo.getBusinessStreamCode();
    }
    
    @NotBlank
    @RegExp(message = "Not Valid", value = "[A-Z,a-z,0-9,' '-]*")
    public void setOperation(String operation) {
        jo.setOperation(CommonUtil.toStringOrNull(operation));
    }
    
    public String getOperation() {
        return jo.getOperation();
    }

    public String getBuName() {
        return jo.getBuName();
       
    }
   
    @NotBlank
    @RegExp(message = "Not Valid", value = "[A-Z,a-z,0-9,' '-]*")
    public void setBuName(String buName) {
        jo.setBuName(CommonUtil.toStringOrNull(buName));
    }

    public String getWarehouseName() {
        return jo.getBranchName();
    }

    @NotBlank
    @RegExp(message = "Not Valid", value = "[A-Z,a-z,0-9,' '-]*")
    public void setWarehouseName(String warehouseName) {
        jo.setBranchName(CommonUtil.toStringOrNull(warehouseName));
    }

    public String getSalesPersonName() {
        return jo.getSalesPersonName();
    }

    @NotBlank
   // @RegExp(message = "Not Valid", value = "[A-Z,a-z,0-9,' ']*")
    public void setSalesPersonName(String salesName) {
        jo.setSalesPersonName(CommonUtil.toStringOrNull(salesName));
    }

    public String getSecondarySalesPersonName() {
        return jo.getSecondarySalesPersonName();
    }

    //@RegExp(message = "Not Valid", value = "[A-Z,a-z,0-9,' ']*")
    public void setSecondarySalesPersonName(String secondarySalesPersonName) {
        jo.setSecondarySalesPersonName(CommonUtil.toStringOrNull(secondarySalesPersonName));
    }

    public String getProjectManagerName() {
        return jo.getProjectManagerName();
    }

    public void setProjectManagerName(String projectManagerName) {
        jo.setProjectManagerName(CommonUtil.toStringOrNull(projectManagerName));
    }

    public boolean getFollowUp() {
        return jo.isFollowUp();
    }

    public void setFollowUp(boolean followUp) {
        jo.setFollowUp(followUp);
    }

    public String getQuoteDate() {
        if (jo.getQuote() != null) {
            return DateUtil.dateToString(jo.getQuote().getQuoteDate());
        }
        else {
            return "";
        }
    }

    public void setQuoteDate(String quoteDate){
        this.qtDate=quoteDate;
        this.formQuoteDate = convertToPhoenixTime(DateUtil.stringToDate(quoteDate));
    }

    public Timestamp getFormQuoteDate() {
        return formQuoteDate;
    }

    public boolean isQuoteDateChanged(){
        if (this.formQuoteDate == null && (jo.getQuote() != null && jo.getQuote().getQuoteDate() != null) // quote date removed
                || (jo.getQuote() == null && this.formQuoteDate != null ) //new quote 
                ||(jo.getQuote() != null && this.formQuoteDate != null && 
                        !this.formQuoteDate.equals(jo.getQuote().getQuoteDate()))) // updated quote date
        {
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getQuoteNumber(){
        if (jo.getQuote() != null){
            return jo.getQuote().getQuoteNumber();
        }
        else{
            return "";
        }
    }
    
    public void setQuoteNumber(String quoteNumber){
        this.formQuoteNumber = quoteNumber;
    }
    
    public String getFormQuoteNumber(){
        return this.formQuoteNumber;
    }
    
    public boolean isQuoteNumberChanged(){
        if( (this.formQuoteNumber == null && (jo.getQuote() != null && jo.getQuote().getQuoteNumber() != null)) // removed quote number 
                || (jo.getQuote() == null && this.formQuoteNumber != null && !"".equals(formQuoteNumber)) // new quote 
                || (jo.getQuote() != null && this.formQuoteNumber != null //updated quote number 
                        && !this.formQuoteNumber.equals(jo.getQuote().getQuoteNumber()))){
            return true;
        }
        else{   
            return false;
        }
    }

    
    public String getEcsOrderDate() {
        if (jo.getQuote() != null) {
            return DateUtil.dateToString(jo.getQuote().getOrderDate());
        }
        else {
            return "";
        }
    }
    
    public String getEcsOrderNumber(){
        if (jo.getQuote() != null){
            return jo.getQuote().getOrderNumber();
        }else{
            return "";
        }
    }

    //this dropdown is changed to operation dropdown in the page. does we need this?
    @SuppressWarnings("unchecked")
    public List<Field> getBusinessStreamCodes() {
        List<String> params = new ArrayList<String>();
        params.add(getJobType());
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("jobOperations", params);
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> getOperations() {
        List<String> params = new ArrayList<String>();
        System.out.println("jobtype in ce joborder form is"+ getJobType());
        params.add(getJobType());
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("jobOperations", params);
    }
    
    

    public String getSourceOrigin() {
        if (jo.getJobContract() != null && jo.getJobContract().getSourceOrigin() != null)
            return jo.getJobContract().getSourceOrigin().getValue();
        else
            return "";
    }

    public void setStatus(String statusCode) {
        OrderStatus[] list = OrderStatus.list();
        for (OrderStatus orderStatus : list) {
            if (statusCode.equals(orderStatus.getValue())) {
                jo.setStatus(orderStatus);
                break;
            }
        }
    }

    public void setBillingStatus(String code) {
        BillingStatus[] list = BillingStatus.list();
        for (BillingStatus billingStatus : list) {
            if (code.equals(billingStatus.getValue())) {
                jo.setBillingStatus(billingStatus);
                break;
            }
        }
    }

    public String getPromiseCompletionDate() {
        return convertToLocalTime(jo.getPromiseCompletionDate());
    }

    public void setPromiseCompletionDate(String promiseCompletionDate) {
        this.promiseCompDate=promiseCompletionDate;     
        this.jo.setPromiseCompletionDate(convertToPhoenixTime(DateUtil.stringToDate(promiseCompletionDate)));
    }

    public String getCustomerReadyDate() {
        return convertToLocalTime(jo.getCustomerReadyDate());
    }

    public void setCustomerReadyDate(String customerReadyDate) {
        this.custReadyDate=customerReadyDate;
        this.jo.setCustomerReadyDate(convertToPhoenixTime(DateUtil.stringToDate(customerReadyDate)));
    }
    
    public String getActReadyDt(){
    	return actReadyDate;
    }
    public String getActualReadyDate() {
        return convertToLocalTime(jo.getActualReadyDate());
    }

    public void setActualReadyDate(String actualReadyDate) {
        this.actReadyDate=actualReadyDate;
        jo.setActualReadyDate(convertToPhoenixTime(DateUtil.stringToDate(actualReadyDate)));
    }

    @SuppressWarnings("unchecked")
    public void setJobOrder(CEJobOrder jo) {
        this.jo = jo;
        if(jo!=null && jo.getRootServiceLevel()!=null && jo.getRootServiceLevel().getChildServiceLevels()!=null && jo.getRootServiceLevel().getChildServiceLevels().size()>0){
            this.products=null;
            ArrayList<JobServiceLevel> al = new ArrayList<JobServiceLevel>();
            for(JobServiceLevel tl:jo.getRootServiceLevel().getChildServiceLevels()){
             al.add(tl);   
            }
            Comparator myComp = new Comparator() {
                public int compare(Object o1, Object o2) {
                    try {
                        JobServiceLevel f1 = (JobServiceLevel) o1;
                        JobServiceLevel f2 = (JobServiceLevel) o2;
                        Long n1 = f1.getId();
                        Long n2 = f2.getId();
                        return n1.compareTo(n2);
                    }
                    catch (Exception e) {
                        // e.printStackTrace();
                    }
                    return -1;
                }
            };
            Collections.sort(al, myComp);
            
            for(JobServiceLevel js : al) {
              addServiceLevelForm(js);
          }
        }
    }

    public String getAddOrDeleteTestLines() {
        return addOrDeleteTestLines;
    }

    public void setAddOrDeleteTestLines(String addOrDeleteTestLines) {
        this.addOrDeleteTestLines = addOrDeleteTestLines;
    }

    public String getAddOrDeleteDepositLines() {
        return addOrDeleteDepositLines;
    }

    public void setAddOrDeleteDepositLines(String addOrDeleteDepositLines) {
        this.addOrDeleteDepositLines = addOrDeleteDepositLines;
    }

    public String getProjectFlag() {
        return projectFlag;
    }

    public void setProjectFlag(String projectFlag) {
        this.projectFlag = projectFlag;
    }

    public int getDepIndex() {
        return depIndex;
    }

    public void setDepIndex(int depIndex) {
        this.depIndex = depIndex;
    }

    public String getAddOrDeleteSplitLines() {
        return addOrDeleteSplitLines;
    }

    public void setAddOrDeleteSplitLines(String addOrDeleteSplitLines) {
        this.addOrDeleteSplitLines = addOrDeleteSplitLines;
    }

    public int getSplitIndex() {
        return splitIndex;
    }

    public void setSplitIndex(int splitIndex) {
        this.splitIndex = splitIndex;
    }

    public String getAddOrDeleteEvent() {
        return addOrDeleteEvent;
    }

    public void setAddOrDeleteEvent(String addOrDeleteEvent) {
        this.addOrDeleteEvent = addOrDeleteEvent;
    }

    public int getTestIndex() {
        return testIndex;
    }

    public void setTestIndex(int testIndex) {
        this.testIndex = testIndex;
    }

    private String warnUserFlag;

    public String getWarnUserFlag() {
        return warnUserFlag;
    }

    public void setWarnUserFlag(String warnUserFlag) {
        this.warnUserFlag = warnUserFlag;
    }

    public void setScrollFlag(String scrollFlag) {
        this.scrollFlag = scrollFlag;
    }

    public String getOperationalStatus() {
        OperationalStatus st = jo.getOperationalStatus();
        if (st == null) {
            return "";
        }
        return st.getValue();
    }
    public void getOperationalStatus(String status) {
        if(!CommonUtil.isNullOrEmpty(status)){
            for(OperationalStatus st:OperationalStatus.values()){
                if(st.getValue().equalsIgnoreCase(status)){
                    jo.setOperationalStatus(st);
                    break;
                }
            }
        }
    }

    
    public CEJobContractForm[] getJobContracts() {
        return jobContractForms;
    }

    public void setJobContracts(CEJobContractForm[] jobContractForms) {
        this.jobContractForms = jobContractForms;
    }

    public String getContractIndex() {
        return contractIndex;
    }

    public void setContractIndex(String contractIndex) {
        this.contractIndex = contractIndex;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public void setDepositOrderLineItems(DepositInvoice[] depositInvoice) {

        Set<DepositInvoice> hs = new HashSet<DepositInvoice>();

        for (DepositInvoice cj : depositInvoice) {
            hs.add(cj);
        }
        this.depositOrderLineItem=depositInvoice;
    }

    public List<DepositInvoice> getDepositOrderLineItems() {
        return SortUtil.sortDepositInvoice(jo.getJobContract().getDepositInvoices());
    }

    
    public CEJobOrderLineItem[] getCejobOrderLineItems() {
        if (jobContractForms != null && jobContractForms.length > 0) {
            return jobContractForms[0].getCejobOrderLineItems();
        }
        else {
            return null;
        }
    }

    public void setCejobOrderLineItems(CEJobOrderLineItem[] ceJobOrderLineItem) {
        if (jobContractForms != null && jobContractForms.length > 0) {
            jobContractForms[0].setCejobOrderLineItems(ceJobOrderLineItem);
        }
    }

    public List<ServiceLevelForm> getProducts() {
        return products;
    }

    public void setProducts(List<ServiceLevelForm> products) {
        this.products = products;
    }

    public void createServiceForm(Set<JobServiceLevel> serviceLevel) {
        products = new ArrayList<ServiceLevelForm>();
        if (serviceLevel != null) {
            for (JobServiceLevel js : serviceLevel) {
                ServiceLevelForm sf = new ServiceLevelForm();
                sf.setProduct(js);
                products.add(sf);
            }
        }
    }

    public void addServiceLevelForm(JobServiceLevel serviceLevel) {
        if (products == null) {
            products = new ArrayList<ServiceLevelForm>();
        }
        if (serviceLevel != null) {
            ServiceLevelForm sf = new ServiceLevelForm();
            sf.setProduct(serviceLevel);
            sf.splitServiceLevelName();
            products.add(sf);
        }
    }

    public ServiceLevelForm getServiceLevelForm(int index) {
        if (products != null && products.size() > index) {
            return products.get(index);
        }
        else
            return null;
    }

    public void removeServiceLevelForm(ServiceLevelForm form) {
        if (products != null && products.size() > 0) {
            products.remove(form);
        }
    }

    public void removeServiceLevelForm(JobServiceLevel serviceLevel) {
        if (products != null && products.size() > 0) {
            for(Iterator<ServiceLevelForm> it=products.iterator(); it.hasNext();){
                ServiceLevelForm form = it.next();
                if(form.getProduct().equals(serviceLevel)){
                    it.remove();
                    break;
                }
            }
        }
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String[] getTestIdList() {
        if (testId != null) {
            String testIds[] = testId.split("~");
            return testIds;
        }
        else {
            return null;
        }
    }

    public int getProdIndex() {
        return prodIndex;
    }

    public void setProdIndex(int prodIndex) {
        this.prodIndex = prodIndex;
    }

    //TODO: need to change the phxProductGroup... as per the new standard
    @SuppressWarnings("unchecked")
    public List<Field> getProductGroupList(){
            List<CEJobOrder> params = new ArrayList<CEJobOrder>();
            params.add(this.getJobOrder());
            return (List<Field>) com.intertek.util.CommonUtil.getDropDown("phxProductGroup", params);
    }

    public List<Field> getProductFields(String groupId) {
        return refenceDataSerivce.getProducts(groupId);
    }
    
    public String getPromiseCompDate() {
        return promiseCompDate;
    }

    public String getCustReadyDate() {
        return custReadyDate;
    }

    public String getActReadyDate() {
        return actReadyDate;
    }

    public String getQtDate() {
        return qtDate;
    }

    public String getProjectManagerFullName() {
        String fullName=jo.getProjectManagerFullName();
        return fullName;
    }

    public void setProjectManagerFullName(String projectManagerFullName) {
        this.projectManagerFullName = projectManagerFullName;
    }

    public void updateForm(){
        if(this.products != null){
            for(ServiceLevelForm serviceLevelForm: products){
                serviceLevelForm.updateForm();
            }
        }
    }
}
