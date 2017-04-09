package com.intertek.meta.dropdown;

import java.util.ArrayList;
import java.util.List;

/**
 * A dropdown meta is the dropdown definition. Normally it is defined in the dropdown.xml. It is used by the dropdown manager to parse the dropdown.xml.
 *
 * A drop down meta can contains a java class implementing a DropDown interface, and (or) a list of static fields.
 *
 * The java class can be used to dynamically fetch a list of fields. It will combine with the static fields when return to the caller.
 *
 **/
public class DropDownMeta
{
  private String id;
  private String clazz;
  private List<Field> fields = new ArrayList<Field>();

  /**
   * .ctr
   *
   * @param id - the id of a dropdown definition in dropdown.xml
   *
   **/
  public DropDownMeta(String id)
  {
    this.id = id;
  }

  /**
   * Get the id of the dropdown definition.
   *
   * @return - the id of the dropdown definition.
   *
   **/
  public String getId()
  {
    return id;
  }

  /**
   * Get the class name of the dropdown definition.
   *
   * @return - the class name of the dropdown definition.
   *
   **/
  public String getClazz()
  {
    return clazz;
  }

  /**
   * Set the class name of the dropdown definition.
   *
   * @param clazz - the class name of the dropdown definition.
   *
   **/
  public void setClazz(String clazz)
  {
    this.clazz = clazz;
  }

  /**
   * Get the static list of fields of this dropdown definition.
   *
   * @return - the static list of fields of this dropdown definition.
   *
   **/
  public List getFields()
  {
    return fields;
  }

  /**
   * Add a field to the static fields of this dropdown definition.
   *
   * @param field - the field to be added to the static fields of this dropdown definition.
   *
   **/
  public void addField(Field field)
  {
    fields.add(field);
  }

  /**
   * If the class name is a valid class, call its execute method to return a list of fields, which will be combined with the static fields as the final returns.
   *
   * @param params - a list of parameters which will be used by the defined class of this dropdown.
   * @return - a list of fields for this dropdown.
   *
   **/
  public List<Field> execute(List params)
  {
    List<Field> results = new ArrayList<Field>();
    if(clazz != null)
    {
      try
      {
        DropDown dropDown = (DropDown)Class.forName(clazz).newInstance();

        results.addAll(dropDown.execute(params));
      }
      catch(Exception e)
      {
        // set error in context
        e.printStackTrace();
      }
    }

    results.addAll(fields);
    return results;
  }

  /**
   * Get the string representation of this dropdown definition.
   *
   * @return - the string representation of this dropdown definition.
   *
   **/
  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    sb.append("<dropdown ");
    sb.append("id=\"").append(id).append("\" ");
    sb.append("class=\"").append(clazz).append("\" >");

    for(int i=0; i<fields.size(); i++)
    {
      Field field= (Field)fields.get(i);
      sb.append("  <field name=\"");
      sb.append(field.getName());
      sb.append("\" value=\"");
      sb.append(field.getValue());
      sb.append("\" />");
    }

    sb.append("</dropdown ");

    return sb.toString();
  }
}
