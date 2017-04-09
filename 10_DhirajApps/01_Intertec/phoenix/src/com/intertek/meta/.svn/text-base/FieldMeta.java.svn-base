package com.intertek.meta;

public class FieldMeta extends MetaEntity
{
  private String label;
  private String fieldType;
  private boolean editable = true;
  private String dropdown;

  public String getLabel()
  {
    if(label == null) return getName();
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getFieldType()
  {
    return fieldType;
  }

  public void setFieldType(String fieldType)
  {
    this.fieldType = fieldType;
  }

  public boolean isEditable()
  {
    return editable;
  }

  public void setEditable(boolean editable)
  {
    this.editable = editable;
  }

  public String getDropdown()
  {
    return dropdown;
  }

  public void setDropdown(String dropdown)
  {
    this.dropdown = dropdown;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("\n<field name=\"" + getName()
      + "\" field_type=\"" + getFieldType()
      + "\" editable=\"" + isEditable()
      + "\" label=\"" + getLabel()
      + "\">\n");

    sb.append("</field>\n");

    return sb.toString();
  }
}
