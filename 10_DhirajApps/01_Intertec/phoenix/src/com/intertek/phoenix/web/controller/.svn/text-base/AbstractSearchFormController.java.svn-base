/**
 * 
 */
package com.intertek.phoenix.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.pagination.Pagination;
import com.intertek.phoenix.search.Search;

/**
 * The abstract base class that for all search forms
 * @author richard.qin
 */
public abstract class AbstractSearchFormController extends SimpleFormController {
    
    protected Pagination doPagination(String numStr){
        int pageNumber = 0;
        try {
            pageNumber = Integer.parseInt(numStr);
        }
        catch (Exception e) {
            pageNumber = 1;
        }
        Pagination pagination = new Pagination(1, 20, pageNumber, 10);
        return pagination;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
     * javax.servlet.http.HttpServletRequest,
     * org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // no impl
    }
}
