/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.search;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.intertek.phoenix.search.CCC.ContractCustContractSearchForm;
import com.intertek.phoenix.search.bank.BankSearchForm;
import com.intertek.phoenix.search.bankaccount.BankAccountSearchForm;
import com.intertek.phoenix.search.businessstream.BusinessStreamSearchForm;
import com.intertek.phoenix.search.businessunit.BuSearchForm;
import com.intertek.phoenix.search.contact.ContactSearchForm;
import com.intertek.phoenix.search.contactcust.ContactCustomerSearchForm;
import com.intertek.phoenix.search.contractcust.ContractCustSearchForm;
import com.intertek.phoenix.search.customer.CustomerSearchForm;
import com.intertek.phoenix.search.employee.EmployeeSearchForm;
import com.intertek.phoenix.search.jobsearch.JobIdSearchForm;
import com.intertek.phoenix.search.po.POSearchForm;
import com.intertek.phoenix.search.servicelocation.ServiceLocationSearchForm;
import com.intertek.phoenix.search.serviceoffering.ServiceOfferingSearchForm;
import com.intertek.phoenix.search.user.UserSearchForm;
import com.intertek.phoenix.search.warehouse.WarehouseSearchForm;

/**
 * 
 * @author Eric Nguyen
 */
public class SearchFormFactory {
    private static final SearchFormFactory me = new SearchFormFactory();

    private SearchFormFactory() {
    }

    public static SearchFormFactory getInstance() {
        return me;
    }

    public SearchForm getSearchForm(HttpServletRequest request) {
        String searchType = request.getParameter("searchType") + "";
        SearchForm form = null;
        if (searchType.equals("warehouse")) {
            form = new WarehouseSearchForm();
        }
        else if (searchType.equals("employee")) {
            form = new EmployeeSearchForm();
        }
        else if (searchType.equals("user")) {
            form = new UserSearchForm();
        }
        else if (searchType.equals("po")) {
            form = new POSearchForm();
        }
        else if (searchType.equals("contractCustContact")) {
            form = new ContractCustContractSearchForm();
        }
        else if (searchType.equals("serviceLocation")) {
            form = new ServiceLocationSearchForm();
        }
        else if (searchType.equals("contactCust")) {
            form = new ContactCustomerSearchForm();
        }
        else if (searchType.equals("bank")) {
            form = new BankSearchForm();
        }
        else if (searchType.equals("bankaccount")) {
            form = new BankAccountSearchForm();
        }
        else if (searchType.equals("serviceoffering")) {
            form = new ServiceOfferingSearchForm();
        }

        else if (searchType.equals("contact")) {
            form = new ContactSearchForm();
        }

        else if (searchType.equals("customer")) {
            form = new CustomerSearchForm();
        }
        else if (searchType.equals("stream")) {
            form = new BusinessStreamSearchForm();
        }
        else if (searchType.equals("job")) {
            form = new JobIdSearchForm();
        }
        else if (searchType.equals("contractcust")) {
            form = new ContractCustSearchForm();
        }
        else if (searchType.equals("bu")) {
            form = new BuSearchForm();
        }
        if (form == null) {
            throw new RuntimeException("Unkown searchType=" + searchType + " no form class found");
        }
        initForm(form, request);
        return form;
    }

    /*
     * initialize the form with setable parameters from the request
     */
    @SuppressWarnings("unchecked")
    private void initForm(SearchForm form, HttpServletRequest request) {
        Enumeration<String> em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = em.nextElement();
            String value = request.getParameter(name);
            try {
                BeanUtils.setProperty(form, name, value);
            }
            catch (Exception e) {
            }
        }
    }
}
