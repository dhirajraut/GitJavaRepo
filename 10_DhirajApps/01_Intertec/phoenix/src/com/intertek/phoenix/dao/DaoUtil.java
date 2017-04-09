/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.intertek.phoenix.util.CommonUtil;

/**
 * This class contains some useful method can be used by various
 * Dao implementation classes.
 * @author richard.qin
 */
public class DaoUtil {

    public static <T> QueryInfo exampleToQuery(T example) throws DaoException {
        QueryInfo query = new QueryInfo(example.getClass());
        List<Field> fields = new ArrayList<Field>();
        // get all the declared fields from this and super classes.
        CommonUtil.getAllDeclaredFields(fields, example.getClass());
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = field.get(example);
                if (fieldValue != null) {
                    // if field is a simple type, including primitives, check if is null/0 equivalent
                    Class<?> fieldCls = field.getType();
                    if(!CommonUtil.isSimpleType(fieldCls) || isValueNullEquivalent(fieldValue)){
                        continue;
                    }
                    query.addFilter(field.getName(), fieldValue);
                }
            }
            return query;
        }
        catch (IllegalArgumentException e) {
            throw new DaoException("Error occured when querying by example: " + example.getClass().getSimpleName(), e);
        }
        catch (IllegalAccessException e) {
            throw new DaoException("Error occured when querying by example: " + example.getClass().getSimpleName(), e);
        }
    }
    
    public static boolean isValueNullEquivalent(Object value){
        if(value == null){
            return true;
        }
        else{
            Class<?> cls = value.getClass();
            if(Number.class.isAssignableFrom(cls) && ((Number)value).doubleValue() == 0.0){
                return true;
            }
            else if(Boolean.class.equals(cls) && ((Boolean)value).booleanValue() == false){
                return true;
            }
            else {
                return false;
            }
        }
    }
    
}
