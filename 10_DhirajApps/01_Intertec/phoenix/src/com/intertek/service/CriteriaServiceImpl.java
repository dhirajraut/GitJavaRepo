package com.intertek.service;

import java.util.List;

import com.intertek.entity.JobSearchCriteria;


public class CriteriaServiceImpl extends BaseService implements CriteriaService{

  public List<JobSearchCriteria> getJobSearchCriteria(String loginName){
    if(loginName==null){
      return null;
    }
    loginName=loginName.trim().toLowerCase();

    return this.getDao().find("from JobSearchCriteria jsc where lower(jsc.loginName)=? order by jsc.searchName", new Object[]{loginName});
  }

  public JobSearchCriteria getJobSearchCriteria(long id){
    List<JobSearchCriteria> list=this.getDao().find("from JobSearchCriteria jsc where jsc.id=?", new Object[]{id});
    if(list!=null && list.size()>0){
      return list.get(0);
    }
    return null;
  }

  public JobSearchCriteria saveJobSearchCriteria(JobSearchCriteria jsc){
    if(jsc!=null){
      return this.getDao().save(jsc);
    }
    return null;
  }

  public void deleteJobSearchCriteria(JobSearchCriteria jsc){
    if(jsc!=null){
      this.getDao().remove(jsc);
    }
  }

  public void deleteJobSearchCriteria(long jscId){
    deleteJobSearchCriteria(getJobSearchCriteria(jscId));
  }
}

