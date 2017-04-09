<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script language="javascript" src="js/po/search_po.js"></script>
<script language="javascript" src="js/po/create_po.js"></script>
<script language="JavaScript">
function custCodeSelected(custCode, custName, add1, add2, add3, add4, city, postal, country, state, location, targetFieldId) {
    top.popupboxclose();   
    top.hidePopupDiv('companyname','companynamebody');      
	window.top.document.getElementById('cust').value=custCode;	
	window.top.document.getElementById('name').innerHTML =custName;
	window.top.document.getElementById('address1').innerHTML=add1;
	window.top.document.getElementById('address2').innerHTML=add2;
	window.top.document.getElementById('address3').innerHTML=add3;
	window.top.document.getElementById('address4').innerHTML=add4;
	window.top.document.getElementById('city').innerHTML=city;
	window.top.document.getElementById('postal').innerHTML=postal;
	window.top.document.getElementById('country').innerHTML=country;
	window.top.document.getElementById('state').innerHTML=state;
	window.top.document.getElementById('location').value=location;	
	window.top.document.getElementById(targetFieldId).focus();
}
</script>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:right:5px;">
	<tr>
		<th colspan="2"><f:message key="customerSearch"/></th>
	</tr>
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="customerid"/>: </td>
		<td width="80%" class="TDShadeBlue">
		   <form:input cssClass="inputBox" path="custCode" />		   
		</td>   
	</tr>
	<tr>
		<td class="TDShade" nowrap><f:message key="customerName"/>:</td>
		<td class="TDShadeBlue">
			<form:input cssClass="inputBox" maxlength="60" path="name" />			
		</td>
	</tr>						
</table>