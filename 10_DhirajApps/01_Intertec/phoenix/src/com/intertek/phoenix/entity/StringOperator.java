/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

    public enum StringOperator {
        match(0, "="), 
        Beginwith(1, "begins with"),
        Contains(2, "contains"),
        notmatch(3, "not =");
    
    
    private final int value;
    private final String description;


    private StringOperator(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    public int value() {
        return value;
    }

    public String description() {
        return description;
    }
  }

