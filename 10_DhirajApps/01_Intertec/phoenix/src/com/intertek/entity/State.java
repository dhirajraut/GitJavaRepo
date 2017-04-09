package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * State generated by hbm2java
 */
public class State  implements java.io.Serializable {

    // Fields    

     private StateId stateId;
     private Country country;
     @Length(min=0, max=32) private String name;
     @Length(min=0, max=2) private String numericCd;

     // Constructors

    /** default constructor */
    public State() {
    }

	/** minimal constructor */
    public State(StateId stateId) {
        this.stateId = stateId;
    }
    /** full constructor */
    public State(StateId stateId, Country country, String name, String numericCd) {
       this.stateId = stateId;
       this.country = country;
       this.name = name;
       this.numericCd = numericCd;
    }
   
    // Property accessors
    public StateId getStateId() {
        return this.stateId;
    }
    
    public void setStateId(StateId stateId) {
        this.stateId = stateId;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getNumericCd() {
        return this.numericCd;
    }
    
    public void setNumericCd(String numericCd) {
        this.numericCd = numericCd;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof State) ) return false;
		 State castOther = ( State ) other; 
         
		 return ( (this.getStateId()==castOther.getStateId()) || ( this.getStateId()!=null && castOther.getStateId()!=null && this.getStateId().equals(castOther.getStateId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStateId() == null ? 0 : this.getStateId().hashCode() );
         
         
         
         return result;
   }   


}


