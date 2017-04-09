package com.intertek.service;

import java.util.List;

import com.intertek.locator.ServiceLocator;
import com.intertek.util.StringUtil;

public class LangServiceImpl extends BaseService implements LangService
{
  public boolean checkLangPrerequisits(String bu_name, String lang, String custCode, int locationNumber){
    boolean result = false;
    if(StringUtil.emptyStr(bu_name)  || StringUtil.emptyStr(lang) || StringUtil.emptyStr(custCode))
      return false;

    //checking for BusinessUnitLang existance
    List buLangs =  getDao().find("from BusinessUnitLanguage b   " +
      "where b.businessUnitLanguageId.name = ?  and  b.businessUnitLanguageId.languageCD = ?",
      new Object[] { bu_name, lang }
      );
    if(buLangs.isEmpty())
      return false;
    //
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    customerService.getCustAddressDetailsBySeqNumber(locationNumber, custCode);

    //checking for customer Language existance
    List cuLangs =  getDao().find("from CustomerLanguage c   " +
      "where c.customerLanguageId.custCode = ?  and  c.customerLanguageId.languageCD = ? ",
      new Object[] { custCode, lang }
      );
    if(cuLangs.isEmpty())
      return false;

    //checking for customer Address Language existance
    List custAddrLangs = getDao().find(
        " select l from CustAddressLanguage l ,CustAddress c where  " +
        " c.custAddrSeq.custAddrSeqId.custCode = ? and  c.custAddrSeq.custAddrSeqId.locationNumber = ? and c.effStatus = 'A' and  l.custAddressLanguageId.languageCD = ?  and  "+
        " l.custAddressLanguageId.CustAddressId=c.id and "+
        " c.effDate = ( select max(effDate) from CustAddress where custAddrSeq.custAddrSeqId.custCode = ?  and custAddrSeq.custAddrSeqId.locationNumber = ? and c.effStatus = 'A' )",
        new Object[] {custCode, locationNumber, lang, custCode, locationNumber}
      );

    if(custAddrLangs.isEmpty())
      return false;


    return true;
  }


}

