package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.SlatePriceExt;
import com.intertek.entity.Slate;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.util.SlateValidationUtil;

public class SlatePriceExtValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return SlatePriceExt.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    SlatePriceExt slatePriceExt = (SlatePriceExt)obj;

    if(slatePriceExt == null) return;

    SlateValidationUtil.validateSlatePrices(slatePriceExt.getSlatePrices(), errors);

    Slate slate = slatePriceExt.getSlate();
    if(slate == null) return;

    String slateDesc = slate.getSlateDescription();
    if( (slateDesc == null) || "".equals(slateDesc.trim()))
    {
      errors.rejectValue("slate.slateDescription", "not.blank", "");
    }
    else if(slateDesc.length() > 254)
    {
      errors.rejectValue("slate.slateDescription", "length", new Object[] {0, 1, 254}, null);
    }
  }
}
