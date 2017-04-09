/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
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
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobOrderAttachment;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;

/**
 * @author patni
 * 
 */
public class JobOrderAttachPopupFormController extends SimpleFormController {
    private static Log log = LogFactory.getLog(JobOrderAttachPopupFormController.class);

    public JobOrderAttachPopupFormController() {
        super();
        setCommandClass(JobOrderAttachForm.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @SuppressWarnings("unused")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        String errorMsg = null;
        try {

            JobOrderService jobOrderService = ServiceManager.getJobOrderService();
            JobOrderAttachForm attachFileForm = (JobOrderAttachForm) command;
            List<JobOrderAttachment> attachList = attachFileForm.getAttachmentList();
            CEJobContract jobContract = ControllerUtil.loadAndUpdate(attachFileForm.getJobContract(), CEJobContract.class);
            Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
            CEJobOrder jobOrder = jobDao.find(attachFileForm.getJobOrderNumber());
            PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
            String path = "";
            if (pRB != null) {
                path = pRB.getString(com.intertek.util.Constants.jobcontractfilepath);
            }

            // saving job order attach
            String saveFlag = request.getParameter("saveFlag");
            if (saveFlag != null && saveFlag.trim().length() > 0 && saveFlag.equals("true")) {
                errorMsg = "problem.saving.jo.attach";
                ControllerUtil.updateCollection(jobContract, attachFileForm.getJobContract(), CEJobContract.class, "attachments");
                for (JobOrderAttachment at : attachFileForm.getJobContract().getAttachments()) {
                    DaoManager.getDao(JobOrderAttachment.class).merge(at);
                }
                attachFileForm.setJobContract(jobContract);
                return showForm(request, response, errors);
            }

            // Deleting a JobOrderAttach
            String deleteAttachIdstr = request.getParameter("deleteAttachId");
            if (deleteAttachIdstr != null && deleteAttachIdstr.trim().length() > 0) {
                errorMsg = "problem.delete.jo.attach";
                long deleteAttachId = Long.parseLong(deleteAttachIdstr);
                for (JobOrderAttachment attachment : attachList) {
                    if (attachment.getId() == deleteAttachId) {
                        Dao<JobOrderAttachment> jDao = DaoManager.getDao(JobOrderAttachment.class);
                        JobOrderAttachment tattachment = jDao.find(attachment.getId());
                        jobOrderService.removeAttachments(jobOrder, tattachment);
                        attachFileForm.setJobContract(jobContract);
                        break;
                    }
                }
                attachFileForm.setDeleteAttachId("");
                return showForm(request, response, errors);
            }

            for (HashMap map : attachFileForm.getFileInfoMapList()) {
                MultipartFile file = (MultipartFile) map.get("file");
                String desc = (String) map.get("desc");
                JobOrderAttachment attachment = new JobOrderAttachment();
                if (file != null && !file.isEmpty() && file.getSize() > 0 && file.getOriginalFilename() != null && !"".equals(file.getOriginalFilename())) {

                    if (attachFileForm.getInputFieldId() == null || attachFileForm.getInputFieldId().trim().equals("")) {
                        attachFileForm.setInputFieldId(attachFileForm.getInputFieldId() + file.getOriginalFilename());
                    }
                    else {
                        attachFileForm.setInputFieldId(attachFileForm.getInputFieldId() + ";" + file.getOriginalFilename());
                    }

                    attachment.setFilename(file.getOriginalFilename());
                    attachment.setDescription(desc);
                    jobContract.addAttachment(attachment);
                    jobOrderService.addAttachment(jobContract, attachment, path, file);
                }
                attachFileForm.setAttachment(new JobOrderAttachment());
            }

        }
        catch (Exception e) {
            log.error(e.getMessage());
            errors.reject(errorMsg == null ? "problem.general.page" : errorMsg, new Object[] { "try again " }, "");
            return showForm(request, response, errors);
        }
        return showForm(request, response, errors);
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
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object, org.springframework.validation.BindException)
     */
    @SuppressWarnings("unchecked")
    protected void onBind(HttpServletRequest req, Object command, BindException errors) {
        JobOrderAttachForm addAttach = (JobOrderAttachForm) command;
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
                    hash.put("file", multipart);
                    hash.put("desc", desc);
                    fileInfoMapList.add(hash);
                }
            }
        }
        addAttach.setFileInfoMapList(fileInfoMapList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        JobOrderAttachForm attachFileForm = new JobOrderAttachForm();
        try {
            String divName = request.getParameter("divName");
            String inputFieldId = request.getParameter("inputFieldId");
            String jobOrderNumber = request.getParameter("jobNumber");
            String jobContractId = request.getParameter("customerId");
            long contractId = Long.parseLong(jobContractId);
            JobOrderService jobOrderService = ServiceManager.getJobOrderService();
            CEJobContract ceJobContract = jobOrderService.findById(CEJobContract.class, contractId);
            attachFileForm.setJobOrderNumber(jobOrderNumber);
            JobOrderAttachment attachment = new JobOrderAttachment();
            attachment.setJobContractId(ceJobContract.getId());
            attachment.setJobContract(ceJobContract);
            attachFileForm.setJobContract(ceJobContract);
            attachFileForm.setAttachment(attachment);
            attachFileForm.setDivName(divName);
            attachFileForm.setInputFieldId(inputFieldId);
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            throw new ServiceException("Error while loading the page", new Object[] { e.getMessage() }, e);
        }
        return attachFileForm;
    }
}
