package com.intertek.service;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import com.intertek.domain.AddContract;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.HighLevelService;
import com.intertek.entity.Service;
import com.intertek.pagination.Pagination;

public interface ContractService
{
  Contract getContractByContractCode(String contractCode);
  Contract  loadContractByContractCode(String contractCode);
  List loadCfgContractsByContractCode(String contractCode);
  List getContractsByContractCode(String contractCode);
  Contract addContract(Contract contract);
  Contract saveContract(Contract contract);
  void searchContract(ContractSearch search,String sortFlag);
  ContractCust getContractCustByCustCodeAndContractCode(String custCode, String contractCode);
  //START: Issue # 99933
  ContractCust loadContractCustByCustCodeContract(String custCode,String contractCode);
  //END: Issue # 99933
  ContractCustContact addContractCustContact(ContractCustContact contractCustContact);
  List getContractCustsByContractCode(String contractCode, Pagination pagination);
  int getContractCustsCountByContractCode(String contractCode);
  List getContractCustContactsByCustCodeAndContractCode(String custCode, String contractCode);
  void deleteContractCustAssociations(ContractCust contractCust);
  void deleteContractCustContactAssociations(ContractCustContact contractCustContact);
  void searchContractCode(ContractSearch search,String sortFlag);

  void submit(Contract contract, String toStateName);
  void deactivate(Contract contract, String toStateName);
  void approve(Contract contract, String toStateName);
  void reject(Contract contract, String toStateName);
  void setContractStatus(Contract contract, String toStateName);
  List getDescriptionByContractDescription(String description);
  List getContractsByOriginator(String originatorName);
  List getContractsBySignatory(String signatoryName);
  List getContractAttachmentsByUserName(String userName);
  void saveContractAttach(ContractAttach contractAttach);

  List getPriceBookSeriesByCurrencyCD(String currencyCD);
  String getPriceBookSeriesNameByPriceBookId(String priceBookId);
  List getPriceBookIdsByCurrencyCD(String currencyCD);
  List getPriceBookIdsByCurrencyCDAndPriceBookSeries(
      String currencyCD,
      String priceBookSeries
  );
  String getActivePriceBookIdByCurrencyCDAndPriceBookSeries(
      String currencyCD,
      String priceBookSeries
  );
  boolean existPriceBookByCurrencyCDAndPriceBookSeriesAndPriceBookId(
      String currencyCD,
      String priceBookSeries,
      String priceBookId
  );

  void saveAddContract(AddContract addContract);
  void saveCfgContractExtList(List cfgContractList);
  void saveCfgContract(CfgContract cfgContract);

  List getReferenceFieldsByContractId(String contractId);

  Date getEarliestBeginDateOfContract(String contractCode);
  List getContractCustsByCustCodeAndContractCode(String custCode,String contractCode);
  List getContactsByContractCode(String contactId, String contractCode);
  List loadContractCustsByCustCodeAndContractCode(AddContract addContract,String contractCode);
  List loadContactsByCustCodeAndContractCode(AddContract addContract,String custCode,String contractCode);
}


