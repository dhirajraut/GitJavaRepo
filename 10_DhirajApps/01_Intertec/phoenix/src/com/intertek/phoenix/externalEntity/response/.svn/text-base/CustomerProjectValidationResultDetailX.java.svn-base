package com.intertek.phoenix.externalEntity.response;

import com.intertek.phoenix.entity.JobTest;

public class CustomerProjectValidationResultDetailX {
    private String lineNumber;
    private String testStandardId;
    private String testStandardDescription;

    public CustomerProjectValidationResultDetailX(){
        
    }
    
    public CustomerProjectValidationResultDetailX(JobTest jt){
        this.lineNumber = jt.getLinenumber() + "";
        this.testStandardId = jt.getTestId();
        this.testStandardDescription = jt.getLineDescription();

    }
    
    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getTestStandardId() {
        return testStandardId;
    }

    public void setTestStandardId(String testStandardId) {
        this.testStandardId = testStandardId;
    }

    public String getTestStandardDescription() {
        return testStandardDescription;
    }

    public void setTestStandardDescription(String testStandardDescription) {
        this.testStandardDescription = testStandardDescription;
    }
}
