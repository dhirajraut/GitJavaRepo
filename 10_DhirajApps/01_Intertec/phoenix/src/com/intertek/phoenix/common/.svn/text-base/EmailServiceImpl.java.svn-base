/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import com.intertek.entity.Contact;
import com.intertek.entity.Contract;
import com.intertek.entity.Customer;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.mail.VelocityMimeMessagePreparator;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobOrderNote;
import com.intertek.phoenix.util.DateUtil;
import com.intertek.phoenix.web.controller.joblog.CEJobEmail;
import com.intertek.phoenix.web.controller.joblog.JobLogCEMailForm;
import com.intertek.phoenix.web.controller.joblog.JobLogCESearchResult;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

/**
 * The class that exposes Email related functionalities for CE.
 * 
 * @author Patni
 */
public class EmailServiceImpl implements EmailService {

    /**
     * Send the email based on the data available in CEJobEMail
     * <p>
     * 
     * @param CEJobEmail
     */
    public void sendEmail(CEJobEmail jobEmail) throws MailException {
        JavaMailSender sender = (JavaMailSender) ServiceLocator.getInstance().getBean("sender");
        VelocityMimeMessagePreparator preparator = (VelocityMimeMessagePreparator) ServiceLocator.getInstance().getBean("passwordMailPreparator");
        Map<String, CEJobEmail> data = new HashMap<String, CEJobEmail>();
        data.put("jobEmail", jobEmail);
        preparator.setTo(jobEmail.getTo());
        preparator.setCc(jobEmail.getCc());
        preparator.setSubject(jobEmail.getSubject());
        preparator.setData(data);
        sender.send(preparator);
    }

    /**
     * create a list of CEJobEmail objects based on search result and the
     * selected row.
     * <p>
     * This method will form the email header and email body based on the
     * JobOrder & customer details
     * 
     * @param JobLogCESearchResult[],
     *            String selectedRow
     * @return A new CEJJobMail
     */
    public CEJobEmail[] createCeJobEmailList(JobLogCESearchResult[] res, String selectedRow) {
        Map<String, JobLogCESearchResult> map = new HashMap<String, JobLogCESearchResult>();
        int[] row = new int[0];
        if (selectedRow != null) {
            String[] row1 = selectedRow.split("~");
            row = new int[row1.length];
            for (int i = 0; i < row1.length; i++) {
                row[i] = Integer.parseInt(row1[i]);
            }
        }
        int cnt = -1;
        for (JobLogCESearchResult jobLogCESearchResult : res) {
            cnt++;
            boolean flg = false;
            for (int i : row) {
                if (i == cnt) {
                    flg = true;
                }
            }
            if (flg == false) {
                continue;
            }
            map.put(jobLogCESearchResult.getOrderNumber(), jobLogCESearchResult);
        }
        JobLogCESearchResult jobLogCESearchResult[] = map.values().toArray(new JobLogCESearchResult[map.size()]);
        CEJobEmail[] ceJobMailList = getCEJobMail(jobLogCESearchResult);
        setEmailContent(ceJobMailList);
        return ceJobMailList;
    }

    private CEJobEmail[] getCEJobMail(JobLogCESearchResult[] jobLogCESearchResult) {

        HashMap<String, CEJobEmail> contractMap = new HashMap<String, CEJobEmail>();
        CEJobEmail jobEmail = null;
        for (int i = 0; i < jobLogCESearchResult.length; i++) {
            try {
                Dao<CEJobOrder> jobDao = DaoManager.getDao(CEJobOrder.class);
                CEJobOrder jobOrder = null;
                // String jobNumber = jobLogCESearchResult[i].getOrderNumber();
                String jobNumber = jobLogCESearchResult[i].getOrderNumber();
                if (jobNumber != null && !"".equals(jobNumber)) {
                    jobOrder = jobDao.find(jobNumber);

                }
                if (jobOrder != null && jobOrder.getJobContract() != null) {

                    Set<CEJobContract> contracts = jobOrder.getJobContracts();
                    for (CEJobContract contract : contracts) {
                        if (contract != null) {
                            jobEmail = contractMap.get(contract.getContractCode());

                            if (jobEmail == null) {
                                jobEmail = new CEJobEmail();
                                jobEmail.setContract(contract);
                                jobEmail.setContactId(contract.getId());
                                contractMap.put(contract.getContractCode(), jobEmail);
                            }
                            if (jobEmail.isOrderExit(jobOrder.getJobNumber()) == false) {
                                JobLogCEMailForm jobLogMailForm = new JobLogCEMailForm();
                                jobLogMailForm.setCeJobOrder(jobOrder);
                                jobLogMailForm.setContract(contract);
                                setEMailNotes(jobLogMailForm, contract);
                                jobEmail.addJobLogMailForm(jobLogMailForm);
                            }

                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        CEJobEmail[] ceJobMailList = contractMap.values().toArray(new CEJobEmail[contractMap.size()]);
        return ceJobMailList;
    }

    private void setEmailContent(CEJobEmail[] ceJobMailList) {
        try {
            UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
            User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
            for (CEJobEmail jobEmail : ceJobMailList) {
                StringBuffer sb = new StringBuffer();
                sb.append("Dear ");
                CEJobContract contract = jobEmail.getContract();
                Contact contact = contract.getContact();
                if (contact == null) {
                    contact = new Contact();
                }
                if (contract.getCustomer() == null) {
                    Customer cust = new Customer();
                    contract.setCustomer(cust);
                }
                jobEmail.setCc(user.getEmail());
                jobEmail.setCoordinator(contact.getFirstName() + " " + contact.getLastName());
                jobEmail.setSubject(Constants.DEFAULT_EMAIL_SUB);
                jobEmail.setCustCode(contract.getCustomer().getCustCode());
                jobEmail.setContactId(contract.getContactId());
                sb.append(contact.getFirstName() + " " + contact.getLastName() + " / ");
                sb.append(contract.getCustomer().getName() + ",");
                sb.append(System.getProperty("line.separator"));
                sb.append(System.getProperty("line.separator"));
                int size = jobEmail.getjobLogCEMailFormList().size();
                sb.append("Please find below the status of " + size + " job(s) you have nominated Intertek Caleb Brett as the independent inspector.\n");
                jobEmail.setIntroduction(sb.toString());
                String introToDisplay = jobEmail.getIntroduction().replaceAll(System.getProperty("line.separator"), "<br>");
                jobEmail.setIntroductionToDisplay(introToDisplay);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMailNotes(JobLogCEMailForm jobLogMailForm, CEJobContract contract) {
        String emailNote = "";
        String emailNoteDisplay = "";

        Set<JobOrderNote> jobOrderNotes = contract.getNotes();
        if (jobOrderNotes != null && jobOrderNotes.size() > 0) {
            for (JobOrderNote jobOrderNote : jobOrderNotes) {
                if (jobOrderNote.getNoteType() != null) {
                    if (jobOrderNote.getNote() != null && !jobOrderNote.getNote().trim().equals("")) {
                        if (emailNote.trim().equals("")) {
                            emailNote = jobOrderNote.getAddedUserId() + ": " + DateUtil.dateToString(jobOrderNote.getTimestamp()) + ": "
                                        + jobOrderNote.getNote();
                            emailNoteDisplay = emailNote;
                        }
                        else {
                            String tmpStr = jobOrderNote.getAddedUserId() + ": " + DateUtil.dateToString(jobOrderNote.getTimestamp()) + ": "
                                            + jobOrderNote.getNote();
                            emailNoteDisplay = emailNoteDisplay + "<br>" + tmpStr;
                            emailNote = emailNote + System.getProperty("line.separator") + tmpStr;
                        }
                    }
                }
            }
        }
        jobLogMailForm.setEmailNote(emailNote);
        jobLogMailForm.setEmailNoteDisplay(emailNoteDisplay);
    }

}
