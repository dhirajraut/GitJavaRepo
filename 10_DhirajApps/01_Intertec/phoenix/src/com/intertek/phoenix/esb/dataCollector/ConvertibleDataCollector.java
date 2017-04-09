package com.intertek.phoenix.esb.dataCollector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.LogicOp;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.entity.Collectable;
import com.intertek.phoenix.esb.ESBService;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.search.SearchableCriteria;
import com.intertek.phoenix.util.CommonSearchUtil;
import com.intertek.util.Constants;

/**
 * 
 * @author Eric.Nguyen
 */
public abstract class ConvertibleDataCollector<T1 extends Collectable, T2> implements DataCollector<T1> {
    private SearchService searchService;
    private ESBService esbService;
    
    public abstract T2 convert(T1 obj);

    @Override
    public List<T1> getObjects(int numInPage) throws PhoenixException {
        Class<T1> entityType = getEntityType();
        QueryInfo queryInfo = null;
        try {
            queryInfo = buildQueryInfo(entityType, numInPage);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new PhoenixException(e);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new PhoenixException(e);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new PhoenixException(e);
        }

        List<T1> list = null;
        try {
            list = getSearchService().search(entityType, queryInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    protected QueryInfo buildQueryInfo(Class<T1> entityType, int numInPage) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        int totalRecord = Pagination.UNDEFINED;
        int currentPageNum = 1;
        int pagesToDisplay = 1;
        Pagination pagination = new Pagination(totalRecord, numInPage, currentPageNum, pagesToDisplay);

        List<SearchCriteria> criterias = CommonSearchUtil.buildCriteria(this, getSearchableCriteria());

        QueryInfo queryInfo = new QueryInfo(entityType, LogicOp.OR);
        queryInfo.setPagination(pagination);
        for (SearchCriteria sc : criterias) {
            queryInfo.addFilter(sc.getName(), sc.getValue(), sc.getFilterOp());
        }
        return queryInfo;
    }

    public List<SearchableCriteria> getSearchableCriteria() {
        List<SearchableCriteria> list = new ArrayList<SearchableCriteria>();
        list.add(new SearchableCriteria("newFlag", Constants.NEW, "=", false));
        list.add(new SearchableCriteria("updateFlag", Constants.NEW, "=", false));
        return list;
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    public ESBService getEsbService() {
        return esbService;
    }

    public void setEsbService(ESBService esbService) {
        this.esbService = esbService;
    }
}
