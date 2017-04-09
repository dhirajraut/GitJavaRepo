/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This a simple implmentation of an Array backed Map. This main idea behind it
 * is that a user can replace the value set in a single operation. It is mainly
 * created for accessing tabular data by column names on a row by row basis.
 * 
 * In the future, this class should be promoted for more general use.
 * @author richard.qin
 *
 */
public class ArrayMap<K, V> implements Map<K, V> {
    private Map<K, Integer> index;
    private V[] values;
    
    public ArrayMap(K[] keys){
        this.index = new HashMap<K, Integer>();
        for(int k=0; k<keys.length; k++){
            index.put(keys[k], new Integer(k));
        }
    }
    
    public ArrayMap(K[] keys, V[] values){
        this.index = new HashMap<K, Integer>();
        for(int k=0; k<keys.length; k++){
            index.put(keys[k], new Integer(k));
        }
        
        this.values = values;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
        return index.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for(Object v: this.values){
            if(v.equals(value))
                return true;
        }
        return false;
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(Object key) {
        if(this.values == null)
            return null;
        
        Integer i = this.index.get(key);
        if(i == null)
            return null;
        
        return this.values[i];
    }

    @Override
    public boolean isEmpty() {
        return this.index.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return this.index.keySet();
    }

    @Override
    public V put(K key, V value) {
        if(this.values == null)
            return null;
        
        Integer i = this.index.get(key);
        if(i == null)
            return null;
        
        V old = this.values[i];
        this.values[i] = value;
        return old;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return this.index.size();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Return the underlying data array
     * 
     * @return the underlying data array. 
     */
    public V[] getValues(){
        return this.values;
    }
    
    /**
     * Set the underlying data values
     * @param values
     */
    public void setValues(V[] values){
        this.values = values;
    }
    
    /**
     * Get the index for the given key
     * @param key
     * @return
     */
    public int getIndex(Object key) {
        Integer i = this.index.get(key);
        if(i == null)
            return -1;
        
        return i;
    }
}
