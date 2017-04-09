<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script	type="text/javascript" >

function locationSelected(custCode, custName, add1, add2, add3, add4, city, postal, country, state, location, targetFieldId) {
 top.popupboxclose();   
    top.hidePopupDiv('addressseq','addressseqbody');
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
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
	<tr>
		<th colspan="2"><f:message key="addresssequence"/></th>
	</tr>
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="locationNo"/>: </td>
		<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="locationNumber"/>
				<form:errors path="locationNumber" cssClass="redstar"/></td>
	</tr>
	<tr>
		<td width="20%" class="TDShade" nowrap><f:message key="addressDescription"/>: </td>
		<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="description" />
				<form:errors path="description" cssClass="redstar"/></td>
	</tr>
	

</table>
