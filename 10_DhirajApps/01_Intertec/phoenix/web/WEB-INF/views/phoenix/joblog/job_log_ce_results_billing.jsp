<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript"	src="js/ce/common.js"></script>

<script type="text/javascript" src="js/calendar.js?random=20060118"></script>
<script type="text/javaScript" src="js/lookup.js"></script>
<script type="text/javascript" src="js/services.js"></script>



</HEAD>
<BODY>
<form:form name="jobLogCeResultForm" method="POST"  action="phx_job_log_ce_results_billing.htm">
<form:hidden id="requestAction" path="requestAction"/>
<form:hidden id="tabNavigationTo" path="tabNavigationTo"/>
<form:hidden id="tabNavigationFrom" path="tabNavigationFrom"/>
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-left-width:0px">
	<tr>
		<th style="height:35px;" nowrap><a href="#" onclick="javascript:top.shortByField('projectOperationalStatus')"><f:message key="projectOperationalStatus"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('orderStatus')"><f:message key="orderStatus"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('customerName')"><f:message key="customerName"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('modelNumber')"><f:message key="modelNumber"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('taskName')"><f:message key="taskName"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('taskOperationalStatus')"><f:message key="taskOperationalStatus"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('currentMonthBilled')"><f:message key="currentMonthBilled"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('currentMonth')"><f:message key="currentMonth"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('rowTotalOfRev')"><f:message key="rowTotalOfRev"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('billStatus')"><f:message key="billStatus"/></a></th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('dateInvoiced')"><f:message key="dateInvoiced"/></a> </th>
		<th nowrap><a href="#" onclick="javascript:top.shortByField('amtInvoiced')"><f:message key="amtInvoiced"/></a>  </th>
	    <th nowrap><a href="#" onclick="javascript:top.shortByField('invoiceNumber')"><f:message key="invoiceNumber"/></a> </th>
	    <th nowrap><a href="#" onclick="javascript:top.shortByField('invoicedCredited')"><f:message key="invoiceCredited"/></a> </th>
	    <th nowrap><a href="#" onclick="javascript:top.shortByField('reopenDate')"><f:message key="reopenDate"/></a> </th>
	    <th nowrap><a href="#" onclick="javascript:top.shortByField('taskManager')"><f:message key="taskManager"/></a> </th>
	    <th nowrap><a href="#" onclick="javascript:top.shortByField('taskComments')"><f:message key="taskComments"/></a></th>
	    <th nowrap><a href="#" onclick="javascript:top.shortByField('taskDescription')"><f:message key="taskDescription"/></a></th>
    </tr>
<c:forEach items="${command.result}" var="jobCEResult" varStatus="status">
	
<tr valign='center'>
<td height="25" align='left' valign="middle" nowrap>
<span class="TDShadeBlue">
  <form:select path="result[${status.index}].projectOperationalStatus" cssClass="selectionBox" id="result[${status.index}].projectOperationalStatus" items="${command.operationalStatusList}" itemLabel="name"  itemValue="value"/>
</span></td>

<td align='left' valign="middle"  nowrap='nowrap'><span class="TDShadeBlue">
  <form:select path="result[${status.index}].orderStatus"  id="result[${status.index}].orderStatus" cssClass="selectionBox" items="${command.orderStatusList}" itemLabel="name"  itemValue="value"/>
</span></td>
<td align='left' valign="middle" nowrap >${jobCEResult.customerName}</td>
<td align='left' valign="middle" nowrap >${jobCEResult.modelNumber}</td>
<td valign="middle" >${jobCEResult.taskName}</td>
<td align='left' valign="middle" nowrap ><span class="TDShadeBlue">
  <form:select path="result[${status.index}].taskOperationalStatus" cssClass="selectionBox" size= "1" id="result[${status.index}].taskOperationalStatus" items="${command.taskOperationalStatusList}" itemLabel="name"  itemValue="value"/>
</span></td>
<td valign="middle" class="tdr" >
${jobCEResult.currentMonthBilled}</td>
<td valign="middle" class="tdr" >${jobCEResult.currentMonth}</td>
<td valign="middle" class="tdr" >${jobCEResult.rowTotalOfRev}</td>
<td valign="middle" class="tdr" >${jobCEResult.billStatus}</td>
<td valign="middle" class="tdr" >${jobCEResult.dateInvoiced}</td>
<td valign="middle" class="tdr" >${jobCEResult.amountInvoiced}</td>
<td valign="middle" class="tdr" >${jobCEResult.invoicedNo}</td>
<td valign="middle" class="tdr" >${jobCEResult.invoicedCredited}</td>
<td valign="middle" class="tdr" >${jobCEResult.reOpenDate}</td>
<td align='left' valign="middle"  nowrap>
  <form:input path="result[${status.index}].taskManager" id="result[${status.index}].taskManager"    cssClass="inputBox" size="25" />
  <a href="#" onClick="searchPopups('taskManager','result[${status.index}].taskManager');">  
  <img src="images/lookup.gif" alt="lookup Project Manager" width="13" height="13" border="0"></a>
</td>
<td valign="middle" >
   <form:input path="result[${status.index}].taskComments" id="result[${status.index}].taskComments"    cssClass="inputBox" size="24"/>
</td>
<td valign="middle" >
   <form:input path="result[${status.index}].taskDescription" id="result[${status.index}].taskDescription"    cssClass="inputBox" size="40"/>
</td>
</tr>
</c:forEach>
</table>
</form:form>
</BODY>
</HTML>
