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
<title>CE invoice Preview</title>
<script type="text/javascript" src="js/balloontip.js"></script>
<script type="text/javaScript" src="js/tabs.js"></script>
<script language="javaScript" src="js/flipmenu.js"></script>
<script type="text/javaScript" src="js/lookup.js"></script>
<script type="text/JavaScript" src="js/mm_menu.js"></script>
<script type="text/javascript" src="js/balloontip.js"></script>
<script type="text/javascript" src="js/globalFunctions.js"></script>
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="js/dw_viewport.js" type="text/javascript"></script>
<script src="js/dw_event.js" type="text/javascript"></script>
<script src="js/dw_tooltip_sel.js" type="text/javascript"></script>
<script language="javascript" src="js/ce/ce_invoice_preview.js"></script>
</HEAD>

<form:form name="ceJobInvoicePreviewTab1Form" method="POST" action="phx_ce_job_invoice_preview_tab1.htm">
<form:hidden path="refreshing" />
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-left-width:0px;">
	<tr>
		<th>&nbsp;</th>
		<th nowrap>&nbsp;</th>
		<th nowrap>&nbsp;</th>
		<th nowrap>&nbsp;</th>
		<th nowrap>&nbsp;</th>
		<th nowrap>&nbsp;</th>
	</tr>	
	<tr>
	  <td height="22" colspan="4" nowrap>&nbsp;</td>
	  <td nowrap>&nbsp;</td>
  </tr>
	<tr>		
		<th nowrap><f:message key="lineNo"/></th>
		<th style="height:24px;" nowrap><f:message key="status"/></th>
		<th nowrap><f:message key="serviceOffering"/></th>
		<th nowrap><f:message key="description"/></th>
		<th rowspan="8" nowrap class="nopadding" valign="top">
<div id="innertbl" style=" width:650px;overflow-x: auto; overflow-y: hidden; padding:0px; border:0px; height:100%">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style=" border:none;">
          
          <tr>
            <th nowrap><f:message key="poNo"/></th>
             <th nowrap>Distribution Amnt </th>
	    <th style="height:24px;" nowrap><f:message key="price"/></th>
            <th nowrap><f:message key="primaryBranch"/></th>
            <th nowrap><f:message key="busSteam"/></th>
            <th nowrap><f:message key="serviceType"/></th>
            <th nowrap><f:message key="revenueSegregation"/> </th>
            <th nowrap><f:message key="comments"/> </th>
			<th nowrap>&nbsp;</th>
          </tr>
          
          <c:forEach items="${command.ceInvLineItemForms}" var="lineItem" varStatus="status">
           	<tr>
           		<td height="25" valign="middle" >
	           		<%--<authz:authorize ifAnyGranted="CreatePurchaseOrder">--%>
				        <a href="phx_create_purchase_order.htm?reqFrom=searchPurchaseOrderForm&po.poNumber=${lineItem.joLineItemForm.poNumber}&edit=true" target="_parent">
					        ${lineItem.joLineItemForm.poNumber}
					    </a>
		    	    <%--</authz:authorize>
		        	<authz:authorize ifNotGranted="CreatePurchaseOrder">
		        		${lineItem.joLineItemForm.poNumber}
		        	</authz:authorize>--%>
          		</td> 

				<td >
					<form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].netPrice" disabled="true"/>
				</td>

				<td height="25" >
				<%--<form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].joLineItemForm.price" disabled="true"/>--%> 
				<form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].total" disabled="true"/>
				</td>
	            <td >${lineItem.joLineItemForm.primaryBranch}</td>
	            <td >${lineItem.joLineItemForm.busStream}</td>
	            <td >&nbsp;<!-- ${lineItem.joLineItemForm.serviceType}--></td>
		    <td ><a href="#" onClick="javascript:updateRevenueSegregationIframeSrc(${status.index});parent.popup_show('revenuesegregation', 'revenuesegregation_drag', 'revenuesegregation_exit', 'screen-corner', 40, 80); parent.showrevenuesegregation(); parent.hideIt();parent.popupboxenable();">view</a></td>
			<td><form:input cssClass="inputBox" size="60" path="ceInvLineItemForms[${status.index}].lineComment" disabled="${lineItem.joLineItemForm.isSplit}"/>  </td>
			<td>&nbsp;</td>
		    </tr>
		    
		    <!-- Split Line items -->
	            <c:forEach items="${lineItem.joLineItemForm.splitLineItemForms}" var="splitItem" varStatus="splitStatus">
	            <tr>
	           		<td height="25" valign="middle" >
		           		<%--<authz:authorize ifAnyGranted="CreatePurchaseOrder">--%>
					        <a href="phx_create_purchase_order.htm?reqFrom=searchPurchaseOrderForm&po.poNumber=${splitItem.poNumber}&edit=true" target="_parent">
						        ${splitItem.poNumber}
						    </a>
			    	    <%--</authz:authorize>
			        	<authz:authorize ifNotGranted="CreatePurchaseOrder">
			        		${splitItem.poNumber}
			        	</authz:authorize>--%>
	          		</td> 
					<td >
						<form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].distributionAmount" disabled="true"/>
					</td>
					<td height="25" >&nbsp;</td>
		            <td >${splitItem.primaryBranch}</td>
		            <td >${splitItem.busStream}</td>
		            <td >&nbsp;<!-- ${splitItem.serviceType}--></td>
			    	<td >&nbsp;</td>
			    </tr>
	          </c:forEach>
		  <!-- Split Line items End -->  
		  
		  </c:forEach>
          
          <tr>
			<td height="25" valign="middle" >&nbsp;</td>
            <td valign="middle" class="tdr"><f:message key="total"/>: </td>
            <td nowrap >
	            <div align="left">
	               <form:input cssClass="inputBox" size="12" path="total" disabled="true"/>
	            </div>
            </td>
            <td height="25" >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>

        </table>
	</div>
	</th>
	</tr>
	
    <c:forEach items="${command.ceInvLineItemForms}" var="lineItem" varStatus="status">
		<tr>			
			<td height="25" align='left' nowrap>${lineItem.joLineItemForm.lineNumber}</td>
			<td >
			  <form:select id="sel1" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].status"
	             items="${lineItem.invoiceStatus}" itemLabel="name" itemValue="value"/>
	          <form:errors path="ceInvLineItemForms[${status.index}].status" cssClass="redstar"/>   
		   </td>
		 <td >
			  <form:select id="sel1" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].serviceOffering"
	             items="${lineItem.serviceOfferings}" itemLabel="name" itemValue="value"/>
	          <form:errors path="ceInvLineItemForms[${status.index}].serviceOffering" cssClass="redstar"/>   
		   </td>
		   
		   
		   <td >
			   <form:input cssClass="inputBox" size="65" path="ceInvLineItemForms[${status.index}].joLineItemForm.description" disabled="true"/>
			   <form:errors path="ceInvLineItemForms[${status.index}].joLineItemForm.description" cssClass="redstar"/>
		   </td>
		</tr>
		
	    <!-- Split Line items -->
	    <c:forEach items="${lineItem.joLineItemForm.splitLineItemForms}" var="splitItem" varStatus="splitStatus">

		<tr>
			<td height="25" align='left' nowrap>&nbsp;</td>
			<td >${splitItem.lineNumber}</td>
			<td >
			   <form:select id="sel2" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].status"
	            items="${lineItem.invoiceStatus}" itemLabel="name" itemValue="value"/>
		   </td>
		   <td >
			   <form:input cssClass="inputBox" size="65" path="ceInvLineItemForms[${status.index}].joLineItemForm.splitLineItemForms[${splitStatus.index}].description"/>
		   </td>
		</tr>
	    
		</c:forEach>
		<!-- Split Line items end -->
		
	</c:forEach>

		<tr>
  			<td height="45" align='left'>&nbsp;</td>
  			<td >&nbsp;</td>
  			<td >&nbsp;</td>
  			<td height="45" >&nbsp;</td>
  		</tr>
</table>
</form:form> 
</HTML>
