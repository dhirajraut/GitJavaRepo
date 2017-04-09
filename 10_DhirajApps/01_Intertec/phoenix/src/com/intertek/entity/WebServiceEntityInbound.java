package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import java.util.Date;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * WebServiceEntityInbound generated by hbm2java
 */
public class WebServiceEntityInbound  implements java.io.Serializable {

    // Fields    

     private long id;
     private String content;
     @Length(min=0, max=512) private String entityKey;
     @Length(min=0, max=16) private String type;
     private Boolean status;
     @Length(min=0, max=1024) private String result;
     @Length(min=0, max=1024) private String message;
     @Length(min=0, max=128) private String createUser;
     private Date createTime;
     private Long parentId;
     private WebServiceInbound mainMsg;

     // Constructors

    /** default constructor */
    public WebServiceEntityInbound() {
    }

    /** full constructor */
    public WebServiceEntityInbound(String content, String entityKey, String type, Boolean status, String result, String message, String createUser, Date createTime, Long parentId, WebServiceInbound mainMsg) {
       this.content = content;
       this.entityKey = entityKey;
       this.type = type;
       this.status = status;
       this.result = result;
       this.message = message;
       this.createUser = createUser;
       this.createTime = createTime;
       this.parentId = parentId;
       this.mainMsg = mainMsg;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public String getEntityKey() {
        return this.entityKey;
    }
    
    public void setEntityKey(String entityKey) {
        this.entityKey = entityKey;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getResult() {
        return this.result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public WebServiceInbound getMainMsg() {
        return this.mainMsg;
    }
    
    public void setMainMsg(WebServiceInbound mainMsg) {
        if(mainMsg!=null){
            this.setParentId(mainMsg.getId());
        }
        else{
            this.setParentId(null);
        }
        this.mainMsg = mainMsg;
    }




}

