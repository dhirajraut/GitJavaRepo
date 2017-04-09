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
 * @author patni
 */
@Entity
@DiscriminatorValue("JOBTEST")
public class JobTestNote extends Note {
    @Column(name = "JOB_TEST_ID", updatable = false, insertable = false)
    private long jobTestId;
    
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_TEST_ID")
    private JobTest jobTest;
    
	public JobTestNote(){

	}

    public JobTest getJobTest() {
        return jobTest;
    }

    public void setJobTest(JobTest jobTest) {
        if(jobTest != null){
            this.jobTestId = jobTest.getId();
        }
        this.jobTest = jobTest;
    }

    public long getJobTestId() {
        return jobTestId;
    }

    public void setJobTestId(long jobTestId) {
        this.jobTestId = jobTestId;
    }
}
