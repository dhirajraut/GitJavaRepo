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
import com.intertek.phoenix.entity.JobService;
import com.intertek.phoenix.entity.JobSlate;
import com.intertek.phoenix.entity.JobTest;

/**
 * The generic methods in this interface are designed for possible 
 * extensions to JobTest/JobSerice objects that are for specific
 * business divisions, such as CE, due to the even changing nature
 * of the requirement. However, if it becomes clear that there is
 * no need for such extension, then these generic methods will be
 * refactored normal methods.
 * 
 * @author richard.qin
 */
public interface JobServiceLevelInt extends ServiceLevel {
    
    public <T extends JobTest> T createJobTest(Class<T> type, Test test);
    public <T extends JobSlate> T createJobSlate(Class<T> type, Slate slate);
    public <T extends JobService> T createJobService(Class<T> type, Service service);
    
    public <T extends JobTest> boolean removeJobTest(T test);
    public <T extends JobSlate> boolean removeJobSlate(T slate);
    public <T extends JobService> boolean removeJobService(T service);
    
    public <T extends JobTest> Set<T> getJobTests();
    public <T extends JobSlate> Set<T> getJobSlates();
    public <T extends JobService> Set<T> getJobServices();
}
