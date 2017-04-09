package com.intertek.calculator;

import com.intertek.entity.LocationDiscount;

/**
 * A Discounter is used to calculate the total discount percentage
 * based on the input <code>CalculatorRequest</code> and <code>LocationDiscount</code>.
 *
 * @version 1.0, 11/12/08
 *
 * @see DiscounterFactory
 * @see StandardDiscounter
 * @see InspectionDiscounter
 * @see TestDiscounter
 * @see SlateDiscounter
 *
 * @since 1.0
 */

public interface Discounter
{
  /**
   * Using input <code>CalculatorRequest</code> and and <code>LocationDiscount</code> to calculate the total discount percentage.
   *
   * @param cr the <code>CalculatorRequest</code> contains the parameters used in the discounter.
   * @param ld the <code>LocationDiscount</code> contains the discount information.
   * @return the total discount percentage.
   */
  Double getDiscount(
    CalculatorRequest cr,
    LocationDiscount ld
  );
}
