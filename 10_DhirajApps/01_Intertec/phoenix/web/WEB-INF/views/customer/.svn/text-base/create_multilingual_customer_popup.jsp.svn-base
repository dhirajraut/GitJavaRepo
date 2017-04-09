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
	 
function languageChange()
{
	document.createMultiligualCustomerForm.languageFlag.value="true";
	document.createMultiligualCustomerForm.lcode.value=document.getElementById("langcode").value;
	document.createMultiligualCustomerForm.submit();
}
</script>
</head>

<icb:list var="selectedLanguage">
  <icb:item>selectedLanguage</icb:item>
</icb:list>

<form:form name="createMultiligualCustomerForm" method="POST" action="create_multilingual_customer_popup.htm">
<form:hidden path="searchForm" /> 
 <form:hidden path="divName"/>
 <form:hidden path="divBody"/>
 <input type="hidden" name="lcode" value="1" />
 <input type="hidden" name="languageFlag" value="false" />
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>

              <table width="100%" cellpadding=0 cellspacing=0  border="0" class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th colspan=2 width="50%"><f:message key="multilingualCustomer"/></th>
                    
                    <th colspan=2 width="50%" bgcolor="#ffffff" style="text-align:right">&nbsp;</th>
                  </tr>
                
				  <tr>
                    <td class="TDShade"><f:message key="customerName"/> <strong> <span class="redstar">*</span>:</strong></td>
                    <td class="TDShadeBlue">
                      <form:input cssClass="inputBox" size="35" maxlength="128" path="customerLanguage.name"/>
                      <form:errors path="customerLanguage.name" cssClass="redstar"/>
                    </td>
                   
					
					<td class="TDShade"><f:message key="languageCode"/> <strong> <span class="redstar">*</span>:</strong> </td>
                    <td  class="TDShadeBlue">
                       <form:select id="langcode" cssClass="selectionBox" path="customerLanguage.customerLanguageId.languageCD" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value" onchange="languageChange();"/>
                      <form:errors path="customerLanguage.customerLanguageId.languageCD" cssClass="redstar"/>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="repDirector"/>:</td>
                    <td class="TDShadeBlue"><form:input cssClass="inputBox" size="35"  maxlength="64"  path="customerLanguage.repDirector"/><form:errors path="customerLanguage.repDirector" cssClass="redstar"/></td>
					<td class="TDShade"><f:message key="businessType"/>:</td></td>
                    <td  class="TDShadeBlue">
                     <form:input cssClass="inputBox" size="35"  maxlength="128" path="customerLanguage.businessType"/>
					<form:errors path="customerLanguage.businessType" cssClass="redstar"/></td>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="businessItem"/>: </td>
                    <td class="TDShadeBlue">					
			      		<form:input cssClass="inputBox" size="35"  maxlength="128" path="customerLanguage.businessItem" />
                      <form:errors path="customerLanguage.businessItem" cssClass="redstar"/>					  
					  </td>
                   <td class="TDShade"></td>
                   <td  class="TDShadeBlue"></td>
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

