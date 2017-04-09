<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script language="javascript">
function submitform()
{
top.document.forms[0].submit();
}

function submitSearch(pageNumber)
{	 
document.projectSearchPopupForm.pageNumber.value = pageNumber;
// START : #119240
document.projectSearchPopupForm.checkPageSort.value = "true";
// END : #119240 
document.projectSearchPopupForm.submit();
}	
function sortProjectByProjectNumber(){
document.projectSearchPopupForm.pageNumber.value = "1";
document.projectSearchPopupForm.sortFlag.value = "projectNumber";
document.projectSearchPopupForm.submit();
}
function sortProjectByCustCode(){
document.projectSearchPopupForm.pageNumber.value = "1";
document.projectSearchPopupForm.sortFlag.value = "custCode";
document.projectSearchPopupForm.submit();
}
function sortProjecteByProjectType(){
document.projectSearchPopupForm.pageNumber.value = "1";
document.projectSearchPopupForm.sortFlag.value = "projectType";
document.projectSearchPopupForm.submit();
}

function submitFunction()
{
	document.projectSearchPopupForm.pageNumber.value = "1";
	document.projectSearchPopupForm.sortFlag.value = "";
	document.projectSearchPopupForm.submit();
}
</script>

<form:form name="projectSearchPopupForm" method="POST" 	action="search_project_popup.htm">

	<input type="hidden" name="pageNumber" value="1" />
	<%--<form:hidden path="searchType" />
	<form:hidden path="searchForm" />--%>
	<form:hidden path="inputFieldId" />
	<form:hidden path="rowNum"/>
	<form:hidden path="sortFlag"/>
	<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  --> 

	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
	<form:errors cssClass="error" /></div>


	<table border="0" cellpadding="0" cellspacing="0" align="center"
		class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
			<div id="tab1" class="innercontent">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="mainApplTable" style="padding:right:5px;">
				<tr>
					<td width="20%" class="TDShade" nowrap><strong><f:message
						key="projectNumber"/>:<span class="redstar">*</span></strong></td>
					<td colspan="4" class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:input cssClass="inputBox" path="projectNumber.value" /> --%>
					<form:input cssClass="inputBox" path="projectNumber.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
						<form:errors
						path="projectNumber.value" cssClass="redstar" /></td> 
				</tr>
				<tr>
					<td width="20%" class="TDShade" nowrap><strong><f:message
						key="customerNumber" />:<span class="redstar">*</span></strong></td>
					<td colspan="4" class="TDShadeBlue">
					<!-- START : #119240 -->
					 <%-- <form:input cssClass="inputBox" path="custCode.value" /> --%>
					 <form:input cssClass="inputBox" path="custCode.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
					<!-- END : #119240 -->	
						<form:errors
						path="custCode.value" cssClass="redstar" /></td> 

				</tr>
				<tr>
					<td width="20%" class="TDShade" nowrap><strong><f:message
						key="projectType"/>:<span class="redstar">*</span></strong></td>
					<td colspan="4" class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:input cssClass="inputBox" path="projectType.value" /> --%>
					<form:input cssClass="inputBox" path="projectType.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
					<!-- END : #119240 -->	
						<form:errors
						path="projectType.value" cssClass="redstar" /></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input id="search" name="button" type="button" class="button1" onClick="submitFunction()" value="Search"
								/> <input id="cancel"
								type="button" value="Cancel" name="cancel" class="button1"
								onClick="javascript:top.hidePopupDiv('searchprojects${command.rowNum}','searchprojectsbody${command.rowNum}');top.popupboxclose();" />

						</tr>
					</table>
					</td>
				</tr>
			</table>

			<br />
		<c:if test="${command.results != null}">
				<div id="permissionlistsearchresults"><strong><f:message
					key="searchResults" /></strong>
				<table width="100%" cellpadding="0" cellspacing="0"
					class="InnerApplTable">
					<tr>
						<th><a href="#start" onClick="sortProjectByProjectNumber();" ><span class="TDShade"><f:message key="projectNumber"/></span></a></th>
						<th><a href="#start" onClick="sortProjectByCustCode();" ><span class="TDShade"><f:message key="customerNumber" /></span></a></th>
						<th><a href="#start" onClick="sortProjecteByProjectType();" ><span class="TDShade"><f:message key="projectType" /></span></a></th>
						
					</tr>
					<c:forEach items="${command.results}" var="projects"
						varStatus="status">
						 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
							<td><a href="#"
								onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${projects.projectNumber}');top.hidePopupDiv('searchprojects${command.rowNum}','searchprojectsbody${command.rowNum}');top.popupboxclose();">${projects.projectNumber}</a></td>
							<td>${projects.custCode}</td>
							<td>${projects.projectType}</td>
							
						</tr>
					</c:forEach>
					<tr>
                        <td>&nbsp;&nbsp;</td>
						<td align="center">
						<!-- START : #119240 -->
						<%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
						<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name}&nbsp;&nbsp;
							</a>
						</c:forEach> --%>
						<%@ include file="../common/pagination.jsp" %>
						<!-- END : #119240 -->
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

	<div id="faderPane"
		style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
	<IMG src=" images/fake-alpha-999.gif"></div>
</form:form>


