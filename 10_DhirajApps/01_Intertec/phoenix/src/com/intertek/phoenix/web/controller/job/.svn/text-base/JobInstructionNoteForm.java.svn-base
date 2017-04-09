package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.JobTestNote;
import com.intertek.phoenix.entity.NoteType;
import com.intertek.phoenix.entity.Visibility;

public class JobInstructionNoteForm extends Form {
    @CascadeValidation
    private JobTestNote note;
    private JobTest jobTest;
    private List<JobTestNote> noteList;
    private String deleteNoteId;
    private String selectedNoteId;
    private String noteId;
    private String orderId;
    private String jobOrderNumber;
    private String requestAction;
    private long jobTestId;
    
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
    
    public long getJobTestId() {
        return jobTestId;
    }

    public void setJobTestId(long jobTestId) {
        this.jobTestId = jobTestId;
    }

    public JobTestNote getNote() {
        return note;
    }

    public void setNote(JobTestNote note) {
        this.note = note;
    }

    public List<JobTestNote> getNoteList() {
        List<JobTestNote> noteTmpList = new ArrayList<JobTestNote>();
       
        if(jobTest.getNotes()!=null && jobTest.getNotes().size()>0){
            JobTestNote jn[] = new JobTestNote[jobTest.getNotes().size()];
            int cnt=0;
            for(JobTestNote note: jobTest.getNotes()){
                jn[cnt]=note;
                cnt++;
            }
            JobTestNote tmpJobTestNote = null;
            for (int i = jn.length - 1; i >= 0; i--) {
                for (int k = i - 1; k >= 0; k--) {
                    if (jn[k].getId().longValue() < jn[i].getId().longValue()) {
                        tmpJobTestNote = jn[i];
                        jn[i] = jn[k];
                        jn[k] = tmpJobTestNote;
                    }
                }
            }
            for (int i = jn.length - 1; i >= 0; i--) {
                noteTmpList.add(jn[i]);
            }
        }
        
        this.noteList=noteTmpList;
        return noteTmpList;
    }

    public void setNoteList(List<JobTestNote> noteList) {
        this.noteList = noteList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public JobTest getJobTest() {
        return jobTest;
    }

    public void setJobTest(JobTest jobTest) {
        this.jobTest = jobTest;
    }

    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

    public String getAddedBy() {
        return note.getAddedUserId();
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
        if (note.getTimestamp() != null)
            return note.getTimestamp().toString();
        else
            return "";
    }

    public String getRequestAction() {
        return requestAction;
    }

    public void setRequestAction(String requestAction) {
        this.requestAction = requestAction;
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

}
