package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * Unit of measurement.
 * @author lily.sun
 */

public enum UOM implements EnumField{
    EACH("Each"),
    HOUR("Hour"),
    PER_DAY("Per Day"),
    SHIFT("Shift");
    
    private String desc;

    UOM(String desc){
        this.desc = desc;
    }

    public static UOM[] list(){
        return UOM.class.getEnumConstants();
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


