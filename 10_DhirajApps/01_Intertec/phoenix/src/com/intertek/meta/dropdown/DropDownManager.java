package com.intertek.meta.dropdown;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.intertek.config.DtdEntityResolver;
import com.intertek.util.CommonUtil;
import com.intertek.util.Constants;

/**
 * This class is the manager of the dropdown framework. It is a singleton class.
 *
 * It will load the dropdown definition from dropdown.xml in the classpath. And it is used to run any of the defined dropdown with its id and a list of parameters.
 *
 **/

public class DropDownManager
{
  private static final Log log = LogFactory.getLog(DropDownManager.class);

  private String[] locations;

  private Map dropDownMetaMap = new HashMap(); //(dropDownId, dropDown)

  private DropDownManager()
  {
  }

  /**
   * Set the location of the dropdown defintion file.
   *
   * @param location - the location of the dropdown defintion file.
   *
   **/
  public void setLocation(String location) {
    this.locations = new String[] {location};
  }

  /**
   * Set the locations of the dropdown defintion file.
   *
   * @param locations - an array of locations of the dropdown defintion files.
   *
   **/
  public void setLocations(String[] locations) {
    this.locations = locations;
  }

  /**
   * load the dropdown defintions from the location(s)
   *
   */
  public void loadDropDownData()
  {
    try
    {
      SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
      builder.setEntityResolver(new DtdEntityResolver());

      int size = locations != null ? locations.length : 0;
      for(int i=0; i<locations.length; i++)
      {
        System.out.println("======= locations[i] = " + i + " " + locations[i]);

        Document document = builder.build(CommonUtil.getClassPathResource(locations[i]));
        buildDropDownSet(document);
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Get a dropdown defintion by its id.
   *
   * @param id - the id of a dropdown defintion.
   * @return - the dropdown defintion identified by the id.
   *
   **/
  public DropDownMeta getDropDownMeta(String id)
  {
    return (DropDownMeta)dropDownMetaMap.get(id);
  }

  /**
   * Set a dropdown defintion by its id.
   *
   * @param id - the id of a dropdown defintion.
   * @dropDown - the dropdown defintion identified by this id.
   *
   **/
  public void setDropDownMeta(String id, DropDownMeta dropDown)
  {
    dropDownMetaMap.put(id, dropDown);
  }

  /**
   * Run a dropdown to a list of fields.
   *
   * @param id - the id of a dropdown defintion.
   * @params - a list of parameters used by the dropdown defintion to get a list of fields.
   *
   **/
  public List execute(String id, List params)
  {
    DropDownMeta dropDown = (DropDownMeta)getDropDownMeta(id);
    if(dropDown == null) return null;

    return dropDown.execute(params);
  }

  private void buildDropDownSet(Document document)
  {
    Element root = document.getRootElement();

    List dropDownElements = root.getChildren(Constants.DROPDOWN);

    System.out.println("============ dropDownElements.size(): " + dropDownElements.size());
    for(int i=0; i<dropDownElements.size(); i++)
    {
      Element dropDownElement = (Element)dropDownElements.get(i);

      DropDownMeta dropDown = buildDropDown(dropDownElement);

      setDropDownMeta(dropDown.getId(), dropDown);
    }
  }

  private DropDownMeta buildDropDown(Element dropDownElement)
  {
    String id = dropDownElement.getAttributeValue(Constants.ID);
    DropDownMeta dropDown = new DropDownMeta(id);

    dropDown.setClazz(dropDownElement.getAttributeValue(Constants.CLASS_));

    List children = dropDownElement.getChildren(Constants.FIELD);
    for(int i=0; i<children.size(); i++)
    {
      Element fieldElement = (Element)children.get(i);
      Field field = new Field();

      String name = fieldElement.getAttributeValue(Constants.NAME);
      field.setName(name);

      String value = fieldElement.getAttributeValue(Constants.VALUE);
      field.setValue(value);

      dropDown.addField(field);
    }

    return dropDown;
  }

  /**
   * Get the string representation of this dropdown definitions.
   *
   * @return - the string representation of this dropdown definitions.
   *
   **/
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("\n<dropdowns>\n");
    Iterator it = dropDownMetaMap.values().iterator();
    while(it.hasNext())
    {
      DropDownMeta dropDown = (DropDownMeta)it.next();
      sb.append(dropDown);
    }
    sb.append("</dropdowns>\n");

    return sb.toString();
  }
}
