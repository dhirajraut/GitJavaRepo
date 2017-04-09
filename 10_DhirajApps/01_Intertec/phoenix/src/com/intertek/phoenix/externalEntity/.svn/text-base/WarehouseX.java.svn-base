/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import com.intertek.entity.Branch;
import com.intertek.phoenix.esb.Logable;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class WarehouseX implements Logable {
    private String id;
    private String BU;
    private AddressX address;
    private String contactPhone;
    private String faxNumber;
    private char status;

    public WarehouseX() {

    }

    public Branch convert() {
        Branch b = new Branch();
        b.setName(this.getId());
        b.setBuName(this.BU);

        AddressX a=this.getAddress();
        
        b.setAddress1(a.getAddress1());
        b.setAddress2(a.getAddress2());
        b.setAddress3(a.getAddress3());
        b.setAddress4(a.getAddress4());
        b.setCity(a.getCity());
        b.setPostal(a.getPostal());
        b.setCounty(a.getCounty());
        b.setStateCode(a.getState());
        b.setCountryCode(a.getCountry());
        
        b.setPhoneNumber(this.getContactPhone());
        b.setFax(this.getFaxNumber());
        b.setStatus(this.getStatus()+"");
        return b;
    }

    public WarehouseX(Branch b) {
        this.id = b.getName();
        this.BU = b.getBuName();
        this.address = new AddressX(b.getAddress1(), b.getAddress2(), b.getAddress3(), b.getAddress4(), b.getCity(), b.getPostal(), b.getCounty(), b
                .getStateCode(), b.getCountryCode(), true);
        this.contactPhone = b.getPhoneNumber();
        this.faxNumber = b.getFax();
        this.status = (b.getStatus() + "A").toUpperCase().charAt(0);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBu() {
        return BU;
    }

    public void setBu(String bu) {
        BU = bu;
    }

    public AddressX getAddress() {
        return address;
    }

    public void setAddress(AddressX address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
