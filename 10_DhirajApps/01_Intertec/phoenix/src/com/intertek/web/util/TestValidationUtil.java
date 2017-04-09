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

public class TestValidationUtil
{
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

      if((minQty != null) && (maxQty != null) && (minQty.intValue() >= maxQty.intValue()))
      {
        errors.rejectValue("testPrices[" + i + "].testPriceId.minQty", "invalid", "");
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

        if((minQty == null) || (maxQty == null)) continue;

        if(i < testPrices.size() - 1)
        {
          List nextTestPriceList = TestUtil.getNextTestPriceList(testPrices, testPrice.getTestPriceId().getLocation(), i + 1);

          for(int j=0; j<nextTestPriceList.size(); j++)
          {
            TestPrice nextTestPrice = (TestPrice)nextTestPriceList.get(j);

            Date nextBeginDate = nextTestPrice.getTestPriceId().getBeginDate();
            Date nextEndDate = nextTestPrice.getEndDate();
            Integer nextMinQty = nextTestPrice.getTestPriceId().getMinQty();
            Integer nextMaxQty = nextTestPrice.getTestPriceId().getMaxQty();

            if((beginDate != null) && (nextEndDate != null) && (nextMinQty != null) && (nextMaxQty != null))
            {
              dateResult = DateUtil.compareToInDate(beginDate, nextEndDate);
              if(dateResult <= 0)
              {
                boolean overlap = NumberUtil.isOverLap(minQty, maxQty, nextMinQty, nextMaxQty);
                if(overlap)
                {
                  errors.rejectValue("testPrices[" + i + "].testPriceId.beginDate", "invalid.date", "");
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
