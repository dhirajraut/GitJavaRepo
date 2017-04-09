package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.EditTestService;
import com.intertek.entity.JobContractTest;

public class EditTestServiceValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return EditTestService.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    EditTestService editTestService = (EditTestService)obj;

    JobContractTest jobContractTest = editTestService.getJobContractTest();
    if(jobContractTest == null) return;

    Double qty = jobContractTest.getQuantity();

    if(qty == null)
    {
      errors.rejectValue("jobContractTest.quantity", "not.blank", "");
    }
    else if(qty <= 0)
    {
      errors.rejectValue("jobContractTest.quantity", "not.a.positive.number", "");
    }
  }
}
