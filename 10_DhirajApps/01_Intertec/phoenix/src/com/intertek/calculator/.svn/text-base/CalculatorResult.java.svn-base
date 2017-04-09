package com.intertek.calculator;

import java.util.HashMap;
import java.util.Map;

import com.intertek.util.Constants;

/**
 * A result which contains the calculation result.
 *
 * It contains the original ContractExpressionExt entity, totalPrice, discount and a data map which contains the parameters used in populate line description.
 *
 **/

public class CalculatorResult
{
  private ContractExpressionExt cee;
  private Double totalPrice;
  private Double discount;

  private Map dataMap = new HashMap();

  /**
   * .ctor
   */
  public CalculatorResult()
  {
  }

  /**
   * Get the ContractExpressionExt.
   *
   * @return the ContractExpressionExt.
   */
  public ContractExpressionExt getContractExpressionExt()
  {
    return cee;
  }

  /**
   * Set the ContractExpressionExt.
   *
   * @param cee ContractExpressionExt.
   */
  public void setContractExpressionExt(ContractExpressionExt cee)
  {
    this.cee = cee;
  }

  /**
   * Get the total price.
   *
   * @return the total price.
   */
  public Double getTotalPrice()
  {
    return totalPrice;
  }

  /**
   * Set the total price.
   *
   * @param totalPrice the total price.
   */
  public void setTotalPrice(Double totalPrice)
  {
    this.totalPrice = totalPrice;
  }

  /**
   * Get the discount.
   *
   * @return the discount.
   */
  public Double getDiscount()
  {
    return discount;
  }

  /**
   * Set the discount.
   *
   * @param discount the discount.
   */
  public void setDiscount(Double discount)
  {
    this.discount = discount;
  }

  /**
   * Get the parameter value by parameter name.
   *
   * @param paramName the parameter name.
   * @return the parameter value.
   */
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

  /**
   * Get the DataMap which is used to store the parameter name value pair.
   *
   * @return the data map
   */
  public Map getDataMap()
  {
    return dataMap;
  }

  /**
   * Get line description.
   *
   * @return the line description
   */
  public String constructDescription()
  {
    return constructDescription(null);
  }

  /**
   * Get line description by descriptor name.
   *
   * @param descriptorName the descriptor name used to get the Descriptor to format the line description.
   * @return the line description
   */
  public String constructDescription(String descriptorName)
  {
    if(descriptorName == null)
    {
      if(cee == null) return null;
      if(cee.getContractExpression() == null) return null;

      descriptorName = CalculatorUtil.getPricingModel(cee.getContractExpression().getComponentType());
    }

    Descriptor descriptor = DescriptorFactory.getDescriptor(descriptorName);
    if(descriptor != null) return descriptor.formatLineDescription(this);

    return null;
  }

  /**
   * Get a string representation of this object.
   *
   * @return the string description of this object.
   */
  public String toString()
  {
    return totalPrice
      + " -->glCode: " + getParameter(Constants.GL_CODE)
      + " -->department: " + getParameter(Constants.DEPARTMENT)
      + " -->serviceType: " + getParameter(Constants.SERVICE_TYPE)
      + " -->branch: " + getParameter(Constants.BRANCH_CODE)
      + " --> productGroup: " + getParameter(Constants.PRODUCT_GROUP);
  }
}
