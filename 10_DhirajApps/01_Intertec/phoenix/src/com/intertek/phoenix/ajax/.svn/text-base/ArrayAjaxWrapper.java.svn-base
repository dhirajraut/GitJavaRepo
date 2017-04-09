package com.intertek.phoenix.ajax;


public class ArrayAjaxWrapper implements AjaxWrapper {

    private Object[] obj;

    public ArrayAjaxWrapper() {
    }

    @Override
    public String getValue() {
        return obj[1]==null?"":obj[0].toString();
    }

    @Override
    public String getText() {
        return obj[1]==null?"":obj[1].toString();
    }

    @Override
    public void setObject(Object obj) {
        this.obj=(Object[])obj;
    }

}
