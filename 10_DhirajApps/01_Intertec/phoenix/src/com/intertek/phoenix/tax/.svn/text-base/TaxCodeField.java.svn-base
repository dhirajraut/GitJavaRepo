/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.tax;

import com.intertek.phoenix.common.EnumField;

/**
 * 
 * @author richard.qin
 */
public class TaxCodeField implements EnumField {

    private String taxCode;
    private String description;
    
    public TaxCodeField(String taxCode, String description){
        this.taxCode = taxCode;
        this.description = description;
    }
    
    /**
     * @see com.intertek.phoenix.common.EnumField#getDescription()
     */
    @Override
    public String getDescription() {
        if(description != null){
            return taxCode + " - " + description;
        }
        return taxCode;
    }

    /**
     * @see com.intertek.phoenix.common.EnumField#getName()
     */
    @Override
    public String getName() {
        return taxCode;
    }

    /**
     * @see com.intertek.phoenix.common.EnumField#getValue()
     */
    @Override
    public String getValue() {
        return taxCode;
    }

}
