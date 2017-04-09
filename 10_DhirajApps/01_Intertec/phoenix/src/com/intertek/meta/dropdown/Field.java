package com.intertek.meta.dropdown;

/**
 * A Field is a name-value pair used to represent an entry in the dropdown.
 *
 **/
public class Field
{
  private String name;
  private String value;

  public Field(){
      
  }
  
  public Field(String name, String value){
      this.name=name;
      this.value=value;
  }
  
  /**
   * Get the name of the field.
   *
   * @return - the name of the field.
   *
   **/
  public String getName()
  {
    return name;
  }

  /**
   * Set the name of the field.
   *
   * @parame name - the name of the field.
   *
   **/
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Get the value of the field.
   *
   * @return - the value of the field.
   *
   **/
  public String getValue()
  {
    return value;
  }

  /**
   * Set the value of the field.
   *
   * @param value - the value of the field.
   *
   **/
  public void setValue(String value)
  {
    this.value = value;
  }
}
