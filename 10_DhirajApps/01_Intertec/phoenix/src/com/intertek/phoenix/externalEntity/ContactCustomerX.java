/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import com.intertek.entity.ContactCust;
import com.intertek.entity.ContactCustId;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class ContactCustomerX {
    private String contactId;
    private String customerCode;
    private int locationNumber;
    private String status;
    private String contactSequenceNumber;
    private boolean billTo;
    private boolean soldTo;
    private boolean shipTo;

    public ContactCustomerX(){
        
    }
    
    public ContactCustomerX(ContactCust cc){        
        ContactCustId id=cc.getContactCustId();
        this.contactId=cc.getContactId()+"";
        this.customerCode=cc.getCustCode();
        this.locationNumber=id.getLocationNumber();
        this.status=cc.getStatus();
        this.contactSequenceNumber=cc.getContactSeqNum()+"";
        this.billTo=new Boolean(cc.getBillTo());
        this.soldTo=new Boolean(cc.getSoldTo());
        this.shipTo=new Boolean(cc.getShipTo());
    }
    
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public int getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactSequenceNumber() {
        return contactSequenceNumber;
    }

    public void setContactSequenceNumber(String contactSequenceNumber) {
        this.contactSequenceNumber = contactSequenceNumber;
    }

    public boolean isBillTo() {
        return billTo;
    }

    public void setBillTo(boolean billTo) {
        this.billTo = billTo;
    }

    public boolean isSoldTo() {
        return soldTo;
    }

    public void setSoldTo(boolean soldTo) {
        this.soldTo = soldTo;
    }

    public boolean isShipTo() {
        return shipTo;
    }

    public void setShipTo(boolean shipTo) {
        this.shipTo = shipTo;
    }
}
