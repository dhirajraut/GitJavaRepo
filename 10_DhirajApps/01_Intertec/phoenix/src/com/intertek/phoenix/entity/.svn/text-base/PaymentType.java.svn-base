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
 * Type of payment.
 * 
 * @author richard.qin
 */
public enum PaymentType implements EnumField{
    NORMAL("Normal", "NML"), 
    DEPOSIT_INVOICE("Deposit Invoice", "DEP"),
    CASH("Cash", "COD"),
    CREDITCARD("Credit Card", "CRE"),
    CHECK("Check", "CHE"),
    EFT("EFT", "ETF"),
    ON_ACCOUNT("On Account", "OAC");

    private final String description;
    private final String value;
    
    private PaymentType(String desc, String value) {
        this.description = desc;
        this.value=value;
    }

    public String value(){
        return Integer.toString(this.ordinal());
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }
    
    public static PaymentType[] list(){
        return PaymentType.class.getEnumConstants();
    }

    public static PaymentType getPaymentType(String value) {
        PaymentType[] paymentTypes = list();

        for (PaymentType paymentType : paymentTypes) {
            if (paymentType.value.equals(value)) {
                return paymentType;
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return name();
    } 
}
