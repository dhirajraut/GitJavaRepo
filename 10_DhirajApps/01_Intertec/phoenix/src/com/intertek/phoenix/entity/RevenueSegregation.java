/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 */
package com.intertek.phoenix.entity;

import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * This class capture the details on how invoice amount for
 * a JobOrderLineItem is recorded for revenue segregation purposes.
 *
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_REVENUE_SEGREGATION")
public class RevenueSegregation {
    @Id
    @SequenceGenerator(name = "Revenue_Segregation_seq", sequenceName = "REVENUE_SEG_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Revenue_Segregation_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHX_JOB_ORDER_LINE_ITEM_ID", updatable = false, insertable = false)
    private Long cEJobOrderLineItemId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PHX_JOB_ORDER_LINE_ITEM_ID")
    @Index(name="REVENUE_SEGREGATION_INDEX_OLI")
    private CEJobOrderLineItem cEJobOrderLineItem;

    @Column(name = "AMOUNT", precision = 38, scale = 4)
    private double amount;
    @Column(name = "ACCOUNT", length = 45)
    private String account; // GL account code
    @Column(name = "DEPT_ID", length = 8)
    private String deptId; // GL dept ID
    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String description;
    @Column(name = "PRIMARY", precision = 1, scale = 0)
    @org.hibernate.annotations.Type(type = "yes_no")
    private java.lang.Boolean primary;

    public RevenueSegregation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public java.lang.Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(java.lang.Boolean primary) {
        this.primary = primary;
    }
    public Long getCEJobOrderLineItemId() {
		return cEJobOrderLineItemId;
	}

	public CEJobOrderLineItem getCEJobOrderLineItem() {
		return cEJobOrderLineItem;
	}

	public void setCEJobOrderLineItemId(Long cEJobOrderLineItemId) {
		this.cEJobOrderLineItemId = cEJobOrderLineItemId;
	}

	public void setCEJobOrderLineItem(CEJobOrderLineItem cEJobOrderLineItem) {
		if (cEJobOrderLineItem != null) {
            this.cEJobOrderLineItemId = cEJobOrderLineItem.getId();
        }
        this.cEJobOrderLineItem = cEJobOrderLineItem;
	}

	public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
