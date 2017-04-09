package com.intertek.meta;

import java.util.List;

import org.jdom.Element;

import com.intertek.locator.ServiceLocator;
import com.intertek.util.Constants;

public class ViewMetaBuilder extends MetaEntityBuilder
{
  public MetaEntity build(MetaEntity metaEntity, Element element)
  {
    if(metaEntity == null) metaEntity = new ViewMeta();
    ViewMeta viewMeta = (ViewMeta)metaEntity;

    super.build(viewMeta, element);

    Element keysElement = element.getChild(Constants.KEYS);
    if(keysElement != null)
    {
      MetaBuilder builder = (MetaBuilder)MetaBuilderFactory.getBuilder(keysElement.getName());
      if(builder != null)
      {
        KeysMeta keysMeta = (KeysMeta)builder.build(null, keysElement);
        viewMeta.setKeysMeta(keysMeta);
      }
    }

    MetaDataManager metaDataMgr = (MetaDataManager)ServiceLocator.getInstance().getBean("metaDataMgr");

    List children = element.getChildren(Constants.OBJECT_REF);
    for(int i=0; i<children.size(); i++)
    {
      Element child = (Element)children.get(i);

      MetaBuilder builder = (MetaBuilder)MetaBuilderFactory.getBuilder(child.getName());
      if(builder != null)
      {
        ObjectRefMeta objectRefMeta = (ObjectRefMeta)builder.build(null, child);
        ObjectMeta objectMeta = metaDataMgr.getObjectMeta(objectRefMeta.getKey());
        if(objectMeta != null)
        {
          viewMeta.setObjectMeta(objectMeta.getName(), objectMeta);
        }
      }
    }

    return viewMeta;
  }
}
