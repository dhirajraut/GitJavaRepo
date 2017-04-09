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
		<th colspan="2"><f:message key="employeeSearchTitle"/></th>
	</tr>
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="firstName"/>: </td>
		<td width="80%" class="TDShadeBlue">
			<form:input cssClass="inputBox" path="firstName" />
		</td>
	</tr>
	<tr>
		<td class="TDShade"><f:message key="lastName"/>:</td>
		<td class="TDShadeBlue">
			<form:input cssClass="inputBox" path="lastName" />
		</td>
	</tr>
	<tr>
		<td class="TDShade"><f:message key="businessUnit"/> : </td>
		<td class="TDShadeBlue">
			<form:select id="sel3" cssClass="selectionBoxbig" path="businessUnit" items="${command.buNames}" itemLabel="name" itemValue="value"/>
		</td>
	</tr>
	<tr>
		<td class="TDShade"><f:message key="operatingUnit"/> : </td>
		<td class="TDShadeBlue"><form:input cssClass="inputBoxBlue" maxlength="128" size="10" path="operatingUnit" /></td>
	</tr>
</table>
<ajax:autocomplete 
	baseUrl="phx_ajax.htm" 
	source="operatingUnit" 
	target="operatingUnit" 
	className="autocomplete" 
	parameters="entity=com.intertek.entity.Branch,textAttribute=name,valueAttribute=name,~buName={businessUnit},~name={operatingUnit}"
	minimumCharacters="3" />