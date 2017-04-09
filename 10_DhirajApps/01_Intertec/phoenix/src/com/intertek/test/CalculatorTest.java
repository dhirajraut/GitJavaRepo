package com.intertek.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.intertek.calculator.CalculatorManager;
import com.intertek.calculator.CalculatorRequest;
import com.intertek.calculator.CalculatorResult;
import com.intertek.calculator.ControlExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.Control;
import com.intertek.entity.InspectionRate;
import com.intertek.entity.PriceBook;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;

public class CalculatorTest
{
  public static void main(String[] args) throws Exception
  {
    ApplicationContext ctx = new FileSystemXmlApplicationContext(
      new String[]
      {
        "../web/WEB-INF/applicationContext.xml"
      }
    );

    ServiceLocator.getInstance().setApplicationContext(ctx);

    CalculatorRequest cr = new CalculatorRequest();
    cr.setParameter(Constants.CONTRACT_ID, "TRAFIGA30523601");
    cr.setParameter(Constants.SERVICE_NAME, "L-Insp");
    cr.setParameter(Constants.VESSEL_TYPE, "BulkCarrier");
    cr.setParameter(Constants.PRODUCT_GROUP_ID, "AGOTHR");
    cr.setParameter(Constants.MASTER_GROUP_ID, "AGOTHR");
    cr.setParameter(Constants.SERVICE_LEVEL, "*");
    cr.setParameter(Constants.LOCATION, "REGION01");
    cr.setParameter(Constants.NOMINATION_DATE_STR, "20071020");
    cr.setParameter(
      Constants.NOMINATION_DATE,
      DateUtil.parseDate("20071020", Constants.DATE_PATTERN_0)
    );
    cr.setParameter(Constants.LANGUAGE_CD, "ENG");
    cr.setParameter(Constants.JOB_TYPE, "INSP");
    cr.setParameter(Constants.BRANCH_CODE, "US170");
    cr.setParameter(Constants.JOB_CODE, "HYDRO");
    cr.setParameter(Constants.CURRENCY_CD, "GBP");

    testInspectionPricingLogic(cr);

/*    CalculatorRequest cr = new CalculatorRequest(
      "UK_GBP05", // contract id
      "SA-Gauging", // service name
      "*", // vessel type
      "*", // product group
      "*",  // master group id
      "*", // service level
      "ALL", // location
      "20071020", // nomination date
      "ENG",  // language CD
      "INSP", // job type
      "US170", // branch Code
      "HYDRO", // job code
      "GBP" // input currency CD
    );
*/
/*
    CalculatorRequest cr = new CalculatorRequest(
      "TRAFIGA30523601", // contract id
      "LAB-SRD",  //"J-AddDock", // service name
      "*", // vessel type
      "*", // product group
      "*",  // master group id
      "*", // service level
      "REGION10", // location
      "20071121", // nomination date
      "ENG",  // language CD
      "INSP", // job type
      "USA01", // branch Code
      "HYDRO", // job code
      "USD" // input currency CD
    );
*/


    //testStandardServicePricingLogic(cr);

    //testGetInspectionRate();

    //testGetVesselType();

    //testGetProductGroup();

    //testGetServices();

    System.out.println("Done");
    System.exit(0);
  }

  public static void testStandardServicePricingLogic(CalculatorRequest cr)
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    CfgContract contract = calculatorService.getContractByContractId(
      (String)cr.getParameter(Constants.CONTRACT_ID),
      (Date)cr.getParameter(Constants.NOMINATION_DATE)
    );
    cr.setContract(contract);

    if(contract == null) return;

    String priceBookId = contract.getPriceBookId();
    if(Constants.CURRENT.equals(priceBookId))
    {
      PriceBook pb = calculatorService.getCurrentPriceBook(
        contract.getPbSeries(),
        contract.getCurrencyCD(),
        (Date)cr.getParameter(Constants.NOMINATION_DATE)
      );
      if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
    }

    List controls = calculatorService.getControls(
      (String)cr.getParameter(Constants.SERVICE_NAME),
      (String)cr.getParameter(Constants.CONTRACT_ID),
      cr.getContract().getPriceBookId(),
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
      (String)cr.getParameter(Constants.LANGUAGE_CD)
    );

    List controlExts = new ArrayList();

    System.out.println("======= controls = " + controls);
    for(int i=0; i<controls.size(); i++)
    {
      Control c = (Control)controls.get(i);
      System.out.println("=========== Control.getControlId().getServiceName() = " + c.getControlId().getServiceName());
      System.out.println("=========== c.getVisibility() = " + c.getVisibility());

      Map userInputs = new HashMap();
      userInputs.put(Constants.QUANTITY, new Double(100));
      //userInputs.put(Constants.PERCENTAGE, new Double(10));

      ControlExt cExt = new ControlExt(c, userInputs);
      controlExts.add(cExt);
    }

    System.out.println("======= controlExts = " + controlExts);

    List contractExpressions = calculatorService.getContractExpressions(
      (String)cr.getParameter(Constants.SERVICE_NAME),
      (String)cr.getParameter(Constants.CONTRACT_ID),
      cr.getContract().getPriceBookId(),
      (String)cr.getParameter(Constants.LOCATION),
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
      (String)cr.getParameter(Constants.LANGUAGE_CD)
    );

    System.out.println("======= contractExpressions = " + contractExpressions);
    for(int i=0; i<contractExpressions.size(); i++)
    {
      ContractExpression contractExpression = (ContractExpression)contractExpressions.get(i);
      System.out.println("=========== contractExpression.getContractExpressionId().getServiceName() = " + contractExpression.getContractExpressionId().getServiceName());
      System.out.println("=========== contractExpression.getVisibility() = " + contractExpression.getVisibility());
    }

    List results = CalculatorManager.calculate(
      cr, controlExts, contractExpressions
    );

    //System.out.println("=========== results = " + results);
    for(int i=0; i<results.size(); i++)
    {
      CalculatorResult calculatorResult = (CalculatorResult)results.get(i);
      if(calculatorResult != null)
      {
        System.out.println(i + " ----> " + calculatorResult.getTotalPrice() + " --> " + calculatorResult);
      }
      else
      {
        System.out.println(i + " ----> 0 --> " + calculatorResult);
      }
    }
  }

  public static void testGetInspectionRate()
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    InspectionRate ir = calculatorService.getInspectionRate(
      "KUWAITA31702501",  // contract id
      "",  // price book id
      "Inspection",  // expression id
      "Tanker", // vessel type
      "FUELAK",  // group id
      "",  //master group id
      "AVONMOUTH",  // location
      100,
      1,
      3, // uomcode
      "20071020" // nomination date
    );

    System.out.println("ir = " + ir);
  }

  public static void testGetVesselType()
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    List vts = calculatorService.getVesselTypes(
      "MCASPHA23312501", // vessel type set
      "KUWAITA31702501",  // contract id
      "", // price book id
      "AVONMOUTH",  // location
      "20071020", // nomination date
      "ENG"  // languageCD
    );

    System.out.println("vts = " + vts);
  }

  public static void testGetProductGroup()
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    List pgs = calculatorService.getProductGroups(
      "KUWAITA31702501", // product group set
      "KUWAITA31702501",  // contract id
      "",  // price book id
      "Tanker",  // vessel type
      3,  // uom code
      "AVONMOUTH",  // location
      "20071020", // nomination date
      "ENG"  // languageCD
    );

    System.out.println("pgs = " + pgs);
  }

  public static void testGetServices()
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    List services = calculatorService.getServices(
      "TRAFIGA30523601", //contract id
      "USAUSD07", // price book id
      "20071121", // nomination date
      "ENG" // language
    );

    System.out.println("services = " + services);
  }




  public static void testInspectionPricingLogic(CalculatorRequest cr)
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    CfgContract contract = calculatorService.getContractByContractId(
      (String)cr.getParameter(Constants.CONTRACT_ID),
      (Date)cr.getParameter(Constants.NOMINATION_DATE)
    );
    cr.setContract(contract);

    if(contract == null) return;

    String priceBookId = contract.getPriceBookId();
    if(Constants.CURRENT.equals(priceBookId))
    {
      PriceBook pb = calculatorService.getCurrentPriceBook(
        contract.getPbSeries(),
        contract.getCurrencyCD(),
        (Date)cr.getParameter(Constants.NOMINATION_DATE)
      );
      if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
    }

    List controlExts = new ArrayList();

    Map userInputs = new HashMap();
    userInputs.put(Constants.QUANTITY, new Double(100));
    userInputs.put(Constants.ADDITIONAL_VESSEL, new Boolean(false));  // additional vessel
    userInputs.put(Constants.ADDITIONAL_LOT, new Boolean(false)); // additional lot
    userInputs.put(Constants.UOM_CODE, new Integer(3));  // uom code  --> int_data_4 from inspection_rate table

    Control c = new Control();

    ControlExt cExt = new ControlExt(c, userInputs);
    controlExts.add(cExt);

    System.out.println("======= controlExts = " + controlExts);

    List contractExpressions = calculatorService.getContractExpressions(
      "L-Insp",
      (String)cr.getParameter(Constants.CONTRACT_ID),
      cr.getContract().getPriceBookId(),
      (String)cr.getParameter(Constants.LOCATION),
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
      (String)cr.getParameter(Constants.LANGUAGE_CD)
    );

    System.out.println("======= contractExpressions = " + contractExpressions);
    for(int i=0; i<contractExpressions.size(); i++)
    {
      ContractExpression contractExpression = (ContractExpression)contractExpressions.get(i);
      System.out.println("=========== contractExpression.getContractExpressionId().getServiceName() = " + contractExpression.getContractExpressionId().getServiceName());
      System.out.println("=========== contractExpression.getVisibility() = " + contractExpression.getVisibility());
    }

    List results = CalculatorManager.calculate(
      cr, controlExts, contractExpressions
    );

    //System.out.println("=========== results = " + results);
    for(int i=0; i<results.size(); i++)
    {
      CalculatorResult calculatorResult = (CalculatorResult)results.get(i);
      if(calculatorResult != null)
      {
        System.out.println(i + " ----> " + calculatorResult.getTotalPrice() + " --> " + calculatorResult);
      }
      else
      {
        System.out.println(i + " ----> 0 --> " + calculatorResult);
      }
    }
  }

}
