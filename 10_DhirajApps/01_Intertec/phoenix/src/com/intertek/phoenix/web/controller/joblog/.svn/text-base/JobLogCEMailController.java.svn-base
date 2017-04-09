package com.intertek.phoenix.web.controller.joblog;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.common.EmailService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;
import com.intertek.phoenix.util.CommonUtil;

public class JobLogCEMailController extends SimpleFormController {
    public JobLogCEMailController() {
        super();
        setCommandClass(JobLogCEMailForm.class);
        setSessionForm(true);
    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        EmailService emailService = (EmailService) ServiceLocator.getInstance().getBean("eMailService");
        JobLogCEMailForm jobLogCEFMailForm = (JobLogCEMailForm) command;
        String refreshing = request.getParameter("refreshing");
        int emailIndex = jobLogCEFMailForm.getEmailIndex();
        if ((refreshing != null) && "true".equals(refreshing)) {
            CEJobEmail[] jobEmails = jobLogCEFMailForm.getJobEmails();
            CEJobEmail jobEmail = jobEmails[emailIndex];
            String introduction = jobEmail.getIntroduction();
            if (introduction != null && (!introduction.trim().equals(""))) {
                String introToDisplay = jobEmail.getIntroduction().replaceAll(System.getProperty("line.separator"), "<br>");
                jobEmail.setIntroductionToDisplay(introToDisplay);
            }
            return showForm(request, response, errors);
        }
        if ((refreshing != null) && "sendEmail".equals(refreshing)) {
            CEJobEmail jobEmail = jobLogCEFMailForm.getJobEmails()[emailIndex];
            if (jobEmail != null) {
                emailService.sendEmail(jobEmail);
                jobEmail.setEmailSentFlag(true);
            }
        }
        return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
     *      javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Form.getCurrentUserFormat());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    private CEJobEmail[] getContractList(HttpServletRequest request) {
        JobLogCEForm jobCeSearch = (JobLogCEForm) request.getSession()
                .getAttribute("com.intertek.phoenix.web.controller.joblog.JobLogCEFormController.FORM.command");
        JobLogCESearchResult[] res = jobCeSearch.getResult();
        String selectedRow = request.getParameter("selectedRow");
        EmailService emailService = (EmailService) ServiceLocator.getInstance().getBean("eMailService");
        return emailService.createCeJobEmailList(res, selectedRow);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     *      (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        CEJobEmail[] contractList = getContractList(request);
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
        JobLogCEMailForm jobLogCEFMailForm = new JobLogCEMailForm();
        jobLogCEFMailForm.setUser(user);
        jobLogCEFMailForm.setTimeFormat(CommonUtil.getConfigValue("email.timeformat"));

        try {
            jobLogCEFMailForm.setJobEmails(contractList);
            if (contractList != null & contractList.length > 0) {
                jobLogCEFMailForm.setEmailCount(contractList.length);
                jobLogCEFMailForm.setEmailIndex(0);
            }
            else {
                throw new ServiceException("No Data found ");
            }
        }
        catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getParams(), e);
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw new ServiceException("generic.error", new Object[] { t.getMessage() }, t);
        }
        return jobLogCEFMailForm;
    }
}
