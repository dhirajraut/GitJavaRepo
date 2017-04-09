package com.intertek.phoenix.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientObjectException;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.intertek.dao.ILikeExpression;
import com.intertek.dao.LikeExpression;
import com.intertek.pagination.Pagination;

/**
 * Created by IntelliJ IDEA.
 * User: jin.wang
 * Date: 2:47:42 PM Jun 11, 2009
 */
public class HibernateDao<T> extends AbstractDao<T> {
    private static Pattern fromPattern = Pattern.compile(".*? (?i)from ");
    private static Pattern fetchPattern = Pattern.compile(" (?i)fetch ");
    private static Pattern sortPattern = Pattern.compile (" (?i)order by .*?$");

    private SessionFactory sessionFactory;

    public HibernateDao() {
        super();
    }

    protected HibernateDao(Class<T> cls) {
        super(cls);
    }

    public void setSesseionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <ID extends Serializable> T find(ID id) throws DaoException {
        return (T) getSession().load(getEntityClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <ID extends Serializable> List<T> find(List<ID> ids) throws DaoException {
        String idPropertyName = this.sessionFactory.getClassMetadata(getEntityClass()).getIdentifierPropertyName();
        List<T> resultList = (List<T>) getSession().createCriteria(getEntityClass()).add(Restrictions.in(idPropertyName, ids)).list();
        return resultList;
    }
    
    @Override
    public List<T> find(long... ids) throws DaoException {
        List<Long> idList = convertPrimitiveArrayToList(ids);
        return find(idList);
    }

    private List<Long> convertPrimitiveArrayToList(long... ids) {
        List<Long> idList = new ArrayList<Long>(ids.length);
        for (long id : ids)
            idList.add(id);
        return idList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() throws DaoException {
        return new ArrayList<T>(getSession().createCriteria(getEntityClass()).list());
    }

    @Override
    public T saveOrUpdate(T entity) throws DaoException {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     * The returned entity is attached to the session. The original entity remains detached.
     * @param entity
     * @return merged entity
     * @throws DaoException
     */
    @Override
    public T merge(T entity) throws DaoException {
        return (T)getSession().merge(entity);
    }

    @Override
    public List<T> saveOrUpdate(List<T> entities) throws DaoException {
        Session session = getSession();
        for (T entity : entities) {
            session.saveOrUpdate(entity);
        }
        return entities;
    }

    @Override
    //TODO: remove returen value of this method, should throw exception when the entity is not persistent
    public boolean remove(T entity) throws DaoException {
        try {
            getSession().delete(entity);
            return true;
        } catch (TransientObjectException e) {
            return false;
        }
    }

    @Override
    public void remove(List<T> entities) throws DaoException {
        Session session = getSession();
        for (T entity : entities) {
            session.delete(entity);
        }
    }

    @Override
    //TODO: remove boolean as return value
    public <ID extends Serializable> boolean removeById(ID id) throws DaoException {
        try {
            getSession().delete(find(id));
            return true;
        } catch (TransientObjectException e) {
            return false;
        }
    }

    @Override
    //TODO: remove return value of the method
    public boolean removeById(long id) throws DaoException {
        return removeById(new Long(id));
    }

    @Override
    /** The implementation assumes that the list is not big or most of ids are already in session,
     *  otherwise a delete hql should be constructed for better performance.
     **/
    public <ID extends Serializable> void removeByIds(List<ID> ids) throws DaoException {
        Session session = getSession();
        for (ID id : ids) {
            session.delete(find(id));
        }
    }

    @Override
    public void removeByIds(long... ids) throws DaoException {
        removeByIds(convertPrimitiveArrayToList(ids));
    }

    @Override
    public List<T> search(T exampleObj) throws DaoException {
        // Hibernate QBE does not perform as expected, as a workaround, 
        // the sample entity is converted to QueryInfo, until a proper
        // solution is found
        return search(DaoUtil.exampleToQuery(exampleObj));

        //Example example = Example.create(exampleObj);
        //example.excludeZeroes();
        //return getSession().createCriteria(getEntityClass()).add(example).list();
    }

    @Override
    public T searchUnique(T exampleObj) throws DaoException {
        return searchUnique(DaoUtil.exampleToQuery(exampleObj));
        //Example example = Example.create(exampleObj);
        //return (T) getSession().createCriteria(getEntityClass()).add(example).uniqueResult();
    }

    private Criteria convertQueryInfoToCriteria(QueryInfo query, boolean sort) throws DaoException {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        populateCriteria(criteria, query);
        if (sort){
            addSortInfoToCriteria(criteria, query);
        }
        return criteria;
    }
    
    private Criteria convertQueryInfoToCriteria(QueryInfo query) throws DaoException {
        return convertQueryInfoToCriteria(query, true);
    }

    private void populateCriteria(Criteria criteria, QueryInfo query) throws DaoException {
        Junction junction = createJunction(query);
        criteria.add(junction);

        List<QueryInfo> leftJoins = query.getLeftJoinedQueryInfo();
        if (leftJoins.size() > 0) {
            for (QueryInfo queryInfo : leftJoins) {
                Criteria leftCriteria = criteria.createCriteria(queryInfo.getTargetFieldName(), CriteriaSpecification.LEFT_JOIN);
                // not sure whether nested join is supported by Hibernate in criteria api, try it anyway.
                // does not seem supported - http://opensource.atlassian.com/projects/hibernate/browse/EJB-380
                populateCriteria(leftCriteria, queryInfo);
            }
        }
        List<QueryInfo> innerJoins = query.getInnerJoinedQueryInfo();
        if (innerJoins.size() > 0) {
            for (QueryInfo queryInfo : innerJoins) {
                Criteria innerCriteria = criteria.createCriteria(queryInfo.getTargetFieldName(), CriteriaSpecification.INNER_JOIN);
                populateCriteria(innerCriteria, queryInfo);
            }
        }
    }

    private Junction createJunction(QueryInfo query) throws DaoException {
        Junction junction = null;
        List<FilterInfo> filters = query.getFilters();

        LogicOp logicOp = query.getLogicOp();
        switch (logicOp) {
            case OR:
                junction = Restrictions.disjunction();
                break;
            case AND:
                junction = Restrictions.conjunction();
                break;
        }

        if (filters != null)
            populateJunction(junction, filters);

        List<QueryInfo> queryInfos = query.getSiblings();
        if (queryInfos.size() > 0) {
            // disregard the join info in the queryInfo since this queryInfo list is only intended to store OR conditions recursively
            for (QueryInfo queryInfo : queryInfos)
                junction.add(createJunction(queryInfo));
        }
        return junction;
    }

    private void populateJunction(Junction junction, List<FilterInfo> filters) throws DaoException {
        for (FilterInfo filter : filters) {
            FilterOp filterOp = filter.getOp();
            switch (filterOp) {
                case EMPTY:
                    junction.add(Restrictions.isEmpty(filter.getName()));
                    break;
                case EQUAL:
                    // TODO check ignore case
                    boolean ignoreCase = filter.isIgnoreCase();
                    if (ignoreCase){
                        junction.add(Restrictions.eq(filter.getName(), filter.getValue()).ignoreCase());
                    }else{
                        junction.add(Restrictions.eq(filter.getName(), filter.getValue()));
                    }
                    break;
                case GREATER_OR_EQUAL:
                    junction.add(Restrictions.ge(filter.getName(), filter.getValue()));
                    break;
                case GREATER_THAN:
                    junction.add(Restrictions.gt(filter.getName(), filter.getValue()));
                    break;
                case IN:
                    Criterion criterion = createInCriterion(filter);
                    junction.add(criterion);
                    break;
                case IS_NOT_NULL:
                    junction.add(Restrictions.isNotNull(filter.getName()));
                    break;
                case IS_NULL:
                    junction.add(Restrictions.isNull(filter.getName()));
                    break;
                case LESS_OR_EQUAL:
                    junction.add(Restrictions.le(filter.getName(), filter.getValue()));
                    break;
                case LESS_THAN:
                    junction.add(Restrictions.lt(filter.getName(), filter.getValue()));
                    break;
                case LIKE:
                    // TODO check ignore case
                    ignoreCase = filter.isIgnoreCase();
                    if (ignoreCase){
                        //junction.add(Restrictions.ilike(filter.getName(), (String) filter.getValue(), MatchMode.ANYWHERE));
                        junction.add(new ILikeExpression(filter.getName(), (String) filter.getValue(), MatchMode.ANYWHERE));
                    }else{
                        //junction.add(Restrictions.like(filter.getName(), filter.getValue()));
                        junction.add(new LikeExpression(filter.getName(), filter.getValue()));
                    }
                        
                    break;
                case NOT_EMPTY:
                    junction.add(Restrictions.isNotEmpty(filter.getName()));
                    break;
                case NOT_EQUAL:
                    junction.add(Restrictions.ne(filter.getName(), filter.getValue()));
                    break;
                case NOT_IN:
                    junction.add(Restrictions.not(createInCriterion(filter)));
                    break;
                case BETWEEN:
                    Conjunction conjunction = Restrictions.conjunction();
                    conjunction.add(Restrictions.le(filter.getName(), filter.getValue()));
                    conjunction.add(Restrictions.ge(filter.getName2(), filter.getValue()));
                    junction.add(conjunction);
                    break;
            }
        }
    }

    private Criterion createInCriterion(FilterInfo filter) throws DaoException {
        Object value = filter.getValue();
        Criterion criterion;
        if (value instanceof Collection)
            criterion = Restrictions.in(filter.getName(), (Collection<?>) filter.getValue());
        else if (value instanceof Object[]) {
            Criterion in = Restrictions.in(filter.getName(), (Object[]) filter.getValue());
            criterion = in;
        }
        else
            //TODO this check should occur at FilterInfo level - a INFilterInfo subclass should be created...
            throw new DaoException("Invalid value for IN filter");
        return criterion;
    }

    private void addSortInfoToCriteria(Criteria criteria, QueryInfo query) {
        SortInfo sortInfo = query.getSortBy();
        if (sortInfo != null) {
            addSortInfoToCriteria(criteria, sortInfo);
            List<SortInfo> sortBys = sortInfo.getSortBys();
            if (sortBys != null) {
                for (SortInfo sortInfoObj : sortBys) {
                    addSortInfoToCriteria(criteria, sortInfoObj);
                }
            }
        }
    }

    private void addSortInfoToCriteria(Criteria criteria, SortInfo sortInfo) {
        if (sortInfo != null) {
            if (sortInfo.isAscending())
                criteria.addOrder(Order.asc(sortInfo.getFieldName()));
            else
                criteria.addOrder(Order.desc(sortInfo.getFieldName()));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T searchUnique(QueryInfo query) throws DaoException {
        Criteria criteria = convertQueryInfoToCriteria(query);
        return (T) criteria.uniqueResult();
    }

    @Override
    // This assumes size of the returned list of objects is small, it will load the whole list in order to find the size.
    // A separate count query should be constructed to find out the list size in most cases.
    public List<T> search(QueryInfo query) throws DaoException {
        Pagination pagination = query.getPagination();
        if (pagination == null)
            return searchByQueryInfoInternal(query);
        if (pagination.getTotalRecord() == Pagination.UNDEFINED)
            pagination.setTotalRecord(count(query));
        if (pagination.getTotalRecord() > 0) {
            return searchByQueryInfoInternal(query);
        } else
            return new ArrayList<T>();
    }

    @SuppressWarnings("unchecked")
    private List<T> searchByQueryInfoInternal(QueryInfo query) throws DaoException {
        Criteria criteria = convertQueryInfoToCriteria(query);
        addPaginationToCriteria(criteria, query.getPagination());
        return criteria.list();
    }

    private int countByQueryInfoInternal(QueryInfo query) throws DaoException {
        Criteria criteria = convertQueryInfoToCriteria(query, false);
        criteria.setProjection(Projections.rowCount());
        return ((Integer) criteria.uniqueResult()).intValue();
    }

    private void addPaginationToCriteria(Criteria criteria, Pagination pagination) {
        if (pagination != null) {
            criteria.setFirstResult(getFirstRecordLocation(pagination));
            criteria.setMaxResults(pagination.getNumInPage());
        }
    }

    private int getFirstRecordLocation(Pagination pagination) {
        int firstRecord = (pagination.getCurrentPageNum() - 1) * pagination.getNumInPage();
        return firstRecord;
    }

    @Override
    // not sure about the value of this method due to the limitation of QBE we offered here. Could be slow if T has many children eager loaded.
    public int count(T example) throws DaoException {
        List<T> list = search(example);
        return list.size();
    }

    @Override
    public int count(QueryInfo query) throws DaoException {
        return countByQueryInfoInternal(query);
    }

    @Override
    public T refresh(T entity) throws DaoException {
        getSession().refresh(entity);
        return entity;
    }

    @Override
    public void flush() throws DaoException {
        getSession().flush();
    }

    @Override
    public int execute(String statement, Object[] parameters) throws DaoException {
        Query query = getSession().createQuery(statement);
        addParameters(parameters, query);
        return query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    //TODO: parameter values? not sure about the purpose of this method. should be removed.
    protected List<Object[]> query(String name, Object statement, Pagination pagination, SortInfo sortInfo) throws DaoException {
        getRecordCount((String) statement, null, pagination);
        Query query = getSession().createQuery((String) statement);
        handlePaginationForQuery(query, pagination);

        if (sortInfo != null) {
            // the current sortInfo do not have alias, this would not work for a query as two entities may have an identical property name.
            //not sure why sortInfo is not in the HQL statement.
        }
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List searchBySql(String sqlString, Object[] parameters, Pagination pagination) throws DaoException {
        getRecordCount(sqlString, parameters, pagination);
        Query query = createQuery(sqlString, parameters);
        handlePaginationForQuery(query, pagination);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    private void getRecordCount(String sqlString, Object[] parameters, Pagination pagination) {
        if (pagination != null && pagination.getTotalRecord() == Pagination.UNDEFINED) {
            Query query = createQuery(constructCountQueryString(sqlString), parameters);
            List countResult = query.list();
            Long count = (Long) countResult.get(0);
            pagination.setTotalRecord(count.intValue());
        }
    }

    private String constructCountQueryString(String sqlString) {
        String resultString = fromPattern.matcher(sqlString).replaceFirst("select count(*) from ");
        resultString = fetchPattern.matcher(resultString).replaceAll(" ");
        resultString = sortPattern.matcher(resultString).replaceAll(" ");
        return resultString;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List searchByNamedQuery(String fnName, Object[] parameters, Pagination pagination) throws DaoException {
        // TODO support pagination
        return namedQueryForListWithHql(fnName, parameters, pagination);
    }

    private void handlePaginationForQuery(Query query, Pagination pagination) {
        if (pagination != null) {
            if (pagination.getTotalRecord() == Pagination.UNDEFINED) {
                List<?> totalList = query.list();
                pagination.setTotalRecord(totalList.size());
            }
            query.setFirstResult(getFirstRecordLocation(pagination));
            query.setMaxResults(pagination.getNumInPage());
        }
    }

    @SuppressWarnings("unchecked")
    private List namedQueryForListWithHql(String fnName, Object[] parameters, Pagination pagination) {
        List result = null;
        try {
        Query query = createNamedQuery(fnName, parameters);
        //handlePaginationForQuery(query, pagination);
        result = query.list();
        result = query.list();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    private Query createQuery(String queryString, Object[] parameters) {
        Query query = getSession().createQuery(queryString);
        addParameters(parameters, query);
        return query;
    }


    private Query createNamedQuery(String fnName, Object[] parameters) {
        Query query = getSession().getNamedQuery(fnName);
        addParameters(parameters, query);
        return query;
    }

    private void addParameters(Object[] parameters, Query query) {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i, parameters[i]);
            }
        }
    }

    @Override
    public String getIdentifierPropertyName() {
        return this.sessionFactory.getClassMetadata(getEntityClass()).getIdentifierPropertyName();
    }
}
