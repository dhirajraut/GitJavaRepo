package com.intertek.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.intertek.pagination.Pagination;

/**
 * The data access object used to access the database.
 * 
 * It provides all the interface needed to access the database.
 * 
 * It is used normally by the service objects which are used by the UI
 * controllers etc.
 * 
 **/

public interface Dao {
	/**
	 * Save the entity to database.
	 * 
	 * @param instance
	 *            - the entity to be saved to database
	 * @return - the saved entity
	 **/
	<T> T save(T instance);

	/**
	 * Update the entity to database.
	 * 
	 * @param instance
	 *            - the entity to be updated to database
	 **/
	void update(Object instance);

	/**
	 * Remove the entity from database.
	 * 
	 * @param instance
	 *            - the entity to be removed from database
	 **/
	void remove(Object instance);

	/**
	 * Remove the entity from database by Class type and id.
	 * 
	 * @param type
	 *            - the class type of the entity to be removed from database
	 * @param id
	 *            - the id of the entity to be removed from database
	 **/
	void remove(Class type, long id);

	/**
	 * Merge the detached entity to database.
	 * 
	 * @param detachedInstance
	 *            - the detached entity to be merged to database
	 * @return - the merged entity
	 **/
	<T> T merge(T detachedInstance);

	/**
	 * Get entity from database by class type and id.
	 * 
	 * @param type
	 *            - the class type of the entity
	 * @param id
	 *            - the id of the entity
	 * @return - the entity from database
	 **/
	<T> T getById(Class<T> type, long id);

	/**
	 * Get all the entities by class type.
	 * 
	 * @param type
	 *            - the class type of the entities
	 * @return - a list of entities from database by the class type.
	 **/
	List getAll(Class type);

	/**
	 * Find a list of entities by query string and values.
	 * 
	 * @param queryString
	 *            - the query string
	 * @param values
	 *            - an array of objects to be used by the query string.
	 * @return - a list of entities from database by the query string and
	 *         values.
	 **/
	List find(String queryString, Object[] values);

	List findNamedParams(String queryString, final Map<String, Object> values);

	/**
	 * Find a paginated list of entities by query string and values.
	 * 
	 * @param queryString
	 *            - the query string
	 * @param values
	 *            - an array of objects to be used by the query string.
	 * @param pagination
	 *            - the pagination object to store the page information.
	 * @return - a paginated list of entities from database by the query string
	 *         and values.
	 **/
	List find(final String queryString, final Object[] values, final Pagination pagination);

	List findNamedParams(final String queryString, final Map<String, Object> values, final Pagination pagination);

	/**
	 * Bulk Update the database by query string and values.
	 * 
	 * @param queryString
	 *            - the query string
	 * @param values
	 *            - an array of objects to be used by the query string.
	 * @return - number of records updated.
	 **/
	int bulkUpdate(String queryString, Object[] values);

	/**
	 * Find a list of entities by the named sql query and values.
	 * 
	 * @param sqlQueryName
	 *            - the name of the named sql query.
	 * @param values
	 *            - an array of objects to be used by the named sql query.
	 * @return - a list of entities from database by the named sql query and
	 *         values.
	 **/
	List findByNamedSqlQuery(final String sqlQueryName, final Object[] values);

	/**
	 * Find a paginated list of entities by the named sql query and values.
	 * 
	 * @param sqlQueryName
	 *            - the name of the named sql query.
	 * @param values
	 *            - an array of objects to be used by the named sql query.
	 * @param pagination
	 *            - the pagination object to store the page information.
	 * @return - a paginated list of entities from database by the named sql
	 *         query and values.
	 **/
	List findByNamedSqlQuery(final String sqlQueryName, final Object[] values, final Pagination pagination);

	/**
	 * Find a list of entities by the sql query and values.
	 * 
	 * @param sql
	 *            - the sql query.
	 * @param values
	 *            - an array of objects to be used by the sql query.
	 * @return - a list of entities from database by the sql query and values.
	 **/
	List findBySQL(final String sql, final Object[] values);

	/**
	 * Find a paginated list of entities by the sql query and values.
	 * 
	 * @param sql
	 *            - the sql query.
	 * @param values
	 *            - an array of objects to be used by the sql query.
	 * @param pagination
	 *            - the pagination object to store the page information.
	 * @return - a paginated list of entities from database by the sql query and
	 *         values.
	 **/
	List findBySQL(final String sql, final Object[] values, final Pagination pagination);

	List findBySQLNamedParams(final String sql, final Map<String, Object> values);

	List findBySQLNamedParams(final String sql, final Map<String, Object> values, final Pagination pagination);

	/**
	 * Execute update by the sql and values.
	 * 
	 * @param sql
	 *            - the sql.
	 * @param values
	 *            - an array of objects to be used by the sql.
	 * @return - number of records updated.
	 **/
	Integer executeUpdateSql(final String sql, final Object[] values);

	/**
	 * Flush the session.
	 * 
	 **/
	void flush();

	/**
	 * Get the hibernate session.
	 * 
	 * @return - the hibernate session.
	 **/
	Session openHibernateSession();

	/**
	 * Closed the hibernate session.
	 * 
	 * @param sess
	 *            - the hibernate session to be closed.
	 **/
	void closeHibernateSession(Session sess);

	Object uniqueResult(final String queryString, final Object[] values);

}
