/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.CustAddress;
import com.intertek.phoenix.util.CommonUtil;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class CustomerAddressSequenceX {
    private String customerCode;
    private int location;
    
    private String addressSequenceNumber;
    private String addressDescription;

    private boolean correspondenceAddress;
    private boolean shipTo;
    private boolean billTo;
    private boolean soldTo;
    private boolean billToPrimary;
    private boolean soldToPrimary;

    private List<CustomerAddressX> customerAddresses;

    public CustomerAddressSequenceX(){
    }
    
    public CustomerAddressSequenceX(CustAddrSeq ca){
        CustAddrSeqId cusSeqId=ca.getCustAddrSeqId();
        if(cusSeqId!=null){
            this.customerCode=cusSeqId.getCustCode();
            this.location=cusSeqId.getLocationNumber();
        }
        //TODO this.addressSequenceNumber=cusSeqId.;
        this.addressDescription=ca.getAddressDescr();
        this.correspondenceAddress=CommonUtil.booleanValue(ca.getCorresponence());
        this.shipTo=CommonUtil.booleanValue(ca.getShipTo());
        this.billTo=CommonUtil.booleanValue(ca.getBillTo());
        //TODO this.billToPrimary=
        //TODO this.soldToPrimary=
        setCustomerAddresses(ca.getCustAddresses());
    }
    
    private void setCustomerAddresses(Set<CustAddress> addresses){
        if(addresses==null){
            return;
        }
        this.customerAddresses=new ArrayList<CustomerAddressX>();
        for(CustAddress address:addresses){
            this.customerAddresses.add(new CustomerAddressX(address));    
        } 
    }
    
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAddressSequenceNumber() {
        return addressSequenceNumber;
    }

    public void setAddressSequenceNumber(String addressSequenceNumber) {
        this.addressSequenceNumber = addressSequenceNumber;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public boolean isCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(boolean correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public boolean isShipTo() {
        return shipTo;
    }

    public void setShipTo(boolean shipTo) {
        this.shipTo = shipTo;
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

    public boolean isBillToPrimary() {
        return billToPrimary;
    }

    public void setBillToPrimary(boolean billToPrimary) {
        this.billToPrimary = billToPrimary;
    }

    public boolean isSoldToPrimary() {
        return soldToPrimary;
    }

    public void setSoldToPrimary(boolean soldToPrimary) {
        this.soldToPrimary = soldToPrimary;
    }

    public List<CustomerAddressX> getCustomerAddresses() {
        return customerAddresses;
    }

    public void setCustomerAddresses(List<CustomerAddressX> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }

}
