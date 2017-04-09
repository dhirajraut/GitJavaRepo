/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import javax.persistence.*;

/**
 * PepoleSoft project.
 * 
 * @author richard.qin
 */
@Entity(name = "project_ce")
@Table(name = "PHX_PROJECT")
public class Project {
    @Id
    @Column(name = "PROJECT_NUMBER", length = 128)
    private String projectNumber;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Column(name = "JOB_NUMBER", length = 128)
    private String jobOrderNumber;

    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String description;

    public Project() {

    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
