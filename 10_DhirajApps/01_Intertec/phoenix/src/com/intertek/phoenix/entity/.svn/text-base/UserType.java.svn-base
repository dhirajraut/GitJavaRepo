package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * User type used in TimeCostEstimation.
 * @author lily.sun
 */

public enum UserType implements EnumField{
    ENGINEER("Engineer"),
    ADMIN("Admin"),
    TECHNICIAN("Technician");
    
    private String desc;

    UserType(String desc){
        this.desc = desc;
    }

    public static UserType[] list(){
        return UserType.class.getEnumConstants();
    }
    
    @Override
    public String getValue() {
        return Integer.toString(this.ordinal());
    }

    @Override
    public String getName() {
        return name();
    } 

    @Override
    public String getDescription() {
        return desc;
    }     
}