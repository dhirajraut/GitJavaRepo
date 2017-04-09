/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.job;

import java.util.Collection;

/**
 * 
 * @author richard.qin
 */
public interface ServiceLevel {
    /**
     * The type of service level is determined by business divisions, for example
     * CE and OCA have different service level types for their service structure.
     */
    enum ServiceLevelType {STANDALONE, VESSEL, LOT, PRODUCT, JOB}; 
    // JOB is the top ServiceLevel.
    // what about other values?
    
    /**
     * Get the type of the service level.
     * @return
     */
    public ServiceLevelType getServiceLevelType();
    /**
     * The name of a service level can be read from relevant database table for the
     * given service level type.
     * @return
     */
    public String getServiceLevelName();
    
    /**
     * ServiceLevel can be organized in a hierarchy, this method returns the
     * parent level ServiceLevel that contains this ServiceLevel.
     * @return
     */
    public ServiceLevel getParentServiceLevel();

    /**
     * ServiceLevel can be organized in a hierarchy, this method returns the
     * next level ServiceLevel contained in ServiceLevel.
     * @return
     */
    public Collection<? extends ServiceLevel> getChildServiceLevels();
    
    public ServiceLevel createChildServiceLevel(ServiceLevelType type, String name);
    
    public boolean removeChildServiceLevel(ServiceLevel childServiceLevel); 
    
}
