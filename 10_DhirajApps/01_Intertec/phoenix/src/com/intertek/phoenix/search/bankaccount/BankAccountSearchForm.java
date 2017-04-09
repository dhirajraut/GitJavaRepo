/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.bankaccount;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.BankAccount;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.search.BankSearchInfo;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.search.SearchableCriteria;
import com.intertek.phoenix.search.SpecificSearch;
import com.intertek.phoenix.search.bankaccount.BankAccountRow;

/**
 * @author patni
 * 
 */
public class BankAccountSearchForm extends SearchForm implements SpecificSearch {
    
    private String buName;
    private String currency;
    private String bankCode;
    private String bankAcctCode;
    private String description;

    public BankAccountSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/bankaccount.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<BankAccount> searchResult = (List<BankAccount>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (BankAccount ba : searchResult) {
            myRows.add(new BankAccountRow(ba));
        }
        return myRows;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAcctCode() {
        return bankAcctCode;
    }

    public void setBankAcctCode(String bankAcctCode) {
        this.bankAcctCode = bankAcctCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("bankAccountId.bankCode", "bankCode", "contains"));
        list.add(new SearchableCriteria("bankAccountId.bankAcctCode", "bankAcctCode", "contains"));
        list.add(new SearchableCriteria("bankAcctDesc", "description", "contains"));
        return list;
    }

    public List<?> buildResult(Pagination pagination, SortInfo sortInfo) throws PhoenixException {
        SearchService searchService = ServiceManager.getSearchService();
        //buName, currency, bank_code, bankAcctCode, bankDescription
        BankSearchInfo searchInfo = new BankSearchInfo();
        searchInfo.setBuName(buName);
        searchInfo.setCurrency(currency);
        searchInfo.setBankCode(bankCode);
        searchInfo.setBankAcctCode(bankAcctCode);
        searchInfo.setBankDesc(description);
        
        return searchService.getBankAccount(searchInfo, pagination, sortInfo);    
    }

    public Class<?> getEntityType() {
        return BankAccount.class;
    }

    public Row getHeader() {
        return new BankAccountRow();
    }
}
