package com.intertek.phoenix.search.contactcust;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContactCust;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.search.ContactCustSearchInfo;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.search.SearchableCriteria;
import com.intertek.phoenix.search.SpecificSearch;

/**
 *
 * @author lily.sun
 */

public class ContactCustomerSearchForm extends SearchForm implements SpecificSearch {

    private String custCode;
    private String customerName;
    private String contactId;
    private String contactName;
    private String callerRowIndex;
    
    
    
    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    public String getCallerRowIndex() {
        return callerRowIndex;
    }
    
    public void setCallerRowIndex(String rowIndex) {
        this.callerRowIndex = rowIndex;
    }

    @Override
    public Class<?> getEntityType() {
        return ContactCust.class;
    }

    @Override
    public Row getHeader() {
        return new ContactCustRow();
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<ContactCust> searchResult = (List<ContactCust>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (ContactCust cc : searchResult) {
            ContactCustRow newRow = new ContactCustRow(cc);
            newRow.setCallerRowIndex(callerRowIndex);
            myRows.add(newRow);
        }
        return myRows;
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/contact_popup.jsp";
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("customer.custCode", "custCode", "contains"));
        list.add(new SearchableCriteria("customer.name", "customerName", "contains"));
        list.add(new SearchableCriteria("contact.id", "contactId", "equals"));
        list.add(new SearchableCriteria("contact.firstName", "contactName", "contains"));
//        list.add(new SearchableCriteria("contact.lastName", "contactName", "contains"));
        return list;
    }

    @Override
    public List<?> buildResult(Pagination pagination, SortInfo sortInfo) throws PhoenixException {
        SearchService jobService = ServiceManager.getSearchService();
        ContactCustSearchInfo searchInfo = new ContactCustSearchInfo(custCode, customerName, contactId, contactName);
        List<ContactCust> results = jobService.searchContactsByCustCode(searchInfo, pagination, sortInfo);
        return results;
    }

}


