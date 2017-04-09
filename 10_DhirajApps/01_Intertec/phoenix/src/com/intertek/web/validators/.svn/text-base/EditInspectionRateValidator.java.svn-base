package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.EditInspectionRate;
import com.intertek.domain.InspectionVersionExt;
import com.intertek.entity.CfgContract;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.web.util.InspectionRateValidationUtil;

public class EditInspectionRateValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return EditInspectionRate.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    EditInspectionRate editInspectionRate = (EditInspectionRate)obj;

    if(editInspectionRate == null) return;

    List inspectionVersionExtList = editInspectionRate.getInspectionVersionExtList();
    if(inspectionVersionExtList == null) return;

    if(inspectionVersionExtList.size() <= 0) return;

    boolean hasError1 = false;
    Date earliestContractBeginDate = editInspectionRate.getEarliestContractBeginDate();
    if(earliestContractBeginDate != null)
    {
      InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)inspectionVersionExtList.get(0);

      Date beginDate = inspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate();

      int dateResult = DateUtil.compareToInDate(beginDate, earliestContractBeginDate);
      if(dateResult < 0)
      {
        errors.rejectValue(
          "inspectionVersionExtList[" + 0 + "].inspectionVersion.inspectionVersionId.beginDate",
          "CAN_NOT_BE_EARLIER_THAN_EALIEST_CONTRACT_BEGIN_DATE",
          ""
        );
        hasError1 = true;
      }
    }

    boolean hasError = InspectionRateValidationUtil.validateInspectionVersions(inspectionVersionExtList, errors);
    if(hasError1 || hasError) errors.reject("edit.contract.inspection.rate.error");
  }
}
