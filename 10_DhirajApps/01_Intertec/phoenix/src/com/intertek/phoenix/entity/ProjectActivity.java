/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * Activities in Project.
 * <p> Each projectActivity will match to a CEJobOrderLineItem.
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_PROJECT_ACTIVITY")
public class ProjectActivity {
    @Id
    @Column(name = "ID", length = 128)
    private String id;

    @Column(name="PROJECT_NUMBER", updatable = false, insertable = false)
    private String projectNumber;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PROJECT_NUMBER")
    @Index(name="PROJECT_ACTIVITY_INDEX_PROJECT")
    private Project project;

    @Column(name="LINE_ITEM_ID", length = 128)
    private String lineItemId;

    @Column(name="TEST", length = 128)
    private String test;

    @Column(name="DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String description;

    @Column(name="WAREHOUSE_NAME", length = 128)
    private String warehouseName;
    
    public ProjectActivity(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

}
