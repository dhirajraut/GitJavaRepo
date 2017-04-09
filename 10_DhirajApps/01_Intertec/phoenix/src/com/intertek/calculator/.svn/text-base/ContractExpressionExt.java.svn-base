package com.intertek.calculator;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContractExpression;
import com.intertek.entity.Expression;

/**
 * A ContractExpressionExt which wrap a normal ContractExpression entity.
 *
 **/

public class ContractExpressionExt
{
  private ContractExpression oldCe;
  private ContractExpression ce;
  private List controlExts = new ArrayList();
  private Expression expression;
  private String expressionId;

  private String controlQuestions;
  private boolean itemChangedFlag;
  private String changedItems;
  private boolean update;

  /**
   * .ctor
   *
   */
  public ContractExpressionExt()
  {
  }

  /**
   * .ctor
   *
   * @param ce the contract expression entity to be wrapped.
   * @param controlExts a list of controlExt associated with this contract expression.
   */
  public ContractExpressionExt(ContractExpression ce, List controlExts)
  {
    this.ce = ce;
    if(controlExts != null) this.controlExts.addAll(controlExts);
  }

  /**
   * Get the wrapped old contract expression entity.
   *
   * @return the wrapped old contract expression entity.
   */
  public ContractExpression getOldContractExpression()
  {
    return oldCe;
  }

  /**
   * Set the wrapped old contract expression entity.
   *
   * @param control the old contract expression entity to be wrapped.
   */
  public void setOldContractExpression(ContractExpression oldCe)
  {
    this.oldCe = oldCe;
  }

  /**
   * Get the wrapped contract expression entity.
   *
   * @return the wrapped contract expression entity.
   */
  public ContractExpression getContractExpression()
  {
    return ce;
  }

  /**
   * Set the wrapped contract expression entity.
   *
   * @param control the contract expression entity to be wrapped.
   */
  public void setContractExpression(ContractExpression ce)
  {
    this.ce = ce;
  }

  /**
   * Get the list of ControlExts associated this contract expression.
   *
   * @return the list of ControlExts associated this contract expression.
   */
  public List getControlExts()
  {
    return controlExts;
  }

  /**
   * Get the expression associated this contract expression.
   *
   * @return the expression associated this contract expression.
   */
  public Expression getExpression()
  {
    return expression;
  }

  /**
   * Set the expression associated this contract expression.
   *
   * @param the expression associated this contract expression.
   */
  public void setExpression(Expression expression)
  {
    this.expression = expression;
  }

  /**
   * Get the expression id associated this contract expression.
   *
   * @return the id expression associated this contract expression.
   */
  public String getExpresionId()
  {
    return expressionId;
  }

  /**
   * Set the expression id associated this contract expression.
   *
   * @param the expression id associated this contract expression.
   */
  public void setExpressionId(String expressionId)
  {
    this.expressionId = expressionId;
  }

  public String getControlQuestions()
  {
    return controlQuestions;
  }

  public void setControlQuestions(String controlQuestions)
  {
    this.controlQuestions = controlQuestions;
  }

  public boolean isItemChangedFlag()
  {
    return itemChangedFlag;
  }

  public void setItemChangedFlag(boolean itemChangedFlag)
  {
    this.itemChangedFlag = itemChangedFlag;
  }

  public String getChangedItems()
  {
    return changedItems;
  }

  public void setChangedItems(String changedItems)
  {
    this.changedItems = changedItems;
  }

  public boolean isUpdate()
  {
    return update;
  }

  public void setUpdate(boolean update)
  {
    this.update = update;
  }
}
