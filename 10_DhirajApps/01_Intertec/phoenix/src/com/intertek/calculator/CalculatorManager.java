package com.intertek.calculator;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContractExpression;
import com.intertek.entity.ControlMap;
import com.intertek.entity.Expression;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;

/**
 * This class consists of static calculation methods for the pricing logic.
 *
 **/

public class CalculatorManager
{
  /**
   * Calculate the price of a service charge based on the CalculatorRequest, a list of controlExts and a list of ContractExpressions.
   *
   * @param cr the CalculatorRequest which contains all the parameters.
   * @param controlExts a list of controlExts which contains the user inputs.
   * @param ces a list of ContractExpressions.
   * @return a list of CalculatorResults.
   **/
  public static List calculate(
    CalculatorRequest cr,
    List controlExts,  // a list of ControlExts
    List ces  // a list of ContractExpressions
  )
  {
    List results = new ArrayList();
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    List ceExts = new ArrayList();
    for(int i=0; i<ces.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ces.get(i);
      ContractExpressionExt ceExt = new ContractExpressionExt(ce, null);

      if("L-Insp".equals(ce.getContractExpressionId().getServiceName()))
      {
        if("L-Inspection".equals(ce.getContractExpressionId().getExpressionId()) && (ces.size() > 1)) continue;

        ceExt.getControlExts().addAll(controlExts);
      }
      else
      {
        List cms = calculatorService.getControlMaps(
          cr.getContract().getCfgContractId().getContractId(),
          cr.getContract().getPriceBookId(),
          ce.getContractExpressionId().getServiceName(),
          ce.getContractExpressionId().getExpressionId()
        );

        for(int j=0; j<controlExts.size(); j++)
        {
          ControlExt cExt = (ControlExt)controlExts.get(j);
          String objectName = cExt.getControl().getControlId().getObjectName();
          for(int k=0; k<cms.size(); k++)
          {
            ControlMap cm = (ControlMap)cms.get(k);
            if(objectName.equals(cm.getControlMapId().getObjectName()))
            {
              ceExt.getControlExts().add(cExt);
              ceExt.setExpressionId(cm.getControlMapId().getExpressionId());
              break;
            }
          }
        }

        // ???????
        //if((cms.size() == 0) && (controlExts.size() == 1))
        //{
        //  ControlExt cExt = (ControlExt)controlExts.get(0);
        //  ceExt.getControlExts().add(cExt);
        //}
      }

      ceExts.add(ceExt);
    }

    for(int i=0; i<ceExts.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExts.get(i);

      if(ceExt.getControlExts().size() > 0)
      {
        Expression expression = calculatorService.getExpressionByExpressionId(
          //ceExt.getContractExpression().getContractExpressionId().getExpressionId()
          ceExt.getExpresionId()
        );

        ceExt.setExpression(expression);
        cr.setParameter(Constants.CONTRACT_EXPRESSION_EXT, ceExt);

        if("L-Insp".equals(ceExt.getContractExpression().getContractExpressionId().getServiceName()))
        {
          Pricer pricer = PricerFactory.getPricer(Constants.CALCULATOR_INSPECTION_PRICER);

          CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

          if(calculatorResult != null) results.add(calculatorResult);
        }
        else
        {
          Pricer pricer = PricerFactory.getPricer(
            CalculatorUtil.getPricingModel(ceExt.getContractExpression().getComponentType())
          );

          CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

          if(calculatorResult != null) results.add(calculatorResult);
        }
      }
    }

    return results;
  }

  /**
   * Calculate the price of a test charge based on the CalculatorRequest.
   *
   * @param cr the CalculatorRequest which contains all the parameters.
   * @return a list of CalculatorResults.
   **/
  public static List calculateTest(CalculatorRequest cr)
  {
    List results = new ArrayList();

    Pricer pricer = PricerFactory.getPricer(Constants.CALCULATOR_TEST_PRICER);
    if(pricer != null)
    {
      CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

      if(calculatorResult != null) results.add(calculatorResult);
    }

    return results;
  }

  /**
   * Calculate the price of a slate charge based on the CalculatorRequest.
   *
   * @param cr the CalculatorRequest which contains all the parameters.
   * @return a list of CalculatorResults.
   **/
  public static List calculateSlate(CalculatorRequest cr)
  {
    List results = new ArrayList();

    Pricer pricer = PricerFactory.getPricer(Constants.CALCULATOR_SLATE_PRICER);
    if(pricer != null)
    {
      CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

      if(calculatorResult != null) results.add(calculatorResult);
    }

    return results;
  }

  /**
   * Calculate the price of a lot level inspection charge based on the CalculatorRequest, a list of controlExts, a list of ContractExpressions and a model type.
   *
   * @param cr the CalculatorRequest which contains all the parameters.
   * @param controlExts a list of controlExts which contains the user inputs.
   * @param ces a list of ContractExpressions.
   * @param modelType the model type: 0 -->model-1, 1 --> model-2, 2 --> model-3
   * @return a list of CalculatorResults.
   **/
  public static List calculateInspection(
    CalculatorRequest cr,
    List controlExts,  // a list of ControlExts
    List ces,  // a list of ContractExpressions,
    int modelType
  )
  {
    List results = new ArrayList();
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    cleanInspectionContractExpressions(
      ces,
      cr.getContract().getCfgContractId().getContractId()
    );

    List ceExts = new ArrayList();
    for(int i=0; i<ces.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ces.get(i);
      ContractExpressionExt ceExt = new ContractExpressionExt(ce, null);

      if(modelType > 0)
      {
        if("L-Inspection".equals(ce.getContractExpressionId().getExpressionId()) && (ces.size() > 1)) continue;
      }
      else
      {
        if(!"L-Inspection".equals(ce.getContractExpressionId().getExpressionId()) && (ces.size() > 1)) continue;
      }

      ceExt.getControlExts().addAll(controlExts);

      ceExts.add(ceExt);
    }

    if((ceExts.size() == 0) && (ces.size() > 0))
    {
      ContractExpression ce = (ContractExpression)ces.get(0);
      ContractExpressionExt ceExt = new ContractExpressionExt(ce, null);
      ceExt.getControlExts().addAll(controlExts);
      ceExts.add(ceExt);
    }

    for(int i=0; i<ceExts.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExts.get(i);

      if(ceExt.getControlExts().size() > 0)
      {
        Expression expression = calculatorService.getExpressionByExpressionId(
          //ceExt.getContractExpression().getContractExpressionId().getExpressionId()
          ceExt.getExpresionId()
        );

        ceExt.setExpression(expression);
        cr.setParameter(Constants.CONTRACT_EXPRESSION_EXT, ceExt);

        Pricer pricer = PricerFactory.getPricer(Constants.CALCULATOR_INSPECTION_PRICER);

        CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

        if(calculatorResult != null) results.add(calculatorResult);
      }
    }

    return results;
  }

  /**
   * Calculate the price of a vessel level inspection (model-2) charge based on the CalculatorRequest, a list of controlExts, and a list of ContractExpressions.
   *
   * @param cr the CalculatorRequest which contains all the parameters.
   * @param controlExts a list of controlExts which contains the user inputs.
   * @param ces a list of ContractExpressions.
   * @return a list of CalculatorResults.
   **/
  public static List calculateInspectionForVessel(
    CalculatorRequest cr,
    List controlExts,  // a list of ControlExts
    List ces  // a list of ContractExpressions,
  )
  {
    List results = new ArrayList();
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    cleanInspectionContractExpressions(
      ces,
      cr.getContract().getCfgContractId().getContractId()
    );
    List ceExts = new ArrayList();
    for(int i=0; i<ces.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ces.get(i);
      ContractExpressionExt ceExt = new ContractExpressionExt(ce, null);

      if("Vessel-Insp".equals(ce.getContractExpressionId().getExpressionId()) && (ces.size() > 1)) continue;

      ceExt.getControlExts().addAll(controlExts);

      ceExts.add(ceExt);
    }

    for(int i=0; i<ceExts.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExts.get(i);

      if(ceExt.getControlExts().size() > 0)
      {
        Expression expression = calculatorService.getExpressionByExpressionId(
          ceExt.getExpresionId()
        );

        ceExt.setExpression(expression);
        cr.setParameter(Constants.CONTRACT_EXPRESSION_EXT, ceExt);

        Pricer pricer = PricerFactory.getPricer(Constants.CALCULATOR_INSPECTION_VESSEL_PRICER);

        CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

        if(calculatorResult != null) results.add(calculatorResult);
      }
    }

    return results;
  }

  /**
   * Calculate the price of a contract level inspection (model-2) charge based on the CalculatorRequest, a list of controlExts, and a list of ContractExpressions.
   *
   * @param cr the CalculatorRequest which contains all the parameters.
   * @param controlExts a list of controlExts which contains the user inputs.
   * @param ces a list of ContractExpressions.
   * @return a list of CalculatorResults.
   **/
  public static List calculateInspectionForContract(
    CalculatorRequest cr,
    List controlExts,  // a list of ControlExts
    List ces  // a list of ContractExpressions,
  )
  {
    List results = new ArrayList();
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    cleanInspectionContractExpressions(
      ces,
      cr.getContract().getCfgContractId().getContractId()
    );
    List ceExts = new ArrayList();
    for(int i=0; i<ces.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ces.get(i);
      ContractExpressionExt ceExt = new ContractExpressionExt(ce, null);

      ceExt.getControlExts().addAll(controlExts);

      ceExts.add(ceExt);
    }

    for(int i=0; i<ceExts.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExts.get(i);

      if(ceExt.getControlExts().size() > 0)
      {
        Expression expression = calculatorService.getExpressionByExpressionId(
          ceExt.getExpresionId()
        );

        ceExt.setExpression(expression);
        cr.setParameter(Constants.CONTRACT_EXPRESSION_EXT, ceExt);

        Pricer pricer = PricerFactory.getPricer(Constants.CALCULATOR_INSPECTION_CONTRACT_PRICER);

        CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

        if(calculatorResult != null) results.add(calculatorResult);
      }
    }

    return results;
  }

  /**
   * Calculate the price of a inspection (model-3 SHELL) charge based on the CalculatorRequest, a list of controlExts, and a list of ContractExpressions.
   *
   * @param cr the CalculatorRequest which contains all the parameters.
   * @param controlExts a list of controlExts which contains the user inputs.
   * @param ces a list of ContractExpressions.
   * @return a list of CalculatorResults.
   **/
  public static List calculateInspectionForShell(
    CalculatorRequest cr,
    List controlExts,  // a list of ControlExts
    List ces  // a list of ContractExpressions,
  )
  {
    List results = new ArrayList();
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    cleanInspectionContractExpressions(
      ces,
      cr.getContract().getCfgContractId().getContractId()
    );

    List ceExts = new ArrayList();
    for(int i=0; i<ces.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ces.get(i);
      ContractExpressionExt ceExt = new ContractExpressionExt(ce, null);

      ceExt.getControlExts().addAll(controlExts);

      ceExts.add(ceExt);
    }

    for(int i=0; i<ceExts.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExts.get(i);

      if(ceExt.getControlExts().size() > 0)
      {
        Expression expression = calculatorService.getExpressionByExpressionId(
          ceExt.getExpresionId()
        );

        ceExt.setExpression(expression);
        cr.setParameter(Constants.CONTRACT_EXPRESSION_EXT, ceExt);

        Pricer pricer = PricerFactory.getPricer(Constants.CALCULATOR_INSPECTION_SHELL_PRICER);

        CalculatorResult calculatorResult = (CalculatorResult)pricer.applyPricingLogic(cr);

        if(calculatorResult != null) results.add(calculatorResult);
      }
    }

    return results;
  }

  /**
   * @param ces takes a List
   * @param contractCode takes a String
   **/
  private static void cleanInspectionContractExpressions(
    List ces,  // contract expression list
    String contractCode
  )
  {
    if((ces == null) || (contractCode == null)) return;

    List contractList = new ArrayList();
    List pricebookList = new ArrayList();

    for(int i=0; i<ces.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ces.get(i);
      if(contractCode.equals(ce.getContractExpressionId().getContractId()))
      {
        contractList.add(ce);
      }
      else
      {
        pricebookList.add(ce);
      }
    }

    ces.clear();

    if(contractList.size() > 0) ces.addAll(contractList);
    else ces.addAll(pricebookList);
  }
}
