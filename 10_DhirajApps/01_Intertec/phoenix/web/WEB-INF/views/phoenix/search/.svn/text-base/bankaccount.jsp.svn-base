<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script	type="text/javascript" >
function bankAccountSelected(bankAccountValue, targetFieldId){
   top.popupboxclose();   
   top.hidePopupDiv('searchaccount','accountbody');  
   	var innerTextStr =bankAccountValue;
	var currTokens = innerTextStr.split('|');
	window.top.document.getElementById(targetFieldId).value = currTokens[0].trim();
	window.top.document.getElementById("remitToBankCode0").value = currTokens[1].trim();
	window.top.document.getElementById("remitToBankAccountBuName0").value = currTokens[2].trim();
   window.top.document.getElementById(targetFieldId).focus();
}
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
</script>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
	<form:hidden path="buName" />
	<form:hidden path="currency" />
	
						<tr>
							<th colspan="2"><f:message key="bankAccountSearchTitle"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="bankCode"/>: </td>
							<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="bankCode" readonly="true"/>
									<form:errors path="bankCode" cssClass="redstar"/></td>
					</tr>


                  <tr>
							<td class="TDShade" nowrap><f:message key="bankAccount"/>:</td>
							<td class="TDShadeBlue">
								<form:input cssClass="inputBox" path="bankAcctCode" />
								<form:errors path="bankAcctCode" cssClass="redstar"/>	
							</td>
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
  document.searchForm.bankAcctCode.focus();
</script>