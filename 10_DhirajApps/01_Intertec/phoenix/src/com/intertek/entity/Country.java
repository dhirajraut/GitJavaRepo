package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Country generated by hbm2java
 */
public class Country  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=3) private String countryCode;
     @Length(min=0, max=30) private String name;
     @Length(min=0, max=10) private String shortName;
     @Length(min=0, max=2) private String country2;
     private Boolean euMemberId;
     @Length(min=0, max=15) private String addr1Lbl;
     private Boolean addr1Avail;
     @Length(min=0, max=15) private String addr2Lbl;
     private Boolean addr2Avail;
     @Length(min=0, max=15) private String addr3Lbl;
     private Boolean addr3Avail;
     @Length(min=0, max=15) private String addr4Lbl;
     private Boolean addr4Avail;
     @Length(min=0, max=15) private String otherLabel;
     private Boolean otherAvail;
     @Length(min=0, max=15) private String city;
     private Boolean cityAvail;
     @Length(min=0, max=15) private String county;
     private Boolean countyAvail;
     @Length(min=0, max=15) private String state;
     private Boolean stateAvail;
     @Length(min=0, max=15) private String postal;
     private Boolean postalAvail;
     private Boolean vatCountry;
     private Boolean vatByProvince;
     private Boolean sameProvinceValidation;
     @Length(min=0, max=3) private String foreignBuyer;
     @Length(min=0, max=254) private String url;
     private Boolean showState;
     private Boolean stateRequiredInAddress;
     private Set<State> states = new HashSet<State>(0);
     private Set<CountryVAT> countryVats = new HashSet<CountryVAT>(0);
     @Length(min=0, max=1) private String status;

     // Constructors

    /** default constructor */
    public Country() {
    }

	/** minimal constructor */
    public Country(String countryCode) {
        this.countryCode = countryCode;
    }
    /** full constructor */
    public Country(String countryCode, String name, String shortName, String country2, Boolean euMemberId, String addr1Lbl, Boolean addr1Avail, String addr2Lbl, Boolean addr2Avail, String addr3Lbl, Boolean addr3Avail, String addr4Lbl, Boolean addr4Avail, String otherLabel, Boolean otherAvail, String city, Boolean cityAvail, String county, Boolean countyAvail, String state, Boolean stateAvail, String postal, Boolean postalAvail, Boolean vatCountry, Boolean vatByProvince, Boolean sameProvinceValidation, String foreignBuyer, String url, Boolean showState, Boolean stateRequiredInAddress, Set<State> states, Set<CountryVAT> countryVats, String status) {
       this.countryCode = countryCode;
       this.name = name;
       this.shortName = shortName;
       this.country2 = country2;
       this.euMemberId = euMemberId;
       this.addr1Lbl = addr1Lbl;
       this.addr1Avail = addr1Avail;
       this.addr2Lbl = addr2Lbl;
       this.addr2Avail = addr2Avail;
       this.addr3Lbl = addr3Lbl;
       this.addr3Avail = addr3Avail;
       this.addr4Lbl = addr4Lbl;
       this.addr4Avail = addr4Avail;
       this.otherLabel = otherLabel;
       this.otherAvail = otherAvail;
       this.city = city;
       this.cityAvail = cityAvail;
       this.county = county;
       this.countyAvail = countyAvail;
       this.state = state;
       this.stateAvail = stateAvail;
       this.postal = postal;
       this.postalAvail = postalAvail;
       this.vatCountry = vatCountry;
       this.vatByProvince = vatByProvince;
       this.sameProvinceValidation = sameProvinceValidation;
       this.foreignBuyer = foreignBuyer;
       this.url = url;
       this.showState = showState;
       this.stateRequiredInAddress = stateRequiredInAddress;
       this.states = states;
       this.countryVats = countryVats;
       this.status = status;
    }
   
    // Property accessors
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getShortName() {
        return this.shortName;
    }
    
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getCountry2() {
        return this.country2;
    }
    
    public void setCountry2(String country2) {
        this.country2 = country2;
    }
    public Boolean getEuMemberId() {
        return this.euMemberId;
    }
    
    public void setEuMemberId(Boolean euMemberId) {
        this.euMemberId = euMemberId;
    }
    public String getAddr1Lbl() {
        return this.addr1Lbl;
    }
    
    public void setAddr1Lbl(String addr1Lbl) {
        this.addr1Lbl = addr1Lbl;
    }
    public Boolean getAddr1Avail() {
        return this.addr1Avail;
    }
    
    public void setAddr1Avail(Boolean addr1Avail) {
        this.addr1Avail = addr1Avail;
    }
    public String getAddr2Lbl() {
        return this.addr2Lbl;
    }
    
    public void setAddr2Lbl(String addr2Lbl) {
        this.addr2Lbl = addr2Lbl;
    }
    public Boolean getAddr2Avail() {
        return this.addr2Avail;
    }
    
    public void setAddr2Avail(Boolean addr2Avail) {
        this.addr2Avail = addr2Avail;
    }
    public String getAddr3Lbl() {
        return this.addr3Lbl;
    }
    
    public void setAddr3Lbl(String addr3Lbl) {
        this.addr3Lbl = addr3Lbl;
    }
    public Boolean getAddr3Avail() {
        return this.addr3Avail;
    }
    
    public void setAddr3Avail(Boolean addr3Avail) {
        this.addr3Avail = addr3Avail;
    }
    public String getAddr4Lbl() {
        return this.addr4Lbl;
    }
    
    public void setAddr4Lbl(String addr4Lbl) {
        this.addr4Lbl = addr4Lbl;
    }
    public Boolean getAddr4Avail() {
        return this.addr4Avail;
    }
    
    public void setAddr4Avail(Boolean addr4Avail) {
        this.addr4Avail = addr4Avail;
    }
    public String getOtherLabel() {
        return this.otherLabel;
    }
    
    public void setOtherLabel(String otherLabel) {
        this.otherLabel = otherLabel;
    }
    public Boolean getOtherAvail() {
        return this.otherAvail;
    }
    
    public void setOtherAvail(Boolean otherAvail) {
        this.otherAvail = otherAvail;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public Boolean getCityAvail() {
        return this.cityAvail;
    }
    
    public void setCityAvail(Boolean cityAvail) {
        this.cityAvail = cityAvail;
    }
    public String getCounty() {
        return this.county;
    }
    
    public void setCounty(String county) {
        this.county = county;
    }
    public Boolean getCountyAvail() {
        return this.countyAvail;
    }
    
    public void setCountyAvail(Boolean countyAvail) {
        this.countyAvail = countyAvail;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public Boolean getStateAvail() {
        return this.stateAvail;
    }
    
    public void setStateAvail(Boolean stateAvail) {
        this.stateAvail = stateAvail;
    }
    public String getPostal() {
        return this.postal;
    }
    
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public Boolean getPostalAvail() {
        return this.postalAvail;
    }
    
    public void setPostalAvail(Boolean postalAvail) {
        this.postalAvail = postalAvail;
    }
    public Boolean getVatCountry() {
        return this.vatCountry;
    }
    
    public void setVatCountry(Boolean vatCountry) {
        this.vatCountry = vatCountry;
    }
    public Boolean getVatByProvince() {
        return this.vatByProvince;
    }
    
    public void setVatByProvince(Boolean vatByProvince) {
        this.vatByProvince = vatByProvince;
    }
    public Boolean getSameProvinceValidation() {
        return this.sameProvinceValidation;
    }
    
    public void setSameProvinceValidation(Boolean sameProvinceValidation) {
        this.sameProvinceValidation = sameProvinceValidation;
    }
    public String getForeignBuyer() {
        return this.foreignBuyer;
    }
    
    public void setForeignBuyer(String foreignBuyer) {
        this.foreignBuyer = foreignBuyer;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public Boolean getShowState() {
        return this.showState;
    }
    
    public void setShowState(Boolean showState) {
        this.showState = showState;
    }
    public Boolean getStateRequiredInAddress() {
        return this.stateRequiredInAddress;
    }
    
    public void setStateRequiredInAddress(Boolean stateRequiredInAddress) {
        this.stateRequiredInAddress = stateRequiredInAddress;
    }
    public Set<State> getStates() {
        return this.states;
    }
    
    public void setStates(Set<State> states) {
        this.states = states;
    }
    public Set<CountryVAT> getCountryVats() {
        return this.countryVats;
    }
    
    public void setCountryVats(Set<CountryVAT> countryVats) {
        this.countryVats = countryVats;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Country) ) return false;
		 Country castOther = ( Country ) other; 
         
		 return ( (this.getCountryCode()==castOther.getCountryCode()) || ( this.getCountryCode()!=null && castOther.getCountryCode()!=null && this.getCountryCode().equals(castOther.getCountryCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCountryCode() == null ? 0 : this.getCountryCode().hashCode() );
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         return result;
   }   


}


