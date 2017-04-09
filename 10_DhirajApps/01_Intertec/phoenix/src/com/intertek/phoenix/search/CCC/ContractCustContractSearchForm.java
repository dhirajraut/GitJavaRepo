/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search.CCC;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContractCustContact;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.search.SearchableCriteria;
import com.intertek.phoenix.search.SpecificSearch;

/**
 * This class is for the ContractCustomerContact popup search form.
 * 
 * @author lily.sun
 */
public class ContractCustContractSearchForm extends SearchForm implements SpecificSearch{

    public ContractCustContractSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/customer_popup.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<ContractCustContact> searchResult = (List<ContractCustContact>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (ContractCustContact ccc : searchResult) {
            myRows.add(new ContractCustContactRow(ccc));
        }
        return myRows;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        return null;
    }
    
    public Class<?> getEntityType() {
        return ContractCustContact.class;
    }

    public Row getHeader() {
        return new ContractCustContactRow();
    }

    /**
     * This methods builds the specific list for the popup to display. 
     * 
     * @throws PhoenixException 
     */
    public List<?> buildResult(Pagination pagination, SortInfo sortInfo) throws PhoenixException {
        SearchService jobOrderService = ServiceManager.getSearchService();
        List<ContractCustContact> entities = jobOrderService.searchContractCustomerContact(super.getSearchValue(), pagination, sortInfo);
        return entities;
    }

}
