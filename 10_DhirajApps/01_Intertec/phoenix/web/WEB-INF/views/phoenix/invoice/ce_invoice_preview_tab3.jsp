<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<title>Intertek - Job Search Results</title>
<script type="text/javascript" src="js/balloontip.js"></script>
<script type="text/JavaScript" src="js/mm_menu.js"></script>
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="js/dw_viewport.js" type="text/javascript"></script>
<script src="js/dw_event.js" type="text/javascript"></script>
<script src="js/dw_tooltip_sel.js" type="text/javascript"></script>
<script language="javascript" src="js/ce/ce_invoice_preview.js"></script>
</HEAD>
<form:form name="ceJobInvoicePreviewTab3Form" method="POST" action="phx_ce_job_invoice_preview_tab3.htm">
<form:hidden path="refreshing" />
<div class="redtext">
  <form:errors cssClass="error"/>
</div>


<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-left-width:0px;">
  <tr>
    <th>&nbsp;</th>
    <th nowrap>&nbsp;</th>
    <th nowrap>&nbsp;</th>
    <th nowrap>&nbsp;</th>
    <th nowrap>&nbsp;</th>
    <th rowspan="9" nowrap class="nopadding">
    	<div id="innertbl" style=" width:650px;overflow-x: auto; overflow-y: hidden; padding:0px; border:0px; height:100%">
      		<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style=" border:none;">
        	<tr>
          		<th nowrap>&nbsp;</th>
          	</tr>
        	<tr>
          		<td nowrap>&nbsp;</td>
          	</tr>
        	<tr>
          		<th nowrap><f:message key="apply"/></th>
          		<th nowrap><f:message key="tobeApply"/></th>
          		<th nowrap><f:message key="depInvoiceNo"/></th>
          	</tr>
          	
          	<c:forEach items="${command.ceInvLineItemForms}" var="lineItem" varStatus="status">
	        	<tr valign='center'>
	        		<c:choose >
	        			<c:when test="${command.depositInvoiceForms != null}">
	          				<td align='left' valign="middle" height="25"><a href="#" onClick="javascript:updateApplyDepositIframeSrc(${status.index});parent.popup_show('applydeposit', 'applydeposit_drag', 'applydeposit_exit', 'screen-corner', 160, 160); parent.showapplydeposit(); parent.hideIt();parent.popupboxenable();">Apply</a></td>
	        				<td align='left' valign="middle" height="25">
							${command.ceInvLineItemForms[status.index].tobeApplied}
							</td>
							<td align='left' valign="middle" height="25">
							${command.ceInvLineItemForms[status.index].depositInvoiceNumber}
							</td>
	        			</c:when>
						<c:otherwise>
							<td>No deposit invoice available</td>
						</c:otherwise>	        			
	        		</c:choose>
	          	</tr>
	          	
	          	<!-- Split Line items -->
		            <c:forEach items="${lineItem.joLineItemForm.splitLineItemForms}" var="splitItem" varStatus="splitStatus">
		               <tr valign='center'>
				          <td align='left' valign="middle" height="25">&nbsp;</td>
          			   </tr>
	   	            </c:forEach>
	  		    <!-- Split Line items End -->  
	        </c:forEach> 
     		</table>
    	</div>
    </th>
  </tr>
  <tr>
    <td colspan="4" nowrap>&nbsp;</td>
  </tr>
  <tr> 
    <th>&nbsp;</th>   
    <th nowrap><f:message key="lineNo"/></th>
    <th nowrap><f:message key="status"/></th>
    <th nowrap><f:message key="serviceOffering"/></th>
    <th nowrap><f:message key="description"/></th>
  </tr>


    <c:forEach items="${command.ceInvLineItemForms}" var="lineItem" varStatus="status">
		<tr valign='center'>	
		    <td>&nbsp;</td>		
			<td height="25" align='left' nowrap valign="middle" >${lineItem.joLineItemForm.lineNumber}</td>
			<td align='left' valign="middle" >
			  <form:select id="sel1" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].status"
	             items="${lineItem.invoiceStatus}" itemLabel="name" itemValue="value"/>
	          <form:errors path="ceInvLineItemForms[${status.index}].status" cssClass="redstar"/>  
		   </td>
		   <td >
			  <form:select id="sel1" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].serviceOffering"
	             items="${lineItem.serviceOfferings}" itemLabel="name" itemValue="value"/>
	          <form:errors path="ceInvLineItemForms[${status.index}].serviceOffering" cssClass="redstar"/>   
		   </td>
		   <td align='left' valign="middle" >
			   <form:input cssClass="inputBox" size="65" path="ceInvLineItemForms[${status.index}].joLineItemForm.description" disabled="true"/>
			   <form:errors path="ceInvLineItemForms[${status.index}].joLineItemForm.description" cssClass="redstar"/>
		   </td>
		</tr>
		
	    <!-- Split Line items -->
	    <c:forEach items="${lineItem.joLineItemForm.splitLineItemForms}" var="splitItem" varStatus="splitStatus">

		<tr valign='center'>
			<td height="25" align='left' nowrap>&nbsp;</td>
			<td align='left' valign="middle" >${splitItem.lineNumber}</td>
			<td align='left' valign="middle" >
			   <form:select id="sel2" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].status"
	            items="${lineItem.invoiceStatus}" itemLabel="name" itemValue="value"/>
	           <form:errors path="ceInvLineItemForms[${status.index}].status" cssClass="redstar"/> 
		   </td>
		   <td align='left' valign="middle" >
			   <form:input cssClass="inputBox" size="65" path="ceInvLineItemForms[${status.index}].joLineItemForm.splitLineItemForms[${splitStatus.index}].description"/>
			   <form:errors path="ceInvLineItemForms[${status.index}].joLineItemForm.splitLineItemForms[${splitStatus.index}].description" cssClass="redstar"/>
		   </td>
		</tr>
	    
		</c:forEach>
		<!-- Split Line items end -->
		
	</c:forEach>

  <tr valign='center'>
    <td height="25" align='left'>&nbsp;</td>
    <td align='left' valign="middle" >&nbsp;</td>
    <td align='left' valign="middle" >&nbsp;</td>
    <td height="45" align='left' valign="middle" >&nbsp;</td>
  </tr>
</table>


</form:form>
</HTML>
