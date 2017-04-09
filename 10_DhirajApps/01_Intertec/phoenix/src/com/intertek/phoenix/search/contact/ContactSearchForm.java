package com.intertek.phoenix.search.contact;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.ContractCustContact;
import com.intertek.phoenix.search.Row;
import com.intertek.phoenix.search.SearchForm;
import com.intertek.phoenix.search.SearchableCriteria;
import com.intertek.phoenix.search.contact.ContactRow;

public class ContactSearchForm extends SearchForm {

    private String contactId;
    private String firstName;
    private String lastName;

    public ContactSearchForm() {
    }

    public String getSearchCriteriaPage() {
        return "/WEB-INF/views/phoenix/search/contact.jsp";
    }

    @SuppressWarnings("unchecked")
    public List<Row> getRows() {
        List<ContractCustContact> searchResult = (List<ContractCustContact>) getResult();
        if (searchResult == null) {
            return null;
        }
        List<Row> myRows = new ArrayList<Row>();

        for (ContractCustContact ccc : searchResult) {
            myRows.add(new ContactRow(ccc));
        }
        return myRows;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("contact.id", "contactId", "contains"));
        list.add(new SearchableCriteria("contact.firstName", "firstName", "contains"));
        list.add(new SearchableCriteria("contact.lastName", "lastName", "contains"));
        return list;
    }

    public Class<?> getEntityType() {
        return ContractCustContact.class;
    }

    public Row getHeader() {
        return new ContactRow();
    }
}
