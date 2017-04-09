/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.job;

import java.util.Set;

import com.intertek.entity.Service;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractSlate;
import com.intertek.phoenix.entity.JobContractTest;

/**
 * 
 * @author richard.qin
 */
public interface ContractServiceLevelInt extends ServiceLevel {
    
    public <T extends JobContractTest> T createJobContractTest(Class<T> type, Test test);
    public <T extends JobContractSlate> T createJobContractSlate(Class<T> type, Slate slate);
    public <T extends JobContractService> T createJobContractService(Class<T> type, Service service);
    
    public <T extends JobContractTest> boolean removeJobContractTest(JobContractTest test);
    public <T extends JobContractSlate> boolean removeJobContractSlate(JobContractSlate slate);
    public <T extends JobContractService> boolean removeJobContractService(JobContractService service);
    
    public <T extends JobContractTest> Set<JobContractTest> getJobContractTests();
    public <T extends JobContractSlate> Set<JobContractSlate> getJobContractSlates();
    public <T extends JobContractService> Set<JobContractService> getJobContractServices();
}
