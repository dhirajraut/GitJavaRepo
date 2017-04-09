/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.phoenix.common.EnumField;

/**
 * A new concept introduced for organizing Test/Service. This information
 * is required for accounting purposes.
 * 
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_SERVICE_OFFERING")
public class ServiceOffering implements EnumField{
    @Id
    @SequenceGenerator(name="PHX_ServiceOffering_seq", sequenceName = "PHX_SERVICE_OFFER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_ServiceOffering_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name="CODE", length=200)
    private String code;
    
    @Column(name="DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String description;

    @Column(name = "PARENT_ID", updatable = false, insertable = false)
    private Long parentServiceOfferingId;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PARENT_ID")
    @Index(name="PHX_SRVC_OFFERING_IDX_PARENT")
    private ServiceOffering parentServiceOffering;
    
    @Column(name="DEPARTMENT_CODE", length = 45)
    private String departmentCode;
   
    public ServiceOffering(){
        
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Long getParentServiceOfferingId() {
		return parentServiceOfferingId;
	}

	public void setParentServiceOfferingId(Long parentServiceOfferingId) {
		this.parentServiceOfferingId = parentServiceOfferingId;
	}

	public ServiceOffering getParentServiceOffering() {
		return parentServiceOffering;
	}

	public void setParentServiceOffering(ServiceOffering parentServiceOffering) {
		this.parentServiceOffering = parentServiceOffering;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return getDescription();
    }

    @Override
    public String getValue() {
        return getId().toString();
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
