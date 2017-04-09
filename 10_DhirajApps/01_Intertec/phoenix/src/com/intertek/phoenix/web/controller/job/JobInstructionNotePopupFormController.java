package com.intertek.phoenix.web.controller.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.exception.ServiceException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.JobTestNote;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;

public class JobInstructionNotePopupFormController extends SimpleFormController {
    /** The log. */
    private static Log log = LogFactory.getLog(JobInstructionNotePopupFormController.class);

    
    public JobInstructionNotePopupFormController() {
        super();
        setCommandClass(JobInstructionNoteForm.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        String errorMsg = "";
        JobInstructionNoteForm noteForm = (JobInstructionNoteForm) command;
        String selectedNoteIdstr = request.getParameter("selectedNoteId");
        try{
            if (selectedNoteIdstr != null && selectedNoteIdstr.trim().length() > 0) {
                errorMsg = "problem.select.note";
                doSelectNote(noteForm);
                return showForm(request, response, errors);
            }
            // Deleting a JobContractNote
            String deleteNoteIdstr = request.getParameter("deleteNoteId");        
            if (deleteNoteIdstr != null && deleteNoteIdstr.trim().length() > 0) {
                errorMsg = "problem.delete.note";
                doDeleteNote(noteForm);
                return showForm(request, response, errors);
            }
    
            String reset = request.getParameter("reset");
            if (reset != null && reset.trim().length() > 0) {
                noteForm.getNote().setNote("");
                return showForm(request, response, errors);
            }
            String requestAction = request.getParameter("requestAction");
            // add or update
            if (requestAction != null && "save".equals(request.getParameter("requestAction"))) {
                errorMsg = "problem.save.note";
                doSaveNote(noteForm);
                
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            log.error(t.getMessage());
            errors.reject(errorMsg.equals("") ? "problem.general.page" : errorMsg);
            return showForm(request, response, errors);
        }
        return showForm(request, response, errors);
        // return new ModelAndView();

    }

    private void doDeleteNote(JobInstructionNoteForm noteForm) throws Exception{
        //Dao<JobTestNote> jDao = DaoManager.getDao(JobTestNote.class);
        Long deleteNoteId = Long.valueOf(noteForm.getDeleteNoteId());
        JobTestNote tnote = ControllerUtil.findById(JobTestNote.class, deleteNoteId);
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        JobTest jobTest = ControllerUtil.loadAndUpdate(noteForm.getJobTest(),JobTest.class);
        jobSrvc.removeNotes(jobTest, tnote);
        System.out.println("after merge");
        noteForm.setJobTest(jobTest);
        noteForm.setDeleteNoteId("");
    }
    
    private void doSelectNote(JobInstructionNoteForm noteForm){
        long selectedNoteId = Long.parseLong(noteForm.getSelectedNoteId());
        for (JobTestNote n : noteForm.getNoteList()) {
            if (n.getId() == selectedNoteId) {
                noteForm.setNote(n);
            }
        }
        noteForm.setSelectedNoteId("");
    }
    
    private void doSaveNote(JobInstructionNoteForm noteForm) throws Exception{
        JobTestNote tmpnote = noteForm.getNote();
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        JobTest jobTest = ControllerUtil.findById(JobTest.class, noteForm.getJobTestId());
        tmpnote.setJobTest(jobTest);
        tmpnote.setTimestamp(new Timestamp(System.currentTimeMillis()));
        if (tmpnote.getId() == null || tmpnote.getId().intValue() == 0) {
            jobTest.addNote(tmpnote);
            jobSrvc.addNotes(jobTest, tmpnote);
        }
        else {
            jobSrvc.updateJobTestNote( jobTest, tmpnote);
        }
        //jobTest = jobSrvc.findById(JobTest.class, noteForm.getJobTestId());
        noteForm.setJobTest(jobTest);
        noteForm.setNote(new JobTestNote());
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#suppressValidation(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        // String refreshing = request.getParameter("refreshing");

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#onBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */protected void onBind(HttpServletRequest req, Object command, BindException errors) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.JobInstructionNotePopupFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        JobInstructionNoteForm noteForm = new JobInstructionNoteForm();
        String jobOrderNumber = request.getParameter("JobOrderNumber");
        String jobTestIdstr = request.getParameter("jobTestId");        
        try {
            if (jobTestIdstr != null && jobTestIdstr.trim().length() > 0 ) {
                noteForm.setJobOrderNumber(jobOrderNumber);
                long jobTestId = Long.parseLong(jobTestIdstr);
                noteForm.setJobTestId(jobTestId);
                JobOrderService jobOrderService = ServiceManager.getJobOrderService();
                JobTest jobTest = jobOrderService.findById(JobTest.class, jobTestId) ;
                noteForm.setJobTest(jobTest); 
                JobTestNote note = new JobTestNote();
                noteForm.setNote(note);
                noteForm.setJobTestId(jobTestId);
            }
        }
        catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getParams(), e);
        }
        return noteForm;
    }
    
    
}
