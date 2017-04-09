package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.ContractTestExt;
import com.intertek.entity.ContractTest;
import com.intertek.entity.Test;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.util.ValidationUtil;

public class ContractTestExtValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return ContractTestExt.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    ContractTestExt contractTestExt = (ContractTestExt)obj;

    if(contractTestExt == null) return;

    ContractTest contractTest = contractTestExt.getContractTest();
    if(contractTest == null) return;

    Test test = contractTest.getTest();
    if(test == null) return;

    String testId = contractTest.getContractTestId().getTestId();
    if( (testId == null) || "".equals(testId.trim()))
    {
      errors.rejectValue("contractTest.contractTestId.testId", "not.blank", "");
    }
    else if(testId.length() > 32)
    {
      errors.rejectValue("contractTest.contractTestId.testId", "length", new Object[] {0, 1, 32}, null);
    }

    String testDesc = test.getTestDescription();
    if( (testDesc == null) || "".equals(testDesc.trim()))
    {
      errors.rejectValue("contractTest.test.testDescription", "not.blank", "");
    }
    else if(testDesc.length() > 254)
    {
      errors.rejectValue("contractTest.test.testDescription", "length", new Object[] {0, 1, 254}, null);
    }
  }
}
