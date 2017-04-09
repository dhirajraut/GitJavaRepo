package com.intertek.service;

import java.util.List;

import com.intertek.domain.CreditAnalystSearch;
import com.intertek.entity.CreditAnalyst;

public interface CreditAnalystService
{
  void searchCreditAnalyst(CreditAnalystSearch search);
  List listCreditAnalystByCode(String code);
  List searchCreditAnalystByCode(String creditAnalystCode);
  CreditAnalyst getCreditAnalystByCode(String creditAnalystCode);
  void addCreditAnalyst(CreditAnalyst ca);
  void saveCreditAnalyst(CreditAnalyst ca);
}

