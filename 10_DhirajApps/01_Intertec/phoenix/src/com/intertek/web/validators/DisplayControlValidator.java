package com.intertek.web.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.calculator.ControlExt;
import com.intertek.domain.DisplayControl;
import com.intertek.util.StringUtil;

public class DisplayControlValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return DisplayControl.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    DisplayControl displayControl = (DisplayControl)obj;

    ControlExt[] controlExts = displayControl.getControlExts();

    if(controlExts != null)
    {
      for(int i=0; i<controlExts.length; i++)
      {
        String dataInput = controlExts[i].getDataInput();

        if(StringUtil.emptyStr(dataInput)) continue;

        Double qty = null;
        try { qty = new Double(dataInput); } catch(Exception e) { }

        if(qty == null)
        {
          errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.number", "");
        }
        else if(qty <= 0)
        {
          errors.rejectValue("controlExts[" + i + "].dataInput", "not.a.positive.number", "");
        }
      }
    }
  }
}
