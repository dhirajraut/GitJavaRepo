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
@DiscriminatorValue("JOLI")
public class JobOrderLineItemNote extends Note {
    @Column(name = "JOLI_ID", updatable = false, insertable = false)
    private long joliId;
    
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
	@JoinColumn(name = "JOLI_ID")
	private CEJobOrderLineItem joli;

	public JobOrderLineItemNote() {

	}

	public CEJobOrderLineItem getJobOrderLineItem(){
		return joli;
	}

	public void setJobOrderLineItem(CEJobOrderLineItem joli){
		this.joli = joli;
	}

	public long getJobOrderLineItemId(){
        return joliId;
    }

    public void setJobOrderLineItemId(long joliId){
        this.joliId = joliId;
    }


}
