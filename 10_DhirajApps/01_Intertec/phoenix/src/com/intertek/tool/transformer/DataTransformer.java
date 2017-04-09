package com.intertek.tool.transformer;

import org.jdom.Document;

public interface DataTransformer
{
  Document transform(Document input);
}
