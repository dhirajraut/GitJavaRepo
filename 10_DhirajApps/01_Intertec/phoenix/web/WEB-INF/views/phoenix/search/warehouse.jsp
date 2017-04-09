<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="js/ce/genericlookup.js"></script>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:right:5px;">
						<tr>
							<th colspan="2"><f:message key="warehouseSearchTitle"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="businessUnitName"/>: </td>
							<td width="80%" class="TDShadeBlue">
							<c:choose>
								<c:when test="${param['buname.disabled']=='true' or empty param['buname.disabled']}">
								    <form:input id="buname" cssClass="inputBox" path="buName" readonly="true"/>
								</c:when>
								<c:otherwise>
									<form:select cssClass="selectionBox" id="buname" path="buName" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value"/>                    
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="branchCode"/>:</td>
							<td class="TDShadeBlue">
								<form:input cssClass="inputBox" path="name" />
							</td>
						</tr>
						<tr>
							<td class="TDShade" nowrap><f:message key="description"/> : </td>
							<td class="TDShadeBlue"><form:input cssClass="inputBox" path="description" /></td>
						
						</tr>
						<tr>
						<td class="TDShade"><f:message key="status" />: </td>
						<td class="TDShadeBlue"><form:select id="sel1"
									cssClass="selectionBox" path="status"
									items="${command.activeStatuses}" itemLabel="name"
									itemValue="value" /> 
						</td>
						
						</tr>

					</table>
					
<script>
  document.searchForm.name.focus();
</script>