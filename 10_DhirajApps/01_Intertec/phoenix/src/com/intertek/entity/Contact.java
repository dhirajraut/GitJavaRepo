package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import com.intertek.phoenix.entity.Collectable;

/**
 * Contact generated by hbm2java
 */
public class Contact  implements java.io.Serializable, Collectable{

    // Fields    

     private long id;
     private long finContactId;
     @NotBlank @Length(min=0, max=128) private String firstName;
     @NotBlank @Length(min=0, max=128) private String lastName;
     @Length(min=0, max=1) private String status;
     @Length(min=0, max=32) private String title;
     @Length(min=0, max=30) private String fax;
     @Email @Length(min=0, max=70) private String workEmail;
     @Email @Length(min=0, max=70) private String personalEmail;
     @Length(min=0, max=64) private String workPhone;
     @Length(min=0, max=64) private String personalPhone;
     @Length(min=0, max=64) private String cellPhone;
     @Length(min=0, max=8) private String salutationCd;
     @Length(min=0, max=8) private String contactFlag;
     @Length(min=0, max=8) private String prefCommunication;
     @Length(min=0, max=8) private String newFlag;
     @Length(min=0, max=8) private String updateFlag;
     @Length(min=0, max=8) private String updateLimsFlag;
     private Date createdTime;
     @Length(min=0, max=128) private String creByUserName;
     private Date updatedTime;
     @Length(min=0, max=128) private String modByUserName;
     private Set<ContactCust> contactCusts = new HashSet<ContactCust>(0);

     // Constructors

    /** default constructor */
    public Contact() {
    }

	/** minimal constructor */
    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /** full constructor */
    public Contact(long finContactId, String firstName, String lastName, String status, String title, String fax, String workEmail, String personalEmail, String workPhone, String personalPhone, String cellPhone, String salutationCd, String contactFlag, String prefCommunication, String newFlag, String updateFlag, String updateLimsFlag, Date createdTime, String creByUserName, Date updatedTime, String modByUserName, Set<ContactCust> contactCusts) {
       this.finContactId = finContactId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.status = status;
       this.title = title;
       this.fax = fax;
       this.workEmail = workEmail;
       this.personalEmail = personalEmail;
       this.workPhone = workPhone;
       this.personalPhone = personalPhone;
       this.cellPhone = cellPhone;
       this.salutationCd = salutationCd;
       this.contactFlag = contactFlag;
       this.prefCommunication = prefCommunication;
       this.newFlag = newFlag;
       this.updateFlag = updateFlag;
       this.updateLimsFlag = updateLimsFlag;
       this.createdTime = createdTime;
       this.creByUserName = creByUserName;
       this.updatedTime = updatedTime;
       this.modByUserName = modByUserName;
       this.contactCusts = contactCusts;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public long getFinContactId() {
        return this.finContactId;
    }
    
    public void setFinContactId(long finContactId) {
        this.finContactId = finContactId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getWorkEmail() {
        return this.workEmail;
    }
    
    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }
    public String getPersonalEmail() {
        return this.personalEmail;
    }
    
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }
    public String getWorkPhone() {
        return this.workPhone;
    }
    
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }
    public String getPersonalPhone() {
        return this.personalPhone;
    }
    
    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }
    public String getCellPhone() {
        return this.cellPhone;
    }
    
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    public String getSalutationCd() {
        return this.salutationCd;
    }
    
    public void setSalutationCd(String salutationCd) {
        this.salutationCd = salutationCd;
    }
    public String getContactFlag() {
        return this.contactFlag;
    }
    
    public void setContactFlag(String contactFlag) {
        this.contactFlag = contactFlag;
    }
    public String getPrefCommunication() {
        return this.prefCommunication;
    }
    
    public void setPrefCommunication(String prefCommunication) {
        this.prefCommunication = prefCommunication;
    }
    public String getNewFlag() {
        return this.newFlag;
    }
    
    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }
    public String getUpdateFlag() {
        return this.updateFlag;
    }
    
    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
    public String getUpdateLimsFlag() {
        return this.updateLimsFlag;
    }
    
    public void setUpdateLimsFlag(String updateLimsFlag) {
        this.updateLimsFlag = updateLimsFlag;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }
    
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public String getCreByUserName() {
        return this.creByUserName;
    }
    
    public void setCreByUserName(String creByUserName) {
        this.creByUserName = creByUserName;
    }
    public Date getUpdatedTime() {
        return this.updatedTime;
    }
    
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    public String getModByUserName() {
        return this.modByUserName;
    }
    
    public void setModByUserName(String modByUserName) {
        this.modByUserName = modByUserName;
    }
    public Set<ContactCust> getContactCusts() {
        return this.contactCusts;
    }
    
    public void setContactCusts(Set<ContactCust> contactCusts) {
        this.contactCusts = contactCusts;
    }




}


