package com.intertek.service;

import java.util.List;

import com.intertek.domain.BankAccountSearch;
import com.intertek.domain.BankAccountSearchResult;
import com.intertek.entity.Bank;

public interface BankService
{
  void saveBank(Bank bank);
  Bank getBankByBankCode(String bankCode);
  
  List<BankAccountSearchResult> searchBankAccount(BankAccountSearch bs);
  
  List<BankAccountSearchResult> searchBankAccount(String BU);
  void saveBankAccountPrimary(String BU, String bankCode, String bankAccountCode, String currencyCode);
}

