package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * TestPriceBookId generated by hbm2java
 */
public class TestPriceBookId  implements java.io.Serializable {

    // Fields    

     @NotBlank @Length(min=0, max=96) private String testId;
     @NotBlank @Length(min=0, max=45) private String priceBookId;
     private Date beginDate;

     // Constructors

    /** default constructor */
    public TestPriceBookId() {
    }

    /** full constructor */
    public TestPriceBookId(String testId, String priceBookId, Date beginDate) {
       this.testId = testId;
       this.priceBookId = priceBookId;
       this.beginDate = beginDate;
    }
   
    // Property accessors
    public String getTestId() {
        return this.testId;
    }
    
    public void setTestId(String testId) {
        this.testId = testId;
    }
    public String getPriceBookId() {
        return this.priceBookId;
    }
    
    public void setPriceBookId(String priceBookId) {
        this.priceBookId = priceBookId;
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
		 if ( !(other instanceof TestPriceBookId) ) return false;
		 TestPriceBookId castOther = ( TestPriceBookId ) other; 
         
		 return ( (this.getTestId()==castOther.getTestId()) || ( this.getTestId()!=null && castOther.getTestId()!=null && this.getTestId().equals(castOther.getTestId()) ) )
 && ( (this.getPriceBookId()==castOther.getPriceBookId()) || ( this.getPriceBookId()!=null && castOther.getPriceBookId()!=null && this.getPriceBookId().equals(castOther.getPriceBookId()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTestId() == null ? 0 : this.getTestId().hashCode() );
         result = 37 * result + ( getPriceBookId() == null ? 0 : this.getPriceBookId().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         return result;
   }   


}


