<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>
<script language="javascript">
function submitform()
{
	top.document.forms[0].towingCompFlag.value="towingCompany";
	top.document.forms[0].submit();
}
function sortTowingCompanyByName(){
document.towingcompanySearchPopupForm.pageNumber.value = "1";
document.towingcompanySearchPopupForm.sortFlag.value = "name";
document.towingcompanySearchPopupForm.txcel.value="false";
document.towingcompanySearchPopupForm.submit();
}
function sortTowingCompanyByCountry(){
document.towingcompanySearchPopupForm.pageNumber.value = "1";
document.towingcompanySearchPopupForm.sortFlag.value = "country.name";
document.towingcompanySearchPopupForm.txcel.value="false";
document.towingcompanySearchPopupForm.submit();
}
function sortTowingCompanyByCity(){
document.towingcompanySearchPopupForm.pageNumber.value = "1";
document.towingcompanySearchPopupForm.sortFlag.value = "city";
document.towingcompanySearchPopupForm.txcel.value="false";
document.towingcompanySearchPopupForm.submit();
}
function sortTowingCompanyByState(){
document.towingcompanySearchPopupForm.pageNumber.value = "1";
document.towingcompanySearchPopupForm.sortFlag.value = "state.name";
document.towingcompanySearchPopupForm.txcel.value="false";
document.towingcompanySearchPopupForm.submit();
}

function sortTowingCompanyByPhone(){
document.towingcompanySearchPopupForm.pageNumber.value = "1";
document.towingcompanySearchPopupForm.sortFlag.value = "phone";
document.towingcompanySearchPopupForm.txcel.value="false";
document.towingcompanySearchPopupForm.submit();
}  
function submitFunction()
{
document.towingcompanySearchPopupForm.pageNumber.value = "1";
document.towingcompanySearchPopupForm.sortFlag.value = "";
document.towingcompanySearchPopupForm.txcel.value="false";
document.towingcompanySearchPopupForm.submit();
}
function submitxcel(){
document.towingcompanySearchPopupForm.txcel.value="true";
document.towingcompanySearchPopupForm.sortFlag.value = "";
document.towingcompanySearchPopupForm.submit();
}
</script>

<form:form name="towingcompanySearchPopupForm" method="POST"
	action="search_towingcompany_popup.htm">

	<input type="hidden" name="pageNumber" value="1"/>
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="sortFlag"/>
<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  --> 
	<input type="hidden" name="txcel" value="false"/>

	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
	<form:errors cssClass="error" /></div>

	<table border="0" cellpadding="0" cellspacing="0" align="center"
		class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
			<div id="tab1" class="innercontent">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="mainApplTable" style="padding:right:5px;">

				<tr class="InnerApplTable">
					<td class="TDShade"><f:message key="country" /><strong>
					:</strong></td>
					<td class="TDShadeBlue">
					 	<!-- START : #119240 -->
					<%-- <form:select cssClass="selectionBox"
						path="country.value" items="${icbfn:dropdown('country', null)}"
						itemLabel="name" itemValue="value" /> --%>
					<form:select cssClass="selectionBox"
						path="country.value" items="${icbfn:dropdown('country', null)}"
						itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
						<!-- END : #119240 -->	
						<form:errors
						path="country.value" cssClass="redstar" /></td>
					<td width="10%" class="TDShade"><f:message key="state" />:</td>
					<td colspan="1" class="TDShadeBlue"><icb:list
						var="countryCodeList">
						<icb:item>${command.country.value}</icb:item>
					</icb:list> 
						<!-- START : #119240 -->
					    <%-- <form:select cssClass="selectionBox" path="state.value"
						items="${icbfn:dropdown('state', countryCodeList)}"
						itemLabel="name" itemValue="value" /> --%>
						<form:select cssClass="selectionBox" path="state.value"
						items="${icbfn:dropdown('state', countryCodeList)}"
						itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
						<!-- END : #119240 -->
						<form:errors
						path="state.value" cssClass="redstar" /></td>
					<ajax:select baseUrl="country.htm" source="country.value"
						target="state.value"
						parameters="country.countryCode={country.value}"
						parser="new ResponseXmlParser()" />

				</tr>
				<tr>
					<td width="20%" class="TDShade"><strong><f:message
						key="city" />:</td>
					<td colspan="4" class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:input
						cssClass="inputBox" maxlength="35" path="city.value" /> --%>
					<form:input	cssClass="inputBox" maxlength="35" path="city.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
					<form:errors path="city.value" cssClass="redstar" /></td>
				</tr>
				<tr>
					<td width="20%" class="TDShade"><strong><f:message
						key="towingCompany" />:</strong></td>
					<td colspan="4" class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:input cssClass="inputBox" maxlength="35" path="name.value" /> --%>
					 <form:input cssClass="inputBox" maxlength="35" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
					<form:errors path="name.value" cssClass="redstar" /></td>

				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input name="button" type="button" onClick="submitFunction()" class="button1" value="Search" /> <input id="cancel"
								type="button" value="Cancel" name="cancel" class="button1"
								onClick="javascript:top.hidePopupDiv('towingco','towingbody');top.popupboxclose();" />
			
								<c:choose>
								<c:when test="${command.pageName==null}">
								<td></td>
								</c:when>
								<c:when test="${command.pageName==Next}">
								<td></td>
								</c:when>

								<c:otherwise>
								
								<a href="create_new_towingcompany.htm?countryCode=${command.country.value}"><f:message
															key="createNewTowingCompany" /></a>
								
								 </c:otherwise>
								</c:choose>  
										
							</td>
							<td style="text-align:right;"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
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
						<th><a href="#start" onClick="sortTowingCompanyByName();" ><span class="TDShade"><f:message
							key="towingCompany" /></span></a></th>
						<th><a href="#start" onClick="sortTowingCompanyByCountry();" ><span class="TDShade"><f:message key="country" /></span></a></th>
						<th><a href="#start" onClick="sortTowingCompanyByCity();" ><span class="TDShade"><f:message key="city" /></span></a></th>
						<th><a href="#start" onClick="sortTowingCompanyByState();" ><span class="TDShade"><f:message key="state" /></span></a></th>
						<th><a href="#start" onClick="sortTowingCompanyByPhone();" ><span class="TDShade"><f:message key="phoneNo" /></span></a></th>
					</tr>
					<c:forEach items="${command.results}" var="towingCompany"
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
								onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${towingCompany.id}');submitform();top.hidePopupDiv('towingco','towingbody');top.popupboxclose();">${towingCompany.name}</a></td>
							<td>${towingCompany.countryCode}</td>
							<td>${towingCompany.city}</td>
							<td>${towingCompany.stateCode}</td>
							<td>${towingCompany.phone}</td>
						</tr>
					</c:forEach>
					<tr>
<td>&nbsp;&nbsp;</td>
						<td align="center">
						<!-- START : #119240 --> 
						<%-- <c:forEach
							items="${command.pagination.pages}" var="page" varStatus="status"> --%>
							<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber)
												  {	 
													 
													document.towingcompanySearchPopupForm.pageNumber.value = pageNumber;
													document.towingcompanySearchPopupForm.txcel.value="false";
												// START : #119240
													document.towingcompanySearchPopupForm.checkPageSort.value = "true";
												// END : #119240 
													document.towingcompanySearchPopupForm.submit();
												  }	

									</SCRIPT>
						<%--	<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name}
							</a>&nbsp;&nbsp;
						</c:forEach> --%>
						<%@ include file="../common/pagination.jsp" %>
						<!-- END : #119240 --> 
						</td>
					</tr>
					<tr></tr>
				</table>
				</div>
			</c:if></div>
			</td>
		</tr>
	</table>


	<div id="faderPane"
		style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
	<IMG src=" images/fake-alpha-999.gif"></div>
</form:form>

<c:if test="${fn:length(command.results)==1}">
<SCRIPT LANGUAGE="JavaScript">
	<c:forEach items="${command.results}" var="towingCompany"	varStatus="status">
	top.return_popup_search_result('${command.inputFieldId}', '${towingCompany.id}');submitform();top.hidePopupDiv('towingco','towingbody');top.popupboxclose();
	</c:forEach>
</script>
</c:if>
