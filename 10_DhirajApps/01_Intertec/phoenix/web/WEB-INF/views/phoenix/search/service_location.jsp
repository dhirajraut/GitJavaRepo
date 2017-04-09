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
							<th colspan="7"><f:message key="serviceLocationSearchTitle"/></th>
						</tr>
						<tr class="InnerApplTable">
					<td width="20%" class="TDShade"><f:message key="country" /><strong>
					:</strong></td>
					<td colspan="1" class="TDShadeBlue"><form:select
						cssClass="selectionBox" path="countryName"
						items="${icbfn:dropdown('country', null)}" itemLabel="name"
						itemValue="value" /> <form:errors path="countryName"
						cssClass="redstar" /></td>
					<td width="20%" class="TDShade"><f:message key="state" />:</td>
					<td colspan="1" class="TDShadeBlue"><icb:list
						var="countryCodeList">
						<icb:item>${command.countryName}</icb:item>
					</icb:list> <form:select cssClass="selectionBox" path="stateName"
						items="${icbfn:dropdown('state', countryCodeList)}"
						itemLabel="name" itemValue="value" /> <form:errors
						path="stateName" cssClass="redstar" /></td>
					
					<ajax:select baseUrl="country.htm" source="countryName"
						target="stateName"
						parameters="country.countryCode={countryName}"
						parser="new ResponseXmlParser()" />

				</tr>
				<tr>
					<td class="TDShade"><strong><f:message
						key="portCity" />:</strong></td>
					<td colspan="1" class="TDShadeBlue"><form:input
						cssClass="inputBox" maxlength="30" path="city" />
					<form:errors path="city" cssClass="redstar" /></td>


					<td width="20%" class="TDShade"><strong><f:message
						key="serviceLocation" />:</strong></td>
					<td colspan="3" class="TDShadeBlue"><form:input
						cssClass="inputBox" maxlength="128" path="name" />
					<form:errors path="name" cssClass="redstar" /></td>

				</tr>
				<tr>
				<td class="TDShade" ><strong><f:message key="serviceLocationCode"/>:</strong> </td>
                <td class="TDShadeBlue" colspan="1"><form:input cssClass="inputBox" maxlength="128"  path="serviceLocationCode" />
                <form:errors path="serviceLocationCode" cssClass="redstar"/></td>
				<td class="TDShade"></td>
				<td class="TDShadeBlue" colspan="3" ></td>
				</tr>

					</table>
<script>
  document.searchForm.city.focus();
</script>