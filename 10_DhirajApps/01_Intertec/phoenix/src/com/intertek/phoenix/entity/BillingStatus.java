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
 * An enum type of BillingStatus for CEJobOrderLineItem. 
 *
 * TODO Details to be completed
 * @author richard.qin
 */
public enum BillingStatus implements EnumField{
    NOT_INVOICEABLE(1, "Not Invoiceable"),
    CANCELED(2, "Canceled"),
    OPEN(1000, "Open"),
    INVOICED(6000, "Invoiced"),
    REBILLED(7100, "Rebilled"),
    CREDITED(7200, "Credited"),
    PartialledBilled(7300, "PartialledBilled"),
    COMPLETED(3000, "Complete"),
    ;

    private final int code;
    private final String description;

    private BillingStatus(int code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public int status() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static BillingStatus[] list(){
        return BillingStatus.class.getEnumConstants();
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
