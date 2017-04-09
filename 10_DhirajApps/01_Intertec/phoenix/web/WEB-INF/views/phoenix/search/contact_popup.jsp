<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="js/ce/ce_services.js"></script>

<script language="JavaScript">
function customerSelected(callerRowIndex, custCode, name, contactId, fullName, addressId, address, targetFieldId){
	window.top.document.createJobsCEForm.tabName.value = "1";
	if ( 'billing' == targetFieldId ){
	//confirm( targetFieldId + callerRowIndex + " " + contactId + " " + custCode + " " + fullName +  " " + addressId + " " + address);
		window.top.document.getElementById('billingCustCode'+callerRowIndex).value=custCode;
		window.top.document.getElementById('billingContactId'+callerRowIndex).value=contactId;
		window.top.document.getElementById('billingContactName'+callerRowIndex).value=fullName;
		window.top.document.getElementById('billingAddressId'+callerRowIndex).value=addressId;
		window.top.document.getElementById('billingContactAddress'+callerRowIndex).value=replaceSpecialCharacterSymbols(address);
		window.top.document.getElementById('custInfo'+callerRowIndex).value=custCode+"-"+name;
	    window.top.document.getElementById('billingContactId'+callerRowIndex).focus();
	    
	}else if ('repreceiving' == targetFieldId ){
		//confirm( targetFieldId + " " + contactId + " " + custCode + " " + fullName +  " " +  addressId + " " + address);
		window.top.document.getElementById('reportReceivingCustCode'+callerRowIndex).value=custCode;
		window.top.document.getElementById('reportReceivingContactId'+callerRowIndex).value=contactId;
		window.top.document.getElementById('reportReceivingContactName'+callerRowIndex).value=fullName;
		window.top.document.getElementById('reportReceivingAddressId'+callerRowIndex).value=addressId;
		window.top.document.getElementById('reportReceivingContactAddress'+callerRowIndex).value=replaceSpecialCharacterSymbols(address);
		window.top.document.getElementById('recvngCustInfo'+callerRowIndex).value=custCode+"-"+name;
	    window.top.document.getElementById('reportReceivingContactId'+callerRowIndex).focus();
	}else if ('oem' == targetFieldId ){
		//confirm( targetFieldId + " " + contactId + " " + custCode + " " + fullName +  " " +  addressId + " " + address);
		window.top.document.getElementById('manfCustCode'+callerRowIndex).value=custCode;
		window.top.document.getElementById('oemContactId'+callerRowIndex).value=contactId;
		window.top.document.getElementById('oemContactName'+callerRowIndex).value=fullName;
		window.top.document.getElementById('manfAddressId'+callerRowIndex).value=addressId;
		window.top.document.getElementById('oemContactAddress'+callerRowIndex).value=replaceSpecialCharacterSymbols(address);
	    window.top.document.getElementById('oemContactId'+callerRowIndex).focus();
	}else if ('supply' == targetFieldId ){
		//confirm( targetFieldId + " " + contactId + " " + custCode + " " + fullName +  " " +  addressId + " " + address);
		window.top.document.getElementById('supplierCustCode'+callerRowIndex).value=custCode;
		window.top.document.getElementById('supplierContactId'+callerRowIndex).value=contactId;
		window.top.document.getElementById('supplierContactName'+callerRowIndex).value=fullName;
		window.top.document.getElementById('supplierAddressId'+callerRowIndex).value=addressId;
		window.top.document.getElementById('supplierContactAddress'+callerRowIndex).value=replaceSpecialCharacterSymbols(address);
	    window.top.document.getElementById('supplierContactId'+callerRowIndex).focus();
	}
	
	top.popupboxclose();   
    top.hidePopupDiv('searchcontact','contactbody');  
	window.close();
//	var name=document.searchForm.searchForm.value;
//	window.top.document.forms[name].submit();
}

	function consolinvSelected(custCode, custName, contactId,fullName,addressId,add1, add2, add3, add4, city,state, postal, country,location, targetFieldId){
	window.top.document.getElementById('contactName').innerHTML=fullName;
	window.top.document.getElementById('address1').innerHTML=add1;	
	window.top.document.getElementById('address2').innerHTML=add2;
	window.top.document.getElementById('address3').innerHTML=add3;
	window.top.document.getElementById('address4').innerHTML=add4;
	window.top.document.getElementById('city').innerHTML=city;	
	window.top.document.getElementById('postal').innerHTML=postal;
	window.top.document.getElementById('country').innerHTML=country;
	window.top.document.getElementById('state').innerHTML=state;
	window.top.document.getElementById('location').value=location;
	window.top.document.getElementById(targetFieldId).value=contactId;
	window.top.document.getElementById(targetFieldId).focus();
	top.popupboxclose();   
    top.hidePopupDiv('contact','contactbody');  
	window.close();	
}



</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:right:5px;">
						<tr>
							<th colspan="2"><f:message key="searchContact"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="customerID"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<form:input cssClass="inputBox" path="custCode" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="customerName"/>: </td>
							<td width="80%" class="TDShadeBlue">
								<form:input cssClass="inputBox" path="customerName" />
							</td>
						</tr>
						<tr>
							<td class="TDShade"><f:message key="contactID"/>:</td>
							<td class="TDShadeBlue">
								<form:input cssClass="inputBox" path="contactId" />
							</td>
						</tr>
						<tr>
							<td class="TDShade"><f:message key="contactName"/> : </td>
							<td class="TDShadeBlue"><form:input cssClass="inputBox" path="contactName" /></td>
						</tr>

					</table>
