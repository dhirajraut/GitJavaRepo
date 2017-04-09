package com.intertek.web.validators;

import java.util.Date;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.EditServiceRate;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceLevel;
import com.intertek.domain.ServiceVersionExt;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.web.util.ServiceRateValidationUtil;

public class AddContractServiceValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return EditServiceRate.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    EditServiceRate editServiceRate = (EditServiceRate)obj;

    if(editServiceRate == null) return;

    ServiceRates serviceRates = editServiceRate.getServiceRates();
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if(serviceVersionExtList == null) return;

    if(serviceVersionExtList.size() <= 0) return;

    boolean hasError1 = false;
    Date earliestContractBeginDate = serviceRates.getEarliestContractBeginDate();
    if(earliestContractBeginDate != null)
    {
      ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(0);

      Date beginDate = serviceVersionExt.getServiceVersion().getServiceVersionId().getBeginDate();

      int dateResult = DateUtil.compareToInDate(beginDate, earliestContractBeginDate);
      if(dateResult < 0)
      {
        errors.rejectValue(
          "serviceRates.serviceLevel.serviceVersionExtList[" + 0 + "].serviceVersion.serviceVersionId.beginDate",
          "CAN_NOT_BE_EARLIER_THAN_EALIEST_CONTRACT_BEGIN_DATE",
          ""
        );
        hasError1 = true;
      }
    }

    boolean hasError = ServiceRateValidationUtil.validateServiceVersions(serviceLevel.getServiceVersionExtList(), errors);
    if(hasError1 || hasError) errors.reject("edit.contract.service.error");
  }
}
