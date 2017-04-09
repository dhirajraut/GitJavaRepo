package com.intertek.service;

import java.util.List;

import com.intertek.domain.CopyPbSlatePrice;
import com.intertek.domain.SlatePriceExt;
import com.intertek.entity.ContractSlate;
import com.intertek.entity.Slate;
import com.intertek.entity.SlatePrice;
import com.intertek.pagination.Pagination;

public interface SlateService
{
  Slate getSlateById(String slateId);

  SlatePrice getSlatePrice(
      String contractId,
      String priceBookId,
      String slateId,
      String location,
      Integer quantity,
      String nominationDateStr
  );


  public List getSlates(
    String contractId,
    String priceBookId,
    String value,
    String searchType,
    String location,
    String nominationDateStr,
    String languageCD
  );

  int getTotalRecordOfPbSlateIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String slateId,
    String description
  );
  List getPbSlateIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String slateId,
    String description,
    Pagination pagination
  );

  int getTotalRecordOfPriceBookSlateIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode
  );
  List loadPriceBookSlateIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode,
    Pagination pagination
  );
  List loadSlatePricesBySlateIds(
    String priceBookId,
    String contractCode,
    List slateIds
  );
  List loadSlatePricesBySlateIds(
    String contractCode,
    List slateIds
  );
  List loadSlatesBySlateIds(List slateIds);

  int getTotalRecordOfContractSlateIdsByPriceBookIdAndContractCode(String contractCode);
  List loadContractSlateIdsByPriceBookIdAndContractCode(
      String contractCode,
      Pagination pagination
  );
  List loadContractSlatesBySlateIds(List slateIds);

  void copySelectedSlatePricesFromPb(CopyPbSlatePrice copyPbSlatePrice);
  void copySelectedSlatePriceFromPb(String slateId, String priceBookId, String contractCode);
  List getPbSlatePricesBySlateIdAndPriceBookId(String slateId, String priceBookId);

  void deleteSlatePricesByContractCodeAndSlateId(String contractCode, String slateId);
  void saveSlatePrices(List slatePrices);
  void saveSlatePrices(SlatePriceExt slatePriceExt);

  ContractSlate getContractSlateById(String contractCode, String slateId, String slateType);
  void addContractSlate(ContractSlate contractSlate);
}

