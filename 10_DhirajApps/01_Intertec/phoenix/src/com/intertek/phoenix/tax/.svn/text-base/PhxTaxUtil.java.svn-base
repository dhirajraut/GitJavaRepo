package com.intertek.phoenix.tax;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intertek.entity.CountryVAT;
import com.intertek.entity.Currency;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TaxCode;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.web.controller.invoice.CEJobInvoicePreviewForm;
import com.intertek.util.Constants;
import com.intertek.util.NumberUtil;

public class PhxTaxUtil {
    public static CEInvoiceLineItem getTaxandVatCode(CEJobOrder ceJO, CEJobInvoicePreviewForm ceInvoicePreviewForm, CEInvoiceLineItem invLineItem) {
        TaxSrvc taxSrvc = ServiceManager.getTaxSrvc();
        try {
            TaxCodeInfo taxCodeInfo = taxSrvc.getTaxRateInfo(ceJO.getJobContract());
            TaxCode taxCodeList = taxSrvc.getTaxCodeByServiceLocation(ceJO.getServiceLocation());

            ceInvoicePreviewForm.setVatCode(taxCodeInfo == null ? null : taxCodeInfo.getStandardVatCode().getTaxCodeHeader());
            ceInvoicePreviewForm.setTaxCode(taxCodeList == null ? null : taxCodeList.getTaxCodeHeader());

            if (invLineItem.getTaxCode() == null || invLineItem.getTaxCode().trim().equals("")) {
                invLineItem.setTaxCode(ceInvoicePreviewForm.getTaxCode());
                invLineItem.setTaxPct(0);
            }
            if (invLineItem.getVatCode() == null || invLineItem.getVatCode().trim().equals("")) {
                invLineItem.setVatCode(ceInvoicePreviewForm.getVatCode());
                invLineItem.setVatPct(0);
            }
            Integer decimalDigits = getDecimalDigitsByCurrency(ceJO);
            Date asOfDate = ceInvoicePreviewForm.getJobOrder().getNominationDate();

            ceInvoicePreviewForm = calculateInvoiceLineItemsOnLoad(ceInvoicePreviewForm, invLineItem, asOfDate, decimalDigits);
        }
        catch (Exception e) {
            // TODO need to handle the proper exception.
            e.printStackTrace();
        }
        return invLineItem;
    }

    public static Integer getDecimalDigitsByCurrency(CEJobOrder ceJO) {
        SearchService searchService = ServiceManager.getSearchService();
        Map<String, String> sMap = new HashMap<String, String>();
        sMap.put("currencyCd", ceJO.getJobContract().getTransactionCurrencyCode());
        Integer decimalDigits = new Integer(0);
        try {
            List<Currency> listCurr = searchService.search(Currency.class, sMap);

            if (listCurr != null && listCurr.size() > 0) {
                decimalDigits = listCurr.get(0).getDecimalDigits();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return decimalDigits;
    }

    public static CEJobInvoicePreviewForm calculateInvoiceLineItemsOnLoad(CEJobInvoicePreviewForm ceInvoicePreviewForm, CEInvoiceLineItem invLineItem,
                                                                          Date taxDate, Integer roundingDigits) {
        String taxCode = ceInvoicePreviewForm.getTaxCode();
        String vatCode = ceInvoicePreviewForm.getVatCode();
        TaxSrvc taxSrvc = ServiceManager.getTaxSrvc();
        String taxArticleCode = ceInvoicePreviewForm.getTaxArticleCode();
        boolean taxVatFlag = false;
        try {
            if (invLineItem.getId().longValue() > 0) {
                invLineItem.setTaxDate(new Timestamp(taxDate.getTime()));
                if (taxCode != null && (!taxCode.trim().equals(""))) {
                    invLineItem.setTaxCode(taxCode);
                    invLineItem.setTaxPct(taxSrvc.getTaxPctByTaxCode(taxCode, taxDate));

                }
                if (vatCode != null && (!vatCode.trim().equals(""))) {
                    invLineItem.setVatCode(vatCode);
                    invLineItem.setVatPct(0);
                }
                if (taxArticleCode != null && (!taxArticleCode.trim().equals(""))) {
                    TaxArticle taxArticle = taxSrvc.getTaxArticle(taxArticleCode);
                    invLineItem.setTaxArticleCode(taxArticle.getTaxArticleCode());
                }
            }

            if ((!taxVatFlag)
                && ((invLineItem.getTaxCode() != null && (!invLineItem.getTaxCode().trim().equals(""))) || (invLineItem.getVatCode() != null && (!invLineItem
                        .getVatCode().trim().equals("")))))
                taxVatFlag = true;
            invLineItem.getInvoice().setTaxVatFlag(taxVatFlag);
            CountryVAT countryVat= taxSrvc.getVatProvinceByCountryCodeandVatCode(ceInvoicePreviewForm.getJobOrder().getBu().getCountry().getCountryCode(),invLineItem.getVatCode());
            if(countryVat!=null){
            invLineItem.getInvoice().setVatProvince(countryVat.getCountryVATId().getStateCode());
            }
            invLineItem = calculateinvLineItem(invLineItem, taxDate, roundingDigits, ceInvoicePreviewForm);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ceInvoicePreviewForm;
    }

    /**
     * To calculate the invoice line items
     * 
     * @param invLineItem
     * @param jobFinishDate
     * @param roundingDigits
     * @param buName
     * @return
     */
    public static CEInvoiceLineItem calculateinvLineItem(CEInvoiceLineItem invLineItem, Date jobFinishDate, Integer roundingDigits,
                                                         CEJobInvoicePreviewForm invoicePreviewForm) {
        try {
            TaxSrvc taxSrvc = ServiceManager.getTaxSrvc();
            if (invLineItem.getTaxCode() != null && (!invLineItem.getTaxCode().trim().equals(""))) {
                TaxCode taxCode = taxSrvc.getTaxCodeByCode(invLineItem.getTaxCode());
                if (taxCode != null) {
                    double taxPct = taxSrvc.getTaxPctByTaxCode(taxCode.getTaxCodeHeader(), jobFinishDate);
                    invLineItem.setTaxPct(taxPct);
                }
            }
            if (invLineItem.getVatCode() != null && (!invLineItem.getVatCode().trim().equals(""))) {
                TaxCode taxCode = taxSrvc.getTaxCodeByCode(invLineItem.getVatCode());
                if (taxCode != null) {
                    double taxPct = taxSrvc.getTaxPctByTaxCode(taxCode.getTaxCodeHeader(), jobFinishDate);
                    invLineItem.setVatPct(taxPct);
                }
            }

            String taxArticleCode = invoicePreviewForm.getTaxArticleCode();
            if (taxArticleCode != null && (!taxArticleCode.trim().equals(""))) {
                TaxArticle taxArticle = taxSrvc.getTaxArticle(taxArticleCode);
                invLineItem.setTaxArticleCode(taxArticle.getTaxArticleCode());
            }
            
            CountryVAT countryVat= taxSrvc.getVatProvinceByCountryCodeandVatCode(invoicePreviewForm.getJobOrder().getBu().getCountry().getCountryCode(),invLineItem.getVatCode());
            if(countryVat!=null){
            invLineItem.getInvoice().setVatProvince(countryVat.getCountryVATId().getStateCode());
            }

            if (roundingDigits == null)
                roundingDigits = 2;

            double taxPct = invLineItem.getTaxPct();
            double vatPct = invLineItem.getVatPct();

            double netPrice = invLineItem.getNetPrice();
            String taxCode = invLineItem.getTaxCode();
            String vatCode = invLineItem.getVatCode();
            TaxCode salesTaxCode = taxSrvc.getTaxCodeByCode(taxCode);
            if (salesTaxCode != null) {
                if (salesTaxCode.getIncludeVat()) {
                    double vatAmt = (netPrice * vatPct) / 100;
                    double salesTaxNetPrice = netPrice + vatAmt;
                    double taxAmt = (salesTaxNetPrice * taxPct) / 100;

                    invLineItem.setNetPrice(NumberUtil.roundHalfUp(netPrice, roundingDigits));
                    invLineItem.setTaxAmt(NumberUtil.roundHalfUp(taxAmt, Constants.TAX_VAT_SCALE));
                    invLineItem.setVatAmt(NumberUtil.roundHalfUp(vatAmt, Constants.TAX_VAT_SCALE));
                }
                else {
                    double taxAmt = (netPrice * taxPct) / 100;
                    double vatAmt = (netPrice * vatPct) / 100;
                    invLineItem.setNetPrice(NumberUtil.roundHalfUp(netPrice, roundingDigits));
                    invLineItem.setTaxAmt(NumberUtil.roundHalfUp(taxAmt, Constants.TAX_VAT_SCALE));
                    invLineItem.setVatAmt(NumberUtil.roundHalfUp(vatAmt, Constants.TAX_VAT_SCALE));
                }
            }
            else {
                double taxAmt = (netPrice * taxPct) / 100;
                double vatAmt = (netPrice * vatPct) / 100;
                invLineItem.setNetPrice(NumberUtil.roundHalfUp(netPrice, roundingDigits));
                invLineItem.setTaxAmt(NumberUtil.roundHalfUp(taxAmt, Constants.TAX_VAT_SCALE));
                invLineItem.setVatAmt(NumberUtil.roundHalfUp(vatAmt, Constants.TAX_VAT_SCALE));
            }

            invLineItem.setTaxPct(taxPct);
            invLineItem.setVatPct(vatPct);
            invLineItem.setTaxCode(taxCode);
            invLineItem.setVatCode(vatCode);
        }
        catch (Exception e) {
            // TODO need to implement proper exception handling
            e.printStackTrace();
        }
        return invLineItem;
    }

    public static CEInvoiceLineItem calculateTaxAndVatCodes(CEJobOrder ceJo, CEJobInvoicePreviewForm invoicePreviewForm, CEInvoiceLineItem invLineItem) {

        Integer roundingDigits = getDecimalDigitsByCurrency(ceJo);
        invLineItem = calculateinvLineItem(invLineItem, invLineItem.getTaxDate(), roundingDigits, invoicePreviewForm);
        return invLineItem;
    }

}
