package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * TestLanguageId generated by hbm2java
 */
public class TestLanguageId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=96) private String testId;
     @NotBlank @Length(min=0, max=9) private String languageCD;
     private Date beginDate;

     // Constructors

    /** default constructor */
    public TestLanguageId() {
    }

    /** full constructor */
    public TestLanguageId(String testId, String languageCD, Date beginDate) {
       this.testId = testId;
       this.languageCD = languageCD;
       this.beginDate = beginDate;
    }
   
    // Property accessors
    public String getTestId() {
        return this.testId;
    }
    
    public void setTestId(String testId) {
        this.testId = testId;
    }
    public String getLanguageCD() {
        return this.languageCD;
    }
    
    public void setLanguageCD(String languageCD) {
        this.languageCD = languageCD;
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
		 if ( !(other instanceof TestLanguageId) ) return false;
		 TestLanguageId castOther = ( TestLanguageId ) other; 
         
		 return ( (this.getTestId()==castOther.getTestId()) || ( this.getTestId()!=null && castOther.getTestId()!=null && this.getTestId().equals(castOther.getTestId()) ) )
 && ( (this.getLanguageCD()==castOther.getLanguageCD()) || ( this.getLanguageCD()!=null && castOther.getLanguageCD()!=null && this.getLanguageCD().equals(castOther.getLanguageCD()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTestId() == null ? 0 : this.getTestId().hashCode() );
         result = 37 * result + ( getLanguageCD() == null ? 0 : this.getLanguageCD().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         return result;
   }   


}

