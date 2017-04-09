package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * InspectionControlId generated by hbm2java
 */
public class InspectionControlId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=375) private String serviceName;
     @NotBlank @Length(min=0, max=96) private String questionId;
     @NotBlank @Length(min=0, max=96) private String objectName;
     private Date beginDate;

     // Constructors

    /** default constructor */
    public InspectionControlId() {
    }

    /** full constructor */
    public InspectionControlId(String serviceName, String questionId, String objectName, Date beginDate) {
       this.serviceName = serviceName;
       this.questionId = questionId;
       this.objectName = objectName;
       this.beginDate = beginDate;
    }
   
    // Property accessors
    public String getServiceName() {
        return this.serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getQuestionId() {
        return this.questionId;
    }
    
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getObjectName() {
        return this.objectName;
    }
    
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InspectionControlId) ) return false;
		 InspectionControlId castOther = ( InspectionControlId ) other; 
         
		 return ( (this.getServiceName()==castOther.getServiceName()) || ( this.getServiceName()!=null && castOther.getServiceName()!=null && this.getServiceName().equals(castOther.getServiceName()) ) )
 && ( (this.getQuestionId()==castOther.getQuestionId()) || ( this.getQuestionId()!=null && castOther.getQuestionId()!=null && this.getQuestionId().equals(castOther.getQuestionId()) ) )
 && ( (this.getObjectName()==castOther.getObjectName()) || ( this.getObjectName()!=null && castOther.getObjectName()!=null && this.getObjectName().equals(castOther.getObjectName()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getServiceName() == null ? 0 : this.getServiceName().hashCode() );
         result = 37 * result + ( getQuestionId() == null ? 0 : this.getQuestionId().hashCode() );
         result = 37 * result + ( getObjectName() == null ? 0 : this.getObjectName().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         return result;
   }   


}


