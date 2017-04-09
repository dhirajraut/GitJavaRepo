package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * TestPriceBook generated by hbm2java
 */
public class TestPriceBook  implements java.io.Serializable {

    // Fields    

     private TestPriceBookId testPriceBookId;
     private Date endDate;
     private Test test;

     // Constructors

    /** default constructor */
    public TestPriceBook() {
    }

	/** minimal constructor */
    public TestPriceBook(TestPriceBookId testPriceBookId) {
        this.testPriceBookId = testPriceBookId;
    }
    /** full constructor */
    public TestPriceBook(TestPriceBookId testPriceBookId, Date endDate, Test test) {
       this.testPriceBookId = testPriceBookId;
       this.endDate = endDate;
       this.test = test;
    }
   
    // Property accessors
    public TestPriceBookId getTestPriceBookId() {
        return this.testPriceBookId;
    }
    
    public void setTestPriceBookId(TestPriceBookId testPriceBookId) {
        this.testPriceBookId = testPriceBookId;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Test getTest() {
        return this.test;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TestPriceBook) ) return false;
		 TestPriceBook castOther = ( TestPriceBook ) other; 
         
		 return ( (this.getTestPriceBookId()==castOther.getTestPriceBookId()) || ( this.getTestPriceBookId()!=null && castOther.getTestPriceBookId()!=null && this.getTestPriceBookId().equals(castOther.getTestPriceBookId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTestPriceBookId() == null ? 0 : this.getTestPriceBookId().hashCode() );
         
         
         return result;
   }   


}


