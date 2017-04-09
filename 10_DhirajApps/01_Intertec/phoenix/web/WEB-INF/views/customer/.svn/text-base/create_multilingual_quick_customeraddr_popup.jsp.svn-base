<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<head>
<script language="javascript">


function custLanguageChange(custAddrId)
{
	document.createMultiligualQuickCustomerAddrForm.custLanguageFlag.value="true";
	document.createMultiligualQuickCustomerAddrForm.custAddrId.value=custAddrId;
	document.createMultiligualQuickCustomerAddrForm.lcode.value=document.getElementById("langcode").value;
	document.createMultiligualQuickCustomerAddrForm.submit()
}
function contactLanguageChange(contactAddrId)
{
	document.createMultiligualQuickCustomerAddrForm.contactLanguageFlag.value="true";
	document.createMultiligualQuickCustomerAddrForm.contactAddrId.value=contactAddrId;

	document.createMultiligualQuickCustomerAddrForm.lcode.value=document.getElementById("langcode").value;
	document.createMultiligualQuickCustomerAddrForm.submit()
}


</script>
</head>


<icb:list var="selectedLanguage">
<icb:item>selectedLanguage</icb:item>
</icb:list>

<form:form name="createMultiligualQuickCustomerAddrForm" method="POST" action="create_multilingual_quick_customeraddr_popup.htm">
 
 <form:hidden path="searchForm" /> 
 <form:hidden path="divName"/>
 <form:hidden path="divBody"/>
 <form:hidden path="custAddrId"/>
 <form:hidden path="contactAddrId"/>
 <input type="hidden" name="lcode" value="1" />
 <input type="hidden" name="custLanguageFlag" value="false" />
 <input type="hidden" name="contactLanguageFlag" value="false" />
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

              <table width="100%" cellpadding=0 cellspacing=0  border="0" class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=2 width="50%"><f:message key="multilingualCustomerAddress"/></th>
                    
                    <th colspan=2 width="50%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                  </tr>
                
				  <tr>
					<td class="TDShade"><f:message key="languageCode"/> <strong> <span class="redstar">*</span>:</strong> </td>
                    <td  class="TDShadeBlue">
                     <%--  <form:select id="langcode" cssClass="selectionBox"  path="custAddressLanguage.custAddressLanguageId.languageCD" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value" onchange="languageChange('${command.custAddrId}','${command.contactAddrId}');"/>
                      <form:errors path="custAddressLanguage.custAddressLanguageId.languageCD" cssClass="redstar"/> --%>

					  <c:choose>
					  <c:when test="${command.custAddress == 'custAddress'}">
					  <form:select id="langcode" cssClass="selectionBox"  path="custAddressLanguage.custAddressLanguageId.languageCD" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value" onchange="custLanguageChange('${command.custAddrId}');"/>
                      <form:errors path="custAddressLanguage.custAddressLanguageId.languageCD" cssClass="redstar"/> 
					  </c:when>
					  <c:otherwise>
					   <form:select id="langcode" cssClass="selectionBox"  path="custAddressLanguage.custAddressLanguageId.languageCD" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value" onchange="contactLanguageChange('${command.contactAddrId}');"/>
					  <form:errors path="custAddressLanguage.custAddressLanguageId.languageCD" cssClass="redstar"/>
					  </c:otherwise>
					  </c:choose>
                    </td>
					<td class="TDShade"><f:message key="address1"/> <strong> :</strong></td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="55" path="custAddressLanguage.address1"/>
                      <form:errors path="custAddressLanguage.address1" cssClass="redstar"/>
                    </td>
                  </tr>


                  <tr>
                    <td class="TDShade"><f:message key="address2"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="55" path="custAddressLanguage.address2"/><form:errors path="custAddressLanguage.address2" cssClass="redstar"/></td>


                   
					<td class="TDShade"><f:message key="address3"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="35" maxlength="55" path="custAddressLanguage.address3"/>
					<form:errors path="custAddressLanguage.address3" cssClass="redstar"/></td>
                    </td>
                  </tr>

				   <tr>
                    <td class="TDShade"><f:message key="address4"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="55" path="custAddressLanguage.address4"/><form:errors path="custAddressLanguage.address4" cssClass="redstar"/></td>


                   
					<td class="TDShade"><f:message key="city"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="35" maxlength="30"  path="custAddressLanguage.city"/>
					<form:errors path="custAddressLanguage.city" cssClass="redstar"/></td>
                    </td>
                  </tr>

				   <tr>
                    <td class="TDShade"><f:message key="county"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35" maxlength="30" path="custAddressLanguage.county"/><form:errors path="custAddressLanguage.county" cssClass="redstar"/></td>


                   
					<td class="TDShade"><f:message key="postal"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" maxlength="12" size="35" path="custAddressLanguage.postal"/>
					<form:errors path="custAddressLanguage.postal" cssClass="redstar"/></td>
                    </td>
                  </tr>
                  
                  <tr>
                    <td class="TDShade"><f:message key="customerName"/> <strong> :</strong></td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="custAddressLanguage.name"/>
                      <form:errors path="custAddressLanguage.name" cssClass="redstar"/>
                    </td>
                    <td class="TDShade"><f:message key="repDirector"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="64"  path="custAddressLanguage.repDirector"/><form:errors path="custAddressLanguage.repDirector" cssClass="redstar"/></td>
					
                  </tr>
                  <tr>
                  <td class="TDShade"><f:message key="businessType"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="35"  maxlength="128" path="custAddressLanguage.businessType"/>
					<form:errors path="custAddressLanguage.businessType" cssClass="redstar"/></td>
                    </td>
                    <td class="TDShade"><f:message key="businessItem"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="128" path="custAddressLanguage.businessItem" />
                      <form:errors path="custAddressLanguage.businessItem" cssClass="redstar"/>					  
					  </td>
                  </tr>
				  		
                  <tr>
			<td class="TDShade">
			<input type="submit"  class="button1" value="Save"/>
			<%-- <input type="submit"  class="button1" value="Save" onclick="javascript:top.hidePopupDiv('${command.divName}','${command.divBody}');top.popupboxclose();"/> --%>
	   <a href="#"><input name="Submit3" type="button" class="button1" value="Cancel" onclick="javascript:top.hidePopupDiv('${command.divName}','${command.divBody}');top.popupboxclose();"/></a></td>
      <td class="TDShade">&nbsp;</td>
      <td colspan="3" class="TDShade">&nbsp;</td>
    </tr>
  </tbody>
</table>
 
</form:form>

