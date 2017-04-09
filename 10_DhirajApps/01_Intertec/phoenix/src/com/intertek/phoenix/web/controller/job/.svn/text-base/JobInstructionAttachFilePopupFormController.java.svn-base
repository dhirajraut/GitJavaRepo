package com.intertek.phoenix.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.exception.ServiceException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.JobTestAttachment;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;

public class JobInstructionAttachFilePopupFormController extends SimpleFormController {

    /** The log. */
    private static Log log = LogFactory.getLog(JobInstructionAttachFilePopupFormController.class);

    /**
     * Instantiates a new Job Instruction Attach File Popup Form Controller.
     */
    public JobInstructionAttachFilePopupFormController() {
        super();
        setCommandClass(JobInstructionAttachFileForm.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        String errorMsg="";
        try {
            JobInstructionAttachFileForm attachFileForm = (JobInstructionAttachFileForm) command;
            String saveFlag = request.getParameter("saveFlag");
            if (saveFlag != null && saveFlag.trim().length() > 0 && saveFlag.equals("true")) {
                errorMsg = "problem.save.attachment";
                doSave(attachFileForm);
                return showForm(request, response, errors);
            }
            // Deleting a JobContractAttach
            String deleteAttachIdstr = request.getParameter("deleteAttachId");
            if (deleteAttachIdstr != null && deleteAttachIdstr.trim().length() > 0) {
                errorMsg = "problem.delete.attachment";
                doDelete(attachFileForm);
                return showForm(request, response, errors);
            }
            errorMsg = "problem.upload.attachment";
            doFileUpload(attachFileForm);
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

    private void doSave(JobInstructionAttachFileForm attachFileForm) throws Exception{
        
        JobTest jobTest=ControllerUtil.loadAndUpdate(attachFileForm.getJobTest(),JobTest.class);
        ControllerUtil.updateCollection(jobTest, attachFileForm.getJobTest(), JobTest.class,"attachments");
        for(JobTestAttachment at: attachFileForm.getJobTest().getAttachments()){
            DaoManager.getDao(JobTestAttachment.class).merge(at);
        }
        attachFileForm.setJobTest(jobTest);
    }
    
    private void doDelete(JobInstructionAttachFileForm attachFileForm) throws Exception{
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        long deleteAttachId = Long.parseLong(attachFileForm.getDeleteAttachId());
        for (JobTestAttachment attachment : attachFileForm.getAttachmentList()) {
            if (attachment.getId() == deleteAttachId) {
                jobSrvc.removeAttachments(attachFileForm.getJobTest(), attachment);
                break;
            }
        }
        attachFileForm.setDeleteAttachId("");
    }
    
    @SuppressWarnings("unchecked")
    private void doFileUpload(JobInstructionAttachFileForm attachFileForm) throws Exception{
        PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
        @SuppressWarnings("unused")
        String path = "";
        if (pRB != null) {
            path = pRB.getString(com.intertek.util.Constants.jobcontractfilepath);
        }
        List<JobTestAttachment> attachList = attachFileForm.getAttachmentList();
        if (attachList == null) {
            attachList = new ArrayList<JobTestAttachment>();
        }
        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        JobTest jobTest = ControllerUtil.loadAndUpdate(attachFileForm.getJobTest(), JobTest.class);
        for (HashMap map : attachFileForm.getFileInfoMapList()) {
            MultipartFile file = (MultipartFile) map.get("file");
            String desc = (String) map.get("desc");
            attachFileForm.setJobTest(jobTest);
            JobTestAttachment attachment = new JobTestAttachment();
            if (file != null && !file.isEmpty() && file.getSize() > 0 && file.getOriginalFilename() != null && !"".equals(file.getOriginalFilename())) {
                attachment.setFilename(file.getOriginalFilename());
                attachment.setDescription(desc);
                if (attachFileForm.getInputFieldId() == null || attachFileForm.getInputFieldId().trim().equals(""))
                    attachFileForm.setInputFieldId(attachFileForm.getInputFieldId() + file.getOriginalFilename());
                else
                    attachFileForm.setInputFieldId(attachFileForm.getInputFieldId() + ";" + file.getOriginalFilename());
                if (attachFileForm.getAttachmentList() == null) {
                    attachFileForm.setAttachmentList(attachList);
                }
                attachFileForm.getAttachmentList().add(attachment);
                jobSrvc.addAttachment(jobTest, attachment, path, file);
            }
        }

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
     * @see org.springframework.web.servlet.mvc.BaseCommandController#onBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    @SuppressWarnings("unchecked")
    protected void onBind(HttpServletRequest req, Object command, BindException errors) {
        JobInstructionAttachFileForm addAttach = (JobInstructionAttachFileForm) command;
        MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) req;
        Iterator fileNames = multiReq.getFileNames();
        List<HashMap> fileInfoMapList = new ArrayList<HashMap>();
        while (fileNames.hasNext()) {
            String fileName = (String) fileNames.next();
            if (fileName != null) {
                MultipartFile multipart = multiReq.getFile(fileName);
                if (multipart != null && !multipart.isEmpty()) {
                    HashMap hash = new HashMap();
                    @SuppressWarnings("unused")
                    int counter = Integer.parseInt(fileName.substring(4));
                    String desc = req.getParameter("desc" + fileName.substring(4));
                    // String type =
                    // req.getParameter("type"+fileName.substring(4));
                    hash.put("file", multipart);
                    hash.put("desc", desc);
                    // hash.put("type", type);
                    fileInfoMapList.add(hash);
                }

            }
        }
        addAttach.setFileInfoMapList(fileInfoMapList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.JobInstructionAttachFilePopupFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        JobInstructionAttachFileForm attachFileForm = new JobInstructionAttachFileForm();
        String divName = request.getParameter("divName");
        String inputFieldId = request.getParameter("inputFieldId");
        String jobOrderNumber = request.getParameter("JobOrderNumber");
        String jobTestIdstr = request.getParameter("jobTestId");
        try {
            if (jobOrderNumber != null && jobOrderNumber.trim().length() > 0 && jobTestIdstr!=null) {
                attachFileForm.setJobOrderNumber(jobOrderNumber);
                long jobTestId=Long.parseLong(jobTestIdstr);
                attachFileForm.setJobTestId(jobTestId);
                JobOrderService jobOrderService = ServiceManager.getJobOrderService();
                JobTest jobTest = jobOrderService.findById(JobTest.class, jobTestId) ;
                attachFileForm.setJobTest(jobTest);                                       
                attachFileForm.setDivName(divName);
                attachFileForm.setInputFieldId(inputFieldId);
            }
        }
        catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getParams(), e);
        }
        return attachFileForm;
    }
}
