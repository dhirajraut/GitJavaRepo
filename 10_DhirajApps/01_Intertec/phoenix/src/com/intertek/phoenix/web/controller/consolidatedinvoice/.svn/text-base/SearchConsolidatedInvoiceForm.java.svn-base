package com.intertek.phoenix.web.controller.consolidatedinvoice;

import java.util.List;

import com.intertek.domain.Search;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.common.ReferenceDataService;

public class SearchConsolidatedInvoiceForm extends Search {
    private SearchCriteria buName;
    private SearchCriteria custCode;
    private SearchCriteria customerName;
    private SearchCriteria invoice;
    private String sortFlag;
    private String cxcel = "false";
    private String pageNo;
    private String sortBy;
    
    
    private final ReferenceDataService refrenceDataService = ServiceManager.getReferenceDataService(); 
    public SearchConsolidatedInvoiceForm() {
        buName = new SearchCriteria();
        custCode= new SearchCriteria();
        customerName = new SearchCriteria();
        invoice = new SearchCriteria();
    }

    public SearchCriteria getBuName() {
        return buName;
    }

    public void setBuName(SearchCriteria buName) {
        this.buName = buName;
    }
    
    
    public SearchCriteria getCustCode() {
        return custCode;
    }

    public void setCustCode(SearchCriteria custCode) {
        this.custCode = custCode;
    }

    public SearchCriteria getCustomerName() {
        return customerName;
    }

    public void setCustomerName(SearchCriteria customerName) {
        this.customerName = customerName;
    }

    public SearchCriteria getInvoice() {
        return invoice;
    }

    public void setInvoice(SearchCriteria invoice) {
        this.invoice = invoice;
    }
    
    public List<FilterOp> getOperaters() {
        return refrenceDataService.getFilterStringOpFields();
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getCxcel() {
        return cxcel;
    }

    public void setCxcel(String cxcel) {
        this.cxcel = cxcel;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
