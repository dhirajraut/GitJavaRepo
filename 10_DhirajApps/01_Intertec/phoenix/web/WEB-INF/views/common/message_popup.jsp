<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html" 
import="com.intertek.util.SecurityUtil" %>
<script language="javascript">
function subform()
{
var f=document.getElementById("sform").value;
if(f!='customerQuickCreateForm'&&f!='branchEditForm'&& f!='buEditForm'&&f!='customerEditForm')
{
top.document.forms[f].submit();
}
}
</script>

<input type="hidden" name="sform" value=${searchForm} >
<p>Hello <%= SecurityUtil.getUser().getFirstName()%> <%= SecurityUtil.getUser().getLastName() %>:</p>
<a href="#" style="color:blue;" onclick="javascript:top.return_popup_search_result('${param1}', '${param2}');subform();top.hidePopupDiv('${param3}','${param4}');top.popupboxclose();">${backUrlDescription} Please click here to return the saved value.
</a>

<br><br><br><br>

