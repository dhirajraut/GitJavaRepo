package com.intertek.domain;

import com.intertek.entity.JobType;
import com.intertek.entity.Operation;

public class AddJobType
{
private JobType jobType;
private String addOrDeleteJobType;
private Operation operation;
private Operation[] operations;
private int jobTypeCount ;
private int jobTypeIndex;
private String operationFlag;
private String rowNum;
private String searchForm;


	public void setJobType(JobType jobType)
	{
	this.jobType = jobType;
	}

	public JobType getJobType()
	{
	return jobType;
	}

public String getAddOrDeleteJobType()
{
return addOrDeleteJobType;
}

public void setAddOrDeleteJobType(String addOrDeleteJobType)
{
this.addOrDeleteJobType = addOrDeleteJobType;
}

public Operation getOperation()
{
return operation;
}

public void  setOperation(Operation  operation)
{
this.operation = operation;
}   

public Operation[] getOperations()
{
return operations;
}

public void setOperations(Operation[] operations)
{
this.operations=operations;
}

public int getJobTypeCount()
{
return jobTypeCount;
}

public void setJobTypeCount(int jobTypeCount)
{
this.jobTypeCount = jobTypeCount;
}

public int getJobTypeIndex()
{
return jobTypeIndex;
}

public void setJobTypeIndex(int jobTypeIndex)
{
this.jobTypeIndex = jobTypeIndex;
}

public String getOperationFlag()
{
return operationFlag;
}

public void setOperationFlag(String operationFlag)
{
this.operationFlag = operationFlag;
}

public String getRowNum()
{
return rowNum;
}

public void setRowNum(String rowNum)
{
this.rowNum = rowNum;
}
public void setSearchForm(String searchForm) 
{
this.searchForm = searchForm;
}

public String getSearchForm() 
{
return searchForm;
}

}
