package com.intertek.service;

import java.util.List;

import com.intertek.domain.CopyPbTestPrice;
import com.intertek.domain.TestPriceExt;
import com.intertek.entity.ContractTest;
import com.intertek.entity.Test;
import com.intertek.entity.TestPrice;
import com.intertek.pagination.Pagination;

public interface TestService
{
  Test getTestById(String testId);

  TestPrice getTestPrice(
      String contractId,
      String priceBookId,
      String testId,
      String location,
      Integer quantity,
      String nominationDateStr
  );


  List getTests(
    String contractId,
    String priceBookId,
    String productGroup,
    String contractSearchCD,
    String value,
    String searchType,
    String location,
    String nominationDateStr,
    String languageCD
  );

  int getTotalRecordOfPbTestIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String testId,
    String description
  );
  List getPbTestIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String testId,
    String description,
    Pagination pagination
  );

  int getTotalRecordOfPriceBookTestIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode
  );
  List loadPriceBookTestIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode,
    Pagination pagination
  );
  List loadTestPricesByTestIds(
    String priceBookId,
    String contractCode,
    List testIds
  );
  List loadTestPricesByTestIds(
    String contractCode,
    List testIds
  );
  List loadTestsByTestIds(List testIds);

  int getTotalRecordOfContractTestIdsByPriceBookIdAndContractCode(String contractCode);
  List loadContractTestIdsByPriceBookIdAndContractCode(
      String contractCode,
      Pagination pagination
  );
  List loadContractTestsByTestIds(List testIds);

  void copySelectedTestPricesFromPb(CopyPbTestPrice copyPbTestPrice);
  void copySelectedTestPriceFromPb(String testId, String priceBookId, String contractCode);
  List getPbTestPricesByTestIdAndPriceBookId(String testId, String priceBookId);

  void deleteTestPricesByContractCodeAndTestId(String contractCode, String testId);
  void saveTestPrices(List testPrices);
  void saveTestPrices(TestPriceExt testPriceExt);

  ContractTest getContractTestById(String contractCode, String testId, String testType);
  void addContractTest(ContractTest contractTest);

//List getContractTests();
}

