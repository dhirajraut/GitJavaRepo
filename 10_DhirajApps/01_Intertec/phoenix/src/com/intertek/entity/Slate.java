package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Slate generated by hbm2java
 */
public class Slate  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=45) private String slateId;
     @Length(min=0, max=450) private String rbKey;
     @Length(min=0, max=762) private String slateDescription;

     // Constructors

    /** default constructor */
    public Slate() {
    }

	/** minimal constructor */
    public Slate(String slateId) {
        this.slateId = slateId;
    }
    /** full constructor */
    public Slate(String slateId, String rbKey, String slateDescription) {
       this.slateId = slateId;
       this.rbKey = rbKey;
       this.slateDescription = slateDescription;
    }
   
    // Property accessors
    public String getSlateId() {
        return this.slateId;
    }
    
    public void setSlateId(String slateId) {
        this.slateId = slateId;
    }
    public String getRbKey() {
        return this.rbKey;
    }
    
    public void setRbKey(String rbKey) {
        this.rbKey = rbKey;
    }
    public String getSlateDescription() {
        return this.slateDescription;
    }
    
    public void setSlateDescription(String slateDescription) {
        this.slateDescription = slateDescription;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Slate) ) return false;
		 Slate castOther = ( Slate ) other; 
         
		 return ( (this.getSlateId()==castOther.getSlateId()) || ( this.getSlateId()!=null && castOther.getSlateId()!=null && this.getSlateId().equals(castOther.getSlateId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSlateId() == null ? 0 : this.getSlateId().hashCode() );
         
         
         return result;
   }   


}

