/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import com.intertek.phoenix.common.EnumField;

/**
 * Logical operators used in the sql statements.
 * 
 * @author richard.qin
 */
public enum LogicOp implements EnumField{
    AND(0, "and"),
    OR(1, "or");
    
    private int value;
    private String symbol;
    
    private LogicOp(int value, String symbol){
        this.value = value;
        this.symbol = symbol;
    }
    
    public int value(){
        return value;
    }
    
    public String symbol(){
        return symbol;
    }
    
    public static LogicOp[] list(){
        return LogicOp.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return value()+"";
    }

    @Override
    public String getDescription() {
        return symbol();
    }

    @Override
    public String getName() {
        return name();
    }    
}
