package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * JobContractAttachType generated by hbm2java
 */
public class JobContractAttachType  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=12) private String type;
     @Length(min=0, max=128) private String description;

     // Constructors

    /** default constructor */
    public JobContractAttachType() {
    }

	/** minimal constructor */
    public JobContractAttachType(String type) {
        this.type = type;
    }
    /** full constructor */
    public JobContractAttachType(String type, String description) {
       this.type = type;
       this.description = description;
    }
   
    // Property accessors
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JobContractAttachType) ) return false;
		 JobContractAttachType castOther = ( JobContractAttachType ) other; 
         
		 return ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         
         return result;
   }   


}


