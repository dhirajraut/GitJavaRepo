package com.intertek.service;

import java.util.Date;
import java.util.List;

import com.intertek.entity.AutoNumber;

public interface AutoNumberService
{
  void addAutoNumber(AutoNumber autoNumber);
  AutoNumber getAutoNumber(String businessUnit, String numberCategory, String numberType, boolean reset);
  AutoNumber getAutoNumber(String businessUnit, String numberCategory, String numberType);
  
  String getSequenceNumber(String businessUnit, String numberCategory, String numberType);
  List getNumCategoryByBusinessUnit(String businessUnit);
  
  String getSeqNumber(String businessUnit, String numberCategory, String numberType);
}

