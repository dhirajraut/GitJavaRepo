package com.intertek.meta;

import org.jdom.Element;

public class KeyMetaBuilder extends MetaEntityBuilder
{
  public MetaEntity build(MetaEntity metaEntity, Element element)
  {
    if(metaEntity == null) metaEntity = new KeyMeta();
    KeyMeta keyMeta =(KeyMeta)metaEntity;

    super.build(metaEntity, element);

    keyMeta.setValue(element.getText());

    return keyMeta;
  }
}
