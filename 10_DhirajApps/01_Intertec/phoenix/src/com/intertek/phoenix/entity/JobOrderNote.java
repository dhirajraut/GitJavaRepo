/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * This class should be named JobContractNote
 * @author eric.nguyen
 * @author richard.qin
 */
@Entity
@DiscriminatorValue("JO")
public class JobOrderNote extends Note {
    @Column(name = "JOB_ORDER_CONTRACT_ID", updatable = false, insertable = false)
    private Long jobContractId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_ORDER_CONTRACT_ID")
    private CEJobContract jobContract;
    
	public JobOrderNote(){

	}

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        if(jobContract != null){
            this.jobContractId = jobContract.getId();
        }
        this.jobContract = jobContract;
    }

    public Long getJobContractId() {
        return jobContractId;
    }

    public void setJobContractId(Long jobContractId) {
        this.jobContractId = jobContractId;
    }
}
