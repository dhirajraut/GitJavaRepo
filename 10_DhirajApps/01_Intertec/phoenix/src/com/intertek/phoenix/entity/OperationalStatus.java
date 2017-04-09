/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * Operational status for a CEJobOrder.
 * <p> the values are taken from the mockup, need to be finalized.
 * 
 * @author richard.qin
 */
public enum OperationalStatus implements EnumField{
    Scheduled(1, "Scheduled"), 
    Unscheduled(2, "Unscheduled"),
    HOLD(3, "Hold"),
    AccrualPriorMonth(4, "AccrualPriorMonth"),
    Completed(5, "Completed"),
    DataCollected(6, "DataCollected"),
    Fail(7, "Fail"),
    InProcess(8, "InProcess"),
    NotApplicable(9, "NotApplicable"),
    Pass(10, "Pass"),
    Pending(11, "Pending"),
    Subcontract(12, "Subcontract"),
    ;
    
    
    private final int statusCode;
    private final String description;

    private OperationalStatus(int code, String desc) {
        this.statusCode = code;
        this.description = desc;
    }

    public int status() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
    
    public static OperationalStatus[] list(){
        return OperationalStatus.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return status()+"";
    }

    @Override
    public String getName() {
        return name();
    }     
}
