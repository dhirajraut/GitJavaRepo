package com.intertek.calculator;

import java.util.HashMap;
import java.util.Map;

import com.intertek.entity.CfgContract;

/**
 * A request for the pricing components.
 *
 * It contains the CfgContract and a data Map which contains the user input data.
 *
 **/

public class CalculatorRequest
{
  private Map dataMap = new HashMap();

  private CfgContract contract;

  /**
   * .ctor
   */
  public CalculatorRequest()
  {
  }

  /**
   * Get the CfgContract
   *
   * @return a CfgContract
   **/
  public CfgContract getContract()
  {
    return contract;
  }

  /**
   * Set the CfgContract
   *
   * @param contract a CfgContract
   **/
  public void setContract(CfgContract contract)
  {
    this.contract = contract;
  }

  /**
   * Get a parameter value by parameter name
   *
   * @param paramName the parameter name
   * @return the parameter value
   **/
  public Object getParameter(String paramName)
  {
    return dataMap.get(paramName);
  }

  /**
   * Set the parameter value by parameter name.
   *
   * @param paramName the paramerter name
   * @param value the parameter value
   **/
  public void setParameter(String paramName, Object value)
  {
    dataMap.put(paramName, value);
  }
}
