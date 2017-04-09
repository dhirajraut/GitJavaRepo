/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.esb;

import com.intertek.entity.JobOrder;
import com.intertek.phoenix.BaseService;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.Project;

/**
 * ESB Related services
 * 
 * @author Eric.Nguyen
 */
public interface ESBService extends BaseService{

    /**
     * Communicate with the ESB to create the project with the given jobOrder
     * <p>
     * 
     * @param jobOrder
     * @return
     */
    public Project createOrUpdateProject(CEJobOrder jobOrder) throws PhoenixException;
    public Project createOrUpdateProject(JobOrder jobOrder) throws PhoenixException;

    public Project createProjectType1(CEJobOrder jobOrder) throws PhoenixException;
    
    public <T> T saveOrUpdate(Class<T> entityType, T obj) throws DaoException;

    public Object sendExtObj(Object obj) throws PhoenixException;

    public int executeQuery(Class<?> entityType, String query, Object[] params) throws DaoException;

    // public <T extends Collectable> boolean publish(int numInPage,
    // DataCollector<T> collector) throws PhoenixException;
}
