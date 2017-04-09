/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import com.intertek.phoenix.entity.ServiceOffering;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class ServiceOfferingX {
    private String id;
    private String description;
    private String parentId;

    public ServiceOfferingX() {
    }

    public ServiceOffering convert(){
        ServiceOffering so=new ServiceOffering();
        so.setId(Long.parseLong(this.getId()));
        so.setDescription(this.getDescription());
        so.setParentServiceOfferingId(Long.parseLong(this.getParentId()));
        return so;
    }
    
    public ServiceOfferingX(ServiceOffering so) {
        this.id = so.getCode() + "";
        this.description = so.getDescription();
        this.parentId = so.getParentServiceOfferingId() + "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
