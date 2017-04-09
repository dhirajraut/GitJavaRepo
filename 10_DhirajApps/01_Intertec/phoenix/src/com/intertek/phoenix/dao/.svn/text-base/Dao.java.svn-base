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
 * A generic DAO interface which defines basic CRUD functionalities plus
 * extended search functions. Implementations of this interface can utilize
 * Hibernate, in-memory objects, xml files, csv files, remote objects, or other
 * alternative data stores. This interface does not have any notion of sql
 * statements. This Dao interface can be used to CRUD any entity objects. On the
 * other hand, a specific DAO implementation for a given entity class can be
 * extended from it. One good reason to extend this GenericDao interface for a
 * specific entity class is to implement advanced search criteria for that
 * entity object, where either sql statements or Criteria can be used.
 * 
 * @author richard.qin
 */
public interface Dao<T> {
    /**
     * Returns the persistent entity class the DAO works with.
     * 
     * @return
     */
    public Class<T> getEntityClass();

    /**
     * Load a persistent instance
     * 
     * @param <T>
     * @param type
     * @param id
     * @return
     */
    public <ID extends Serializable> T find(ID id) throws DaoException;

    public T find(long id) throws DaoException;

    /**
     * Load persistent instances of given ids
     * 
     * @param <T>
     * @param type
     * @param ids
     * @return
     */
    public <ID extends Serializable> List<T> find(List<ID> ids) throws DaoException;

    public <ID extends Serializable> List<T> find(ID... ids) throws DaoException;

    public List<T> find(long... ids) throws DaoException;

    /**
     * Load all persistent instances of a given entity type
     * 
     * @param <T>
     * @param type
     * @return
     */
    public List<T> findAll() throws DaoException;

    /**
     * Save or update an entity object
     * 
     * @param <T>
     * @param entity
     * @return
     */
    public T saveOrUpdate(T entity) throws DaoException;

    /**
     * Save or update a list of entity objects
     * 
     * @param <T>
     * @param entities
     * @return
     */
    public List<T> saveOrUpdate(List<T> entities) throws DaoException;

    public List<T> saveOrUpdate(T... entities) throws DaoException;

    public T merge(T entity) throws DaoException;
    
    /**
     * Delete a persistent entity object from the database
     * 
     * @param <T>
     * @param entity
     * @return
     */
    public boolean remove(T entity) throws DaoException;

    /**
     * Delete a list of entity objects from the database
     * 
     * @param <T>
     * @param entities
     */
    public void remove(List<T> entities) throws DaoException;

    public void remove(T... entities) throws DaoException;

    /**
     * Delete a persistent entity object based on ID
     * 
     * @param <T>
     * @param type
     * @param id
     * @return
     */
    public <ID extends Serializable> boolean removeById(ID id) throws DaoException;

    public boolean removeById(long id) throws DaoException;

    /**
     * Delete persistent entity objects based on a list of ids
     * 
     * @param <T>
     * @param type
     * @param ids
     */
    public <ID extends Serializable> void removeByIds(List<ID> ids) throws DaoException;

    public <ID extends Serializable> void removeByIds(ID... ids) throws DaoException;

    public void removeByIds(long... ids) throws DaoException;

    /**
     * Search using query by example. QBE is somewhat different from Hibernate's
     * feature. Here, an example entity is an arbitary object, all it's non-null
     * fields will be used as filters (ANDed) to build a query construct.
     * 
     * @param <T>
     * @param example
     * @return A list of persistent entity objects
     */
    public List<T> search(T example) throws DaoException;

    /**
     * Search a single object using query by example
     * 
     * @param <T>
     * @param example
     * @return
     */
    public T searchUnique(T example) throws DaoException;

    /**
     * Search based on more general criteria
     * 
     * @param query
     * @return
     */
    public List<T> search(QueryInfo query) throws DaoException;

    /**
     * Search a single object based on more general criteria
     * 
     * @param <T>
     * @param query
     * @return
     */
    public T searchUnique(QueryInfo query) throws DaoException;

    /**
     * Count the number of objects that meet the criteria
     * 
     * @param query
     * @return
     */
    public int count(T example) throws DaoException;

    public int count(QueryInfo query) throws DaoException;

    /**
     * Refreshes a persistent or a detached instance by synchronizing its state
     * with the database
     */
    public T refresh(T entity) throws DaoException;

    public void flush() throws DaoException;

    /**
     * Load objects by executing a query string, using positional arguments.
     * What the list contains is based on the query. It could be a list of entities or list of object arrays.
     */
    @SuppressWarnings("unchecked")
    List searchBySql(String sqlString, Object[] parameters, Pagination pagination) throws DaoException;
    @SuppressWarnings("unchecked")
    List searchBySql(String sqlString, Object[] parameters) throws DaoException;

    /**
     * Load objects by executing a named query, using positional arguments.
     * What the list contains is based on the query. It could be a list of entities or list of object arrays.
     */
    @SuppressWarnings("unchecked")
    List searchByNamedQuery(String fnName, Object[] parameters, Pagination pagination) throws DaoException;
    @SuppressWarnings("unchecked")
    List searchByNamedQuery(String fnName, Object[] parameters) throws DaoException;

    /**
     * execute update with parameters
     * @param updateString
     * @param parameters
     * @throws DaoException
     */
    int execute(String updateString, Object[] parameters) throws DaoException;
    String getIdentifierPropertyName();
}
