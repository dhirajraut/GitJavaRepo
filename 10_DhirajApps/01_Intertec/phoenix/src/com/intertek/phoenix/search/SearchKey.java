package com.intertek.phoenix.search;

import com.intertek.phoenix.common.EnumField;

public enum SearchKey implements EnumField{
    JobId("Job ID"),
    Invoice("Invoice"),
    Quote("Quote"),
    ECSOrder("ECS Order");
    
    private String description;

    SearchKey(String desc){
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getValue() {
        return getName();
    }
}
