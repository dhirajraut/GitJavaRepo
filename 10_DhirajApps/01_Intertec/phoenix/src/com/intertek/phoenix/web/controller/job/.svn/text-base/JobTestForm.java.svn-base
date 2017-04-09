/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.web.controller.job;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.intertek.entity.Branch;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.Test;
import com.intertek.entity.User;
import com.intertek.phoenix.entity.Attachment;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.entity.Estimation;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.JobTestAttachment;
import com.intertek.phoenix.entity.JobTestNote;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.entity.QuoteLine;
import com.intertek.phoenix.entity.ServiceOffering;

/**
 * 
 * @author richard.qin
 */
public class JobTestForm {
    private JobTest jobTest;
    private JobTestForm[] split;
    
    /**
     * @param related
     * @return
     */
    static public JobTestForm[] buildJobTestForms(Set<JobTest> jobTests) {
        List<JobTestForm> result = new ArrayList<JobTestForm>();
        for(JobTest jobTest: jobTests){
            result.add(new JobTestForm(jobTest));
        }
        Collections.sort(result, new Comparator<JobTestForm>(){
            @Override
            public int compare(JobTestForm one, JobTestForm two) {
                return (int)(one.getJobTest().getLinenumber() - two.getJobTest().getLinenumber());
            }
            
        });
        
        return result.toArray(new JobTestForm[0]);
    }

    public JobTestForm(JobTest jobTest){
        this.jobTest = jobTest;
        // build related JobTests
        split = buildJobTestForms(jobTest.getRelated());
    }

    public JobTest getJobTest() {
        return jobTest;
    }

    public void setJobTest(JobTest jobTest) {
        this.jobTest = jobTest;
    }

    public JobTestForm[] getSplit() {
        return split;
    }

    public void setSplit(JobTestForm[] split) {
        this.split = split;
    }
    
    public Long getId() {
        return jobTest.getId();
    }

    public void setId(Long id) {
        jobTest.setId(id);
    }

    public Long getJobServiceLevelId() {
        return jobTest.getJobServiceLevelId();
    }

    public void setJobServiceLevelId(Long jobServiceLevelId) {
        jobTest.setJobServiceLevelId(jobServiceLevelId);
    }

    public String getTestId() {
        return jobTest.getTestId();
    }

    public void setTestId(String testId) {
        jobTest.setTestId(testId);
    }

    public JobServiceLevel getJobServiceLevel() {
        return jobTest.getJobServiceLevel();
    }

    public void setJobServiceLevel(JobServiceLevel jobServiceLevel) {
        jobTest.setJobServiceLevel(jobServiceLevel);
    }

    public Test getTest() {
        return jobTest.getTest();
    }

    public void setTest(Test test) {
        jobTest.setTest(test);
    }

    public double getQuantity() {
        return jobTest.getQuantity();
    }

    public void setQuantity(double quantity) {
        jobTest.setQuantity(quantity);
    }

    public boolean getOt() {
        return jobTest.getOt();
    }

    public void setOt(boolean ot) {
        jobTest.setOt(ot);
    }

    public double getUnitPrice() {
        return jobTest.getUnitPrice();
    }

    public void setUnitPrice(double unitPrice) {
        jobTest.setUnitPrice(unitPrice);
    }

    public String getLineDescription() {
        return jobTest.getLineDescription();
    }

    public void setLineDescription(String lineDescription) {
        jobTest.setLineDescription(lineDescription);
    }

    public String getContractRefNo() {
        return jobTest.getContractRefNo();
    }

    public void setContractRefNo(String contractRefNo) {
        jobTest.setContractRefNo(contractRefNo);
    }

    public boolean isManualTest() {
        return jobTest.isManualTest();
    }

    public Set<JobContractTest> getJobContractTests() {
        return jobTest.getJobContractTests();
    }

    public boolean addJobContractTest(JobContractTest jct){
        return jobTest.addJobContractTest(jct);
    }

    public boolean removeJobContractTest(JobContractTest jct){
        return jobTest.removeJobContractTest(jct);
    }
    
    public void setQuotedAmount(double amount){
        jobTest.setQuotedAmount(amount);
    }

    public double getQuotedAmount() {
        return jobTest.getQuotedAmount();
    }

    public String getSampleDescription() {
        return jobTest.getSampleDescription();
    }

    public void setSampleDescription(String sampleDescription) {
        jobTest.setSampleDescription(sampleDescription);
    }

    public Timestamp getStartDate() {
        return jobTest.getStartDate();
    }

    public void setStartDate(Timestamp startDate) {
        jobTest.setStartDate(startDate);
    }

    public Timestamp getEndDate() {
        return jobTest.getEndDate();
    }

    public void setEndDate(Timestamp endDate) {
        jobTest.setEndDate(endDate);
    }

    public Timestamp getTaskReadyDate() {
        return jobTest.getTaskReadyDate();
    }

    public void setTaskReadyDate(Timestamp taskReadyDate) {
        jobTest.setTaskReadyDate(taskReadyDate);
    }

    public String getBranchName() {
        return jobTest.getBranchName();
    }

    public void setBranchName(String branchName) {
        jobTest.setBranchName(branchName);
    }

    public Branch getBranch() {
        return jobTest.getBranch();
    }

    public void setBranch(Branch branch) {
        jobTest.setBranch(branch);
    }

    public String getModelNumber() {
        return jobTest.getModelNumber();
    }

    public void setModelNumber(String modelNumber) {
        jobTest.setModelNumber(modelNumber);
    }

    public String getTaskManagerId() {
        return jobTest.getTaskManagerId();
    }

    public void setTaskManagerId(String taskManagerId) {
        jobTest.setTaskManagerId(taskManagerId);
    }

    public Employee getTaskManager() {
        return jobTest.getTaskManager();
    }

    public void setTaskManager(Employee taskManager) {
        jobTest.setTaskManager(taskManager);
    }

    public String getCreditOverrideById() {
        return jobTest.getCreditOverrideById();
    }

    public void setCreditOverrideById(String creditOverrideById) {
        jobTest.setCreditOverrideById(creditOverrideById);
    }

    public User getCreditOverrideBy() {
        return jobTest.getCreditOverrideBy();
    }

    public void setCreditOverrideBy(User creditOverrideBy) {
        jobTest.setCreditOverrideBy(creditOverrideBy);
    }

    public String getServiceLocationCode() {
        return jobTest.getServiceLocationCode();
    }

    public void setServiceLocationCode(String serviceLocationCode) {
        jobTest.setServiceLocationCode(serviceLocationCode);
    }

    public ServiceLocation getServiceLocation() {
        return jobTest.getServiceLocation();
    }

    public void setServiceLocation(ServiceLocation serviceLocation) {
        jobTest.setServiceLocation(serviceLocation);
    }

    public BillingStatus getBillingStatus() {
        return jobTest.getBillingStatus();
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        jobTest.setBillingStatus(billingStatus);
    }

    public OperationalStatus getOperationalStatus() {
        return jobTest.getOperationalStatus();
    }

    public void setOperationalStatus(OperationalStatus operationalStatus) {
        jobTest.setOperationalStatus(operationalStatus);
    }

    public double getForcastedRevenue() {
        return jobTest.getForcastedRevenue();
    }

    public void setForcastedRevenue(double forcastedRevenue) {
        jobTest.setForcastedRevenue(forcastedRevenue);
    }

    public String getUom() {
        return jobTest.getUom();
    }

    public void setUom(String uom) {
        jobTest.setUom(uom);
    }

    public Long getPoId() {
        return jobTest.getPoId();
    }

    public void setPoId(Long poId) {
        jobTest.setPoId(poId);
    }

    public PurchaseOrder getPurchaseOrder() {
        return jobTest.getPurchaseOrder();
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        jobTest.setPurchaseOrder(purchaseOrder);
    }

    public Long getQuoteLineId() {
        return jobTest.getQuoteLineId();
    }

    public void setQuoteLineId(Long quoteLineId) {
        jobTest.setQuoteLineId(quoteLineId);
    }

    public QuoteLine getQuoteLine() {
        return jobTest.getQuoteLine();
    }

    public void setQuoteLine(QuoteLine quoteLine) {
        jobTest.setQuoteLine(quoteLine);
    }

    public Long getServiceOfferingId() {
        return jobTest.getServiceOfferingId();
    }

    public void setServiceOfferingId(Long serviceOfferingId) {
        jobTest.setServiceOfferingId(serviceOfferingId);
    }

    public ServiceOffering getServiceOffering() {
        return jobTest.getServiceOffering();
    }

    public void setServiceOffering(ServiceOffering serviceOffering) {
        jobTest.setServiceOffering(serviceOffering);
    }

    public double getFundedAmount() {
        return jobTest.getFundedAmount();
    }

    public void setFundedAmount(double fundedAmount) {
        jobTest.setFundedAmount(fundedAmount);
    }

    public Long getMasterId() {
        return jobTest.getMasterId();
    }

    public void setMasterId(Long masterId) {
        jobTest.setMasterId(masterId);
    }

    public JobTest getMaster() {
        return jobTest.getMaster();
    }

    public void setMaster(JobTest master) {
        jobTest.setMaster(master);
    }

    public Set<Estimation> getEstimations() {
        return jobTest.getEstimations();
    }
    
    public boolean addEstimation(Estimation est){
        return jobTest.addEstimation(est);
    }
    
    public boolean removeEstimation(Estimation est){
        return jobTest.removeEstimation(est);
    }

    public Set<JobTest> getRelated() {
        return jobTest.getRelated();
    }
    
    public boolean addRelated(JobTest jobTest){
        return jobTest.addRelated(jobTest);
    }
    
    public boolean removeRelated(JobTest jobTest){
        return jobTest.removeRelated(jobTest);
    }

    public boolean isInvoiceable() {
        return jobTest.isInvoiceable();
    }

    public void setInvoiceable(boolean invoiceable) {
        jobTest.setInvoiceable(invoiceable);
    }

    public Set<JobTestNote> getNotes() {
        return jobTest.getNotes();
    }

    public boolean addNote(JobTestNote note) {
        return jobTest.addNote(note);
    }

    public boolean removeNote(JobTestNote note) {
        return jobTest.removeNote(note);
    }

    public Set<JobTestAttachment> getAttachments() {
        return jobTest.getAttachments();
    }
    
    public boolean addAttachment(JobTestAttachment attachment) {
        return jobTest.addAttachment(attachment);
    }

    public boolean removeAttachment(Attachment attachment) {
        return jobTest.removeAttachment(attachment);
    }

    public long getLinenumber() {
        return jobTest.getLinenumber();
    }

    public void setLinenumber(long linenumber) {
        jobTest.setLinenumber(linenumber);
    }

    public String getUpdateFlag() {
        return jobTest.getUpdateFlag();
    }

    public void setUpdateFlag(String updateFlag) {
        jobTest.setUpdateFlag(updateFlag);
    }

    public boolean isNotRelated(){
        return jobTest.isNotRelated();
    }
    
    public String getServiceOfferingName(){
        if(jobTest.getServiceOffering() != null){
            return jobTest.getServiceOffering().getName();
        }
        else {
            return "";
        }
    }
    
    public String getPoNumber(){
        if(jobTest.getPurchaseOrder()!=null){
            return jobTest.getPurchaseOrder().getPoNumber();
        }
        else {
            return "";
        }
    }
}
