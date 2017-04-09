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
<title>Intertek - View All</title>
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
<script type="text/javascript" src="js/calendar.js"></script>
<script language="javascript" src="js/ce/ce_invoice_preview.js"></script>
</HEAD>


<form:form name="ceJobInvoicePreviewTab4Form" method="POST" action="phx_ce_job_invoice_preview_tab4.htm">
<div class="redtext">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="refreshing" />
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
  <tr>
    <th>&nbsp;</th>
    <th nowrap>&nbsp;</th>
    <th nowrap>&nbsp;</th>
     <th nowrap>&nbsp;</th>
    <th nowrap>&nbsp;</th>
    <th rowspan="10" nowrap class="nopadding"><div id="innertbl" style=" width:650px;overflow-x: auto; overflow-y: hidden; padding:0px; border:0px; height:100%">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style=" border:none;">
        <tr>
          <th nowrap>&nbsp;</th>
          <th nowrap>&nbsp;</th>
          <th nowrap>&nbsp;</th>
          <th nowrap>&nbsp;</th>
          <th nowrap>&nbsp;</th>
          <th nowrap><f:message key="taxCode"/></th>
          <th nowrap>&nbsp;</th>
          <th nowrap><f:message key="belgiumTax"/></th>
          <th nowrap><f:message key="vatCode"/></th>
          <th nowrap colspan="2"><f:message key="vatRegId"/></th>
          <th nowrap>&nbsp;</th>
          <th nowrap>&nbsp;</th>
          <th nowrap>&nbsp;</th>
        </tr>
        <tr>
          <td nowrap>&nbsp;</td>
          <td nowrap>&nbsp;</td>
          <td nowrap>&nbsp;</td>
          <td nowrap>&nbsp;</td>
          <td nowrap>&nbsp;</td>
          <td nowrap><form:select id="sel1" cssClass="selectionBox"  path="taxCode"
	             items="${command.taxCodes}" itemLabel="description" itemValue="value" onchange="javascript:onTaxCodeChange();"/>
	          <form:errors path="taxCode" cssClass="redstar"/></td>
          <td nowrap>&nbsp;</td>
          <td nowrap><form:select id="tarticle" cssClass="selectionBox"  path="taxArticleCode"
	             items="${command.taxArticleCodes}" itemLabel="description" itemValue="value" onchange="javascript:onTaxCodeChange();"/>
	            <form:errors path="taxArticleCode" cssClass="redstar"/></td>
          <td nowrap>
          	  <form:select id="sel2" cssClass="selectionBox"  path="vatCode"
	      		items="${command.vatCodes}" itemLabel="description" itemValue="value" onchange="javascript:onTaxCodeorDateChange();"/>
	      	  <form:errors path="vatCode" cssClass="redstar"/>
		  </td>
          <td nowrap colspan="2">${command.vatRegId}</td>
          <td nowrap><div align="right">
               &nbsp;&nbsp;</td>
          <td nowrap>&nbsp;</td>
          <td nowrap>&nbsp;</td>
        </tr>
        <tr>
          <th nowrap><f:message key="poNo"/></th>
          <th nowrap><f:message key="price"/></th>
          <th nowrap><f:message key="primaryBranch"/></th>
          <th nowrap><f:message key="serviceType"/></th>
          <th nowrap><f:message key="revenueSegregation"/></th>
          <th nowrap><f:message key="taxVatDate"/></th>
          <th nowrap><f:message key="taxCode"/></th>
          <th nowrap><f:message key="tax"/></th>
          <th nowrap><f:message key="taxAmt"/></th>
          <th nowrap colspan="2"><f:message key="vatCode"/></th>
          <th nowrap><f:message key="vat"/></th>
          <th nowrap><f:message key="vatAmt"/></th>
          <th nowrap><f:message key="busSteam"/></th>
          <th nowrap>&nbsp;</th>
        </tr>
        
        <c:forEach items="${command.ceInvLineItemForms}" var="lineItem" varStatus="status">
        
        <tr valign='center'>
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
          <td height="25" >
			<form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].joLineItemForm.billingAmount" disabled="disabled"/>
			<form:errors path="ceInvLineItemForms[${status.index}].joLineItemForm.billingAmount" cssClass="redstar"/>
          </td>
          <td >${lineItem.joLineItemForm.primaryBranch}</td>
          <td >&nbsp;<!-- ${lineItem.joLineItemForm.serviceType}--></td>
          <td >
          	<a href="#" onClick="javascript:updateRevenueSegregationIframeSrc(${status.index});parent.popup_show('revenuesegregation', 'revenuesegregation_drag', 'revenuesegregation_exit', 'screen-corner', 40, 80); parent.showrevenuesegregation(); parent.hideIt();parent.popupboxenable();">view</a>
          </td>
          <td height="25" valign="middle" nowrap>              
             <form:input id="tadate" cssClass="inputBox" path="ceInvLineItemForms[${status.index}].taxDate" onchange="onTaxCodeViewAllDateChange()"/>
              <form:errors path="ceInvLineItemForms[${status.index}].taxDate" cssClass="redstar" />
             <a href="#" onClick="displayCalendar(document.ceJobInvoicePreviewTab4Form.tadate[${status.index}],'${lineItem.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>
          </td>											   				
          <td height="25">
			<form:select id="sel3${status.index}" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].taxCode"
	              items="${command.taxCodes}" itemLabel="description" itemValue="value" onchange="setTaxPct('${status.index}');"/> 
	        <form:errors path="ceInvLineItemForms[${status.index}].taxCode" cssClass="redstar"/>              
	      </td>
          <td ><span >&nbsp;
                  <form:input id="taxct${status.index}" cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].taxPct"/>
                  <form:errors path="ceInvLineItemForms[${status.index}].taxPct" cssClass="redstar"/>
          	</span>
          </td>
          <td >
          	<form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].taxAmt"/>
          	<form:errors path="ceInvLineItemForms[${status.index}].taxAmt" cssClass="redstar"/>
          </td>
          <td >
          	  <form:select id="sel4${status.index}" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].vatCode"
              	items="${command.vatCodes}" itemLabel="description" itemValue="value"/>
              <form:errors path="ceInvLineItemForms[${status.index}].vatCode" cssClass="redstar"/>	
          </td>
          <td >&nbsp;</td>
          <td  nowrap='nowrap'>
          	<form:input id="vatpt${status.index}" cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].vatPct"/>
          	<form:errors path="ceInvLineItemForms[${status.index}].vatPct" cssClass="redstar"/>
          </td>
		  <td>
	          <form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].vatAmt"/>
	          <form:errors path="ceInvLineItemForms[${status.index}].vatAmt" cssClass="redstar"/>
          </td>
          <td >${lineItem.joLineItemForm.busStream}</td>
          <td  nowrap='nowrap'><div id="div" class="mindiv">
          	<a href="#"><img src="images/delete.gif" onClick="deleteLineItem('${status.index}')" alt="Delete" width="13" height="12" hspace="1px" border="0"/></a> 
          </div></td>
        </tr>
        
        
        <!-- Split Line items -->
          <c:forEach items="${lineItem.joLineItemForm.splitLineItemForms}" var="splitItem" varStatus="splitStatus">

    		<tr valign='center'>
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
          	<td height="25" >
				<form:input cssClass="inputBox" size="12" path="ceInvLineItemForms[${status.index}].joLineItemForm.price" disabled="disabled"/>
          	</td>
          	<td >${splitItem.primaryBranch}</td>
          	<td >&nbsp;<!-- ${splitItem.serviceType}--></td>
          	<td >&nbsp;</td>
          	<td height="25">&nbsp;</td>
    	    <td ><span >&nbsp;</span></td>
          	<td >&nbsp;</td>
    	    <td >&nbsp;</td>
          	<td >&nbsp;</td>
          	<td nowrap='nowrap'>&nbsp;</td>
			<td nowrap='nowrap'>&nbsp;</td>
          	<td >${lineItem.joLineItemForm.busStream}</td>
          	<td  nowrap='nowrap'><div id="div" class="mindiv">
          		<a href="#"><img src="images/delete.gif" onClick="deleteSplitLineItem('${status.index}','${splitStatus.index}')" alt="Delete" width="13" height="12" hspace="1px" border="0"/></a> 
          	</div></td>
        </tr>
          
          
          	
          </c:forEach>
		<!-- Split Line items End -->  
		<%--<ajax:updateField
            baseUrl = "phx_taxrate.htm"
            source = "sel3${status.index}"
            target = "taxct${status.index}"
            action = "sel3${status.index}"
            parameters = "taxcode={ceInvLineItemForms[${status.index}].taxCode},taxType=S,jobNumber=${command.jobOrder.jobNumber}"  
            parser="new ResponseXmlParser()"/>  		 
	  	
	  	<ajax:updateField
            baseUrl = "phx_taxrate.htm"
            source = "sel4${status.index}"
            target = "vatpt${status.index}"
            action = "sel4${status.index}"
            parameters = "taxcode={ceInvLineItemForms[${status.index}].vatCode},taxType=V,jobNumber=${command.jobOrder.jobNumber}"  
            parser="new ResponseXmlParser()"/>--%>
            
            
             <ajax:updateField
            baseUrl = "phx_taxrate.htm"
            source = "sel3${status.index}"
            target = "taxct${status.index}"
            action = "sel3${status.index}"
            parameters = "taxcode={ceInvLineItemForms[${status.index}].taxCode},taxType=S,jobNumber=${command.jobOrder.jobNumber},taxDate={ceInvLineItemForms[${status.index}].taxDate}"  
            parser="new ResponseXmlParser()"/>  		 
	  	
	  	<ajax:updateField
            baseUrl = "phx_taxrate.htm"
            source = "sel4${status.index}"
            target = "vatpt${status.index}"
            action = "sel4${status.index}"
            parameters = "taxcode={ceInvLineItemForms[${status.index}].vatCode},taxType=V,jobNumber=${command.jobOrder.jobNumber},taxDate={ceInvLineItemForms[${status.index}].taxDate}"  
            parser="new ResponseXmlParser()"/>
            
            
            
	  </c:forEach>
        
        <tr valign='center'>
          <td valign="middle" class="tdr"><f:message key="total"/>: </td>
            <td nowrap >
	            <div align="left">
	               <form:input cssClass="inputBox" size="12" path="total" disabled="true"/>
	            </div>
            </td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td height="26">&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td  nowrap='nowrap'>&nbsp;</td>
          <td nowrap >&nbsp;</td>
          <td nowrap >&nbsp;</td>
          <td nowrap >&nbsp;</td>
          </tr>
      </table>
    </div></th>
  </tr>
  <tr>
    <td height="22" colspan="4" nowrap>
      &nbsp;</td>
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
			   <form:input cssClass="inputBox" size="65" path="ceInvLineItemForms[${status.index}].joLineItemForm.description"/>
			   <form:errors path="ceInvLineItemForms[${status.index}].joLineItemForm.description" cssClass="redstar"/>
		   </td>
		</tr>
		
	    <!-- Split Line items -->
	    <c:forEach items="${lineItem.joLineItemForm.splitLineItemForms}" var="splitItem" varStatus="splitStatus">

		<tr valign='center'>
			<td height="25" align='left' nowrap>&nbsp;</td>
			<td >${splitItem.lineNumber}</td>
			<td >
			   <form:select id="sel2" cssClass="selectionBox"  path="ceInvLineItemForms[${status.index}].status"
	            items="${lineItem.invoiceStatus}" itemLabel="name" itemValue="value"/>
		   </td>
		   <td >
			   <form:input cssClass="inputBox" size="65" path="ceInvLineItemForms[${status.index}].joLineItemForm.splitLineItemForms[${splitStatus.index}].description"/>
			   <form:errors path="ceInvLineItemForms[${status.index}].joLineItemForm.splitLineItemForms[${splitStatus.index}].description" cssClass="redstar"/>
		   </td>
		</tr>
	    
		</c:forEach>
		<!-- Split Line items end -->
		
	</c:forEach>




  <tr valign='center'>
    <td height="25" align='left'>&nbsp;</td>
    <td >&nbsp;</td>
    <td >&nbsp;</td>
    <td height="45" >&nbsp;</td>
  </tr>
</table>


</form:form>
</HTML>
