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
 * The status of a Job Order. 
 * <p>The values are copies from the exising phoenix.
 * 
 * @author richard.qin
 */
public enum OrderStatus implements EnumField{
    NEW(0, "New"),
    CANCELED(1, "Canceled"),
    OPEN(1000, "Open"),
    CLOSED (6000, "Closed"),
    REOPENED (7000, "Reopened"),
    // above in phoenix 1
    HOLD(2, "Hold"),
    BOOKED(3, "Booked"),
    BOOKED_PENDING(4, "Booked-Pending")
    ;

    private final int code;
    private final String description;

    private OrderStatus(int code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public int status() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static OrderStatus[] list(){
        return OrderStatus.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return Integer.toString(code);
    }

    @Override
    public String getName() {
        return name();
    } 
}
