package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Control generated by hbm2java
 */
public class Control  implements java.io.Serializable {

    // Fields    

     private ControlId controlId;
     @Length(min=0, max=450) private String rbKey;
     @Length(min=0, max=384) private String attributes;
     @Length(min=0, max=30) private String controlType;
     @Length(min=0, max=384) private String parameters;
     private Integer sortOrderNum;
     @Length(min=0, max=192) private String helpUrl;
     @Length(min=0, max=12) private String visibility;
     private Date endDate;

     // Constructors

    /** default constructor */
    public Control() {
    }

	/** minimal constructor */
    public Control(ControlId controlId) {
        this.controlId = controlId;
    }
    /** full constructor */
    public Control(ControlId controlId, String rbKey, String attributes, String controlType, String parameters, Integer sortOrderNum, String helpUrl, String visibility, Date endDate) {
       this.controlId = controlId;
       this.rbKey = rbKey;
       this.attributes = attributes;
       this.controlType = controlType;
       this.parameters = parameters;
       this.sortOrderNum = sortOrderNum;
       this.helpUrl = helpUrl;
       this.visibility = visibility;
       this.endDate = endDate;
    }
   
    // Property accessors
    public ControlId getControlId() {
        return this.controlId;
    }
    
    public void setControlId(ControlId controlId) {
        this.controlId = controlId;
    }
    public String getRbKey() {
        return this.rbKey;
    }
    
    public void setRbKey(String rbKey) {
        this.rbKey = rbKey;
    }
    public String getAttributes() {
        return this.attributes;
    }
    
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    public String getControlType() {
        return this.controlType;
    }
    
    public void setControlType(String controlType) {
        this.controlType = controlType;
    }
    public String getParameters() {
        return this.parameters;
    }
    
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
    public Integer getSortOrderNum() {
        return this.sortOrderNum;
    }
    
    public void setSortOrderNum(Integer sortOrderNum) {
        this.sortOrderNum = sortOrderNum;
    }
    public String getHelpUrl() {
        return this.helpUrl;
    }
    
    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }
    public String getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Control) ) return false;
		 Control castOther = ( Control ) other; 
         
		 return ( (this.getControlId()==castOther.getControlId()) || ( this.getControlId()!=null && castOther.getControlId()!=null && this.getControlId().equals(castOther.getControlId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getControlId() == null ? 0 : this.getControlId().hashCode() );
         
         
         
         
         
         
         
         
         return result;
   }   


}

