package com.intertek.config;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.intertek.util.CommonUtil;

/**
 * Resolve the dtd for the config xml.
 *
 **/

public class DtdEntityResolver implements EntityResolver
{
  /**
   * Resolve the dtd based on the publicId and systemId
   *
   * @param publicId - the public id of the xml
   * @param systemId - the system id of the xml
   *
   **/
  public InputSource resolveEntity (String publicId, String systemId)
  {
    if((publicId != null) && (publicId.startsWith("meta") || publicId.startsWith("statemachine")))
    {
      return new InputSource(CommonUtil.getClassPathResource(publicId));
    } else {
      return null;// use the default behaviour
    }
  }
}
