package com.intertek.web.validators;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.entity.JobContract;
import com.intertek.domain.AddJobContract;
import com.intertek.entity.Prebill;

public class AddJobContractPreviewValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddJobContract.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddJobContract addJobContract = (AddJobContract)obj;
    if(addJobContract == null) return;

    JobContract jobContract = addJobContract.getJobContract();
    if(jobContract == null) return;

    Set prebills = jobContract.getPrebills();
    if(prebills == null) return;

    int count = 0;
    Iterator it = prebills.iterator();
    while(it.hasNext())
    {
      Prebill prebill = (Prebill)it.next();

      Double splitPct = prebill.getSplitPct();
      if(splitPct != null)
      {
        if((splitPct.doubleValue() < 0.0) || (splitPct.doubleValue() > 100.0))
        {
          errors.rejectValue("jobContract.prebills[" + count + "].splitPct", "invalid", "");
        }
      }

      count ++;
    }
  }
}
