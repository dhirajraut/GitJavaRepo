/**
 * 
 */
package com.intertek.phoenix.search;

import java.util.List;

import com.intertek.domain.StringSearchField;
import com.intertek.pagination.Pagination;

/**
 *
 * @author richard.qin
 */
abstract public class Search<T> {
    
    private Pagination pagination;
    private List<T> results;
    private List<T> totalResults;

    /**
     * Construct a search query based on the values of the search object
     * @return
     */
    abstract public QueryStruct constructQuery();
    
    /**
     * Extract the result into an array of Strings. It is possible to contain nested array of strings
     * in the final result. This is especially useful when an entity object contains a collection of
     * dependent objects which must also to returned as strings.
     * @param index points the result object in the result set
     * @return An array of strings as an Object array. If there are nested objects, then the result
     * Object array may also contain a nested array of strings, and so on so forth.
     */
    abstract public Object[] getResultAsStrings(int index);
    
    public int getResultSize(){
        if(results != null){
            return results.size();
        }
        return 0;
    }
    
    /**
     * Get the heading for the result set.
     * @return
     */
    abstract public String[] getResultHeaders();
    
    /**
     * Get the filename to be exported
     * @return
     */
    abstract public String getExportFileName();
    
    public String getExportContentType(){
        return "application/download";
    }
    
    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public List<T> getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(List<T> totalResults) {
        this.totalResults = totalResults;
    }
    
    /**
     * A helper method to assist build query parameter value
     * @param val
     * @return
     */
    protected boolean buildQueryParameter(StringBuffer sb, List<String> params, StringSearchField val, 
                                          String paramString, boolean hasWhere){
        String searchField = null;
        if(val != null){
            String value = val.getValue();
            if ((value != null) && value.trim().length() > 0) {
                searchField = '%' + value.toUpperCase() + '%';
                String str = hasWhere ? " and " : " where ";
                sb.append(str);
                sb.append(" upper(")
                  .append(paramString)
                  .append(") like ? ");
                params.add(searchField);
                hasWhere = true;
            }
        }
        return hasWhere;
    }
}
