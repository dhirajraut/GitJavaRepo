package com.intertek.phoenix.entity;

/**
 * This class holds GLCode related information.
 *
 * @author lily.sun
 */

public class GLCode {

    private String account;
    private String deptId;
    private String serviceDescription;

    public GLCode() {
        super();
    }

    public GLCode(String account, String deptId, String serviceDescription) {
        this.account = account;
        this.deptId = deptId;
        this.serviceDescription = serviceDescription;
    }

    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getDeptId() {
        return deptId;
    }
    
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    
    public String getServiceDescription() {
        return serviceDescription;
    }
    
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}


