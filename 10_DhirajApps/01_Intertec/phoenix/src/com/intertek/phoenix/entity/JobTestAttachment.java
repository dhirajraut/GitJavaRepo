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
 * @author patni
 */
@Entity
@DiscriminatorValue("JOBTEST")
public class JobTestAttachment extends Attachment {
    @Column(name = "JOB_TEST_ID", updatable = false, insertable = false)
    private long jobTestId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_TEST_ID")
    private JobTest jobTest;

	public JobTestAttachment() {

	}

	public long getJobTestId() {
        return jobTestId;
    }

    public void setJobTestId(long jobTestId) {
        this.jobTestId = jobTestId;
    }

    public JobTest getJobTest() {
        return jobTest;
    }

    public void setJobTest(JobTest jobTest) {
        if (jobTest != null){
            setJobTestId(jobTest.getId());
        }
        this.jobTest = jobTest;
    }
}
