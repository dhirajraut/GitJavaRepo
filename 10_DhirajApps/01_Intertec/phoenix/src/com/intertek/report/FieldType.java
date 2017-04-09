/**
 * 
 */
package com.intertek.report;


/**
 * A simple enum type to represent java sql types
 * @author richard.qin
 */
public enum FieldType {
    String (java.lang.String.class),
    BigDecimal (java.math.BigDecimal.class),
    Timestamp (java.sql.Timestamp.class);
    
    private Class<?> cls;
    
    FieldType(Class<?> cls){
        this.cls = cls;
    }
    
    Class<?> type(){
        return cls;
    }
}
