package com.intertek.meta.dropdown;

import java.util.List;

/**
 * A dropdown is a list of fields used to populate the selection box in jsp.
 *
 **/
public interface DropDown
{
  /**
   * Execute some logic to get a list of fields based on a list of parameters.
   *
   * @param params - a list of parameter used by the dropdown implemenation to get the list of fields.
   * @return - the list of fields used to populate the selection box in jsp.
   *
   **/
  List execute(List params);
}
