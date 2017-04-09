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
			document.serviceLocationSearchPopupForm.sxcel.value="false";
			document.serviceLocationSearchPopupForm.reqForm.value = "jobsForm";
			top.document.forms[0].submit();
		}
function sortServiceLocationByName(){
document.serviceLocationSearchPopupForm.pageNumber.value = "1";
document.serviceLocationSearchPopupForm.sortFlag.value = "name";
document.serviceLocationSearchPopupForm.sxcel.value="false";
document.serviceLocationSearchPopupForm.submit();
}
function sortServiceLocationByCode(){
document.serviceLocationSearchPopupForm.pageNumber.value = "1";
document.serviceLocationSearchPopupForm.sortFlag.value = "serviceLocationCode";
document.serviceLocationSearchPopupForm.sxcel.value="false";
document.serviceLocationSearchPopupForm.submit();
}
function sortServiceLocationByCity(){
document.serviceLocationSearchPopupForm.pageNumber.value = "1";
document.serviceLocationSearchPopupForm.sortFlag.value = "city";
document.serviceLocationSearchPopupForm.sxcel.value="false";
document.serviceLocationSearchPopupForm.submit();
}
function sortServiceLocationByAddress(){
document.serviceLocationSearchPopupForm.pageNumber.value = "1";
document.serviceLocationSearchPopupForm.sortFlag.value = "address1";
document.serviceLocationSearchPopupForm.sxcel.value="false";
document.serviceLocationSearchPopupForm.submit();
}
function sortServiceLocationByState(){
document.serviceLocationSearchPopupForm.pageNumber.value = "1";
document.serviceLocationSearchPopupForm.sortFlag.value = "stateCode";
document.serviceLocationSearchPopupForm.sxcel.value="false";
document.serviceLocationSearchPopupForm.submit();
}
function sortServiceLocationByCountry(){
document.serviceLocationSearchPopupForm.pageNumber.value = "1";
document.serviceLocationSearchPopupForm.sortFlag.value = "country.name";
document.serviceLocationSearchPopupForm.sxcel.value="false";
document.serviceLocationSearchPopupForm.submit();
}

function submitFunction()
{
document.serviceLocationSearchPopupForm.pageNumber.value = "1";
document.serviceLocationSearchPopupForm.sortFlag.value = "";
document.serviceLocationSearchPopupForm.sxcel.value="false";
document.serviceLocationSearchPopupForm.submit();
}

function submitxcel(){
document.serviceLocationSearchPopupForm.sxcel.value="true";
document.serviceLocationSearchPopupForm.sortFlag.value = "";
document.serviceLocationSearchPopupForm.submit();
}
</script>
<form:form name="serviceLocationSearchPopupForm" method="POST"
	action="search_serviceLocation_popup.htm">

	<input type="hidden" name="pageNumber" value="1"/>
	<form:hidden path="reqForm"/>
	<form:hidden path="inputFieldId" />
	<form:hidden path="sortFlag"/>
	<form:hidden path="div1"/>
	<form:hidden path="div2"/>
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
					<%--
					<form:select
						cssClass="selectionBox" path="country.value"
						items="${icbfn:dropdown('country', null)}" itemLabel="name"
						itemValue="value" /> --%>
					<form:select
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
				<c:choose>
					<c:when test="${command.reqForm == 'destinationForm'}">
						<td class="TDShade"><strong>City:</strong></td>
					</c:when>
					<c:otherwise>
						<td class="TDShade"><strong><f:message key="portLocation" />:</strong></td>
					</c:otherwise>
				</c:choose>
					<td colspan="1" class="TDShadeBlue">
					<!-- START : #119240 -->
					<%-- <form:input cssClass="inputBox" maxlength="30" path="city.value" /> --%>
					<form:input	cssClass="inputBox" maxlength="30" path="city.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
					<!-- END : #119240 -->
					<form:errors path="city.value" cssClass="redstar" /></td>
				<c:choose>
					<c:when test="${command.reqForm == 'destinationForm'}">
						<td width="20%" class="TDShade"></td>
						<td colspan="3" class="TDShadeBlue"></td>
					</c:when>
					<c:otherwise>
						<td width="20%" class="TDShade"><strong><f:message
							key="serviceLocation" />:</strong></td>
						<td colspan="3" class="TDShadeBlue">
						<!-- START : #119240 -->
						<%-- <form:input	cssClass="inputBox" maxlength="128" path="name.value" /> --%>
						<form:input	cssClass="inputBox" maxlength="128" path="name.value" onkeypress="if(event.keyCode==13) {submitFunction();}"/>
						<!-- END : #119240 -->
						<form:errors path="name.value" cssClass="redstar" /></td>
					</c:otherwise>
				</c:choose>
				</tr>
				<tr>
				<c:choose>
					<c:when test="${command.reqForm == 'destinationForm'}">
						<td class="TDShade" ></td>
						<td class="TDShadeBlue" colspan="1"></td>
						<td class="TDShade"></td>
						<td class="TDShadeBlue" colspan="3" ></td>
					</c:when>
					<c:otherwise>
						<td class="TDShade" ><strong><f:message key="serviceLocationID"/>:</strong> </td>
						<td class="TDShadeBlue" colspan="1">
						<!-- START : #119240 -->
						<%-- <form:input cssClass="inputBox" maxlength="128"  path="serviceLocationCode.value" /> --%>
						<form:input cssClass="inputBox" maxlength="128"  path="serviceLocationCode.value" onkeypress="if(event.keyCode==13) {submitFunction();}" />
						<!-- END : #119240 -->
						<form:errors path="serviceLocationCode.value" cssClass="redstar"/></td>
						<td class="TDShade"></td>
						<td class="TDShadeBlue" colspan="3" ></td>
					</c:otherwise>
				</c:choose>
				
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="applTableBot">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><input id="search" type="button" onClick="submitFunction()" value="Search"
								name="button" class="button1" />
								&nbsp<c:choose>
								<c:when test="${command.reqForm=='jobsForm'}">
								<input id="cancel"
								type="button" value="Cancel" name="cancel" class="button1"
								onClick="javascript:top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();" />
								</c:when>
								<c:otherwise>
								<input id="cancel"
								type="button" value="Cancel" name="cancel" class="button1"	onClick="javascript:top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();" />
								</c:otherwise>
								</c:choose>  
								<c:choose>
								<c:when test="${command.pageName==null}">
								<td></td>
								</c:when>
								
								<c:otherwise>
								
								<a href="create_new_servicelocation.htm?inputFieldId=${command.inputFieldId}&branchCode=${command.branchCode.value}&div1=${command.div1}&div2=${command.div2}"><f:message
															key="createNewServiceLocation" /></a>
								
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
				<div id="serviceLocationsearchresults"><strong><f:message
					key="searchResults" /></strong>
				<table width="100%" cellpadding="0" cellspacing="0" border="0"
					class="InnerApplTable">
					<tr>
					<c:choose>
						<c:when test="${command.reqForm == 'destinationForm'}">
							
							<th width="20%" nowrap><a href="#start" onClick="sortServiceLocationByCity();" ><span class="TDShade" nowrap><f:message key="city" /></span></a></th>
							
							<th width="10%" nowrap><a href="#start" onClick="sortServiceLocationByState();" ><span class="TDShade" nowrap><f:message key="state" /></span></a></th>
							<th width="20%" nowrap><a href="#start" onClick="sortServiceLocationByCountry();" ><span class="TDShade" nowrap><f:message key="country" /></span></a></th>
						</c:when>
						<c:otherwise>
							<th width="10%" nowrap><a href="#start" onClick="sortServiceLocationByCode();" ><span class="TDShade"><f:message key="serviceLocationID"/></span></a></th>
							<th width="20%" nowrap ><a href="#start" onClick="sortServiceLocationByName();" ><span class="TDShade" nowrap><f:message	key="serviceLocation" /></span></a></th>
							<th width="20%" nowrap><a href="#start" onClick="sortServiceLocationByCity();" ><span class="TDShade" nowrap><f:message key="city" /></span></a></th>
							<th width="20%" nowrap><a href="#start" onClick="sortServiceLocationByAddress();"><span class="TDShade" nowrap><f:message key="address" /></span></a></th>
							<th width="10%" nowrap><a href="#start" onClick="sortServiceLocationByState();" ><span class="TDShade" nowrap><f:message key="state" /></span></a></th>
							<th width="20%" nowrap><a href="#start" onClick="sortServiceLocationByCountry();" ><span class="TDShade" nowrap><f:message key="country" /></span></a></th>
						</c:otherwise>
					</c:choose>
					</tr>
			
					<c:choose>
					<c:when test="${command.reqForm == 'destinationForm'}">
						<c:forEach items="${command.results}" var="servicelocation"	varStatus="status">
							<c:choose>
								<c:when test="${status.index%2==0}">
									<tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
									<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
							</c:choose>
							<td width="10%"><a href="#"
								onClick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${servicelocation.serviceLocationCode}');submitform();top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();">${servicelocation.city}</a> </td>
							<td width="10%">
							${servicelocation.stateCode}
							</td>
							<td width="20%">${servicelocation.country.name}</td>
							</tr>
						</c:forEach>
						<tr colspan="3"> 
							<td align="center" nowrap>
							<!-- START : #119240 -->
							<%-- <c:forEach 	items="${command.pagination.pages}" var="page" varStatus="status"> --%>
								<SCRIPT LANGUAGE="JavaScript">

										function submitSearch(pageNumber)
													  {	 
														 
														document.serviceLocationSearchPopupForm.pageNumber.value = pageNumber;
														document.serviceLocationSearchPopupForm.reqForm.value = "destinationForm";
														document.serviceLocationSearchPopupForm.sxcel.value="false";
												// START : #119240
												document.serviceLocationSearchPopupForm.checkPageSort.value = "true";
												// END : #119240 
														document.serviceLocationSearchPopupForm.submit();
													  }	

								</SCRIPT>
							<%--	<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name}
								</a>&nbsp;&nbsp;
							</c:forEach> --%>
							<%@ include file="../common/pagination.jsp" %>
							<!-- END : #119240 -->
							</td>
						</tr>
					</c:when>

					<c:otherwise>
						<c:forEach items="${command.results}" var="servicelocation"	varStatus="status">
						<c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						  </c:when>
						  <c:otherwise>
						  <tr style="background-color:#e7eeff;">                    
						  </c:otherwise>
						  </c:choose>
							<td>${servicelocation.serviceLocationCode}</td>
							<c:choose>
							<c:when test="${command.reqForm=='jobsForm'}">
							<td width="20%">
							<a href="#"
								onClick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${servicelocation.serviceLocationCode}');submitform();top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();">${servicelocation.name}</a></td>
								<td width="20%">${servicelocation.city}</td>
								</c:when>
								<c:otherwise>
							<td width="20%">${servicelocation.name}</td>
							<td width="10%"><a href="#"
								onClick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${servicelocation.serviceLocationCode}');submitform();top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();">${servicelocation.city}</a> </td>
							</c:otherwise>
							</c:choose>
							
							<td width="20%">${servicelocation.address1}${servicelocation.address2}${servicelocation.address3}${servicelocation.address4}</td>
							<td width="10%">
							${servicelocation.stateCode}
							</td>
							<td width="20%">${servicelocation.country.name}</td>
							</tr>
						</c:forEach>
						<tr colspan="3">
							 <td></td>
							  <td></td>
							  <td></td>
							<td align="center" nowrap>
							<!-- START : #119240 -->
							<%-- <c:forEach 	items="${command.pagination.pages}" var="page" varStatus="status"> --%>
								<SCRIPT LANGUAGE="JavaScript">

										function submitSearch(pageNumber)
													  {	 
														 
														document.serviceLocationSearchPopupForm.pageNumber.value = pageNumber;
														document.serviceLocationSearchPopupForm.reqForm.value = "jobsForm";
													document.serviceLocationSearchPopupForm.sxcel.value="false";
												// START : #119240
												document.serviceLocationSearchPopupForm.checkPageSort.value = "true";
												// END : #119240 
														document.serviceLocationSearchPopupForm.submit();
													  }	

										</SCRIPT>
							<%--	<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name}
								</a>&nbsp;&nbsp;
							</c:forEach> --%>
							<%@ include file="../common/pagination.jsp" %>
							<!-- END : #119240 -->
							</td>
						</tr>
					</c:otherwise>
					</c:choose>

					
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
	<c:forEach items="${command.results}" var="servicelocation"	varStatus="status">
	top.return_popup_search_result('${command.inputFieldId}', '${servicelocation.serviceLocationCode}');submitform();top.hidePopupDiv('${command.div1}','${command.div2}');top.popupboxclose();	
	</c:forEach>
</script>
</c:if>

<div id="faderPane"
	style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src=" images/fake-alpha-999.gif"></div>
