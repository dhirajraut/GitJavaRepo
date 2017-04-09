package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

public class CustomerAddresses extends Search
{
	@CascadeValidation
	private AddCustomerAddress[] addCustomerAddresses;

 public CustomerAddresses()
  {
    
  }

public AddCustomerAddress[] getAddCustomerAddresses() {
	return addCustomerAddresses;
}

public void setAddCustomerAddresses(AddCustomerAddress[] addCustomerAddresses) {
	this.addCustomerAddresses = addCustomerAddresses;
}   					
 
  
  }