package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.EditVesselTypeSet;
import com.intertek.domain.EditRBExt;
import com.intertek.domain.RBExt;
import com.intertek.domain.VesselTypeExt;
import com.intertek.entity.RB;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.web.util.ValidationUtil;

public class EditVesselTypeSetValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return EditVesselTypeSet.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    EditVesselTypeSet editVesselTypeSet = (EditVesselTypeSet)obj;

    if(editVesselTypeSet == null) return;

    boolean hasError = false;

    EditRBExt editRBExt = editVesselTypeSet.getEditRBExt();
    if(editRBExt != null)
    {
      List rbExtList = editRBExt.getRbExtList();
      if(rbExtList != null)
      {
        for(int i=0; i<rbExtList.size(); i++)
        {
          RBExt rbExt = (RBExt)rbExtList.get(i);
          RB rb = rbExt.getNotesRb();


          Date beginDate = rb.getRbId().getBeginDate();
          Date endDate = rb.getRbId().getEndDate();

          String rbValue = rb.getRbValue();
          if((rbValue == null) || "".equals(rbValue.trim()))
          {
            errors.rejectValue("editRBExt.rbExtList[" + i + "].notesRb.rbValue", "not.blank", "");
            hasError = true;

            continue;
          }

          if(beginDate == null)
          {
            errors.rejectValue("editRBExt.rbExtList[" + i + "].notesRb.rbId.beginDate", "not.blank", "");
            hasError = true;

            continue;
          }
          else if(endDate == null)
          {
            errors.rejectValue("editRBExt.rbExtList[" + i + "].notesRb.rbId.endDate", "not.blank", "");
            hasError = true;

            continue;
          }
          else
          {
            int dateResult = DateUtil.compareToInDate(beginDate, endDate);
            if(dateResult > 0)
            {
              errors.rejectValue("editRBExt.rbExtList[" + i + "].notesRb.rbId.beginDate", "invalid.date", "");
              hasError = true;

              continue;
            }

            if(i < rbExtList.size() - 1)
            {
              RBExt nextRbExt = (RBExt)rbExtList.get(i + 1);
              Date nextBeginDate = nextRbExt.getNotesRb().getRbId().getBeginDate();
              Date nextEndDate = nextRbExt.getNotesRb().getRbId().getEndDate();

              if(nextBeginDate != null)
              {
                dateResult = DateUtil.compareToInDate(endDate, nextBeginDate);
                if(dateResult >= 0)
                {
                  errors.rejectValue("editRBExt.rbExtList[" + i + "].notesRb.rbId.beginDate", "invalid.date", "");
                  hasError = true;
                }
              }
            }
          }
        }
      }
    }
    else
    {
      List vesselTypeExts = editVesselTypeSet.getVesselTypeExts();
      if((vesselTypeExts != null) && (vesselTypeExts.size() > 0))
      {
        for(int i=0; i<vesselTypeExts.size(); i++)
        {
          VesselTypeExt pgExt = (VesselTypeExt)vesselTypeExts.get(i);

          boolean newlyAdded = pgExt.getNewlyAdded();
          if(newlyAdded)
          {
            RBExt rbExt = pgExt.getRbExt();
            if((rbExt == null) || (rbExt.getNotesRb() == null))
            {
              errors.rejectValue("vesselTypeExts[" + i + "].rbExt.notesRb", "not.blank", "");
              hasError = true;
            }
          }
        }
      }
    }

    if(hasError) errors.reject("edit.contract.product.group.error");
  }
}