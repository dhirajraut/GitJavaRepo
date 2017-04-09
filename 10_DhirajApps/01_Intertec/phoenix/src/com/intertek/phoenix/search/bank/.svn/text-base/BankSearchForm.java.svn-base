/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.bank;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Bank;
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
import com.intertek.phoenix.search.bank.BankRow;

/**
 * @author patni
 * 
 */
public class BankSearchForm extends SearchForm implements SpecificSearch {

    private String buName;
    private String currency;
    private String bankCode;
    private String description;

    public BankSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/bank.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<Bank> searchResult = (List<Bank>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (Bank bk : searchResult) {
            myRows.add(new BankRow(bk));
        }
        return myRows;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buname) {
        this.buName = buname;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("bankCode", "bankCode", "contains"));
        list.add(new SearchableCriteria("bankDesc", "description", "contains"));

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Bank> buildResult(Pagination pagination, SortInfo sortInfo) throws PhoenixException {
        SearchService searchService = ServiceManager.getSearchService();
        //buName, currency, bank_code, bankDescription 
        BankSearchInfo searchInfo = new BankSearchInfo();
        searchInfo.setBuName(buName);
        searchInfo.setCurrency(currency);
        searchInfo.setBankCode(bankCode);
        searchInfo.setBankDesc(description);
        
        return searchService.getBankCode(searchInfo, pagination, sortInfo);
    }

    public Class<?> getEntityType() {
        return Bank.class;
    }

    public Row getHeader() {
        return new BankRow();
    }
}
