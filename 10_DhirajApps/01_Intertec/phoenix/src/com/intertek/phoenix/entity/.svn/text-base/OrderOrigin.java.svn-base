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
 * Enum type for the origin of a job order.
 * 
 * @author richard.qin
 */
public enum OrderOrigin implements EnumField{
    ECS(1, "ECS"),
    PHOENIX (2, "Phoenix"),
    DC_APP (3, "DC App");
    
    private final int originCode;
    private final String description;

    private OrderOrigin(int code, String desc) {
        this.originCode = code;
        this.description = desc;
    }

    public int originCode() {
        return originCode;
    }

    public String getDescription() {
        return description;
    }
    
    public static OrderOrigin[] list(){
        return OrderOrigin.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return originCode()+"";
    }

    @Override
    public String getName() {
        return name();
    }
    
}
