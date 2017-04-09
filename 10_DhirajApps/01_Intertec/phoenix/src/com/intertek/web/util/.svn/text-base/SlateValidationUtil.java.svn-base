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

public class SlateValidationUtil
{
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

      if((minQty != null) && (maxQty != null) && (minQty.intValue() >= maxQty.intValue()))
      {
        errors.rejectValue("slatePrices[" + i + "].slatePriceId.minQty", "invalid", "");
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

        if((minQty == null) || (maxQty == null)) continue;

        if(i < slatePrices.size() - 1)
        {
          List nextSlatePriceList = SlateUtil.getNextSlatePriceList(slatePrices, slatePrice.getSlatePriceId().getLocation(), i + 1);

          for(int j=0; j<nextSlatePriceList.size(); j++)
          {
            SlatePrice nextSlatePrice = (SlatePrice)nextSlatePriceList.get(j);

            Date nextBeginDate = nextSlatePrice.getSlatePriceId().getBeginDate();
            Date nextEndDate = nextSlatePrice.getEndDate();
            Integer nextMinQty = nextSlatePrice.getSlatePriceId().getMinQty();
            Integer nextMaxQty = nextSlatePrice.getSlatePriceId().getMaxQty();

            if((beginDate != null) && (nextEndDate != null) && (nextMinQty != null) && (nextMaxQty != null))
            {
              dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
              if(dateResult <= 0)
              {
                boolean overlap = NumberUtil.isOverLap(minQty, maxQty, nextMinQty, nextMaxQty);
                if(overlap)
                {
                  errors.rejectValue("slatePrices[" + i + "].slatePriceId.beginDate", "invalid.date", "");
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
