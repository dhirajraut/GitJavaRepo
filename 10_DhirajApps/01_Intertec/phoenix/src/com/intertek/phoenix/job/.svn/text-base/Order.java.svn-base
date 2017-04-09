/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.job;

import java.util.Collection;
import java.util.Date;

import com.intertek.phoenix.entity.JobOrderType;


/**
 * The base interface of JobOrder entities. It defines all the common
 * functionalities of job orders for all business divisions. This interface
 * is one of the key components to model the unified Service Structure
 * for all Intertek business divisions.
 * 
 * Specifics of different JobOrder for different divisions are captured
 * at the entity class level. For example, CEJobOrder and OCA JobOrder
 * have different details in their respective classes.
 * 
 * The name of this interface should be JobOrder, however, since JobOrder
 * is already an entity class defined in Old Phoenix. We may need to rename
 * existing JobOrder to be OCAJobOrder later.
 * 
 * This interface extends ServiceLevel, which means that services can be 
 * provided at the order level, not tied to any established ServiceLevel. 
 * This feature is also know as Standalone service.
 * 
 * @author richard.qin
 */
public interface Order {
    
    /**
     * Get the job order name
     * @return
     */
    String getJobOrderName();
    /**
     * Get the nomination date
     */
    Date getNominationDate();
    /**
     * Get the job finish date
     * @return
     */
    Date getJobFinishDate();

    /**
     * Get the job type
     * @return
     */
    JobOrderType getJobType();
    /**
     * Get the branch related to the JobOrder
     * @return
     */
    String getBranchName();
    /**
     * Get the contract job orders related to this job order.
     * <p> Depends on the business divisions, some may support one contract
     * only, others may support multiple contracts.
     * @return
     */
    Collection<? extends ContractJobOrder> getContractJobOrder();
}
