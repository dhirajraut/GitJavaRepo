package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.ContractSlateExt;
import com.intertek.entity.ContractSlate;
import com.intertek.entity.Slate;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.util.ValidationUtil;

public class ContractSlateExtValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return ContractSlateExt.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    ContractSlateExt contractSlateExt = (ContractSlateExt)obj;

    if(contractSlateExt == null) return;

    ContractSlate contractSlate = contractSlateExt.getContractSlate();
    if(contractSlate == null) return;

    Slate slate = contractSlate.getSlate();
    if(slate == null) return;

    String slateId = contractSlate.getContractSlateId().getSlateId();
    if( (slateId == null) || "".equals(slateId.trim()))
    {
      errors.rejectValue("contractSlate.contractSlateId.slateId", "not.blank", "");
    }
    else if(slateId.length() > 15)
    {
      errors.rejectValue("contractSlate.contractSlateId.slateId", "length", new Object[] {0, 1, 15}, null);
    }

    String slateDesc = slate.getSlateDescription();
    if( (slateDesc == null) || "".equals(slateDesc.trim()))
    {
      errors.rejectValue("contractSlate.slate.slateDescription", "not.blank", "");
    }
    else if(slateDesc.length() > 254)
    {
      errors.rejectValue("contractSlate.slate.slateDescription", "length", new Object[] {0, 1, 254}, null);
    }
  }
}
