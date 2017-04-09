package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.AddJobContractVessel;
import com.intertek.entity.JobContractVessel;

public class AddJobContractVesselValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddJobContractVessel.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddJobContractVessel addJobContractVessel = (AddJobContractVessel)obj;
    JobContractVessel jobContractVessel = addJobContractVessel.getJobContractVessel();
    if(jobContractVessel == null) return;

    if (jobContractVessel.getVesselName() == null || jobContractVessel.getVesselName().trim().length() == 0)
    {
      errors.rejectValue("jobContractVessel.vesselName", "not.blank", "");
    }

    if (jobContractVessel.getType() == null || jobContractVessel.getType().trim().length() == 0)
    {
      errors.rejectValue("jobContractVessel.type", "not.blank", "");
    }
  }
}
