/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.phoenix.esb.Logable;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class ContactX implements Logable {
    private String contactId;
    private char status;
    private String firstName;
    private String lastName;
    private String title;
    private String salutation;
    private String workPhone; // string
    private String fax;
    private String personalPhone;
    private String workEmail;
    private String cellPhone;
    private String personalEmail;
    private String contactFlag;
    private String preferredCommunicator;

    private List<ContactCustomerX> contactCustomer;

    public ContactX(){
        
    }
    
    public ContactX(Contact con){
        //TODO: this.customerCode=con.getcu
        this.contactId=con.getId()+"";
        this.status=(con.getStatus()+"A").toUpperCase().charAt(0);
        this.firstName=con.getFirstName();
        this.lastName=con.getLastName();
        this.title=con.getTitle();
        this.salutation=con.getSalutationCd();
        this.workPhone=con.getWorkPhone();
        this.fax=con.getFax();
        this.personalPhone=con.getPersonalPhone();
        this.workEmail=con.getWorkEmail();
        this.cellPhone=con.getCellPhone();
        this.personalEmail=con.getPersonalEmail();
        this.contactFlag=con.getContactFlag();
        this.preferredCommunicator=con.getPrefCommunication();
        
        setContactCustomer(con.getContactCusts());
        
    }

    private void setContactCustomer(Set<ContactCust> set){
        if(set==null){
            return;
        }
        this.contactCustomer=new ArrayList<ContactCustomerX>();
        for(ContactCust cc:set){
            this.contactCustomer.add(new ContactCustomerX(cc));    
        }        
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPersonalPhone() {
        return personalPhone;
    }

    public void setPersonalPhone(String personalPhone) {
        this.personalPhone = personalPhone;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getContactFlag() {
        return contactFlag;
    }

    public void setContactFlag(String contactFlag) {
        this.contactFlag = contactFlag;
    }

    public String getPreferredCommunicator() {
        return preferredCommunicator;
    }

    public void setPreferredCommunicator(String preferredCommunicator) {
        this.preferredCommunicator = preferredCommunicator;
    }

    public List<ContactCustomerX> getContactCustomer() {
        return contactCustomer;
    }

    public void setContactCustomer(List<ContactCustomerX> contactCustomer) {
        this.contactCustomer = contactCustomer;
    }

    @Override
    public String getId() {
        return getContactId();
    }
}
