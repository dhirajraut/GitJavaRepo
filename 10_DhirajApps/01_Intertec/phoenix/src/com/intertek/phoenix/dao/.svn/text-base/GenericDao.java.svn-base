/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import java.io.Serializable;
import java.util.List;

import com.intertek.pagination.Pagination;

/**
 * 
 * @author richard.qin
 */
public class GenericDao<T> extends AbstractDao<T> {
    // the actual implementation of the Dao interface, can be hibernate, CSV
    // or in-memory dao implementation.
    private AbstractDao<T> delegate;
    
    // The only protected constructor means that this class cannot be instantiated
    // by users of this class. 
    protected GenericDao(AbstractDao<T> realDao){
        super(realDao.getEntityClass());
        this.delegate = realDao;
    }
    
    /**
     * Get the delegate dao. This method is useful for DAO implementations.
     * @return
     */
    protected Dao<T> getDelegate(){
        return delegate;
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#count(java.lang.Object)
     */
    @Override
    public int count(T example) throws DaoException{
        return delegate.count(example);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#count(com.intertek.phoenix.dao.QueryInfo)
     */
    @Override
    public int count(QueryInfo query) throws DaoException{
        return delegate.count(query);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#find(java.io.Serializable)
     */
    @Override
    public <ID extends Serializable> T find(ID id) throws DaoException{
        return delegate.find(id);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#find(long)
     */
    @Override
    public T find(long id) throws DaoException{
        return delegate.find(id);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#find(java.util.List)
     */
    @Override
    public <ID extends Serializable> List<T> find(List<ID> ids) throws DaoException{
        return delegate.find(ids);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#find(ID[])
     */
    @Override
    public <ID extends Serializable> List<T> find(ID... ids) throws DaoException{
        return delegate.find(ids);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#find(long[])
     */
    @Override
    public List<T> find(long... ids) throws DaoException{
        return delegate.find(ids);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#findAll()
     */
    @Override
    public List<T> findAll() throws DaoException{
        return delegate.findAll();
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#flush()
     */
    @Override
    public void flush() throws DaoException{
        delegate.flush();
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#getEntityClass()
     */
    @Override
    public Class<T> getEntityClass() {
        return delegate.getEntityClass();
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#refresh(java.lang.Object)
     */
    @Override
    public T refresh(T entity) throws DaoException{
        return delegate.refresh(entity);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#remove(java.lang.Object)
     */
    @Override
    public boolean remove(T entity) throws DaoException{
        return delegate.remove(entity);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#remove(java.util.List)
     */
    @Override
    public void remove(List<T> entities) throws DaoException{
        delegate.remove(entities);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#remove(T[])
     */
    @Override
    public void remove(T... entities) throws DaoException{
        delegate.remove(entities);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeById(java.io.Serializable)
     */
    @Override
    public <ID extends Serializable> boolean removeById(ID id) throws DaoException{
        return delegate.removeById(id);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeById(long)
     */
    @Override
    public boolean removeById(long id) throws DaoException{
        return delegate.removeById(id);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeByIds(java.util.List)
     */
    @Override
    public <ID extends Serializable> void removeByIds(List<ID> ids) throws DaoException{
        delegate.removeByIds(ids);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeByIds(ID[])
     */
    @Override
    public <ID extends Serializable> void removeByIds(ID... ids) throws DaoException{
        delegate.removeByIds(ids);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeByIds(long[])
     */
    @Override
    public void removeByIds(long... ids) throws DaoException{
        delegate.removeByIds(ids);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#saveOrUpdate(java.lang.Object)
     */
    @Override
    public T saveOrUpdate(T entity) throws DaoException{
        if(entity == null){
            throw new DaoException("Invalid entity: null");
        }
        return delegate.saveOrUpdate(entity);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#saveOrUpdate(java.util.List)
     */
    @Override
    public List<T> saveOrUpdate(List<T> entities) throws DaoException{
        return delegate.saveOrUpdate(entities);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#saveOrUpdate(T[])
     */
    @Override
    public List<T> saveOrUpdate(T... entities) throws DaoException{
        return delegate.saveOrUpdate(entities);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#search(java.lang.Object)
     */
    @Override
    public List<T> search(T example) throws DaoException{
        if(example == null){
            throw new DaoException("Invalid example entity: null");
        }
        return delegate.search(example);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#search(com.intertek.phoenix.dao.QueryInfo)
     */
    @Override
    public List<T> search(QueryInfo query) throws DaoException{
        return delegate.search(query);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#searchUnique(java.lang.Object)
     */
    @Override
    public T searchUnique(T example) throws DaoException{
        if(example == null){
            throw new DaoException("Invalid example entity: null");
        }
        return delegate.searchUnique(example);
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#searchUnique(com.intertek.phoenix.dao.QueryInfo)
     */
    @Override
    public T searchUnique(QueryInfo query) throws DaoException{
        return delegate.searchUnique(query);
    }

    /* (non-Javadoc)
     * @see com.intertek.phoenix.dao.AbstractDao#execute(java.lang.Object)
     */
    @Override
    public int execute(String statement, Object[] parameters) throws DaoException {
        return delegate.execute(statement, parameters);
    }

    /* (non-Javadoc)
     * @see com.intertek.phoenix.dao.AbstractDao#query(java.lang.Object)
     */
    @Override
    public List<Object[]> query(String name, Object statement, Pagination pagination, SortInfo sort) throws DaoException{
        return delegate.query(name, statement, pagination, sort);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List searchByNamedQuery(String fnName, Object[] parameters, Pagination pagination) throws DaoException {
        return delegate.searchByNamedQuery(fnName, parameters, pagination);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List searchBySql(String sqlString, Object[] parameters, Pagination pagination) throws DaoException {
        return delegate.searchBySql(sqlString, parameters, pagination);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List searchByNamedQuery(String fnName, Object[] parameters) throws DaoException {
        return delegate.searchByNamedQuery(fnName, parameters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List searchBySql(String sqlString, Object[] parameters) throws DaoException {
        return delegate.searchBySql(sqlString, parameters);
    }

    @Override
    public String getIdentifierPropertyName() {
        return delegate.getIdentifierPropertyName();
    }

    /**
     * The returned entity is attached to the session. The original entity remains detached.
     * @param detachedEntity
     * @return merged entity
     * @throws DaoException
     */
    @Override
    public T merge(T detachedEntity) throws DaoException {
        return delegate.merge(detachedEntity);
    }
}
