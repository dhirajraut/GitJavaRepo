package com.intertek.web.validators;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.AddContract;
import com.intertek.domain.CfgContractExt;
import com.intertek.domain.ZoneExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contract;
import com.intertek.entity.ReferenceField;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.web.util.ValidationUtil;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;

public class AddContractValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddContract.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddContract addContract = (AddContract)obj;

    if(addContract == null) return;

    Contract contract = addContract.getContract();
    if(contract == null) return;

    if(addContract.getIsNewFlag())
    {
      ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
      Contract existedContract = contractService.getContractByContractCode(contract.getContractCode());
      if(existedContract != null)
      {
        errors.rejectValue("contract.contractCode", "duplicate", "");
      }
    }

    boolean validCode = ContractUtil.isContractCodeValid(contract.getContractCode());
    if(!validCode)
    {
        errors.rejectValue("contract.contractCode", "invalid", "");
    }

    List cfgContractExtList = addContract.getCfgContractExtList();
    if(cfgContractExtList != null)
    {
      for(int i=0; i<cfgContractExtList.size(); i++)
      {
        CfgContractExt cfgContractExt = (CfgContractExt)cfgContractExtList.get(i);
        CfgContract cfgContract = cfgContractExt.getCfgContract();

        Date beginDate = cfgContract.getCfgContractId().getBeginDate();
        Date endDate = cfgContract.getEndDate();

        if(beginDate == null)
        {
          errors.rejectValue("cfgContractExtList[" + i + "].cfgContract.cfgContractId.beginDate", "not.blank", "");
        }
        else if(endDate == null)
        {
          errors.rejectValue("cfgContractExtList[" + i + "].cfgContract.cfgContractId.endDate", "not.blank", "");
        }
        else
        {
          int dateResult = DateUtil.compareToInDate(beginDate, endDate);
          if(dateResult > 0)
          {
            errors.rejectValue("cfgContractExtList[" + i + "].cfgContract.cfgContractId.beginDate", "invalid.date", "");

            continue;
          }

          if(i < cfgContractExtList.size() - 1)
          {
            CfgContractExt nextCfgContractExt = (CfgContractExt)cfgContractExtList.get(i + 1);
            CfgContract nextCfgContract = nextCfgContractExt.getCfgContract();
            Date nextBeginDate = nextCfgContract.getCfgContractId().getBeginDate();
            Date nextEndDate = nextCfgContract.getEndDate();

            if(nextEndDate != null)
            {
              dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
              if(dateResult <= 0)
              {
                errors.rejectValue("cfgContractExtList[" + i + "].cfgContract.cfgContractId.beginDate", "invalid.date", "");
              }
            }
          }
        }
      }
    }

    List referenceFieldList = addContract.getReferenceFieldList();
    if(referenceFieldList != null)
    {
      Set rfSet = new HashSet();

      for(int i=0; i<referenceFieldList.size(); i++)
      {
        ReferenceField referenceField = (ReferenceField)referenceFieldList.get(i);
        String rfIdStr = referenceField.getReferenceFieldId().getReferenceFieldId();
        if((rfIdStr == null) || "".equals(rfIdStr.trim()))
        {
        }
        else
        {
          if(rfSet.contains(rfIdStr))
          {
            errors.rejectValue("referenceFieldList[" + i + "].referenceFieldId.referenceFieldId", "duplicate", "");
          }

          rfSet.add(rfIdStr);
        }
      }
    }
  }
}
