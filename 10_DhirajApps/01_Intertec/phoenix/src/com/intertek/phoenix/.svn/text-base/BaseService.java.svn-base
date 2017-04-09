/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix;

import java.io.Serializable;
import java.util.List;

import com.intertek.phoenix.dao.DaoException;

/**
 * BaseService provice some shared functionality for all the other
 * service components, such as find by entity id, and query by example.
 * 
 * @author richard.qin
 */
public interface BaseService {

    /**
     * Find an entity instance by ID.
     * <p> 
     * @param <ID> the parameterized id for an entity class, must extends from Serializable
     * @param cls
     * @param id
     * @return
     * @throws DaoException
     */
    public abstract <T, ID extends Serializable> T findById(Class<T> cls, ID id) throws DaoException;

    /**
     * Find an entity instance by its numeric id.
     * @param cls
     * @param id the numeric id for an entity object.
     * @return
     * @throws DaoException
     */
    public abstract <T> T findById(Class<T> cls, long id) throws DaoException;

    /**
     * Query By Example for common service components.
     * 
     * @param example
     * @return
     * @throws DaoException
     */
    public abstract <T> List<T> queryByExample(T example) throws DaoException;

}
