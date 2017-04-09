/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

/**
 * 
 * @author richard.qin
 */
@Entity
@DiscriminatorValue("TEST")
public class TestJobOrderLineItem extends CEJobOrderLineItem {
    @Column(name = "JOB_CONTRACT_TASK_ID", updatable = false, insertable = false)
    private Long jobContractTaskId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_CONTRACT_TASK_ID") 
    @Index(name="PHX_TESTJOLI_IDX_JCT_ID")
    private JobContractTest jobContractTask;
    
    public TestJobOrderLineItem(){
        
    }

    public Long getJobContractTaskId() {
        return jobContractTaskId;
    }

    public void setJobContractTaskId(Long jobContractTaskId) {
        this.jobContractTaskId = jobContractTaskId;
    }

    public JobContractTest getJobContractTask() {
        return jobContractTask;
    }

    public void setJobContractTask(JobContractTest jobContractTask) {
        if(jobContractTask != null){
            jobContractTaskId = jobContractTask.getId();
        }
        this.jobContractTask = jobContractTask;
    }
    
    public TestJobOrderLineItem clone(){
        TestJobOrderLineItem joli = new TestJobOrderLineItem();
        return this.copyTo(joli);
    }

    protected TestJobOrderLineItem copyTo(TestJobOrderLineItem joli) {
        super.copyTo(joli);
        this.getJobContractTask().addJobOrderLineItem(joli);
        
        return joli;
    }
}
