package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.AddManualTestService;
import com.intertek.domain.JobContractManualTestExt;

public class ManualTestServiceValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddManualTestService.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddManualTestService addManualTestService = (AddManualTestService)obj;

    JobContractManualTestExt jobContractManualTestExt = addManualTestService.getJobContractManualTestExt();
    if(jobContractManualTestExt == null) return;

    int testIdSize = jobContractManualTestExt.getManualTest().getTestId() == null ? 0 : jobContractManualTestExt.getManualTest().getTestId().trim().length();
    if (testIdSize == 0)
    {
      errors.rejectValue("jobContractManualTestExt.manualTest.testId", "not.blank", "");
    }

    if (testIdSize >= 96)
    {
      errors.rejectValue("jobContractManualTestExt.manualTest.testId", "max.length", "96");
    }

    int testDescriptionSize = jobContractManualTestExt.getManualTest().getTestDescription() == null ? 0 : jobContractManualTestExt.getManualTest().getTestDescription().trim().length();

    if (testIdSize >= 762)
    {
      errors.rejectValue("jobContractManualTestExt.manualTest.testDescription", "max.length", "762");
    }

    Double quantity = jobContractManualTestExt.getManualTest().getQuantity();
    if(quantity == null)
    {
      errors.rejectValue("jobContractManualTestExt.manualTest.quantity", "not.blank", "");
    }
    else if(quantity <= 0)
    {
      errors.rejectValue("jobContractManualTestExt.manualTest.quantity", "not.a.positive.number", "");
    }

    Double totalPrice = jobContractManualTestExt.getManualTest().getTotalPrice();
    if(totalPrice == null)
    {
      errors.rejectValue("jobContractManualTestExt.manualTest.totalPrice", "not.blank", "");
    }
    else if(totalPrice <= 0)
    {
      errors.rejectValue("jobContractManualTestExt.manualTest.totalPrice", "not.a.positive.number", "");
    }
  }
}
