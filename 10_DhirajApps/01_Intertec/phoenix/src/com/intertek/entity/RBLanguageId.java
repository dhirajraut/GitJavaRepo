package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * RBLanguageId generated by hbm2java
 */
public class RBLanguageId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String contractId;
     @NotBlank @Length(min=0, max=450) private String rbKey;
     @NotBlank @Length(min=0, max=9) private String languageCD;
     private Date beginDate;
     private Date endDate;

     // Constructors

    /** default constructor */
    public RBLanguageId() {
    }

    /** full constructor */
    public RBLanguageId(String contractId, String rbKey, String languageCD, Date beginDate, Date endDate) {
       this.contractId = contractId;
       this.rbKey = rbKey;
       this.languageCD = languageCD;
       this.beginDate = beginDate;
       this.endDate = endDate;
    }
   
    // Property accessors
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getRbKey() {
        return this.rbKey;
    }
    
    public void setRbKey(String rbKey) {
        this.rbKey = rbKey;
    }
    public String getLanguageCD() {
        return this.languageCD;
    }
    
    public void setLanguageCD(String languageCD) {
        this.languageCD = languageCD;
    }
    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RBLanguageId) ) return false;
		 RBLanguageId castOther = ( RBLanguageId ) other; 
         
		 return ( (this.getContractId()==castOther.getContractId()) || ( this.getContractId()!=null && castOther.getContractId()!=null && this.getContractId().equals(castOther.getContractId()) ) )
 && ( (this.getRbKey()==castOther.getRbKey()) || ( this.getRbKey()!=null && castOther.getRbKey()!=null && this.getRbKey().equals(castOther.getRbKey()) ) )
 && ( (this.getLanguageCD()==castOther.getLanguageCD()) || ( this.getLanguageCD()!=null && castOther.getLanguageCD()!=null && this.getLanguageCD().equals(castOther.getLanguageCD()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) )
 && ( (this.getEndDate()==castOther.getEndDate()) || ( this.getEndDate()!=null && castOther.getEndDate()!=null && this.getEndDate().equals(castOther.getEndDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContractId() == null ? 0 : this.getContractId().hashCode() );
         result = 37 * result + ( getRbKey() == null ? 0 : this.getRbKey().hashCode() );
         result = 37 * result + ( getLanguageCD() == null ? 0 : this.getLanguageCD().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         result = 37 * result + ( getEndDate() == null ? 0 : this.getEndDate().hashCode() );
         return result;
   }   


}

