/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * 
 * @author richard.qin
 */
public enum InvoiceGenerationType implements EnumField {
    REGULAR(1, "Regular"), 
    CONSOLIDATED(2, "Consolidated");

    private final int code;
    private final String description;

    private InvoiceGenerationType(int code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public String getValue() {
        return Integer.toString(this.code);
    }

    public String getDescription() {
        return description;
    }
    
    public String getName(){
        return name();
    }

    public static InvoiceGenerationType[] list() {
        return InvoiceGenerationType.class.getEnumConstants();
    }

    public static InvoiceGenerationType getIvoiceGenerationType(String code) {
        InvoiceGenerationType[] types = list();

        for (InvoiceGenerationType type : types) {
            if (type.getValue().equals(code)) {
                return type;
            }
        }

        return null;
    }
}
