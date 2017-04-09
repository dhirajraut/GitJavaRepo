/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 */
package com.intertek.phoenix.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.util.CommonUtil;

/**
 * QueryInfo support a simplified construction for expressing query intents. A
 * QueryInfo object consists of one or more FilterInfo objects, which are always
 * ANDed together. A QueryInfo object can also have zero or more sibling
 * QueryInfo objects, each of which is another set of filters. All sibling
 * QueryInfo objects are ORed to produce the final query construction.
 * <p/>
 * QueryInfo supports join with zero or more other QueryInfos, with supported
 * join types INNER and LEFT.
 * <p/>
 * For more complicated query construction, a custom DAO<EntityClass> should be
 * defined with special methods dedicated to the complex queries. This situation
 * can be found in reporting or advanced search queries.
 *
 * @author richard.qin
 */
public class QueryInfo {
    public enum JoinType {
        INNER, LEFT, RIGHT
    }

    // the class of the entity to be searched
    private Class<?> targetCls;
    // type determines how FilterInfos and sibling QueryInfos are grouped
    // by default, all filters and sibling queries are ANDed together
    private LogicOp logicOp = LogicOp.AND;
    // all filters in this set are AND'ed or OR'ed together depends on the query type
    private List<FilterInfo> filters;
    // using List<QueryInfo> allows more complicated queries to be constructed.
    // the target class of the siblings are ignored
    private List<QueryInfo> siblings = new ArrayList<QueryInfo>();

    // joins
    // this is only used in joins
    private String targetFieldName;
    // different types of joins are kept in separate lists.
    private List<QueryInfo> innerJoins = new ArrayList<QueryInfo>();
    private List<QueryInfo> leftJoins = new ArrayList<QueryInfo>();

    private SortInfo sortBy = null;
    private Pagination pagination = null;

    /**
     * A factory method that creates a blank QueryInfo.
     * <p/>
     * The QueryInfo created by calling this factory method
     * will return all the objects, while the QueryInfo created
     * by calling a constructor will return nothing. Of course addFilter
     * is always supported, and after calling addFilter(), the factory
     * constructed QueryInfo is just like the one created using one of
     * the constructors.
     *
     * @param cls
     * @return
     */
    public static QueryInfo newQueryInfo(Class<?> cls) {
        QueryInfo query = new QueryInfo();
        query.targetCls = cls;

        return query;
    }

    public static QueryInfo newQueryInfo(Class<?> cls, LogicOp logicOp) {
        QueryInfo query = new QueryInfo();
        query.targetCls = cls;
        query.logicOp = logicOp;
        return query;
    }

    private QueryInfo() {
    }

    public QueryInfo(Class<?> targetCls) {
        this.filters = new ArrayList<FilterInfo>();
        this.targetCls = targetCls;
    }

    public QueryInfo(Class<?> targetCls, LogicOp op) {
        this.logicOp = op;
        this.filters = new ArrayList<FilterInfo>();
        this.targetCls = targetCls;
    }

    // these constructors not really adding value
//    public QueryInfo(Class<?> targetCls, FilterInfo filter) {
//        this(targetCls);
//        this.addFilter(filter);
//    }
//
//    public QueryInfo(Class<?> targetCls, String name, Object value, FilterOp op) {
//        this(targetCls);
//        this.addFilter(name, value, op);
//    }
//
//    public QueryInfo(Class<?> targetCls, String name, Object value) {
//        this(targetCls);
//        this.addFilter(name, value);
//    }

    /**
     * Add a new filter to be ANDed / ORed, depends on the logic op.
     *
     * @param filter
     * @return
     */
    public QueryInfo addFilter(FilterInfo filter) {
        if (filter != null) {
            if (this.filters == null) {
                this.filters = new ArrayList<FilterInfo>();
            }
            filters.add(filter);
        }
        return this;
    }

    /**
     * Create a new filter and add it to this QueryInfo
     *
     * @param name
     * @param value
     * @return
     */
    public QueryInfo addFilter(String name, Object value) {
        if (this.filters == null) {
            this.filters = new ArrayList<FilterInfo>();
        }
        filters.add(new FilterInfo(name, value));
        return this;
    }

    public QueryInfo addFilter(String name, Object value, boolean ignoreCase) {
        if (this.filters == null) {
            this.filters = new ArrayList<FilterInfo>();
        }
        filters.add(new FilterInfo(name, value, ignoreCase));
        return this;
    }

    /**
     * Create a new filter with Operator and add it to this QueryInfo
     *
     * @param name
     * @param value
     * @return
     */
    public QueryInfo addFilter(String name, Object value, FilterOp op) {
        if (this.filters == null) {
            this.filters = new ArrayList<FilterInfo>();
        }
        filters.add(new FilterInfo(name, op, value));
        return this;
    }

    public QueryInfo addFilter(String name, Object value, FilterOp op, boolean ignoreCase) {
        if (this.filters == null) {
            this.filters = new ArrayList<FilterInfo>();
        }
        filters.add(new FilterInfo(name, op, value, ignoreCase));
        return this;
    }

    /**
     * Create a ternary operator Filter.
     *
     * @param name
     * @param name2
     * @param value
     * @param op
     * @return
     */
    public QueryInfo addFilter(String name, String name2, Object value, FilterOp op) {
        if (this.filters == null) {
            this.filters = new ArrayList<FilterInfo>();
        }
        filters.add(new FilterInfo(name, name2, op, value));
        return this;
    }

    public QueryInfo addFilter(String name, String name2, Object value, FilterOp op, boolean ignoreCase) {
        if (this.filters == null) {
            this.filters = new ArrayList<FilterInfo>();
        }
        filters.add(new FilterInfo(name, name2, op, value, ignoreCase));
        return this;
    }

    /**
     * Add an empty Or query.
     *
     * @return
     */
    public QueryInfo addOrQuery() {
        QueryInfo sibling = new QueryInfo(null);
        sibling.logicOp = LogicOp.OR;
        siblings.add(sibling);
        return sibling;
    }

    /**
     * Add an empty And query
     *
     * @return
     */
    public QueryInfo addAndQuery() {
        QueryInfo sibling = new QueryInfo(null);
        siblings.add(sibling);
        return sibling;
    }

    /**
     * Add a joined query
     *
     * @return
     * @throws DaoException
     */
    public QueryInfo addJoin(String joinField) throws DaoException {
        return addJoin(joinField, JoinType.INNER);
    }

    /**
     * Add a joined query
     *
     * @return
     * @throws DaoException
     */
    public QueryInfo addLeftJoin(String joinField) throws DaoException {
        return addJoin(joinField, JoinType.LEFT);
    }

    /**
     * Add a joined query
     *
     * @param joinType Type of join, INNER or LEFT
     * @return
     * @throws DaoException
     */
    public QueryInfo addJoin(String joinFieldName, JoinType joinType) throws DaoException {
        Class<?> joinCls = null;
        try {
            Field joinField = CommonUtil.getDeclaredField(this.targetCls, joinFieldName);
            joinCls = joinField.getType();
        }
        catch (NoSuchFieldException e) {
            throw new DaoException("Invalid join field: " + joinFieldName);
        }

        QueryInfo joinedQueryInfo = new QueryInfo(joinCls);
        joinedQueryInfo.setTargetFieldName(joinFieldName);
        switch (joinType) {
            case INNER:
                this.innerJoins.add(joinedQueryInfo);
                break;
            case LEFT:
            default: // default to left join
                this.leftJoins.add(joinedQueryInfo);
                break;
        }
        return joinedQueryInfo;
    }

    /**
     * Find the joined QueryInfo for a given entity type
     *
     * @return
     */
    public QueryInfo getJoinedQueryInfo(String joinFieldName) {
        for (QueryInfo joined : this.leftJoins) {
            if (joinFieldName.equals(joined.targetFieldName)) {
                return joined;
            }
        }
        for (QueryInfo joined : this.innerJoins) {
            if (joinFieldName.equals(joined.targetFieldName)) {
                return joined;
            }
        }
        return null;
    }

    public List<QueryInfo> getInnerJoinedQueryInfo() {
        return this.innerJoins;
    }

    public List<QueryInfo> getLeftJoinedQueryInfo() {
        return this.leftJoins;
    }

    public Class<?> getTargetCls() {
        return targetCls;
    }

    public List<FilterInfo> getFilters() {
        return filters;
    }

    public List<QueryInfo> getSiblings() {
        return siblings;
    }

    public String getTargetFieldName() {
        return targetFieldName;
    }

    public void setTargetFieldName(String targetFieldName) {
        this.targetFieldName = targetFieldName;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public LogicOp getLogicOp() {
        return logicOp;
    }

    public void setType(LogicOp logicOp) {
        this.logicOp = logicOp;
    }

    public SortInfo getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortInfo sortBys) {
        this.sortBy = sortBys;
    }

    public void addSortInfo(SortInfo si) {
        if (sortBy == null) {
            sortBy = si;
        } else {
            sortBy.addSortInfo(si);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FETCH ");
        return toString(sb, 0);
    }

    private String toString(StringBuilder sb, int ind) {
        if (targetCls != null) {
            sb.append(targetCls.getSimpleName().toLowerCase());
        }
        if (this.filters != null && this.filters.size() > 0) {
            if (targetCls != null) {
                sb.append(" WHERE ");
            }
            for (int k = 0; k < this.filters.size(); k++) {
                FilterInfo filter = filters.get(k);
                sb.append(filter.toString());
                if (k < this.filters.size() - 1) {
                    sb.append(" ").append(this.logicOp).append(" ");
                }
            }
        }
        // do the siblings
        for (QueryInfo queryInfo : this.siblings) {
            sb.append("\n").append(indent(ind + 1))
                    .append(" ").append(this.logicOp).append(" ");
            queryInfo.toString(sb, ind + 1);
        }

        // do the joins
        for (QueryInfo queryInfo : this.innerJoins) {
            sb.append("\n").append(indent(ind)).append(" JOIN ");
            queryInfo.toString(sb, ind + 1);
        }
        for (QueryInfo queryInfo : this.leftJoins) {
            sb.append("\n").append(indent(ind)).append(" LEFT JOIN ");
            queryInfo.toString(sb, ind + 1);
        }
        if (this.sortBy != null && this.sortBy.getSortBys().size() > 0) {
            sb.append("\n").append(indent(ind + 1)).append(" SORT BY ");
            sb.append(sortBy.toString());
        }
        return sb.toString();
    }

    private String indent(int size) {
        String[] indentations = new String[]{
                "    ",
                "        ",
                "            ",
                "                ",
                "                    ",
                "                        ",
                "                            "
        };
        return indentations[size];
    }

    public static QueryInfo buildQuery(Class<?> targetCls, Map<String, ? extends Object> input) throws PhoenixException {
        QueryInfo query = new QueryInfo(targetCls);
        return query.buildQuery(input);
    }

    public static QueryInfo buildQuery(Class<?> targetCls, String[] names, String[] values, FilterOp[] ops) throws PhoenixException {
        QueryInfo query = new QueryInfo(targetCls);
        return query.buildQuery(names, values, ops);
    }

    public QueryInfo buildQuery(Map<String, ? extends Object> input) throws PhoenixException {
        for (String line : input.keySet()) {
            String[] parts = line.split("\\.");
            Object value = input.get(line);
            try {
                buildQuery(this.targetCls, this, parts, FilterOp.EQUAL, value, 0); // 0 means start from the beginning
            }
            catch (Exception e) {
                throw new PhoenixException("Invalid query string: " + line);
            }
        }
        return this;
    }

    public QueryInfo buildQuery(String[] names, String[] values, FilterOp[] ops) throws DaoException {
        for (int k = 0; k < names.length; k++) {
            String[] parts = names[k].split("\\.");
            buildQuery(this.targetCls, this, parts, ops[k], values[k], 0); // 0 means start from the beginning
        }
        return this;
    }

    private void buildQuery(Class<?> hostcls, QueryInfo query, String[] parts, FilterOp op, Object value, int pos) 
            throws DaoException {
        if (pos < parts.length - 1) {
            String part = parts[pos];
            QueryInfo joined = query.getJoinedQueryInfo(part);
            if (joined == null) {
                joined = query.addJoin(part, JoinType.LEFT);
            }
            buildQuery(joined.targetCls, joined, parts, op, value, pos + 1);
        } 
        else if (pos == parts.length - 1) {
            try {
                Object val = getTypedValue(hostcls, parts[pos], (String) value);
                FilterInfo fi = new FilterInfo(parts[pos], op, val, true);
                query.addFilter(fi);
            }
            catch (NoSuchFieldException e) {
                throw new DaoException("Cannot build query", e);
            }
        } 
        else {
            // just indicate that there is an error, let the upstream to
            // formulate a more meaningful exception. This is a quick way 
            // to exit the recursive function.
            throw new DaoException("Cannot build query");
        }
    }

    private Object getTypedValue(Class<?> hostcls, String fieldName, String value) throws NoSuchFieldException {
        Field field = CommonUtil.getDeclaredField(hostcls, fieldName);
        Class<?> fieldType = field.getType();
        return CommonUtil.stringToObject(fieldType, value);
    }

    public QueryInfo setLogicOp(LogicOp op) {
        this.logicOp = op;
        return this;
    }

    /**
     * Set a filter. If a filter with the given name already exists, its value
     * will be updated. Otherwise, a new FilterInfo will be added.
     * @param string
     * @param string2
     */
    public void setFilter(String name, Object value) {
        for(FilterInfo filter: this.filters){
            if(filter.getName().equals(name)){
                filter.setValue(value);
                return;
            }
        }
        this.addFilter(name, value);
    }

}
