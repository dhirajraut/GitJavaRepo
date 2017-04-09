package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * ApplicationLevelLockId generated by hbm2java
 */
public class ApplicationLevelLockId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=3) private String name;
     @NotBlank @Length(min=0, max=9) private String key;

     // Constructors

    /** default constructor */
    public ApplicationLevelLockId() {
    }

    /** full constructor */
    public ApplicationLevelLockId(String name, String key) {
       this.name = name;
       this.key = key;
    }
   
    // Property accessors
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ApplicationLevelLockId) ) return false;
		 ApplicationLevelLockId castOther = ( ApplicationLevelLockId ) other; 
         
		 return ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getKey()==castOther.getKey()) || ( this.getKey()!=null && castOther.getKey()!=null && this.getKey().equals(castOther.getKey()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getKey() == null ? 0 : this.getKey().hashCode() );
         return result;
   }   


}

