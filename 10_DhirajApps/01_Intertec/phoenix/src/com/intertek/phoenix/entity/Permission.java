/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import com.intertek.phoenix.common.EnumField;

/**
 * @author eric.nguyen
 */
public enum Permission implements EnumField{
    PROJECT_TYPE_3("accessProjectType3", "Project Type 3");
    
    private final String permName;
    private final String desc;

    private Permission(String permName, String desc) {
        this.permName = permName;
        this.desc = desc;
    }

    public static Permission[] list(){
        return Permission.class.getEnumConstants();
    }

    @Override
    public String getValue() {
        return permName()+"";
    }

    @Override
    public String getName() {
        return name();
    }

    public String permName() {
        return permName;
    }

    @Override
    public String getDescription() {
        return desc;
    }
    
}
