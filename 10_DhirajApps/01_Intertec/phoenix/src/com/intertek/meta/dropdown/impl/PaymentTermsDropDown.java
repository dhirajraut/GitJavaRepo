package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.PaymentTerm;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.AdminService;

public class PaymentTermsDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
   //JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
   AdminService adminService=(AdminService)ServiceLocator.getInstance().getBean("adminService");
	   

    List paymentTerms=adminService.getAllPaymentTerms();

     for(int i=0; i<paymentTerms.size(); i++)
    {
      PaymentTerm paymentTerm = (PaymentTerm)paymentTerms.get(i);
      Field field = new Field();
     field.setName(paymentTerm.getPaymentTermsDesc());
	  field.setValue(paymentTerm.getPaymentTermsCode());
      results.add(field);
    }
    
    return results;
  }
}
