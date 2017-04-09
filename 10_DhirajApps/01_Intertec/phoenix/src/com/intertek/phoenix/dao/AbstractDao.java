/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.intertek.pagination.Pagination;
import com.intertek.phoenix.util.CollectionUtil;

/**
 * AbstractDao implements some convenient methods that do not depend on the
 * underlying dao technology. It also introduces some important method execute()
 * and executeBulk() etc that are not available in the Dao interface. The reason
 * that such methods are defined here is to prevent direct invocation from the
 * user code. These methods are useful for derived Dao class to execute
 * arbitrarily complex queries and bulk update/delete operations.
 * 
 * @author richard.qin
 */
public abstract class AbstractDao<T> implements Dao<T> {
    // a cached entity class definition
    private Class<T> entityClass;

    protected AbstractDao(Class<T> cls) {
        this.entityClass = cls;
    }

    protected AbstractDao(){

    }

    public void setEntityClass(Class<T> cls) {
       this.entityClass = cls;
    }
    
    /**
     * @see com.intertek.phoenix.dao.Dao#getEntityClass()
     */
    @SuppressWarnings("unchecked")

    public Class<T> getEntityClass() {
        if (entityClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            entityClass = (Class<T>) thisType.getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public <ID extends Serializable> List<T> find(ID... ids) throws DaoException{
        return find(CollectionUtil.arrayToList(ids));
    }


    public List<T> saveOrUpdate(T... entities) throws DaoException {
        return saveOrUpdate(CollectionUtil.arrayToList(entities));
    }


    public void remove(T... entities) throws DaoException{
        remove(CollectionUtil.arrayToList(entities));
    }


    public <ID extends Serializable> void removeByIds(ID... ids) throws DaoException{
        removeByIds(CollectionUtil.arrayToList(ids));
    }

    /**
     * Excecute an arbitary query
     */
    abstract protected List<Object[]> query(String name, Object statement, Pagination pagination, SortInfo s) throws DaoException;


    @SuppressWarnings("unchecked")
    public List searchBySql(String sqlString, Object[] parameters) throws DaoException{
        return searchBySql(sqlString, parameters, null);
    }


    @SuppressWarnings("unchecked")
    public List searchByNamedQuery(String fnName, Object[] parameters) throws DaoException{
        return searchByNamedQuery(fnName, parameters, null);
    }

    public T find(long id) throws DaoException {
        // make id a Long, and implicit id
        return find(new Long(id));
    }
}
