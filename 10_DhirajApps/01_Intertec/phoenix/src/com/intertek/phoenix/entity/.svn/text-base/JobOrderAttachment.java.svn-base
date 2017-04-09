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
import javax.persistence.Table;


/**
 * 
 * @author eric.nguyen
 * @author richard.qin
 */
@Entity
@DiscriminatorValue("JO")
public class JobOrderAttachment extends Attachment {
    @Column(name = "JOB_ORDER_CONTRACT_ID", updatable = false, insertable = false)
    private long jobContractId;
	@ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "JOB_ORDER_CONTRACT_ID")
	private CEJobContract jobContract;

	public JobOrderAttachment() {

	}

	public long getJobContractId() {
        return jobContractId;
    }

    public void setJobContractId(long ceJobOrderContractId) {
        this.jobContractId = ceJobOrderContractId;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        if (jobContract != null){
            setJobContractId(jobContract.getId());
        }
        this.jobContract = jobContract;
    }
}
