package com.intertek.mail;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * A normal Mime message preparator used to prepare the email message.
 *
 **/
public class NormalMimeMessagePreparator implements MimeMessagePreparator
{
  private static final Log log = LogFactory.getLog(NormalMimeMessagePreparator.class);

  private String from;
  private String to;
  private String subject;
  private String bodyStr;

  /**
   * Prepare the email message.
   *
   * @param msg - the MimeMessage to be prepared.
   * @throws - an Exception thrown during preparation of the message.
   *
   **/
  public void prepare(MimeMessage msg) throws Exception
  {
    // set header details
    msg.addFrom(InternetAddress.parse(from));
    msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    msg.setSubject(subject);

    // create wrapper multipart/alternative part
    MimeMultipart ma = new MimeMultipart("alternative");
    msg.setContent(ma);

    BodyPart bodyPart = new MimeBodyPart();
    bodyPart.setText(bodyStr);
    ma.addBodyPart(bodyPart);
  }

  /**
   * Set the sent-to email address
   *
   * @param to - the sent-to emaill address
   *
   **/
  public void setTo(String to)
  {
    this.to = to;
  }

  /**
   * Set the sent-from email address
   *
   * @param from - the sent-from emaill address
   *
   **/
  public void setFrom(String from)
  {
    this.from = from;
  }

  /**
   * Set the subject of this email
   *
   * @param subject - the subject of this email.
   *
   **/
  public void setSubject(String subject)
  {
    this.subject = subject;
  }

  /**
   * Get the subject of this email
   *
   * @return - the subject of this email.
   *
   **/
  public String getSubject()
  {
    return subject;
  }

  /**
   * Set the body string of this email
   *
   * @param bodyStr - the body string of this email.
   *
   **/
  public void setBodyStr(String bodyStr)
  {
    this.bodyStr = bodyStr;
  }

  /**
   * Get the body string of this email
   *
   * @return - the body string of this email.
   *
   **/
  public String getBodyStr()
  {
    return bodyStr;
  }
}