package com.intertek.web.util;

import java.util.*;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.calculator.*;
import com.intertek.entity.*;
import com.intertek.domain.*;
import com.intertek.util.*;
import com.intertek.service.*;
import com.intertek.locator.*;

public class ServiceRateValidationUtil
{
  public static boolean validateServiceVersions(List serviceVersionExts, Errors errors)
  {
    if((serviceVersionExts == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<serviceVersionExts.size(); i++)
    {
      ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExts.get(i);
      ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

      Date beginDate = serviceVersion.getServiceVersionId().getBeginDate();
      Date endDate = serviceVersion.getEndDate();

      if(beginDate == null)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + i + "].serviceVersion.serviceVersionId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + i + "].serviceVersion.endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + i + "].serviceVersion.serviceVersionId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if(i < serviceVersionExts.size() - 1)
        {
          ServiceVersionExt nextServiceVersionExt = (ServiceVersionExt)serviceVersionExts.get(i + 1);
          Date nextBeginDate = nextServiceVersionExt.getServiceVersion().getServiceVersionId().getBeginDate();
          Date nextEndDate = nextServiceVersionExt.getServiceVersion().getEndDate();

          if(endDate != null)
          {
            dateResult = DateUtil.compareToInDate(nextBeginDate, endDate);
            if(dateResult <= 0)
            {
              errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + (i+1) + "].serviceVersion.serviceVersionId.beginDate", "invalid.date", "");
              hasError = true;
            }
          }
        }
      }

      List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
      boolean rateHasError = validateServiceRates(i, contractServiceRateExtList, errors);
      if(rateHasError) hasError = true;
    }

    return hasError;
  }

  public static boolean validateServiceRates(int versionIndex, List serviceRateExtList, Errors errors)
  {
    if((serviceRateExtList == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<serviceRateExtList.size(); i++)
    {
      ServiceRateExt serviceRateExt = (ServiceRateExt)serviceRateExtList.get(i);
      ServiceRate serviceRate = serviceRateExt.getServiceRate();

      if(serviceRate == null) continue;

      Integer intData2 = serviceRate.getServiceRateId().getIntData2();
      if(intData2 == null)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.intData2", "not.blank", "");
        hasError = true;
      }
      else if(intData2 < 0)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.intData2", "non_negative", "");
        hasError = true;
      }

      Long intData3 = serviceRate.getIntData3();
      if(intData3 == null)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.intData3", "not.blank", "");
        hasError = true;
      }
      else if(intData3 < 0)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.intData3", "non_negative", "");
        hasError = true;
      }
      else
      {
        if((intData2 != null) && (intData2.intValue() > intData3.intValue()))
        {
          errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.intData2", "invalid", "");
          hasError = true;
        }
      }

      String location = serviceRate.getServiceRateId().getLocation();
      if(location == null)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.location", "not.blank", "");
        hasError = true;
      }

      Date beginDate = serviceRate.getServiceRateId().getBeginDate();
      Date endDate = serviceRate.getEndDate();
      if(beginDate == null)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if((intData2 == null) || (intData3 == null)) continue;

        if(i < serviceRateExtList.size() - 1)
        {
          List nextServiceRateExtList = ContractServiceUtil.getNextServiceRateExtListByCurrentServiceRate(serviceRateExtList, serviceRate, i + 1);

          for(int j=0; j<nextServiceRateExtList.size(); j++)
          {
            ServiceRateExt nextServiceRateExt = (ServiceRateExt)nextServiceRateExtList.get(j);
            ServiceRate nextServiceRate = nextServiceRateExt.getServiceRate();

            Date nextBeginDate = nextServiceRate.getServiceRateId().getBeginDate();
            Date nextEndDate = nextServiceRate.getEndDate();
            Integer nextIntData2 = nextServiceRate.getServiceRateId().getIntData2();
            Long nextIntData3 = nextServiceRate.getIntData3();

            if((nextBeginDate == null) || (endDate == null) || (nextIntData2 != null) && (nextIntData3 != null))
            {
              dateResult = DateUtil.compareToInDate(nextBeginDate, endDate);
              if(dateResult <= 0)
              {
                boolean overlap = NumberUtil.isOverLap(intData2.intValue(), intData3.intValue(), nextIntData2.intValue(), nextIntData3.intValue());
                if(overlap)
                {
                  errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.beginDate", "invalid.date", "");
                  hasError = true;
                }
              }
            }
          }
        }
      }
    }

    return hasError;
  }
}

