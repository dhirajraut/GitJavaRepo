<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
function submitSearch(pageNo){
document.OperationSearchForm.pageNo.value = pageNo;
document.OperationSearchForm.oxcel.value="false";
document.OperationSearchForm.submitFlag.value="true";
document.OperationSearchForm.submit();
}
function sortOperationByCode(){
document.OperationSearchForm.pageNo.value = "1";
document.OperationSearchForm.sortFlag.value = "operationCode";
document.OperationSearchForm.oxcel.value="false";
document.OperationSearchForm.submit();
}
function sortOperationByDesc(){
document.OperationSearchForm.pageNo.value = "1";
document.OperationSearchForm.sortFlag.value = "description";
document.OperationSearchForm.oxcel.value="false";
document.OperationSearchForm.submit();
}
function sortOperationByStatus(){
document.OperationSearchForm.pageNo.value = "1";
document.OperationSearchForm.sortFlag.value = "status";
document.OperationSearchForm.oxcel.value="false";
document.OperationSearchForm.submit();
}


function submitFunction(){
document.OperationSearchForm.pageNo.value = "1";
document.OperationSearchForm.sortFlag.value = "";
document.OperationSearchForm.oxcel.value="false";
document.OperationSearchForm.submitFlag.value="true";
document.OperationSearchForm.submit();
}

function submitxcel(){
document.OperationSearchForm.oxcel.value="true";
document.OperationSearchForm.sortFlag.value = "";
top.document.OperationSearchForm.submit();
}
</script>
<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<form:form name="OperationSearchForm" method="POST" action="search_operations.htm">
<div style="color:red;"><form:errors cssClass="error"/></div>
<form:hidden path="pageNo" />
<form:hidden path="sortFlag" />
<form:hidden path="submitFlag"/>
  <input type="hidden" name="oxcel" value="false"/>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<!----------------------------------------------MAIN CONTAINER----------------------------------------------------->
<div id="MainContentContainer">
<!------------------------------------------------TABS CONTENTS---------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="operationsSearch"/></span></a></li>
</ul>
</div>
<!--------------------------------Sub Menus container. Do not remove----------------------------------------------->
<div id="tab_inner">
<!-----------------------------------------TAB1 CONTAINER---------------------------------------------------------->
<div id="tab1" class="innercontent" >
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
<%--<tr>
<th colspan="2"><f:message key="operationsSearch"/><img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px; margin-right:5px;"></th>
</tr>--%>
<tr>
<th colspan="2"><f:message key="operationsSearch"/></th>
</tr>
<tr>
<td width="20%" class="TDShade"><f:message key="operation"/>:</td>
<td width="80%" class="TDShadeBlue">
<form:input cssClass="inputBox" maxlength="256" path="operationCode.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
</td>
</tr>
<tr>
<td class="TDShade"><f:message key="description"/>:</td>
<td class="TDShadeBlue">
<form:input cssClass="inputBox" path="description.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
</td>
</tr>

		<tr>
		<td class="TDShade"><f:message key="status"/>:</td>
		<td class="TDShadeBlue">
		<form:select cssClass="selectionBox" path="status.value" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
		</td>
		</tr>



</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td ><input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>"  /></td>
 <td style="text-align:right;"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
</tr>
</table></td>
</tr>
</table>
<br>
<c:if test="${command.results != null}">
<div id="operationsearchresults"> 
<strong><f:message key="searchResults"/></strong>
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<th><a href="#" onClick="sortOperationByCode();" ><span class="TDShade"><f:message key="operation"/></span></a></th>

<th><a href="#" onClick="sortOperationByDesc();" ><span class="TDShade"><f:message key="description"/></span></a></th>

<th><a href="#" onClick="sortOperationByStatus();" ><span class="TDShade"><f:message key="status"/></span></a></th>
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
<td  width="45%"><a href="create_operation_event.htm?name=${operation.operationCode}">${operation.operationCode}</a></td>
<td  width="45%">${operation.description}</td>
<td width="45%"><f:message key="activeStatus${operation.status}"/></td>
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
<!-------------------------------------------------TAB1 CONTAINER END---------------------------------------------->
</div>
</div>
<script type="text/javascript">
dolphintabs.init("tabnav", 0)
</script>
<!-----------------------------------------------------TAB CONTENT END--------------------------------------------->
</div>
<!-----------------------------------------MAIN CONTAINER END------------------------------------------------------>
</td>
</tr>
</table>
</form:form>
