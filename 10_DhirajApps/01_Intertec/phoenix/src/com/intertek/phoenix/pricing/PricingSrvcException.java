/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;

import com.intertek.phoenix.PhoenixException;

/**
 * 
 * @author richard.qin
 */
@SuppressWarnings("serial")
public class PricingSrvcException extends PhoenixException {
    
    public PricingSrvcException(String message, Throwable t){
        super(message, t);
    }
    
    public PricingSrvcException(String message){
        super(message);
    }
}
