package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * SlatePriceId generated by hbm2java
 */
public class SlatePriceId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String contractId;
     @NotBlank @Length(min=0, max=3) private String slateType;
     @NotBlank @Length(min=0, max=45) private String slateId;
     @NotBlank @Length(min=0, max=105) private String location;
     private Date beginDate;
     private Integer minQty;
     private Integer maxQty;

     // Constructors

    /** default constructor */
    public SlatePriceId() {
    }

    /** full constructor */
    public SlatePriceId(String contractId, String slateType, String slateId, String location, Date beginDate, Integer minQty, Integer maxQty) {
       this.contractId = contractId;
       this.slateType = slateType;
       this.slateId = slateId;
       this.location = location;
       this.beginDate = beginDate;
       this.minQty = minQty;
       this.maxQty = maxQty;
    }
   
    // Property accessors
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getSlateType() {
        return this.slateType;
    }
    
    public void setSlateType(String slateType) {
        this.slateType = slateType;
    }
    public String getSlateId() {
        return this.slateId;
    }
    
    public void setSlateId(String slateId) {
        this.slateId = slateId;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Integer getMinQty() {
        return this.minQty;
    }
    
    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }
    public Integer getMaxQty() {
        return this.maxQty;
    }
    
    public void setMaxQty(Integer maxQty) {
        this.maxQty = maxQty;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SlatePriceId) ) return false;
		 SlatePriceId castOther = ( SlatePriceId ) other; 
         
		 return ( (this.getContractId()==castOther.getContractId()) || ( this.getContractId()!=null && castOther.getContractId()!=null && this.getContractId().equals(castOther.getContractId()) ) )
 && ( (this.getSlateType()==castOther.getSlateType()) || ( this.getSlateType()!=null && castOther.getSlateType()!=null && this.getSlateType().equals(castOther.getSlateType()) ) )
 && ( (this.getSlateId()==castOther.getSlateId()) || ( this.getSlateId()!=null && castOther.getSlateId()!=null && this.getSlateId().equals(castOther.getSlateId()) ) )
 && ( (this.getLocation()==castOther.getLocation()) || ( this.getLocation()!=null && castOther.getLocation()!=null && this.getLocation().equals(castOther.getLocation()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) )
 && ( (this.getMinQty()==castOther.getMinQty()) || ( this.getMinQty()!=null && castOther.getMinQty()!=null && this.getMinQty().equals(castOther.getMinQty()) ) )
 && ( (this.getMaxQty()==castOther.getMaxQty()) || ( this.getMaxQty()!=null && castOther.getMaxQty()!=null && this.getMaxQty().equals(castOther.getMaxQty()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContractId() == null ? 0 : this.getContractId().hashCode() );
         result = 37 * result + ( getSlateType() == null ? 0 : this.getSlateType().hashCode() );
         result = 37 * result + ( getSlateId() == null ? 0 : this.getSlateId().hashCode() );
         result = 37 * result + ( getLocation() == null ? 0 : this.getLocation().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         result = 37 * result + ( getMinQty() == null ? 0 : this.getMinQty().hashCode() );
         result = 37 * result + ( getMaxQty() == null ? 0 : this.getMaxQty().hashCode() );
         return result;
   }   


}

