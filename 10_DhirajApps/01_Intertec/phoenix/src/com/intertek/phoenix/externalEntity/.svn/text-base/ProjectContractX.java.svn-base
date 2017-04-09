/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.intertek.entity.JobOrder;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.Project;
import com.intertek.phoenix.esb.Logable;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */

public class ProjectContractX implements Logable {
    private String businessUnit;
    private String jobNumber;
    private String jobDescription;
    private String jobDescriptionDetail;
    private String projectType;
    private String customerId;
    private String projectManager;
    private Date projectManagerEffDt;
    private String operatingUnit;
    private String product;
    private String serviceOffering;
    private Date charfieldtEffDt;
    private String jobStatus;
    private Date jobStatusEffDt;
    private String msgAction;

    private List<ProjectActivityX> projectActivities;

    public ProjectContractX() {

    }

    public ProjectContractX(JobOrder jo) {
        this.businessUnit=jo.getBuName();
        this.jobNumber=jo.getJobNumber();
        this.jobDescription=jo.getJobDescription();
        this.jobDescriptionDetail=jo.getJobDescription();

        //TODO: need to add project type to OC&A job table
        //this.projectType=jo.getProjectType().getType();

        this.customerId=""; //per Rafiq we dont' send customer for OC&A        
        this.projectManager="US10538";//TODO: jo.getProjectManagerName(); //Should be employee ID
        this.projectManagerEffDt=null;  //Skip
        this.operatingUnit=jo.getBranchName();
        
        //TODO: 
        this.product="AGOTHR"; //TODO:;//Product Group Id -- in CE only one product group
        this.jobStatus=jo.getJobStatus();
        this.msgAction="C";
        
        //TODO: need project object in OC&A job
        //Project project=jo.getProject();
        //if(project!=null && !(project.getProjectNumber()+"").trim().isEmpty()){
            //this.msgAction="U";
        //}        
    }
    
    public ProjectContractX(CEJobOrder jo) {
        this.businessUnit=jo.getBu().getName();
        this.jobNumber=jo.getJobNumber();
        this.jobDescription=jo.getModelNumber();//Model number
        this.jobDescriptionDetail=jo.getDescription();
        this.projectType=jo.getProjectType().getType();
        this.customerId=jo.getJobContract().getCustomerCode();
        this.projectManager="US10538";//TODO: jo.getProjectManagerName(); //Should be employee ID
        //this.projectManagerEffDt=null;  //Skip
        this.operatingUnit=jo.getBranchName();
        this.product="AGOTHR"; //TODO:;//Product Group Id -- in CE only one product group
        //this.serviceOffering="TESTCF2";//TODO: Skip -- it is required in the xsd
        //this.charfieldtEffDt=null;//Skip
        this.jobStatus=jo.getStatus().getValue();
        //this.jobStatusEffDt=null;//Skip
        this.msgAction="C";
        Project project=jo.getProject();
        if(project!=null && !(project.getProjectNumber()+"").trim().isEmpty()){
            this.msgAction="U";
        }

        this.projectActivities = new ArrayList<ProjectActivityX>();
        
        Set<JobTest> jobTests=jo.getRootServiceLevel().getJobTests();
        addProjectActivities(this.businessUnit, this.jobNumber, jobTests);
        
        Collection<JobServiceLevel> jsls=jo.getRootServiceLevel().getChildServiceLevels();
        for(JobServiceLevel level:jsls){
            jobTests=level.getJobTests();
            addProjectActivities(this.businessUnit, this.jobNumber, jobTests);
        }
    }

    public void addProjectActivities(String bu, String jobNumber, Set<JobTest> jobTests) {
        for(JobTest jt:jobTests){
            this.projectActivities.add(new ProjectActivityX(bu, jobNumber, jt));
        }
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDescriptionDetail() {
        return jobDescriptionDetail;
    }

    public void setJobDescriptionDetail(String jobDescriptionDetail) {
        this.jobDescriptionDetail = jobDescriptionDetail;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public Date getProjectManagerEffDt() {
        return projectManagerEffDt;
    }

    public void setProjectManagerEffDt(Date projectManagerEffDt) {
        this.projectManagerEffDt = projectManagerEffDt;
    }

    public String getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(String operatingUnit) {
        this.operatingUnit = operatingUnit;
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

    public Date getCharfieldtEffDt() {
        return charfieldtEffDt;
    }

    public void setCharfieldtEffDt(Date charfieldtEffDt) {
        this.charfieldtEffDt = charfieldtEffDt;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getJobStatusEffDt() {
        return jobStatusEffDt;
    }

    public void setJobStatusEffDt(Date jobStatusEffDt) {
        this.jobStatusEffDt = jobStatusEffDt;
    }

    public String getMsgAction() {
        return msgAction;
    }

    public void setMsgAction(String msgAction) {
        this.msgAction = msgAction;
    }

    public List<ProjectActivityX> getProjectActivities() {
        return projectActivities;
    }

    public void setProjectActivities(List<ProjectActivityX> projectActivities) {
        this.projectActivities = projectActivities;
    }

    @Override
    public String getId() {
        return getJobNumber();
    }
}
