package com.intertek.meta;

import java.util.List;

import org.jdom.Element;

import com.intertek.util.Constants;

public class KeysMetaBuilder extends MetaEntityBuilder
{
  public MetaEntity build(MetaEntity metaEntity, Element element)
  {
    if(metaEntity == null) metaEntity = new KeysMeta();
    KeysMeta keysMeta = (KeysMeta)metaEntity;

    super.build(keysMeta, element);

    List children = element.getChildren(Constants.KEY);
    for(int i=0; i<children.size(); i++)
    {
      Element child = (Element)children.get(i);

      MetaBuilder builder = (MetaBuilder)MetaBuilderFactory.getBuilder(child.getName());
      if(builder != null)
      {
        KeyMeta keyMeta = (KeyMeta)builder.build(null, child);
        keysMeta.addKeyMeta(keyMeta);
      }
    }

    return keysMeta;
  }
}
