package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.AddTestService;
import com.intertek.domain.SelectedTest;

public class AddTestServiceValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddTestService.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddTestService addTestService = (AddTestService)obj;

    SelectedTest[] selectedTests = addTestService.getSelectedTests();
    int size = selectedTests != null ? selectedTests.length : 0;
    for(int i=0; i<size; i++)
    {
      if(selectedTests[i].isSelected())
      {
        Double qty = selectedTests[i].getQty();
        if(qty == null)
        {
          errors.rejectValue("selectedTests[" + i + "].qty", "not.blank", "");
        }
        else if(qty <= 0)
        {
          errors.rejectValue("selectedTests[" + i + "].qty", "not.a.positive.number", "");
        }
      }
    }
  }
}
