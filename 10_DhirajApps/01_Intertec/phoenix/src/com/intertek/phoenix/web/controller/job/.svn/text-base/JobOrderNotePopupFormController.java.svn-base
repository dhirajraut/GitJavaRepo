/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

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
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobOrderNote;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;

/**
 * @author patni
 * 
 */
public class JobOrderNotePopupFormController extends SimpleFormController {
    private static Log log = LogFactory.getLog(JobOrderNotePopupFormController.class);

    public JobOrderNotePopupFormController() {
        super();
        setCommandClass(JobOrderNoteForm.class);
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
        String errorMsg = null;
        try {
            JobOrderService jobOrderService = ServiceManager.getJobOrderService();
            JobOrderNoteForm noteForm = (JobOrderNoteForm) command;
            List<JobOrderNote> noteList = noteForm.getNoteList();
            CEJobContract jobContract = ControllerUtil.loadAndUpdate(noteForm.getJobContract(), CEJobContract.class);
            // Selecting a JobOrderNote
            String selectedNoteIdstr = request.getParameter("selectedNoteId");
            if (selectedNoteIdstr != null && selectedNoteIdstr.trim().length() > 0) {
                errorMsg = "problem.fetch.note";
                long selectedNoteId = Long.parseLong(selectedNoteIdstr);
                for (JobOrderNote n : noteList) {
                    if (n.getId() == selectedNoteId) {
                        noteForm.setNote(n);
                    }
                }
                noteForm.setSelectedNoteId("");
                return showForm(request, response, errors);
            }

            // Deleting a JobOrderNote
            String deleteNoteIdstr = request.getParameter("deleteNoteId");
            if (deleteNoteIdstr != null && deleteNoteIdstr.trim().length() > 0) {
                errorMsg = "problem.delete.note";
                long deleteNoteId = Long.parseLong(deleteNoteIdstr);

                for (JobOrderNote n : noteList) {
                    if (n.getId() == deleteNoteId) {
                        JobOrderNote tnote = ControllerUtil.findById(JobOrderNote.class, n.getId());
                        jobOrderService.removeNotes(jobContract, tnote);
                        noteForm.setJobContract(jobContract);

                    }

                }
                noteForm.setDeleteNoteId("");
                return showForm(request, response, errors);
            }

            String reset = request.getParameter("reset");
            if (reset != null && reset.trim().length() > 0) {
                errorMsg = "problem.reset.note";
                noteForm.getNote().setNote("");
                return showForm(request, response, errors);
            }
            String requestAction = request.getParameter("requestAction");
            // add or update
            if (requestAction != null && "save".equals(request.getParameter("requestAction"))) {
                errorMsg = "problem.update.note";
                Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
                CEJobOrder jobOrder = jobDao.find(noteForm.getJobNumber());

                JobOrderNote tmpnote = noteForm.getNote();
                tmpnote.setJobContract(jobContract);
                tmpnote.setTimestamp(new Timestamp(System.currentTimeMillis()));
                if (jobContract == null) {
                    jobContract = new CEJobContract();
                }
                if (tmpnote.getId() == null || tmpnote.getId().intValue() == 0) {
                    jobContract.addNote(tmpnote);
                    jobOrderService.addNotes(jobContract, tmpnote);
                }
                else {
                    jobOrderService.updateContractNote(jobOrder, jobContract, tmpnote);
                }
                noteForm.setJobContract(jobContract);
                noteForm.setNote(new JobOrderNote());
                noteForm.setRequestAction("none");
            }

        }
        catch (Exception e) {
            log.error(e.getMessage());
            errors.reject(errorMsg == null ? "problem.general.page" : errorMsg, new Object[] { "try again" }, "");

        }
        return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#suppressValidation(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object)
     */
    @SuppressWarnings("deprecation")
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
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
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        JobOrderService jobOrderService = ServiceManager.getJobOrderService();
        JobOrderNoteForm noteForm = new JobOrderNoteForm();
        try {
            String jobContractId = request.getParameter("id");
            String jobNumber = request.getParameter("jobNumber");
            long contractId = Long.parseLong(jobContractId);
            CEJobContract ceJobContract = jobOrderService.findById(CEJobContract.class, contractId);
            noteForm.setJobContractId(jobContractId);
            JobOrderNote note = new JobOrderNote();
            note.setJobContractId(ceJobContract.getId());
            note.setJobContract(ceJobContract);
            noteForm.setJobContract(ceJobContract);
            noteForm.setNote(note);
            noteForm.setJobNumber(jobNumber);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw new ServiceException("Error while loading the page", new Object[] { e.getMessage() }, e);
        }
        return noteForm;
    }
}
