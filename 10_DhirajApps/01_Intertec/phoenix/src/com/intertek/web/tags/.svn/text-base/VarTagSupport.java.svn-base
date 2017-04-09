package com.intertek.web.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class VarTagSupport extends SimpleTagSupport {
    protected String varName;
    protected int varScope;

    protected VarTagSupport() {
        varScope = PageContext.PAGE_SCOPE;
    }

    public void setVar(String name) throws JspException {
        varName = name;
    }

    public void setScope(String scope) throws JspException {
        if (scope.equalsIgnoreCase("page"))
            varScope = PageContext.PAGE_SCOPE;
        else if (scope.equalsIgnoreCase("request"))
            varScope = PageContext.REQUEST_SCOPE;
        else if (scope.equalsIgnoreCase("session"))
            varScope = PageContext.SESSION_SCOPE;
        else if (scope.equalsIgnoreCase("application"))
            varScope = PageContext.APPLICATION_SCOPE;
        else
            throw new JspException("Invalid scope: " + scope);
    }

    protected boolean export(Object value) {
        if (varName == null)
            return false;
        JspContext jspContext = getJspContext();
        if (value != null)
            jspContext.setAttribute(varName, value, varScope);
        else
            jspContext.removeAttribute(varName, varScope);
        return true;
    }

    protected String invokeBody() throws JspException {
        JspFragment body = getJspBody();
        StringWriter buffer = new StringWriter();
        try {
            body.invoke(buffer);
        } catch (IOException x) {
            throw new JspException(x);
        }
        return buffer.toString();
    }

    protected void write(String str) throws JspException {
        JspContext jspContext = getJspContext();
        JspWriter out = jspContext.getOut();
        try {
            out.write(str);
        } catch (IOException x) {
            throw new JspException(x);
        }
    }

}
