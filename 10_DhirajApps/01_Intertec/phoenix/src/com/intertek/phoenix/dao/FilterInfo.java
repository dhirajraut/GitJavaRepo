/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

/**
 * @author richard.qin
 */
public class FilterInfo {
    private String name;
    private String name2;
    private FilterOp op;
    private Object value;
    private boolean ignoreCase;

    public FilterInfo(){
        
    }
    /**
     * Construct a FilterInfo with a ternary operator.
     * @param name
     * @param name2
     * @param op
     * @param value
     */
    public FilterInfo(String name, String name2, FilterOp op, Object value){
        if(op.type() == OperatorType.TERNARY){
            this.name = name;
            this.op = op;
            this.value = value;
            this.name2 = name2;
        }
        else
            throw new RuntimeException("Invalid operator type, a ternary operator is expected: " + op.getDescription());
    }
    
    public FilterInfo(String name, String name2, FilterOp op, Object value, boolean ignoreCase){
        if(op.type() == OperatorType.TERNARY){
            this.name = name;
            this.op = op;
            this.value = value;
            this.name2 = name2;
            this.ignoreCase = ignoreCase;
        }
        else
            throw new RuntimeException("Invalid operator type, a ternary operator is expected: " + op.getDescription());
    }
    
    /**
     * Construct a FilterInfo with name, value and a binary operator
     * 
     * @param name
     * @param op
     * @param value
     */
    public FilterInfo(String name, FilterOp op, Object value) {
        if(op.type() == OperatorType.BINARY){
            this.name = name;
            this.op = op;
            this.value = value;
        }
        else
            throw new RuntimeException("Invalid operator type, a binary operator is expected: " + op.getDescription());
    }

    public FilterInfo(String name, FilterOp op, Object value, boolean ignoreCase) {
        if(op.type() == OperatorType.BINARY){
            this.name = name;
            this.op = op;
            this.value = value;
            this.ignoreCase = ignoreCase;
        }
        else
            throw new RuntimeException("Invalid operator type, a binary operator is expected: " + op.getDescription());
    }

    /**
     * Construct a FilterInfo with name and value, default operator to
     * FilterOp.EQUAL
     * 
     * @param name
     * @param value
     */
    public FilterInfo(String name, Object value) {
        this.name = name;
        this.op = FilterOp.EQUAL;
        this.value = value;
    }
    
    public FilterInfo(String name, Object value, boolean ignoreCase) {
        this.name = name;
        this.op = FilterOp.EQUAL;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }
    
    /**
     * Construct a FilterInfo with a unary operator.
     * @param name
     * @param op
     */
    public FilterInfo(String name, FilterOp op){
        if(op.type() == OperatorType.UNARY){
            this.name = name;
            this.op = op;
        }
        else
            throw new RuntimeException("Invalid operator type, a unary operator is expected: " + op.getDescription());
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the op
     */
    public FilterOp getOp() {
        return op;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    /**
     * 
     * @return
     */
    public String getName2() {
        return name2;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setName2(String name2) {
        this.name2 = name2;
    }
    
    public void setOp(FilterOp op) {
        this.op = op;
    }
    /**
     * Return the textual form of this filter info.
     * @see java.lang.Object#toString()
     */
    public String toString(){
        String result = null;
        switch(op.type()){
            case UNARY:
                result = this.name + " " + this.op.symbol();
                break;
            case BINARY:
                String val = this.value.toString();
                if(this.value.getClass().equals(String.class)){
                    if(this.op == FilterOp.LIKE){
                        val = " '%"  + this.value +  "%'";
                    }
                    else{
                        val = " '"  + this.value +  "'";
                    }
                }
                result = this.name + " " + this.op.symbol() + " " + val;
                break;
            case TERNARY:
                if (this.op == FilterOp.BETWEEN){
                    result = this.value + " " + this.op.symbol() + " " 
                           + this.name + " and " + this.name2;
                }
                break;
            default:
                break;
        }
        return result;
    }
}
