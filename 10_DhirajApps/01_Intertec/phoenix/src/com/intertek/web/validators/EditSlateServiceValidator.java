package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.EditSlateService;
import com.intertek.entity.JobContractSlate;

public class EditSlateServiceValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return EditSlateService.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    EditSlateService editSlateService = (EditSlateService)obj;

    JobContractSlate jobContractSlate = editSlateService.getJobContractSlate();
    if(jobContractSlate == null) return;

    Double qty = jobContractSlate.getQuantity();

    if(qty == null)
    {
      errors.rejectValue("jobContractSlate.quantity", "not.blank", "");
    }
    else if(qty <= 0)
    {
      errors.rejectValue("jobContractSlate.quantity", "not.a.positive.number", "");
    }
  }
}
