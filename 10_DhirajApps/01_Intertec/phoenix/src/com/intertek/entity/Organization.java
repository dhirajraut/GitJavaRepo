package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Organization generated by hbm2java
 */
public class Organization  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=128) private String name;
     private String description;
     private Set<BusinessUnit> businessUnits = new HashSet<BusinessUnit>(0);

     // Constructors

    /** default constructor */
    public Organization() {
    }

	/** minimal constructor */
    public Organization(String name) {
        this.name = name;
    }
    /** full constructor */
    public Organization(String name, String description, Set<BusinessUnit> businessUnits) {
       this.name = name;
       this.description = description;
       this.businessUnits = businessUnits;
    }
   
    // Property accessors
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<BusinessUnit> getBusinessUnits() {
        return this.businessUnits;
    }
    
    public void setBusinessUnits(Set<BusinessUnit> businessUnits) {
        this.businessUnits = businessUnits;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Organization) ) return false;
		 Organization castOther = ( Organization ) other; 
         
		 return ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         
         
         return result;
   }   


}


