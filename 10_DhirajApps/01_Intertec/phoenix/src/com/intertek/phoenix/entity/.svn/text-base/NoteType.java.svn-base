/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * Type of Notes.
 * 
 * @author richard.qin
 */
public enum NoteType implements EnumField{

    BILLING (1, "Billing"),
    COMMENT(2, "Comment"),
    CUSTOMER_CALL(3, "Customer Call"),
    INVOICE (4, "Invoice"),
    PRICING (5, "Pricing"),
    RESEARCH (6, "Research");
    
    private int value;
    private String description;
    private Visibility visibility;
    
    private NoteType(int value, String description){
        this.value = value;
        this.description = description;
        this.visibility = Visibility.EVERYWHERE;
    }
    
    private NoteType(int value, String description, Visibility visibility){
        this.value = value;
        this.description = description;
        this.visibility = visibility;
    }
    
    public int value(){
        return this.value;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public Visibility visibility(){
        return this.visibility;
    }
    
    public static NoteType[] list(){
        return NoteType.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return Integer.toString(value);
    }

    @Override
    public String getName() {
        return name();
    }     
}
