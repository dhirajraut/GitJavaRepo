package com.intertek.web.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.search.SearchKey;

public class SearchTag extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {

        ReferenceDataService referenceDataService = (ReferenceDataService) ServiceLocator.getInstance().getBean("referenceDataService");
        List<SearchKey> searchFields = referenceDataService.getsearchFields();

        StringBuffer sb = new StringBuffer();
        sb.append("<table width=\"250\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
        sb.append("<tr>");
        sb.append("<td style=\"padding-left:10px; border-left:#999999 solid 1px;\">");
        sb.append("<label>");
        sb.append("<select name=\"sel1\" id=\"sel1\" class=\"selectionBox\" >");
        for (SearchKey key : searchFields) {
            sb.append("<option value=" + key.getName() + ">" + key.getDescription() + "</option>");
        }
        sb.append("</select>");
        sb.append("</label>");
        sb.append("</td>");
        sb.append("<td>");
        sb.append("<label>");
        sb.append("<input id=\"searchValue\" name=\"textfield\" type=\"text\" class=\"inputBox\" size=\"13\">");
        sb.append("</label>");
        sb.append("</td>");
        sb.append("<td>");
        sb.append("<label>");
        sb.append("<input name=\"Submit\" type=\"submit\" class=\"button1\" value=\"Search\" "
                  + "style=\"border-width:1px;\" onclick=\"javascript:validateSearchField();\">");
        sb.append("</label>");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</table>");

        getJspContext().getOut().print(sb.toString());
    }

}
