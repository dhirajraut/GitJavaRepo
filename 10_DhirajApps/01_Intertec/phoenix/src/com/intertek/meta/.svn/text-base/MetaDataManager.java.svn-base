package com.intertek.meta;

import java.io.InputStream;
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

public class MetaDataManager
{
  private static final Log log = LogFactory.getLog(MetaDataManager.class);

  private Map<String, ObjectMeta> objectMap = new HashMap<String, ObjectMeta>();
  private Map<KeysMeta, ViewMeta> metaMap = new HashMap<KeysMeta, ViewMeta>(); //(keys, MetaEntity)
  private String[] locations;

  private MetaDataManager()
  {
  }

  public void setLocation(String location) {
    this.locations = new String[] {location};
  }

  public void setLocations(String[] locations) {
    this.locations = locations;
  }

  public ViewMeta getViewMeta(String[] keys)
  {
    if(keys == null) return null;

    KeysMeta keysMeta = new KeysMeta();
    for(int i=0; i<keys.length; i++)
    {
      if(keys[i] != null)
      {
        KeyMeta keyMeta = new KeyMeta();
        keyMeta.setValue(keys[i]);

        keysMeta.addKeyMeta(keyMeta);
      }
    }

    return getViewMeta(keysMeta);
  }

  public ViewMeta getViewMeta(List keys)
  {
    if(keys == null) return null;

    KeysMeta keysMeta = new KeysMeta();
    for(int i=0; i<keys.size(); i++)
    {
      String key = (String)keys.get(i);
      if(key != null)
      {
        KeyMeta keyMeta = new KeyMeta();
        keyMeta.setValue(key);

        keysMeta.addKeyMeta(keyMeta);
      }
    }

    return getViewMeta(keysMeta);
  }

  public ObjectMeta getObjectMeta(String key)
  {
    return (ObjectMeta)objectMap.get(key);
  }

  public void setObjectMeta(String key, ObjectMeta objectMeta)
  {
    objectMap.put(key, objectMeta);
  }

  public ViewMeta getViewMeta(KeysMeta keysMeta)
  {
    return (ViewMeta)metaMap.get(keysMeta);
  }

  public void setViewMeta(KeysMeta keysMeta, ViewMeta viewMeta)
  {
    metaMap.put(keysMeta, viewMeta);
  }

  public void loadMetaData()
  {
    try
    {
      int size = locations != null ? locations.length : 0;
      for(int i=0; i<locations.length; i++)
      {
        System.out.println("======= locations[i] = " + i + " " + locations[i]);

        loadOneMetaData(CommonUtil.getClassPathResource(locations[i]));
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    System.out.println("======= metaMap = " + metaMap);
  }

  private void loadOneMetaData(InputStream inputStream) throws Exception
  {
    SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
    builder.setEntityResolver(new DtdEntityResolver());

    Document document = builder.build(inputStream);
    Element root = document.getRootElement();

    log.info("****************** In parsing meta xml: " + inputStream);

    List children = root.getChildren(Constants.OBJECT);
    for(int i=0; i<children.size(); i++)
    {
      Element child = (Element)children.get(i);
      ObjectMeta objectMeta = (ObjectMeta)MetaBuilderFactory.getBuilder(child.getName()).build(null, child);

      setObjectMeta(objectMeta.getKey(), objectMeta);
    }

    children = root.getChildren(Constants.VIEW);
    for(int i=0; i<children.size(); i++)
    {
      Element child = (Element)children.get(i);
      ViewMeta viewMeta = (ViewMeta)MetaBuilderFactory.getBuilder(child.getName()).build(null, child);

      setViewMeta(viewMeta.getKeysMeta(), viewMeta);
    }
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("\n<meta>\n");
    Iterator it = metaMap.values().iterator();
    while(it.hasNext())
    {
      MetaEntity metaEntity = (MetaEntity)it.next();
      sb.append(metaEntity);
    }
    sb.append("</meta>\n");

    return sb.toString();
  }
}