package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * RateMapId generated by hbm2java
 */
public class RateMapId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String contractId;
     @NotBlank @Length(min=0, max=375) private String serviceName;
     @NotBlank @Length(min=0, max=105) private String expressionId;
     @NotBlank @Length(min=0, max=105) private String serviceRateExpressionId;

     // Constructors

    /** default constructor */
    public RateMapId() {
    }

    /** full constructor */
    public RateMapId(String contractId, String serviceName, String expressionId, String serviceRateExpressionId) {
       this.contractId = contractId;
       this.serviceName = serviceName;
       this.expressionId = expressionId;
       this.serviceRateExpressionId = serviceRateExpressionId;
    }
   
    // Property accessors
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getServiceName() {
        return this.serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getExpressionId() {
        return this.expressionId;
    }
    
    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }
    public String getServiceRateExpressionId() {
        return this.serviceRateExpressionId;
    }
    
    public void setServiceRateExpressionId(String serviceRateExpressionId) {
        this.serviceRateExpressionId = serviceRateExpressionId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RateMapId) ) return false;
		 RateMapId castOther = ( RateMapId ) other; 
         
		 return ( (this.getContractId()==castOther.getContractId()) || ( this.getContractId()!=null && castOther.getContractId()!=null && this.getContractId().equals(castOther.getContractId()) ) )
 && ( (this.getServiceName()==castOther.getServiceName()) || ( this.getServiceName()!=null && castOther.getServiceName()!=null && this.getServiceName().equals(castOther.getServiceName()) ) )
 && ( (this.getExpressionId()==castOther.getExpressionId()) || ( this.getExpressionId()!=null && castOther.getExpressionId()!=null && this.getExpressionId().equals(castOther.getExpressionId()) ) )
 && ( (this.getServiceRateExpressionId()==castOther.getServiceRateExpressionId()) || ( this.getServiceRateExpressionId()!=null && castOther.getServiceRateExpressionId()!=null && this.getServiceRateExpressionId().equals(castOther.getServiceRateExpressionId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContractId() == null ? 0 : this.getContractId().hashCode() );
         result = 37 * result + ( getServiceName() == null ? 0 : this.getServiceName().hashCode() );
         result = 37 * result + ( getExpressionId() == null ? 0 : this.getExpressionId().hashCode() );
         result = 37 * result + ( getServiceRateExpressionId() == null ? 0 : this.getServiceRateExpressionId().hashCode() );
         return result;
   }   


}


