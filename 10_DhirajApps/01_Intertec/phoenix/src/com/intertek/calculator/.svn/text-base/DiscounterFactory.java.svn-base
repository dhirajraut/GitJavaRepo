package com.intertek.calculator;

import java.util.HashMap;
import java.util.Map;

import com.intertek.util.Constants;

/**
 * A Factory to return different kind of Disounter implementation.
 *
 * @version 1.0, 11/12/08
 *
 * @see Discounter
 * @see StandardDiscounter
 * @see InspectionDiscounter
 * @see TestDiscounter
 * @see SlateDiscounter
 *
 * @since 1.0
 */

public class DiscounterFactory
{
  private static Map discounterMap = new HashMap();

  static
  {
    discounterMap.put(Constants.CALCULATOR_STANDARD_DISCOUNTER, new StandardDiscounter());
    discounterMap.put(Constants.CALCULATOR_INSPECTION_DISCOUNTER, new InspectionDiscounter());
    discounterMap.put(Constants.CALCULATOR_TEST_DISCOUNTER, new TestDiscounter());
    discounterMap.put(Constants.CALCULATOR_SLATE_DISCOUNTER, new SlateDiscounter());
  };

  /**
   * Returns the different Discounter implementation based on the input name.
   *
   * @param name the discounter name.
   * @return a <code>Discounter</code> implementation which implements certain discount logic.
   */
  public static Discounter getDiscounter(String name)
  {
    return (Discounter)discounterMap.get(name);
  }
}
