package com.intertek.tool.collector.impl;

import com.intertek.tool.collector.DataCollector;

public abstract class BaseDataCollector implements DataCollector
{
  protected int startPage = 1;
  protected int numInPage = 1;

  public void setStartPage(int startPage)
  {
    this.startPage = startPage;
  }

  public void setNumInPage(int numInPage)
  {
    this.numInPage = numInPage;
  }
}

