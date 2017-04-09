package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.AddSlateService;
import com.intertek.domain.SelectedSlate;

public class AddSlateServiceValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddSlateService.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddSlateService addSlateService = (AddSlateService)obj;

    SelectedSlate[] selectedSlates = addSlateService.getSelectedSlates();
    int size = selectedSlates != null ? selectedSlates.length : 0;
    for(int i=0; i<size; i++)
    {
      if(selectedSlates[i].isSelected())
      {
        Double qty = selectedSlates[i].getQty();
        if(qty == null)
        {
          errors.rejectValue("selectedSlates[" + i + "].qty", "not.blank", "");
        }
        else if(qty <= 0)
        {
          errors.rejectValue("selectedSlates[" + i + "].qty", "not.a.positive.number", "");
        }
      }
    }
  }
}
