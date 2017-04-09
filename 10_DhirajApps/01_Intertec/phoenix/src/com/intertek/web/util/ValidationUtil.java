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

public class ValidationUtil
{
  public static boolean validateLocationDiscounts(int zoneIndex, List locationDiscounts,Errors errors)
  {
    if((locationDiscounts == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<locationDiscounts.size(); i++)
    {
      LocationDiscount locationDiscount = (LocationDiscount)locationDiscounts.get(i);

      Date beginDate = locationDiscount.getLocationDiscountId().getBeginDate();
      Date endDate = locationDiscount.getEndDate();

      if(beginDate == null)
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].locationDiscounts[" + i + "].locationDiscountId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].locationDiscounts[" + i + "].endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("zoneExtList[" + zoneIndex + "].locationDiscounts[" + i + "].locationDiscountId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if(i < locationDiscounts.size() - 1)
        {
          LocationDiscount nextLocationDiscount = (LocationDiscount)locationDiscounts.get(i + 1);
          Date nextBeginDate = nextLocationDiscount.getLocationDiscountId().getBeginDate();
          Date nextEndDate = nextLocationDiscount.getEndDate();

          if(nextEndDate != null)
          {
            dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
            if(dateResult <= 0)
            {
              errors.rejectValue("zoneExtList[" + zoneIndex + "].locationDiscounts[" + i + "].locationDiscountId.beginDate", "invalid.date", "");
              hasError = true;
            }
          }
        }
      }
    }

    return hasError;
  }

  public static boolean validateBranchLocationExts(int zoneIndex, List branchLocationExts,Errors errors)
  {
    if((branchLocationExts == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<branchLocationExts.size(); i++)
    {
      BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExts.get(i);
      BranchLocation branchLocation = branchLocationExt.getBranchLocation();

      boolean hasBu = true;
      boolean hasBranch = true;
      String branchSetId = branchLocation.getBranchLocationId().getBranchSetId();
      if( (branchSetId == null) || "".equals(branchSetId.trim()))
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].branchLocationExts[" + i + "].branchLocation.branchLocationId.branchSetId", "not.blank", "");
        hasBu = false;
        hasError = true;
      }

      String branchCode = branchLocation.getBranchLocationId().getBranchCode();
      if( (branchCode == null) || "".equals(branchCode.trim()))
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].branchLocationExts[" + i + "].branchLocation.branchLocationId.branchCode", "not.blank", "");
        hasBranch = false;
        hasError = true;
      }

      if(hasBu && hasBranch)
      {
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        List branches = userService.getBranchesByBusinessUnitNameAndBranchName(branchSetId, branchCode);
        if(branches.size() <= 0)
        {
          errors.rejectValue("zoneExtList[" + zoneIndex + "].branchLocationExts[" + i + "].branchLocation.branchLocationId.branchCode", "invalid", "");
          hasError = true;
        }
      }

      Date beginDate = branchLocation.getBranchLocationId().getBeginDate();
      Date endDate = branchLocation.getEndDate();

      if(beginDate == null)
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].branchLocationExts[" + i + "].branchLocation.branchLocationId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].branchLocationExts[" + i + "].branchLocation.endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("zoneExtList[" + zoneIndex + "].branchLocationExts[" + i + "].branchLocation.branchLocationId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if(i < branchLocationExts.size() - 1)
        {
          BranchLocationExt nextBranchLocationExt = ZoneUtil.getNextBranchLocationExt(branchLocationExts, branchLocation.getBranchLocationId().getBranchSetId(), branchLocation.getBranchLocationId().getBranchCode(), i + 1);

          if(nextBranchLocationExt != null)
          {
            BranchLocation nextBranchLocation = nextBranchLocationExt.getBranchLocation();

            Date nextBeginDate = nextBranchLocation.getBranchLocationId().getBeginDate();
            Date nextEndDate = nextBranchLocation.getEndDate();

            if(nextEndDate != null)
            {
              dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
              if(dateResult <= 0)
              {
                errors.rejectValue("zoneExtList[" + zoneIndex + "].branchLocationExts[" + i + "].branchLocation.branchLocationId.beginDate", "invalid.date", "");
                hasError = true;
              }
            }
          }
        }
      }
    }

    return hasError;
  }

  public static boolean validatePortLocations(int zoneIndex, List portLocations,Errors errors)
  {
    if((portLocations == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<portLocations.size(); i++)
    {
      PortLocation portLocation = (PortLocation)portLocations.get(i);

      String portCode = portLocation.getPortLocationId().getPortCode();
      if( (portCode == null) || "".equals(portCode.trim()))
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].portLocations[" + i + "].portLocationId.portCode", "not.blank", "");
        hasError = true;
      }

      Date beginDate = portLocation.getPortLocationId().getBeginDate();
      Date endDate = portLocation.getEndDate();

      if(beginDate == null)
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].portLocations[" + i + "].portLocationId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("zoneExtList[" + zoneIndex + "].portLocations[" + i + "].endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("zoneExtList[" + zoneIndex + "].portLocations[" + i + "].portLocationId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if(i < portLocations.size() - 1)
        {
          PortLocation nextPortLocation = ZoneUtil.getNextPortLocation(portLocations, portLocation.getPortLocationId().getPortCode(), i + 1);
          if(nextPortLocation != null)
          {
            Date nextBeginDate = nextPortLocation.getPortLocationId().getBeginDate();
            Date nextEndDate = nextPortLocation.getEndDate();

            if(nextEndDate != null)
            {
              dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
              if(dateResult <= 0)
              {
                errors.rejectValue("zoneExtList[" + zoneIndex + "].portLocations[" + i + "].portLocationId.beginDate", "invalid.date", "");
                hasError = true;
              }
            }
          }
        }
      }
    }

    return hasError;
  }

  public static void addZoneError(int zoneIndex, Errors errors)
  {
    errors.rejectValue("zoneExtList[" + zoneIndex + "]", "invalid", "");
  }

  public static boolean validateTestPrices(List testPrices, Errors errors)
  {
    if((testPrices == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<testPrices.size(); i++)
    {
      TestPrice testPrice = (TestPrice)testPrices.get(i);

      String zone = testPrice.getTestPriceId().getLocation();
      if( (zone == null) || "".equals(zone.trim()))
      {
        errors.rejectValue("testPrices[" + i + "].testPriceId.location", "not.blank", "");

        return true;
      }

      String contractRef = testPrice.getContractRef();
      if( (contractRef == null) || "".equals(contractRef.trim()))
      {
        errors.rejectValue("testPrices[" + i + "].contractRef", "not.blank", "");
      }

      Integer minQty = testPrice.getTestPriceId().getMinQty();
      if(minQty == null)
      {
        errors.rejectValue("testPrices[" + i + "].testPriceId.minQty", "not.blank", "");
      }
      else if(minQty < 0)
      {
        errors.rejectValue("testPrices[" + i + "].testPriceId.minQty", "non_negative", "");
      }

      Integer maxQty = testPrice.getTestPriceId().getMaxQty();
      if(maxQty == null)
      {
        errors.rejectValue("testPrices[" + i + "].testPriceId.maxQty", "not.blank", "");
      }
      else if(maxQty < 0)
      {
        errors.rejectValue("testPrices[" + i + "].testPriceId.maxQty", "non_negative", "");
      }

      Double unitPrice = testPrice.getUnitPrice();
      if(unitPrice == null)
      {
        errors.rejectValue("testPrices[" + i + "].unitPrice", "not.blank", "");
      }
      else if(unitPrice < 0)
      {
        errors.rejectValue("testPrices[" + i + "].unitPrice", "non_negative", "");
      }

      Date beginDate = testPrice.getTestPriceId().getBeginDate();
      Date endDate = testPrice.getEndDate();

      if(beginDate == null)
      {
        errors.rejectValue("testPrices[" + i + "].testPriceId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("testPrices[" + i + "].endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("testPrices[" + i + "].testPriceId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if(i < testPrices.size() - 1)
        {
          TestPrice nextTestPrice = (TestPrice) (TestUtil.getNextTestPriceList(testPrices, testPrice.getTestPriceId().getLocation(), i + 1)).get(0);

          if(nextTestPrice != null)
          {
            Date nextBeginDate = nextTestPrice.getTestPriceId().getBeginDate();
            Date nextEndDate = nextTestPrice.getEndDate();

            if(nextEndDate != null)
            {
              dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
              if(dateResult <= 0)
              {
                errors.rejectValue("testPrices[" + i + "].testPriceId.beginDate", "invalid.date", "");
                hasError = true;
              }
            }
          }
        }
      }
    }

    return hasError;
  }


  public static boolean validateSlatePrices(List slatePrices, Errors errors)
  {
    if((slatePrices == null) || (errors == null)) return false;

    boolean hasError = false;

    for(int i=0; i<slatePrices.size(); i++)
    {
      SlatePrice slatePrice = (SlatePrice)slatePrices.get(i);

      String zone = slatePrice.getSlatePriceId().getLocation();
      if( (zone == null) || "".equals(zone.trim()))
      {
        errors.rejectValue("slatePrices[" + i + "].slatePriceId.location", "not.blank", "");

        return true;
      }

      String contractRef = slatePrice.getContractRef();
      if( (contractRef == null) || "".equals(contractRef.trim()))
      {
        errors.rejectValue("slatePrices[" + i + "].contractRef", "not.blank", "");
      }

      Integer minQty = slatePrice.getSlatePriceId().getMinQty();
      if(minQty == null)
      {
        errors.rejectValue("slatePrices[" + i + "].slatePriceId.minQty", "not.blank", "");
      }
      else if(minQty < 0)
      {
        errors.rejectValue("slatePrices[" + i + "].slatePriceId.minQty", "non_negative", "");
      }

      Integer maxQty = slatePrice.getSlatePriceId().getMaxQty();
      if(maxQty == null)
      {
        errors.rejectValue("slatePrices[" + i + "].slatePriceId.maxQty", "not.blank", "");
      }
      else if(maxQty < 0)
      {
        errors.rejectValue("slatePrices[" + i + "].slatePriceId.maxQty", "non_negative", "");
      }

      Double unitPrice = slatePrice.getUnitPrice();
      if(unitPrice == null)
      {
        errors.rejectValue("slatePrices[" + i + "].unitPrice", "not.blank", "");
      }
      else if(unitPrice < 0)
      {
        errors.rejectValue("slatePrices[" + i + "].unitPrice", "non_negative", "");
      }

      Date beginDate = slatePrice.getSlatePriceId().getBeginDate();
      Date endDate = slatePrice.getEndDate();

      if(beginDate == null)
      {
        errors.rejectValue("slatePrices[" + i + "].slatePriceId.beginDate", "not.blank", "");
        hasError = true;
      }
      else if(endDate == null)
      {
        errors.rejectValue("slatePrices[" + i + "].endDate", "not.blank", "");
        hasError = true;
      }
      else
      {
        int dateResult = DateUtil.compareToInDate(beginDate, endDate);
        if(dateResult > 0)
        {
          errors.rejectValue("slatePrices[" + i + "].slatePriceId.beginDate", "invalid.date", "");
          hasError = true;

          continue;
        }

        if(i < slatePrices.size() - 1)
        {
          SlatePrice nextSlatePrice = (SlatePrice) (SlateUtil.getNextSlatePriceList(slatePrices, slatePrice.getSlatePriceId().getLocation(), i + 1)).get(0);

          if(nextSlatePrice != null)
          {
            Date nextBeginDate = nextSlatePrice.getSlatePriceId().getBeginDate();
            Date nextEndDate = nextSlatePrice.getEndDate();

            if(nextEndDate != null)
            {
              dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
              if(dateResult <= 0)
              {
                errors.rejectValue("slatePrices[" + i + "].slatePriceId.beginDate", "invalid.date", "");
                hasError = true;
              }
            }
          }
        }
      }
    }

    return hasError;
  }

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

        if(i < serviceRateExtList.size() - 1)
        {
          ServiceRateExt nextServiceRateExt = (ServiceRateExt) (ContractServiceUtil.getNextServiceRateExtListByCurrentServiceRate(serviceRateExtList, serviceRate, i + 1)).get(0);

          if(nextServiceRateExt != null)
          {
            ServiceRate nextServiceRate = nextServiceRateExt.getServiceRate();
            Date nextBeginDate = nextServiceRate.getServiceRateId().getBeginDate();
            Date nextEndDate = nextServiceRate.getEndDate();

            if(nextEndDate != null)
            {
              dateResult = DateUtil.compareToInDate(nextBeginDate, endDate);
              if(dateResult <= 0)
              {
                errors.rejectValue("serviceRates.serviceLevel.serviceVersionExtList[" + versionIndex + "].contractServiceRateExtList[" + i + "].serviceRate.serviceRateId.beginDate", "invalid.date", "");
                hasError = true;
              }
            }
          }
        }
      }
    }

    return hasError;
  }

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

      if(i < inspectionRateExtList.size() - 1)
      {
        InspectionRateExt nextInspectionRateExt = (InspectionRateExt) InspectionRateUtil.getNextInspectionRateExtListByCurrentInspectionRate(inspectionRateExtList, inspectionRate, i + 1).get(0);

        if(nextInspectionRateExt != null)
        {
          errors.rejectValue("inspectionVersionExtList[" + versionIndex + "].vesselTypeExtList[" + vesselTypeExtIndex + "].contractInspectionRateExtList[" + i + "].inspectionRate.inspectionRateId.groupId", "duplicate", "");
          hasError = true;
        }
      }
    }

    return hasError;
  }
  

  /**
   * Name :validateDate 
   * Purpose : Validates date range. 
   * Begin date can not be greater than end date
   * 
   * @param dateSmaller
   *            the date smaller
   * @param dateLarger
   *            the date larger
   * @param errors
   *            the errors
   * 
   * @return true, if successful
   */
  public static boolean validateDate(Date dateSmaller, Date dateLarger, Errors errors) {
      boolean hasError = false;

      if (dateSmaller.after(dateLarger)) {
          hasError = true;
      }

      return hasError;
  }
}
