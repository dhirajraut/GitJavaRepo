package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

public class CreditReason
{
  @CascadeValidation


  private String creditReasonNote;
  private String creditReasonUser;
  private String contractIndex;
  private String readWriteFlag;
  private String controllerName;
  
public String getCreditReasonNote() {
	return creditReasonNote;
}
public void setCreditReasonNote(String creditReasonNote) {
	this.creditReasonNote = creditReasonNote;
}
public String getCreditReasonUser() {
	return creditReasonUser;
}
public void setCreditReasonUser(String creditReasonUser) {
	this.creditReasonUser = creditReasonUser;
}
public String getContractIndex() {
	return contractIndex;
}
public void setContractIndex(String contractIndex) {
	this.contractIndex = contractIndex;
}
public String getReadWriteFlag() {
	return readWriteFlag;
}
public void setReadWriteFlag(String readWriteFlag) {
	this.readWriteFlag = readWriteFlag;
}
public String getControllerName() {
	return controllerName;
}
public void setControllerName(String controllerName) {
	this.controllerName = controllerName;
}
  
  
}