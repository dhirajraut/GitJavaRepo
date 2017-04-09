package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * SlateTest generated by hbm2java
 */
public class SlateTest  implements java.io.Serializable {

    // Fields    

     private SlateTestId slateTestId;
     private Integer testNumber;
     private Slate slate;
     private Test test;

     // Constructors

    /** default constructor */
    public SlateTest() {
    }

	/** minimal constructor */
    public SlateTest(SlateTestId slateTestId) {
        this.slateTestId = slateTestId;
    }
    /** full constructor */
    public SlateTest(SlateTestId slateTestId, Integer testNumber, Slate slate, Test test) {
       this.slateTestId = slateTestId;
       this.testNumber = testNumber;
       this.slate = slate;
       this.test = test;
    }
   
    // Property accessors
    public SlateTestId getSlateTestId() {
        return this.slateTestId;
    }
    
    public void setSlateTestId(SlateTestId slateTestId) {
        this.slateTestId = slateTestId;
    }
    public Integer getTestNumber() {
        return this.testNumber;
    }
    
    public void setTestNumber(Integer testNumber) {
        this.testNumber = testNumber;
    }
    public Slate getSlate() {
        return this.slate;
    }
    
    public void setSlate(Slate slate) {
        this.slate = slate;
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
		 if ( !(other instanceof SlateTest) ) return false;
		 SlateTest castOther = ( SlateTest ) other; 
         
		 return ( (this.getSlateTestId()==castOther.getSlateTestId()) || ( this.getSlateTestId()!=null && castOther.getSlateTestId()!=null && this.getSlateTestId().equals(castOther.getSlateTestId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSlateTestId() == null ? 0 : this.getSlateTestId().hashCode() );
         
         
         
         return result;
   }   


}


