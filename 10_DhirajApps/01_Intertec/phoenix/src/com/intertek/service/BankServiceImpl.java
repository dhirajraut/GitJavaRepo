package com.intertek.service;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.BankAccountSearch;
import com.intertek.domain.BankAccountSearchResult;
import com.intertek.entity.Bank;

public class BankServiceImpl extends BaseService implements BankService
{
  public void saveBank(Bank bank)
  {
     getDao().save(bank);
  }

  public Bank getBankByBankCode(String bankCode)
  {
    List results = getDao().find(
      "from Bank b where b.bankCode = ?",
      new Object[] { bankCode }
    );

    if(results.size() > 0) return (Bank)results.get(0);

    return null;
  }

  public List<BankAccountSearchResult> searchBankAccount(BankAccountSearch bs) {
    String sql=
    "select " +
      "  bac.business_unit_name, bac.bank_code, bac.bank_acct_code, bac.currency_code, bac.primary, " +
      "  ba.bank_acct_desc, ba.BI, " +
      "  b.bank_desc, b.bank_id_nbr " +
    "from " +
    "  bank_account_curr bac, bank_accounts ba, banks b " +
    "where " +
    "  bac.business_unit_name=ba.business_unit_name " +
    "  and bac.bank_code=ba.bank_code " +
    "  and bac.bank_acct_code=ba.bank_acct_code " +
    "  and bac.bank_code=b.bank_code " +
    "";

    ArrayList values=new ArrayList();
    String bu=null;
    String accountCode=null;
    String bankCode=null;
    String bankName=null;
    String BI=null;

    if(bs!=null){
      bu=bs.getBU();
      accountCode=bs.getAccountCode();
      bankCode=bs.getBankCode();
      bankName=bs.getBankName();
      BI=bs.getBI();
    }

    if(bu!=null && !bu.trim().isEmpty()){
      sql=sql+" and bac.business_unit_name=? ";
      values.add(bu.trim());
    }

    if(accountCode!=null && !accountCode.trim().isEmpty()){
      sql=sql+" and lower(bac.bank_acct_code) like ? ";
      values.add("%"+accountCode.toLowerCase()+"%");
    }

    if(bankCode!=null && !bankCode.trim().isEmpty()){
      sql=sql+" and lower(bac.bank_code) like ? ";
      values.add("%"+bankCode.toLowerCase()+"%");
    }

    if(bankName!=null && !bankName.trim().isEmpty()){
      sql=sql+" and lower(bank_desc) like ? ";
      values.add("%"+bankName.toLowerCase()+"%");
    }

    if(BI!=null && !BI.trim().isEmpty()){
      sql=sql+" and upper(ba.BI)=? ";
      values.add(BI.equalsIgnoreCase("true")?"Y":"N");
    }

    sql=sql+" order by bac.business_unit_name, bac.currency_code ";
    List list=this.getDao().findBySQL(sql, values.toArray());
    return createBankAccountObjList(list);
  }

  public List<BankAccountSearchResult> searchBankAccount(String BU) {
    String sql=
      "select " +
        "  bac.business_unit_name, bac.bank_code, bac.bank_acct_code, bac.currency_code, bac.primary, " +
        "  ba.bank_acct_desc, ba.BI, " +
        "  b.bank_desc, b.bank_id_nbr " +
      "from " +
      "  bank_account_curr bac, bank_accounts ba, banks b " +
      "where " +
      "  bac.business_unit_name=ba.business_unit_name " +
      "  and bac.bank_code=ba.bank_code " +
      "  and bac.bank_acct_code=ba.bank_acct_code " +
      "  and bac.bank_code=b.bank_code " +
      "  and bac.business_unit_name=? "+
      " order by bac.business_unit_name, bac.currency_code ";

    List list=this.getDao().findBySQL(sql, new Object[]{BU});
    return createBankAccountObjList(list);
  }

  private List<BankAccountSearchResult> createBankAccountObjList(List list){
    List<BankAccountSearchResult> myList=new ArrayList<BankAccountSearchResult>();
    for(int i=0; list!=null && i<list.size(); i++){
      Object[] row=(Object[])list.get(i);
      String BU1=(String)row[0];
      String bankCode1=(String)row[1];
      String bankAccountCode1=(String)row[2];
      String currencyCode1=(String)row[3];
      String primary1=row[4]==null?"N":(Character)row[4]+"";
      String bankAccountDesc1=(String)row[5];
      String BI1=row[6]==null?"N":(Character)row[6]+"";
      String bankDesc1=(String)row[7];
      String bankIDNbr1=(String)row[8];
      myList.add(new BankAccountSearchResult(BU1, bankCode1, bankAccountCode1, currencyCode1, primary1, bankAccountDesc1, BI1, bankDesc1, bankIDNbr1));
    }
    return myList;
  }

  public void saveBankAccountPrimary(String BU, String bankCode, String bankAccountCode, String currencyCode){
    String sql1="update BankAccountCurr set primary=true where bankAccountCurrId.businessUnitName=? and bankAccountCurrId.bankCode=? and bankAccountCurrId.bankAcctCode=? and bankAccountCurrId.currencyCode=?";
    String sql2="update BankAccountCurr set primary=false where bankAccountCurrId.businessUnitName=? and bankAccountCurrId.bankCode!=? and bankAccountCurrId.bankAcctCode!=? and bankAccountCurrId.currencyCode=?";
    Object[] values=new Object[]{BU, bankCode, bankAccountCode, currencyCode};
    this.getDao().bulkUpdate(sql1, values);
    this.getDao().bulkUpdate(sql2, values);
  }
}
