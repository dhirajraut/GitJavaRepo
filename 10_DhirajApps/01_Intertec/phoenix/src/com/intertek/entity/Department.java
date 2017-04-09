package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Department generated by hbm2java
 */
public class Department  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String GLCode;
     @NotBlank @Length(min=0, max=45) private String departmentCode;

     // Constructors

    /** default constructor */
    public Department() {
    }

    /** full constructor */
    public Department(String GLCode, String departmentCode) {
       this.GLCode = GLCode;
       this.departmentCode = departmentCode;
    }
   
    // Property accessors
    public String getGLCode() {
        return this.GLCode;
    }
    
    public void setGLCode(String GLCode) {
        this.GLCode = GLCode;
    }
    public String getDepartmentCode() {
        return this.departmentCode;
    }
    
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Department) ) return false;
		 Department castOther = ( Department ) other; 
         
		 return ( (this.getGLCode()==castOther.getGLCode()) || ( this.getGLCode()!=null && castOther.getGLCode()!=null && this.getGLCode().equals(castOther.getGLCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getGLCode() == null ? 0 : this.getGLCode().hashCode() );
         
         return result;
   }   


}

