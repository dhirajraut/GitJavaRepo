package com.intertek.service;

import java.util.List;

import com.intertek.entity.BankAccount;

public class BankAccountServiceImpl extends BaseService implements BankAccountService
{
  public void saveBankAccount(BankAccount bankAccount)
  {
     getDao().save(bankAccount);
  }

  public BankAccount getBankAccountByCompositeId(
    String buName,
    String bankCode,
    String bankAccountCode
  )
  {
    List bcs = getDao().find(
      "from BankAccount ba where ba.bankAccountId.businessUnitName = ? and ba.bankAccountId.bankCode = ? and ba.bankAccountId.bankAcctCode = ?",
      new Object[] {buName, bankCode, bankAccountCode}
    );

    if(bcs.size() > 0) return (BankAccount)bcs.get(0);

    return null;
  }
}

