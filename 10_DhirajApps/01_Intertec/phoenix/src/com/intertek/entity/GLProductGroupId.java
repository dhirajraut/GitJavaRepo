package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * GLProductGroupId generated by hbm2java
 */
public class GLProductGroupId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=6) private String product;
     private Date effDate;

     // Constructors

    /** default constructor */
    public GLProductGroupId() {
    }

    /** full constructor */
    public GLProductGroupId(String product, Date effDate) {
       this.product = product;
       this.effDate = effDate;
    }
   
    // Property accessors
    public String getProduct() {
        return this.product;
    }
    
    public void setProduct(String product) {
        this.product = product;
    }
    public Date getEffDate() {
        return this.effDate;
    }
    
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof GLProductGroupId) ) return false;
		 GLProductGroupId castOther = ( GLProductGroupId ) other; 
         
		 return ( (this.getProduct()==castOther.getProduct()) || ( this.getProduct()!=null && castOther.getProduct()!=null && this.getProduct().equals(castOther.getProduct()) ) )
 && ( (this.getEffDate()==castOther.getEffDate()) || ( this.getEffDate()!=null && castOther.getEffDate()!=null && this.getEffDate().equals(castOther.getEffDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProduct() == null ? 0 : this.getProduct().hashCode() );
         result = 37 * result + ( getEffDate() == null ? 0 : this.getEffDate().hashCode() );
         return result;
   }   


}

