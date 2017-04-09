package com.intertek.web.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.NumberUtils;

public class CustomDiscountEditor extends PropertyEditorSupport
{
  private Class numberClass;
  private NumberFormat numberFormat;
  private Object defaultValue;

  public CustomDiscountEditor(
    Class numberClass,
    NumberFormat numberFormat,
    Object defaultValue
  ) throws IllegalArgumentException
  {
    if (numberClass == null ||
      (numberClass != Integer.class
       && numberClass != Float.class
       && numberClass != Double.class
       && numberClass != Long.class)
    )
    {
      throw new IllegalArgumentException(
        "The parameter 'numberClass' must not be null and must either be Integer, Float, Long, or Double."
      );
    }

    if (defaultValue == null)
    {
      if (numberClass == Integer.class)
      {
        defaultValue = new Integer(-1);
      }
      else if (numberClass != Float.class)
      {
        defaultValue = new Float(-1);
      }
      else if (numberClass != Double.class)
      {
        defaultValue = new Double(-1);
      }
      else if (numberClass != Long.class)
      {
        defaultValue = new Long(-1);
      }
    }

    if (defaultValue == null || numberClass.isInstance(defaultValue) == false)
    {
      throw new IllegalArgumentException(
        "The parameter 'defaultValue' must not be null and must be of the same type as parameter 'numberClass'."
      );
    }

    this.numberClass = numberClass;
    this.numberFormat = numberFormat;
    this.defaultValue = defaultValue;
  }

  public void setAsText(String text) throws IllegalArgumentException
  {
    if (StringUtils.isEmpty(text) == true)
    {
      // If the text is null or empty string, then set to default value.
      setValue(this.defaultValue);
    }
    else if (this.numberFormat != null)
    {
      // use given NumberFormat for parsing text
      setValue(NumberUtils.parseNumber(text, this.numberClass, this.numberFormat));
    }
    else
    {
      // use default valueOf methods for parsing text
      setValue(NumberUtils.parseNumber(text, this.numberClass));
    }
  }

  public String getAsText()
  {
    Object value = getValue();
    if (value != null)
    {
      Number number = (Number)value;
      if(number.intValue() < 0) return "";

      if (this.numberFormat != null)
      {
        // use NumberFormat for rendering value
        return this.numberFormat.format(value);
      }
      else
      {
        // use toString method for rendering value
        return value.toString();
      }
    }
    else
    {
      return "";
    }
  }
}
