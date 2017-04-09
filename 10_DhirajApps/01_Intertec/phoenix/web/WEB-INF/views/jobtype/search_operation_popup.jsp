<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script >
function submitSearch(pageNumber){	 													 
document.operationSearchPopupForm.pageNumber.value = pageNumber;
document.operationSearchPopupForm.submit();
}	

function submitform(){
var name=document.getElementById("searchForm").value;
top.document.forms[name].operationFlag.value="newval";
if(name!='')
top.document.forms[name].submit();
else
return 			
}
function sortOperationByCode(){
document.operationSearchPopupForm.pageNumber.value = "1";
document.operationSearchPopupForm.sortFlag.value = "operationCode";
document.operationSearchPopupForm.submit();
}
function sortOperationByDesc(){
document.operationSearchPopupForm.pageNumber.value = "1";
document.operationSearchPopupForm.sortFlag.value = "description";
document.operationSearchPopupForm.submit();
}

function submitFunction()
{
	document.operationSearchPopupForm.pageNumber.value = "1";
	document.operationSearchPopupForm.sortFlag.value = "";
	document.operationSearchPopupForm.submit();
}

</script>

<form:form name="operationSearchPopupForm" method="POST" action="search_operation_popup.htm">

	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
    <form:hidden path="rowNum" />
	<form:hidden path="sortFlag" />
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;"><form:errors cssClass="error"/></div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							<th colspan="2"><f:message key="operationsSearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="operationCode"/>: </td>
							<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="operationCode.value" />
							<form:errors path="operationCode.value" cssClass="redstar"/>	
							</td>
							</tr>
							<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="operationDescription"/>: </td>
							<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="description.value" />
							<form:errors path="description.value" cssClass="redstar"/>	
							</td>
					</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td ><input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>"  /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					 <br />
<c:if test="${command.results != null}">
<div id="operationsearchresults"> 
<strong><f:message key="searchResults"/></strong>
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<th><a href="#start" onClick="sortOperationByCode();" ><span class="TDShade"><f:message key="operationCode"/></span></a></th>
<th><a href="#start" onClick="sortOperationByDesc();" ><span class="TDShade"><f:message key="operationDescription"/></span></a></th>
</tr>
<c:forEach items="${command.results}" var="operation" varStatus="status">
<c:choose>
<c:when test="${status.index%2==0}">
<tr style="background-color:#FFFFFF;">
</c:when>
<c:otherwise>
<tr style="background-color:#e7eeff;">                    
</c:otherwise>
</c:choose>
<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${operation.operationCode}');top.hidePopupDiv('operationlist${command.rowNum}','operationlistbody${command.rowNum}');top.popupboxclose();submitform();">${operation.operationCode}</a></td>
<td>${operation.description}</td>			
</tr>
</c:forEach>
<tr>
<td>&nbsp;</td>					
<td align="center">
<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
</c:forEach>
</td>
</tr>			
<tr></tr>
</table>
</div>
</c:if>	
</div>
</td>
</tr>
</table>
</form:form>		  