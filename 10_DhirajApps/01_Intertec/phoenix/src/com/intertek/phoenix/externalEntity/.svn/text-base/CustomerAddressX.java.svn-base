/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.Date;

import com.intertek.entity.CustAddress;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class CustomerAddressX {
    private String id;
    private String customerCode;
    private int locationNumber;

    private char status;
    private Date effectiveDate;
    private String taxCode;
    private AddressX address;

    public CustomerAddressX(){
    }
    
    public CustomerAddressX(CustAddress address) {
        this.id=address.getId()+"";
        this.customerCode = address.getCustCode();
        this.locationNumber = address.getLocationNumber();
        this.status = (address.getEffStatus() + "A").toUpperCase().charAt(0);
        this.effectiveDate = address.getEffDate();
        this.taxCode = address.getTaxCode();
        this.address = new AddressX(address);
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public AddressX getAddress() {
        return address;
    }

    public void setAddress(AddressX address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
