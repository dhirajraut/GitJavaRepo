/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;

import javax.persistence.Embeddable;

/**
 * Address encapsulates common address attributes and operations. This is a
 * value-class that may or may not mapped to a separate database table. In
 * current Phoenix, attrbutes of Address are embedded in tables such as
 * BANK_ACCOUNTS, BANKS, BRANCHES, BUSINESS_UNITS, CUST_ADDRESSES,
 * JOB_CONTRACT_INVOICE, SERVICE_LOCATION. In the case when an existing table is
 * to be used by CE, the Address class will be used to mapped to those address
 * attribute and provide Address related business functions.
 * 
 * @author richard.qin
 */

@Embeddable
public class Address {
    @Length(min = 0, max = 55)
    private String address1;
    @Length(min = 0, max = 55)
    private String address2;
    @Length(min = 0, max = 55)
    private String address3;
    @Length(min = 0, max = 55)
    private String address4;
    @Length(min = 0, max = 30)
    private String city;
    @Length(min = 0, max = 12)
    private String postal;
    @Length(min = 0, max = 20) // TODO fix the length
    private String state;
    @Length(min = 0, max = 20) // TODO fix the length
    private String country;

    public Address() {
    }

    public String getFullAddress() {
        return getFullAddress(",");
    }
    

    /**
     * Get full textual presentation of the address, separate each component
     * with a delimiter
     * 
     * @param delimiter
     *            String used to separate address components
     * @return A full address
     */
    public String getFullAddress(String delimiter) {
        String retVal = "";
        retVal = address1 !=null ? address1 : "" ;
        retVal = appendValue(delimiter, retVal, address2);
        retVal = appendValue(delimiter, retVal, address3);
        retVal = appendValue(delimiter, retVal,  address4);
        retVal = appendValue(delimiter, retVal,  city);
        retVal = appendValue(delimiter, retVal,  state);
        retVal = appendValue(delimiter, retVal,   postal);
        retVal = appendValue(delimiter, retVal,   country);
        return retVal;
    }

    private String appendValue(String delimiter, String retVal, String value) {
        if (value != null && !value.trim().equals("")){
            retVal += (retVal.length() > 0 ? delimiter : "") + value;
        }
        return retVal;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * @return the address4
     */
    public String getAddress4() {
        return address4;
    }

    /**
     * @param address4 the address4 to set
     */
    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the postal
     */
    public String getPostal() {
        return postal;
    }

    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return getFullAddress(",");
    }
    
    
    
}
