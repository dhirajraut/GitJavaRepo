package com.intertek.meta;

import org.jdom.Element;

import com.intertek.util.Constants;

public class ObjectRefMetaBuilder extends MetaEntityBuilder
{
  public MetaEntity build(MetaEntity metaEntity, Element element)
  {
    if(metaEntity == null) metaEntity = new ObjectRefMeta();
    ObjectRefMeta objectRefMeta =(ObjectRefMeta)metaEntity;

    super.build(metaEntity, element);

    objectRefMeta.setKey(element.getAttributeValue(Constants.KEY));

    return objectRefMeta;
  }
}
