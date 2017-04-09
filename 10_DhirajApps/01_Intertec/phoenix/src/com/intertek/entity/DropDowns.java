package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * DropDowns generated by hbm2java
 */
public class DropDowns  implements java.io.Serializable {

    // Fields    

     private DropDownId dropDownId;
     @Length(min=0, max=128) private String fieldValue;

     // Constructors

    /** default constructor */
    public DropDowns() {
    }

	/** minimal constructor */
    public DropDowns(DropDownId dropDownId) {
        this.dropDownId = dropDownId;
    }
    /** full constructor */
    public DropDowns(DropDownId dropDownId, String fieldValue) {
       this.dropDownId = dropDownId;
       this.fieldValue = fieldValue;
    }
   
    // Property accessors
    public DropDownId getDropDownId() {
        return this.dropDownId;
    }
    
    public void setDropDownId(DropDownId dropDownId) {
        this.dropDownId = dropDownId;
    }
    public String getFieldValue() {
        return this.fieldValue;
    }
    
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DropDowns) ) return false;
		 DropDowns castOther = ( DropDowns ) other; 
         
		 return ( (this.getDropDownId()==castOther.getDropDownId()) || ( this.getDropDownId()!=null && castOther.getDropDownId()!=null && this.getDropDownId().equals(castOther.getDropDownId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDropDownId() == null ? 0 : this.getDropDownId().hashCode() );
         
         return result;
   }   


}

