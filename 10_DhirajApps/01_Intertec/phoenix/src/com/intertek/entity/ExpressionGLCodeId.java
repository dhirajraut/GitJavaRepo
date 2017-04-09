package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * ExpressionGLCodeId generated by hbm2java
 */
public class ExpressionGLCodeId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=105) private String expressionId;
     @NotBlank @Length(min=0, max=45) private String nominationType;

     // Constructors

    /** default constructor */
    public ExpressionGLCodeId() {
    }

    /** full constructor */
    public ExpressionGLCodeId(String expressionId, String nominationType) {
       this.expressionId = expressionId;
       this.nominationType = nominationType;
    }
   
    // Property accessors
    public String getExpressionId() {
        return this.expressionId;
    }
    
    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }
    public String getNominationType() {
        return this.nominationType;
    }
    
    public void setNominationType(String nominationType) {
        this.nominationType = nominationType;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ExpressionGLCodeId) ) return false;
		 ExpressionGLCodeId castOther = ( ExpressionGLCodeId ) other; 
         
		 return ( (this.getExpressionId()==castOther.getExpressionId()) || ( this.getExpressionId()!=null && castOther.getExpressionId()!=null && this.getExpressionId().equals(castOther.getExpressionId()) ) )
 && ( (this.getNominationType()==castOther.getNominationType()) || ( this.getNominationType()!=null && castOther.getNominationType()!=null && this.getNominationType().equals(castOther.getNominationType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getExpressionId() == null ? 0 : this.getExpressionId().hashCode() );
         result = 37 * result + ( getNominationType() == null ? 0 : this.getNominationType().hashCode() );
         return result;
   }   


}

