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

<form:form name="ceJobInvoicePreviewTab0Form" method="POST" action="phx_ce_job_invoice_preview_tab0.htm">
<div class="redtext">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="refreshing" />
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-width:0px;">
	<tr>
		<th nowrap>&nbsp;</th>
	</tr>	
	<tr>
	  <td height="22" nowrap>&nbsp;</td>
  </tr>
	<tr>
	  <th height="120" valign="top" nowrap class="nopadding"><table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-width:1px; border-bottom:0px;">
        <tr>
          <!--  th nowrap>&nbsp;</th -->
          <th nowrap><f:message key="depInvoiceNo"/></th>
          <th style="height:24px;" nowrap><f:message key="status"/></th>
          <th nowrap><f:message key="depositAmount"/></th> 
          <th nowrap><f:message key="paymentMode"/></th>
          <th nowrap><f:message key="depositeRefNo"/></th>
          <th nowrap><f:message key="branch"/></th>
          <th nowrap><f:message key="account"/></th>
          <th nowrap><f:message key="busSteam"/></th>
          <th nowrap><f:message key="deptId"/></th>
        </tr>
        
        <c:forEach items="${command.depositInvoiceForms}" var="depInvoice" varStatus="status">
	        <tr>
	        <!--  
	          <td align='left' nowrap>
	          	<form:checkbox id="checkbox1${status.index}"  path="depositInvoiceForms[${status.index}].selected"/>
	          </td>
	         -->
	          <td height="25" valign="middle" >&nbsp;${depInvoice.depInvoiceNumber}</td>
	          <td>${command.depositInvoiceForms[status.index].depositInvoice.status }</td>
	          <!--  
	          <td >
      			  <form:select id="sel1" cssClass="selectionBox"  path="depositInvoiceForms[${status.index}].status"
     	             items="${depInvoice.invoiceStatus}" itemLabel="name" itemValue="value"/>
     	          <form:errors path="depositInvoiceForms[${status.index}].status" cssClass="redstar"/> 
	          </td >
	          -->
	          <td nowrap>&nbsp;${depInvoice.depositAmount}</td>
	          <!--  
	          <td nowrap >
	          	<form:input cssClass="inputBox" size="12" path="depositInvoiceForms[${status.index}].depositAmount"/>
	          	<form:errors path="depositInvoiceForms[${status.index}].depositAmount" cssClass="redstar"/>
	          </td>
	          -->
	          <td>${command.depositInvoiceForms[status.index].depositInvoice.paymentType }</td>
	          <!-- 
	          <td >
      			  <form:select id="sel2" cssClass="selectionBox"  path="depositInvoiceForms[${status.index}].paymentType"
     	             items="${depInvoice.paymentTypes}" itemLabel="name" itemValue="value"/>
     	          <form:errors path="depositInvoiceForms[${status.index}].paymentType" cssClass="redstar"/>   
	          </td>
	          -->
	          <td >&nbsp;${depInvoice.depositReferenceNo}</td>
	          <td >&nbsp;${depInvoice.primaryBranch}</td>
	          <td >&nbsp;${depInvoice.account}</td>
	          <td >&nbsp;${depInvoice.busStream}</td>
	          <td >&nbsp;${depInvoice.deptId}</td>
	        </tr>
        </c:forEach>
      </table></th>
	</tr>
</table>
</form:form> 
</HTML>
