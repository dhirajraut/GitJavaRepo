/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;



import javax.persistence.*;


/**
 * 
 * @author eric.nguyen
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_JOB_ORDER_LINE_ITEM_ATTACHMENT")
@DiscriminatorValue("JOLI")
public class JobOrderLineItemAttachment extends Attachment {
	@ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "JOLI_ID")
	private CEJobOrderLineItem joli;

	public JobOrderLineItemAttachment() {

	}

	public CEJobOrderLineItem getJobOrderLineItem(){
		return joli;
	}

	public void setJobOrderLineItem(CEJobOrderLineItem joli){
		this.joli = joli;
	}
}
