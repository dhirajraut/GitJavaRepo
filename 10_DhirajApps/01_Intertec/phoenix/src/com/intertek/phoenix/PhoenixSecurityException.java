/**
 * 
 */
package com.intertek.phoenix;

/**
 * The base class for all security related Exceptions in Phoenix.
 * 
 * @author richard.qin
 */
@SuppressWarnings("serial")
public class PhoenixSecurityException extends RuntimeException {
    
    public PhoenixSecurityException(){
    }
    
    public PhoenixSecurityException(Throwable cause){
        super(cause);
    }
    
    public PhoenixSecurityException(String message){
        super(message);
    }
    
    public PhoenixSecurityException(String message, Throwable cause){
        super(message, cause);
    }
}
