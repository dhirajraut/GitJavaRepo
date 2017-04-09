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
public enum DepositType implements EnumField {
    WIRE(0, "Wire"), 
    CC(1, "Credit Card"), 
    CASH(2, "Cash"), ;

    private int value;
    private String description;

    DepositType(int value, String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String getValue() {
        return Integer.toString(this.value);
    }

    public static DepositType[] list() {
        return DepositType.class.getEnumConstants();
    }

    @Override
    public String getName() {
        return name();
    }

}
