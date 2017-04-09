<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script language="JavaScript">
function contractSelected(callerRowIndex, contractCode, custCode, targetFieldId) {
    top.popupboxclose();	
    top.hidePopupDiv('parentcontract','parentcontract');
	window.top.document.getElementById('parentcontract'+callerRowIndex).value=contractCode;
	window.top.document.getElementById('parentCust'+callerRowIndex).value=custCode;
	window.top.document.getElementById(targetFieldId).focus();
}
</script>

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="mainApplTable" style="padding: right :   5px;">
	<tr>
		<th colspan="2"><f:message key="parentContractSearchTitle" /></th>
	</tr>
	
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="custCode" />:</td>
		<td width="80%" class="TDShadeBlue"><form:input id="customerCode" cssClass="inputBox" path="customerCode" /></td>
	</tr>
	
	<tr>
		<td class="TDShade" nowrap><f:message key="customerName" />:</td>
		<td class="TDShadeBlue"><form:input cssClass="inputBox" path="customerName" /></td>
	</tr>
	
	<tr>
		<td class="TDShade" nowrap><f:message key="contractCode" /> :</td>
		<td class="TDShadeBlue"><form:input cssClass="inputBox"	path="contractCode" /></td>
	</tr>

</table>