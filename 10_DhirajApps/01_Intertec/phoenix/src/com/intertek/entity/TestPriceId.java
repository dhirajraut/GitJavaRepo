package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * TestPriceId generated by hbm2java
 */
public class TestPriceId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String contractId;
     @NotBlank @Length(min=0, max=3) private String testType;
     @NotBlank @Length(min=0, max=96) private String testId;
     @NotBlank @Length(min=0, max=105) private String location;
     private Date beginDate;
     private Integer minQty;
     private Integer maxQty;

     // Constructors

    /** default constructor */
    public TestPriceId() {
    }

    /** full constructor */
    public TestPriceId(String contractId, String testType, String testId, String location, Date beginDate, Integer minQty, Integer maxQty) {
       this.contractId = contractId;
       this.testType = testType;
       this.testId = testId;
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
    public String getTestType() {
        return this.testType;
    }
    
    public void setTestType(String testType) {
        this.testType = testType;
    }
    public String getTestId() {
        return this.testId;
    }
    
    public void setTestId(String testId) {
        this.testId = testId;
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
		 if ( !(other instanceof TestPriceId) ) return false;
		 TestPriceId castOther = ( TestPriceId ) other; 
         
		 return ( (this.getContractId()==castOther.getContractId()) || ( this.getContractId()!=null && castOther.getContractId()!=null && this.getContractId().equals(castOther.getContractId()) ) )
 && ( (this.getTestType()==castOther.getTestType()) || ( this.getTestType()!=null && castOther.getTestType()!=null && this.getTestType().equals(castOther.getTestType()) ) )
 && ( (this.getTestId()==castOther.getTestId()) || ( this.getTestId()!=null && castOther.getTestId()!=null && this.getTestId().equals(castOther.getTestId()) ) )
 && ( (this.getLocation()==castOther.getLocation()) || ( this.getLocation()!=null && castOther.getLocation()!=null && this.getLocation().equals(castOther.getLocation()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) )
 && ( (this.getMinQty()==castOther.getMinQty()) || ( this.getMinQty()!=null && castOther.getMinQty()!=null && this.getMinQty().equals(castOther.getMinQty()) ) )
 && ( (this.getMaxQty()==castOther.getMaxQty()) || ( this.getMaxQty()!=null && castOther.getMaxQty()!=null && this.getMaxQty().equals(castOther.getMaxQty()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContractId() == null ? 0 : this.getContractId().hashCode() );
         result = 37 * result + ( getTestType() == null ? 0 : this.getTestType().hashCode() );
         result = 37 * result + ( getTestId() == null ? 0 : this.getTestId().hashCode() );
         result = 37 * result + ( getLocation() == null ? 0 : this.getLocation().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         result = 37 * result + ( getMinQty() == null ? 0 : this.getMinQty().hashCode() );
         result = 37 * result + ( getMaxQty() == null ? 0 : this.getMaxQty().hashCode() );
         return result;
   }   


}

