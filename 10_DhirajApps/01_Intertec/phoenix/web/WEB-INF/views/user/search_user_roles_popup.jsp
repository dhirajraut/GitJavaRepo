
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script>
function submitform(){	
var name=document.getElementById("searchForm").value;			
top.document.forms[name].submit();
}

function submitSearch(pageNumber)  {	 			  
document.userRolesSearchPopUpForm.pageNumber.value = pageNumber;
// START : #119240 : 24/06/09
document.userRolesSearchPopUpForm.checkPageSort.value = "true";
// END : #119240 : 24/06/09
document.userRolesSearchPopUpForm.submit();
}
function sortUserRolesByName(){
document.userRolesSearchPopUpForm.pageNumber.value = "1";
document.userRolesSearchPopUpForm.sortFlag.value = "name";
document.userRolesSearchPopUpForm.submit();
}
function sortUserRolesByDesc(){
document.userRolesSearchPopUpForm.pageNumber.value = "1";
document.userRolesSearchPopUpForm.sortFlag.value = "roleDesc";
document.userRolesSearchPopUpForm.submit();
}
function submitFunction()
{
	document.userRolesSearchPopUpForm.pageNumber.value = "1";
	document.userRolesSearchPopUpForm.sortFlag.value = "";
	document.userRolesSearchPopUpForm.submit();
}
</script>

<form:form name="userRolesSearchPopUpForm"  method="POST" action="search_user_roles_popup.htm">
<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="searchForm" />
 <form:hidden path="sortFlag"/>
<form:hidden path="inputFieldId" />
<form:hidden path="rowNum" />
<!-- START : #119240 : 22/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 22/06/09 --> 
<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
<form:errors cssClass="error"/>
</div>
<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
<tr>
<td valign="top">
<div id="tab1" class="innercontent" >
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
<tr>
<th colspan="2"><f:message key="userRoleSearch"/></th>
</tr>
<tr>
<td width="20%" class="TDShade" nowrap><f:message key="role"/>: </td>
<td width="80%" class="TDShadeBlue">
<!-- START : #119240 -->
<%-- <form:input cssClass="inputBox" path="role.value" /> --%>
<form:input cssClass="inputBox" path="role.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
<!-- END : #119240 -->
<form:errors path="role.value" cssClass="redstar"/>	
</td>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td><input name="button" type="button" onClick="submitFunction()" class="button1" value="<f:message key="search"/>"  /></td>
</tr>
</table>
</td>
</tr>
</table>
<br />
				<c:if test="${command.results != null}">
						<div id="userrolesearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortUserRolesByName();" ><span class="TDShade"><f:message key="role"/></span></a></th>
									<th><a href="#start" onClick="sortUserRolesByDesc();" ><span class="TDShade"><f:message key="description"/></span></a></th>
								</tr>
								<c:forEach items="${command.results}" var="roles" varStatus="status">
									<c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
										<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${roles.name}');top.hideVatcode('userrole${command.rowNum}','userrolebody${command.rowNum}');
										top.popupboxclose();top.document.${command.searchForm }.submit();">${roles.name}</a></td>
				                    <td>${roles.roleDesc}</td>
										
								</tr>
							 	</c:forEach>
									<tr>
									<td>&nbsp;</td>
									<td align="center">
										<!-- START : #119240 : 22/06/09 --> 	
											<%--<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
											<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
											  </c:forEach> --%>
											<%@ include file="../common/pagination.jsp" %>
			    						<!-- END : #119240 : 22/06/09 -->	  
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
