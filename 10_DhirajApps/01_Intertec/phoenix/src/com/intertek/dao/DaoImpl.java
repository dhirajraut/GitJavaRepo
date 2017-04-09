package com.intertek.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.intertek.pagination.Pagination;

/**
 * An implementation of data access object used to access the database. It uses
 * the hibernate managed by SpringFramework to access database.
 * 
 * It provides all the interface needed to access the database.
 * 
 * It is used normally by the service objects which are used by the UI
 * controllers etc.
 * 
 **/

public class DaoImpl extends HibernateDaoSupport implements Dao {
	/**
	 * Save the entity to database.
	 * 
	 * @param instance
	 *            - the entity to be saved to database
	 * @return - the saved entity
	 **/
	public <T> T save(T instance){
		return (T) getHibernateTemplate().merge(instance);
	}

	/**
	 * Update the entity to database.
	 * 
	 * @param instance
	 *            - the entity to be updated to database
	 **/
	public void update(Object instance){
		// merge first to avoid NonUniqueObjectException
		getHibernateTemplate().update(getHibernateTemplate().merge(instance));
	}

	/**
	 * Remove the entity from database.
	 * 
	 * @param instance
	 *            - the entity to be removed from database
	 **/
	public void remove(Object instance){
		// merge first to avoid NonUniqueObjectException
		getHibernateTemplate().delete(getHibernateTemplate().merge(instance));
	}

	/**
	 * Remove the entity from database by Class type and id.
	 * 
	 * @param type
	 *            - the class type of the entity to be removed from database
	 * @param id
	 *            - the id of the entity to be removed from database
	 **/
	public void remove(Class type, long id){
		getHibernateTemplate().delete(getById(type, id));
	}

	/**
	 * Merge the detached entity to database.
	 * 
	 * @param detachedInstance
	 *            - the detached entity to be merged to database
	 * @return - the merged entity
	 **/
	public <T> T merge(T detachedInstance){
		return (T) getHibernateTemplate().merge(detachedInstance);
	}

	/**
	 * Get entity from database by class type and id.
	 * 
	 * @param type
	 *            - the class type of the entity
	 * @param id
	 *            - the id of the entity
	 * @return - the entity from database
	 **/
	public <T> T getById(Class<T> type, long id){
		return (T) getHibernateTemplate().load(type, id);
	}

	/**
	 * Get all the entities by class type.
	 * 
	 * @param type
	 *            - the class type of the entities
	 * @return - a list of entities from database by the class type.
	 **/
	public List getAll(Class type){
		return getHibernateTemplate().loadAll(type);
	}

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
	public List find(String queryString, Object[] values){
		return getHibernateTemplate().find(queryString, values);
	}

	public List findNamedParams(String queryString, final Map<String, Object> values){
		return findNamedParams(queryString, values, null);
	}

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
	public List find(final String queryString, final Object[] values, final Pagination pagination){
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException{
				Query query = session.createQuery(queryString);
				if(pagination != null){
					query.setFirstResult((pagination.getCurrentPageNum() - 1) * pagination.getNumInPage());
					query.setMaxResults(pagination.getNumInPage());
				}

				int paramSize = values != null ? values.length : 0;
				for(int i = 0; i < paramSize; i++){
					query.setParameter(i, values[i]);
				}

				return query.list();
			}
		});
	}

	public List findNamedParams(final String queryString, final Map<String, Object> values, final Pagination pagination){
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException{
				Query query = session.createQuery(queryString);
				if(pagination != null){
					query.setFirstResult((pagination.getCurrentPageNum() - 1) * pagination.getNumInPage());
					query.setMaxResults(pagination.getNumInPage());
				}

				Iterator<String> itr = values.keySet().iterator();
				while (itr.hasNext()){
					String name = itr.next();
					Object value = values.get(name);
					if(Collection.class.isAssignableFrom(value.getClass())){
						query.setParameterList(name, (Collection) value);
					}
					else{
						query.setParameter(name, value);
					}
				}

				return query.list();
			}
		});
	}

	public Object uniqueResult(final String queryString, final Object[] values){
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException{
				Query query = session.createQuery(queryString);

				int paramSize = values != null ? values.length : 0;
				for(int i = 0; i < paramSize; i++){
					query.setParameter(i, values[i]);
				}

				return query.uniqueResult();
			}
		});
	}

	/**
	 * Bulk Update the database by query string and values.
	 * 
	 * @param queryString
	 *            - the query string
	 * @param values
	 *            - an array of objects to be used by the query string.
	 * @return - number of records updated.
	 **/
	public int bulkUpdate(String queryString, Object[] values){
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

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
	public List findByNamedSqlQuery(final String sqlQueryName, final Object[] values){
		return findByNamedSqlQuery(sqlQueryName, values, null);
	}

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
	public List findByNamedSqlQuery(final String sqlQueryName, final Object[] values, final Pagination pagination){
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException{
				Query query = session.getNamedQuery(sqlQueryName);
				if(pagination != null){
					query.setFirstResult((pagination.getCurrentPageNum() - 1) * pagination.getNumInPage());
					query.setMaxResults(pagination.getNumInPage());
				}

				int paramSize = values != null ? values.length : 0;
				for(int i = 0; i < paramSize; i++){
					query.setParameter(i, values[i]);
				}

				return query.list();
			}
		});
	}

	/**
	 * Find a list of entities by the sql query and values.
	 * 
	 * @param sql
	 *            - the sql query.
	 * @param values
	 *            - an array of objects to be used by the sql query.
	 * @return - a list of entities from database by the sql query and values.
	 **/
	public List findBySQL(final String sql, final Object[] values){
		return findBySQL(sql, values, null);
	}

	public List findBySQLNamedParams(final String sql, final Map<String, Object> values){
		return findBySQLNamedParams(sql, values, null);
	}

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
	public List findBySQL(final String sql, final Object[] values, final Pagination pagination){
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException{
				Query query = session.createSQLQuery(sql);
				if(pagination != null){
					query.setFirstResult((pagination.getCurrentPageNum() - 1) * pagination.getNumInPage());
					query.setMaxResults(pagination.getNumInPage());
				}

				int paramSize = values != null ? values.length : 0;
				for(int i = 0; i < paramSize; i++){
					query.setParameter(i, values[i]);
				}

				return query.list();
			}
		});
	}

	/**
	 * same as findBySQL but the parameter as assumed using name instead of
	 * index
	 * 
	 * @return - a paginated list of entities from database by the sql query and
	 *         values.
	 **/
	public List findBySQLNamedParams(final String sql, final Map<String, Object> values, final Pagination pagination){
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException{
				Query query = session.createSQLQuery(sql);
				if(pagination != null){
					query.setFirstResult((pagination.getCurrentPageNum() - 1) * pagination.getNumInPage());
					query.setMaxResults(pagination.getNumInPage());
				}

				Iterator<String> itr = values.keySet().iterator();
				while (itr.hasNext()){
					String name = itr.next();
					Object value = values.get(name);
					if(Collection.class.isAssignableFrom(value.getClass())){
						query.setParameterList(name, (Collection) value);
					}
					else{
						query.setParameter(name, value);
					}
				}

				return query.list();
			}
		});
	}

	/**
	 * Execute update by the sql and values.
	 * 
	 * @param sql
	 *            - the sql.
	 * @param values
	 *            - an array of objects to be used by the sql.
	 * @return - number of records updated.
	 **/
	public Integer executeUpdateSql(final String sql, final Object[] values){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException{
				Query query = session.createSQLQuery(sql);

				int paramSize = values != null ? values.length : 0;
				for(int i = 0; i < paramSize; i++){
					query.setParameter(i, values[i]);
				}

				return new Integer(query.executeUpdate());
			}
		});
	}

	/**
	 * Flush the session.
	 * 
	 **/
	public void flush(){
		getHibernateTemplate().flush();
	}

	/**
	 * Get the hibernate session.
	 * 
	 * @return - the hibernate session.
	 **/
	public Session openHibernateSession(){
		return this.getSession();
	}

	/**
	 * Closed the hibernate session.
	 * 
	 * @param sess
	 *            - the hibernate session to be closed.
	 **/
	public void closeHibernateSession(Session sess){
		this.releaseSession(sess);
	}
}
