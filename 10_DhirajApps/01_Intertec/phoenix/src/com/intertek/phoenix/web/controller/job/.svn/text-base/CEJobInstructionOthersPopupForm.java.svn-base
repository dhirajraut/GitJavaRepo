/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.sql.Timestamp;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.EnumField;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.entity.Estimation;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.util.DateUtil;


/**
 * The Class CEJobInstructionOthersPopupForm.
 * 
 * @author Patni
 */

// TODO: "many fields on this page do not belong to CEJobOrderLineItem, instead,
// they belong to CEJobTest". Change it accordingly
public class CEJobInstructionOthersPopupForm extends Form {
    @CascadeValidation
    private String divName;
    private String divNameBody;
    double fundedAmount;
    private String workertimeFlag;
    private String addOrDeleteTestLines;
    private String testIndex;
    private String projectFlag;
    private String depIndex;
    private String addOrDeleteDepositLines;
    private String addOrDeleteSplitLines;
    private String productIndex;
    private String splitIndex;
    private String buName;
    private String serviceType;
    double customerPoAmount;
    private Estimation[] estimation;
    private int rowNum;
    private int lineItemNo;
    private List<Field> billingStatusList;
    private List<Field> operationalStatusList;
    private List<Field> orderStatusList;
    private List<Field> userTypeList;
    private List<Field> serviceTypeList;
    private List<EnumField> serviceOfferingList;
    private String estimationId;

    private JobTest jobTest;
    private CEJobInstructionEstimationForm[] estimationForm;
    private String taskReadyDate1;
    private String endDate1;
    private String startDate1;
    private String purchaseOrderNo;
    private String taskManagerFullName;
    
    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public Estimation[] getEstimation() {
        int size = jobTest.getEstimations().size();
        Estimation result[] = jobTest.getEstimations().toArray(new Estimation[size]);
        Estimation tmpEst = null;
        for (int i = result.length - 1; i >= 0; i--) {
            for (int k = i - 1; k >= 0; k--) {
                if (result[k].getId() > result[i].getId()) {
                    tmpEst = result[i];
                    result[i] = result[k];
                    result[k] = tmpEst;
                }
            }
        }
        return result;
    }

    public void setEstimation(Estimation[] estimation) {
        this.estimation = estimation;
    }

    public void setJobTest(JobTest jobTest) {
        this.jobTest = jobTest;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getDivNameBody() {
        return divNameBody;
    }

    public void setDivNameBody(String divNameBody) {
        this.divNameBody = divNameBody;
    }

    public String getDateFormat() {
        return Form.getCurrentUserFormat();
    }

    public String getWorkertimeFlag() {
        return workertimeFlag;
    }

    public void setWorkertimeFlag(String workertimeFlag) {
        this.workertimeFlag = workertimeFlag;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(int lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getAddOrDeleteTestLines() {
        return addOrDeleteTestLines;
    }

    public void setAddOrDeleteTestLines(String addOrDeleteTestLines) {
        this.addOrDeleteTestLines = addOrDeleteTestLines;
    }

    public String getTestIndex() {
        return testIndex;
    }

    public void setTestIndex(String testIndex) {
        this.testIndex = testIndex;
    }

    public String getProjectFlag() {
        return projectFlag;
    }

    public void setProjectFlag(String projectFlag) {
        this.projectFlag = projectFlag;
    }

    public String getDepIndex() {
        return depIndex;
    }

    public void setDepIndex(String depIndex) {
        this.depIndex = depIndex;
    }

    public String getAddOrDeleteDepositLines() {
        return addOrDeleteDepositLines;
    }

    public void setAddOrDeleteDepositLines(String addOrDeleteDepositLines) {
        this.addOrDeleteDepositLines = addOrDeleteDepositLines;
    }

    public String getAddOrDeleteSplitLines() {
        return addOrDeleteSplitLines;
    }

    public void setAddOrDeleteSplitLines(String addOrDeleteSplitLines) {
        this.addOrDeleteSplitLines = addOrDeleteSplitLines;
    }

    public String getSplitIndex() {
        return splitIndex;
    }

    public void setSplitIndex(String splitIndex) {
        this.splitIndex = splitIndex;
    }

    public void setFundedAmount(double fundedAmount) {
        jobTest.setFundedAmount(fundedAmount);
    }

    public double getFundedAmount() {
        return jobTest.getFundedAmount();
    }

    public List<Field> getBillingStatusList() {
        return billingStatusList;
    }

    public void setBillingStatusList(List<Field> billingStatusList) {
        this.billingStatusList = billingStatusList;
    }

    public List<Field> getOperationalStatusList() {
        return operationalStatusList;
    }

    public void setOperationalStatusList(List<Field> operationalStatusList) {
        this.operationalStatusList = operationalStatusList;
    }

    public List<Field> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<Field> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public List<Field> getUserTypeList() {
        return userTypeList;
    }

    public void setUserTypeList(List<Field> userTypeList) {
        this.userTypeList = userTypeList;
    }

    public List<Field> getServiceTypeList() {
        return serviceTypeList;
    }

    public void setServiceTypeList(List<Field> serviceTypeList) {
        this.serviceTypeList = serviceTypeList;
    }

    public String getStartDate() {
        if (jobTest.getStartDate() == null) {
            return "";
        }
        return convertToLocalTime(jobTest.getStartDate());

    }

    
    public void setStartDate(String startDate) throws Exception {
        this.startDate1 = startDate;
        if (startDate != null && !"".equals(startDate)) {
            Timestamp stringToDate = DateUtil.stringToDate(startDate);
            //jobTest.setStartDate(convertToPhoenixTime(stringToDate));
            jobTest.setStartDate(stringToDate);
        }
        else {
            jobTest.setStartDate(null);
        }
    }

    public String getEndDate() {

        if (jobTest.getEndDate() == null) {
            return "";
        }
        return convertToLocalTime(jobTest.getEndDate());
    }

    
    public void setEndDate(String endDate) throws Exception {
        this.endDate1 = endDate;
        if (endDate != null && !"".equals(endDate)) {
            Timestamp stringToDate = DateUtil.stringToDate(endDate);
            if (stringToDate == null) {
                //throw new Exception("Type miss match");
            }
            //jobTest.setEndDate(convertToPhoenixTime(stringToDate));
            jobTest.setEndDate(stringToDate);
        }
        else {
            jobTest.setEndDate(null);
        }
    }

    public String getTaskReadyDate() {

        if (jobTest.getTaskReadyDate() == null) {
            return "";
        }
        return convertToLocalTime(jobTest.getTaskReadyDate());
    }

    @SuppressWarnings("deprecation")
    public void setTaskReadyDate(String taskReadyDate) throws Exception {
        this.taskReadyDate1 = taskReadyDate;
        if (taskReadyDate != null && !"".equals(taskReadyDate)) {
            Timestamp readyDate = DateUtil.stringToDate(taskReadyDate);
            if (readyDate == null) {
                //throw new Exception("Type miss match");
            }
            //jobTest.setTaskReadyDate(convertToPhoenixTime(readyDate));
            jobTest.setTaskReadyDate(readyDate);
        }
        else {
            jobTest.setTaskReadyDate(null);
        }
    }

    public String getWarehouseName() {
        return jobTest.getBranchName();
    }

    //@RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    public void setWarehouseName(String warehouseName) {
        jobTest.setBranchName(warehouseName);
    }

    public String getSampleDescription() {
        return jobTest.getSampleDescription();
    }

    @NotBlank(errorCode = "not.blank")
    public void setSampleDescription(String sampleDescription) {
        jobTest.setSampleDescription(sampleDescription);
    }

    public String getServiceType() {
        return serviceType;
        // return ceJobTest.getS();

    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
        // ceJobOrderLineItem.setServiceType(serviceType);
    }

    public String getServiceLocationCode() {
        return jobTest.getServiceLocationCode();
        // return ceJobOrderLineItem.getServiceLocationCode();

    }

    @NotBlank(errorCode = "not.blank")
    //    @RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    public void setServiceLocationCode(String serviceLocationCode) {
        if (serviceLocationCode != null) {
            jobTest.setServiceLocationCode(serviceLocationCode.trim());
        }
        else {
            jobTest.setServiceLocationCode(null);
        }

        // ceJobOrderLineItem.setServiceLocationCode(serviceLocationCode);
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    @RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getBillingstatus() {
        if (jobTest != null && jobTest.getBillingStatus() != null) {
            return jobTest.getBillingStatus().getValue();
        }
        else {
            return BillingStatus.OPEN.getValue();
        }
    }

    //@RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    public void setBillingstatus(String billingstatus) {
        if(!CommonUtil.isNullOrEmpty(billingstatus)){
        for (BillingStatus bs : BillingStatus.values()) {
            if (bs.getValue().equals(billingstatus)) {
                jobTest.setBillingStatus(bs);
            }
        }
        }else{
            jobTest.setBillingStatus(BillingStatus.OPEN);
        }
    }

    public String getOperationalStatus() {
        if (jobTest != null && jobTest.getOperationalStatus() != null) {
            return jobTest.getOperationalStatus().getValue();
        }
        else {
            return OperationalStatus.Unscheduled.getValue();
        }
    }

    public void setOperationalStatus(String operationalStatus) {
        for (OperationalStatus os : OperationalStatus.values()) {
            if (os.getValue().equals(operationalStatus)) {
                jobTest.setOperationalStatus(os);
            }
        }
    }

    public String getTaskManagerId() {
        return jobTest.getTaskManagerId();
        // return "";

    }

    //    @RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    public void setTaskManagerId(String taskManagerId) {
        jobTest.setTaskManagerId(taskManagerId);
    }

    public String getCreditOverrideById() {
        return jobTest.getCreditOverrideById();
    }

    //    @RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    public void setCreditOverrideById(String creditOverrideById) {
        jobTest.setCreditOverrideById(creditOverrideById);
    }

    public String getModelNumber() {
        return jobTest.getModelNumber();

    }

    @NotBlank(errorCode = "not.blank")
    //    @RegExp(errorCode = "not.valid", message = "Not Valid", value = "[A-Z,a-z,0-9]*")
    public void setModelNumber(String modelNumber) {
        jobTest.setModelNumber(modelNumber);
    }

    public String getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(String productIndex) {
        this.productIndex = productIndex;
    }

    public JobTest getJobTest() {
        return jobTest;
    }

    public double getCustomerPoAmount() {
        return customerPoAmount;
    }

    public void setCustomerPoAmount(double customerPoAmount) {
        this.customerPoAmount = customerPoAmount;
        //jobTest.setCustomerPoAmount(customerPoAmount);

    }

    public List<EnumField> getServiceOfferingList() {
        return serviceOfferingList;
    }

    public void setServiceOfferingList(List<EnumField> serviceOfferingList) {
        this.serviceOfferingList = serviceOfferingList;
    }

    public long getServiceOfferingId() {
        if (jobTest != null && jobTest.getServiceOfferingId() != null) {
            return jobTest.getServiceOfferingId().longValue();
        }
        return 0l;
    }

    public void setServiceOfferingId(long serviceofferingId) {
        if (jobTest != null && serviceofferingId > 0) {
            jobTest.setServiceOfferingId(new Long(serviceofferingId));
        }
    }

    public CEJobInstructionEstimationForm[] getEstimationForm() {
        if (estimation != null && estimation.length > 0) {
            CEJobInstructionEstimationForm[] estimationForm = new CEJobInstructionEstimationForm[estimation.length];
            for (int i = 0; i < estimation.length; i++) {
                estimationForm[i] = new CEJobInstructionEstimationForm();
                estimationForm[i].setEstimation(estimation[i]);
            }
            this.estimationForm = estimationForm;
            return estimationForm;
        }
        return null;
    }

    public void setEstimationForm(CEJobInstructionEstimationForm[] estimationForm) {
        this.estimationForm = estimationForm;
    }

    public String getTaskReadyDate1() {
        return taskReadyDate1;
    }

    public String getEndDate1() {
        return endDate1;
    }

    public String getStartDate1() {
        return startDate1;
    }

    public String getEstimationId() {
        return estimationId;
    }

    public void setEstimationId(String estimationId) {
        this.estimationId = estimationId;
    }

    public String getServiceLocationName() {
        if(jobTest.getServiceLocation() != null){
            return jobTest.getServiceLocation().getValue();
        }
        return null;
    }

    public String getTaskManagerFullName() {
        Employee tm=jobTest.getTaskManager();
        if(tm!=null){
            return tm.getFullName();
        }
        return "";
    }

    public void setTaskManagerFullName(String taskManagerFullName) {
        this.taskManagerFullName = taskManagerFullName;
    }

}
