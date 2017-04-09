package com.intertek.service;

import java.util.List;

import com.intertek.domain.CollectorSearch;
import com.intertek.entity.Collector;

public interface CollectorService
{
  void searchCollector(CollectorSearch search);
  List getCollectorNameByCode(String collectorCode);
  Collector getCollectorByCode(String collectorCode);
  List listCollectorByCode(String code);
  void addCollector(Collector collector);
  void saveCollector(Collector collector);
}

