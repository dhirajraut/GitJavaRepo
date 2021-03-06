package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * RevisionNotes generated by hbm2java
 */
public class RevisionNotes  implements java.io.Serializable {

    // Fields    

     private RevisionNoteId revisionNoteId;
     private Date revisionDateTime;
     @Length(min=0, max=128) private String addedByUserName;
     @Length(min=0, max=254) private String revisionNote;
     private User addedBy;

     // Constructors

    /** default constructor */
    public RevisionNotes() {
    }

	/** minimal constructor */
    public RevisionNotes(RevisionNoteId revisionNoteId) {
        this.revisionNoteId = revisionNoteId;
    }
    /** full constructor */
    public RevisionNotes(RevisionNoteId revisionNoteId, Date revisionDateTime, String addedByUserName, String revisionNote, User addedBy) {
       this.revisionNoteId = revisionNoteId;
       this.revisionDateTime = revisionDateTime;
       this.addedByUserName = addedByUserName;
       this.revisionNote = revisionNote;
       this.addedBy = addedBy;
    }
   
    // Property accessors
    public RevisionNoteId getRevisionNoteId() {
        return this.revisionNoteId;
    }
    
    public void setRevisionNoteId(RevisionNoteId revisionNoteId) {
        this.revisionNoteId = revisionNoteId;
    }
    public Date getRevisionDateTime() {
        return this.revisionDateTime;
    }
    
    public void setRevisionDateTime(Date revisionDateTime) {
        this.revisionDateTime = revisionDateTime;
    }
    public String getAddedByUserName() {
        return this.addedByUserName;
    }
    
    public void setAddedByUserName(String addedByUserName) {
        this.addedByUserName = addedByUserName;
    }
    public String getRevisionNote() {
        return this.revisionNote;
    }
    
    public void setRevisionNote(String revisionNote) {
        this.revisionNote = revisionNote;
    }
    public User getAddedBy() {
        return this.addedBy;
    }
    
    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RevisionNotes) ) return false;
		 RevisionNotes castOther = ( RevisionNotes ) other; 
         
		 return ( (this.getRevisionNoteId()==castOther.getRevisionNoteId()) || ( this.getRevisionNoteId()!=null && castOther.getRevisionNoteId()!=null && this.getRevisionNoteId().equals(castOther.getRevisionNoteId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRevisionNoteId() == null ? 0 : this.getRevisionNoteId().hashCode() );
         
         
         
         
         return result;
   }   


}


