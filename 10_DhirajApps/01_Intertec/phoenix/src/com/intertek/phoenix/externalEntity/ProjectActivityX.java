/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.Date;

import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.ServiceOffering;
import com.intertek.util.Constants;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */

public class ProjectActivityX {
    private long jobTestId;
    private String businessUnit;
    private String jobNumber;
    private String jobLineNumber;
    private String jobLineDescription;
    private String jobLineDetailDescription;
    private String jobLineStatus;
    private Date jobLineStatusEffDt;
    private String operatingUnit;
    private String departmentId;
    private String product;
    private String serviceOffering;
    private Date chartfieldEffDt;
    private String msgAction;

    public ProjectActivityX() {

    }

    public ProjectActivityX(String bu, String jobNumber, JobTest jt) {
        this.jobTestId = jt.getId();
        this.businessUnit = bu;
        this.jobNumber = jobNumber;

        this.jobLineNumber = jt.getLinenumber() + "";
        this.jobLineDescription = jt.getTestId();
        this.jobLineDetailDescription = jt.getLineDescription();
        OperationalStatus opStatus = jt.getOperationalStatus();
        if (opStatus == null) {
            this.jobLineStatus = "P"; // opertional status (Billing status - Rafiq)
        }
        else {
            this.jobLineStatus = opStatus.getValue();
        }

        this.jobLineStatusEffDt = null;
        this.operatingUnit = jt.getBranchName();
        this.departmentId = "";// TODO:
        
        this.product = getProductId(jt.getJobServiceLevel()); // "AGOTHR"; product group id
        
        this.product="AGOTHR"; //TODO : TEST ONLY
        
        ServiceOffering x=jt.getServiceOffering(); 
        this.serviceOffering ="";
        if(x!=null){
            this.serviceOffering=x.getCode().toUpperCase();
        }

        this.chartfieldEffDt = null;
        this.msgAction = "C";
        String updateFlag = jt.getUpdateFlag();
        if (updateFlag != null && (updateFlag.equalsIgnoreCase(Constants.UPDATE) || updateFlag.equalsIgnoreCase(Constants.SENT))) {
            this.msgAction = "U";
        }
    }

    private String getProductId(JobServiceLevel jsl) {
        if (jsl == null) {
            return null;
        }
        String productId = "";
        String slName = jsl.getServiceLevelName() + "";
        String[] tokens = slName.split("/");
        if (tokens != null && tokens.length > 1) {
            productId = tokens[1];
        }
        return productId;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobLineNumber() {
        return jobLineNumber;
    }

    public void setJobLineNumber(String jobLineNumber) {
        this.jobLineNumber = jobLineNumber;
    }

    public String getJobLineDescription() {
        return jobLineDescription;
    }

    public void setJobLineDescription(String jobLineDescription) {
        this.jobLineDescription = jobLineDescription;
    }

    public String getJobLineDetailDescription() {
        return jobLineDetailDescription;
    }

    public void setJobLineDetailDescription(String jobLineDetailDescription) {
        this.jobLineDetailDescription = jobLineDetailDescription;
    }

    public String getJobLineStatus() {
        return jobLineStatus;
    }

    public void setJobLineStatus(String jobLineStatus) {
        this.jobLineStatus = jobLineStatus;
    }

    public Date getJobLineStatusEffDt() {
        return jobLineStatusEffDt;
    }

    public void setJobLineStatusEffDt(Date jobLineStatusEffDt) {
        this.jobLineStatusEffDt = jobLineStatusEffDt;
    }

    public String getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(String operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(String serviceOffering) {
        this.serviceOffering = serviceOffering;
    }

    public Date getChartfieldEffDt() {
        return chartfieldEffDt;
    }

    public void setChartfieldEffDt(Date chartfieldEffDt) {
        this.chartfieldEffDt = chartfieldEffDt;
    }

    public String getMsgAction() {
        return msgAction;
    }

    public void setMsgAction(String msgAction) {
        this.msgAction = msgAction;
    }

    public long getJobTestId() {
        return jobTestId;
    }

    public void setJobTestId(long jobTestId) {
        this.jobTestId = jobTestId;
    }

}
