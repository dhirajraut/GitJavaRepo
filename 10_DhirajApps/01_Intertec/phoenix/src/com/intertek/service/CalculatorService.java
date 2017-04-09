package com.intertek.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.intertek.entity.BranchCode;
import com.intertek.entity.CfgContract;
import com.intertek.entity.CurrencyRate;
import com.intertek.entity.Department;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionGLCode;
import com.intertek.entity.InspectionRate;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.PriceBook;
import com.intertek.entity.ProductCode;
import com.intertek.entity.RB;
import com.intertek.entity.ServiceRate;

public interface CalculatorService
{
  CfgContract getContractByContractId(String contractId, Date date);
  PriceBook getCurrentPriceBook(
    String pbSeries,
    String currencyCD,
    Date nominationDate
  );

  ServiceRate getServiceRate(
    String contractId,
    String expressionId,
    String vesselType,
    String productGroupId,
    String serviceLevel,
    String location,
    int intQuantity,
    double floatQuantity,
    String nominationDateStr
  );

  List getContractExpressions(
    String serviceName,
    String contractId,
    String priceBookId,
    String location,
    String nominationDateStr,
    String languageCD
  );

  List getControls(
    String serviceName,
    String contractId,
    String priceBookId,
    String nominationDateStr,
    String languageCD
  );

  List getServices(
    String contractId,
    String priceBookId,
    String nominationDateStr,
    String languageCD
  );


  InspectionRate getInspectionRate(
    String contractId,
    String priceBookId,
    String expressionId,
    String vesselType,
    String productGroupId,
    String masterGroupId,
    String location,
    int intQuantity,
    double floatQuantity, // not used. use 1.
    int uomCode,
    String nominationDateStr
  );

  List getVesselTypes(
    String vesselTypeSet,
    String contractId,
    String priceBookId,
    String location,
    String nominationDateStr,
    String languageCD
  );

  List getProductGroups(
      String productGroupSet,
      String contractId,
      String priceBookId,
      String vesselType,
      Integer uomCode,
      String location,
      String nominationDateStr,
      String languageCD
  );

  List getControlMaps(
    String contractId,
    String priceBookId,
    String serviceName,
    String expressionId
  );

  Expression getExpressionByExpressionId(String expressionId);
  LocationDiscount getLocationDiscount(String contractId, String location, Date date);
  ExpressionGLCode getExpressionGLCode(String expressionId, String nominationType);
  Department getDepartment(String glCode);
  BranchCode getBranchCodeByBranchCode(String branchCode);

  CurrencyRate getCurrencyRate(String fromCurrencyCD, String toCurrencyCD, Date date);

  String getProductGroup(
      Double useGroupId,
      ExpressionGLCode eGLCode,
      String jobCode,
      String masterGroup
  );
  ProductCode getProductCode(String jobCode, String noProdCode);
  double getCurrencyMultiplier(String contractCurrencyCD, String inputCurrencyCD, Date date);

  RB getRbByRbKey(String rbKey);
  List getCfgJobCodes();

  int getInspectionModelType(String contractCode, Date jobFinishDate);
  HashMap<String, Double> getCurrencyRateMap(String fromCurrencyCD, String toCurrencyCD, Date date);
}

