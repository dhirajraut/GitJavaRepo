<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script >
 function submitSearch(pageNumber)
			  {	 
				document.permissionListSearchPopupForm.pageNumber.value = pageNumber;
				// START : #119240 : 24/06/09
				document.permissionListSearchPopupForm.checkPageSort.value = "true";
				// END : #119240 : 24/06/09
				document.permissionListSearchPopupForm.submit();
			  }

  function submitform()
		{
			var name=document.getElementById("searchForm").value;
			top.document.forms[name].submit();
		}
function sortPermissionListByUrl(){
document.permissionListSearchPopupForm.pageNumber.value = "1";
document.permissionListSearchPopupForm.sortFlag.value = "url";
document.permissionListSearchPopupForm.submit();
}
function sortPermissionListByName(){
document.permissionListSearchPopupForm.pageNumber.value = "1";
document.permissionListSearchPopupForm.sortFlag.value = "name";
document.permissionListSearchPopupForm.submit();
}
function sortPermissionListByDesc(){
document.permissionListSearchPopupForm.pageNumber.value = "1";
document.permissionListSearchPopupForm.sortFlag.value = "description";
document.permissionListSearchPopupForm.submit();
}
function submitFunction()
{
	document.permissionListSearchPopupForm.pageNumber.value = "1";
	document.permissionListSearchPopupForm.sortFlag.value = "";
	document.permissionListSearchPopupForm.submit();
}
</script>

<form:form name="permissionListSearchPopupForm" method="POST" action="search_control_popup.htm">

	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
    <form:hidden path="rowNum" />
	<form:hidden path="sortFlag"/>
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
							<th colspan="2"><f:message key="permissionListSearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="permissionControl"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<!-- START : #119240 -->
								<%-- <form:input cssClass="inputBox" path="control.value" /> --%>
								<form:input cssClass="inputBox" path="control.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
								<!-- END : #119240 -->
									<form:errors path="control.value" cssClass="redstar"/>	
							</td>
					
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="button" type="button" onClick="submitFunction()"  class="button1" value="<f:message key="search"/>"  /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					 <br />
					<c:if test="${command.results != null}">
						<div id="permissionlistsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortPermissionListByName();" ><span class="TDShade"><f:message key="permissionList"/></span></a></th>
									<th><a href="#start" onClick="sortPermissionListByUrl();" ><span class="TDShade"><f:message key="URL"/></span></a></th>
									<th><a href="#start" onClick="sortPermissionListByDesc();" ><span class="TDShade"><f:message key="permissionListDescription"/></span></a></th>
								</tr>
							<c:forEach items="${command.results}" var="permission" varStatus="status">
						<c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
										<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${permission.name}');top.hideVatcode('permissionlist${command.rowNum}','permissionlistbody${command.rowNum}');top.popupboxclose();submitform();">${permission.name}</a></td>
				                    <td>${permission.url}</td>
									<td>${permission.description}</td>
									</tr>
							 	</c:forEach>
								 	 <tr>
					           <td></td>
                             <td align="center">
                             <!-- START : #119240 : 22/06/09 -->
		   					     <%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
		 						<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp&nbsp
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
