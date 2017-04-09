package com.intertek.domain;

	import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.ServiceLocation;

	public class AddServiceLocation
	{
	  @CascadeValidation
	  private ServiceLocation serviceLocation;
	  private String existingBranchFlag;
	  private String branchTypeFlag;
	  private String inputFieldId;
	  private String labelFlag;
	  private String countyLabel;
	  private String stateLabel;
	  private String postalLabel;
	  private String address1Label;
	  private String address2Label;
	  private String address3Label;
	  private String address4Label;
	  private String okButton;
	  private String branchCode;
	  private String taxCode;
	  private String div1;
	  private String div2;
	  private boolean viewOnly;

	  public void setServiceLocation(ServiceLocation serviceLocation)
	  {
		this.serviceLocation = serviceLocation;
	  }

	  public ServiceLocation getServiceLocation()
	  {
		return serviceLocation;
	  }

	  public void setExistingBranchFlag(String existingBranchFlag)
	  {
	    this.existingBranchFlag = existingBranchFlag;
	  }

	  public String getExistingBranchFlag()
	  {
	    return existingBranchFlag;
	  }

	public String getBranchTypeFlag() {
		return branchTypeFlag;
	}

	public void setBranchTypeFlag(String branchTypeFlag) {
		this.branchTypeFlag = branchTypeFlag;
	}

	public String getInputFieldId() {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId) {
		this.inputFieldId = inputFieldId;
	}
	public String getLabelFlag() {
		return labelFlag;
	}

	public void setLabelFlag(String labelFlag) {
		this.labelFlag = labelFlag;
	}

	public String getCountyLabel() {
		return countyLabel;
	}

	public void setCountyLabel(String countyLabel) {
		this.countyLabel = countyLabel;
	}

	public String getPostalLabel() {
		return postalLabel;
	}

	public void setPostalLabel(String postalLabel) {
		this.postalLabel = postalLabel;
	}

	public String getStateLabel() {
		return stateLabel;
	}

	public void setStateLabel(String stateLabel) {
		this.stateLabel = stateLabel;
	}

	public String getOkButton() {
		return okButton;
	}

	public void setOkButton(String okButton) {
		this.okButton = okButton;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAddress1Label() {
		return address1Label;
	}

	public void setAddress1Label(String address1Label) {
		this.address1Label = address1Label;
	}

	public String getAddress2Label() {
		return address2Label;
	}

	public void setAddress2Label(String address2Label) {
		this.address2Label = address2Label;
	}

	public String getAddress3Label() {
		return address3Label;
	}

	public void setAddress3Label(String address3Label) {
		this.address3Label = address3Label;
	}

	public String getAddress4Label() {
		return address4Label;
	}

	public void setAddress4Label(String address4Label) {
		this.address4Label = address4Label;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getDiv1() {
		return div1;
	}

	public void setDiv1(String div1) {
		this.div1 = div1;
	}

	public String getDiv2() {
		return div2;
	}

	public void setDiv2(String div2) {
		this.div2 = div2;
	}
	
	public void setViewOnly(boolean viewOnly)
	  {
	    this.viewOnly = viewOnly;
	  }

	  public boolean getViewOnly()
	  {
	    return viewOnly;
	  }
	
}
