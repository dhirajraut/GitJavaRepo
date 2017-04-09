package com.intertek.meta;

import org.jdom.Element;

public interface MetaBuilder
{
  MetaEntity build(MetaEntity metaEntity, Element element);
}
