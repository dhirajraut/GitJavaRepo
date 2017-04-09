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

public class InspectionRateValidationUtil
{
  public static boolean validateInspectionVersions(List inspectionVersionExts, Errors errors)
  {
    if((inspectionVersionExts == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<inspectionVersionExts.size(); i++)
    {
      InspectionVersionExt inspectionVersionExt = (InspectionVersionExt)inspectionVersionExts.get(i);
      InspectionVersion inspectionVersion = inspectionVersionExt.getInspectionVersion();

      Date beginDate = inspectionVersion.getInspectionVersionId().getBeginDate();
      Date endDate = inspectionVersion.getEndDate();

      if(beginDate == null)
      {
        errors.rejectValue("inspectionVersionExtList[" + i + "].inspectionVersion.inspectionVersionId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("inspectionVersionExtList[" + i + "].inspectionVersion.endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("inspectionVersionExtList[" + i + "].inspectionVersion.inspectionVersionId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if(i < inspectionVersionExts.size() - 1)
        {
          InspectionVersionExt nextInspectionVersionExt = (InspectionVersionExt)inspectionVersionExts.get(i + 1);
          Date nextBeginDate = nextInspectionVersionExt.getInspectionVersion().getInspectionVersionId().getBeginDate();
          Date nextEndDate = nextInspectionVersionExt.getInspectionVersion().getEndDate();

          if(endDate != null)
          {
            dateResult = DateUtil.compareToInDate(nextBeginDate, endDate);
            if(dateResult <= 0)
            {
              errors.rejectValue("inspectionVersionExtList[" + (i+1) + "].inspectionVersion.inspectionVersionId.beginDate", "invalid.date", "");
              hasError = true;
            }
          }
        }
      }

      List vesselTypeExtList = inspectionVersionExt.getVesselTypeExtList();
      if(vesselTypeExtList != null)
      {
        for(int j=0; j<vesselTypeExtList.size(); j++)
        {
          VesselTypeExt vesselTypeExt = (VesselTypeExt)vesselTypeExtList.get(j);
          List contractInspectionRateExtList = vesselTypeExt.getContractInspectionRateExtList();
          boolean rateHasError = validateInspectionRates(i, j, contractInspectionRateExtList, errors);
          if(rateHasError) hasError = true;
        }
      }
    }

    return hasError;
  }


  public static boolean validateInspectionRates(int versionIndex, int vesselTypeExtIndex, List inspectionRateExtList, Errors errors)
  {
    if((inspectionRateExtList == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<inspectionRateExtList.size(); i++)
    {
      InspectionRateExt inspectionRateExt = (InspectionRateExt)inspectionRateExtList.get(i);
      InspectionRate inspectionRate = inspectionRateExt.getInspectionRate();

      if(inspectionRate == null) continue;

      Integer intData2 = inspectionRate.getInspectionRateId().getIntData2();
      if(intData2 == null)
      {
        errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.inspectionRateId.intData2", "not.blank", "");
        hasError = true;
      }
      else if(intData2 < 0)
      {
        errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.inspectionRateId.intData2", "non_negative", "");
        hasError = true;
      }

      Long intData3 = inspectionRate.getIntData3();
      if(intData3 == null)
      {
        errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.intData3", "not.blank", "");
        hasError = true;
      }
      else if(intData3 < 0)
      {
        errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.intData3", "non_negative", "");
        hasError = true;
      }
      else
      {
        if((intData2 != null) && (intData2.intValue() > intData3.intValue()))
        {
          errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.inspectionRateId.intData2", "invalid", "");
          hasError = true;
        }
      }

      Integer intData4 = inspectionRate.getInspectionRateId().getIntData4();
      if(intData4 == null)
      {
        errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.inspectionRateId.intData4", "not.blank", "");
        hasError = true;
      }

      String location = inspectionRate.getInspectionRateId().getLocation();
      if(location == null)
      {
        errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.inspectionRateId.location", "not.blank", "");
        hasError = true;
      }

      if((intData2 == null) || (intData3 == null)) continue;

      if(i < inspectionRateExtList.size() - 1)
      {
        List nextInspectionRateExtList = InspectionRateUtil.getNextInspectionRateExtListByCurrentInspectionRate(inspectionRateExtList, inspectionRate, i + 1);

        for(int j=0; j<nextInspectionRateExtList.size(); j++)
        {
          InspectionRateExt nextInspectionRateExt = (InspectionRateExt)nextInspectionRateExtList.get(j);
          InspectionRate nextInspectionRate = nextInspectionRateExt.getInspectionRate();

          Integer nextIntData2 = nextInspectionRate.getInspectionRateId().getIntData2();
          Long nextIntData3 = nextInspectionRate.getIntData3();

          if((nextIntData2 != null) && (nextIntData3 != null))
          {
            boolean overlap = NumberUtil.isOverLap(intData2.intValue(), intData3.intValue(), nextIntData2.intValue(), nextIntData3.intValue());
            if(overlap)
            {
              errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.inspectionRateId.intData2", "invalid", "");
              hasError = true;
            }
          }
        }
      }
    }

    return hasError;
  }
}
