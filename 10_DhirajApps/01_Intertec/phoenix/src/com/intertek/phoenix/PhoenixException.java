/**
 * 
 */
package com.intertek.phoenix;

/**
 * The base type of all Phoenix exceptions, except security related
 * 
 * @author richard.qin
 */
public class PhoenixException extends Exception {

    /**
     * Auto generated version uid
     */
    private static final long serialVersionUID = -201906543439131658L;

    public PhoenixException(){
    }
    
    public PhoenixException(Throwable cause){
        super(cause);
    }
    
    public PhoenixException(String message, Throwable cause){
        super(message, cause);
    }
    
    public PhoenixException(String message){
        super(message);
    }
}
