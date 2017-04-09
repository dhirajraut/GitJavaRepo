<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>

<script language="javascript">

function submitSearch(pageNumber){	 
document.timeZoneSearchPopUpForm.pageNumber.value = pageNumber;
// START : #119240 : 24/06/09
document.timeZoneSearchPopUpForm.checkPageSort.value = "true";
// END : #119240 : 24/06/09
document.timeZoneSearchPopUpForm.submit();
}	

function sortTimeZoneByZones(){
document.timeZoneSearchPopUpForm.pageNumber.value = "1";
document.timeZoneSearchPopUpForm.sortFlag.value = "timeZone";
document.timeZoneSearchPopUpForm.submit();
}
function sortTimeZoneByDescription(){
document.timeZoneSearchPopUpForm.pageNumber.value = "1";
document.timeZoneSearchPopUpForm.sortFlag.value = "timeZoneDesc";
document.timeZoneSearchPopUpForm.submit();
}
function submitFunction()
{
	document.timeZoneSearchPopUpForm.pageNumber.value = "1";
	document.timeZoneSearchPopUpForm.sortFlag.value = "";
	document.timeZoneSearchPopUpForm.submit();
}
	 </script>
</head>
<form:form name="timeZoneSearchPopUpForm" method="POST"
	action="search_timezone_popup.htm">
	

<input type="hidden" name="pageNumber" value="1" />

	<form:hidden path="searchType" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="sortFlag"/>
	<form:hidden path="div1" />
	<form:hidden path="div2" />
<!-- START : #119240 : 19/06/09 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 : 19/06/09 -->
 
	<div style="width:auto;padding-left:10px;padding-top:5px;color:red;">
	<form:errors cssClass="error" /></div>


	<table border="0" cellpadding="0" cellspacing="0" align="center"
		class="MainTable" style="padding:left:5px;padding-top:0px;">
		<tr>
			<td valign="top">
			<div id="tab1" class="innercontent">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="mainApplTable" style="padding:right:0px;padding-top:0px;">

				</tr>
				<tr class="InnerApplTable">
					<td width="20%" class="TDShade"><f:message key="timeZone" />:
					</td>
					<td width="80%" class="TDShadeBlue">
						<!-- START : #119240 -->
						<%-- <form:input	cssClass="inputBox" path="timeZone.value" /> --%>
						<form:input	cssClass="inputBox" path="timeZone.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
						<!-- END : #119240 -->
						<form:errors path="timeZone.value" />						
					</td>
				</tr>
				<tr>
					<td class="TDShade"><f:message key="description" />:</td>
					<td class="TDShadeBlue">
						<!-- START : #119240 -->
					<%--	<form:input cssClass="inputBox" path="timeZoneDesc.value" /> --%>
						<form:input cssClass="inputBox" path="timeZoneDesc.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
						<!-- END : #119240 -->
						<form:errors path="timeZoneDesc.value" cssClass="redstar" />
					</td>
				</tr>

			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input name="button" type="button" class="button1" onClick="submitFunction()"
								value="search" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			<br />
			<c:if test="${command.results != null}">
				<div id="collectorsearchresults"><strong><f:message
					key="searchResults" /></strong>
				<table width="100%" cellpadding="0" cellspacing="0"
					class="InnerApplTable">
					<tr>
						<th><a href="#start" onClick="sortTimeZoneByZones();" ><span class="TDShade"><f:message key="timeZone" /></span></a></th>
						<th><a href="#start" onClick="sortTimeZoneByDescription();" ><span class="TDShade"><f:message key="description" /></span></a></th>

					</tr>
					<c:forEach items="${command.results}" var="timeZones"
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
								onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${timeZones.timeZone}');top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();">${timeZones.timeZone}</a></td>
							<td>${timeZones.timeZoneDesc}</td>
						</tr>


					</c:forEach>
					<tr>
						<td>&nbsp;</td>
						<td align="center">
						 <!-- START : #119240 : 19/06/09 --> 
					<%--	<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
							
							<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name}</a>&nbsp;&nbsp;
						</c:forEach> --%>
						 <%@ include file="../common/pagination.jsp" %>
    				<!-- END : #119240 : 19/06/09 -->
						</td>
					</tr>
					<tr></tr>
				</table>
				</div>
			</c:if></div>
			</td>
		</tr>
	</table>

</form:form>


<c:if test="${fn:length(command.results)==1}">
<SCRIPT LANGUAGE="JavaScript">
	<c:forEach items="${command.results}" var="timeZones" varStatus="status">
	top.return_popup_search_result('${command.inputFieldId}', '${timeZones.timeZone}');top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();	
	</c:forEach>
</script>
</c:if>
