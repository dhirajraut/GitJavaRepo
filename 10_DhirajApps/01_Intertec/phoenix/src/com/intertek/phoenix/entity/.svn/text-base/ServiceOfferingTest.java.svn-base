/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Test;

/**
 * This is the joint table for ServiceOffering and Test.
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.ServiceOfferingTest")
@Table(name="PHX_SERVICEOFFERING_TEST")
public class ServiceOfferingTest {
    
    @Id
    @SequenceGenerator(name="PHX_ServiceOfferingTest_seq", 
                       sequenceName = "PHX_SERVICEOFFERING_TEST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_ServiceOfferingTest_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "SERVICE_OFFERING_ID", updatable = false, insertable = false)
    private Long serviceOfferingId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "SERVICE_OFFERING_ID")
    @Index(name="PHX_SRVC_OFFER_TEST_IDX_SRVC")
    private ServiceOffering serviceOffering;
    
    @Column(name = "TEST_ID", updatable = false, insertable = false)
    private String testId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "TEST_ID")
    @Index(name="PHX_SRVC_OFFER_TEST_IDX_TEST")
    private Test test;

    public ServiceOfferingTest(){
        
    }
    
    public Long getServiceOfferingId() {
        return serviceOfferingId;
    }

    public void setServiceOfferingId(Long serviceOfferingId) {
        this.serviceOfferingId = serviceOfferingId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceOffering getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(ServiceOffering serviceOffering) {
        this.serviceOfferingId = serviceOffering.getId();
        this.serviceOffering = serviceOffering;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.testId = test.getTestId();
        this.test = test;
    }

}
