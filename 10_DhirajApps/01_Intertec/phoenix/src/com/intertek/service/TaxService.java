package com.intertek.service;

import java.util.Date;
import java.util.List;

import com.intertek.domain.AddTaxArticles;
import com.intertek.domain.AddTaxLabels;

import com.intertek.domain.AddTaxRates;
import com.intertek.domain.VatRateSearch;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxLabel;
import com.intertek.entity.TaxRate;

public interface TaxService
{

    TaxRate addTaxRate(TaxRate taxRate);
    boolean deleteTaxRate(TaxRate taxRate);

List getAllTaxRates(AddTaxRates search);
    TaxRate getTaxRateByVatCode(String vatCode);
    void searchVatRate(VatRateSearch search,String sortFlag);
    TaxRate getTaxdateByCode(String taxCode);
    List getTaxRatesByCode(String taxCode);
    TaxRate getTaxRateByCode(String taxCode);
    List getVatCodesByCode(String vatCode,String taxType);
    List getTaxRatesByType(String taxType);
    List getAllTaxArticles(AddTaxArticles search);
    TaxArticle addTaxArticle(TaxArticle taxArticle);
    TaxArticle getTaxArticleByCode(String taxArticleCode);
     boolean deleteTaxArticle(TaxArticle taxArticle);
     TaxCode getTaxCodeByCode(String taxCode);
     List getAllTaxCodes(String taxType);
     TaxCode getTaxCodeByCodeWithTaxRates(String taxCode);
     TaxRate getTaxRateByTaxCodeAndEffDate(String taxCode,Date jobFinishDate,String taxType);
     void searchTaxCodes(VatRateSearch search);

     TaxCode addTaxCode(TaxCode taxCode);
     TaxLabel getTaxLabelByCtryCodeAndStateCode(String ctryCode,String stateCode);
     List getAllTaxLabels(AddTaxLabels search);
     boolean deleteTaxLabel(TaxLabel taxLabel);
     TaxLabel addTaxLabel(TaxLabel taxLabel);
     TaxRate getTaxRateByTaxCodeHdr(String taxCodeHdr, Date jobFinishDate);
}

