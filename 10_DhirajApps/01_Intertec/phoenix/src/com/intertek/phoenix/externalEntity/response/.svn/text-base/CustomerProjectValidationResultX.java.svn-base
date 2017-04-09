package com.intertek.phoenix.externalEntity.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobTest;

public class CustomerProjectValidationResultX {
    private String jobNumber;
    private String jobDescription;
    private List<CustomerProjectValidationResultDetailX> tests;

    public CustomerProjectValidationResultX(){
        
    }
    
    public CustomerProjectValidationResultX(CEJobOrder jo){
        this.jobNumber=jo.getJobNumber();
        this.jobDescription=jo.getDescription();
        
        this.tests = new ArrayList<CustomerProjectValidationResultDetailX>();
        
        Set<JobTest> jobTests=jo.getRootServiceLevel().getJobTests();
        addDetails(jobTests);
        
        Collection<JobServiceLevel> jsls=jo.getRootServiceLevel().getChildServiceLevels();
        for(JobServiceLevel level:jsls){
            jobTests=level.getJobTests();
            addDetails(jobTests);
        }
        
    }

    public void addDetails(Set<JobTest> jobTests) {
        for(JobTest jt:jobTests){
            this.tests.add(new CustomerProjectValidationResultDetailX(jt));
        }
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

    public List<CustomerProjectValidationResultDetailX> getTests() {
        return tests;
    }

    public void setTests(List<CustomerProjectValidationResultDetailX> tests) {
        this.tests = tests;
    }
}
