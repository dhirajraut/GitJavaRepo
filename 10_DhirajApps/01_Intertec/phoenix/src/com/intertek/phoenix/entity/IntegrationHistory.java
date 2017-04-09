/**
 * 
 */
package com.intertek.phoenix.entity;

import javax.persistence.*;

/**
 * 
 * @author richard.qin
 */
@Entity
@Table(name="PHX_INTEGRATION_HISTORY")
public class IntegrationHistory {
    
    @Id
    @Column (name = "JOB_ORDER_ID" )
    private String jobOrderNumber;

    /**
     * @return the jobOrderId
     */
    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    /**
     * @param jobOrderId the jobOrderId to set
     */
    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

}
