package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.User;

public class AddJobEmail
{
  @CascadeValidation

 

  private JobEmail[] jobEmails;
  private User user;
  private int emailIndex = 0;
  private int emailCount;
  private String timeFormat;


public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public JobEmail[] getJobEmails() {
	return jobEmails;
}

public void setJobEmails(JobEmail[] jobEmails) {
	this.jobEmails = jobEmails;
}



public int getEmailCount() {
	return emailCount;
}

public void setEmailCount(int emailCount) {
	this.emailCount = emailCount;
}

public int getEmailIndex() {
	return emailIndex;
}

public void setEmailIndex(int emailIndex) {
	this.emailIndex = emailIndex;
}

/**
 * @return the timeFormat
 */
public String getTimeFormat() {
	return timeFormat;
}

/**
 * @param timeFormat the timeFormat to set
 */
public void setTimeFormat(String timeFormat) {
	this.timeFormat = timeFormat;
}
  
  

  
  
  


}
