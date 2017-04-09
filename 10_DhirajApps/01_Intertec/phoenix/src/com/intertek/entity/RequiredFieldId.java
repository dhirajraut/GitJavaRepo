package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * RequiredFieldId generated by hbm2java
 */
public class RequiredFieldId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String className;
     @NotBlank @Length(min=0, max=60) private String fieldName;

     // Constructors

    /** default constructor */
    public RequiredFieldId() {
    }

    /** full constructor */
    public RequiredFieldId(String className, String fieldName) {
       this.className = className;
       this.fieldName = fieldName;
    }
   
    // Property accessors
    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    public String getFieldName() {
        return this.fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RequiredFieldId) ) return false;
		 RequiredFieldId castOther = ( RequiredFieldId ) other; 
         
		 return ( (this.getClassName()==castOther.getClassName()) || ( this.getClassName()!=null && castOther.getClassName()!=null && this.getClassName().equals(castOther.getClassName()) ) )
 && ( (this.getFieldName()==castOther.getFieldName()) || ( this.getFieldName()!=null && castOther.getFieldName()!=null && this.getFieldName().equals(castOther.getFieldName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getClassName() == null ? 0 : this.getClassName().hashCode() );
         result = 37 * result + ( getFieldName() == null ? 0 : this.getFieldName().hashCode() );
         return result;
   }   


}


