/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple expression for sorting.
 * <p> This class can be enhanced to support multiple sorting fields
 * 
 * @author richard.qin
 */
public class SortInfo {
    private String fieldName;
    private boolean ascending;
    
    private List<SortInfo> sortBys;
    
    public SortInfo(){
        addSortInfo(this);
    }
    
    public SortInfo(String fieldName, boolean ascending){
        this.fieldName = fieldName;
        this.ascending = ascending;
        addSortInfo(this);
    }

     public SortInfo(String sortField) {
        this.fieldName = sortField;
        this.ascending = false;
        addSortInfo(this);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public List<SortInfo> getSortBys() {
        if (sortBys == null)
            sortBys = new ArrayList<SortInfo>();
        return sortBys;
    }

    public void setSortBys(List<SortInfo> sortBys) {
        this.sortBys = sortBys;
    }
    
    public void addSortInfo(SortInfo sortInfo) {
        getSortBys().add(sortInfo);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getSortBys().size(); i++) {
            SortInfo sortBy = sortBys.get(i);
            if (sortBy.getFieldName() == null)
                continue;
            sb.append(sortBy.getFieldName() + (sortBy.isAscending() ? " ASC" : " DESC"));
            if ( i < sortBys.size() - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
    
    

    
}
