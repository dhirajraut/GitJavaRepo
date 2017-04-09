package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.TestPriceExt;
import com.intertek.entity.Test;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.util.TestValidationUtil;

public class TestPriceExtValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return TestPriceExt.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    TestPriceExt testPriceExt = (TestPriceExt)obj;

    if(testPriceExt == null) return;

    TestValidationUtil.validateTestPrices(testPriceExt.getTestPrices(), errors);

    Test test = testPriceExt.getTest();
    if(test == null) return;

    String testDesc = test.getTestDescription();
    if( (testDesc == null) || "".equals(testDesc.trim()))
    {
      errors.rejectValue("test.testDescription", "not.blank", "");
    }
    else if(testDesc.length() > 254)
    {
      errors.rejectValue("test.testDescription", "length", new Object[] {0, 1, 254}, null);
    }
  }
}
