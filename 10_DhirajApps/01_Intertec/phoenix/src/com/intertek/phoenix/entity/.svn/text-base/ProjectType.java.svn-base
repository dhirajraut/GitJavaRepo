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
 * Project type as specified in the requirement doc.
 * 
 * @author richard.qin
 */
public enum ProjectType implements EnumField{
    TYPE_1("Type 1", "TP1"), 
    TYPE_2("Type 2", "TP2"), 
    TYPE_3("Type 3", "TP3");

    private final String description;
    private final String value;
    
    private ProjectType(String desc, String value) {
        this.description = desc;
        this.value=value;
    }

    public String getDescription() {
        return description;
    }
    
    public static ProjectType[] list(){
        return ProjectType.class.getEnumConstants();
    }

    public String getType() {
        return value;
    } 
    
    @Override
    public String getValue() {
        return name();//Integer.toString(this.ordinal());
    } 

    @Override
    public String getName() {
        return name();
    } 
}
