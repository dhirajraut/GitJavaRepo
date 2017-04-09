package com.intertek.meta;

import org.jdom.Element;

import com.intertek.util.Constants;

public abstract class MetaEntityBuilder implements MetaBuilder
{
  public MetaEntity build(MetaEntity metaEntity, Element element)
  {
    metaEntity.setName(element.getAttributeValue(Constants.NAME));

    return metaEntity;
  }
}
