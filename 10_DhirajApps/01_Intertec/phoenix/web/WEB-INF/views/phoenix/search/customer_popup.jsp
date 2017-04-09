<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script language="JavaScript">
function customerSelected(cccCode, contractStatus, targetFieldId){
	var validationCheck = "false";
	if(contractStatus != null && (contractStatus != 'APP' && contractStatus != 'INPR') )
	{
		confirm("This contract is InActive. Please contact Regional Billing Center!");
		validationCheck="true";
	}
	if(validationCheck=='true'){
		return false;
	}
	//confirm("selected " + cccCode);
	window.top.document.getElementById(targetFieldId).value = cccCode;
	window.top.document.getElementById('actionFlag').value = 'addCustomer';
	var name=document.searchForm.searchForm.value;
	window.top.document.forms[name].submit();
}
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:right:5px;">
	<tr>
		<th colspan="2"><f:message key="contractIDDescription"/></th>
	</tr>
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="addCustomer"/> : </td>
		<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="searchValue" /></td>
	</tr>
</table>
