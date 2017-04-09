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
			top.document.forms[0].shippingAgentFlag.value="shippingAgent";
			top.document.forms[0].submit();
			
		}
function sortShippingAgentByName(){
document.shippingAgentSearchPopupForm.pageNumber.value = "1";
document.shippingAgentSearchPopupForm.sortFlag.value = "name";
document.shippingAgentSearchPopupForm.sxcel.value="false";
document.shippingAgentSearchPopupForm.submit();
}
function sortShippingAgentByCountry(){
document.shippingAgentSearchPopupForm.pageNumber.value = "1";
document.shippingAgentSearchPopupForm.sortFlag.value = "country.name";
document.shippingAgentSearchPopupForm.sxcel.value="false";
document.shippingAgentSearchPopupForm.submit();
}
function sortShippingAgentByCity(){
document.shippingAgentSearchPopupForm.pageNumber.value = "1";
document.shippingAgentSearchPopupForm.sortFlag.value = "city";
document.shippingAgentSearchPopupForm.sxcel.value="false";
document.shippingAgentSearchPopupForm.submit();
}
function sortShippingAgentByState(){
document.shippingAgentSearchPopupForm.pageNumber.value = "1";
document.shippingAgentSearchPopupForm.sortFlag.value = "state.name";
document.shippingAgentSearchPopupForm.sxcel.value="false";
document.shippingAgentSearchPopupForm.submit();
}

function sortShippingAgentByPhone(){
document.shippingAgentSearchPopupForm.pageNumber.value = "1";
document.shippingAgentSearchPopupForm.sxcel.value="false";
document.shippingAgentSearchPopupForm.sortFlag.value = "phone";
document.shippingAgentSearchPopupForm.submit();
}  
function submitFunction()
{
document.shippingAgentSearchPopupForm.pageNumber.value = "1";
document.shippingAgentSearchPopupForm.sxcel.value="false";
document.shippingAgentSearchPopupForm.sortFlag.value = "";
document.shippingAgentSearchPopupForm.submit();
}		
function submitxcel(){
document.shippingAgentSearchPopupForm.sxcel.value="true";
document.shippingAgentSearchPopupForm.sortFlag.value = "";
document.shippingAgentSearchPopupForm.submit();
}
   </script>


<form:form name="shippingAgentSearchPopupForm" method="POST"
	action="search_shippingagent_popup.htm">

	<input type="hidden" name="pageNumber" value="1"/>
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	<form:hidden path="countryFlag"/>
	<form:hidden path="sortFlag"/>
<!-- START : #119240 -->
<form:hidden path="currentSortFlag"/>
<form:hidden path="prevSortFlag"/>
<form:hidden path="sortOrderFlag"/>
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240  --> 
  <input type="hidden" name="sxcel" value="false"/>
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
	<form:errors cssClass="error" /></div>
	<table border="0" cellpadding="0" cellspacing="0" align="center"
		class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>
			<td valign="top">
			<div id="tab1" class="innercontent">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="mainApplTable" style="padding:right:5px;">


				</tr>
				<tr class="InnerApplTable">
					<td width="20%" class="TDShade"><f:message key="country" /><strong>
					:</strong></td>
					<td colspan="1" class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:select id="country"
						cssClass="selectionBox" path="country.value"
						items="${icbfn:dropdown('country', null)}" itemLabel="name"
						itemValue="value" />  --%>
						<form:select id="country"
						cssClass="selectionBox" path="country.value"
						items="${icbfn:dropdown('country', null)}" itemLabel="name"
						itemValue="value" onkeypress="if(event.keyCode==13) {submitFunction();}"/> 
					<!-- END : #119240 -->
						<form:errors path="country.value"
						cssClass="redstar" /></td>

					<td width="20%" class="TDShade"><f:message key="state" />:</td>
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
						key="city" />:</strong></td>
					<td colspan="3" class="TDShadeBlue">
					<!-- START : #119240 -->	
					<%-- <form:input	cssClass="inputBox" maxlength="64" path="city.value" /> --%>
					<form:input	cssClass="inputBox" maxlength="64" path="city.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
					<form:errors path="city.value" cssClass="redstar" /></td>
				</tr>
				<tr>
					<td width="20%" class="TDShade"><strong><f:message
						key="shippingAgent" />:</strong></td>
					<td colspan="3" class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:input	cssClass="inputBox" maxlength="128" path="name.value" /> --%>
					<form:input	cssClass="inputBox" maxlength="128" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
					<!-- END : #119240 -->
					<form:errors path="name.value" cssClass="redstar" /></td>
					<%-- <td width="20%" class="TDShade"><strong> &nbsp;</strong></td>
					  <td colspan="2" class="TDShadeBlue">&nbsp;</td> --%>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input id="search" type="button" value="Search"
								name="search" class="button1" onClick="submitFunction()"/>
								<input id="cancel"
								type="button" value="Cancel" name="cancel" class="button1"
								onClick="javascript:top.hidePopupDiv('shippingagent','shippingagentbody');top.popupboxclose();" />
								<c:choose>
								<c:when test="${command.pageName==null}">
								<td></td>
								</c:when>
								
								<c:otherwise>
								
								<a href="create_new_shippingagent.htm?countryCode=${command.country.value}" ><f:message
															key="createNewShippingAgent"/></a>
	
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
<c:if test="${command.results != null}"> <div id="permissionlistsearchresults"><strong><f:message key="searchResults" /></strong> 
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr> <th><a href="#start" onClick="sortShippingAgentByName();" ><span class="TDShade"><f:message key="shippingAgent" /></span></th> 
<th><a href="#start" onClick="sortShippingAgentByCountry();" ><span class="TDShade"><f:message key="country" /></span></a></th> 
<th><a href="#start" onClick="sortShippingAgentByCity();" ><span class="TDShade"><f:message key="city" /></span></a></th>
<th><a href="#start" onClick="sortShippingAgentByState();" ><span class="TDShade"><f:message key="state" /></span></a></th> 
<th><a href="#start" onClick="sortShippingAgentByPhone();" ><span class="TDShade"><f:message key="phoneNo" />.</span></a></th> </tr>
<c:forEach items="${command.results}" var="shippingAgent" varStatus="status">
 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose><td><a href="#"

onclick="javascript:top.return_popup_search_result('${command.inputFieldId}','${shippingAgent.id}');submitform();top.hidePopupDiv('shippingagent','shippingagentbody');top.popupboxclose();">${shippingAgent.name}</a></td> <td>${shippingAgent.countryCode}</td> <td>${shippingAgent.city}</td> <td>${shippingAgent.stateCode}</td> <td>${shippingAgent.phone}</td>

						</tr>
					</c:forEach>
					<tr>
<td>&nbsp;&nbsp;</td>
						<td align="center">
						<!-- START : #119240 -->
						<%-- <c:forEach	items="${command.pagination.pages}" var="page" varStatus="status"> --%>
							<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber)
												  {	 
													 
													document.shippingAgentSearchPopupForm.pageNumber.value = pageNumber;
													document.shippingAgentSearchPopupForm.sxcel.value="false";
												// START : #119240
													document.shippingAgentSearchPopupForm.checkPageSort.value = "true";
												// END : #119240 
													document.shippingAgentSearchPopupForm.submit();
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
</form:form>

<c:if test="${fn:length(command.results)==1}">
<SCRIPT LANGUAGE="JavaScript">
	<c:forEach items="${command.results}" var="shippingAgent" varStatus="status">
	top.return_popup_search_result('${command.inputFieldId}','${shippingAgent.id}');submitform();top.hidePopupDiv('shippingagent','shippingagentbody');top.popupboxclose();	
	</c:forEach>
</script>
</c:if>

<div id="faderPane"
	style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src=" images/fake-alpha-999.gif"></div>
