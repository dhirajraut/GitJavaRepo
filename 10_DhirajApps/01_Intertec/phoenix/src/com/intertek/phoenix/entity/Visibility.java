/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * An attribute to Notes. 
 * 
 * @author richard.qin
 */
public enum Visibility implements EnumField{
    EVERYWHERE ("Everywhere"),
    INTERNAL ("Internal"),
    EXTERNAL ("External");
    
    private String description;
    
    private Visibility(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public static Visibility[] list(){
        return Visibility.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return Integer.toString(this.ordinal());
    }

    @Override
    public String getName() {
        return name();
    } 
}
