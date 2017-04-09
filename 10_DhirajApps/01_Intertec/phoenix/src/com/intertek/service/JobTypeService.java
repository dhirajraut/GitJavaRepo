package com.intertek.service;

import java.util.List;

import com.intertek.domain.JobTypeSearch;
import com.intertek.entity.Event;
import com.intertek.entity.JobType;
import com.intertek.entity.Operation;

public interface JobTypeService
{
void searchJobType(JobTypeSearch search);	
public List getJobDescByJobType(String jobTypeCode); 
Operation getOperationsByName(String operationcode);
public List getOperationDescByOperationType(String operationName); 
JobType addJobType(JobType jobType);
public JobType getJobTypeByName(String name);
void saveJobType(JobType jobType);
void searchOperation(JobTypeSearch search,String sortFlag);	
JobType getJobOperationByJobType(String jType);
Operation addOperation(Operation operation);
Operation saveOperation(Operation operation);
Operation getOperationByOperatoinCode(String name);
Event saveEvent(Event event);
void searchEvent(JobTypeSearch search,String sortFlag);
Event getEventByEventCode(String eventCode);

}

