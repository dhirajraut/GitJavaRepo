/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.util;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;


/**
 * ArrayMapGrid encapsulate a tabular data holder with headers. Data in the grid is to be accessed
 * on row-by-row basis, using hearder names as lookup key. For example, to access the first row data,
 * call next() once and then get(key). Alternative to next(), moveTo() can also been called.
 * The class is designed to best support row-oriented data access. Once on the right row, the data
 * can be accessed just like a Map.
 * 
 * Internally, it uses two data structures: an ArrayMap and a List of Object[]. 
 * 
 * @author richard.qin
 */
public class ArrayMapGrid {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(ArrayMapGrid.class);
    
    protected ArrayMap<String, Object> map;
    protected List<Object[]> dataset;
    protected int current;

    public ArrayMapGrid(ArrayMap<String, Object> am, List<Object[]> dataset){
        this.map = am;
        this.dataset = dataset;
        this.current = -1;
    }
    
    /**
     * Return a duplicated ArrayMapGrid that shares the same underlying dataset. The new
     * ArrayMapGrid will be positioned before the first row of the dataset.
     * @param host owner of the deplicated ArrayMapDataSource
     * @param params Map of parameters
     * @return a new ArrayMapDataSource object that shares the same dataset.
     */
    public ArrayMapGrid duplicate(){
        ArrayMapGrid newGrid = new ArrayMapGrid(this.map, this.dataset);
        newGrid.current = -1;

        return newGrid;
    }
    
    public boolean moveNext() {
        this.current++;
        
        if(current >= this.dataset.size()){
            return false;
        }
        
        Object[] values = (Object[])this.dataset.get(this.current);
        this.map.setValues(values);
        
        return true;
    }
    
    public ArrayMapGrid moveTo(int index){
        if(index >=0 && index < this.dataset.size()){
            Object[] values = (Object[])this.dataset.get(index);
            this.map.setValues(values);
            return this;
        }
        throw new RuntimeException("Invalid operation, index out of range");
    }
    
    public Set<String> getFieldNames(){
        return this.map.keySet();
    }

    public Object getFieldValue(String name){
        Object value = this.map.get(name);
        return value;
    }
    
    public int getColumnCount(){
        return map.size();
    }
    
    public int getRecordCount(){
        return dataset.size();
    }
    
    public int getFieldIndex(String fieldName){
        return map.getIndex(fieldName);
    }
    
    public ArrayMap<String, Object> getArrayMap(){
        return this.map;
    }
    
    public List<Object[]> getDataset(){
        return this.dataset;
    }
    
    /**
     * Reset the grid position
     */
    public void reset() {
        this.current = 0;
    }
    
    /**
     * Return the current row of data
     * @return
     */
    public Object[] getFields(){
        return this.map.getValues();
    }
}
