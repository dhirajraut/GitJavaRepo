<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
<script type="text/javascript" src="js/globalFunctions.js"></script>
<script language="javascript" src="js/ce/ce_invoice_preview.js"></script>


<form:form name="applyDepositPopupForm" method="POST" action="phx_apply_deposit_popup.htm">
<div class="redtext">
  <form:errors cssClass="error"/>
</div>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <th>&nbsp;</th>
	  <th><f:message key="depositNumber" /></th>
	  <th><f:message key="netPrice" /></th>
      <th><f:message key="amount" /></th>
      <th><f:message key="balance" /></th>
	  <th><f:message key="amountToApply" /></th>
    </tr>

<c:forEach items="${command.depositInvoiceForms}" var="depInvoice" varStatus="status">
  <tr>
    <td><form:checkbox id="checkbox1"  path="depositInvoiceForms[${status.index}].selected"/></td>
	<td>${depInvoice.depInvoiceNumber}</td>
	<td>${command.ceInvoiceLineItemForm.netPrice}</td>
    <td><form:input cssClass="inputBox" size="12" path="depositInvoiceForms[${status.index}].depositAmount" disabled="true"/></td>
    <td><form:input cssClass="inputBox" size="12" path="depositInvoiceForms[${status.index}].availableAmount" disabled="true"/></td>
    <td>
       <form:input cssClass="inputBox" size="12" path="depositInvoiceForms[${status.index}].amountToApply"/>
       <form:errors path="depositInvoiceForms[${status.index}].amountToApply" cssClass="redstar"/>
    </td>
  </tr>
</c:forEach>  
 <tr>
  <td colspan="5">
    <input name="Button" type="button" class="button1" value="Submit" onClick="document.applyDepositPopupForm.submit();" /></td>
  </tr>
</table>
</form:form>
