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
 * 
 *
 * @author richard.qin
 */
public enum InvoiceStatus implements EnumField{
    NEW(1, "NEW"), 
    INVOICED(2, "Invioced"),
    PAID(3, "Paid"),
    CLOSED (4, "Closed"),
    CREDITED (5, "Credited"),
    ;

    private final int code;
    private final String description;

    private InvoiceStatus(int code, String desc) {
        this.code = code;
        this.description = desc;
    }

    /**
     * @return the status
     */
    public int status() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static InvoiceStatus[] list(){
        return InvoiceStatus.class.getEnumConstants();
    }

    public static InvoiceStatus getInvoiceStatus(String value) {
        InvoiceStatus[] invoiceStatuses = list();

        for (InvoiceStatus invoiceStatus : invoiceStatuses) {
            if (invoiceStatus.getValue().equals(value)) {
                return invoiceStatus;
            }
        }

        return null;
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
