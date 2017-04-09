package com.intertek.entity;
// Generated by Hibernate Tools 3.2.0.beta8


import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * LogConfigDetail generated by hbm2java
 */
public class LogConfigDetail  implements java.io.Serializable {

    // Fields    

     private LogConfigDetailId logConfigDetailId;
     private LogConfigList logConfigList;
     private String dbColName;
     private String colName;
     private Boolean displayStatus;
     private Integer displayOrder;
     private Integer sortOrder;
     private Integer freezeOrder;

     // Constructors

    /** default constructor */
    public LogConfigDetail() {
    }

	/** minimal constructor */
    public LogConfigDetail(LogConfigDetailId logConfigDetailId) {
        this.logConfigDetailId = logConfigDetailId;
    }
    /** full constructor */
    public LogConfigDetail(LogConfigDetailId logConfigDetailId, LogConfigList logConfigList, String dbColName, String colName, Boolean displayStatus, Integer displayOrder, Integer sortOrder, Integer freezeOrder) {
       this.logConfigDetailId = logConfigDetailId;
       this.logConfigList = logConfigList;
       this.dbColName = dbColName;
       this.colName = colName;
       this.displayStatus = displayStatus;
       this.displayOrder = displayOrder;
       this.sortOrder = sortOrder;
       this.freezeOrder = freezeOrder;
    }
   
    // Property accessors
    public LogConfigDetailId getLogConfigDetailId() {
        return this.logConfigDetailId;
    }
    
    public void setLogConfigDetailId(LogConfigDetailId logConfigDetailId) {
        this.logConfigDetailId = logConfigDetailId;
    }
    public LogConfigList getLogConfigList() {
        return this.logConfigList;
    }
    
    public void setLogConfigList(LogConfigList logConfigList) {
        this.logConfigList = logConfigList;
    }
    public String getDbColName() {
        return this.dbColName;
    }
    
    public void setDbColName(String dbColName) {
        this.dbColName = dbColName;
    }
    public String getColName() {
        return this.colName;
    }
    
    public void setColName(String colName) {
        this.colName = colName;
    }
    public Boolean getDisplayStatus() {
        return this.displayStatus;
    }
    
    public void setDisplayStatus(Boolean displayStatus) {
        this.displayStatus = displayStatus;
    }
    public Integer getDisplayOrder() {
        return this.displayOrder;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    public Integer getSortOrder() {
        return this.sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Integer getFreezeOrder() {
        return this.freezeOrder;
    }
    
    public void setFreezeOrder(Integer freezeOrder) {
        this.freezeOrder = freezeOrder;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LogConfigDetail) ) return false;
		 LogConfigDetail castOther = ( LogConfigDetail ) other; 
         
		 return ( (this.getLogConfigDetailId()==castOther.getLogConfigDetailId()) || ( this.getLogConfigDetailId()!=null && castOther.getLogConfigDetailId()!=null && this.getLogConfigDetailId().equals(castOther.getLogConfigDetailId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogConfigDetailId() == null ? 0 : this.getLogConfigDetailId().hashCode() );
         
         
         
         
         
         
         
         return result;
   }   


}

