package com.intertek.phoenix.search.contractcust;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContractCust;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;

public class ContractCustSearchForm extends SearchForm {
    private String customerCode;
    private String customerName;
    private String contractCode;
    private String callerRowIndex;

    public ContractCustSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/parentcontract.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<ContractCust> searchResult = (List<ContractCust>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (ContractCust cc : searchResult) {
            ContractCustSearchRow newRow = new ContractCustSearchRow(cc);
            newRow.setCallerRowIndex(callerRowIndex);
            myRows.add(newRow);
        }
        return myRows;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getCallerRowIndex() {
        return callerRowIndex;
    }

    public void setCallerRowIndex(String callerRowIndex) {
        this.callerRowIndex = callerRowIndex;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("customer.custCode", "customerCode", "contains"));
        list.add(new SearchableCriteria("customer.name", "customerName", "contains"));
        list.add(new SearchableCriteria("contract.contractCode", "contractCode", "contains"));
        return list;
    }

    public Class<?> getEntityType() {
        return ContractCust.class;
    }

    public Row getHeader() {
        return new ContractCustSearchRow();
    }

}
