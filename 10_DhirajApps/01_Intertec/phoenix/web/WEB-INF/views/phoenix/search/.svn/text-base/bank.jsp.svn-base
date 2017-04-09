<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script	type="text/javascript" >
function bankSelected(bankCodeValue, targetFieldId) {
	top.popupboxclose();   
    top.hidePopupDiv('searchremitto','remittobody');  
    window.top.document.getElementById(targetFieldId).value=bankCodeValue;
    window.top.document.getElementById(targetFieldId).focus();	
}

</script>
  
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
	<form:hidden path="currency" />
	<tr>
		<th colspan="2"><f:message key="bankCodeSearchTitle"/></th>
	</tr>
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="businessUnit"/>: </td>
		<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="buName" readonly="true"/>
				<form:errors path="buName" cssClass="redstar"/></td>
	</tr>
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="bankCode"/>: </td>
		<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="bankCode" />
				<form:errors path="bankCode" cssClass="redstar"/></td>
	</tr>
	<tr>
		<td class="TDShade" nowrap><f:message key="bankCodeDescription"/>:</td>
		<td class="TDShadeBlue">
			<form:input cssClass="inputBox" path="description" />
			<form:errors path="description" cssClass="redstar"/>	
		</td>
	</tr>

</table>
<script>
  document.searchForm.bankCode.focus();
</script>