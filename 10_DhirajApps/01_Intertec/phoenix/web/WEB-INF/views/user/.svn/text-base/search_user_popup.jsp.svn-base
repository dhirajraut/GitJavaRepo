<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>
function submitSearch(pageNumber)
{	 
document.userSearchPopUpForm.pageNumber.value = pageNumber;
document.userSearchPopUpForm.uxcel.value="false";
// START : #119240 : 24/06/09
document.userSearchPopUpForm.checkPageSort.value = "true";
// END : #119240 : 24/06/09
document.userSearchPopUpForm.submit();
}	

function submitform()
		{
			var name=document.getElementById("searchForm").value;
		/*	if(name!='')
			top.document.forms[name].submit();
			else
			return */
			if((name!="userCreateForm")&&(name!="userEditForm"))
			top.document.forms[name].submit();
			else
			return 
			
		}
function sortUserByLoginName(){
document.userSearchPopUpForm.pageNumber.value = "1";
document.userSearchPopUpForm.sortFlag.value = "loginName";
document.userSearchPopUpForm.uxcel.value="false";
document.userSearchPopUpForm.submit();
}

function sortUserByName(){
document.userSearchPopUpForm.pageNumber.value = "1";
document.userSearchPopUpForm.sortFlag.value = "name";
document.userSearchPopUpForm.uxcel.value="false";
document.userSearchPopUpForm.submit();
}
function sortUserByEmpStatus(){
document.userSearchPopUpForm.pageNumber.value = "1";
document.userSearchPopUpForm.sortFlag.value = "employeeStatus";
document.userSearchPopUpForm.uxcel.value="false";
document.userSearchPopUpForm.submit();
}
function submitFunction()
{
	document.userSearchPopUpForm.pageNumber.value = "1";
	document.userSearchPopUpForm.sortFlag.value = "";
	document.userSearchPopUpForm.uxcel.value="false";
	document.userSearchPopUpForm.submit();
}
function submitxcel(){
document.userSearchPopUpForm.uxcel.value="true";
document.userSearchPopUpForm.sortFlag.value = "";
document.userSearchPopUpForm.submit();
}
</script>

<form:form name="userSearchPopUpForm" method="POST" action="search_user_popup.htm">
	<input type="hidden" name="pageNumber" value="1"/>
	<form:hidden path="inputFieldId" />
     <form:hidden path="div1"/>
	<form:hidden path="div2"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="sortFlag"/>
<!-- START : #119240 : 22/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 22/06/09 --> 
    <input type="hidden" name="uxcel" value="false"/>
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
	<form:errors cssClass="error" /></div>

	<table border="0" cellpadding="0" cellspacing="0" align="center"
		class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="loginName"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="name.value" /> --%>
								<form:input cssClass="inputBox" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
							</td>
						</tr>
						<tr>
							<td class="TDShade"><f:message key="firstName"/>:</td>
							<td class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="firstName.value" /> --%>
								<form:input cssClass="inputBox" path="firstName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
							</td>
						</tr>
						<tr>
							<td class="TDShade"><f:message key="lastName"/> : </td>
							<td class="TDShadeBlue">
							<!-- START : #119240 -->
							<%-- <form:input cssClass="inputBox" path="lastName.value" /> --%>
							<form:input cssClass="inputBox" path="lastName.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
							<!-- END : #119240 -->
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" class="button1" onClick="submitFunction()" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right;"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<c:if test="${command.results != null}">
						<div id="usersearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortUserByLoginName();" ><span class="TDShade"><f:message key="loginName"/></span></a></th>
									<th><a href="#start" onClick="sortUserByName();" ><span class="TDShade"><f:message key="name"/></span></a></th>
									<th><a href="#" onClick="sortUserByEmpStatus();" ><span class="TDShade"><f:message key="employeeStatus"/></span></a></th>
									
								</tr>
								<c:forEach items="${command.results}" var="user" varStatus="status">
									
									<c:choose>
									<c:when test="${status.index%2==0}">
									<tr style="background-color:#FFFFFF;">
									</c:when>
									<c:otherwise>
									<tr style="background-color:#e7eeff;">                    
									</c:otherwise>
									</c:choose>
										<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${user.loginName}');top.popupboxclose();top.hidePopupDiv('${command.div1}','${command.div2}');submitform();">${user.loginName}</a></td>
				                        <td>${user.firstName} ${user.lastName}</td>
				                        <td><f:message key="empstatus_${user.employeeStatus}"/></td>
										
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
