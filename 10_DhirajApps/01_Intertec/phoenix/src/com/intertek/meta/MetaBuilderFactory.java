package com.intertek.meta;

import java.util.HashMap;
import java.util.Map;

import com.intertek.util.Constants;

public class MetaBuilderFactory
{
  private static Map builderMap = new HashMap();

  static
  {
    builderMap.put(Constants.VIEW, new ViewMetaBuilder());
    builderMap.put(Constants.OBJECT, new ObjectMetaBuilder());
    builderMap.put(Constants.FIELD, new FieldMetaBuilder());
    builderMap.put(Constants.KEYS, new KeysMetaBuilder());
    builderMap.put(Constants.KEY, new KeyMetaBuilder());
    builderMap.put(Constants.OBJECT_REF, new ObjectRefMetaBuilder());
  };

  public static MetaBuilder getBuilder(String name)
  {
    return (MetaBuilder)builderMap.get(name);
  }
}