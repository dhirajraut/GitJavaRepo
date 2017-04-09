/**  
 * @ Company: Intertek
 * @ Copyright: Intertek 2009
 * @ Project : Phoenix 2.0 for Consumer and Electronics
 * @ File Name : Warehouse.java
 * @ Date : 4/22/2009
 * @ Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The class models Business Stream, used for CE Job orders
 * TODO
 * 
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_BUSINESS_STREAM")
public class BusinessStream implements Collectable{
    @Id
    @SequenceGenerator(name="PHX_BusinessStream_seq", sequenceName = "PHX_BUSINESS_STREAM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_BusinessStream_seq" )
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE", length = 128)
    private String code;
    @Column(name = "DESCRIPTION", length = 128)
    private String description;

    @Column(name = "NEW_FLAG", length = 8)
    private String newFlag;
    
    @Column(name = "UPDATE_FLAG", length = 8)
    private String updateFlag;
    
    public BusinessStream() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
}
