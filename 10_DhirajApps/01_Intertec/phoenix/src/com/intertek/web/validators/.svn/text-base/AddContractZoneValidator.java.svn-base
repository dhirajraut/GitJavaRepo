package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.EditZone;
import com.intertek.domain.ZoneExt;
import com.intertek.entity.CfgContract;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.util.ValidationUtil;

public class AddContractZoneValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return EditZone.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    EditZone editZone = (EditZone)obj;

    if(editZone == null) return;

    boolean hasZoneError = false;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList != null)
    {
      for(int i=0; i<zoneExtList.size(); i++)
      {
        ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);
        boolean hasError = false;

        if(Constants.Custom.equals(zoneExt.getCustomFlag()))
        {
          String customZoneId = zoneExt.getCustomZoneId();
          if( (customZoneId == null) || "".equals(customZoneId.trim()))
          {
            errors.rejectValue("zoneExtList[" + i + "].customZoneId", "not.blank", "");
            hasError = true;
          }
          else
          {
            if(ZoneUtil.existZoneExtByZoneId(zoneExtList, customZoneId.trim(), i + 1))
            {
              errors.rejectValue("zoneExtList[" + i + "].customZoneId", "duplicate", "");
              hasError = true;
            }
          }
        }
        else
        {
          String priceBookZoneId = zoneExt.getPriceBookZoneId();
          if( (priceBookZoneId == null) || "".equals(priceBookZoneId.trim()))
          {
            errors.rejectValue("zoneExtList[" + i + "].priceBookZoneId", "not.blank", "");
            hasError = true;
          }
          else
          {
            if(ZoneUtil.existZoneExtByZoneId(zoneExtList, priceBookZoneId.trim(), i + 1))
            {
              errors.rejectValue("zoneExtList[" + i + "].priceBookZoneId", "duplicate", "");
              hasError = true;
            }
          }
        }

        if(ValidationUtil.validateLocationDiscounts(i, zoneExt.getLocationDiscounts(), errors)) hasError = true;
        if(ValidationUtil.validateBranchLocationExts(i, zoneExt.getBranchLocationExts(), errors)) hasError = true;
        if(ValidationUtil.validatePortLocations(i, zoneExt.getPortLocations(), errors)) hasError = true;

        if(hasError)
        {
          ValidationUtil.addZoneError(i, errors);
          hasZoneError = true;
        }
      }
    }

    if(hasZoneError) errors.reject("edit.contract.zone.error");
  }
}
