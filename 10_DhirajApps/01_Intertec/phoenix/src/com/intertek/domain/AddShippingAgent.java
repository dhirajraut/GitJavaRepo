package com.intertek.domain;
import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.ShippingAgent;

public class AddShippingAgent {
     @CascadeValidation
	
	private ShippingAgent shippingAgent;
	private String inputFieldId;
	private String okButton;

	public String getInputFieldId() {
		return inputFieldId;
	}

	public void setInputFieldId(String inputFieldId) {
		this.inputFieldId = inputFieldId;
	}

	public ShippingAgent getShippingAgent() {
		return shippingAgent;
	}

	public void setShippingAgent(ShippingAgent shippingAgent) {
		this.shippingAgent = shippingAgent;
	}
	public String getOkButton() {
		return okButton;
	}

	public void setOkButton(String okButton) {
		this.okButton = okButton;
	}
}
