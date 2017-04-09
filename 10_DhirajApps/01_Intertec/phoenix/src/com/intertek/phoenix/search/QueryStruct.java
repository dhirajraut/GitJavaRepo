package com.intertek.phoenix.search;

import java.util.List;

/**
 * This class provide as simple construct for building search query strings. There are two basic
 * query constructs, one for the search query, the other for the result count query. Each query
 * is broken down to three parts: the query, the criteria and the arguments.
 * 
 * As implied by its name, this class is more like a C struct, which contains little or no behavior
 * at all.
 * 
 * @author richard.qin
 */
public class QueryStruct {
    /* the search query string */
    private String sql;
    /* the query to returned the result count */
    private String sqlCount;
    /* the where clause */
    private String criteria;
    /* the argument values for the search criteria */
    private List<String> params;
    
    public QueryStruct(String sql, String sqlCount, String criteria, List<String> params){
        this.sql = sql;
        this.sqlCount = sqlCount;
        this.criteria = criteria;
        this.params = params;
    }

    /**
     *  Get the sql statement for record counting
     * @return
     */
    public String getCountSql() {
        return this.sqlCount + this.criteria;
    }

    /**
     *  Get the sql statement for search
     * @return
     */
    public String getSearchSql() {
        return this.sql + this.criteria;
    }
    
    /**
     * Get query parameters as an array
     * @return
     */
    public Object[] getParamsAsArray(){
        return this.params.toArray();
    }

    /**
     * @return the sql statement
     */
    public String getSql() {
        return sql;
    }

    /**
     * @param sql the sql to set
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * @return the sql statement used to count the number of record of the given query
     */
    public String getSqlCount() {
        return sqlCount;
    }

    /**
     * @param sqlCount the sqlCount to set
     */
    public void setSqlCount(String sqlCount) {
        this.sqlCount = sqlCount;
    }

    /**
     * @return the where clause for the query
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * @return the a list of parameters
     */
    public List<String> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(List<String> params) {
        this.params = params;
    }
}
