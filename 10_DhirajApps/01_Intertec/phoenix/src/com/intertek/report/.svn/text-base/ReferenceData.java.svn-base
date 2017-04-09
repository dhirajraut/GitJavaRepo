package com.intertek.report;

import java.util.HashMap;
import java.util.Map;

public class ReferenceData {
    private ArrayMapDataSource dataset;
    private Map<String, Integer> keyMap;
    
    public ReferenceData(ArrayMapDataSource dataset, String[] keyFieldNames){
        this.dataset = dataset;
        keyMap = new HashMap<String, Integer>();
        int count = 0;
        
        try{
            while(dataset.next()){
                StringBuilder sb = new StringBuilder();
                for(String key: keyFieldNames){
                    Object v = dataset.getFieldValue(key);
                    if(v == null){
                        sb.append('|');
                    }
                    else{
                        sb.append(v.toString().trim()).append('|');
                    }
                }
                keyMap.put(sb.toString(), count++);
            }
        }
        catch(Exception e){
            
        }
    }
    
    public Object getReferenceValue(Object[] keys, String fieldName){
        StringBuilder sb = new StringBuilder();
        
        for(Object k: keys){
            if(k == null){
                sb.append('|');
            }
            else{
                sb.append(k.toString().trim()).append('|');
            }
        }
        // find record
        int index = keyMap.get(sb.toString());
        // get field 
        Object result = dataset.moveTo(index).getFieldValue(fieldName);
        
        return result;
    }
}
