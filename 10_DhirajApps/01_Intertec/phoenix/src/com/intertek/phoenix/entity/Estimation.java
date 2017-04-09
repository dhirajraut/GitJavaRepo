package com.intertek.phoenix.entity;

import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * This class holds different estimations for Job Order Line Item.
 *
 * @author lily.sun
 * @author richard.qin
 */

@Entity
@Table(name = "PHX_ESTIMATION")
public class Estimation {
    @Id
    @SequenceGenerator(name = "PHX_Estimation_seq", sequenceName = "PHX_ESTIMATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_Estimation_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "ESTIMATED_HOUR", precision = 38, scale = 4)
    private double estimatedHour;

    @Transient
    private String userTypeId;

//    @Column(name = "LINE_ID", updatable = false, insertable = false)
//    private Long lineId;
//    @ManyToOne
//    @JoinColumn(name = "LINE_ID")
//    @Index(name="ESTIMATION_INDEX_JOLI")
//    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    private CEJobOrderLineItem ceJobOrderLineItem;
//
    @Column(name = "JOBTEST_ID", updatable = false, insertable = false)
    private Long jobTestId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOBTEST_ID")
    @Index(name = "PHX_ESTIMATION_IDX_JOBTEST")
    private JobTest jobTest;
    
    public Estimation(){
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public double getEstimatedHour() {
        return estimatedHour;
    }

    public void setEstimatedHour(double estimatedHour) {
        this.estimatedHour = estimatedHour;
    }

    public Long getJobTestId() {
        return jobTestId;
    }

    public void setJobTestId(Long jobTestId) {
        this.jobTestId = jobTestId;
    }

    public JobTest getJobTest() {
        return jobTest;
    }

    public void setJobTest(JobTest jobTest) {
        this.jobTest = jobTest;
    }

    public String getUserTypeId() {
        if(this.getUserType()!=null){
            userTypeId=this.getUserType().getValue();
            return userTypeId;
        }else{
            return null;
        }        
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
        for(UserType uType : UserType.values()){
            if(uType.getValue().equals(userTypeId)){
                this.setUserType(uType);
                break;
            }
        }
    }

//    public Long getCeJobTestId() {
//        return ceJobTestId;
//    }
//
//    public void setCeJobTestId(Long ceJobTestId) {
//        this.ceJobTestId = ceJobTestId;
//    }
//
//    public CEJobTest getCeJobTest() {
//        return ceJobTest;
//    }
//
//    public void setCeJobTest(CEJobTest ceJobTest) {
//        this.ceJobTest = ceJobTest;
//    }

}
