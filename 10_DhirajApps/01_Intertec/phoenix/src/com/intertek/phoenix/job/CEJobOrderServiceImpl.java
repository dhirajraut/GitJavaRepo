/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.job;

import com.intertek.entity.Service;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractSlate;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.entity.JobService;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobSlate;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.job.ServiceLevel.ServiceLevelType;


/**
 * This class override JobSrvcImpl for CE specific behaviors, for example,
 * CEJobOrder supports no additional service levels
 * 
 * @author richard.qin
 */
public class CEJobOrderServiceImpl extends JobOrderServiceImpl {

    @Override
    public JobContractService createJobContractService(ContractServiceLevel serviceLevel, Service service) throws JobSrvcException {
        if(serviceLevel.getParentServiceLevelId() == null){
            return super.createJobContractService(serviceLevel, service);
        }
        throw new JobSrvcException("CE JobOrder does not support additional service levels");
    }

    @Override
    public JobContractSlate createJobContractSlate(ContractServiceLevel serviceLevel, Slate slate) throws JobSrvcException {
        throw new JobSrvcException("CE JobOrder does not support adding Slates");
    }

    @Override
    public JobContractTest createJobContractTest(ContractServiceLevel serviceLevel, Test test) throws JobSrvcException {
        if(serviceLevel.getParentServiceLevelId() == null){
            return super.createJobContractTest(serviceLevel, test);
        }
        throw new JobSrvcException("CE JobOrder does not support additional service levels");
    }

    @Override
    public JobService createJobService(JobServiceLevel serviceLevel, Service service) throws JobSrvcException {
        if(serviceLevel.getParentServiceLevelId() == null){
            return super.createJobService(serviceLevel, service);
        }
        throw new JobSrvcException("CE JobOrder does not support additional service levels");
    }

    @Override
    public JobSlate createJobSlate(JobServiceLevel serviceLevel, Slate slate) throws JobSrvcException {
        throw new JobSrvcException("CE JobOrder does not support adding Slates");
    }

    @Override
    public JobTest createJobTest(JobServiceLevel serviceLevel, Test test) throws JobSrvcException {
//        if(serviceLevel.getParentServiceLevelId() == null){
            JobTest jobTest = serviceLevel.createJobTest(JobTest.class, test);
            jobTest.setJobServiceLevelId(serviceLevel.getId());
            try {
                DaoManager.getDao(JobTest.class).saveOrUpdate(jobTest);
            }
            catch (DaoException e) {
                throw new JobSrvcException("Failed to save new CEJobTest", e);
            }
            System.out.println("Job Test ID"+jobTest.getId());
            return jobTest;
//        }
//        throw new JobSrvcException("CE JobOrder does not support additional service levels");

    }

    @Override
    public ContractServiceLevel createContractServiceLevel(ContractServiceLevel parent, ServiceLevelType type, 
                                                           String serviceLevelName) throws JobSrvcException {
        throw new UnsupportedOperationException("CE JobOrder does not support additional service levels");
    }

    @Override
    public JobServiceLevel createJobServiceLevel(JobServiceLevel parent, ServiceLevelType type, 
                                                 String serviceLevelName) throws JobSrvcException {
        throw new UnsupportedOperationException("CE JobOrder does not support additional service levels");
    }
}
