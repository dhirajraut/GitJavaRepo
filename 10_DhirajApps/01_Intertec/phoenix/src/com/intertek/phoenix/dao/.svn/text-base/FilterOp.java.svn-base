/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import com.intertek.phoenix.common.EnumField;

/**
 * Some common operators used in sql statements
 * 
 * @author richard.qin
 */
public enum FilterOp implements EnumField{
    EQUAL(0, "=", "Equal"),
    NOT_EQUAL(1, "<>", "Not Equal"),
    GREATER_THAN(2, ">", "Greater Than"),
    GREATER_OR_EQUAL(3, ">=", "Greater Than or Equal"),
    LESS_THAN(4, "<", "Less Than"),
    LESS_OR_EQUAL(5, "<=", "Less Than or Equal"),
    LIKE(6, "like", "Like"),
    IN(7, "in", "In"),
    NOT_IN(8, "not in", "Not In"),
    IS_NULL(9, "is null", "Null", OperatorType.UNARY),
    IS_NOT_NULL(10, "is not null", "Not Null", OperatorType.UNARY),
    EMPTY(11, "is empty", "Empty", OperatorType.UNARY),
    NOT_EMPTY(12, "is not empty", "Not Empty", OperatorType.UNARY),
    BETWEEN(13, "between", "Between", OperatorType.TERNARY),
    //no operator called begins with in oracle,so need to handle it in making criteria
    BEGINS_WITH(14, "Begins With", "Begins With")
    ;
    
    private int value;
    private String symbol;
    private String description;
    private OperatorType type;
    
    private FilterOp(int value, String symbol, String desc){
        this.value = value;
        this.symbol = symbol;
        this.description = desc;
        this.type = OperatorType.BINARY;
    }
    
    private FilterOp(int value, String symbol, String desc, OperatorType opType){
        this.value = value;
        this.symbol = symbol;
        this.description = desc;
        this.type = opType;
    }
    
    public int value(){
        return this.value;
    }
    
    public String symbol(){
        return this.symbol;
    }
    
    public OperatorType type() {
        return type;
    }
    
    @Override
    public String getDescription(){
        return this.description;
    }

    @Override
    public String getValue() {
        return symbol();
    }

    public static FilterOp[] list(){
        return FilterOp.class.getEnumConstants();
    }

    /**
     * @see com.intertek.phoenix.common.EnumField#getName()
     */
    @Override
    public String getName() {
        return name();
    }

}
