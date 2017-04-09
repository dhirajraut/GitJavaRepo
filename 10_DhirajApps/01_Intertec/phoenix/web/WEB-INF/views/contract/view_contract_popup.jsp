<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script>
</script>
 <icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
<icb:list var="workingPb">
  <icb:item>workingPb</icb:item>
</icb:list>
<icb:list var="workingUOM">
  <icb:item>workingUOM</icb:item>
</icb:list>
<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="activeCurrencyCDList">
  <icb:item>${icbfn:activeCurrencyCD(command)}</icb:item>
</icb:list>
<form:form name="contractCreatePopUpForm" method="POST" action="view_contract_popup.htm">
 <input type="hidden" name="pageNumber" value="1" />
 <input type="hidden" name="status" value="1" />	
	<form:hidden path="rowNum"/>
	<form:hidden path="inputFieldId"/>
    <form:hidden path="searchForm" /> 
	<form:hidden path="div1"/>
	<form:hidden path="div2"/>

 	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>
			
	  <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
            
                  <tr>
                    <th class="TDShade" colspan="6"><f:message key="contractid"/><strong>:${command.contract.contractCode}</strong><img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
					<f:message key="status"/><strong>:</strong> 
					<form:select disabled="true" cssClass="selectionBox" path="status" items="${icbfn:dropdown('staticDropdown',activeStatus)}" itemLabel="name" itemValue="value"/>
					<f:message key="workingPB"/><strong> :</strong>
					<form:select id="sel1" cssClass="selectionBox" path="contract.workingPB" items="${icbfn:dropdown('workingPriceBookIdDropdown',activeCurrencyCDList)}" itemLabel="name" itemValue="value" disabled="true"/>
					<f:message key="workingUOM"/><strong> :</strong>
        			<form:select id="sel2" cssClass="selectionBox" path="contract.workingUOM" items="${icbfn:dropdown('staticDropdown',workingUOM)}" itemLabel="name" itemValue="value" disabled="true"/>
					<f:message key="masterListDate"/><strong>:</strong>
					 <form:input disabled="true" cssClass="inputBox" id="masterListDate" path="contract.masterListDate" size="10" maxlength="12"/>
                      <a href="#" onClick="displayCalendar(document.forms[0].masterListDate,'mm/dd/yyyy',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></th>
                    	 <form:errors path="contract.masterListDate" cssClass="redstar"/>
                  </tr>	

                  <tr>
                    <td class="TDShade">	<f:message key="statusdate"/><strong>:</strong></td>
                    <td class="TDShadeBlue" >
                       <form:input cssClass="inputBox" disabled="true" id="statusDate" path="contract.statusDate" size="10" maxlength="12"/>
                      &nbsp;<a href="#" onClick="displayCalendar(document.forms[0].statusDate,'mm/dd/yyyy',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
					   <form:errors path="contract.masterListDate" cssClass="redstar"/>
                    </td>
					<td class="TDShade"><f:message key="description"/><strong>:</strong></td>
                    <td class="TDShadeBlue" colspan="4" >
                      <form:input disabled="true" cssClass="inputBox" path="contract.description" size="35" maxlength="512"/>
					  <form:errors path="contract.description" cssClass="redstar"/></td>
                  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="invoiceType"/><strong>:</strong></td>
                    <td class="TDShadeBlue"><form:select disabled="true" cssClass="selectionBox" path="contract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value"/></td>
                    <td class="TDShade"><f:message key="paymentterms"/><strong>:</strong></td>
                    <td class="TDShadeBlue"><form:select disabled="true" cssClass="selectionBox" path="contract.paymentTermsCD" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value"/></td>
				  <!-- this close tr was missing -->
				  </tr>
                  <tr>
                    <td class="TDShade"><f:message key="versions"/><strong>:</strong></td>
                    <td class="TDShadeBlue" colspan="5">&nbsp;</td>
                  </tr>
                 <tr>
                          <td class="TDShade" style="border-top:#CCCCCC dashed 1px;">
						  <f:message key="expirationDate"/><strong>:</strong></td>
						  <td class="TDShadeBlue" style="border-top:#CCCCCC dashed 1px;">
											
						 <form:input cssClass="inputBox" disabled="true" id="expireDate" path="contract.expireDate" size="10" maxlength="12"/>
                          &nbsp;<a href="#" onClick="displayCalendar(document.forms[0].expireDate,'mm/dd/yyyy',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/>
						   <form:errors path="contract.expireDate" cssClass="redstar"/>
						  </td>
						 <td class="TDShade"><f:message key="contractCode"/><strong>:</strong></td>
						 <td class="TDShadeBlue">
						 <form:input id="contractCode" disabled="true" cssClass="inputBox" path="contract.contractCode" size="10" maxlength="15"/>
						  <form:errors path="contract.contractCode" cssClass="redstar"/>	 </td>
					</tr>
                     <tr>
                                            <td class="TDShade"><f:message key="originator"/><strong>:</strong></td>
                                            <td class="TDShadeBlue">
											<form:input disabled="true" cssClass="inputBox" path="contract.originatorUserName" size="10" maxlength="128"/>
											<form:errors path="contract.originatorUserName" cssClass="redstar"/></td>
                                            <td class="TDShade"><f:message key="signatory"/><strong>:</strong></td>
										    <td class="TDShadeBlue" colspan="3"> 
											<form:input disabled="true" cssClass="inputBox" path="contract.signatoryUserName" size="10" maxlength="128"/></td>
											<form:errors path="contract.signatoryUserName" cssClass="redstar"/>
                    </tr>

                    <tr>
                                            <td class="TDShade"><f:message key="originatorEmail"/>:</td>
                                            <td class="TDShadeBlue"><form:input disabled="true" cssClass="inputBox" path="contract.originator.email" size="10" maxlength="70"/>
											<form:errors path="contract.originator.email" cssClass="redstar"/></td>
                                            <td class="TDShade"><f:message key="signatoryEmail"/>: </td>
                                            <td class="TDShadeBlue" colspan="3"><form:input disabled="true" cssClass="inputBox" path="contract.signatory.email" size="10" maxlength="70"/>
											<form:errors path="contract.signatory.email" cssClass="redstar"/></td>
                   </tr>          

                
       </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                   		<td>			
						 <input type="button" value="Close" name="Close" class="button1" onclick="javascript:top.hidePopupDiv('viewcontract${command.rowNum}','viewcontractbody${command.rowNum}');top.popupboxclose();"/>
                  		</td>
                      </tr>						
                    </table>
				  </td>								
                </tr>

  </table>
 </div>
</form:form>       
      
