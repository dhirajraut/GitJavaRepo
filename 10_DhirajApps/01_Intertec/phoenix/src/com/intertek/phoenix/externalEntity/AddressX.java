/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import com.intertek.entity.CustAddress;
import com.intertek.phoenix.util.CommonUtil;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class AddressX {
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String city;
    private String postal;
    private String county;
    private String state;
    private String country;
    private boolean inCityLimit;

    public AddressX(){
        
    }
    
    public AddressX(CustAddress address){
        this.address1=address.getAddress1();
        this.address2=address.getAddress2();
        this.address3=address.getAddress3();
        this.address4=address.getAddress4();
        this.city=address.getCity();
        this.postal=address.getPostal();
        this.country=address.getCountry();
        this.county=address.getCounty();
        this.state=address.getState();
        this.inCityLimit=CommonUtil.booleanValue(address.getInCityLimit());
    }
    
    public AddressX(String address1, String address2, String address3, String address4, String city, String postal, String county, String state, String country, boolean inCityLimit){
        this.address1=address1;
        this.address2=address2;
        this.address3=address3;
        this.address4=address4;
        this.city=city;
        this.postal=postal;
        this.county=county;
        this.state=state;
        this.country=country;
        this.inCityLimit=inCityLimit;
    }
    
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isInCityLimit() {
        return inCityLimit;
    }

    public void setInCityLimit(boolean inCityLimit) {
        this.inCityLimit = inCityLimit;
    }

}
