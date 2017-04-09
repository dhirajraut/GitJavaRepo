package com.intertek.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.intertek.domain.EditZone;

public interface ZoneService
{
  List getLocationDiscountsByContractCode(String contractCode);
  List getBranchLocationsByContractCode(String contractCode);
  List getPortLocationsByContractCode(String contractCode);

  List getPriceBookZoneIds();
  List getPriceBookZoneIdsForPriceBookId(String priceBookId);
  List getZoneIdsForContract(String contractCode);
  List getZoneIdsForContractWithDate(String contractCode, String priceBookId, Date date);

  Map getZoneIdServiceRateCountMap(String contractCode);
  Map getZoneIdInspectionRateCountMap(String contractCode);
  Map getZoneIdTestPriceCountMap(String contractCode);
  Map getZoneIdSlatePriceCountMap(String contractCode);

  void saveZonesFromEditZone(EditZone editZone);

  Map getBranchDescriptionMap(Collection branchNameCol);
}

