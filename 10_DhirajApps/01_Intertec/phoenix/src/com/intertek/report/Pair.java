/**
 * 
 */
package com.intertek.report;

/**
 * A simple name / value pair class
 * @author richard.qin
 */
public class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
    
    public K key(){
        return this.key;
    }
    
    public V value(){
        return this.value;
    }
    
    public String toString(){
        return key + "=" + value;
    }
}
