/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 */
package com.intertek.phoenix.entity;

import com.intertek.phoenix.util.CommonUtil;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * A simple note object for various types of notes.
 *
 * @author richard.qin
 * @author eric.nguyen
 */
@Entity
@Table(name = "PHX_NOTE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CARRIER_TYPE",
    discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("GENERAL")
public class Note {

    @Id
    @SequenceGenerator(name = "Note_seq_gen", sequenceName = "NOTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Note_seq_gen")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOTE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private NoteType noteType;

    @Column(name = "VISIBILITY", length = 20)
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    
    @Column(name = "NOTE", columnDefinition = "NVARCHAR2(1024)")
    private String note;

    @Column(name = "NOTE_SUMMARY", length = 128)
    private String noteSummary;

    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;

    @Column(name = "ADDED_USER_ID")
    private String addedUserId;

    public Note() {
    }

    public Note(String note, NoteType type) {
        this.note = note;
        this.noteType = type;
        this.timestamp = new Timestamp(new Date().getTime());

        // TODO review: this is questionable whether CommonUtil should be exposed to
        // an entity, but it seems more convenient if it does.
        // TODO Please remove this
        this.addedUserId = CommonUtil.getCurrentUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAddedUserId() {
        return addedUserId;
    }

    public void setAddedUserId(String addedUserId) {
        this.addedUserId = addedUserId;
    }

    public String getNoteSummary() {
        return noteSummary;
    }

    public void setNoteSummary(String noteSummary) {
        this.noteSummary = noteSummary;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

}
