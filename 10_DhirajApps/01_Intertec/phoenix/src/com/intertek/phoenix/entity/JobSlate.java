/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import javax.persistence.CascadeType;
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

import com.intertek.entity.Slate;

/**
 * 
 * @author richard.qin
 */
@Entity(name = "com.intertek.phoenix.entity.JobSlate")
@Table(name = "PHX_JOB_SLATE")
public class JobSlate {
    @Id
    @SequenceGenerator(name="PHX_JobSlate_seq", sequenceName = "PHX_JOB_SLATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobSlate_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "JOB_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long jobServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SERVICE_LEVEL_ID")
    @Index(name="PHX_JOB_SLATE_IDX_JS_LVL")
    private JobServiceLevel jobServiceLevel;
    
    @Column(name = "SLATE_ID", updatable = false, insertable = false)
    private String slateId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "SLATE_ID")
    @Index(name="PHX_JOB_SLATE_IDX_SLATE")
    private Slate slate;
    
    @Column(name = "QUANTITY", precision = 38, scale = 4)
    private double quantity;
    
    @Column(name = "OT", precision = 1, scale = 0)
    private boolean ot;
    
    @Column(name = "LINE_DESCRIPTION", length = 1024)
    private String lineDescription;
    
    @Column(name = "CONTRACT_REF_NO", length = 45)
    private String contractRefNo;
    
    // TODO not sure is the pricing info belong here
    @Column(name = "UNIT_PRICE", precision = 38, scale = 4)
    private double unitPrice;
    
    @Column(name = "DISCOUNT", precision = 38, scale = 4)
    private double discount;
    
    @Column(name = "TOTAL_PRICE", precision = 38, scale = 4)
    private double totalPrice;
    
    public JobSlate(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobServiceLevel getJobServiceLevel() {
        return jobServiceLevel;
    }

    public void setJobServiceLevel(JobServiceLevel jobServiceLevel) {
        if(jobServiceLevel != null){
            this.jobServiceLevelId = jobServiceLevel.getId();
        }
        this.jobServiceLevel = jobServiceLevel;
    }

    public Slate getSlate() {
        return slate;
    }

    public void setSlate(Slate slate) {
        this.slateId = slate.getSlateId();
        this.slate = slate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isOt() {
        return ot;
    }

    public void setOt(boolean ot) {
        this.ot = ot;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public String getContractRefNo() {
        return contractRefNo;
    }

    public void setContractRefNo(String contractRefNo) {
        this.contractRefNo = contractRefNo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getJobServiceLevelId() {
        return jobServiceLevelId;
    }

    public void setJobServiceLevelId(Long jobServiceLevelId) {
        this.jobServiceLevelId = jobServiceLevelId;
    }

    public String getSlateId() {
        return slateId;
    }

    public void setSlateId(String slateId) {
        this.slateId = slateId;
    }

}
