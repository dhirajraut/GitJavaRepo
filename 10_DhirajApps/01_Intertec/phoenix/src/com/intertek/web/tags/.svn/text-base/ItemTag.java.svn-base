package com.intertek.web.tags;

import javax.servlet.jsp.JspException;

public class ItemTag extends VarTagSupport {
    private Object itemValue;

    public void setValue(Object value) throws JspException {
        itemValue = value;
    }

    public void doTag() throws JspException {
        ListTag ancestor = (ListTag) findAncestorWithClass(
            this, ListTag.class);
        if (ancestor == null)
            throw new JspException(
                "Couldn't find 'list' ancestor for 'item'");
        if (itemValue == null)
            itemValue = invokeBody();
        ancestor.add(itemValue);
    }

}
