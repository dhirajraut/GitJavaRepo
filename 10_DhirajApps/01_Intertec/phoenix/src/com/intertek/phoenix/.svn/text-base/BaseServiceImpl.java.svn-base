/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix;

import java.io.Serializable;
import java.util.List;

import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;

/**
 * BaseService provice some shared functionality for all the other
 * service components, such as find by entity id, and query by example.
 * 
 * @author richard.qin
 */
public abstract class BaseServiceImpl implements BaseService {
    /**
     * @see com.intertek.phoenix.BaseService#findById(java.lang.Class, ID)
     */
    public <T, ID extends Serializable> T findById(Class<T> cls, ID id) throws DaoException{
        Dao<T> dao = DaoManager.getDao(cls);
        return dao.find(id);
    }
    
    /**
     * @see com.intertek.phoenix.BaseService#findById(java.lang.Class, long)
     */
    public <T> T findById(Class<T> cls, long id) throws DaoException{
        Dao<T> dao = DaoManager.getDao(cls);
        return dao.find(id);
    }
    
    /**
     * @see com.intertek.phoenix.BaseService#queryByExample(T)
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> queryByExample(T example) throws DaoException{
        Dao<T> dao = DaoManager.getDao((Class<T>)example.getClass());
        return dao.search(example);
    }
}
