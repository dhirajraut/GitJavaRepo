/**
 * 
 */
package com.intertek.phoenix.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.intertek.pagination.Pagination;
import com.intertek.phoenix.search.QueryStruct;
import com.intertek.phoenix.search.Search;
import com.intertek.service.AdminServiceImpl;

/**
 * This class implements the AdminService interface.
 * It is designed to override the existing implementation following a better
 * design and in order to produce a cleaner code for maintenance.
 * The strategy is to gradually and selectively re-implement the methods
 * in the old impl class.
 * 
 * @author richard.qin
 */
public class AdminServiceImpl2 extends AdminServiceImpl {
    private static final Logger log = Logger.getLogger(AdminServiceImpl2.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public void searchAdminObject(Search search){
        if (search == null){
            return;
        }
        
        // construct the search query string based on RoleSearch
        QueryStruct query = search.constructQuery();
        
        // prepare pagenation
        Pagination pagination = preparePagination(search, query);
       
        // execute search
        log.debug(query.getSearchSql());
        List<?> results = getDao().find(query.getSearchSql(), query.getParamsAsArray(), pagination);
        
        search.setResults(results);
        search.setPagination(pagination);
    }
    
    /**
     * A simple method that perpare pagination for the current search result
     * @param search
     * @param query
     * @return
     */
    private Pagination preparePagination(Search<?> search, QueryStruct query){
        Pagination pagination = search.getPagination();
        if (pagination != null && pagination.getTotalRecord() > 0) {
            List<?> counts = getDao().find(query.getCountSql(), query.getParamsAsArray());

            if (counts.size() > 0) {
                Number count = (Number) counts.get(0);
                pagination.setTotalRecord(count.intValue());
            }
            
            pagination.calculatePages();
        }
        return pagination;
    }
}
