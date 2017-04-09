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
 * Payment status for an invoice.
 * Note, this class may be removed if it proves to be redundant.
 * TODO check again
 * 
 * @author richard.qin
 */
public enum PaymentStatus implements EnumField{
    NOT_PAID("Not Paid"), 
    PAID("Paid");

    private final String description;

    private PaymentStatus(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
    
    public static PaymentStatus[] list(){
        return PaymentStatus.class.getEnumConstants();
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
