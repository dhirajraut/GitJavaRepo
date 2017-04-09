package com.intertek.mail;

import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * A velocity-based Mime message preparator used to prepare the email message.
 *
 * It will use the velocity framework to format the email body.
 *
 **/
public class VelocityMimeMessagePreparator implements MimeMessagePreparator, InitializingBean
{
  private static final Log log = LogFactory.getLog(VelocityMimeMessagePreparator.class);
  private JavaMailSender mailSender;

  private VelocityEngine velocityEngine;
  private String bodyTemplatePath;
  private String from;
  private String to;
  private String cc;
  private String subject;
  private Map data;
  private String bodyStr;
  private String customizedMsg;

  /**
   * Prepare the email message by formating the email body with velocity framework.
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
    if(cc != null)
    {
      msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
    }
    msg.setSubject(subject);

    // create wrapper multipart/alternative part
    MimeMultipart ma = new MimeMultipart("alternative");
    msg.setContent(ma);


    BodyPart bodyPart = new MimeBodyPart();
  try{
  bodyStr = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, bodyTemplatePath, data);
  }
  catch(Exception e)
    {
    e.printStackTrace();
    }
    if(customizedMsg != null)
    {
      bodyStr = customizedMsg + "\n\n========================================================\n\n" + bodyStr;
    }
    log.info("=============== bodyStr = " + bodyStr);

    bodyPart.setText(bodyStr);
  bodyPart.setContent(bodyStr,"text/html");
    ma.addBodyPart(bodyPart);
    msg.setContent(ma);
  }

  /**
   * Check if the velocity engine got set or not.
   *
   **/
  public void afterPropertiesSet() throws Exception
  {
    if (velocityEngine == null)
    {
      throw new IllegalArgumentException("Must set the velocityEngine property of " + getClass().getName());
    }
  }

  /**
   * Set the velocity template path of the email body.
   *
   * @param bodyTemplatePath - the velocity template path.
   *
   **/
  public void setBodyTemplatePath(String bodyTemplatePath)
  {
    this.bodyTemplatePath = bodyTemplatePath;
  }

  /**
   * Set the velocity engine.
   *
   * @param velocityEngine - the velocity engine used to format the email body.
   *
   **/
  public void setVelocityEngine(VelocityEngine velocityEngine)
  {
    this.velocityEngine = velocityEngine;
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
   * Set the cc email address
   *
   * @param from - the cc emaill address
   *
   **/
  public void setCc(String cc)
  {
    this.cc = cc;
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
   * Set the data used by the velocity engihe to format the email body.
   *
   * @param data - a map of data.
   *
   **/
  public void setData(Map data)
  {
    this.data = data;
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

  /**
   * Get the customized message
   *
   * @return - the customized message.
   *
   **/
  public String getCustomizedMsg()
  {
    return customizedMsg;
  }

  /**
   * Set the customized message
   *
   * @param customizedMsg - the customized message.
   *
   **/
  public void setCustomizedMsg(String customizedMsg)
  {
    this.customizedMsg = customizedMsg;
  }

  /**
   * Set the java mail sender
   *
   * @param mailSender - the java mail sender.
   *
   **/
  public void setMailSender(JavaMailSender mailSender)
  {
    this.mailSender = mailSender;
  }

  /**
   * Get the java mail sender
   *
   * @return - the java mail sender.
   *
   **/
  public JavaMailSender getJavaMailSender()
  {
    return mailSender;
  }
}
