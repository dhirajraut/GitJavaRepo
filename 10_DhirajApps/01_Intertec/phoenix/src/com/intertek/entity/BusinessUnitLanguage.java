package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * BusinessUnitLanguage generated by hbm2java
 */
public class BusinessUnitLanguage  implements java.io.Serializable {

    // Fields    

     private BusinessUnitLanguageId businessUnitLanguageId;
     private String repDirector;
     private String businessType;
     private String businessItem;
     private String description;
     private String address1;
     private String address2;
     private String address3;
     private String address4;
     private String city;
     private String county;
     private String postal;
     private String companyDesc;
     private BusinessUnit businessUnit;

     // Constructors

    /** default constructor */
    public BusinessUnitLanguage() {
    }

	/** minimal constructor */
    public BusinessUnitLanguage(BusinessUnitLanguageId businessUnitLanguageId) {
        this.businessUnitLanguageId = businessUnitLanguageId;
    }
    /** full constructor */
    public BusinessUnitLanguage(BusinessUnitLanguageId businessUnitLanguageId, String repDirector, String businessType, String businessItem, String description, String address1, String address2, String address3, String address4, String city, String county, String postal, String companyDesc, BusinessUnit businessUnit) {
       this.businessUnitLanguageId = businessUnitLanguageId;
       this.repDirector = repDirector;
       this.businessType = businessType;
       this.businessItem = businessItem;
       this.description = description;
       this.address1 = address1;
       this.address2 = address2;
       this.address3 = address3;
       this.address4 = address4;
       this.city = city;
       this.county = county;
       this.postal = postal;
       this.companyDesc = companyDesc;
       this.businessUnit = businessUnit;
    }
   
    // Property accessors
    public BusinessUnitLanguageId getBusinessUnitLanguageId() {
        return this.businessUnitLanguageId;
    }
    
    public void setBusinessUnitLanguageId(BusinessUnitLanguageId businessUnitLanguageId) {
        this.businessUnitLanguageId = businessUnitLanguageId;
    }
    public String getRepDirector() {
        return this.repDirector;
    }
    
    public void setRepDirector(String repDirector) {
        this.repDirector = repDirector;
    }
    public String getBusinessType() {
        return this.businessType;
    }
    
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getBusinessItem() {
        return this.businessItem;
    }
    
    public void setBusinessItem(String businessItem) {
        this.businessItem = businessItem;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getAddress3() {
        return this.address3;
    }
    
    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    public String getAddress4() {
        return this.address4;
    }
    
    public void setAddress4(String address4) {
        this.address4 = address4;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return this.county;
    }
    
    public void setCounty(String county) {
        this.county = county;
    }
    public String getPostal() {
        return this.postal;
    }
    
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public String getCompanyDesc() {
        return this.companyDesc;
    }
    
    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }
    public BusinessUnit getBusinessUnit() {
        return this.businessUnit;
    }
    
    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BusinessUnitLanguage) ) return false;
		 BusinessUnitLanguage castOther = ( BusinessUnitLanguage ) other; 
         
		 return ( (this.getBusinessUnitLanguageId()==castOther.getBusinessUnitLanguageId()) || ( this.getBusinessUnitLanguageId()!=null && castOther.getBusinessUnitLanguageId()!=null && this.getBusinessUnitLanguageId().equals(castOther.getBusinessUnitLanguageId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBusinessUnitLanguageId() == null ? 0 : this.getBusinessUnitLanguageId().hashCode() );
         
         
         
         
         
         
         
         
         
         
         
         
         
         return result;
   }   


}

