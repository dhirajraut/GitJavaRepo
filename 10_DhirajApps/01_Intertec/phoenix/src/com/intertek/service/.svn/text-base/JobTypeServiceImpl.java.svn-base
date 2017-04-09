package com.intertek.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.exception.ServiceException;
import com.intertek.domain.JobTypeSearch;
import com.intertek.entity.Event;
import com.intertek.entity.JobType;
import com.intertek.entity.Operation;
import com.intertek.pagination.Pagination;
import com.intertek.util.Constants;

public class JobTypeServiceImpl extends BaseService implements JobTypeService
{

public void searchJobType(JobTypeSearch search)
{
  if(search == null) return;

  StringBuffer sb = new StringBuffer();
  List params = new ArrayList();
  boolean hasWhere = false;
    String jobtype = search.getJobTypeCode().getValue();
  //String strJobTypeCodeSearch ='%'+jobtype.toUpperCase() + '%';

    if((jobtype != null) && !"".equals(jobtype.trim()))
    {
      String strJobTypeCodeSearch ='%'+jobtype.toUpperCase() + '%';
    sb.append("where upper(j.jobTypeCode) like ? ");
    params.add(strJobTypeCodeSearch);
    hasWhere = true;
    }

String description=search.getJobTypeDesc().getValue();
//String strJobTypeDesc='%'+description.toUpperCase()+'%';

      if((description!= null) && !"".equals(description.trim()))
      {
          String strJobTypeDesc='%'+description.toUpperCase()+'%';
          if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
        sb.append("upper(j.jobTypeDesc) like ?  ");
        params.add(strJobTypeDesc);
    }

  Pagination pagination = search.getPagination();
  List results = null;

  if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find("select count(j.jobTypeCode) from JobType j "+ sb.toString(),params.toArray());

        if(counts.size() > 0)
        {
        Number count = (Number)counts.get(0);
        pagination.setTotalRecord(count.intValue());
        }
    }
     results = getDao().find("select distinct j from JobType j " + sb.toString() + " order by  j.jobTypeCode ",params.toArray(),pagination);
          pagination.calculatePages();
   }
    else
    {
    results = getDao().find("select distinct j from JobType j "+ sb.toString(),params.toArray());
    }
   search.setResults(results);
   search.setPagination(pagination);
}



  public List getJobDescByJobType(String jobTypeCode)
  {
    String jobTypeCodeSearch = jobTypeCode.toUpperCase() + '%';
    List jobTypeCodes = getDao().find("from JobType j where upper(j.jobTypeCode) like ?",new Object[]{jobTypeCodeSearch});
    return jobTypeCodes;
  }


 public Operation getOperationsByName(String operationCode)
  {
   Operation operation=null;
    List operations = getDao().find("from Operation o where o.operationCode = ?",new Object[] {operationCode});
  if(operations!= null && operations.size() >0) {
      operation = (Operation)operations.get(0);
    } else{
          throw new ServiceException("operation.not.exist.error", new Object[] {operationCode}, null);
    }
  return operation;
  }

  public List getOperationDescByOperationType(String operationCode)
  {
    String operationCodeSearch = operationCode.toUpperCase() + '%';
    List operationCodes = getDao().find("from Operation o where upper(o.operationCode) like ?",new Object[]{operationCodeSearch});
    return operationCodes;
  }

public JobType addJobType(JobType jobType)
  {

  if(jobType == null) return null;
  JobType existingJobType = null;
  if(jobType != null)
  existingJobType = getJobTypeByName(jobType.getJobTypeCode());

  if(existingJobType != null)
  {
    Set existingOperatoinSet = existingJobType.getOperations();
    if(existingOperatoinSet != null && existingOperatoinSet.size() > 0)
      {
        Iterator iter = jobType.getOperations().iterator();
        while(iter.hasNext())
        {
          Operation operation= (Operation) iter.next();
          existingOperatoinSet.add(operation);
        }
      }
     jobType.setOperations(existingOperatoinSet);
  }

  return getDao().save(jobType);
  }



 public JobType getJobTypeByName(String name)
  {
  JobType jobtype=null;
   List jobTypes = new ArrayList();
       jobTypes = getDao().find("from JobType j left join fetch j.operations where upper(j.jobTypeCode) = '"+ name.toUpperCase() + "'", null);
  if(jobTypes!= null && jobTypes.size() >0) {
      jobtype = (JobType)jobTypes.get(0);
    } else{
          throw new ServiceException("jobtype.not.exist.error", new Object[] {name}, null);
    }
  return jobtype;
  }

public void saveJobType(JobType jobType)
{
  getDao().save(jobType);
}



public void searchOperation(JobTypeSearch search,String sortFlag)
{
  if(search == null) return;

  StringBuffer sb = new StringBuffer();
  List params = new ArrayList();
  boolean hasWhere = false;
    String oCode = search.getOperationCode().getValue();
  //String strOperationCodeSearch ='%'+oCode.toUpperCase() + '%';

    if((oCode != null) && !"".equals(oCode.trim()))
    {
      String strOperationCodeSearch ='%'+oCode.toUpperCase() + '%';
    sb.append("where upper(o.operationCode) like ? ");
    params.add(strOperationCodeSearch);
    hasWhere = true;
    }

      String oDescription=search.getDescription().getValue();
    //  String strOperationDesc='%'+oDescription.toUpperCase()+'%';

      if((oDescription!= null) && !"".equals(oDescription.trim()))
      {
        String strOperationDesc='%'+oDescription.toUpperCase()+'%';
          if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
        sb.append("upper(o.description) like ?");
        params.add(strOperationDesc);
    }

 String oStatus = search.getStatus().getValue();

if(oStatus!=null &&  !"".equals(oStatus.trim()))
  {
  if(oStatus.equals(Constants.STATUS_A) ||oStatus.equals(Constants.STATUS_I))
      {
          if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
      sb.append("o.status = ?");
        params.add(oStatus);
    }
}

  Pagination pagination = search.getPagination();
  List results = null;
  if(sortFlag != null && sortFlag.equals(""))
  {
  if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find("select count(o.operationCode) from Operation o "+ sb.toString(),params.toArray());

        if(counts.size() > 0)
        {
        Number count = (Number)counts.get(0);
        pagination.setTotalRecord(count.intValue());
        }
    }
     results = getDao().find("select distinct o from Operation o " + sb.toString() + " order by  o.operationCode ",params.toArray(),pagination);
          pagination.calculatePages();
  }else
    {
    results = getDao().find("select distinct o from Operation o "+ sb.toString()+ " order by o.operationCode ",params.toArray());
    }
   search.setResults(results);
   search.setPagination(pagination);
  }else
  {
    String orderByValue=" order by o."+sortFlag;
    if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find("select count(o.operationCode) from Operation o "+ sb.toString(),params.toArray());

            if(counts.size() > 0)
            {
            Number count = (Number)counts.get(0);
            pagination.setTotalRecord(count.intValue());
            }
        }
              pagination.calculatePages();
       }
    results = getDao().find("select distinct o from Operation o " + sb.toString() + orderByValue,params.toArray(),pagination);
    search.setResults(results);
    search.setPagination(pagination);
    //search.setTotalResults(results);

  }
}

public  JobType  getJobOperationByJobType(String jType)
{
List operations=getDao().find( " from JobType j left join fetch j.operations op where j.jobTypeCode ='"+jType+"' and op.status='A' order by  op.description asc ",null);
if(operations.size()>0) return (JobType)operations.get(0);
return null;
}
public Operation addOperation(Operation operation)
{
  return getDao().save(operation);
}

public Operation saveOperation(Operation operation)
{
return getDao().save(operation);
}

 public Operation getOperationByOperatoinCode(String name)
  {
  Operation operation = null;
  if(name != null) {
    List operations = new ArrayList();

       operations = getDao().find("from Operation o left join fetch o.events where upper(o.operationCode) = '"+ name.toUpperCase() + "'", null);
     if(operations!=null && operations.size()>0)
    {
      operation = (Operation)operations.get(0);
    } else{
          throw new ServiceException("operation.not.exist.error", new Object[] {name}, null);
    }
  }
  return operation;
  }



public Event saveEvent(Event  event)
{
return getDao().save(event);
}
public void searchEvent(JobTypeSearch search,String sortFlag)
{
  if(search == null) return;

  StringBuffer sb = new StringBuffer();
  List params = new ArrayList();
  boolean hasWhere = false;
    String event = search.getEventCode().getValue();
  //String strEventCodeSearch ='%'+event.toUpperCase() + '%';

    if((event != null) && !"".equals(event.trim()))
    {
      String strEventCodeSearch ='%'+event.toUpperCase() + '%';
    sb.append("where upper(e.eventCode) like ? ");
    params.add(strEventCodeSearch);
    hasWhere = true;
    }

String name=search.getEventName().getValue();
//String strEventName='%'+name.toUpperCase()+'%';

      if((name!= null) && !"".equals(name.trim()))
      {
        String strEventName='%'+name.toUpperCase()+'%';
          if(hasWhere) sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
        sb.append("upper(e.eventName) like ?  ");
        params.add(strEventName);
    }

  Pagination pagination = search.getPagination();
  List results = null;
  if(sortFlag != null && sortFlag.equals(""))
  {
  if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find("select count(e.eventCode) from Event e "+ sb.toString(),params.toArray());

        if(counts.size() > 0)
        {
        Number count = (Number)counts.get(0);
        pagination.setTotalRecord(count.intValue());
        }
    }
     results = getDao().find("select distinct e from Event e " + sb.toString() + " order by  e.eventCode ",params.toArray(),pagination);
          pagination.calculatePages();
   }
    else
    {
    results = getDao().find("select distinct e from Event e "+ sb.toString()+" order by e.eventCode ",params.toArray());
    }
   search.setResults(results);
   search.setPagination(pagination);
  }else
  {
    String orderByValue=" order by e."+sortFlag;

    if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find("select count(e.eventCode) from Event e "+ sb.toString(),params.toArray());

            if(counts.size() > 0)
            {
            Number count = (Number)counts.get(0);
            pagination.setTotalRecord(count.intValue());
            }
        }
            pagination.calculatePages();
       }

    results = getDao().find("select distinct e from Event e " + sb.toString() +orderByValue,params.toArray(),pagination);

    search.setResults(results);
    search.setPagination(pagination);
    //search.setTotalResults(results);
  }
}


 public Event getEventByEventCode(String eventCode)
  {
   Event event=null;
    List events = getDao().find("from Event e where e.eventCode = ?",new Object[] {eventCode});
if(events!= null && events.size() >0) {
      event = (Event)events.get(0);
    } else{
          throw new ServiceException("invalid.event.error", new Object[] {eventCode}, null);
    }
  return event;
  }
}
