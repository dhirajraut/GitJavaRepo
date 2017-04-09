<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script >
function sortStateByStateCode(){
document.stateSearchPopupForm.pageNumber.value = "1";
document.stateSearchPopupForm.sortFlag.value = "stateId.stateCode";
document.stateSearchPopupForm.submit();
}
</script>

<form:form name="stateSearchPopupForm" method="POST" action="search_state_popup.htm">

	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="searchForm" />
	<form:hidden path="inputFieldId" />
	 <form:hidden path="sortFlag"/>
    <form:hidden path="rowNum" />
	 <form:hidden path="countryCode"/>
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
							<th colspan="2"><f:message key="searchStateCode"/></th>
							<th>&nbsp;</th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="stateCode"/>: </td>
							<td width="80%" colspan="2" class="TDShadeBlue">
								<form:input cssClass="inputBox" path="stateCode.value" />
									<form:errors path="stateCode.value" cssClass="redstar"/>	
							</td>
					</tr>
					
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="Submit" type="submit" class="button1" value="<f:message key="search"/>"  /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					 <br />
							<c:if test="${command.results != null}">
								<div id="statesearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><a href="#start" onClick="sortStateByStateCode();" ><span class="TDShade"><f:message key="stateCode"/></span></a></th>
                                   <th>&nbsp;</th>
								</tr>
							<c:forEach items="${command.results}" var="state" varStatus="status">
									<c:choose>
									<c:when test="${status.index%2==0}">
									<tr style="background-color:#FFFFFF;">
									</c:when>
									<c:otherwise>
									<tr style="background-color:#e7eeff;">                    
									</c:otherwise>
									</c:choose>
										<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${state.stateId.stateCode}');top.hidePopupDiv('state${command.rowNum}','statebody${command.rowNum}');top.popupboxclose();">${state.stateId.stateCode}</a></td>
				                   <td>&nbsp;</td>
									
									</tr>
							 	</c:forEach>
								<tr>
					<td>&nbsp;</td>
									<td align="center">
									<!-- START : #119240 : 22/06/09 -->
										<%-- <c:forEach items="${command.pagination.pages}" var="page" varStatus="status"> --%>
										<SCRIPT LANGUAGE="JavaScript">
	
										function submitSearch(pageNumber)
										 {	 
											document.stateSearchPopupForm.pageNumber.value = pageNumber;
											// START : #119240 : 25/06/09
											document.stateSearchPopupForm.checkPageSort.value = "true";
											// END : #119240 : 25/06/09
											document.stateSearchPopupForm.submit();
										  }	
	
										</SCRIPT>
										<%--		<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
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
