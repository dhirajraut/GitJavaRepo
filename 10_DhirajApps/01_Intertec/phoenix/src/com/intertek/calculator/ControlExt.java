package com.intertek.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.intertek.entity.Control;
import com.intertek.util.Constants;

/**
 * A ContractExt which wrap a normal Control entity and used in UI to capture user input.
 *
 **/

public class ControlExt
{
  private Control oldControl;
  private Control control;
  private Map dataMap = new HashMap();
  private String dataInput;
  private boolean deleted;

  /**
   * .ctor
   */
  public ControlExt()
  {
  }

  /**
   * .ctor
   *
   * @param control the control entity to be wrapped.
   */
  public ControlExt(Control control)
  {
    this.control = control;
  }

  /**
   * .ctor
   *
   * @param control the control entity to be wrapped.
   * @param userInputs the Map to contain the user inputs.
   */
  public ControlExt(Control control, Map userInputs)
  {
    this(control);
    if(userInputs != null) this.dataMap.putAll(userInputs);
  }

  /**
   * Get the wrapped old control entity.
   *
   * @return the wrapped old control entity.
   */
  public Control getOldControl()
  {
    return oldControl;
  }

  /**
   * Set the wrapped old control entity.
   *
   * @param control the old control entity to be wrapped.
   */
  public void setOldControl(Control oldControl)
  {
    this.oldControl = oldControl;
  }

  /**
   * Get the wrapped control entity.
   *
   * @return the wrapped control entity.
   */
  public Control getControl()
  {
    return control;
  }

  /**
   * Set the wrapped control entity.
   *
   * @param control the control entity to be wrapped.
   */
  public void setControl(Control control)
  {
    this.control = control;
  }

  /**
   * Get the parameter value by parameter name.
   *
   * @return the parameter value.
   */
  public Object getParameter(String paramName)
  {
    return dataMap.get(paramName);
  }

  /**
   * Set a paired parameter name and value.
   *
   * @param paramName the parameter name.
   * @param value the parameter value.
   */
  public void setParameter(String paramName, Object value)
  {
    dataMap.put(paramName, value);
  }

  /**
   * Get the user input string.
   *
   * @return the user input string.
   */
  public String getDataInput()
  {
    return dataInput;
  }

  /**
   * Set the user input string.
   *
   * @param dataInput the user input string.
   */
  public void setDataInput(String dataInput)
  {
    this.dataInput = dataInput;
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

  public boolean getDeleted()
  {
    return deleted;
  }

  public void setDeleted(boolean deleted)
  {
    this.deleted = deleted;
  }

  /**
   * Get the display items for the control as a Map
   *
   * @return the map of display items
   */
  public Map getDisplayItems()
  {
    Map map = new TreeMap();
    if(control == null) return map;

    if( Constants.PRODUCT_TYPE.equals(control.getControlId().getObjectName())
      || Constants.PRODUCT_TYPE1.equals(control.getControlId().getObjectName())
    )
    {
      map.put(Constants.Petroleum, Constants.Petroleum);
      map.put(Constants.Chemical, Constants.Chemical);
      map.put(Constants.None, Constants.None);
    }
    else if( Constants.LIGHTERAGE_TYPE.equals(control.getControlId().getObjectName()) )
    {
      map.put(Constants.Harbor, Constants.Harbor);
      map.put(Constants.Offshore, Constants.Offshore);
      map.put(Constants.No_Lighterage, Constants.No_Lighterage);
    }
    else if( Constants.Tow.equals(control.getControlId().getObjectName()) )
    {
      map.put("NoTow", "No Tow");
      map.put("Tow1", "Tow1");
      map.put("Tow2", "Tow2");
      map.put("Tow3", "Tow3");
      map.put("Tow4", "Tow4");
      map.put("Tow5", "Tow5");

      if(dataInput == null) dataInput = "NoTow";
    }
    else
    {
      map.put(Constants.Yes, Constants.Yes);
      map.put(Constants.No, Constants.No);

      if(dataInput == null) dataInput = Constants.No;
    }

    return map;
  }
}
