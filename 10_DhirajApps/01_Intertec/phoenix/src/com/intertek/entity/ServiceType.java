package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * ServiceType generated by hbm2java
 */
public class ServiceType  implements java.io.Serializable {

    // Fields    

     private ServiceTypeId serviceTypeId;
     @Length(min=0, max=45) private String displayLevel;
     @Length(min=0, max=45) private String parentServiceId;
     @Length(min=0, max=45) private String inputComponentType;
     @Length(min=0, max=45) private String serviceLevel;
     @Length(min=0, max=450) private String rbKey;

     // Constructors

    /** default constructor */
    public ServiceType() {
    }

	/** minimal constructor */
    public ServiceType(ServiceTypeId serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
    /** full constructor */
    public ServiceType(ServiceTypeId serviceTypeId, String displayLevel, String parentServiceId, String inputComponentType, String serviceLevel, String rbKey) {
       this.serviceTypeId = serviceTypeId;
       this.displayLevel = displayLevel;
       this.parentServiceId = parentServiceId;
       this.inputComponentType = inputComponentType;
       this.serviceLevel = serviceLevel;
       this.rbKey = rbKey;
    }
   
    // Property accessors
    public ServiceTypeId getServiceTypeId() {
        return this.serviceTypeId;
    }
    
    public void setServiceTypeId(ServiceTypeId serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
    public String getDisplayLevel() {
        return this.displayLevel;
    }
    
    public void setDisplayLevel(String displayLevel) {
        this.displayLevel = displayLevel;
    }
    public String getParentServiceId() {
        return this.parentServiceId;
    }
    
    public void setParentServiceId(String parentServiceId) {
        this.parentServiceId = parentServiceId;
    }
    public String getInputComponentType() {
        return this.inputComponentType;
    }
    
    public void setInputComponentType(String inputComponentType) {
        this.inputComponentType = inputComponentType;
    }
    public String getServiceLevel() {
        return this.serviceLevel;
    }
    
    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
    public String getRbKey() {
        return this.rbKey;
    }
    
    public void setRbKey(String rbKey) {
        this.rbKey = rbKey;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ServiceType) ) return false;
		 ServiceType castOther = ( ServiceType ) other; 
         
		 return ( (this.getServiceTypeId()==castOther.getServiceTypeId()) || ( this.getServiceTypeId()!=null && castOther.getServiceTypeId()!=null && this.getServiceTypeId().equals(castOther.getServiceTypeId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getServiceTypeId() == null ? 0 : this.getServiceTypeId().hashCode() );
         
         
         
         
         
         return result;
   }   


}


