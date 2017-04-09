<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
function submitSearch(pageNo)
{
document.JobTypeSearchForm.pageNo.value = pageNo;
document.JobTypeSearchForm.submit();
}	
</script>

<form:form name="JobTypeSearchForm" method="POST" action="search_job_type.htm">
<div style="color:red;">
<form:errors cssClass="error"/>
</div>
<form:hidden path="pageNo" />
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<!------------------------------------------------------------------MAIN CONTAINER------------------------------------------------------------------------>
<div id="MainContentContainer">
<!------------------------------------------------------------------TABS CONTENTS------------------------------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="jobTypeSearch"/></span></a></li>
</ul>
</div>
<!--------------------------------------------------------Sub Menus container. Do not remove----------------------------------------------------------->
<div id="tab_inner">
<!------------------------------------------------------------------TAB1 CONTAINER------------------------------------------------------------------------>
<div id="tab1" class="innercontent" >
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
<tr>
<th colspan="2"><f:message key="jobTypeSearch"/><img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"><a href="create_job_type.htm"><f:message key="addJobType"/></a></th>
</tr>
<tr>
<th colspan="2"><f:message key="jobTypeSearch"/></th>
</tr>
<tr>
<td width="20%" class="TDShade"><f:message key="jobType"/>:</td>
<td width="80%" class="TDShadeBlue">
<form:input cssClass="inputBox" maxlength="256" path="jobTypeCode.value" /> 
</td>
</tr>
<tr>
<td class="TDShade"><f:message key="description"/>:</td>
<td class="TDShadeBlue">
<form:input cssClass="inputBox" path="jobTypeDesc.value" /> 
</td>
</tr>

</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td><input name="Submit" type="submit" class="button1" value="<f:message key="search"/>"/></td>
<td style="text-align:right"></td>
</tr>
</table></td>
</tr>
</table>
<br>
<c:if test="${command.results != null}">
<div id="jobTypesearchresults"> 
<strong><f:message key="searchResults"/></strong>
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<th><span class="TDShade"><f:message key="jobType"/></span></th>

<th><span class="TDShade"><f:message key="description"/></span></th>
</tr>
<c:forEach items="${command.results}" var="jobType" varStatus="status">
<tr>
<td  width="45%"><a href="edit_job_type.htm?name=${jobType.jobTypeCode}">${jobType.jobTypeCode}</a></td>
<td  width="45%">${jobType.jobTypeDesc}</td>
</tr>
</c:forEach>
<tr>
<td>&nbsp;</td>
<td align="center">
<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
<a href="#" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
</c:forEach>
</td>
</tr>
<tr></tr>
</table>
</div>
</c:if>
</div>
<!------------------------------------------------------------------TAB1 CONTAINER END------------------------------------------------------------------->
</div>
</div>
<script type="text/javascript">
dolphintabs.init("tabnav", 0)
</script>
<!------------------------------------------------------------------TAB CONTENT END----------------------------------------------------------------------->
</div>
<!---------------------------------------------------------------MAIN CONTAINER END---------------------------------------------------------------------->
</td>
</tr>
</table>
</form:form>
