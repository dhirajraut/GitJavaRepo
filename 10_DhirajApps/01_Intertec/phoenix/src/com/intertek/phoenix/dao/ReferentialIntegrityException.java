/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;


/**
 * 
 * @author richard.qin
 */
@SuppressWarnings("serial")
public class ReferentialIntegrityException extends DaoException {

    public ReferentialIntegrityException(){
        super();
    }
    
    public ReferentialIntegrityException(String message){
        super(message);
    }
}
