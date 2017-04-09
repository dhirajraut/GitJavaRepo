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
 * Types of Invoices
 * 
 * @author richard.qin
 */
public enum InvoiceType implements EnumField{
    NORMAL(1, "Normal"), 
    REBILL(2, "Rebill"),
    DRAFT(3, "Draft"),
    CREDIT(4, "Credit"),
    DEPOSIT(5, "Deposit")
    ;
    
    private final int type;
    private final String description;

    private InvoiceType(int type, String desc) {
        this.type = type;
        this.description = desc;
    }

    /**
     * @return the type
     */
    public int type() {
        return type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    public static InvoiceType[] list(){
        return InvoiceType.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return Integer.toString(type);
    }

    @Override
    public String getName() {
        return name();
    }
    
}
