/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.JobOrderNote;
import com.intertek.phoenix.entity.NoteType;
import com.intertek.phoenix.entity.Visibility;

/**
 * @author patni
 * 
 */
public class JobOrderNoteForm extends Form {

    @CascadeValidation
    private JobOrderNote note;
    private List<JobOrderNote> noteList;
    private String deleteNoteId;
    private String selectedNoteId;
    private String noteId;
    private String orderId;
    private String requestAction;
    private String jobContractId;
    private CEJobContract jobContract;

    public JobOrderNote getNote() {
        return note;
    }

    public void setNote(JobOrderNote note) {
        this.note = note;
    }

    public String getRequestAction() {
        return requestAction;
    }

    public void setRequestAction(String requestAction) {
        this.requestAction = requestAction;
    }

    public List<JobOrderNote> getNoteList() {
        List<JobOrderNote> noteTmpList = new ArrayList<JobOrderNote>();
        if (jobContract.getNotes() != null && jobContract.getNotes().size() > 0) {
            for (JobOrderNote note : jobContract.getNotes()) {
                noteTmpList.add(note);
            }
        }
        this.noteList = noteTmpList;
        return noteTmpList;
    }

    public void setNoteList(List<JobOrderNote> noteList) {
        this.noteList = noteList;
    }

    public String getDeleteNoteId() {
        return deleteNoteId;
    }

    public void setDeleteNoteId(String deleteNoteId) {
        this.deleteNoteId = deleteNoteId;
    }

    public String getSelectedNoteId() {
        return selectedNoteId;
    }

    public void setSelectedNoteId(String selectedNoteId) {
        this.selectedNoteId = selectedNoteId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddedBy() {
        if (note != null && note.getAddedUserId() != null) {
            return note.getAddedUserId();
        }
        else {
            return "";
        }
    }

    public String getNoteSummary() {
        return note.getNoteSummary();
    }

    public void setNoteSummary(String noteSummary) {
        note.setNoteSummary(noteSummary);
    }

    public String getNoteDetail() {
        return note.getNote();
    }

    public void setNoteDetail(String noteDetail) {
        this.note.setNote(noteDetail);
    }

    public String getTimeAdded() {
        if (note != null && note.getTimestamp() != null)
            return note.getTimestamp().toString();
        else
            return "";
    }

    public List<Field> getVisibilityFields() {
        return refenceDataSerivce.getVisibilityFields();
    }

    public List<Field> getNoteTypes() {
        return refenceDataSerivce.getNoteTypeFields();
    }

    public String getNoteType() {
        if (note.getNoteType() != null)
            return note.getNoteType().getValue();
        else
            return "";
    }

    public String getVisibility() {
        if (note.getVisibility() != null) {
            return note.getVisibility().getValue();
        }
        else
            return "";
    }

    public void setNoteType(String noteType) {
        if (noteType != null & !noteType.equals("")) {
            int noteValue = Integer.parseInt(noteType);
            NoteType[] list = NoteType.list();
            for (NoteType nt : list) {
                if (nt.value() == noteValue) {
                    getNote().setNoteType(nt);
                    break;
                }
            }
        }
    }

    public void setVisibility(String visibility) {
        if (visibility != null & !visibility.equals("")) {
            Visibility[] list = Visibility.list();
            for (Visibility vs : list) {
                if (vs.getValue().equals(visibility)) {
                    getNote().setVisibility(vs);
                    break;
                }
            }
        }
    }

    public String getJobContractId() {
        return jobContractId;
    }

    public void setJobContractId(String jobContractId) {
        this.jobContractId = jobContractId;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }

}
