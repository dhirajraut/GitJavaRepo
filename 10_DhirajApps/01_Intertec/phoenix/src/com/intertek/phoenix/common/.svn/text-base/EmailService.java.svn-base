/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.common;

import org.springframework.mail.MailException;
import com.intertek.phoenix.web.controller.joblog.CEJobEmail;
import com.intertek.phoenix.web.controller.joblog.JobLogCESearchResult;

/**
 * The interface that exposes Email related functionalities for CE.
 * 
 * @author Patni
 */
public interface EmailService {

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

    public CEJobEmail[] createCeJobEmailList(JobLogCESearchResult[] res, String selectedRow);

    /**
     * Send the email based on the data available in CEJobEMail
     * <p>
     * 
     * @param CEJobEmail
     */
    public void sendEmail(CEJobEmail jobEmail) throws MailException;
}
