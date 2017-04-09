/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author eric.nguyen
 */
@Entity(name = "com.intertek.phoenix.entity.Employee")
@Table(name = "PHX_EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_ID", length = 11)
    private String employeeId;

    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @Column(name = "SAM_ACCOUNT", length = 30)
    private String samAccount;

    @Column(name = "BUSINESS_UNIT", length = 5)
    private String businessUnit;

    @Column(name = "OPERATING_UNIT", length = 10)
    private String operatingUnit;

    @Column(name = "UPDATE_DATE", columnDefinition = "date default sysdate")
    private Timestamp updateDate;

    @Column(name = "STATUS", columnDefinition = "char(1) default 'A'")
    private char status;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSamAccount() {
        return samAccount;
    }

    public void setSamAccount(String samAccount) {
        this.samAccount = samAccount;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(String operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getFullName(){
        return firstName+" "+lastName+" (BU="+businessUnit+")";
    }
}
