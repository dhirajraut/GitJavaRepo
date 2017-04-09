/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import java.io.Serializable;
import java.util.List;

/**
 * A generic DAO interface which defines basic CRUD functionalities plus
 * extended search functions. Implementation of this interface can utilize
 * Hibernate, in-memory objects, xml files, csv files, remote objects, or other
 * alternatives. This interface does not have any notion of sql statements. This
 * Dao interface can be used to CRUD any entity objects, or a specific DAO
 * implementation for a given entity class can be created (extended) from it.
 * One good reason to extend this GenericDao interface for a specific entity
 * class is to implement advanced search criteria for a given entity object,
 * where either sql statements or Criteria can be used.
 * 
 * @author richard.qin
 */
public interface Dao2 {

    /**
     * Load a persistent instance
     * 
     * @param <T>
     * @param type
     * @param id
     * @return
     */
    public <T, ID extends Serializable > T find(Class<T> type, ID id);
    public <T> T find(Class<T> type, long id);

    /**
     * Load persistent instances of given ids
     * 
     * @param <T>
     * @param type
     * @param ids
     * @return
     */
    public <T, ID extends Serializable > List<T> find(Class<T> type, List<ID> ids);
    public <T, ID extends Serializable > List<T> find(Class<T> type, ID... ids);
    public <T> List<T> find(Class<T> type, long... ids);
    /**
     * Load all persistent instances of a given entity type
     * 
     * @param <T>
     * @param type
     * @return
     */
    public <T> List<T> findAll(Class<T> type);

    /**
     * Save or update an entity object
     * 
     * @param <T>
     * @param entity
     * @return
     */
    public <T> boolean saveOrUpdate(T entity);

    /**
     * Save or update a list of entity objects
     * 
     * @param <T>
     * @param entities
     * @return
     */
    public <T> List<Boolean> saveOrUpdate(List<T> entities);
    public <T> List<Boolean> saveOrUpdate(T... entities);

    /**
     * Delete a persistent entity object from the database
     * 
     * @param <T>
     * @param entity
     * @return
     */
    public <T> boolean remove(T entity);

    /**
     * Delete a list of entity objects from the database
     * 
     * @param <T>
     * @param entities
     */
    public <T> void remove(List<T> entities);
    public <T> void remove(T... entities);

    /**
     * Delete a persistent entity object based on ID
     * 
     * @param <T>
     * @param type
     * @param id
     * @return
     */
    public <T, ID extends Serializable > boolean removeById(Class<T> type, ID id);
    public <T> boolean removeById(Class<T> type, long id);

    /**
     * Delete persistent entity objects based on a list of ids
     * 
     * @param <T>
     * @param type
     * @param ids
     */
    public <T, ID extends Serializable > void removeByIds(Class<T> type, List<ID> ids);
    public <T, ID extends Serializable > void removeByIds(Class<T> type, ID... ids);
    public <T> void removeByIds(Class<T> type, long... ids);

    /**
     * Search using query by example
     * 
     * @param <T>
     * @param example
     * @return A list of persistent entity objects
     */
    public <T> List<T> search(T example);

    /**
     * Search a single object using query by example
     * 
     * @param <T>
     * @param example
     * @return
     */
    public <T> T searchUnique(T example);

    /**
     * Count the number of objects that meet the criteria
     * @param condition
     * @return
     */
    public <T> int count(T example);
    public int count(QueryInfo condition);
    
    /**
     * Search based on more general criteria
     * @param condition
     * @return
     */
    public <T> List<T> search(QueryInfo condition);
    
    /**
     * Search a single object based on more general criteria
     * @param <T>
     * @param condition
     * @return
     */
    public <T> T searchUnique(QueryInfo condition);
    

    /**
     * Refreshes a persistent or a detached instance by synchronizing its state
     * with the database
     */
    public <T> void refresh(Class<T> entity);

    public void flush();

}
