package com.intertek.meta;

import java.util.List;

import org.jdom.Element;

import com.intertek.util.Constants;

public class ObjectMetaBuilder extends MetaEntityBuilder
{
  public MetaEntity build(MetaEntity metaEntity, Element element)
  {
    if(metaEntity == null) metaEntity = new ObjectMeta();
    ObjectMeta objectMeta = (ObjectMeta)metaEntity;

    super.build(objectMeta, element);

    objectMeta.setKey(element.getAttributeValue(Constants.KEY));

    List children = element.getChildren(Constants.FIELD);
    for(int i=0; i<children.size(); i++)
    {
      Element child = (Element)children.get(i);

      MetaBuilder builder = (MetaBuilder)MetaBuilderFactory.getBuilder(child.getName());
      if(builder != null)
      {
        FieldMeta fieldMeta = (FieldMeta)builder.build(null, child);
        objectMeta.getFieldMetas().add(fieldMeta);
      }
    }

    return objectMeta;
  }
}
