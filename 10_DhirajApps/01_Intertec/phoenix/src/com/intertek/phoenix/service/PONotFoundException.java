/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.service;

import com.intertek.phoenix.PhoenixException;

/**
 * Exception: Purchase Order not found
 * 
 * @author rautsmit
 * 
 */
@SuppressWarnings("serial")
public class PONotFoundException extends PhoenixException {

    public PONotFoundException(String message) {
        super(message);
    }

    public PONotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
