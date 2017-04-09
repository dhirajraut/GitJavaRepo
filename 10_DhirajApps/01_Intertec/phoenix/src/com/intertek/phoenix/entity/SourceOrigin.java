/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * Source of submission of a job order.
 * 
 * @author richard.qin
 */
public enum SourceOrigin implements EnumField{
    EML ("Email"),
    IM ("IM"),
    WEB ("Web"),
    PHN ("Phone"),
    FAX("Fax"),
    MYCB("MyCB"),
    PTRO("Petro"),
    TRAN("Stand Ordr"),
    VRBL("Verbal"),
    WEBP("Web Phone"),
    WSAM("With Samp");
    
    private String desc;
    
    private SourceOrigin(String desc){
        this.desc = desc;
    }
    
    public String getDescription(){
        return this.desc;
    }
    
    public static SourceOrigin[] list(){
        return SourceOrigin.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return Integer.toString(this.ordinal());
    }

    @Override
    public String getName() {
        return name();
    } 

}
