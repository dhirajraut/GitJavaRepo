package com.intertek.calculator;

import java.util.HashMap;
import java.util.Map;

import com.intertek.util.Constants;

/**
 * A Factory to return different kind of Descriptor implementation.
 *
 * @version 1.0, 11/12/08
 *
 * @see Descriptor
 * @see StandardDescriptor
 * @see BooleanBasePriceDescriptor
 * @see PbSampleRetentionDisposalDescriptor
 * @see PbLighterageDaysDescriptor
 * @see PbPressurizedCylindersDescriptor
 * @see SamplingIncludesDescriptor
 * @see InspectionDescriptor
 * @see InspectionModel2Descriptor
 * @see InspectionVesselDescriptor
 * @see InspectionContractDescriptor
 * @see InspectionShellDescriptor
 * @see TestDescriptor
 * @see SlateDescriptor
 *
 * @since 1.0
 */

public class DescriptorFactory
{
  private static Map descriptorMap = new HashMap();

  static
  {
    descriptorMap.put(Constants.CALCULATOR_STANDARD_FORMULA_DESCRIPTOR, new StandardDescriptor());
    descriptorMap.put(Constants.CALCULATOR_BOOLEAN_BASE_PRICE_DESCRIPTOR, new BooleanBasePriceDescriptor());
    descriptorMap.put(Constants.CALCULATOR_PB_SAMPLE_RETENTION_DISPOSAL_DESCRIPTOR, new PbSampleRetentionDisposalDescriptor());
    descriptorMap.put(Constants.CALCULATOR_PB_LIGHTERAGE_DAYS_DESCRIPTOR, new PbLighterageDaysDescriptor());
    descriptorMap.put(Constants.CALCULATOR_PB_PRESSURIZED_CYLINDERS_DESCRIPTOR, new PbPressurizedCylindersDescriptor());
    descriptorMap.put(Constants.CALCULATOR_SAMPLING_INCLUDES_DESCRIPTOR, new SamplingIncludesDescriptor());

    descriptorMap.put(Constants.CALCULATOR_INSPECTION_DESCRIPTOR, new InspectionDescriptor());
    descriptorMap.put(Constants.CALCULATOR_INSPECTION_MODEL_2_DESCRIPTOR, new InspectionModel2Descriptor());
    descriptorMap.put(Constants.CALCULATOR_INSPECTION_VESSEL_DESCRIPTOR, new InspectionVesselDescriptor());
    descriptorMap.put(Constants.CALCULATOR_INSPECTION_CONTRACT_DESCRIPTOR, new InspectionContractDescriptor());
    descriptorMap.put(Constants.CALCULATOR_INSPECTION_SHELL_DESCRIPTOR, new InspectionShellDescriptor());

    descriptorMap.put(Constants.CALCULATOR_TEST_DESCRIPTOR, new TestDescriptor());
    descriptorMap.put(Constants.CALCULATOR_SLATE_DESCRIPTOR, new SlateDescriptor());
  };

  public static Descriptor getDescriptor(String name)
  {
    Descriptor descriptor = (Descriptor)descriptorMap.get(name);
    if(descriptor != null) return descriptor;

    return (Descriptor)descriptorMap.get(Constants.CALCULATOR_STANDARD_FORMULA_DESCRIPTOR);
  }
}
