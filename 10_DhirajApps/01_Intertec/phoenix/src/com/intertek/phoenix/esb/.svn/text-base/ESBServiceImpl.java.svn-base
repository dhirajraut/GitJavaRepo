/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.esb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.intertek.entity.JobOrder;
import com.intertek.entity.WebServiceEntity;
import com.intertek.exception.ServiceException;
import com.intertek.phoenix.BaseServiceImpl;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.Project;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.externalEntity.ProjectActivityX;
import com.intertek.phoenix.externalEntity.ProjectContractX;
import com.intertek.phoenix.externalEntity.ProjectX;
import com.intertek.util.Constants;

/**
 * ESB Related services
 * 
 * @author Eric.Nguyen
 */
public class ESBServiceImpl extends BaseServiceImpl implements ESBService {
    protected Map<Class<?>, ESBSender> senders;

    public ESBServiceImpl() {
    }

    @Override
    public Project createOrUpdateProject(JobOrder jobOrder) throws PhoenixException {
        ProjectContractX toSend = new ProjectContractX(jobOrder);
        ProjectX p = null;
        try {
            p = (ProjectX) this.sendExtObj(toSend);
        }
        catch (Exception e) {
            throw new PhoenixException(e);
        }

        String projectNumber = jobOrder.getPhxProjectNumber();
        
        Project jobProject=null;
        if (projectNumber==null || projectNumber.isEmpty()) {
            jobProject = new Project();
        }
        else{
            jobProject=this.findById(Project.class, projectNumber);
        }
        
        jobProject.setProjectNumber(p.getProjectNumber());
        jobProject.setJobOrderNumber(jobOrder.getJobNumber());
        jobOrder.setPhxProjectNumber(jobProject.getProjectNumber());

        this.saveOrUpdate(Project.class, jobProject);
        this.saveOrUpdate(JobOrder.class, jobOrder);
        
        return jobProject;
    }
    
    @Override
    public Project createProjectType1(CEJobOrder jobOrder) throws PhoenixException{
        Project p=new Project();
        p.setProjectNumber(jobOrder.getJobNumber());
        p.setJobOrderNumber(jobOrder.getJobNumber());
        p.setType(ProjectType.TYPE_1);
        
        jobOrder.setProject(p);
        jobOrder.setProjectType(p.getType());
        
        this.saveOrUpdate(CEJobOrder.class, jobOrder);
        return p;
    }
    
    @Override
    public Project createOrUpdateProject(CEJobOrder jobOrder) throws PhoenixException {
        ProjectContractX toSend = new ProjectContractX(jobOrder);

        ProjectX p = null;
        try {
            p = (ProjectX) this.sendExtObj(toSend);
        }
        catch (Exception e) {
            throw new PhoenixException(e);
        }

        Project jobProject = jobOrder.getProject();
        if (jobProject == null) {
            jobProject = new Project();
        }
        jobProject.setProjectNumber(p.getProjectNumber());
        jobProject.setJobOrderNumber(jobOrder.getJobNumber());
        jobOrder.setProject(jobProject);
        jobOrder.setProjectType(jobProject.getType());

        this.saveOrUpdate(CEJobOrder.class, jobOrder);

        List<ProjectActivityX> pas = toSend.getProjectActivities();
        for (ProjectActivityX pa : pas) {
            String query = "update com.intertek.phoenix.entity.JobTest set updateFlag=? where id=?";
            Object[] params = new Object[] { Constants.SENT, pa.getJobTestId() };
            this.executeQuery(JobTest.class, query, params);
        }
        return jobProject;
    }

    @Override
    public <T> T saveOrUpdate(Class<T> entityType, T obj) throws DaoException {
        return DaoManager.getDao(entityType).saveOrUpdate(obj);
    }

    /*
     */
    @Override
    public Object sendExtObj(Object obj) throws PhoenixException {
        ESBSender sender = senders.get(obj.getClass());
        if (sender == null) {
            throw new ServiceException("Sender cannot be null");
        }

        WebServiceEntity log = null;

        if (obj instanceof Logable) {
            log = new WebServiceEntity();
            Logable logObj = (Logable) obj;
            log.setEntityKey(logObj.getId());
            log.setType(sender.getType());
        }

        Object response = null;
        try {
            response = sender.send(obj, log);
        }
        catch (Exception e) {
            throw new PhoenixException(e);
        }
        finally {
            if(log!=null){
                log.setCreateTime(new Date(System.currentTimeMillis()));
                this.saveOrUpdate(WebServiceEntity.class, log);
            }
        }

        return response;
    }

    public Map<Class<?>, ESBSender> getSenders() {
        return senders;
    }

    public void setSenders(Map<Class<?>, ESBSender> senders) {
        this.senders = senders;
    }

    @Override
    public int executeQuery(Class<?> entityType, String query, Object[] params) throws DaoException {
        Dao<?> dao = DaoManager.getDao(entityType);
        return dao.execute(query, params);
    }
}
