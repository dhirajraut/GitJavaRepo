package com.intertek.meta;

import org.jdom.Element;

import com.intertek.util.Constants;

public class FieldMetaBuilder extends MetaEntityBuilder
{
  public MetaEntity build(MetaEntity metaEntity, Element element)
  {
    if(metaEntity == null) metaEntity = new FieldMeta();
    FieldMeta fieldMeta =(FieldMeta)metaEntity;

    super.build(metaEntity, element);

    fieldMeta.setFieldType(element.getAttributeValue(Constants.FIELD_TYPE));
    fieldMeta.setLabel(element.getAttributeValue(Constants.LABEL));
    fieldMeta.setDropdown(element.getAttributeValue(Constants.DROPDOWN));

    boolean editable = true;
    String editableStr = element.getAttributeValue(Constants.EDITABLE);
    if((editableStr != null) && (!"".equals(editableStr.trim())))
    {
      try { editable = new Boolean(editableStr).booleanValue(); } catch(Exception e) { e.printStackTrace(); }
    }
    fieldMeta.setEditable(editable);

    return fieldMeta;
  }
}
