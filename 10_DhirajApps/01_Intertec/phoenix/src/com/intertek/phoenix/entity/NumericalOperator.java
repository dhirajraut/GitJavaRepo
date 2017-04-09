/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

    public enum NumericalOperator {
        Greaterthan(0, ">"), 
        Scheduled(1, "<"),
        Unscheduled(2, "=");
    
    
    private final int operator;
    private final String description;


    private NumericalOperator(int operator, String desc) {
        this.operator = operator;
        this.description = desc;
    }

    public int operator() {
        return operator;
    }

    public String description() {
        return description;
    }
  }

