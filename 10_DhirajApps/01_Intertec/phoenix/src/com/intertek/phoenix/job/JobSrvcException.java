/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.job;

import com.intertek.phoenix.PhoenixException;

/**
 * 
 * @author richard.qin
 */
@SuppressWarnings("serial")
public class JobSrvcException extends PhoenixException {
    public JobSrvcException(String message){
        super(message);
    }
    
    public JobSrvcException(String message, Throwable t){
        super(message, t);
    }
}
