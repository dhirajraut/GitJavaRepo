/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author richard.qin
 */
public class DataTransformer {
    /**
     * Format an array of objects to a csv string
     * @param data
     * @param delimiter
     * @return
     */
    public static String toCsvString(Object[] data, String delimiter){
        StringBuilder sb = new StringBuilder();
        for(int k=0; k<data.length; k++){
            Object obj = data[k];
            sb.append(escapeString(obj, " ", "\t", "\r", "\n", ",", delimiter));
            if(k < data.length-1){
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    /**
     * Format an array of objects to an xml segment
     * @param header
     * @param data
     * @return
     */
    public static String toXmlSegment(String[] header, Object[] data){
        throw new UnsupportedOperationException("toXmlSegment() is not supported.");
    }
    
    private static String escapeString(Object obj, String... toEscape){
        String str = obj.toString();
        str = str.replace("\"", "'");
        
        for(String escape: toEscape){
            if(str.contains(escape)){
                return "\"" + str + "\"";
            }
        }
        return str;
    }
    
    /**
     * Flatten the nested string arrays for the given string array
     * 
     * @param raw An array of strings, which may contain some nest string arrays to any depth
     * @return an array of flattened strings
     */
    public static String[] flattenStrings(Object[] raw){
        List<String> list = new ArrayList<String>();
        for(Object obj: raw){
            if(obj == null){
                list.add("");
            }
            else if(obj.getClass().isArray()){
                String[] strings = flattenStrings((Object[])obj);
                list.add(toCsvString(strings, ","));
            }
            else {
                list.add(obj.toString());
            }
        }
        
        return list.toArray(new String[0]);
    }
}
