package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * ProductGroupId generated by hbm2java
 */
public class ProductGroupId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String productGroupSet;
     @NotBlank @Length(min=0, max=45) private String groupId;
     private Date beginDate;

     // Constructors

    /** default constructor */
    public ProductGroupId() {
    }

    /** full constructor */
    public ProductGroupId(String productGroupSet, String groupId, Date beginDate) {
       this.productGroupSet = productGroupSet;
       this.groupId = groupId;
       this.beginDate = beginDate;
    }
   
    // Property accessors
    public String getProductGroupSet() {
        return this.productGroupSet;
    }
    
    public void setProductGroupSet(String productGroupSet) {
        this.productGroupSet = productGroupSet;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ProductGroupId) ) return false;
		 ProductGroupId castOther = ( ProductGroupId ) other; 
         
		 return ( (this.getProductGroupSet()==castOther.getProductGroupSet()) || ( this.getProductGroupSet()!=null && castOther.getProductGroupSet()!=null && this.getProductGroupSet().equals(castOther.getProductGroupSet()) ) )
 && ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProductGroupSet() == null ? 0 : this.getProductGroupSet().hashCode() );
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         return result;
   }   


}

